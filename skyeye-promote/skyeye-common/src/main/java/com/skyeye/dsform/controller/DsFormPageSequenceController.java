/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.entity.DsFormPageSequenceApiBox;
import com.skyeye.dsform.service.DsFormPageSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DsFormPageSequenceController
 * @Description: 业务数据关联的表单布局控制类
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/2 15:20
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "业务数据关联的表单布局", tags = "业务数据关联的表单布局", modelName = "动态表单模块")
public class DsFormPageSequenceController {

    @Autowired
    private DsFormPageSequenceService dsFormPageSequenceService;

    /**
     * 保存表单布局的数据信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformpage014", value = "保存表单布局的数据信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DsFormPageSequenceApiBox.class)
    @RequestMapping("/post/DsFormPageSequenceController/saveDsFormData")
    public void saveDsFormData(InputObject inputObject, OutputObject outputObject) {
        dsFormPageSequenceService.saveDsFormData(inputObject, outputObject);
    }

    /**
     * 根据objectId获取用户已经提交的动态表单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformpage015", value = "根据objectId获取用户已经提交的动态表单信息", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "业务数据id", required = "required")})
    @RequestMapping("/post/DsFormPageSequenceController/queryDsFormDataByObjectId")
    public void queryDsFormDataByObjectId(InputObject inputObject, OutputObject outputObject) {
        dsFormPageSequenceService.queryDsFormDataByObjectId(inputObject, outputObject);
    }

}
