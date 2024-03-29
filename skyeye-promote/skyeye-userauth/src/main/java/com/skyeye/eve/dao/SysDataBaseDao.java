/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface SysDataBaseDao {

    List<Map<String, Object>> querySysDataBaseSelectList(Map<String, Object> map);

    List<Map<String, Object>> querySysDataBaseDescSelectList(Map<String, Object> map);

}
