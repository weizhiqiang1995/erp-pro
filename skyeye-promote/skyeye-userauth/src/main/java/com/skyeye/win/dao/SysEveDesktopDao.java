/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.win.entity.SysDesktop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveDesktopDao
 * @Description: 桌面信息管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/22 19:23
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveDesktopDao extends SkyeyeBaseMapper<SysDesktop> {

    List<Map<String, Object>> querySysDesktopList(CommonPageInfo commonPageInfo);

    Map<String, Object> querySysDesktopUpMationById(Map<String, Object> map);

    int editSysDesktopMationOrderNumUpById(Map<String, Object> map);

    Map<String, Object> querySysDesktopDownMationById(Map<String, Object> map);

    Map<String, Object> querySysDesktopStateAndMenuNumById(@Param("id") String id);

    Integer querySysEveDesktopMaxOrderBum();
}
