/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface SysEveWinTypeDao {

    List<Map<String, Object>> querySysWinTypeList(Map<String, Object> map);

    List<Map<String, Object>> querySysWinFirstTypeList(Map<String, Object> map);

    Map<String, Object> querySysWinTypeByNameANDLevel(Map<String, Object> map);

    Map<String, Object> querySysWinTypeBySimpleLevel(Map<String, Object> map);

    int insertSysWinTypeMation(Map<String, Object> map);

    Map<String, Object> querySysWinTypeMationToEditById(Map<String, Object> map);

    Map<String, Object> querySysWinTypeByNameANDLevelAndId(Map<String, Object> map);

    int editSysWinTypeMationById(Map<String, Object> map);

    List<Map<String, Object>> querySysWinFirstTypeListNotIsThisId(Map<String, Object> map);

    Map<String, Object> querySysWinTypeParentMationById(Map<String, Object> map);

    int deleteSysWinTypeMationById(Map<String, Object> map);

    int deleteSysWinTypeChildMationById(Map<String, Object> map);

    Map<String, Object> querySysWinTypeUpMationById(Map<String, Object> map);

    int editSysWinTypeMationOrderNumUpById(Map<String, Object> map);

    Map<String, Object> querySysWinTypeDownMationById(Map<String, Object> map);

    Map<String, Object> querySysWinTypeStateById(Map<String, Object> map);

    int editSysWinTypeMationStateUpById(Map<String, Object> map);

    int editSysWinTypeMationStateDownById(Map<String, Object> map);

    List<Map<String, Object>> querySysWinTypeFirstMationStateIsUp(Map<String, Object> map);

    List<Map<String, Object>> querySysWinTypeSecondMationStateIsUp(Map<String, Object> map);


}
