/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.dao;

import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.menupc.entity.SysMenuAuthPoint;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveMenuAuthPointDao
 * @Description: 菜单权限点管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 21:24
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface SysEveMenuAuthPointDao extends SkyeyeBaseMapper<SysMenuAuthPoint> {

    List<Map<String, Object>> queryMenuAuthPointList(TableSelectInfo selectInfo);

}
