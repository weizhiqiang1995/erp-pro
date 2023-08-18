/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.personnel.entity.SysEveUserStaff;

import java.util.Map;

/**
 * @ClassName: SysEveUserStaffService
 * @Description: 员工管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 11:50
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveUserStaffService extends SkyeyeBusinessService<SysEveUserStaff> {

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
