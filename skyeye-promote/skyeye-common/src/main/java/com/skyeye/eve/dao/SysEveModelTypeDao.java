/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveModelTypeDao
 * @Description: 系统模板分类数据层
 * @author: skyeye云系列
 * @date: 2021/11/13 10:11
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveModelTypeDao {

    List<Map<String, Object>> querySysEveModelTypeList(Map<String, Object> map);

    void insertSysEveModelType(Map<String, Object> map);

    Map<String, Object> querySysEveModelTypeById(@Param("id") String id);

    String querySysEveModelTypeByParentIdAndTypeName(Map<String, Object> map);

    List<Map<String, Object>> querySysEveModelTypeByParentId(@Param("parentId") String parentId);

    void updateSysEveModelTypeById(Map<String, Object> map);

    void delSysEveModelTypeById(@Param("id") String id);

    void delSysEveModelTypeByParentId(@Param("parentId") String parentId);

}
