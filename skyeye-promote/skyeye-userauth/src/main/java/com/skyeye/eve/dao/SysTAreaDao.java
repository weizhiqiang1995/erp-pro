/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.eve.entity.userauth.area.SysTArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysTAreaDao
 * @Description: 区域管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/11 15:09
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysTAreaDao extends SkyeyeBaseMapper<SysTArea> {

    List<Map<String, Object>> querySysTAreaList(TableSelectInfo selectInfo);

    List<Map<String, Object>> queryAreaListByParentCode(@Param("parentCode") String parentCode);

    List<Map<String, Object>> queryTAreaPhoneList(Map<String, Object> map);

}
