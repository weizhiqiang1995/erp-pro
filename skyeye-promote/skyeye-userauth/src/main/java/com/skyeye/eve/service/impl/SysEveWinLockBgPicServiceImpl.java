/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.FileUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveWinLockBgPicDao;
import com.skyeye.eve.service.SysEveWinLockBgPicService;
import com.skyeye.jedis.JedisClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysEveWinLockBgPicServiceImpl implements SysEveWinLockBgPicService {

    @Autowired
    private SysEveWinLockBgPicDao sysEveWinLockBgPicDao;

    @Autowired
    public JedisClientService jedisClient;

    @Value("${IMAGES_PATH}")
    private String tPath;

    /**
     * 获取win系统锁屏桌面图片列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveWinLockBgPicList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveWinLockBgPicDao.querySysEveWinLockBgPicList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 添加win系统锁屏桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysEveWinLockBgPicMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createId", user.get("id"));
        map.put("createTime", DateUtil.getTimeAndToString());
        sysEveWinLockBgPicDao.insertSysEveWinLockBgPicMation(map);
        jedisClient.del(Constants.getSysWinLockBgPicRedisKey());
    }

    /**
     * 删除win系统锁屏桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysEveWinLockBgPicMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinLockBgPicDao.querySysEveMationById(map);
        String basePath = tPath + bean.get("picUrl").toString().replace("/images/", "");
        FileUtil.deleteFile(basePath);
        sysEveWinLockBgPicDao.deleteSysEveWinLockBgPicMationById(map);
        jedisClient.del(Constants.getSysWinLockBgPicRedisKey());
    }

    /**
     * 用户自定义上传win系统锁屏桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysEveWinBgPicMationByCustom(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createId", user.get("id"));
        map.put("createTime", DateUtil.getTimeAndToString());
        sysEveWinLockBgPicDao.insertSysEveWinBgPicMationByCustom(map);
    }

    /**
     * 获取win系统锁屏桌面图片列表用户自定义
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveWinBgPicCustomList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("createId", user.get("id"));
        List<Map<String, Object>> beans = sysEveWinLockBgPicDao.querySysEveWinBgPicCustomList(map);
        if (beans != null && !beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 删除win系统锁屏桌面图片信息用户自定义
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysEveWinBgPicMationCustomById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinLockBgPicDao.querySysEveMationById(map);
        String basePath = tPath + bean.get("picUrl").toString().replace("/images/", "");
        FileUtil.deleteFile(basePath);
        sysEveWinLockBgPicDao.deleteSysEveWinBgPicMationCustomById(map);
    }

}
