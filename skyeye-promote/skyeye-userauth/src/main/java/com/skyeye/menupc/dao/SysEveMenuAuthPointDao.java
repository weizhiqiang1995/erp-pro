/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.menupc.entity.SysMenuAuthPointMation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEveMenuAuthPointDao extends SkyeyeBaseMapper<SysMenuAuthPointMation> {

    List<Map<String, Object>> querySysEveMenuAuthPointListByMenuId(Map<String, Object> map);

    int insertBatch(@Param("list") List<SysMenuAuthPointMation> sysMenuAuthPointMationList);

}
