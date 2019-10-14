package com.skyeye.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dao.ErpCommonDao;
import com.skyeye.service.ErpCommonService;

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
	@Override
	public void queryDepotHeadDetailsMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		//获取主表信息
		Map<String, Object> bean = erpCommonDao.queryDepotHeadDetailsMationById(map);
		if(bean != null && !bean.isEmpty()){
			//获取子表信息
			List<Map<String, Object>> norms = erpCommonDao.queryDepotItemDetailsMationById(bean);
			bean.put("items", norms);
			outputObject.setBean(bean);
			outputObject.settotal(1);
		}else{
			outputObject.setreturnMessage("该数据已不存在.");
		}
	}
	
}
