/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyeye.clazz.dao.SkyeyeClassEnumDao;
import com.skyeye.clazz.entity.classenum.SkyeyeClassEnumApiMation;
import com.skyeye.clazz.entity.classenum.SkyeyeClassEnumMation;
import com.skyeye.clazz.service.SkyeyeClassEnumService;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SkyeyeClassEnumServiceImpl
 * @Description: 基本框架---具备某个特征的枚举类管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/11 20:26
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SkyeyeClassEnumServiceImpl extends ServiceImpl<SkyeyeClassEnumDao, SkyeyeClassEnumMation> implements SkyeyeClassEnumService {

    @Autowired
    private SkyeyeClassEnumDao skyeyeClassEnumDao;

    /**
     * 批量新增枚举类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeClassEnum(InputObject inputObject, OutputObject outputObject) {
        SkyeyeClassEnumApiMation skyeyeClassEnumApiMation = inputObject.getParams(SkyeyeClassEnumApiMation.class);

        // 根据服务名删除枚举信息
        QueryWrapper<SkyeyeClassEnumMation> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassEnumMation::getAppId), skyeyeClassEnumApiMation.getAppId());
        remove(wrapper);

        // 解析数据并添加
        List<SkyeyeClassEnumMation> skyeyeClassEnumMationList = new ArrayList<>();
        skyeyeClassEnumApiMation.getValueList().forEach((className, enumDto) -> {
            SkyeyeClassEnumMation skyeyeClassEnumMation = new SkyeyeClassEnumMation();
            skyeyeClassEnumMation.setClassName(className);
            skyeyeClassEnumMation.setValueList(enumDto);
            skyeyeClassEnumMation.setAppId(skyeyeClassEnumApiMation.getAppId());
            DataCommonUtil.setCommonDataByGenericity(skyeyeClassEnumMation, CommonConstants.ADMIN_USER_ID);
            DataCommonUtil.setId(skyeyeClassEnumMation);
            skyeyeClassEnumMationList.add(skyeyeClassEnumMation);
        });

        saveBatch(skyeyeClassEnumMationList);
    }

    /**
     * 根据className获取可以展示在界面上的枚举数据信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void getEnumDataByClassName(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        String filterKey = params.get("filterKey").toString();
        String filterValue = params.get("filterValue").toString();
        List<Map<String, Object>> skyeyeEnumDtoList = queryEnumDataList(className, filterKey, filterValue);

        outputObject.setBeans(skyeyeEnumDtoList);
        outputObject.settotal(skyeyeEnumDtoList.size());
    }

    @Override
    public List<Map<String, Object>> queryEnumDataList(String className, String filterKey, String filterValue) {
        QueryWrapper<SkyeyeClassEnumMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassEnumMation::getClassName), className);
        SkyeyeClassEnumMation skyeyeClassEnumMation = skyeyeClassEnumDao.selectOne(queryWrapper);
        // 只加载可以展示的数据
        List<Map<String, Object>> skyeyeEnumDtoList = skyeyeClassEnumMation.getValueList()
            .stream().filter(bean -> filterSkyeyeEnumDto(bean, filterKey, filterValue)).collect(Collectors.toList());
        return skyeyeEnumDtoList;
    }

    private Boolean filterSkyeyeEnumDto(Map<String, Object> enumValueMap, String filterKey, String filterValue) {
        Boolean show = (Boolean) enumValueMap.get("show");
        if (ToolUtil.isBlank(filterValue)) {
            return show;
        }
        List<String> filterValueList = Arrays.asList(filterValue.split(CommonCharConstants.COMMA_MARK));
        // 需要过滤出来的数据并且是可以显示的数据
        if (filterValueList.contains(String.valueOf(enumValueMap.get(filterKey))) && show) {
            return true;
        }
        return false;
    }

}
