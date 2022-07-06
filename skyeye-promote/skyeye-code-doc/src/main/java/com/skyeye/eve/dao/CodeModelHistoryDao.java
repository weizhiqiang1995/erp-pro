/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.codedoc.history.CodeModelHistoryQueryDo;

import java.util.List;
import java.util.Map;

public interface CodeModelHistoryDao {

    List<Map<String, Object>> queryCodeModelHistoryList(CodeModelHistoryQueryDo codeModelHistoryQuery);

    List<Map<String, Object>> queryCodeModelHistoryListByFilePath(Map<String, Object> map);

}
