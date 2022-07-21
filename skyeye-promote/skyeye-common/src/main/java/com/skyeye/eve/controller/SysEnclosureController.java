/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEnclosureController {

    @Autowired
    private SysEnclosureService sysEnclosureService;

    /**
     * 获取我的附件分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/querySysEnclosureListByUserId")
    public void querySysEnclosureListByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.querySysEnclosureListByUserId(inputObject, outputObject);
    }

    /**
     * 新增我的附件分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/insertSysEnclosureMationByUserId")
    public void insertSysEnclosureMationByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.insertSysEnclosureMationByUserId(inputObject, outputObject);
    }

    /**
     * 获取我的附件一级分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/querySysEnclosureFirstTypeListByUserId")
    public void querySysEnclosureFirstTypeListByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.querySysEnclosureFirstTypeListByUserId(inputObject, outputObject);
    }

    /**
     * 获取指定文件夹下的文件夹和文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/queryThisFolderChilsByFolderId")
    public void queryThisFolderChilsByFolderId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryThisFolderChilsByFolderId(inputObject, outputObject);
    }

    /**
     * 编辑我的附件分类时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/querySysEnclosureMationByUserIdToEdit")
    public void querySysEnclosureMationByUserIdToEdit(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.querySysEnclosureMationByUserIdToEdit(inputObject, outputObject);
    }

    /**
     * 编辑我的附件分类/附件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/editSysEnclosureMationByUserId")
    public void editSysEnclosureMationByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.editSysEnclosureMationByUserId(inputObject, outputObject);
    }

    /**
     * 上传文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/insertUploadFileByUserId")
    public void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.insertUploadFileByUserId(inputObject, outputObject);
    }

    /**
     * 上传文件合并块
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/insertUploadFileChunksByUserId")
    public void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.insertUploadFileChunksByUserId(inputObject, outputObject);
    }

    /**
     * 文件分块上传检测是否上传
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/queryUploadFileChunksByChunkMd5")
    public void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryUploadFileChunksByChunkMd5(inputObject, outputObject);
    }

    /**
     * 获取我的附件库
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/querySysEnclosureListToTreeByUserId")
    public void querySysEnclosureListToTreeByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.querySysEnclosureListToTreeByUserId(inputObject, outputObject);
    }

    /**
     * 人员选择获取所有公司和人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/queryAllPeopleToTree")
    public void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryAllPeopleToTree(inputObject, outputObject);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/queryCompanyPeopleToTreeByUserBelongCompany")
    public void queryCompanyPeopleToTreeByUserBelongCompany(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryCompanyPeopleToTreeByUserBelongCompany(inputObject, outputObject);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司部门展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/queryDepartmentPeopleToTreeByUserBelongDepartment")
    public void queryDepartmentPeopleToTreeByUserBelongDepartment(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryDepartmentPeopleToTreeByUserBelongDepartment(inputObject, outputObject);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司岗位展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/queryJobPeopleToTreeByUserBelongJob")
    public void queryJobPeopleToTreeByUserBelongJob(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryJobPeopleToTreeByUserBelongJob(inputObject, outputObject);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司同级部门展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/querySimpleDepPeopleToTreeByUserBelongSimpleDep")
    public void querySimpleDepPeopleToTreeByUserBelongSimpleDep(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.querySimpleDepPeopleToTreeByUserBelongSimpleDep(inputObject, outputObject);
    }

    /**
     * 根据聊天组展示用户
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/queryTalkGroupUserListByUserId")
    public void queryTalkGroupUserListByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryTalkGroupUserListByUserId(inputObject, outputObject);
    }

    /**
     * 一次性上传附件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEnclosureController/insertUploadFileToDataByUserId")
    public void insertUploadFileToDataByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.insertUploadFileToDataByUserId(inputObject, outputObject);
    }

    /**
     * 根据ids(逗号隔开)获取多个附件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnclosureInfoByIds", value = "根据ids(逗号隔开)获取多个附件信息", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "enclosureInfoIds", name = "enclosureInfoIds", value = "附件id(多个用逗号隔开)")})
    @RequestMapping("/post/SysEnclosureController/queryEnclosureInfo")
    public void queryEnclosureInfo(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryEnclosureInfo(inputObject, outputObject);
    }

}
