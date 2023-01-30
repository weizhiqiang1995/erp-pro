/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.operate.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OperateController
 * @Description: 操作管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/29 18:06
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "操作管理", tags = "操作管理", modelName = "系统公共模块")
public class OperateController {

    @Autowired
    private OperateService operateService;

}
