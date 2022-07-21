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
import com.skyeye.eve.dao.AppWorkPageAuthPointDao;
import com.skyeye.eve.service.AppWorkPageAuthPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AppWorkPageAuthPointServiceImpl
 * @Description: APP菜单权限点管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class AppWorkPageAuthPointServiceImpl implements AppWorkPageAuthPointService {

    @Autowired
    private AppWorkPageAuthPointDao appWorkPageAuthPointDao;

    /**
     * 根据菜单id获取菜单权限点列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAppWorkPageAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = appWorkPageAuthPointDao.queryAppWorkPageAuthPointListByMenuId(map);
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
    public void insertAppWorkPageAuthPointMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = appWorkPageAuthPointDao.queryAppWorkPageAuthPointMationByAuthName(map);
        if (bean == null) {
            map.put("menuNum", DateUtil.getTimeStampAndToString());
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            appWorkPageAuthPointDao.insertAppWorkPageAuthPointMation(map);
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
    public void queryAppWorkPageAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = appWorkPageAuthPointDao.queryAppWorkPageAuthPointMationToEditById(map);
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
    public void editAppWorkPageAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = appWorkPageAuthPointDao.queryAppWorkPageAuthPointMationByAuthNameAndId(map);
        if (bean == null) {
            appWorkPageAuthPointDao.editAppWorkPageAuthPointMationById(map);
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
    public void deleteAppWorkPageAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        appWorkPageAuthPointDao.deleteAppWorkPageAuthPointMationById(map);
    }

}
