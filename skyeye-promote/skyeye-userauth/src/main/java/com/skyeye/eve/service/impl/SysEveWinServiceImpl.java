/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.GetUserToken;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.HttpClient;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveWinDao;
import com.skyeye.eve.service.SysEveWinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveWinServiceImpl
 * @Description: 系统信息管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:26
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysEveWinServiceImpl implements SysEveWinService {

    @Autowired
    private SysEveWinDao sysEveWinDao;

    /**
     * 获取系统信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryWinMationList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveWinDao.queryWinMationList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增系统信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertWinMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinDao.queryWinMationByNameOrUrl(map);
        if (bean != null && !bean.isEmpty()) {
            outputObject.setreturnMessage("存在相同的系统或系统地址，请更换");
        } else {
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysEveWinDao.insertWinMation(map);
        }
    }

    /**
     * 编辑系统信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryWinMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinDao.queryWinMationToEditById(map);
        outputObject.setBean(bean);
    }

    /**
     * 编辑系统信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editWinMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinDao.queryWinMationByNameOrUrlAndId(map);
        if (bean != null && !bean.isEmpty()) {
            outputObject.setreturnMessage("存在相同的系统或系统地址，请更换");
        } else {
            sysEveWinDao.editWinMationById(map);
        }
    }

    /**
     * 删除系统信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteWinMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinDao.queryChildMationById(map);
        if (Integer.parseInt(bean.get("menuNum").toString()) > 0 || Integer.parseInt(bean.get("useNum").toString()) > 0) {
            outputObject.setreturnMessage("该系统存在功能菜单或者使用商户，请先进行菜单或商户操作。");
        } else {
            sysEveWinDao.deleteWinMationById(map);
        }
    }

    /**
     * 进行商户系统授权
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editAuthorizationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinDao.querySysEveWinNum(map);
        if (bean != null && !bean.isEmpty()) {
            map.put("winNumId", bean.get("id"));
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysEveWinDao.insertAuthorizationById(map);
        } else {
            outputObject.setreturnMessage("暂无可授权的商户。");
        }
    }

    /**
     * 进行商户系统取消授权
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editCancleAuthorizationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        sysEveWinDao.editCancleAuthorizationById(map);
    }

    /**
     * 获取应用商店
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryWinMationListToShow(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveWinDao.queryWinMationListToShow(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 系统重要的同步操作
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertWinMationImportantSynchronization(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinDao.queryWinMationSynchronizationById(map);//判断是否有权限
        if (bean == null) {
            outputObject.setreturnMessage("您不具备该系统的同步权限。");
        } else {
            List<Map<String, Object>> hasRows = sysEveWinDao.queryWinMationSynchronizationByWinId(map);
            if (hasRows.isEmpty()) {
                String url = map.get("url") + "/sysimportantsynchronization002?loginPCIp=" + PutObject.getRequest().getHeader("loginPCIp")
                    + "&userToken=" + GetUserToken.getUserToken(PutObject.getRequest()) + "&winid=" + map.get("id");
                String str = HttpClient.doGet(url);
                JSONObject json = JSONUtil.toBean(str, null);
                if ("0".equals(json.get("returnCode").toString())) {
                    Map<String, Object> user = inputObject.getLogParams();
                    JSONObject jo = JSONUtil.toBean(json.get("bean").toString(), null);
                    //处理菜单
                    List<Map<String, Object>> beans = JSONUtil.toList(jo.get("menuBeans").toString(), null);
                    for (Map<String, Object> row : beans) {
                        row.put("sysWinId", map.get("id"));
                        row.put("createId", user.get("id"));
                        row.put("createTime", DateUtil.getTimeAndToString());
                        if (!"--".equals(row.get("menuUrl").toString())) {//一级菜单
                            row.put("menuUrl", map.get("url").toString() + "/" + row.get("menuUrl").toString().replace("../../", ""));
                        }
                    }
                    sysEveWinDao.insertWinMationImportantSynchronization(beans);
                    //处理权限点
                    List<Map<String, Object>> points = JSONUtil.toList(jo.get("pointBeans").toString(), null);
                    for (Map<String, Object> row : points) {
                        row.put("createId", user.get("id"));
                        row.put("createTime", DateUtil.getTimeAndToString());
                    }
                    sysEveWinDao.insertWinMationImportantSynchronizationPoint(points);
                } else {
                    outputObject.setreturnMessage(json.get("returnMessage").toString());
                }
            } else {
                outputObject.setreturnMessage("系统菜单只能同步一次哦。");
            }
        }
    }

    /**
     * 系统重要的同步操作获取数据
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryWinMationImportantSynchronizationData(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> menuBeans = sysEveWinDao.queryWinMationImportantSynchronizationData(map);
        List<Map<String, Object>> pointBeans = sysEveWinDao.queryWinMationImportantSynchronizationPointData(map);
        if (!menuBeans.isEmpty() || !pointBeans.isEmpty()) {
            Map<String, Object> bean = new HashMap<>();
            bean.put("menuBeans", menuBeans);
            bean.put("pointBeans", pointBeans);
            outputObject.setBean(bean);
        } else {
            outputObject.setreturnMessage("暂无可以同步的数据");
        }
    }

}
