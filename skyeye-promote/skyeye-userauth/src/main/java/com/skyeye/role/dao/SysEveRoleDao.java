/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.role.dao;

import com.skyeye.common.entity.CommonPageInfo;
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
public interface SysEveRoleDao {

    List<Map<String, Object>> querySysRoleList(CommonPageInfo commonPageInfo);

    List<Map<String, Object>> querySysRoleBandMenuList(Map<String, Object> map);

    Map<String, Object> querySysRoleNameByName(Map<String, Object> map);

    int insertSysRoleMation(Map<String, Object> map);

    int insertSysRoleMenuMation(List<Map<String, Object>> beans);

    Map<String, Object> querySysRoleMationByRoleId(Map<String, Object> map);

    List<Map<String, Object>> querySysRoleMenuIdByRoleId(Map<String, Object> map);

    Map<String, Object> queryRoleNameByIdAndName(Map<String, Object> map);

    int editSysRoleMationById(Map<String, Object> map);

    int deleteRoleMenuByRoleId(@Param("roleId") String roleId);

    Map<String, Object> queryUserRoleByRoleId(Map<String, Object> map);

    int deleteRoleByRoleId(Map<String, Object> map);

    List<Map<String, Object>> querySysRoleBandAppMenuList(Map<String, Object> map);

    List<Map<String, Object>> querySysRoleAppMenuIdByRoleId(Map<String, Object> map);

    int deleteRoleAppMenuByRoleId(Map<String, Object> map);

    int insertSysRoleAppMenuMation(List<Map<String, Object>> beans);

    int deleteRoleAppPointByRoleId(Map<String, Object> map);

    int insertSysRoleAppPointMation(List<Map<String, Object>> beans);

}
