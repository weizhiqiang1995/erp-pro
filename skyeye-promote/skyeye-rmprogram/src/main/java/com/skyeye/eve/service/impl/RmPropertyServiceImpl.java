/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.eve.dao.RmPropertyDao;
import com.skyeye.eve.service.RmPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class RmPropertyServiceImpl implements RmPropertyService {

    @Autowired
    private RmPropertyDao rmPropertyDao;

    /**
     * 获取小程序样式属性列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryRmPropertyList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = rmPropertyDao.queryRmPropertyList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 添加小程序样式属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertRmPropertyMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = rmPropertyDao.queryRmPropertyMationByName(map);
        if (CollectionUtils.isEmpty(bean)) {
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            rmPropertyDao.insertRmPropertyMation(map);
        } else {
            outputObject.setreturnMessage("该样式属性名称已存在，不可进行二次保存");
        }
    }

    /**
     * 删除小程序样式属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteRmPropertyMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = rmPropertyDao.queryRmPropertyValueNumById(map);
        if (CollectionUtils.isEmpty(bean)) {
            Map<String, Object> useThisBean = rmPropertyDao.queryUseRmPropertyNumById(map);
            if (CollectionUtils.isEmpty(useThisBean)) {
                rmPropertyDao.deleteRmPropertyMationById(map);
            } else {
                if (Integer.parseInt(useThisBean.get("usePropertyNum").toString()) == 0) {//该样式属性没有被使用
                    rmPropertyDao.deleteRmPropertyMationById(map);
                } else {
                    outputObject.setreturnMessage("该样式属性正在使用中，无法删除。");
                }
            }
        } else {
            if (Integer.parseInt(bean.get("propertyValueNum").toString()) == 0) {//该样式属性下没有值
                Map<String, Object> useThisBean = rmPropertyDao.queryUseRmPropertyNumById(map);
                if (useThisBean == null) {
                    rmPropertyDao.deleteRmPropertyMationById(map);
                } else {
                    if (Integer.parseInt(useThisBean.get("usePropertyNum").toString()) == 0) {//该样式属性没有被使用
                        rmPropertyDao.deleteRmPropertyMationById(map);
                    } else {
                        outputObject.setreturnMessage("该样式属性正在使用中，无法删除。");
                    }
                }
            } else {
                outputObject.setreturnMessage("该样式属性下存在值，无法删除。");
            }
        }
    }

    /**
     * 编辑小程序样式属性信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryRmPropertyMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = rmPropertyDao.queryRmPropertyMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑小程序样式属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editRmPropertyMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = rmPropertyDao.queryRmPropertyMationByNameAndId(map);
        if (CollectionUtils.isEmpty(bean)) {
            rmPropertyDao.editRmPropertyMationById(map);
        } else {
            outputObject.setreturnMessage("该样式属性名称已存在，不可进行二次保存");
        }
    }

    /**
     * 获取小程序样式属性供展示
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryRmPropertyListToShow(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = rmPropertyDao.queryRmPropertyListToShow(map);
        if (beans != null && !beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

}
