package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 奈何繁华如云烟
 * @Description 供应商管理
 * @Date 2019/9/16 21:21
 */
@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 获取供应商信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SupplierController/querySupplierByList")
    @ResponseBody
    public void querySupplierByList(InputObject inputObject, OutputObject outputObject) throws Exception{
        supplierService.querySupplierByList(inputObject, outputObject);
    }

    /**
     * 添加供应商
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SupplierContronller/insertSupplier")
    @ResponseBody
    public void insertSupplier(InputObject inputObject, OutputObject outputObject) throws Exception{
        supplierService.insertSupplier(inputObject, outputObject);
    }

    /**
     * 根据ID查询供应商信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SupplierController/querySupplierById")
    @ResponseBody
    public void querySupplierById(InputObject inputObject, OutputObject outputObject) throws Exception{
        supplierService.querySupplierById(inputObject, outputObject);
    }

    /**
     * 删除供应商信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SupplierController/deleteSupplierById")
    @ResponseBody
    public void deleteSupplierById(InputObject inputObject, OutputObject outputObject) throws Exception{
        supplierService.deleteSupplierById(inputObject, outputObject);
    }

    /**
     * 编辑供应商信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SupplierController/editSupplierById")
    @ResponseBody
    public void editSupplierById(InputObject inputObject, OutputObject outputObject) throws Exception{
        supplierService.editSupplierById(inputObject, outputObject);
    }

    /**
     * 供应商状态改为启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SupplierController/editSupplierByEnabled")
    @ResponseBody
    public void editSupplierByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception{
        supplierService.editSupplierByEnabled(inputObject, outputObject);
    }

    /**
     * 供应商状态改为未启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SupplierController/editSupplierByNotEnabled")
    @ResponseBody
    public void editSupplierByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception{
        supplierService.editSupplierByNotEnabled(inputObject, outputObject);
    }

    /**
     * 查看供应商详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SupplierController/querySupplierByIdAndInfo")
    @ResponseBody
    public void querySupplierByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception{
        supplierService.querySupplierByIdAndInfo(inputObject, outputObject);
    }
}
