/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysNoticeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysNoticeTypeController {

    @Autowired
    private SysNoticeTypeService sysNoticeTypeService;

    /**
     * 获取公告类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/querySysNoticeTypeList")
    public void querySysNoticeTypeList(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.querySysNoticeTypeList(inputObject, outputObject);
    }


    /**
     * 添加公告类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/insertSysNoticeTypeMation")
    public void insertSysNoticeTypeMation(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.insertSysNoticeTypeMation(inputObject, outputObject);
    }

    /**
     * 删除公告类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/deleteSysNoticeTypeById")
    public void deleteSysNoticeTypeById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.deleteSysNoticeTypeById(inputObject, outputObject);
    }

    /**
     * 上线公告类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/updateUpSysNoticeTypeById")
    public void updateUpSysNoticeTypeById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.updateUpSysNoticeTypeById(inputObject, outputObject);
    }

    /**
     * 下线公告类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/updateDownSysNoticeTypeById")
    public void updateDownSysNoticeTypeById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.updateDownSysNoticeTypeById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的公告类型信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/selectSysNoticeTypeById")
    public void selectSysNoticeTypeById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.selectSysNoticeTypeById(inputObject, outputObject);
    }

    /**
     * 编辑公告类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/editSysNoticeTypeMationById")
    public void editSysNoticeTypeMationById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.editSysNoticeTypeMationById(inputObject, outputObject);
    }

    /**
     * 公告类型上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/editSysNoticeTypeMationOrderNumUpById")
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.editSysNoticeTypeMationOrderNumUpById(inputObject, outputObject);
    }

    /**
     * 公告类型下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/editSysNoticeTypeMationOrderNumDownById")
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.editSysNoticeTypeMationOrderNumDownById(inputObject, outputObject);
    }

    /**
     * 获取已经上线的一级类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/queryFirstSysNoticeTypeUpStateList")
    public void queryFirstSysNoticeTypeUpStateList(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.queryFirstSysNoticeTypeUpStateList(inputObject, outputObject);
    }

    /**
     * 获取所有的一级类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/queryAllFirstSysNoticeTypeStateList")
    public void queryAllFirstSysNoticeTypeStateList(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.queryAllFirstSysNoticeTypeStateList(inputObject, outputObject);
    }

    /**
     * 获取上线的一级类型对应的上线的二级类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeTypeController/querySecondSysNoticeTypeUpStateList")
    public void querySecondSysNoticeTypeUpStateList(InputObject inputObject, OutputObject outputObject) {
        sysNoticeTypeService.querySecondSysNoticeTypeUpStateList(inputObject, outputObject);
    }
}
