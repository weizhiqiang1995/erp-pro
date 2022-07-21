/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.MyNoteService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyNoteController {

    @Autowired
    private MyNoteService myNoteService;

    /**
     * 根据当前用户获取笔记文件夹
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/queryFileMyNoteByUserId")
    public void queryFileMyNoteByUserId(InputObject inputObject, OutputObject outputObject) {
        myNoteService.queryFileMyNoteByUserId(inputObject, outputObject);
    }

    /**
     * 添加文件夹
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/insertFileMyNoteByUserId")
    public void insertFileMyNoteByUserId(InputObject inputObject, OutputObject outputObject) {
        myNoteService.insertFileMyNoteByUserId(inputObject, outputObject);
    }

    /**
     * 删除文件夹以及文件夹下的所有文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/deleteFileFolderById")
    public void deleteFileFolderById(InputObject inputObject, OutputObject outputObject) {
        myNoteService.deleteFileFolderById(inputObject, outputObject);
    }

    /**
     * 编辑文件夹名称
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/editFileFolderById")
    public void editFileFolderById(InputObject inputObject, OutputObject outputObject) {
        myNoteService.editFileFolderById(inputObject, outputObject);
    }

    /**
     * 根据当前用户获取最新的笔记列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/queryMyNoteListNewByUserId")
    public void queryMyNoteListNewByUserId(InputObject inputObject, OutputObject outputObject) {
        myNoteService.queryMyNoteListNewByUserId(inputObject, outputObject);
    }

    /**
     * 添加笔记
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/insertMyNoteContentByUserId")
    public void insertMyNoteContentByUserId(InputObject inputObject, OutputObject outputObject) {
        myNoteService.insertMyNoteContentByUserId(inputObject, outputObject);
    }

    /**
     * 根据文件夹id获取文件夹下的文件夹和笔记列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/queryFileAndContentListByFolderId")
    public void queryFileAndContentListByFolderId(InputObject inputObject, OutputObject outputObject) {
        myNoteService.queryFileAndContentListByFolderId(inputObject, outputObject);
    }

    /**
     * 编辑笔记时回显信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/queryMyNoteContentMationById")
    public void queryMyNoteContentMationById(InputObject inputObject, OutputObject outputObject) {
        myNoteService.queryMyNoteContentMationById(inputObject, outputObject);
    }

    /**
     * 编辑笔记信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/editMyNoteContentById")
    public void editMyNoteContentById(InputObject inputObject, OutputObject outputObject) {
        myNoteService.editMyNoteContentById(inputObject, outputObject);
    }

    /**
     * 保存文件夹拖拽后的信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/editFileToDragById")
    public void editFileToDragById(InputObject inputObject, OutputObject outputObject) {
        myNoteService.editFileToDragById(inputObject, outputObject);
    }

    /**
     * 保存笔记移动后的信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/editNoteToMoveById")
    public void editNoteToMoveById(InputObject inputObject, OutputObject outputObject) {
        myNoteService.editNoteToMoveById(inputObject, outputObject);
    }

    /**
     * 获取文件夹或笔记移动时的选择树
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/queryTreeToMoveByUserId")
    public void queryTreeToMoveByUserId(InputObject inputObject, OutputObject outputObject) {
        myNoteService.queryTreeToMoveByUserId(inputObject, outputObject);
    }

    /**
     * 根据id获取分享笔记的内容
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/queryShareNoteById")
    public void queryShareNoteById(InputObject inputObject, OutputObject outputObject) {
        myNoteService.queryShareNoteById(inputObject, outputObject);
    }

    /**
     * 根据id(文件夹或者笔记id)将笔记输出为压缩包
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/MyNoteController/outputNoteIsZipJob")
    public void outputNoteIsZipJob(InputObject inputObject, OutputObject outputObject) {
        myNoteService.outputNoteIsZipJob(inputObject, outputObject);
    }

}
