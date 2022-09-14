/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyeye.common.base.classenum.dto.SkyeyeEnumDto;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SkyeyeClassEnumDao;
import com.skyeye.eve.entity.classenum.SkyeyeClassEnumApiMation;
import com.skyeye.eve.entity.classenum.SkyeyeClassEnumMation;
import com.skyeye.eve.service.SkyeyeClassEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public void writeClassEnum(InputObject inputObject, OutputObject outputObject) {
        SkyeyeClassEnumApiMation skyeyeClassEnumApiMation = inputObject.getParams(SkyeyeClassEnumApiMation.class);

        // 根据服务名删除枚举信息
        QueryWrapper<SkyeyeClassEnumMation> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassEnumMation::getSpringApplicationName), skyeyeClassEnumApiMation.getSpringApplicationName());
        remove(wrapper);

        // 解析数据并添加
        List<SkyeyeClassEnumMation> skyeyeClassEnumMationList = new ArrayList<>();
        skyeyeClassEnumApiMation.getValueList().forEach((className, enumDto) -> {
            SkyeyeClassEnumMation skyeyeClassEnumMation = new SkyeyeClassEnumMation();
            skyeyeClassEnumMation.setClassName(className);
            skyeyeClassEnumMation.setValueList(enumDto);
            skyeyeClassEnumMation.setSpringApplicationName(skyeyeClassEnumApiMation.getSpringApplicationName());
            DataCommonUtil.setCommonDataByGenericity(skyeyeClassEnumMation, "0dc9dd4cd4d446ae9455215fe753c44e");
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
        QueryWrapper<SkyeyeClassEnumMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassEnumMation::getClassName), className);
        SkyeyeClassEnumMation skyeyeClassEnumMation = skyeyeClassEnumDao.selectOne(queryWrapper);
        // 只加载可以展示的数据
        List<Map<String, Object>> skyeyeEnumDtoList = skyeyeClassEnumMation.getValueList()
            .stream().filter(bean -> filterSkyeyeEnumDto(bean, filterKey))
            .map(bean -> {
                Map<String, Object> item = new HashMap<>();
                item.put("id", bean.getKey());
                item.put("name", bean.getValue());
                item.put("isDefault", bean.getIsDefault());
                return item;
            }).collect(Collectors.toList());

        Map<String, String> skyeyeEnumDtoMap = skyeyeClassEnumMation.getValueList()
            .stream().filter(bean -> filterSkyeyeEnumDto(bean, filterKey))
            .collect(Collectors.toMap(bean -> String.valueOf(bean.getKey()), bean -> bean.getValue()));

        outputObject.setBean(skyeyeEnumDtoMap);
        outputObject.setBeans(skyeyeEnumDtoList);
        outputObject.settotal(skyeyeEnumDtoList.size());
    }

    private Boolean filterSkyeyeEnumDto(SkyeyeEnumDto skyeyeEnumDto, String filterKey) {
        if (ToolUtil.isBlank(filterKey)) {
            return skyeyeEnumDto.getShow();
        }
        List<String> filterKeyList = Arrays.asList(filterKey.split(CommonCharConstants.COMMA_MARK));
        // 需要过滤出来的数据并且是可以显示的数据
        if (filterKeyList.contains(String.valueOf(skyeyeEnumDto.getKey())) && skyeyeEnumDto.getShow()) {
            return true;
        }
        return false;
    }

}
