/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.ObjectConstant;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.personnel.dao.SysEveUserDao;
import com.skyeye.win.dao.SysEveWinDragDropDao;
import com.skyeye.win.service.SysEveWinDragDropService;
import com.skyeye.jedis.JedisClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysEveWinDragDropServiceImpl implements SysEveWinDragDropService {

    @Autowired
    private SysEveWinDragDropDao sysEveWinDragDropDao;

    @Autowired
    public SysEveUserDao sysEveUserDao;

    @Autowired
    public JedisClientService jedisClient;

    /**
     * 用户自定义创建菜单盒子
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertWinCustomMenuBox(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        map.put("userId", userId);
        List<Map<String, Object>> menuBoxNameList = sysEveWinDragDropDao.queryMenuBoxNameInByName(map);
        if (menuBoxNameList != null && !menuBoxNameList.isEmpty()) {
            outputObject.setreturnMessage("该名称已存在，请更换。");
        } else {
            // 获取当前用户已经创建的菜单盒子中值最大的排序号
            Map<String, Object> orderNum = sysEveWinDragDropDao.queryWinCustomMenuBoxNumByUserId(map);
            int order = 1;
            if (orderNum != null && !orderNum.isEmpty()) {
                order = Integer.parseInt(orderNum.get("orderNum").toString()) + 1;
            }
            map.put("order", order);
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysEveWinDragDropDao.insertWinCustomMenuBox(map);
            outputObject.setBean(map);
            // 桌面菜单列表
            List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
            deskTops = ToolUtil.listToTree(deskTops, "id", "parentId", "childs");
            jedisClient.set(ObjectConstant.getDeskTopsCacheKey(userId), JSONUtil.toJsonStr(deskTops));
        }
    }

    /**
     * 用户自定义创建菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertWinCustomMenu(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        map.put("userId", userId);
        List<Map<String, Object>> menuBoxNameList = sysEveWinDragDropDao.queryMenuNameInByName(map);
        if (menuBoxNameList != null && !menuBoxNameList.isEmpty()) {
            outputObject.setreturnMessage("该名称已存在，请更换。");
        } else {
            map.put("menuType", "html");
            map.put("menuParentId", "0");
            map.put("openType", "2");
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysEveWinDragDropDao.insertWinCustomMenu(map);
            outputObject.setBean(map);
            // 桌面菜单列表
            List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
            deskTops = ToolUtil.listToTree(deskTops, "id", "parentId", "childs");
            jedisClient.set(ObjectConstant.getDeskTopsCacheKey(userId), JSONUtil.toJsonStr(deskTops));
        }
    }

    /**
     * 用户删除自定义菜单或文件夹
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteWinMenuOrBoxById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        map.put("userId", userId);
        Map<String, Object> bean = sysEveWinDragDropDao.queryMenuMationFromSysById(map);//查询菜单
        if (bean != null && !bean.isEmpty()) {
            // 菜单存在
            if ("1".equals(bean.get("type").toString())) {
                // 要删除的菜单是系统菜单
                List<Map<String, Object>> childs = sysEveWinDragDropDao.queryChildsMenuById(map);
                List<Map<String, Object>> removeChild = new ArrayList<>();//系统子菜单移出自定义删除表
                String delCustomMenu = "";//自定义子菜单直接删除
                for (Map<String, Object> child : childs) {
                    if ("1".equals(child.get("type").toString())) {
                        // 系统子菜单
                        child.put("rowId", ToolUtil.getSurFaceId());
                        child.put("createId", userId);
                        child.put("createTime", DateUtil.getTimeAndToString());
                        removeChild.add(child);
                    } else if ("2".equals(child.get("type").toString())) {
                        // 自定义菜单
                        delCustomMenu += child.get("id").toString();
                    }
                }
                //删除系统子菜单
                if (!removeChild.isEmpty()) {
                    sysEveWinDragDropDao.deleteUserSysMenuByIds(removeChild);
                }
                // 删除自定义菜单
                Map<String, Object> delCustomMenuBean = new HashMap<>();
                delCustomMenuBean.put("ids", delCustomMenu);
                sysEveWinDragDropDao.deleteCustomMenuByIds(delCustomMenuBean);
                sysEveWinDragDropDao.deleteCustomMenuParentByIds(delCustomMenuBean);
                // 删除系统文件夹
                Map<String, Object> delSysBoxMenuBean = new HashMap<>();
                delSysBoxMenuBean.put("menuId", map.get("id"));
                DataCommonUtil.setCommonData(delSysBoxMenuBean, userId);
                sysEveWinDragDropDao.deleteSysBoxMenuById(delSysBoxMenuBean);
            } else if ("2".equals(bean.get("type").toString())) {
                // 要删除的菜单是菜单文件夹（菜单盒子）
                List<Map<String, Object>> childs = sysEveWinDragDropDao.queryChildsMenuById(map);
                List<Map<String, Object>> removeChild = new ArrayList<>();//系统子菜单移出自定义删除表
                String delCustomMenu = "";//自定义子菜单直接删除
                for (Map<String, Object> child : childs) {
                    if ("1".equals(child.get("type").toString())) {
                        // 系统子菜单
                        child.put("rowId", ToolUtil.getSurFaceId());
                        child.put("createId", userId);
                        child.put("createTime", DateUtil.getTimeAndToString());
                        removeChild.add(child);
                    } else if ("2".equals(child.get("type").toString())) {
                        // 自定义菜单
                        delCustomMenu += child.get("id").toString();
                    }
                }
                //删除系统子菜单
                if (!removeChild.isEmpty()) {
                    sysEveWinDragDropDao.deleteUserSysMenuByIds(removeChild);
                }
                //删除自定义菜单
                Map<String, Object> delCustomMenuBean = new HashMap<>();
                delCustomMenuBean.put("ids", delCustomMenu);
                sysEveWinDragDropDao.deleteCustomMenuByIds(delCustomMenuBean);
                sysEveWinDragDropDao.deleteCustomMenuParentByIds(delCustomMenuBean);
                //删除自定义文件夹
                sysEveWinDragDropDao.deleteCustomBoxMenuById(map);
            } else if ("3".equals(bean.get("type").toString())) {
                // 要删除的菜单是自定义菜单,直接删除
                sysEveWinDragDropDao.deleteCustomMenuById(map);
            }
            // 桌面菜单列表
            List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
            deskTops = ToolUtil.listToTree(deskTops, "id", "parentId", "childs");
            jedisClient.set(ObjectConstant.getDeskTopsCacheKey(userId), JSONUtil.toJsonStr(deskTops));
        } else {
            outputObject.setreturnMessage("该菜单不存在，请刷新页面");
        }
    }

    /**
     * 用户自定义父菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editMenuParentIdById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        map.put("userId", userId);
        sysEveWinDragDropDao.delMenuParentIdById(map);
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createTime", DateUtil.getTimeAndToString());
        if (!ToolUtil.isBlank(map.get("parentId").toString())) {
            map.put("parentId", map.get("parentId").toString() + ",");
            map.put("menuLevel", "1");
        } else {
            map.put("parentId", "0");
            map.put("menuLevel", "0");
        }
        sysEveWinDragDropDao.insertMenuParentId(map);
        // 桌面菜单列表
        List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
        deskTops = ToolUtil.listToTree(deskTops, "id", "parentId", "childs");
        jedisClient.set(ObjectConstant.getDeskTopsCacheKey(userId), JSONUtil.toJsonStr(deskTops));
    }

    /**
     * 获取菜单类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryMenuMationTypeById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinDragDropDao.queryMenuMationTypeById(map);
        outputObject.setBean(bean);
    }

    /**
     * 编辑自定义盒子时回显信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCustomMenuBoxMationEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        Map<String, Object> bean = sysEveWinDragDropDao.queryCustomMenuBoxMationEditById(map);
        outputObject.setBean(bean);
    }

    /**
     * 编辑自定义盒子
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editCustomMenuBoxMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        map.put("userId", userId);
        sysEveWinDragDropDao.editCustomMenuBoxMationById(map);
        // 桌面菜单列表
        List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
        deskTops = ToolUtil.listToTree(deskTops, "id", "parentId", "childs");
        jedisClient.set(ObjectConstant.getDeskTopsCacheKey(userId), JSONUtil.toJsonStr(deskTops));
    }

    /**
     * 编辑快捷方式时回显信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCustomMenuMationEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        Map<String, Object> bean = sysEveWinDragDropDao.queryCustomMenuMationEditById(map);
        outputObject.setBean(bean);
    }

    /**
     * 编辑快捷方式
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editCustomMenuMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        map.put("userId", userId);
        sysEveWinDragDropDao.editCustomMenuMationById(map);
        // 桌面菜单列表
        List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
        deskTops = ToolUtil.listToTree(deskTops, "id", "parentId", "childs");
        jedisClient.set(ObjectConstant.getDeskTopsCacheKey(userId), JSONUtil.toJsonStr(deskTops));
    }

    /**
     * 系统菜单发送到桌面快捷方式
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editCustomMenuToDeskTopById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        map.put("userId", userId);
        Map<String, Object> bean = sysEveWinDragDropDao.queryCustomMenuToDeskTopById(map);
        if (bean != null && !bean.isEmpty()) {
            sysEveWinDragDropDao.editCustomMenuToDeskTopById(map);
            Map<String, Object> item = sysEveWinDragDropDao.queryMenuToDeskTopById(map);
            // 桌面菜单列表
            List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
            deskTops = ToolUtil.listToTree(deskTops, "id", "parentId", "childs");
            jedisClient.set(ObjectConstant.getDeskTopsCacheKey(userId), JSONUtil.toJsonStr(deskTops));
            outputObject.setBean(item);
        } else {
            outputObject.setreturnMessage("该菜单在桌面上已存在。");
        }
    }

}
