/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.document.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.document.entity.Document;
import com.skyeye.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DocumentController
 * @Description: 文档管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/7/24 8:18
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "文档管理", tags = "文档管理", modelName = "基本服务")
public class DocumentController {
    
    @Autowired
    private DocumentService documentService;

    /**
     * 获取文档列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDocumentList", value = "获取文档列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/DocumentController/queryDocumentList")
    public void queryDocumentList(InputObject inputObject, OutputObject outputObject) {
        documentService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑文档信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDocumentMation", value = "新增/编辑文档信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Document.class)
    @RequestMapping("/post/DocumentController/writeDocumentMation")
    public void writeDocumentMation(InputObject inputObject, OutputObject outputObject) {
        documentService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除文档信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDocumentMationById", value = "根据id删除文档信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DocumentController/deleteDocumentMationById")
    public void deleteDocumentMationById(InputObject inputObject, OutputObject outputObject) {
        documentService.deleteById(inputObject, outputObject);
    }

}
