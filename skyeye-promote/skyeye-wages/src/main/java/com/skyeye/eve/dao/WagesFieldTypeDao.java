/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WagesFieldTypeDao {

    List<Map<String, Object>> queryWagesFieldTypeList(Map<String, Object> map);

    List<Map<String, Object>> queryWagesFieldTypeListByKey(@Param("key") String key);

    int insertWagesFieldTypeMation(Map<String, Object> key);

    int insertWagesFieldTypeKeyToStaff(@Param("list") List<Map<String, Object>> staff);

    Map<String, Object> queryWagesFieldTypeMationById(@Param("id") String id);

    int editWagesFieldTypeMationById(Map<String, Object> map);

    int editWagesFieldTypeStateMationById(@Param("id") String id, @Param("state") int state);

    List<Map<String, Object>> queryEnableWagesFieldTypeList(Map<String, Object> map);

    List<Map<String, Object>> queryAllStaffMationList();

    /**
     * 获取所有的薪资要素字段，包括启用，禁用，以及删除的要素字段key；相同的key，该SQL语句会根据key进行分组
     *
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> queryAllWagesFieldTypeList();
}
