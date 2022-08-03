/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MQUserEmailDao {

    Map<String, Object> queryServiceMationBySericeId(@Param("serviceId") String serviceId);

    List<Map<String, Object>> queryCooperationUserNameById(@Param("serviceId") String serviceId);

    int insertNoticeListMation(List<Map<String, Object>> notices);

}
