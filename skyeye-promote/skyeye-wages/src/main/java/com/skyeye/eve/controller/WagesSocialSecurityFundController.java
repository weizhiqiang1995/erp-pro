/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.WagesSocialSecurityFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WagesSocialSecurityFundController {

    @Autowired
    private WagesSocialSecurityFundService wagesSocialSecurityFundService;

    /**
     * 获取社保公积金模板列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesSocialSecurityFundController/queryWagesSocialSecurityFundList")
    public void queryWagesSocialSecurityFundList(InputObject inputObject, OutputObject outputObject) throws Exception {
        wagesSocialSecurityFundService.queryWagesSocialSecurityFundList(inputObject, outputObject);
    }

    /**
     * 新增社保公积金模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesSocialSecurityFundController/insertWagesSocialSecurityFundMation")
    public void insertWagesSocialSecurityFundMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        wagesSocialSecurityFundService.insertWagesSocialSecurityFundMation(inputObject, outputObject);
    }

    /**
     * 编辑社保公积金模板信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesSocialSecurityFundController/queryWagesSocialSecurityFundMationToEditById")
    public void queryWagesSocialSecurityFundMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        wagesSocialSecurityFundService.queryWagesSocialSecurityFundMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑社保公积金模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesSocialSecurityFundController/editWagesSocialSecurityFundMationById")
    public void editWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        wagesSocialSecurityFundService.editWagesSocialSecurityFundMationById(inputObject, outputObject);
    }

    /**
     * 删除社保公积金模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesSocialSecurityFundController/deleteWagesSocialSecurityFundMationById")
    public void deleteWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        wagesSocialSecurityFundService.deleteWagesSocialSecurityFundMationById(inputObject, outputObject);
    }

    /**
     * 启用社保公积金模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesSocialSecurityFundController/enableWagesSocialSecurityFundMationById")
    public void enableWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        wagesSocialSecurityFundService.enableWagesSocialSecurityFundMationById(inputObject, outputObject);
    }

    /**
     * 禁用社保公积金模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesSocialSecurityFundController/disableWagesSocialSecurityFundMationById")
    public void disableWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        wagesSocialSecurityFundService.disableWagesSocialSecurityFundMationById(inputObject, outputObject);
    }

    /**
     * 社保公积金模板详情信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesSocialSecurityFundController/queryWagesSocialSecurityFundMationById")
    public void queryWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        wagesSocialSecurityFundService.queryWagesSocialSecurityFundMationById(inputObject, outputObject);
    }

}
