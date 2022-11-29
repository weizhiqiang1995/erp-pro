/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.catalog.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.catalog.service.CatalogService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.object.query.BaseServerQueryDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CatalogController
 * @Description: 目录管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:58
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "目录管理", tags = "目录管理", modelName = "基本服务")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    /**
     * 一次性获取所有的目录为树结构
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCatalogForTree", value = "一次性获取所有的目录为树结构", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = BaseServerQueryDo.class)
    @RequestMapping("/post/CatalogController/queryCatalogForTree")
    public void queryCatalogForTree(InputObject inputObject, OutputObject outputObject) {
        catalogService.queryCatalogForTree(inputObject, outputObject);
    }

}
