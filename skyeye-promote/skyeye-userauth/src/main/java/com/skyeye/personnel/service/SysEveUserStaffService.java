/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.Map;

public interface SysEveUserStaffService {

    void querySysUserStaffList(InputObject inputObject, OutputObject outputObject);

    void insertSysUserStaffMation(InputObject inputObject, OutputObject outputObject);

    void querySysUserStaffById(InputObject inputObject, OutputObject outputObject);

    void editSysUserStaffById(InputObject inputObject, OutputObject outputObject);

    void querySysUserStaffByIdToDetails(InputObject inputObject, OutputObject outputObject);

    void editSysUserStaffState(InputObject inputObject, OutputObject outputObject);

    void editTurnTeacher(InputObject inputObject, OutputObject outputObject);

    void insertNewUserMation(Map<String, Object> map);

    void querySysUserStaffListToTable(InputObject inputObject, OutputObject outputObject);

    void querySysUserStaffListByIds(InputObject inputObject, OutputObject outputObject);

    void querySysUserStaffLogin(InputObject inputObject, OutputObject outputObject);

    void queryUserMationList(InputObject inputObject, OutputObject outputObject);

    void editSysUserStaffAnnualLeaveById(InputObject inputObject, OutputObject outputObject);

    void updateSysUserStaffHolidayNumberById(InputObject inputObject, OutputObject outputObject);

    void updateSysUserStaffRetiredHolidayNumberById(InputObject inputObject, OutputObject outputObject);

    void queryStaffCheckWorkTimeRelationNameByStaffId(InputObject inputObject, OutputObject outputObject);

}
