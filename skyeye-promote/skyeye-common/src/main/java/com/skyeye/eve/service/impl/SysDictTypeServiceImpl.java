/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SysDictTypeDao;
import com.skyeye.eve.entity.dict.SysDictType;
import com.skyeye.eve.service.SysDictTypeService;
import com.skyeye.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SysDictTypeServiceImpl
 * @Description: 数据字典类型管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:31
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysDictTypeServiceImpl extends SkyeyeBusinessServiceImpl<SysDictTypeDao, SysDictType> implements SysDictTypeService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<SysDictType> beans = skyeyeBaseMapper.queryDictTypeList(commonPageInfo);
        return beans.stream().map(bean -> BeanUtil.beanToMap(bean)).collect(Collectors.toList());
    }

    @Override
    public void validatorEntity(SysDictType entity) {
        // 根据dictName和dictCode进行校验
        QueryWrapper<SysDictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.toColumns(SysDictType::getDictName), entity.getDictName())
                .or().eq(MybatisPlusUtil.toColumns(SysDictType::getDictCode), entity.getDictCode()));
        if (StringUtils.isNotEmpty(entity.getId())) {
            queryWrapper.ne(CommonConstants.ID, entity.getId());
        }
        SysDictType checkDictType = getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(checkDictType)) {
            throw new CustomException("this name or dictCode is exist.");
        }
    }

    /**
     * 根据状态获取数据字典类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDictTypeListByEnabled(InputObject inputObject, OutputObject outputObject) {
        String enabled = inputObject.getParams().get("enabled").toString();
        QueryWrapper<SysDictType> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(enabled)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(SysDictType::getEnabled), enabled);
        }
        List<SysDictType> dictTypeList = list(queryWrapper);
        List<Map<String, Object>> result = new ArrayList<>();
        for (SysDictType bean : dictTypeList) {
            Map<String, Object> map = BeanUtil.beanToMap(bean);
            map.put("name", map.get("dictName"));
            map.put("pId", CommonNumConstants.NUM_ZERO);
            result.add(map);
        }
        outputObject.setBeans(result);
        outputObject.settotal(result.size());
    }

}
