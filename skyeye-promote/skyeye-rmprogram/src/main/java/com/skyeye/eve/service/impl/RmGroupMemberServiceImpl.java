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
import com.skyeye.common.util.FileUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.RmGroupMemberDao;
import com.skyeye.eve.service.RmGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RmGroupMemberServiceImpl
 * @Description: 小程序组件管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/3 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class RmGroupMemberServiceImpl implements RmGroupMemberService {

    @Autowired
    private RmGroupMemberDao rmGroupMemberDao;

    @Value("${IMAGES_PATH}")
    private String tPath;

    /**
     * 获取小程序组件列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryRmGroupMemberList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String tPath = "images/upload/smpropic/";
        map.put("basePath", tPath);
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = rmGroupMemberDao.queryRmGroupMemberList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 添加小程序组件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertRmGroupMemberMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
        // 获取最靠前的小程序组件
        Map<String, Object> item = rmGroupMemberDao.queryRmGroupMemberISTop(map);
        if (item == null) {
            map.put("sort", 1);
        } else {
            map.put("sort", Integer.parseInt(item.get("sort").toString()) + 1);
        }
        rmGroupMemberDao.insertRmGroupMemberMation(map);
    }

    /**
     * 小程序组件展示顺序上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editRmGroupMemberSortTopById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> topBean = rmGroupMemberDao.queryRmGroupMemberISTopByThisId(map);//根据排序获取这条数据的上一条数据
        if (topBean == null) {
            outputObject.setreturnMessage("已经是最靠前组件，无法移动。");
        } else {
            map.put("sort", topBean.get("sort"));
            topBean.put("sort", topBean.get("thisSort"));
            rmGroupMemberDao.editRmGroupMemberSortTopById(map);
            rmGroupMemberDao.editRmGroupMemberSortTopById(topBean);
        }
    }

    /**
     * 小程序组件展示顺序下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editRmGroupMemberSortLowerById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> topBean = rmGroupMemberDao.queryRmGroupMemberISLowerByThisId(map);//根据排序获取这条数据的下一条数据
        if (topBean == null) {
            outputObject.setreturnMessage("已经是最靠后组件，无法移动。");
        } else {
            map.put("sort", topBean.get("sort"));
            topBean.put("sort", topBean.get("thisSort"));
            rmGroupMemberDao.editRmGroupMemberSortTopById(map);
            rmGroupMemberDao.editRmGroupMemberSortTopById(topBean);
        }
    }

    /**
     * 删除小程序组件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteRmGroupMemberById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = rmGroupMemberDao.queryUseRmGroupMemberNumById(map);
        if (bean == null) {
            Map<String, Object> item = rmGroupMemberDao.queryRmGroupMemberMationById(map);
            String basePath = tPath + "\\upload\\smpropic\\" + item.get("printsPicUrl").toString();
            FileUtil.deleteFile(basePath);
            rmGroupMemberDao.deleteRmGroupMemberById(map);

        } else {
            if (Integer.parseInt(bean.get("groupUseMemberNum").toString()) == 0) {//该组件没有正在使用中
                Map<String, Object> item = rmGroupMemberDao.queryRmGroupMemberMationById(map);
                String basePath = tPath + "\\upload\\smpropic\\" + item.get("printsPicUrl").toString();
                FileUtil.deleteFile(basePath);
                rmGroupMemberDao.deleteRmGroupMemberById(map);
            } else {
                outputObject.setreturnMessage("该组件正在使用中，无法删除。");
            }
        }
    }

    /**
     * 编辑小程序组件信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryRmGroupMemberMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String tPath = "images/upload/smpropic/";
        map.put("basePath", tPath);
        Map<String, Object> bean = rmGroupMemberDao.queryRmGroupMemberMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑小程序组件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editRmGroupMemberMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> item = rmGroupMemberDao.queryRmGroupMemberMationById(map);
        if (!item.get("printsPicUrl").toString().equals(map.get("img").toString())) {//保存的图片和原来的图片不符
            //删除原来的图片
            String basePath = tPath + "\\upload\\smpropic\\" + item.get("printsPicUrl").toString();
            FileUtil.deleteFile(basePath);
        }
        rmGroupMemberDao.editRmGroupMemberMationById(map);
    }

    /**
     * 编辑小程序组件和标签属性的绑定信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editRmGroupMemberAndPropertyMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String[] propertyIds = map.get("propertyIds").toString().split(",");
        List<Map<String, Object>> beans = new ArrayList<>();
        String userId = inputObject.getLogParams().get("id").toString();
        for (String str : propertyIds) {
            if (!ToolUtil.isBlank(str)) {
                Map<String, Object> bean = new HashMap<>();
                bean.put("propertyId", str);
                bean.put("memberId", map.get("memberId"));
                DataCommonUtil.setCommonData(bean, userId);
                beans.add(bean);
            }
        }
        if (!beans.isEmpty()) {
            // 删除之前的绑定信息
            rmGroupMemberDao.deleteRmGroupMemberAndPropertyMationById(map);
            // 新增绑定信息
            rmGroupMemberDao.insertRmGroupMemberAndPropertyMationById(beans);
        }
    }

    /**
     * 获取小程序组件和标签属性的绑定信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryRmGroupMemberAndPropertyMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = rmGroupMemberDao.queryRmGroupMemberAndPropertyMationById(map);
        if (beans != null && !beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

}
