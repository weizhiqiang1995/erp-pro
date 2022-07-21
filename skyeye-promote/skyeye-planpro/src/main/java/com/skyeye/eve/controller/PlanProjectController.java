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
import com.skyeye.eve.service.PlanProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PlanProjectController
 * @Description: 业务流程规划管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/2 0:35
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "业务流程规划", tags = "业务流程规划", modelName = "业务流程规划")
public class PlanProjectController {

    @Autowired
    private PlanProjectService planProjectService;

    /**
     * 获取业务流程规划列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "planproject001", value = "获取业务流程规划列表", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "limit", name = "limit", value = "分页参数,每页多少条数据", required = "required,num"),
        @ApiImplicitParam(id = "page", name = "page", value = "分页参数,第几页", required = "required,num"),
        @ApiImplicitParam(id = "projectName", name = "projectName", value = "业务流程规划名称")})
    @RequestMapping("/post/PlanProjectController/queryPlanProjectList")
    public void queryPlanProjectList(InputObject inputObject, OutputObject outputObject) {
        planProjectService.queryPlanProjectList(inputObject, outputObject);
    }

    /**
     * 新增业务流程规划
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectController/insertPlanProjectMation")
    public void insertPlanProjectMation(InputObject inputObject, OutputObject outputObject) {
        planProjectService.insertPlanProjectMation(inputObject, outputObject);
    }

    /**
     * 删除业务流程规划
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectController/deletePlanProjectMationById")
    public void deletePlanProjectMationById(InputObject inputObject, OutputObject outputObject) {
        planProjectService.deletePlanProjectMationById(inputObject, outputObject);
    }

    /**
     * 编辑业务流程规划时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectController/queryPlanProjectMationToEditById")
    public void queryPlanProjectMationToEditById(InputObject inputObject, OutputObject outputObject) {
        planProjectService.queryPlanProjectMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑业务流程规划
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectController/editPlanProjectMationById")
    public void editPlanProjectMationById(InputObject inputObject, OutputObject outputObject) {
        planProjectService.editPlanProjectMationById(inputObject, outputObject);
    }

}
