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
import com.skyeye.eve.dao.MailListDao;
import com.skyeye.eve.service.MailListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MailListServiceImpl
 * @Description: 通讯录管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:54
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class MailListServiceImpl implements MailListService {

    @Autowired
    private MailListDao mailListDao;

    /**
     * 获取通讯录列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryMailMationList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = mailListDao.queryMailMationList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增通讯录(个人或者公共通讯录)
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertMailMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 通讯录类型
        String category = map.get("category").toString();
        if ("1".equals(category)) {
            // 个人通讯录
            if (ToolUtil.isBlank(map.get("typeId").toString())) {
                // 通讯录所属类别为空
                outputObject.setreturnMessage("请选择类别");
                return;
            }
            // 将他人权限制空
            map.remove("readonly");
        } else if ("2".equals(category)) {
            // 公共通讯录
            if (ToolUtil.isBlank(map.get("readonly").toString())) {
                // 他人是否只读为空
                outputObject.setreturnMessage("请选择他人只读权限");
                return;
            }
            // 将所属类别制空
            map.remove("typeId");
        }
        DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
        mailListDao.insertMailMation(map);
    }

    /**
     * 删除通讯录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteMailMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        mailListDao.deleteMailMationById(map);
    }

    /**
     * 编辑通讯录进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryMailMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = mailListDao.queryMailMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑通讯录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editMailMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 通讯录类型
        String category = map.get("category").toString();
        if ("1".equals(category)) {
            // 个人通讯录
            if (ToolUtil.isBlank(map.get("typeId").toString())) {
                // 通讯录所属类别为空
                outputObject.setreturnMessage("请选择类别");
                return;
            }
            // 将他人权限制空
            map.remove("readonly");
        } else if ("2".equals(category)) {
            // 公共通讯录
            if (ToolUtil.isBlank(map.get("readonly").toString())) {
                // 他人是否只读为空
                outputObject.setreturnMessage("请选择他人只读权限");
                return;
            }
            // 将所属类别制空
            map.remove("typeId");
        }
        mailListDao.editMailMationById(map);
    }

    /**
     * 个人/公共通讯录详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryMailMationDetailsById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = mailListDao.queryMailMationDetailsById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

}
