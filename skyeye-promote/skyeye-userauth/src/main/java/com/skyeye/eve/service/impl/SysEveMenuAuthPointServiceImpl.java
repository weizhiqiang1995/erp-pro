/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveMenuAuthPointDao;
import com.skyeye.eve.service.SysEveMenuAuthPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysEveMenuAuthPointServiceImpl implements SysEveMenuAuthPointService {

    @Autowired
    private SysEveMenuAuthPointDao sysEveMenuAuthPointDao;

    /**
     * 获取菜单权限点列表根据菜单id
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveMenuAuthPointDao.querySysEveMenuAuthPointListByMenuId(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());

    }

    /**
     * 添加菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveMenuAuthPointDao.querySysEveMenuAuthPointMationByAuthName(map);
        if (bean == null) {
            map.put("menuNum", DateUtil.getTimeStampAndToString());
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysEveMenuAuthPointDao.insertSysEveMenuAuthPointMation(map);
        } else {
            outputObject.setreturnMessage("该菜单下已存在该名称的权限点，请进行更改.");
        }
    }

    /**
     * 编辑菜单权限点时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveMenuAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveMenuAuthPointDao.querySysEveMenuAuthPointMationToEditById(map);
        outputObject.setBean(bean);
    }

    /**
     * 编辑菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveMenuAuthPointDao.querySysEveMenuAuthPointMationByAuthNameAndId(map);
        if (bean == null) {
            sysEveMenuAuthPointDao.editSysEveMenuAuthPointMationById(map);
        } else {
            outputObject.setreturnMessage("该菜单下已存在该名称的权限点，请进行更改.");
        }
    }

    /**
     * 删除菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        sysEveMenuAuthPointDao.deleteSysEveMenuAuthPointMationById(map);
    }


}
