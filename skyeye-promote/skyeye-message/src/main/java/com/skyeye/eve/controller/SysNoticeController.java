/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysNoticeController {

    @Autowired
    private SysNoticeService sysNoticeService;

    /**
     * 获取公告列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/querySysNoticeList")
    public void querySysNoticeList(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.querySysNoticeList(inputObject, outputObject);
    }


    /**
     * 添加公告
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/insertSysNoticeMation")
    public void insertSysNoticeMation(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.insertSysNoticeMation(inputObject, outputObject);
    }

    /**
     * 删除公告
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/deleteSysNoticeById")
    public void deleteSysNoticeById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.deleteSysNoticeById(inputObject, outputObject);
    }

    /**
     * 上线公告
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/updateUpSysNoticeById")
    public void updateUpSysNoticeById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.updateUpSysNoticeById(inputObject, outputObject);
    }

    /**
     * 下线公告
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/updateDownSysNoticeById")
    public void updateDownSysNoticeById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.updateDownSysNoticeById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的公告信息用以编辑
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/selectSysNoticeById")
    public void selectSysNoticeById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.selectSysNoticeById(inputObject, outputObject);
    }

    /**
     * 编辑公告
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/editSysNoticeMationById")
    public void editSysNoticeMationById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.editSysNoticeMationById(inputObject, outputObject);
    }

    /**
     * 公告上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/editSysNoticeMationOrderNumUpById")
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.editSysNoticeMationOrderNumUpById(inputObject, outputObject);
    }

    /**
     * 公告下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/editSysNoticeMationOrderNumDownById")
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.editSysNoticeMationOrderNumDownById(inputObject, outputObject);
    }

    /**
     * 定时上线时间
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/editSysNoticeTimeUpById")
    public void editSysNoticeTimeUpById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.editSysNoticeTimeUpById(inputObject, outputObject);
    }

    /**
     * 公告详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/querySysNoticeDetailsById")
    public void querySysNoticeDetailsById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.querySysNoticeDetailsById(inputObject, outputObject);
    }

    /**
     * 用户收到的公告
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/queryUserReceivedSysNotice")
    public void queryUserReceivedSysNotice(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.queryUserReceivedSysNotice(inputObject, outputObject);
    }

    /**
     * 用户收到的公告详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysNoticeController/queryReceivedSysNoticeDetailsById")
    public void queryReceivedSysNoticeDetailsById(InputObject inputObject, OutputObject outputObject) {
        sysNoticeService.queryReceivedSysNoticeDetailsById(inputObject, outputObject);
    }

}
