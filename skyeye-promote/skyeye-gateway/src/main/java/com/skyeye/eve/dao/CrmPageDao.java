/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CrmPageDao {

    List<Map<String, Object>> queryInsertNumByYear(@Param("year") String year);

    List<Map<String, Object>> queryCustomNumByType();

    List<Map<String, Object>> queryCustomNumByFrom();

    List<Map<String, Object>> queryCustomNumByIndustry();

    List<Map<String, Object>> queryCustomNumByGroup();

    List<Map<String, Object>> queryCustomDocumentaryType(@Param("year") String year);

    List<Map<String, Object>> queryNewContractNum(@Param("year") String year);

    List<Map<String, Object>> queryNewDocumentaryNum(@Param("year") String year);

}
