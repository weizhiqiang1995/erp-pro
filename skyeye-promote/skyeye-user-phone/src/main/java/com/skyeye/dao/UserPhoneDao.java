/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserPhoneDao {

    Map<String, Object> queryMationByUserCode(Map<String, Object> map);

    Map<String, Object> queryUserMationByOpenId(String openId);

    int insertWxUserMation(Map<String, Object> map);

    Map<String, Object> queryUserBindMationByUserId(@Param("userId") String userId);

    int updateBindUserMation(Map<String, Object> map);

    Map<String, Object> queryUserMationByOPenId(String openId);

    List<Map<String, Object>> queryAllPeopleToTree(Map<String, Object> map);

}
