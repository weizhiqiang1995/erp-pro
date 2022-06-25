package com.skyeye.eve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysStaffLanguageService;

@Controller
public class SysStaffLanguageController {

    @Autowired
    private SysStaffLanguageService sysStaffLanguageService;

    /**
     * @param inputObject
     * @param outputObject
     * @throws Exception
     * @throws
     * @Title: queryAllSysStaffLanguageList
     * @Description: 查询所有语言能力列表
     * @return: void
     */
    @RequestMapping("/post/SysStaffLanguageController/queryAllSysStaffLanguageList")
    @ResponseBody
    public void queryAllSysStaffLanguageList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysStaffLanguageService.queryAllSysStaffLanguageList(inputObject, outputObject);
    }

    /**
     * @param inputObject
     * @param outputObject
     * @throws Exception
     * @throws
     * @Title: insertSysStaffLanguageMation
     * @Description: 员工语言能力信息录入
     * @return: void
     */
    @RequestMapping("/post/SysStaffLanguageController/insertSysStaffLanguageMation")
    @ResponseBody
    public void insertSysStaffLanguageMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysStaffLanguageService.insertSysStaffLanguageMation(inputObject, outputObject);
    }

    /**
     * @param inputObject
     * @param outputObject
     * @throws Exception
     * @throws
     * @Title: querySysStaffLanguageMationToEdit
     * @Description: 编辑员工语言能力信息时回显
     * @return: void
     */
    @RequestMapping("/post/SysStaffLanguageController/querySysStaffLanguageMationToEdit")
    @ResponseBody
    public void querySysStaffLanguageMationToEdit(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysStaffLanguageService.querySysStaffLanguageMationToEdit(inputObject, outputObject);
    }

    /**
     * @param inputObject
     * @param outputObject
     * @throws Exception
     * @throws
     * @Title: editSysStaffLanguageMationById
     * @Description: 编辑员工语言能力信息
     * @return: void
     */
    @RequestMapping("/post/SysStaffLanguageController/editSysStaffLanguageMationById")
    @ResponseBody
    public void editSysStaffLanguageMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysStaffLanguageService.editSysStaffLanguageMationById(inputObject, outputObject);
    }

    /**
     * @param inputObject
     * @param outputObject
     * @throws Exception
     * @throws
     * @Title: deleteSysStaffLanguageMationById
     * @Description: 删除员工语言能力信息
     * @return: void
     */
    @RequestMapping("/post/SysStaffLanguageController/deleteSysStaffLanguageMationById")
    @ResponseBody
    public void deleteSysStaffLanguageMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysStaffLanguageService.deleteSysStaffLanguageMationById(inputObject, outputObject);
    }

    /**
     * @param inputObject
     * @param outputObject
     * @throws Exception
     * @throws
     * @Title: queryPointStaffSysStaffLanguageList
     * @Description: 查询指定员工的语言能力列表
     * @return: void
     */
    @RequestMapping("/post/SysStaffLanguageController/queryPointStaffSysStaffLanguageList")
    @ResponseBody
    public void queryPointStaffSysStaffLanguageList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysStaffLanguageService.queryPointStaffSysStaffLanguageList(inputObject, outputObject);
    }

}
