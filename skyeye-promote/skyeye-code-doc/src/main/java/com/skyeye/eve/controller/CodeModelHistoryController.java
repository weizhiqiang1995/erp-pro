/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.codedoc.history.CodeModelHistoryQueryDo;
import com.skyeye.eve.service.CodeModelHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CodeModelHistoryController
 * @Description: 代码生成历史管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/6 10:03
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "代码生成历史", tags = "代码生成历史", modelName = "代码生成器")
public class CodeModelHistoryController {

    @Autowired
    private CodeModelHistoryService codeModelHistoryService;

    /**
     * 获取模板生成历史列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "codemodel015", value = "获取模板生成历史列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CodeModelHistoryQueryDo.class)
    @RequestMapping("/post/CodeModelHistoryController/queryCodeModelHistoryList")
    public void queryCodeModelHistoryList(InputObject inputObject, OutputObject outputObject) {
        codeModelHistoryService.queryCodeModelHistoryList(inputObject, outputObject);
    }

    /**
     * 重新生成文件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelHistoryController/insertCodeModelHistoryCreate")
    public void insertCodeModelHistoryCreate(InputObject inputObject, OutputObject outputObject) {
        codeModelHistoryService.insertCodeModelHistoryCreate(inputObject, outputObject);
    }

    /**
     * 下载文件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelHistoryController/downloadCodeModelHistory")
    public void downloadCodeModelHistory(InputObject inputObject, OutputObject outputObject) {
        codeModelHistoryService.downloadCodeModelHistory(inputObject, outputObject);
    }

}
