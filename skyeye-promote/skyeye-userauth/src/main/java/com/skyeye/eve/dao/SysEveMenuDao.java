/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveMenuDao
 * @Description: 菜单管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/5 21:43
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveMenuDao {

    List<Map<String, Object>> querySysMenuList(Map<String, Object> map);

    int insertSysMenuMation(Map<String, Object> map);

    Map<String, Object> querySysMenuMationToEditById(Map<String, Object> map);

    List<Map<String, Object>> querySysMenuMationBySimpleLevel(Map<String, Object> map);

    int editSysMenuMationById(Map<String, Object> map);

    int deleteSysMenuChildMationById(Map<String, Object> map);

    int deleteSysMenuMationById(Map<String, Object> map);

    Map<String, Object> queryUseThisMenuRoleById(Map<String, Object> map);

    List<Map<String, Object>> querySysMenuLevelList(Map<String, Object> map);

    Map<String, Object> querySysMenuAfterOrderBumByParentId(Map<String, Object> map);

    Map<String, Object> queryOldParentIdById(Map<String, Object> map);

    Map<String, Object> querySysEveMenuISTopByThisId(Map<String, Object> map);

    int editSysEveMenuSortTopById(Map<String, Object> map);

    Map<String, Object> querySysEveMenuISLowerByThisId(Map<String, Object> map);

    int editSysEveMenuSortLowerById(Map<String, Object> map);

    Map<String, Object> querySysEveMenuBySysId(Map<String, Object> map);

}
