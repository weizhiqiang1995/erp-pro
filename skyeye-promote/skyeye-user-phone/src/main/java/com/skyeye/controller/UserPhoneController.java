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
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/UserPhoneController/queryPhoneToLogin")
    public void queryPhoneToLogin(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryPhoneToLogin(inputObject, outputObject);
    }

    /**
     * 根据openId获取用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/UserPhoneController/queryUserMationByOpenId")
    public void queryUserMationByOpenId(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryUserMationByOpenId(inputObject, outputObject);
    }

    /**
     * openId绑定用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/UserPhoneController/insertUserMationByOpenId")
    public void insertUserMationByOpenId(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.insertUserMationByOpenId(inputObject, outputObject);
    }

    /**
     * 人员选择获取所有公司和人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/UserPhoneController/queryAllPeopleToTree")
    public void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) {
        userPhoneService.queryAllPeopleToTree(inputObject, outputObject);
    }

}
