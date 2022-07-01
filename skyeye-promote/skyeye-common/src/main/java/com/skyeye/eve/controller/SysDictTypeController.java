/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.eitity.dict.SysDictTypeMation;
import com.skyeye.eve.eitity.dict.SysDictTypeQueryDO;
import com.skyeye.eve.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysDictTypeController
 * @Description: 数据字典类型管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:26
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "数据字典类型管理", tags = "数据字典类型管理", modelName = "系统公共模块")
public class SysDictTypeController {

    @Autowired
    private SysDictTypeService sysDictTypeService;

    /**
     * 获取数据字典类型列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "queryDictTypeList", value = "获取数据字典类型列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = SysDictTypeQueryDO.class)
    @RequestMapping("/post/SysDictTypeController/queryDictTypeList")
    public void queryDictTypeList(InputObject inputObject, OutputObject outputObject) {
        sysDictTypeService.queryDictTypeList(inputObject, outputObject);
    }

    /**
     * 新增/编辑数据字典类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "writeDictTypeMation", value = "新增/编辑数据字典类型", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = SysDictTypeMation.class)
    @RequestMapping("/post/SysDictTypeController/writeDictTypeMation")
    public void writeDictTypeMation(InputObject inputObject, OutputObject outputObject) {
        sysDictTypeService.writeDictTypeMation(inputObject, outputObject);
    }

}
