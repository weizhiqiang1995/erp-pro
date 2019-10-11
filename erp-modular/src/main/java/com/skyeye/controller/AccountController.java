package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 奈何繁华如云烟
 * @Description 结算账户信息
 * @Date 2019/10/6 15:31
 */
@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 查询账户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AccountController/queryAccountByList")
    @ResponseBody
    public void queryAccountByList(InputObject inputObject, OutputObject outputObject) throws Exception{
        accountService.queryAccountByList(inputObject, outputObject);
    }

    /**
     * 添加账户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AccountController/insertAccount")
    @ResponseBody
    public void insertAccount(InputObject inputObject, OutputObject outputObject) throws Exception{
        accountService.insertAccount(inputObject, outputObject);
    }

    /**
     * 查询单个账户信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AccountController/queryAccountById")
    @ResponseBody
    public void queryAccountById(InputObject inputObject, OutputObject outputObject) throws Exception{
        accountService.queryAccountById(inputObject, outputObject);
    }

    /**
     * 删除账户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AccountController/deleteAccountById")
    @ResponseBody
    public void deleteAccountById(InputObject inputObject, OutputObject outputObject) throws Exception{
        accountService.deleteAccountById(inputObject, outputObject);
    }

    /**
     * 编辑账户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AccountController/editAccountById")
    @ResponseBody
    public void editAccountById(InputObject inputObject, OutputObject outputObject) throws Exception{
        accountService.editAccountById(inputObject, outputObject);
    }

    /**
     * 设置默认
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AccountController/editAccountByIdAndIsDefault")
    @ResponseBody
    public void editAccountByIdAndIsDefault(InputObject inputObject, OutputObject outputObject) throws Exception{
        accountService.editAccountByIdAndIsDefault(inputObject, outputObject);
    }

    /**
     * 查看账户详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AccountController/queryAccountByIdAndInfo")
    @ResponseBody
    public void queryAccountByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception{
        accountService.queryAccountByIdAndInfo(inputObject, outputObject);
    }

    /**
     * 查看账户流水
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AccountController/queryAccountStreamById")
    @ResponseBody
    public void queryAccountStreamById(InputObject inputObject, OutputObject outputObject) throws Exception{
        accountService.queryAccountStreamById(inputObject, outputObject);
    }
}
