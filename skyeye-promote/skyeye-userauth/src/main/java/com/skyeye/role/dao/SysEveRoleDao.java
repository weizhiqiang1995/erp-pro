/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.role.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.role.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveRoleDao
 * @Description: 角色管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:38
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveRoleDao extends SkyeyeBaseMapper<Role> {

    List<Map<String, Object>> querySysRoleList(CommonPageInfo commonPageInfo);

    List<Map<String, Object>> querySysRoleBandMenuList(Map<String, Object> map);

    int insertSysRoleMenuMation(List<Map<String, Object>> beans);

    List<String> querySysRoleMenuIdByRoleId(@Param("roleId") String roleId);

    int deleteRoleMenuByRoleId(@Param("roleId") String roleId);

    Integer queryUserRoleByRoleId(@Param("roleId") String roleId);

    List<Map<String, Object>> querySysRoleBandAppMenuList(Map<String, Object> map);

    List<String> querySysRoleAppMenuIdByRoleId(@Param("roleId") String roleId);

    int deleteRoleAppMenuByRoleId(Map<String, Object> map);

    int insertSysRoleAppMenuMation(List<Map<String, Object>> beans);

    int deleteRoleAppPointByRoleId(Map<String, Object> map);

    int insertSysRoleAppPointMation(List<Map<String, Object>> beans);

}
