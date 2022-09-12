/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SkyeyeClassEnumDao;
import com.skyeye.eve.entity.classenum.SkyeyeClassEnumApiMation;
import com.skyeye.eve.entity.classenum.SkyeyeClassEnumMation;
import com.skyeye.eve.service.SkyeyeClassEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
