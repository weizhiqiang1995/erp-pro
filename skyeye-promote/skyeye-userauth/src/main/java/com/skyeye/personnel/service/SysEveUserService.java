/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveUserService {

    void querySysUserList(InputObject inputObject, OutputObject outputObject);

    void editSysUserLockStateToLockById(InputObject inputObject, OutputObject outputObject);

    void editSysUserLockStateToUnLockById(InputObject inputObject, OutputObject outputObject);

    void editSysUserPasswordMationById(InputObject inputObject, OutputObject outputObject);

    void queryUserToLogin(InputObject inputObject, OutputObject outputObject);

    void queryUserMationBySession(InputObject inputObject, OutputObject outputObject);

    void deleteUserMationBySession(InputObject inputObject, OutputObject outputObject);

    /**
     * 退出登录
     *
     * @param userId 用户id
     */
    void removeLogin(String userId);

    void queryRoleAndBindRoleByUserId(InputObject inputObject, OutputObject outputObject);

    void editRoleIdsByUserId(InputObject inputObject, OutputObject outputObject);

    void queryDeskTopMenuBySession(InputObject inputObject, OutputObject outputObject);

    void queryAllMenuBySession(InputObject inputObject, OutputObject outputObject);

    void editUserInstallThemeColor(InputObject inputObject, OutputObject outputObject);

    void editUserInstallWinBgPic(InputObject inputObject, OutputObject outputObject);

    void editUserInstallWinLockBgPic(InputObject inputObject, OutputObject outputObject);

    void editUserInstallWinStartMenuSize(InputObject inputObject, OutputObject outputObject);

    void editUserInstallWinTaskPosition(InputObject inputObject, OutputObject outputObject);

    void insertSysUserMationById(InputObject inputObject, OutputObject outputObject);

    void editUserPassword(InputObject inputObject, OutputObject outputObject);

    void editUserInstallVagueBgSrc(InputObject inputObject, OutputObject outputObject);

    void editUserInstallLoadMenuIconById(InputObject inputObject, OutputObject outputObject);

    void queryUserLockByLockPwd(InputObject inputObject, OutputObject outputObject);

    void queryUserDetailsMationByUserId(InputObject inputObject, OutputObject outputObject);

    void editUserDetailsMationByUserId(InputObject inputObject, OutputObject outputObject);

    void querySysUserListByUserName(InputObject inputObject, OutputObject outputObject);

    void querySysDeskTopByUserId(InputObject inputObject, OutputObject outputObject);

    void queryDeskTopsMenuByUserId(InputObject inputObject, OutputObject outputObject);

    void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject);

    void queryCompanyPeopleToTreeByUserBelongCompany(InputObject inputObject, OutputObject outputObject);

    void queryDepartmentPeopleToTreeByUserBelongDepartment(InputObject inputObject, OutputObject outputObject);

    void queryJobPeopleToTreeByUserBelongJob(InputObject inputObject, OutputObject outputObject);

    void querySimpleDepPeopleToTreeByUserBelongSimpleDep(InputObject inputObject, OutputObject outputObject);

    void queryTalkGroupUserListByUserId(InputObject inputObject, OutputObject outputObject);

    void queryAllSysUserIsIncumbency(InputObject inputObject, OutputObject outputObject);
}
