/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.List;
import java.util.Map;

public interface WagesStaffMationService {

    void queryWagesStaffWaitAllocatedMationList(InputObject inputObject, OutputObject outputObject);

    void queryStaffWagesModelFieldMationListByStaffId(InputObject inputObject, OutputObject outputObject);

    void saveStaffWagesModelFieldMation(InputObject inputObject, OutputObject outputObject);

    void queryWagesStaffDesignMationList(InputObject inputObject, OutputObject outputObject);

    void queryWagesStaffPaymentDetail(InputObject inputObject, OutputObject outputObject);

    /**
     * 设置应出勤的班次以及小时
     *
     * @param staffWorkTime      员工对应的考勤班次
     * @param staffModelFieldMap 员工拥有的所有薪资要素字段以及对应的值
     * @param lastMonthDate      指定年月，格式为yyyy-MM
     */
    void setLastMonthBe(List<Map<String, Object>> staffWorkTime, Map<String, String> staffModelFieldMap, String lastMonthDate);

}
