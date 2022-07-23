/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveWinDao
 * @Description: 系统信息管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:26
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveWinDao {

    List<Map<String, Object>> queryWinMationList(Map<String, Object> map);

    Map<String, Object> queryWinMationByNameOrUrl(Map<String, Object> map);

    int insertWinMation(Map<String, Object> map);

    Map<String, Object> queryWinMationToEditById(Map<String, Object> map);

    Map<String, Object> queryWinMationByNameOrUrlAndId(Map<String, Object> map);

    int editWinMationById(Map<String, Object> map);

    int deleteWinMationById(Map<String, Object> map);

    Map<String, Object> queryChildMationById(Map<String, Object> map);

    Map<String, Object> querySysEveWinNum(Map<String, Object> map);

    int insertAuthorizationById(Map<String, Object> map);

    int editCancleAuthorizationById(Map<String, Object> map);

    List<Map<String, Object>> queryWinMationListToShow(Map<String, Object> map);

    Map<String, Object> queryWinMationSynchronizationById(Map<String, Object> map);

    List<Map<String, Object>> queryWinMationImportantSynchronizationData(Map<String, Object> map);

    int insertWinMationImportantSynchronization(List<Map<String, Object>> rows);

    List<Map<String, Object>> queryWinMationSynchronizationByWinId(Map<String, Object> map);

    List<Map<String, Object>> queryWinMationImportantSynchronizationPointData(Map<String, Object> map);

}
