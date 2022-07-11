/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ErpProducePageDao {

    List<Map<String, Object>> queryPickMaterialYearByDepartmentId(@Param("departmentId") String departmentId, @Param("year") String year);

    List<Map<String, Object>> queryPickMaterialNumYearByDepartmentId(@Param("departmentId") String departmentId, @Param("year") String year,
                                                                     @Param("id") String id);

    List<Map<String, Object>> queryPatchMaterialYearByDepartmentId(@Param("departmentId") String departmentId, @Param("year") String year);

    List<Map<String, Object>> queryPatchMaterialNumYearByDepartmentId(@Param("departmentId") String departmentId, @Param("year") String year,
                                                                      @Param("id") String id);

    List<Map<String, Object>> queryReturnMaterialYearByDepartmentId(@Param("departmentId") String departmentId, @Param("year") String year);

    List<Map<String, Object>> queryReturnMaterialNumYearByDepartmentId(@Param("departmentId") String departmentId, @Param("year") String year,
                                                                       @Param("id") String id);

    List<Map<String, Object>> queryDepartmentMachin(@Param("departmentId") String departmentId, @Param("year") String year);

}
