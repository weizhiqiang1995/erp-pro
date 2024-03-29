/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.dao;

import com.skyeye.eve.entity.userauth.user.SysUserQueryDo;
import com.skyeye.eve.entity.userauth.user.UserTreeQueryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEveUserDao {

    List<Map<String, Object>> querySysUserList(SysUserQueryDo sysUserQuery);

    Map<String, Object> querySysUserLockStateById(Map<String, Object> map);

    int editSysUserLockStateToLockById(Map<String, Object> map);

    int editSysUserLockStateToUnLockById(Map<String, Object> map);

    /**
     * 重置密码
     *
     * @param map
     * @return
     */
    int editSysUserPasswordMationById(Map<String, Object> map);

    Map<String, Object> queryMationByUserCode(@Param("userCode") String userCode);

    Map<String, Object> queryBindRoleMationByUserId(Map<String, Object> map);

    int editRoleIdsByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryDeskTopsMenuByUserId(@Param("userId") String userId);

    int editUserInstallThemeColor(Map<String, Object> map);

    int editUserInstallWinBgPic(Map<String, Object> map);

    int editUserInstallWinLockBgPic(Map<String, Object> map);

    int editUserInstallWinStartMenuSize(Map<String, Object> map);

    int editUserInstallWinTaskPosition(Map<String, Object> map);

    Map<String, Object> querySysUserCodeByMation(Map<String, Object> map);

    int insertSysUserMation(Map<String, Object> map);

    int insertSysUserInstallMation(Map<String, Object> map);

    int editUserPassword(Map<String, Object> bean);

    int editUserInstallVagueBgSrc(Map<String, Object> map);

    int editUserInstallLoadMenuIconById(Map<String, Object> map);

    Map<String, Object> queryUserDetailsMationByUserId(@Param("userId") String userId);

    int editUserDetailsMationByUserId(Map<String, Object> map);

    List<Map<String, Object>> querySysUserListByUserName(Map<String, Object> map);

    int editSysUserStaffBindUserId(Map<String, Object> map);

    List<Map<String, Object>> querySysDeskTopByUserId(Map<String, Object> user);

    Map<String, Object> queryUserSchoolMationByUserId(@Param("userId") String userId);

    /**
     * 修改用户状态
     *
     * @param userId    用户id
     * @param lockState 锁定状态
     * @return
     */
    int editSysUserLock(@Param("userId") String userId, @Param("lockState") Integer lockState);

    List<Map<String, Object>> queryUserStaffToTree(UserTreeQueryDo queryDo);

    List<Map<String, Object>> queryUserStaffDepToTree(UserTreeQueryDo queryDo);

    List<Map<String, Object>> queryTalkGroupUserListByUserId(UserTreeQueryDo queryDo);

    Map<String, Object> queryWxUserMationByOpenId(@Param("openId") String openId);

    Map<String, Object> queryUserMationByOpenId(@Param("openId") String openId);

    Map<String, Object> queryUserBindMationByUserId(@Param("userId") String userId);

    int updateBindUserMation(Map<String, Object> map);

    int insertWxUserMation(Map<String, Object> map);

}
