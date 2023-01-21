/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.role.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.role.dao.SysEveRoleDao;
import com.skyeye.role.entity.Role;
import com.skyeye.role.service.SysEveRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveRoleServiceImpl
 * @Description: 角色管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:38
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "角色管理", groupName = "系统设置")
public class SysEveRoleServiceImpl extends SkyeyeBusinessServiceImpl<SysEveRoleDao, Role> implements SysEveRoleService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysEveRoleServiceImpl.class);

    @Autowired
    private SysEveRoleDao sysEveRoleDao;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = sysEveRoleDao.querySysRoleList(commonPageInfo);
        return beans;
    }

    @Override
    public Role getDataFromDb(String id) {
        Role role = super.getDataFromDb(id);
        List<String> menuIds = sysEveRoleDao.querySysRoleMenuIdByRoleId(id);
        List<String> appMenuIds = sysEveRoleDao.querySysRoleAppMenuIdByRoleId(id);
        role.setMenuIds(menuIds);
        role.setAppMenuIds(appMenuIds);
        return role;
    }

    @Override
    public void updatePostpose(Role entity, String userId) {
        // 删除缓存
        deleteRoleCache(entity.getId(), "delete");
    }

    /**
     * 获取所有模块(桌面)/菜单/权限点/分组/数据权限列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
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
        deskDefault.put("sysName", "基础系统");
        deskDefault.put("pageType", "桌面");
        beans.add(deskDefault);
        outputObject.setBeans(beans);
    }

    /**
     * 编辑角色PC端权限
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void editSysRolePCAuth(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        String roleId = map.get("id").toString();
        saveRoleMenuMation(map, roleId, user.get("id").toString(), DateUtil.getTimeAndToString());
        // 删除缓存
        deleteRoleCache(roleId, "delete");
        refreshCache(roleId);
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

    @Override
    public void deletePreExecution(String id) {
        // 判断当前是否有用户在使用该角色
        Integer useNum = sysEveRoleDao.queryUserRoleByRoleId(id);
        if (useNum > 0) {
            throw new CustomException("该角色下有用户正在使用，只能对角色进行维护.");
        }
    }

    @Override
    public void deletePostpose(String id) {
        // 删除角色菜单关联表信息
        sysEveRoleDao.deleteRoleMenuByRoleId(id);
        // 删除缓存
        deleteRoleCache(id, "delete");
    }

    /**
     * 获取角色需要绑定的手机端菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysRoleBandAppMenuList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveRoleDao.querySysRoleBandAppMenuList(map);
        outputObject.setBeans(beans);
    }

    /**
     * 手机端菜单授权
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysRoleAppMenuById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String[] menuIds = map.get("menuIds").toString().split(",");
        if (menuIds.length > 0) {
            String roleId = map.get("id").toString();
            // 桌面模块信息以及菜单页面信息
            List<Map<String, Object>> menuList = new ArrayList<>();
            for (String str : menuIds) {
                Map<String, Object> item = new HashMap<>();
                item.put("id", ToolUtil.getSurFaceId());
                item.put("roleId", roleId);
                item.put("menuId", str);
                menuList.add(item);
            }
            sysEveRoleDao.deleteRoleAppMenuByRoleId(map);
            sysEveRoleDao.insertSysRoleAppMenuMation(menuList);

            // 权限点信息以及数据权限信息
            List<Map<String, Object>> authPointList = new ArrayList<>();
            String[] pointIds = map.get("pointIds").toString().split(",");
            if (pointIds.length > 0) {
                for (String str : pointIds) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", ToolUtil.getSurFaceId());
                    item.put("roleId", roleId);
                    item.put("pointId", str);
                    authPointList.add(item);
                }
            }
            sysEveRoleDao.deleteRoleAppPointByRoleId(map);
            if (!authPointList.isEmpty()) {
                sysEveRoleDao.insertSysRoleAppPointMation(authPointList);
            }
            // 删除角色关联的APP菜单信息
            deleteAPPRoleCache(roleId);
            refreshCache(roleId);
        } else {
            outputObject.setreturnMessage("请选择该角色即将拥有的权限！");
        }
    }

    private void deleteRoleCache(String roleId, String type) {
        LOGGER.info("delete Role Cache, roleId is {}", roleId);
        jedisClientService.del(String.format("roleHasMenu:%s", roleId));
        jedisClientService.del(String.format("roleHasMenuPoint:%s", roleId));
        if ("delete".equals(type)) {
            deleteAPPRoleCache(roleId);
        }
    }

    private void deleteAPPRoleCache(String roleId) {
        LOGGER.info("delete Role app Cache");
        jedisClientService.del(String.format("roleHasAppMenu:%s", roleId));
        jedisClientService.del(String.format("roleHasAppMenuPoint:%s", roleId));
    }

}
