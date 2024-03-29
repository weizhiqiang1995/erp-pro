/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.win.dao.SysEveWinThemeColorDao;
import com.skyeye.win.service.SysEveWinThemeColorService;
import com.skyeye.jedis.JedisClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysEveWinThemeColorServiceImpl implements SysEveWinThemeColorService {

    @Autowired
    private SysEveWinThemeColorDao sysEveWinThemeColorDao;

    @Autowired
    public JedisClientService jedisClient;

    /**
     * 获取win系统主题颜色列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveWinThemeColorList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveWinThemeColorDao.querySysEveWinThemeColorList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 添加win系统主题颜色信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysEveWinThemeColorMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinThemeColorDao.querySysEveWinThemeColorMationByName(map);
        if (bean == null) {
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysEveWinThemeColorDao.insertSysEveWinThemeColorMation(map);
            jedisClient.del(Constants.getSysWinThemeColorRedisKey());
        } else {
            outputObject.setreturnMessage("该win系统主题颜色名称已存在，不可进行二次保存");
        }
    }

    /**
     * 删除win系统主题颜色信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysEveWinThemeColorMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        sysEveWinThemeColorDao.deleteSysEveWinThemeColorMationById(map);
        jedisClient.del(Constants.getSysWinThemeColorRedisKey());
    }

    /**
     * 编辑win系统主题颜色信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveWinThemeColorMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinThemeColorDao.querySysEveWinThemeColorMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 编辑win系统主题颜色信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysEveWinThemeColorMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinThemeColorDao.querySysEveWinThemeColorMationByNameAndId(map);
        if (bean == null) {
            sysEveWinThemeColorDao.editSysEveWinThemeColorMationById(map);
            jedisClient.del(Constants.getSysWinThemeColorRedisKey());
        } else {
            outputObject.setreturnMessage("该win系统主题颜色名称已存在，不可进行二次保存");
        }
    }

}
