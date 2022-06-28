/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WagesModelTypeDao {

    public List<Map<String, Object>> queryWagesModelTypeList(Map<String, Object> map);

    public Map<String, Object> queryWagesModelTypeByNameAndNotId(@Param("nameCn") String nameCn, @Param("notId") String id);

    public int insertWagesModelTypeMation(Map<String, Object> map);

    public Map<String, Object> queryWagesModelTypeMationToEditById(@Param("id") String id);

    public int editWagesModelTypeMationById(Map<String, Object> map);

    public int editWagesModelTypeStateMationById(@Param("id") String id, @Param("state") int state);

    public List<Map<String, Object>> queryEnableWagesModelTypeList(Map<String, Object> map);
}
