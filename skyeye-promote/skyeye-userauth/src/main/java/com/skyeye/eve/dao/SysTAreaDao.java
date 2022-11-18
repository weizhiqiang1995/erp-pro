/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.common.entity.TableSelectInfo;
import com.skyeye.eve.entity.userauth.area.SysTArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysTAreaDao extends SkyeyeBaseMapper<SysTArea> {

    List<Map<String, Object>> querySysTAreaList(TableSelectInfo selectInfo);

    List<Map<String, Object>> queryAreaListByParentCode(@Param("parentCode") String parentCode);

}
