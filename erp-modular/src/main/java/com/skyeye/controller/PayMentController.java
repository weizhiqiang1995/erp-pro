package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.PayMentService;

/**
 * @Author 卫志强
 * @Description 付款单
 * @Date 2019/10/20 10:22
 */
@Controller
public class PayMentController {

    @Autowired
    private PayMentService payMentService;

    /**
     * 查询付款单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PayMentController/queryPayMentByList")
    @ResponseBody
    public void queryPayMentByList(InputObject inputObject, OutputObject outputObject) throws Exception{
        payMentService.queryPayMentByList(inputObject, outputObject);
    }

    /**
     * 添加付款单
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PayMentController/insertPayMent")
    @ResponseBody
    public void insertPayMent(InputObject inputObject, OutputObject outputObject) throws Exception{
        payMentService.insertPayMent(inputObject, outputObject);
    }

    /**
     * 查询付款单用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PayMentController/queryPayMentToEditById")
    @ResponseBody
    public void queryPayMentToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
        payMentService.queryPayMentToEditById(inputObject, outputObject);
    }

    /**
     * 编辑付款单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PayMentController/editPayMentById")
    @ResponseBody
    public void editPayMentById(InputObject inputObject, OutputObject outputObject) throws Exception{
        payMentService.editPayMentById(inputObject, outputObject);
    }

    /**
     * 删除付款单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PayMentController/deletePayMentById")
    @ResponseBody
    public void deletePayMentById(InputObject inputObject, OutputObject outputObject) throws Exception{
        payMentService.deletePayMentById(inputObject, outputObject);
    }

    /**
     * 查看付款单详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PayMentController/queryPayMentByDetail")
    @ResponseBody
    public void queryPayMentByDetail(InputObject inputObject, OutputObject outputObject) throws Exception{
        payMentService.queryPayMentByDetail(inputObject, outputObject);
    }
}
