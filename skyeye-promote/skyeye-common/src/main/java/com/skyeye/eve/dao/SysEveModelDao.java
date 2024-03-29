/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.sysmodel.SysEveModelQueryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveModelDao
 * @Description: 系统编辑器模板数据层
 * @author: skyeye云系列
 * @date: 2021/11/14 9:03
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveModelDao {

    List<Map<String, Object>> querySysEveModelList(SysEveModelQueryDo sysEveModelQuery);

    Map<String, Object> querySysEveModelMationByNameAndType(Map<String, Object> map);

    int insertSysEveModelMation(Map<String, Object> map);

    int deleteSysEveModelById(@Param("id") String id);

    Map<String, Object> selectSysEveModelMationById(@Param("id") String id);

    int editSysEveModelMationById(Map<String, Object> map);

}
