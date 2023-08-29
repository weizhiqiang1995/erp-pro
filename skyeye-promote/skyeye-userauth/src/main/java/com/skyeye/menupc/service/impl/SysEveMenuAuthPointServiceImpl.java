/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.menupc.classenum.MenuPointType;
import com.skyeye.menupc.dao.SysEveMenuAuthPointDao;
import com.skyeye.menupc.entity.SysMenuAuthPoint;
import com.skyeye.menupc.service.SysEveMenuAuthPointService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveMenuAuthPointServiceImpl
 * @Description: 菜单权限点管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/23 19:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "菜单权限点管理", groupName = "菜单管理")
public class SysEveMenuAuthPointServiceImpl extends SkyeyeBusinessServiceImpl<SysEveMenuAuthPointDao, SysMenuAuthPoint> implements SysEveMenuAuthPointService {

    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        TableSelectInfo selectInfo = inputObject.getParams(TableSelectInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryMenuAuthPointList(selectInfo);
        beans.forEach(bean -> {
            bean.put("typeName", MenuPointType.getTypeName(Integer.parseInt(bean.get("type").toString())));
        });
        return beans;
    }

    @Override
    protected void createPrepose(SysMenuAuthPoint entity) {
        entity.setMenuNum(String.valueOf(DateUtil.getTimeStampAndToString()));
    }

    @Override
    protected void validatorEntity(SysMenuAuthPoint entity) {
        super.validatorEntity(entity);
        QueryWrapper<SysMenuAuthPoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.toColumns(SysMenuAuthPoint::getName), entity.getName())
                .or().eq(MybatisPlusUtil.toColumns(SysMenuAuthPoint::getAuthMenu), entity.getAuthMenu()));
        queryWrapper.eq(MybatisPlusUtil.toColumns(SysMenuAuthPoint::getObjectId), entity.getObjectId());
        if (StringUtils.isNotEmpty(entity.getId())) {
            queryWrapper.ne(CommonConstants.ID, entity.getId());
        }
        SysMenuAuthPoint checkSysMenuAuthPoint = getOne(queryWrapper);

        if (!ObjectUtils.isEmpty(checkSysMenuAuthPoint)) {
            throw new CustomException("该菜单下已存在该名称的权限点，请进行更改。");
        }
    }

}
