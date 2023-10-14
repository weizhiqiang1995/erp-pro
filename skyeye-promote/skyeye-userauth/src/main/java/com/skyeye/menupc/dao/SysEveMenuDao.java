/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.menupc.entity.SysMenu;
import com.skyeye.menupc.entity.SysMenuQueryDo;
import org.apache.ibatis.annotations.Param;

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
public interface SysEveMenuDao extends SkyeyeBaseMapper<SysMenu> {

    List<Map<String, Object>> querySysMenuList(SysMenuQueryDo sysMenuQuery);

    List<Map<String, Object>> querySysMenuMationBySimpleLevel(Map<String, Object> map);

    Map<String, Object> queryUseThisMenuRoleById(@Param("id") String id);

    Map<String, Object> querySysMenuAfterOrderBumByParentId(@Param("parentId") String parentId);

    Map<String, Object> querySysEveMenuISTopByThisId(Map<String, Object> map);

    Map<String, Object> querySysEveMenuISLowerByThisId(Map<String, Object> map);

    /**
     * 根据父id查询所有的子节点信息(包含父id)，如果是多个
     *
     * @param ids 父id
     * @return
     */
    List<String> queryAllChildIdsByParentId(@Param("ids") List<String> ids);
}
