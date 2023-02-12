/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.win.dao.SysEveDesktopDao;
import com.skyeye.win.entity.SysDesktop;
import com.skyeye.win.service.SysEveDesktopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SkyeyeService(name = "桌面信息管理", groupName = "基础模块")
public class SysEveDesktopServiceImpl extends SkyeyeBusinessServiceImpl<SysEveDesktopDao, SysDesktop> implements SysEveDesktopService {

    @Autowired
    private SysEveDesktopDao sysEveDesktopDao;

    @Override
    protected List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        commonPageInfo.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
        List<Map<String, Object>> beans = sysEveDesktopDao.querySysDesktopList(commonPageInfo);
        return beans;
    }

    @Override
    public void validatorEntity(SysDesktop entity) {
        QueryWrapper<SysDesktop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SysDesktop::getDeleteFlag), DeleteFlagEnum.NOT_DELETE.getKey());
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.toColumns(SysDesktop::getName), entity.getName())
                .or().eq(MybatisPlusUtil.toColumns(SysDesktop::getDesktopCode), entity.getDesktopCode()));
        if (StringUtils.isNotEmpty(entity.getId())) {
            queryWrapper.ne(CommonConstants.ID, entity.getId());
        }
        SysDesktop checkSysEveDesktop = getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(checkSysEveDesktop)) {
            throw new CustomException("该桌面名称已存在，请更换.");
        }
    }

    @Override
    protected void createPrepose(SysDesktop entity) {
        Integer nextOrderBy = sysEveDesktopDao.querySysEveDesktopMaxOrderBum();
        entity.setOrderBy(nextOrderBy);
    }

    @Override
    protected void deletePreExecution(String id) {
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopStateAndMenuNumById(id);
        if (!"0".equals(bean.get("allNum").toString())) {
            throw new CustomException("该桌面下包含有子菜单，无法删除.");
        }
    }

    /**
     * 桌面上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDesktopMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 获取当前数据的同级分类下的上一条数据
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopUpMationById(map);
        if (CollectionUtils.isEmpty(bean)) {
            outputObject.setreturnMessage("当前桌面名称已经是首位，无须进行上移。");
        } else {
            // 进行位置交换
            map.put("upOrderBy", bean.get("prevOrderBy"));
            bean.put("upOrderBy", bean.get("thisOrderBy"));
            sysEveDesktopDao.editSysDesktopMationOrderNumUpById(map);
            sysEveDesktopDao.editSysDesktopMationOrderNumUpById(bean);
        }
    }

    /**
     * 桌面下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDesktopMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 获取当前数据的同级分类下的下一条数据
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopDownMationById(map);
        if (CollectionUtils.isEmpty(bean)) {
            outputObject.setreturnMessage("当前桌面名称已经是末位，无须进行下移。");
        } else {
            //进行位置交换
            map.put("upOrderBy", bean.get("prevOrderBy"));
            bean.put("upOrderBy", bean.get("thisOrderBy"));
            sysEveDesktopDao.editSysDesktopMationOrderNumUpById(map);
            sysEveDesktopDao.editSysDesktopMationOrderNumUpById(bean);
        }
    }

    /**
     * 获取全部的桌面用于系统菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllSysDesktopList(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> beans = queryAllDataForMap();
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 一键移除所有菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void removeAllSysEveMenuByDesktopId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("desktopId", "winfixedpage00000000");
        sysEveDesktopDao.removeAllSysEveMenuByDesktopId(map);
    }

    @Override
    public List<Map<String, Object>> queryAllDataForMap() {
        List<Map<String, Object>> desktopList = super.queryAllDataForMap();
        desktopList.forEach(desktop -> {
            desktop.put("pId", "0");
            desktop.put("sysName", "基础系统");
            desktop.put("pageType", "桌面");
            desktop.put("type", "page");
        });
        desktopList = desktopList.stream()
            .sorted(Comparator.comparing(bean -> Integer.parseInt(bean.get("orderBy").toString()))).collect(Collectors.toList());
        return desktopList;
    }
}
