/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysStaffFamilyDao
 * @Description: 员工家庭情况管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 18:53
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysStaffFamilyDao {

    List<Map<String, Object>> queryAllSysStaffFamilyList(Map<String, Object> params);

    int insertSysStaffFamilyMation(Map<String, Object> map);

    Map<String, Object> querySysStaffFamilyMationToEdit(@Param("id") String id);

    int editSysStaffFamilyMationById(Map<String, Object> map);

    int deleteSysStaffFamilyMationById(@Param("id") String id);

    List<Map<String, Object>> queryPointStaffSysStaffFamilyList(PointStaffQueryDo pointStaffQuery);

}
