/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */

package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @ClassName: CustomerController
 * @Description: 客户信息管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/26 9:48
 *
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 获取客户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerController/queryCustomerByList")
    @ResponseBody
    public void queryCustomerByList(InputObject inputObject, OutputObject outputObject) throws Exception{
        customerService.queryCustomerByList(inputObject, outputObject);
    }

    /**
     * 添加客户
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerContronller/insertCustomer")
    @ResponseBody
    public void insertCustomer(InputObject inputObject, OutputObject outputObject) throws Exception{
        customerService.insertCustomer(inputObject, outputObject);
    }

    /**
     * 根据ID查询客户信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerController/queryCustomerById")
    @ResponseBody
    public void queryCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception{
        customerService.queryCustomerById(inputObject, outputObject);
    }

    /**
     * 删除客户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerController/deleteCustomerById")
    @ResponseBody
    public void deleteCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception{
        customerService.deleteCustomerById(inputObject, outputObject);
    }

    /**
     * 编辑客户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerController/editCustomerById")
    @ResponseBody
    public void editCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception{
        customerService.editCustomerById(inputObject, outputObject);
    }

    /**
     * 客户状态改为启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerController/editCustomerByEnabled")
    @ResponseBody
    public void editCustomerByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception{
        customerService.editCustomerByEnabled(inputObject, outputObject);
    }

    /**
     * 客户状态改为未启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerController/editCustomerByNotEnabled")
    @ResponseBody
    public void editCustomerByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception{
        customerService.editCustomerByNotEnabled(inputObject, outputObject);
    }

    /**
     * 查看客户详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerController/queryCustomerByIdAndInfo")
    @ResponseBody
    public void queryCustomerByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception{
        customerService.queryCustomerByIdAndInfo(inputObject, outputObject);
    }
    
    /**
     * 获取客户列表信息展示为下拉框
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CustomerController/queryCustomerListToSelect")
    @ResponseBody
    public void queryCustomerListToSelect(InputObject inputObject, OutputObject outputObject) throws Exception{
    	customerService.queryCustomerListToSelect(inputObject, outputObject);
    }
    
}
