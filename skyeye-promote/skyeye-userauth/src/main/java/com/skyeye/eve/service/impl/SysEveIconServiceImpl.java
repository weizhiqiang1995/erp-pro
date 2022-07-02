/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveIconDao;
import com.skyeye.eve.service.SysEveIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class SysEveIconServiceImpl implements SysEveIconService {

    @Autowired
    private SysEveIconDao sysEveIconDao;

    /**
     * 获取ICON列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysIconList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveIconDao.querySysIconList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 添加ICON信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysIconMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveIconDao.checkSysIconMation(map);
        if (CollectionUtils.isEmpty(bean)) {
            Map<String, Object> user = inputObject.getLogParams();
            map.put("id", ToolUtil.getSurFaceId());
            map.put("createId", user.get("id"));
            map.put("createTime", DateUtil.getTimeAndToString());
            sysEveIconDao.insertSysIconMation(map);
        } else {
            outputObject.setreturnMessage("该ICON属性已存在，不可进行二次保存");
        }
    }

    /**
     * 删除ICON信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysIconMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        sysEveIconDao.deleteSysIconMationById(map);
    }

    /**
     * 编辑ICON信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysIconMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveIconDao.querySysIconMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑ICON信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysIconMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveIconDao.checkSysIconMation(map);
        if (CollectionUtils.isEmpty(bean)) {
            sysEveIconDao.editSysIconMationById(map);
        } else {
            outputObject.setreturnMessage("该ICON属性已存在，不可进行二次保存");
        }
    }

    /**
     * 获取ICON列表供menu菜单使用
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysIconListToMenu(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveIconDao.querySysIconListToMenu(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }


}
