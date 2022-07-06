/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.codedoc.develop.SysDeveLopDocQueryDo;

import java.util.List;
import java.util.Map;

public interface SysDeveLopDocDao {

    List<Map<String, Object>> querySysDeveLopTypeList(Map<String, Object> map);

    Map<String, Object> querySysDeveLopByName(Map<String, Object> map);

    Map<String, Object> queryMaxSysDeveLopBySimpleParentId(Map<String, Object> map);

    int insertSysDeveLopType(Map<String, Object> map);

    Map<String, Object> querySysDeveLopTypeByIdToEdit(Map<String, Object> map);

    int editSysDeveLopTypeById(Map<String, Object> map);

    int deleteSysDeveLopTypeById(Map<String, Object> map);

    Map<String, Object> querySysDeveLopTypeContentNumById(Map<String, Object> map);

    List<Map<String, Object>> querySysDeveLopTypeByFirstType(Map<String, Object> map);

    Map<String, Object> querySysDeveLopByNameAndId(Map<String, Object> map);

    Map<String, Object> querySysDeveLopTypeStateById(Map<String, Object> map);

    int editSysDeveLopTypeStateISupById(Map<String, Object> map);

    int editSysDeveLopTypeStateISdownById(Map<String, Object> map);

    Map<String, Object> querySysDeveLopTypeOrderByISupById(Map<String, Object> map);

    int editSysDeveLopTypeOrderByISupById(Map<String, Object> map);

    Map<String, Object> querySysDeveLopTypeOrderByISdownById(Map<String, Object> map);

    int editSysDeveLopTypeOrderByISdownById(Map<String, Object> bean);

    List<Map<String, Object>> querySysDeveLopDocList(SysDeveLopDocQueryDo sysDeveLopDocQuery);

    Map<String, Object> querySysDeveLopDocByNameAndParentId(Map<String, Object> map);

    Map<String, Object> queryMaxSysDeveLopDocBySimpleParentId(Map<String, Object> map);

    int insertSysDeveLopDoc(Map<String, Object> map);

    Map<String, Object> querySysDeveLopDocByIdToEdit(Map<String, Object> map);

    Map<String, Object> querySysDeveLopDocByNameAndId(Map<String, Object> map);

    int editSysDeveLopDocById(Map<String, Object> map);

    int deleteSysDeveLopDocById(Map<String, Object> map);

    Map<String, Object> querySysDeveLopDocStateById(Map<String, Object> map);

    int editSysDeveLopDocStateISupById(Map<String, Object> map);

    int editSysDeveLopDocStateISdownById(Map<String, Object> map);

    Map<String, Object> querySysDeveLopDocOrderByISupById(Map<String, Object> map);

    int editSysDeveLopDocOrderByISupById(Map<String, Object> map);

    Map<String, Object> querySysDeveLopDocOrderByISdownById(Map<String, Object> map);

    int editSysDeveLopDocOrderByISdownById(Map<String, Object> map);

    List<Map<String, Object>> querySysDeveLopFirstTypeToShow(Map<String, Object> map);

    List<Map<String, Object>> querySysDeveLopSecondTypeToShow(Map<String, Object> map);

    List<Map<String, Object>> querySysDeveLopDocToShow(Map<String, Object> map);

    Map<String, Object> querySysDeveLopDocContentToShow(Map<String, Object> map);

}
