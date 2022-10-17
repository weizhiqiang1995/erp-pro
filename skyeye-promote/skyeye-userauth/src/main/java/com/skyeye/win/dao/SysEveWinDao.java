/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.dao;

import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.entity.userauth.win.SysEveWinMation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveWinDao
 * @Description: 服务信息管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:26
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveWinDao extends SkyeyeBaseMapper<SysEveWinMation> {

    List<Map<String, Object>> queryWinMationList(CommonPageInfo commonPageInfo);

    Map<String, Object> queryChildMationById(@Param("id")String id);

    List<Map<String, Object>> querySysEveWinList();
}
