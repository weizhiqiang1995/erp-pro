/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface CompanyChatDao {

    Map<String, Object> queryUserMineByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyDepartmentByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryDepartmentUserByDepartId(Map<String, Object> depart);

    List<Map<String, Object>> queryUserGroupByUserId(Map<String, Object> map);

    int editUserSignByUserId(Map<String, Object> map);

}
