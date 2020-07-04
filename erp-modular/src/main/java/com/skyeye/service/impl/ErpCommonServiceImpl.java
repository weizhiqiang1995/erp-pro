/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.ErpCommonDao;
import com.skyeye.service.ErpCommonService;

import net.sf.json.JSONArray;

@Service
public class ErpCommonServiceImpl implements ErpCommonService{
	
	@Autowired
	private ErpCommonDao erpCommonDao;
	
	/**
     * 获取单据详情信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	public void queryDepotHeadDetailsMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("tenantId", inputObject.getLogParams().get("tenantId"));
		//获取主表信息
		Map<String, Object> bean = erpCommonDao.queryDepotHeadDetailsMationById(map);
		if(bean != null && !bean.isEmpty()){
			//获取子表信息
			List<Map<String, Object>> norms = erpCommonDao.queryDepotItemDetailsMationById(bean);
			bean.put("items", norms);
			//获取采购项目列表
			if(bean.containsKey("otherMoneyList") && !ToolUtil.isBlank(bean.get("otherMoneyList").toString()) && ToolUtil.isJson(bean.get("otherMoneyList").toString())){
				JSONArray jArray = JSONArray.fromObject(bean.get("otherMoneyList").toString());
				List<Map<String, Object>> otherMoneyList = new ArrayList<>();
				Map<String, Object> item;//中间转换对象
				for(int i = 0; i < jArray.size(); i++){
					item = jArray.getJSONObject(i);
					item = erpCommonDao.queryInoutitemMationById(item);
					otherMoneyList.add(item);
				}
				bean.put("otherMoneyList", otherMoneyList);
			}else {
				bean.put("otherMoneyList", new JSONArray());
			}
			outputObject.setBean(bean);
			outputObject.settotal(1);
		}else{
			outputObject.setreturnMessage("该数据已不存在.");
		}
	}

	/**
     * 删除单据信息（不包括采购单和销售单）
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void deleteDepotHeadDetailsMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("tenantId", inputObject.getLogParams().get("tenantId"));
		//删除删除单据信息
		int count = erpCommonDao.deleteDepotHeadDetailsMationById(map);
		if(count > 0){
			//删除删除单据关联产品信息
			erpCommonDao.deleteDepotHeadDetailsNormsMationById(map);
		}
	}

}
