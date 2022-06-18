/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.FileConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileConsoleController {
	
	@Autowired
	private FileConsoleService fileConsoleService;

	/**
	 * 根据当前用户获取目录
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryFileFolderByUserId")
	public void queryFileFolderByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryFileFolderByUserId(inputObject, outputObject);
	}

	/**
	 * 添加目录
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertFileFolderByUserId")
	public void insertFileFolderByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertFileFolderByUserId(inputObject, outputObject);
	}

	/**
	 * 获取这个目录下的所有文件+目录
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryFilesListByFolderId")
	public void queryFilesListByFolderId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryFilesListByFolderId(inputObject, outputObject);
	}

	/**
	 * 删除目录以及目录下的所有文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/deleteFileFolderById")
	public void deleteFileFolderById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.deleteFileFolderById(inputObject, outputObject);
	}

	/**
	 * 编辑目录名称
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/editFileFolderById")
	public void editFileFolderById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.editFileFolderById(inputObject, outputObject);
	}

	/**
	 * 上传文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertUploadFileByUserId")
	public void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertUploadFileByUserId(inputObject, outputObject);
	}

	/**
	 * 上传文件合并块
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertUploadFileChunksByUserId")
	public void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertUploadFileChunksByUserId(inputObject, outputObject);
	}

	/**
	 * 文件分块上传检测是否上传
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryUploadFileChunksByChunkMd5")
	public void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryUploadFileChunksByChunkMd5(inputObject, outputObject);
	}

	/**
	 * 文件获取路径
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryUploadFilePathById")
	public void queryUploadFilePathById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryUploadFilePathById(inputObject, outputObject);
	}

	/**
	 * office文件编辑
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping(value="/post/FileConsoleController/editUploadOfficeFileById", method = RequestMethod.POST)
	public void editUploadOfficeFileById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.editUploadOfficeFileById(inputObject, outputObject);
	}

	/**
	 * 根据当前用户获取总文件大小
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryAllFileSizeByUserId")
	public void queryAllFileSizeByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryAllFileSizeByUserId(inputObject, outputObject);
	}

	/**
	 * 加入回收站
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertFileCatalogToRecycleById")
	public void insertFileCatalogToRecycleById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertFileCatalogToRecycleById(inputObject, outputObject);
	}

	/**
	 * 我的回收站
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryFileRecycleBinByUserId")
	public void queryFileRecycleBinByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryFileRecycleBinByUserId(inputObject, outputObject);
	}

	/**
	 * 回收站内容还原
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/deleteFileRecycleBinById")
	public void deleteFileRecycleBinById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.deleteFileRecycleBinById(inputObject, outputObject);
	}

	/**
	 * 文件分享
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertFileToShareById")
	public void insertFileToShareById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertFileToShareById(inputObject, outputObject);
	}

	/**
	 * 文件分享列表
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryShareFileListByUserId")
	public void queryShareFileListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryShareFileListByUserId(inputObject, outputObject);
	}

	/**
	 * 删除文件分享外链
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/deleteShareFileById")
	public void deleteShareFileById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.deleteShareFileById(inputObject, outputObject);
	}

	/**
	 * 文件共享输入密码时获取文件信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryShareFileMationById")
	public void queryShareFileMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryShareFileMationById(inputObject, outputObject);
	}

	/**
	 * 文件共享输入密码时校验
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryShareFileMationCheckById")
	public void queryShareFileMationCheckById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryShareFileMationCheckById(inputObject, outputObject);
	}

	/**
	 * 获取分享文件基础信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryShareFileBaseMationById")
	public void queryShareFileBaseMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryShareFileBaseMationById(inputObject, outputObject);
	}

	/**
	 * 根据父id获取该id下分享的文件和文件夹
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryShareFileListByParentId")
	public void queryShareFileListByParentId(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryShareFileListByParentId(inputObject, outputObject);
	}

	/**
	 * 分享文件保存
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertShareFileListToSave")
	public void insertShareFileListToSave(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertShareFileListToSave(inputObject, outputObject);
	}

	/**
	 * 文档在线预览
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryFileToShowById")
	public void queryFileToShowById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryFileToShowById(inputObject, outputObject);
	}

	/**
	 * 新建word文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertWordFileToService")
	public void insertWordFileToService(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertWordFileToService(inputObject, outputObject);
	}

	/**
	 * 新建excel文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertExcelFileToService")
	public void insertExcelFileToService(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertExcelFileToService(inputObject, outputObject);
	}

	/**
	 * 新建ppt文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertPPTFileToService")
	public void insertPPTFileToService(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertPPTFileToService(inputObject, outputObject);
	}

	/**
	 * 新建txt文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertTXTFileToService")
	public void insertTXTFileToService(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertTXTFileToService(inputObject, outputObject);
	}

	/**
	 * 新建html文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertHtmlFileToService")
	public void insertHtmlFileToService(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertHtmlFileToService(inputObject, outputObject);
	}

	/**
	 * 创建副本
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertDuplicateCopyToService")
	public void insertDuplicateCopyToService(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertDuplicateCopyToService(inputObject, outputObject);
	}

	/**
	 * 获取文件属性
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryFileMationById")
	public void queryFileMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.queryFileMationById(inputObject, outputObject);
	}

	/**
	 * 文件打包
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertFileMationToPackageToFolder")
	public void insertFileMationToPackageToFolder(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertFileMationToPackageToFolder(inputObject, outputObject);
	}

	/**
	 * 压缩包解压
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertFileMationPackageToFolder")
	public void insertFileMationPackageToFolder(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertFileMationPackageToFolder(inputObject, outputObject);
	}

	/**
	 * 文件或者文件夹复制
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertPasteCopyToService")
	public void insertPasteCopyToService(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertPasteCopyToService(inputObject, outputObject);
	}

	/**
	 * 文件或者文件夹剪切
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertPasteCutToService")
	public void insertPasteCutToService(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertPasteCutToService(inputObject, outputObject);
	}

	/**
	 * office文件编辑获取修改时间作为最新的key
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
    @RequestMapping("/post/FileConsoleController/queryOfficeUpdateTimeToKey")
    public void queryOfficeUpdateTimeToKey(InputObject inputObject, OutputObject outputObject) throws Exception{
        fileConsoleService.queryOfficeUpdateTimeToKey(inputObject, outputObject);
    }

	/**
	 * 文件统计报表
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/queryFileNumStatistics")
    public void queryFileNumStatistics(InputObject inputObject, OutputObject outputObject) throws Exception{
        fileConsoleService.queryFileNumStatistics(inputObject, outputObject);
    }

	/**
	 * 文件打包下载
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/FileConsoleController/insertFileMationToPackageDownload")
	public void insertFileMationToPackageDownload(InputObject inputObject, OutputObject outputObject) throws Exception{
		fileConsoleService.insertFileMationToPackageDownload(inputObject, outputObject);
	}
	
}
