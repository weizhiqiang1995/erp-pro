/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.diskcloud.filerecycle.FileRecycleQueryDo;
import com.skyeye.eve.entity.diskcloud.fileshare.FileShareQueryDo;
import com.skyeye.eve.service.FileConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysWorkPlanController
 * @Description: 文件管理--云盘
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:26
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "文件管理--云盘", tags = "文件管理--云盘", modelName = "文件模块")
public class FileConsoleController {

    @Autowired
    private FileConsoleService fileConsoleService;

    /**
     * 根据当前用户获取目录
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryFileFolderByUserId")
    public void queryFileFolderByUserId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryFileFolderByUserId(inputObject, outputObject);
    }

    /**
     * 添加目录
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertFileFolderByUserId")
    public void insertFileFolderByUserId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertFileFolderByUserId(inputObject, outputObject);
    }

    /**
     * 获取这个目录下的所有文件+目录
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryFilesListByFolderId")
    public void queryFilesListByFolderId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryFilesListByFolderId(inputObject, outputObject);
    }

    /**
     * 删除目录以及目录下的所有文件
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "fileconsole004", value = "删除目录以及目录下的所有文件", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "fileList", name = "fileList", value = "要删除的目录id集合,包含rowId,fileType", required = "required,json")})
    @RequestMapping("/post/FileConsoleController/deleteFileFolderById")
    public void deleteFileFolderById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.deleteFileFolderById(inputObject, outputObject);
    }

    /**
     * 编辑目录名称
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/editFileFolderById")
    public void editFileFolderById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.editFileFolderById(inputObject, outputObject);
    }

    /**
     * 上传文件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertUploadFileByUserId")
    public void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertUploadFileByUserId(inputObject, outputObject);
    }

    /**
     * 上传文件合并块
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertUploadFileChunksByUserId")
    public void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertUploadFileChunksByUserId(inputObject, outputObject);
    }

    /**
     * 文件分块上传检测是否上传
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryUploadFileChunksByChunkMd5")
    public void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryUploadFileChunksByChunkMd5(inputObject, outputObject);
    }

    /**
     * 文件获取路径
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryUploadFilePathById")
    public void queryUploadFilePathById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryUploadFilePathById(inputObject, outputObject);
    }

    /**
     * office文件编辑
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping(value = "/post/FileConsoleController/editUploadOfficeFileById", method = RequestMethod.POST)
    public void editUploadOfficeFileById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.editUploadOfficeFileById(inputObject, outputObject);
    }

    /**
     * 根据当前用户获取总文件大小
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryAllFileSizeByUserId")
    public void queryAllFileSizeByUserId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryAllFileSizeByUserId(inputObject, outputObject);
    }

    /**
     * 加入回收站
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertFileCatalogToRecycleById")
    public void insertFileCatalogToRecycleById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertFileCatalogToRecycleById(inputObject, outputObject);
    }

    /**
     * 我的回收站
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "fileconsole014", value = "我的回收站", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = FileRecycleQueryDo.class)
    @RequestMapping("/post/FileConsoleController/queryFileRecycleBinByUserId")
    public void queryFileRecycleBinByUserId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryFileRecycleBinByUserId(inputObject, outputObject);
    }

    /**
     * 回收站内容还原
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/deleteFileRecycleBinById")
    public void deleteFileRecycleBinById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.deleteFileRecycleBinById(inputObject, outputObject);
    }

    /**
     * 文件分享
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertFileToShareById")
    public void insertFileToShareById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertFileToShareById(inputObject, outputObject);
    }

    /**
     * 文件分享列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "fileconsole017", value = "我的文件分享列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = FileShareQueryDo.class)
    @RequestMapping("/post/FileConsoleController/queryShareFileListByUserId")
    public void queryShareFileListByUserId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryShareFileListByUserId(inputObject, outputObject);
    }

    /**
     * 删除文件分享外链
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/deleteShareFileById")
    public void deleteShareFileById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.deleteShareFileById(inputObject, outputObject);
    }

    /**
     * 文件共享输入密码时获取文件信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryShareFileMationById")
    public void queryShareFileMationById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryShareFileMationById(inputObject, outputObject);
    }

    /**
     * 文件共享输入密码时校验
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryShareFileMationCheckById")
    public void queryShareFileMationCheckById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryShareFileMationCheckById(inputObject, outputObject);
    }

    /**
     * 获取分享文件基础信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryShareFileBaseMationById")
    public void queryShareFileBaseMationById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryShareFileBaseMationById(inputObject, outputObject);
    }

    /**
     * 根据父id获取该id下分享的文件和文件夹
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryShareFileListByParentId")
    public void queryShareFileListByParentId(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryShareFileListByParentId(inputObject, outputObject);
    }

    /**
     * 分享文件保存
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertShareFileListToSave")
    public void insertShareFileListToSave(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertShareFileListToSave(inputObject, outputObject);
    }

    /**
     * 文档在线预览
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryFileToShowById")
    public void queryFileToShowById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryFileToShowById(inputObject, outputObject);
    }

    /**
     * 新建word文件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertWordFileToService")
    public void insertWordFileToService(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertWordFileToService(inputObject, outputObject);
    }

    /**
     * 新建excel文件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertExcelFileToService")
    public void insertExcelFileToService(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertExcelFileToService(inputObject, outputObject);
    }

    /**
     * 新建ppt文件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertPPTFileToService")
    public void insertPPTFileToService(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertPPTFileToService(inputObject, outputObject);
    }

    /**
     * 新建txt文件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertTXTFileToService")
    public void insertTXTFileToService(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertTXTFileToService(inputObject, outputObject);
    }

    /**
     * 新建html文件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertHtmlFileToService")
    public void insertHtmlFileToService(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertHtmlFileToService(inputObject, outputObject);
    }

    /**
     * 创建副本
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertDuplicateCopyToService")
    public void insertDuplicateCopyToService(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertDuplicateCopyToService(inputObject, outputObject);
    }

    /**
     * 获取文件属性
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryFileMationById")
    public void queryFileMationById(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryFileMationById(inputObject, outputObject);
    }

    /**
     * 文件打包
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertFileMationToPackageToFolder")
    public void insertFileMationToPackageToFolder(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertFileMationToPackageToFolder(inputObject, outputObject);
    }

    /**
     * 压缩包解压
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertFileMationPackageToFolder")
    public void insertFileMationPackageToFolder(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertFileMationPackageToFolder(inputObject, outputObject);
    }

    /**
     * 文件或者文件夹复制
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertPasteCopyToService")
    public void insertPasteCopyToService(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertPasteCopyToService(inputObject, outputObject);
    }

    /**
     * 文件或者文件夹剪切
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertPasteCutToService")
    public void insertPasteCutToService(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertPasteCutToService(inputObject, outputObject);
    }

    /**
     * office文件编辑获取修改时间作为最新的key
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryOfficeUpdateTimeToKey")
    public void queryOfficeUpdateTimeToKey(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryOfficeUpdateTimeToKey(inputObject, outputObject);
    }

    /**
     * 文件统计报表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/queryFileNumStatistics")
    public void queryFileNumStatistics(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.queryFileNumStatistics(inputObject, outputObject);
    }

    /**
     * 文件打包下载
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/FileConsoleController/insertFileMationToPackageDownload")
    public void insertFileMationToPackageDownload(InputObject inputObject, OutputObject outputObject) {
        fileConsoleService.insertFileMationToPackageDownload(inputObject, outputObject);
    }

}
