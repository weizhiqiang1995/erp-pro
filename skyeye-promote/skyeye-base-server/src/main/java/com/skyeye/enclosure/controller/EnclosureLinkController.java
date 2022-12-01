/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.enclosure.entity.EnclosureLinkApi;
import com.skyeye.enclosure.service.EnclosureLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EnclosureLinkController
 * @Description: 附件信息与业务对象关系的控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:00
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "附件与业务对象关系管理", tags = "附件与业务对象关系管理", modelName = "基本服务")
public class EnclosureLinkController {

    @Autowired
    private EnclosureLinkService enclosureLinkService;

    /**
     * 新增/编辑附件与业务对象关系
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeEnclosureLink", value = "新增/编辑附件与业务对象关系", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = EnclosureLinkApi.class)
    @RequestMapping("/post/EnclosureLinkController/writeEnclosureLink")
    public void writeEnclosureLink(InputObject inputObject, OutputObject outputObject) {
        enclosureLinkService.writeEnclosureLink(inputObject, outputObject);
    }

    /**
     * 根据业务对象数据获取附件与业务对象关系
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnclosureLinkList", value = "根据业务对象数据获取附件与业务对象关系", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "业务对象数据的id", required = "required"),
        @ApiImplicitParam(id = "objectKey", name = "objectKey", value = "业务对象服务的className", required = "required")})
    @RequestMapping("/post/EnclosureLinkController/queryEnclosureLinkList")
    public void queryEnclosureLinkList(InputObject inputObject, OutputObject outputObject) {
        enclosureLinkService.queryEnclosureLinkList(inputObject, outputObject);
    }

    /**
     * 根据业务对象数据删除附件与业务对象关系
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteEnclosureLink", value = "根据业务对象数据删除附件与业务对象关系", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "业务对象数据的id", required = "required"),
        @ApiImplicitParam(id = "objectKey", name = "objectKey", value = "业务对象服务的className", required = "required")})
    @RequestMapping("/post/EnclosureLinkController/deleteEnclosureLink")
    public void deleteEnclosureLink(InputObject inputObject, OutputObject outputObject) {
        enclosureLinkService.deleteEnclosureLink(inputObject, outputObject);
    }

}
