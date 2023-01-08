/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coderule.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.coderule.dao.CodeMaxSerialDao;
import com.skyeye.coderule.dao.CodeRuleDao;
import com.skyeye.coderule.entity.CodeMaxSerial;
import com.skyeye.coderule.entity.CodeRule;
import com.skyeye.coderule.entity.util.CodePattern;
import com.skyeye.coderule.entity.util.FeatureScriptUtil;
import com.skyeye.coderule.service.CodeRuleService;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.coderule.entity.CodeMation;
import com.skyeye.exception.CustomException;
import com.skyeye.jedis.util.RedisLock;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class CodeRuleServiceImpl extends SkyeyeBusinessServiceImpl<CodeRuleDao, CodeRule> implements CodeRuleService {

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
    private Executor codeRuleExecutor;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = codeRuleDao.queryCodeRuleList(commonPageInfo);
        return beans;
    }

    @Override
    public void validatorEntity(CodeRule entity) {
        QueryWrapper<CodeRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.toColumns(CodeRule::getCodeNum), entity.getCodeNum())
                .or().eq(MybatisPlusUtil.toColumns(CodeRule::getName), entity.getName())
                .or().eq(MybatisPlusUtil.toColumns(CodeRule::getPattern), entity.getPattern()));
        if (StringUtils.isNotEmpty(entity.getId())) {
            queryWrapper.ne(CommonConstants.ID, entity.getId());
        }
        CodeRule checkCodeRule = codeRuleDao.selectOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(checkCodeRule)) {
            throw new CustomException("this data is non-existent.");
        }
        CodePattern.validationCodeRulePatten(entity.getPattern());
        this.validFeatureScript(entity.getFeatureScript());
    }

    private void validFeatureScript(String featureScript) {
        if (StringUtils.isNotBlank(featureScript)) {
            if (!JSONUtil.isJsonObj(featureScript)) {
                throw new CustomException("特征码附加脚本格式错误，未成功解析成json");
            }
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
        CodeRule codeRuleMation = selectById(ruleCode);
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
        CodeMaxSerial codeMaxSerialMation = new CodeMaxSerial();
        codeMaxSerialMation.setFeatureCode(featureCode);
        codeMaxSerialMation.setCodeRuleId(relationId);
        codeMaxSerialMation.setSerialCode(String.valueOf(size));
        DataCommonUtil.setCommonDataByGenericity(codeMaxSerialMation, CommonConstants.ADMIN_USER_ID);
        DataCommonUtil.setId(codeMaxSerialMation);
        codeMaxSerialDao.insert(codeMaxSerialMation);
    }

    private Map<String, Object> getCodeMaxSerial(String featureCode, String relationId) {
        String cacheKey = getCacheKey(featureCode, relationId);
        return redisCache.getMap(cacheKey, key -> {
            QueryWrapper<CodeMaxSerial> wrapper = new QueryWrapper<>();
            wrapper.eq(MybatisPlusUtil.toColumns(CodeMaxSerial::getFeatureCode), featureCode);
            wrapper.eq(MybatisPlusUtil.toColumns(CodeMaxSerial::getCodeRuleId), relationId);
            return codeMaxSerialDao.selectOne(wrapper);
        }, RedisConstants.THIRTY_DAY_SECONDS);
    }

    private void refresh(String featureCode, String relationId, String serialCode) {
        // 异步更新数据库
        codeRuleExecutor.execute(() -> {
            UpdateWrapper<CodeMaxSerial> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set(MybatisPlusUtil.toColumns(CodeMaxSerial::getSerialCode), serialCode);
            updateWrapper.set(MybatisPlusUtil.toColumns(CodeMaxSerial::getLastUpdateTime), DateUtil.getTimeAndToString());
            updateWrapper.eq(MybatisPlusUtil.toColumns(CodeMaxSerial::getCodeRuleId), relationId);
            updateWrapper.eq(MybatisPlusUtil.toColumns(CodeMaxSerial::getFeatureCode), featureCode);
            codeMaxSerialDao.update(null, updateWrapper);
        });

        // 更新缓存
        CodeMaxSerial codeMaxSerialMation = new CodeMaxSerial();
        codeMaxSerialMation.setFeatureCode(featureCode);
        codeMaxSerialMation.setCodeRuleId(relationId);
        codeMaxSerialMation.setSerialCode(serialCode);
        String cacheKey = getCacheKey(featureCode, relationId);
        jedisClientService.set(cacheKey, JSON.toJSONString(codeMaxSerialMation), RedisConstants.THIRTY_DAY_SECONDS);
    }

    private String getCacheKey(String featureCode, String relationId) {
        String cacheKey = String.format(Locale.ROOT, "%s:maxSerial:%s_%s", CacheConstants.CODE_RULE_CACHE_KEY, featureCode, relationId);
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
        List<CodeRule> codeRuleMationList = list();
        outputObject.setBeans(codeRuleMationList);
        outputObject.settotal(codeRuleMationList.size());
    }

}
