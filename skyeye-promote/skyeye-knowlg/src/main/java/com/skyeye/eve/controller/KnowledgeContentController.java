/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.eve.entity.knowlg.KnowledgeContentVO;
import com.skyeye.eve.entity.knowlg.KnowledgeTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.KnowledgeContentService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: KnowledgeContentController
 * @Description: 知识库管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/21 15:27
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "知识库", tags = "知识库", modelName = "知识库模块")
public class KnowledgeContentController {

    @Autowired
    private KnowledgeContentService knowledgeContentService;

    /**
     * 获取知识库列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent001", value = "获取知识库列表", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "limit", name = "limit", value = "分页参数,每页多少条数据", required = "required,num"),
        @ApiImplicitParam(id = "page", name = "page", value = "分页参数,第几页", required = "required,num"),
        @ApiImplicitParam(id = "title", name = "title", value = "标题"),
        @ApiImplicitParam(id = "state", name = "state", value = "上线状态"),
        @ApiImplicitParam(id = "typeId", name = "typeId", value = "所属类型")})
    @RequestMapping("/post/KnowledgeContentController/queryKnowledgeContentList")
    public void queryKnowledgeContentList(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryKnowledgeContentList(inputObject, outputObject);
    }


    /**
     * 添加知识库
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent002", value = "添加知识库", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = KnowledgeContentVO.class)
    @RequestMapping("/post/KnowledgeContentController/insertKnowledgeContentMation")
    public void insertKnowledgeContentMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.insertKnowledgeContentMation(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的知识库信息用以编辑
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent003", value = "通过id查找对应的知识库信息用以编辑", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库id", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/selectKnowledgeContentById")
    public void selectKnowledgeContentById(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.selectKnowledgeContentById(inputObject, outputObject);
    }

    /**
     * 编辑知识库信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent004", value = "编辑知识库信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = KnowledgeContentVO.class, value = {
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库id", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/editKnowledgeContentById")
    public void editKnowledgeContentById(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.editKnowledgeContentById(inputObject, outputObject);
    }

    /**
     * 删除知识库
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent005", value = "删除知识库", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库id", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/deleteKnowledgeContentById")
    public void deleteKnowledgeContentById(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.deleteKnowledgeContentById(inputObject, outputObject);
    }

    /**
     * 知识库详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent006", value = "知识库详情", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库id", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/queryKnowledgeContentMationById")
    public void queryKnowledgeContentMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryKnowledgeContentMationById(inputObject, outputObject);
    }

    /**
     * 上传文件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent007", value = "上传文件", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "name", name = "name", value = "文件名", required = "required"),
        @ApiImplicitParam(id = "size", name = "size", value = "文件大小", required = "required,num"),
        @ApiImplicitParam(id = "md5", name = "md5", value = "文件唯一标示", required = "required"),
        @ApiImplicitParam(id = "chunk", name = "chunk", value = "分块上传，块下标", required = "required"),
        @ApiImplicitParam(id = "chunkSize", name = "chunkSize", value = "分块上传时，块的大小，用于最后合并", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/insertUploadFileByUserId")
    public void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.insertUploadFileByUserId(inputObject, outputObject);
    }

    /**
     * 上传文件合并块
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent008", value = "上传文件合并块", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "name", name = "name", value = "文件名", required = "required"),
        @ApiImplicitParam(id = "size", name = "size", value = "文件大小", required = "required,num"),
        @ApiImplicitParam(id = "md5", name = "md5", value = "文件唯一标示", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/insertUploadFileChunksByUserId")
    public void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.insertUploadFileChunksByUserId(inputObject, outputObject);
    }

    /**
     * 文件分块上传检测是否上传
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent009", value = "文件分块上传检测是否上传", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "md5", name = "md5", value = "文件唯一标示", required = "required"),
        @ApiImplicitParam(id = "chunk", name = "chunk", value = "分块上传，块下标", required = "required"),
        @ApiImplicitParam(id = "chunkSize", name = "chunkSize", value = "分块上传时，块的大小，用于最后合并", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/queryUploadFileChunksByChunkMd5")
    public void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryUploadFileChunksByChunkMd5(inputObject, outputObject);
    }

    /**
     * 获取待审核的知识库列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent010", value = "获取待审核的知识库列表", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "limit", name = "limit", value = "分页参数,每页多少条数据", required = "required,num"),
        @ApiImplicitParam(id = "page", name = "page", value = "分页参数,第几页", required = "required,num"),
        @ApiImplicitParam(id = "title", name = "title", value = "标题"),
        @ApiImplicitParam(id = "typeId", name = "typeId", value = "所属类型"),
        @ApiImplicitParam(id = "createUser", name = "createUser", value = "提交人"),
        @ApiImplicitParam(id = "startTime", name = "startTime", value = "提交开始时间"),
        @ApiImplicitParam(id = "endTime", name = "endTime", value = "提交结束时间")})
    @RequestMapping("/post/KnowledgeContentController/queryUnCheckedKnowledgeContentList")
    public void queryUnCheckedKnowledgeContentList(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryUnCheckedKnowledgeContentList(inputObject, outputObject);
    }

    /**
     * 获取知识库信息用于回显审核
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent011", value = "获取知识库信息用于回显审核", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库id", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/queryKnowledgeContentByIdToCheck")
    public void queryKnowledgeContentByIdToCheck(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryKnowledgeContentByIdToCheck(inputObject, outputObject);
    }

    /**
     * 审核知识库
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent012", value = "获取知识库信息用于回显审核", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库id", required = "required"),
        @ApiImplicitParam(id = "examineState", name = "examineState", value = "审核状态", required = "required"),
        @ApiImplicitParam(id = "examineNopassReason", name = "examineNopassReason", value = "审核不通过原因")})
    @RequestMapping("/post/KnowledgeContentController/editKnowledgeContentToCheck")
    public void editKnowledgeContentToCheck(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.editKnowledgeContentToCheck(inputObject, outputObject);
    }

    /**
     * 获取已经审核的知识库列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent013", value = "获取已经审核的知识库列表", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "limit", name = "limit", value = "分页参数,每页多少条数据", required = "required,num"),
        @ApiImplicitParam(id = "page", name = "page", value = "分页参数,第几页", required = "required,num"),
        @ApiImplicitParam(id = "title", name = "title", value = "标题"),
        @ApiImplicitParam(id = "state", name = "state", value = "上线状态"),
        @ApiImplicitParam(id = "typeId", name = "typeId", value = "所属类型"),
        @ApiImplicitParam(id = "createUser", name = "createUser", value = "提交人"),
        @ApiImplicitParam(id = "startTime", name = "startTime", value = "提交开始时间"),
        @ApiImplicitParam(id = "endTime", name = "endTime", value = "提交结束时间"),
        @ApiImplicitParam(id = "examineStartTime", name = "examineStartTime", value = "审核开始时间"),
        @ApiImplicitParam(id = "examineEndTime", name = "examineEndTime", value = "审核结束时间")})
    @RequestMapping("/post/KnowledgeContentController/queryCheckedKnowledgeContentList")
    public void queryCheckedKnowledgeContentList(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryCheckedKnowledgeContentList(inputObject, outputObject);
    }

    /**
     * 未审核知识库详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent014", value = "未审核知识库详情", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库id", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/queryUncheckedKnowledgeContent")
    public void queryUncheckedKnowledgeContent(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryUncheckedKnowledgeContent(inputObject, outputObject);
    }

    /**
     * 已审核的知识库详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent015", value = "已审核的知识库详情", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库id", required = "required")})
    @RequestMapping("/post/KnowledgeContentController/queryCheckedKnowledgeContent")
    public void queryCheckedKnowledgeContent(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryCheckedKnowledgeContent(inputObject, outputObject);
    }

    /**
     * 获取企业知识库列表(已审核通过)
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "knowledgecontent016", value = "获取企业知识库列表(已审核通过)", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "limit", name = "limit", value = "分页参数,每页多少条数据", required = "required,num"),
        @ApiImplicitParam(id = "page", name = "page", value = "分页参数,第几页", required = "required,num"),
        @ApiImplicitParam(id = "title", name = "title", value = "标题"),
        @ApiImplicitParam(id = "label", name = "label", value = "标签"),
        @ApiImplicitParam(id = "typeId", name = "typeId", value = "所属类型")})
    @RequestMapping("/post/KnowledgeContentController/queryAllPassKnowledgeContentList")
    public void queryAllPassKnowledgeContentList(InputObject inputObject, OutputObject outputObject) throws Exception {
        knowledgeContentService.queryAllPassKnowledgeContentList(inputObject, outputObject);
    }

}
