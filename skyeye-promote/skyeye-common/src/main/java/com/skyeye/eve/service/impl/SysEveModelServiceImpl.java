/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.FileUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveModelDao;
import com.skyeye.eve.entity.sysmodel.SysEveModelQueryDo;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.eve.service.SysEveModelService;
import com.skyeye.jedis.JedisClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveModelServiceImpl
 * @Description: 系统编辑器模板服务类
 * @author: skyeye云系列
 * @date: 2021/11/14 9:10
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysEveModelServiceImpl implements SysEveModelService {

    @Autowired
    private SysEveModelDao sysEveModelDao;

    @Value("${IMAGES_PATH}")
    private String tPath;

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 获取系统编辑器模板表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveModelList(InputObject inputObject, OutputObject outputObject) {
        SysEveModelQueryDo sysEveModelQuery = inputObject.getParams(SysEveModelQueryDo.class);
        sysEveModelQuery.setUserId(inputObject.getLogParams().get("id").toString());
        Page pages = PageHelper.startPage(sysEveModelQuery.getPage(), sysEveModelQuery.getLimit());
        List<Map<String, Object>> beans = sysEveModelDao.querySysEveModelList(sysEveModelQuery);
        iAuthUserService.setNameForMap(beans, "createId", "createName");
        iAuthUserService.setNameForMap(beans, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增系统编辑器模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysEveModelMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = sysEveModelDao.querySysEveModelMationByNameAndType(map);
        if (bean != null && !bean.isEmpty()) {
            outputObject.setreturnMessage("该系统编辑器模板已存在，请更换");
        } else {
            map.put("id", ToolUtil.getSurFaceId());
            map.put("createTime", DateUtil.getTimeAndToString());
            sysEveModelDao.insertSysEveModelMation(map);
        }
    }

    /**
     * 删除编辑器模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysEveModelById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = sysEveModelDao.selectSysEveModelMationById(id);
        if (bean != null && !bean.isEmpty()) {
            FileUtil.deleteFile(tPath.replace("images", "") + bean.get("logo").toString());
            sysEveModelDao.deleteSysEveModelById(id);
        }
    }

    /**
     * 通过id查找对应的编辑器模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void selectSysEveModelById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = sysEveModelDao.selectSysEveModelMationById(id);
        outputObject.setBean(bean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 通过id编辑对应的编辑器模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysEveModelMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = sysEveModelDao.querySysEveModelMationByNameAndType(map);
        if (bean != null && !bean.isEmpty()) {
            outputObject.setreturnMessage("该编辑器模板已存在，请更换");
        } else {
            map.put("lastUpdateTime", DateUtil.getTimeAndToString());
            sysEveModelDao.editSysEveModelMationById(map);
        }
    }

    /**
     * 通过id查找对应的编辑器模板详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void selectSysEveModelMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = sysEveModelDao.selectSysEveModelMationById(id);
        outputObject.setBean(bean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }
}

