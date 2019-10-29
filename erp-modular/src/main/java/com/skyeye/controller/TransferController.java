package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.TransferService;

/**
 * @Author 卫志强
 * @Description 转账单
 * @Date 2019/10/20 10:22
 */
@Controller
public class TransferController {

    @Autowired
    private TransferService transferService;

    /**
     * 查询转账单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/TransferController/queryTransferByList")
    @ResponseBody
    public void queryTransferByList(InputObject inputObject, OutputObject outputObject) throws Exception{
        transferService.queryTransferByList(inputObject, outputObject);
    }

    /**
     * 添加转账单
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/TransferController/insertTransfer")
    @ResponseBody
    public void insertTransfer(InputObject inputObject, OutputObject outputObject) throws Exception{
        transferService.insertTransfer(inputObject, outputObject);
    }

    /**
     * 查询转账单用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/TransferController/queryTransferToEditById")
    @ResponseBody
    public void queryTransferToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
        transferService.queryTransferToEditById(inputObject, outputObject);
    }

    /**
     * 编辑转账单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/TransferController/editTransferById")
    @ResponseBody
    public void editTransferById(InputObject inputObject, OutputObject outputObject) throws Exception{
        transferService.editTransferById(inputObject, outputObject);
    }

    /**
     * 删除转账单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/TransferController/deleteTransferById")
    @ResponseBody
    public void deleteTransferById(InputObject inputObject, OutputObject outputObject) throws Exception{
        transferService.deleteTransferById(inputObject, outputObject);
    }

    /**
     * 查看转账单详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/TransferController/queryTransferByDetail")
    @ResponseBody
    public void queryTransferByDetail(InputObject inputObject, OutputObject outputObject) throws Exception{
        transferService.queryTransferByDetail(inputObject, outputObject);
    }
    
    /**
     * 导出Excel
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/TransferController/queryMationToExcel")
    @ResponseBody
    public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception{
    	transferService.queryMationToExcel(inputObject, outputObject);
    }
    
}
