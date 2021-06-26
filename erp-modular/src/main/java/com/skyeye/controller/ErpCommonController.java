/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */

package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.ErpCommonService;

/**
 *
 * @ClassName: ErpCommonController
 * @Description: ERP公共模块管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/26 9:49
 *
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Controller
public class ErpCommonController {
	
	@Autowired
	private ErpCommonService erpCommonService;
	
	/**
     * 获取单据详情信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ErpCommonController/queryDepotHeadDetailsMationById")
    @ResponseBody
    public void queryDepotHeadDetailsMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	erpCommonService.queryDepotHeadDetailsMationById(inputObject, outputObject);
    }
    
    /**
     * 删除单据信息（不包括采购单和销售单）
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ErpCommonController/deleteDepotHeadDetailsMationById")
    @ResponseBody
    public void deleteDepotHeadDetailsMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	erpCommonService.deleteDepotHeadDetailsMationById(inputObject, outputObject);
    }
    
}
