/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPhoneController {

    @Autowired
    private UserPhoneService userPhoneService;

    /**
     * 手机端用户登录
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserPhoneController/queryPhoneToLogin")
    public void queryPhoneToLogin(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryPhoneToLogin(inputObject, outputObject);
    }

    /**
     * 手机端从session中获取用户信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserPhoneController/queryPhoneUserMation")
    public void queryPhoneUserMation(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryPhoneUserMation(inputObject, outputObject);
    }

    /**
     * 手机端从session中获取菜单权限信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserPhoneController/queryPhoneUserMenuAuth")
    public void queryPhoneUserMenuAuth(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryPhoneUserMenuAuth(inputObject, outputObject);
    }

    /**
     * 手机端注销登录
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserPhoneController/queryPhoneToExit")
    public void queryPhoneToExit(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryPhoneToExit(inputObject, outputObject);
    }

    /**
     * 根据openId获取用户信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserPhoneController/queryUserMationByOpenId")
    public void queryUserMationByOpenId(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryUserMationByOpenId(inputObject, outputObject);
    }

    /**
     * openId绑定用户信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserPhoneController/insertUserMationByOpenId")
    public void insertUserMationByOpenId(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.insertUserMationByOpenId(inputObject, outputObject);
    }

    /**
     * 人员选择获取所有公司和人
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserPhoneController/queryAllPeopleToTree")
    public void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryAllPeopleToTree(inputObject, outputObject);
    }

}
