/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveRoleDao;
import com.skyeye.eve.service.SysEveRoleService;
import com.skyeye.jedis.JedisClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: SysEveRoleServiceImpl
 * @Description: 角色管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:38
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysEveRoleServiceImpl implements SysEveRoleService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysEveRoleServiceImpl.class);

    @Autowired
    private SysEveRoleDao sysEveRoleDao;

    @Autowired
    private JedisClientService jedisClient;

    /**
     * 获取角色列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysRoleList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        List<Map<String, Object>> beans = sysEveRoleDao.querySysRoleList(commonPageInfo);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 获取角色需要绑定的菜单列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysRoleBandMenuList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveRoleDao.querySysRoleBandMenuList(map);
        String[] str;
        for (Map<String, Object> bean : beans) {
            str = bean.get("pId").toString().split(",");
            bean.put("pId", str[str.length - 1]);
        }
        Map<String, Object> deskDefault = new HashMap<>();
        deskDefault.put("id", "winfixedpage00000000");
        deskDefault.put("name", "默认桌面");
        deskDefault.put("pId", "0");
        deskDefault.put("isParent", true);
        deskDefault.put("type", "0");//
        deskDefault.put("open", false);
        deskDefault.put("sysName", "基础系统");
        deskDefault.put("pageType", "桌面");
        beans.add(deskDefault);
        outputObject.setBeans(beans);
    }

    /**
     * 新增角色
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysRoleMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        Map<String, Object> roleName = sysEveRoleDao.querySysRoleNameByName(map);
        if (roleName == null) {
            String roleId = ToolUtil.getSurFaceId();
            map.put("id", roleId);
            map.put("createId", user.get("id"));
            map.put("createTime", DateUtil.getTimeAndToString());
            sysEveRoleDao.insertSysRoleMation(map);
            LOGGER.info("add role [{}] success", map.get("roleName").toString());
        } else {
            outputObject.setreturnMessage("该角色名称已存在，请更换！");
        }
    }

    /**
     * 编辑角色时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysRoleMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> roleMation = sysEveRoleDao.querySysRoleMationByRoleId(map);
        List<Map<String, Object>> roleMenuId = sysEveRoleDao.querySysRoleMenuIdByRoleId(map);
        outputObject.setBean(roleMation);
        outputObject.setBeans(roleMenuId);
        outputObject.settotal(roleMenuId.size());
    }

    /**
     * 编辑角色
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysRoleMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> roleName = sysEveRoleDao.queryRoleNameByIdAndName(map);
        if (roleName == null) {
            String roleId = map.get("id").toString();
            sysEveRoleDao.editSysRoleMationById(map);
            // 删除缓存
            this.deleteRoleCache(roleId, "delete");
        } else {
            outputObject.setreturnMessage("该角色名称已存在，请更换！");
        }
    }

    /**
     * 编辑角色PC端权限
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void editSysRolePCAuth(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        Map<String, Object> roleName = sysEveRoleDao.queryRoleNameByIdAndName(map);
        if (roleName == null) {
            String roleId = map.get("id").toString();
            saveRoleMenuMation(map, roleId, user.get("id").toString(), DateUtil.getTimeAndToString());
            // 删除缓存
            this.deleteRoleCache(roleId, "delete");
        } else {
            outputObject.setreturnMessage("该角色名称已存在，请更换！");
        }
    }

    private void saveRoleMenuMation(Map<String, Object> map, String roleId, String createId, String createTime) {
        List<String> menuIds = (List<String>) map.get("menuIds");
        // 删除角色菜单关联表信息
        sysEveRoleDao.deleteRoleMenuByRoleId(roleId);
        if (menuIds.size() > 0) {
            List<Map<String, Object>> beans = new ArrayList<>();
            menuIds.stream().forEach(str -> {
                Map<String, Object> item = new HashMap<>();
                item.put("id", ToolUtil.getSurFaceId());
                item.put("roleId", roleId);
                item.put("menuId", str);
                item.put("createId", createId);
                item.put("createTime", createTime);
                beans.add(item);
            });
            sysEveRoleDao.insertSysRoleMenuMation(beans);
        }
    }

    /**
     * 删除角色
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysRoleMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String roleId = map.get("id").toString();
        // 判断当前是否有用户在使用该角色
        Map<String, Object> bean = sysEveRoleDao.queryUserRoleByRoleId(map);
        if (Integer.parseInt(bean.get("num").toString()) == 0) {
            // 删除角色菜单关联表信息
            sysEveRoleDao.deleteRoleMenuByRoleId(roleId);
            // 删除角色信息
            sysEveRoleDao.deleteRoleByRoleId(map);
            // 删除缓存
            this.deleteRoleCache(map.get("id").toString(), "delete");
        } else {
            outputObject.setreturnMessage("该角色下有用户正在使用，只能对角色进行维护。");
        }
    }

    private void deleteRoleCache(String roleId, String type) {
        LOGGER.info("delete Role Cache, roleId is {}", roleId);
        jedisClient.del(String.format("roleHasMenu:%s", roleId));
        jedisClient.del(String.format("roleHasMenuPoint:%s", roleId));
        if ("delete".equals(type)) {
            this.deleteAPPRoleCache(roleId);
        }
    }

    /**
     * 获取角色需要绑定的手机端菜单列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysRoleBandAppMenuList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveRoleDao.querySysRoleBandAppMenuList(map);
        for (Map<String, Object> bean : beans) {
            String[] str = bean.get("pId").toString().split(",");
            bean.put("pId", str[str.length - 1]);
        }
        outputObject.setBeans(beans);
    }

    /**
     * 手机端菜单授权时的信息回显
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysRoleToAppMenuEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> roleMation = sysEveRoleDao.querySysRoleMationByRoleId(map);
        List<Map<String, Object>> roleMenuId = sysEveRoleDao.querySysRoleAppMenuIdByRoleId(map);
        outputObject.setBean(roleMation);
        outputObject.setBeans(roleMenuId);
        outputObject.settotal(roleMenuId.size());
    }

    /**
     * 手机端菜单授权
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysRoleAppMenuById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = new ArrayList<>();
        List<Map<String, Object>> beanp = new ArrayList<>();
        String[] menuIds = map.get("menuIds").toString().split(",");
        if (menuIds.length > 0) {
            for (String str : menuIds) {
                Map<String, Object> item = new HashMap<>();
                item.put("id", ToolUtil.getSurFaceId());
                item.put("roleId", map.get("id").toString());
                item.put("menuId", str);
                beans.add(item);
            }
            sysEveRoleDao.deleteRoleAppMenuByRoleId(map);//删除角色菜单关联表信息
            sysEveRoleDao.insertSysRoleAppMenuMation(beans);

            String[] pointIds = map.get("pointIds").toString().split(",");
            if (pointIds.length > 0) {
                for (String str : pointIds) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", ToolUtil.getSurFaceId());
                    item.put("roleId", map.get("id").toString());
                    item.put("pointId", str);
                    beanp.add(item);
                }
            }
            sysEveRoleDao.deleteRoleAppPointByRoleId(map);//删除角色权限点关联表信息
            if (!beanp.isEmpty()) {
                sysEveRoleDao.insertSysRoleAppPointMation(beanp);
            }
            this.deleteAPPRoleCache(map.get("id").toString());
        } else {
            outputObject.setreturnMessage("请选择该角色即将拥有的权限！");
        }
    }

    private void deleteAPPRoleCache(String roleId) {
        LOGGER.info("delete Role app Cache");
        jedisClient.del(String.format("roleHasAppMenu:%s", roleId));
        jedisClient.del(String.format("roleHasAppMenuPoint:%s", roleId));
    }

}
