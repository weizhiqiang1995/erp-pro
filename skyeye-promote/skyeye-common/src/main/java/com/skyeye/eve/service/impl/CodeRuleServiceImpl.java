/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.CodeMaxSerialDao;
import com.skyeye.eve.dao.CodeRuleDao;
import com.skyeye.eve.entity.coderule.CodeMaxSerialMation;
import com.skyeye.eve.entity.coderule.CodeRuleMation;
import com.skyeye.eve.entity.coderule.util.CodePattern;
import com.skyeye.eve.entity.coderule.util.FeatureScriptUtil;
import com.skyeye.eve.rest.coderule.CodeMation;
import com.skyeye.eve.service.CodeRuleService;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.exception.CustomException;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.jedis.util.RedisLock;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.Executor;

/**
 * @ClassName: CodeRuleServiceImpl
 * @Description: 编码规则管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class CodeRuleServiceImpl extends ServiceImpl<CodeRuleDao, CodeRuleMation> implements CodeRuleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeRuleServiceImpl.class);

    @Autowired
    private CodeRuleDao codeRuleDao;

    @Autowired
    private CodeMaxSerialDao codeMaxSerialDao;

    private static final int SERIAL_NO_MAX_SIZE = 5000;

    private static final int SERIAL_NO_MIN_SIZE = 1;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JedisClientService jedisClient;

    @Autowired
    private IAuthUserService iAuthUserService;

    @Autowired
    private Executor codeRuleExecutor;

    /**
     * 获取编码规则列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCodeRuleList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        List<Map<String, Object>> beans = codeRuleDao.queryCodeRuleList(commonPageInfo);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增/编辑编码规则
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeCodeRuleMation(InputObject inputObject, OutputObject outputObject) {
        CodeRuleMation codeRule = inputObject.getParams(CodeRuleMation.class);
        // 1. 校验
        QueryWrapper<CodeRuleMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.toColumns(CodeRuleMation::getCodeNum), codeRule.getCodeNum())
                .or().eq(MybatisPlusUtil.toColumns(CodeRuleMation::getName), codeRule.getName())
                .or().eq(MybatisPlusUtil.toColumns(CodeRuleMation::getPattern), codeRule.getPattern()));
        if (StringUtils.isNotEmpty(codeRule.getId())) {
            queryWrapper.ne(CommonConstants.ID, codeRule.getId());
        }
        CodeRuleMation checkCodeRule = codeRuleDao.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(checkCodeRule)) {
            CodePattern.validationCodeRulePatten(codeRule.getPattern());
            this.validFeatureScript(codeRule.getFeatureScript());
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(codeRule.getId())) {
                LOGGER.info("update codeRule data, id is {}", codeRule.getId());
                codeRuleDao.updateById(codeRule);
            } else {
                DataCommonUtil.setId(codeRule);
                LOGGER.info("insert codeRule data, id is {}", codeRule.getId());
                codeRuleDao.insert(codeRule);
            }
        } else {
            outputObject.setreturnMessage("this data is non-existent.");
        }
    }

    private void validFeatureScript(String featureScript) {
        if (StringUtils.isNotBlank(featureScript)) {
            if (!JSONUtil.isJsonObj(featureScript)) {
                throw new CustomException("特征码附加脚本格式错误，未成功解析成json");
            } else {
                JSONObject parse = JSONUtil.parseObj(featureScript);
                parse.keySet().forEach(key -> {
                    JSONObject jsonObject = parse.getJSONObject(key);
                    if (!jsonObject.containsKey("type")) {
                        throw new CustomException("特征码附加脚本中未包含脚本类型");
                    }
                    String type = jsonObject.getStr("type");
                    if ("map".equalsIgnoreCase(type)) {
                        String content = jsonObject.getStr("content");
                        if (!JSONUtil.isJsonObj(content)) {
                            throw new CustomException("特征码扩展脚本map映射内容错误");
                        }
                    } else if ("javaScript".equalsIgnoreCase(type)) {
                        String content = jsonObject.getStr("content");
                        if (!FeatureScriptUtil.testScript(content)) {
                            throw new CustomException("特征码扩展脚本JavaScript脚本内容错误");
                        }
                    } else {
                        throw new CustomException("特征码扩展脚本中不包含" + type + "的实现");
                    }
                });
            }
        }
    }

    /**
     * 根据ID获取编码规则信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCodeRuleMationById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        CodeRuleMation codeRule = codeRuleDao.selectById(id);
        outputObject.setBean(codeRule);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据ID删除编码规则
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteCodeRuleMationById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        codeRuleDao.deleteById(id);
    }

    private CodeRuleMation getCodeRuleBycode(String code) {
        QueryWrapper<CodeRuleMation> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(CodeRuleMation::getCodeNum), code);
        return codeRuleDao.selectOne(wrapper);
    }

    /**
     * 获取编码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void getNextCodes(InputObject inputObject, OutputObject outputObject) {
        CodeMation codeMation = inputObject.getParams(CodeMation.class);
        List<String> codes = this.getNextCodes(codeMation.getRuleCode(), codeMation.getBusinessData(), codeMation.getSize());
        Map<String, Object> result = new HashMap<>();
        result.put("list", codes);
        outputObject.setBean(result);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    private List<String> getNextCodes(String ruleCode, Map<String, Object> businessData, int size) {
        if (size < SERIAL_NO_MIN_SIZE) {
            size = SERIAL_NO_MIN_SIZE;
        }
        if (size > SERIAL_NO_MAX_SIZE) {
            size = SERIAL_NO_MAX_SIZE;
        }
        CodeRuleMation codeRuleMation = this.getCodeRuleBycode(ruleCode);
        if (codeRuleMation == null) {
            throw new CustomException("未找到命名规则或附加关系");
        }
        // 解析编码规则
        CodePattern codePattern = new CodePattern(codeRuleMation.getPattern());
        if (!codePattern.isValid()) {
            throw new CustomException("解析命名规则模式失败");
        }

        // 设置特征码
        codePattern.setFeatureCode(businessData, codeRuleMation.getFeatureScript());
        return getCodeByFeature(codePattern, codeRuleMation.getId(), size);
    }

    /**
     * 根据特征码获取编号
     *
     * @param codePattern 命名规则生成器
     * @param nameRuleId  编码规则id
     * @param size        获取的序列码数量
     * @return list
     */
    private List<String> getCodeByFeature(CodePattern codePattern, String nameRuleId, int size) {
        List<String> codes = new ArrayList<>(size);
        String featureCode = codePattern.getFeatureCode();
        LOGGER.info(">>>>>>>>>>featureCode==== " + featureCode);
        // 初始redis没有值时，获取数据库值或插入数据到数据库。如果有值，则直接获取序列号且加一。　如果初始没值且高并发，在循环中处理，避免重复
        long serialCode = getMaxSerialCode(featureCode, nameRuleId, size);
        LOGGER.info("serialCode===========" + serialCode);
        for (long i = serialCode - size + 1; i <= serialCode; i++) {
            String code = codePattern.getCode(i);
            codes.add(code);
        }
        return codes;
    }

    private long getMaxSerialCode(String featureCode, String relationId, int size) {
        Long serialCode = null;
        String lockKey = "";
        RedisLock lock = new RedisLock(lockKey);
        try {
            if (!lock.lock()) {
                // 加锁失败
                throw new CustomException("当前并发量较大，请稍后再次尝试.");
            }
            Map<String, Object> codeMaxSerial = this.getCodeMaxSerial(featureCode, relationId);
            if (CollectionUtils.isEmpty(codeMaxSerial)) {
                // 创建maxSerial数据
                serialCode = (long) size;
                this.saveCodeMaxSerial(featureCode, relationId, size);
            } else {
                serialCode = Long.parseLong(codeMaxSerial.get("serialCode").toString()) + size;
            }
            // 刷新缓存
            this.refresh(featureCode, relationId, String.valueOf(serialCode));
        } catch (Exception ex) {
            LOGGER.error("查询最大序列码出错！", ex);
            throw new CustomException("获取编码失败");
        } finally {
            lock.unlock();
        }
        return serialCode;
    }

    private void saveCodeMaxSerial(String featureCode, String relationId, int size) {
        CodeMaxSerialMation codeMaxSerialMation = new CodeMaxSerialMation();
        codeMaxSerialMation.setFeatureCode(featureCode);
        codeMaxSerialMation.setCodeRuleId(relationId);
        codeMaxSerialMation.setSerialCode(String.valueOf(size));
        DataCommonUtil.setCommonDataByGenericity(codeMaxSerialMation, "0dc9dd4cd4d446ae9455215fe753c44e");
        DataCommonUtil.setId(codeMaxSerialMation);
        codeMaxSerialDao.insert(codeMaxSerialMation);
    }

    private Map<String, Object> getCodeMaxSerial(String featureCode, String relationId) {
        String cacheKey = getCacheKey(featureCode, relationId);
        return redisCache.getMap(cacheKey, key -> {
            QueryWrapper<CodeMaxSerialMation> wrapper = new QueryWrapper<>();
            wrapper.eq(MybatisPlusUtil.toColumns(CodeMaxSerialMation::getFeatureCode), featureCode);
            wrapper.eq(MybatisPlusUtil.toColumns(CodeMaxSerialMation::getCodeRuleId), relationId);
            return codeMaxSerialDao.selectOne(wrapper);
        }, RedisConstants.THIRTY_DAY_SECONDS);
    }

    private void refresh(String featureCode, String relationId, String serialCode) {
        // 异步更新数据库
        codeRuleExecutor.execute(() -> {
            UpdateWrapper<CodeMaxSerialMation> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set(MybatisPlusUtil.toColumns(CodeMaxSerialMation::getSerialCode), serialCode);
            updateWrapper.set(MybatisPlusUtil.toColumns(CodeMaxSerialMation::getLastUpdateTime), DateUtil.getTimeAndToString());
            updateWrapper.eq(MybatisPlusUtil.toColumns(CodeMaxSerialMation::getCodeRuleId), relationId);
            updateWrapper.eq(MybatisPlusUtil.toColumns(CodeMaxSerialMation::getFeatureCode), featureCode);
            codeMaxSerialDao.update(null, updateWrapper);
        });

        // 更新缓存
        CodeMaxSerialMation codeMaxSerialMation = new CodeMaxSerialMation();
        codeMaxSerialMation.setFeatureCode(featureCode);
        codeMaxSerialMation.setCodeRuleId(relationId);
        codeMaxSerialMation.setSerialCode(serialCode);
        String cacheKey = getCacheKey(featureCode, relationId);
        jedisClient.set(cacheKey, JSON.toJSONString(codeMaxSerialMation), RedisConstants.THIRTY_DAY_SECONDS);
    }

    private String getCacheKey(String featureCode, String relationId) {
        String cacheKey = String.format(Locale.ROOT, "codeMaxSerial:%s_%s", featureCode, relationId);
        return cacheKey;
    }

    /**
     * 获取所有的编码规则
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void getAllCodeRuleList(InputObject inputObject, OutputObject outputObject) {
        List<CodeRuleMation> codeRuleMationList = super.list();
        outputObject.setBeans(codeRuleMationList);
        outputObject.settotal(codeRuleMationList.size());
    }

}
