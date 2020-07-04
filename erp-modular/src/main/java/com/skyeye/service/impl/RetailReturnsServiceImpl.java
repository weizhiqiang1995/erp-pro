/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ExcelUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.RetailReturnsDao;
import com.skyeye.erp.util.ErpConstants;
import com.skyeye.erp.util.ErpOrderNum;
import com.skyeye.service.RetailReturnsService;

import net.sf.json.JSONArray;

@Service
public class RetailReturnsServiceImpl implements RetailReturnsService{
	
	@Autowired
	private RetailReturnsDao retailReturnsDao;

	/**
     * 获取零售退货列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryRetailReturnsToList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        List<Map<String, Object>> beans = retailReturnsDao.queryRetailReturnsToList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
	}

	/**
     * 新增零售退货信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value="transactionManager")
	public void insertRetailReturnsMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		String depotheadStr = map.get("depotheadStr").toString();//零售产品列表
		if(ToolUtil.isJson(depotheadStr)){
			String useId = ToolUtil.getSurFaceId();//单据主表id
			String tenantId = inputObject.getLogParams().get("tenantId").toString();
			//处理数据
			JSONArray jArray = JSONArray.fromObject(depotheadStr);
			//产品中间转换对象，单据子表存储对象
			Map<String, Object> bean, entity;
			List<Map<String, Object>> entitys = new ArrayList<>();//单据子表实体集合信息
			BigDecimal allPrice = new BigDecimal("0");//主单总价
			BigDecimal itemAllPrice = null;//子单对象
			for(int i = 0; i < jArray.size(); i++){
				bean = jArray.getJSONObject(i);
				entity = retailReturnsDao.queryMaterialsById(bean);
				if(entity != null && !entity.isEmpty()){
					//获取单价
					itemAllPrice = new BigDecimal(bean.get("estimateSalePrice").toString());
					entity.put("id", ToolUtil.getSurFaceId());
					entity.put("headerId", useId);//单据主表id
					entity.put("operNumber", bean.get("rkNum"));//数量
					//计算子单总价：单价*数量
					itemAllPrice = itemAllPrice.multiply(new BigDecimal(bean.get("rkNum").toString()));
					entity.put("allPrice", itemAllPrice);//单据子表总价
					entity.put("estimateSalePrice", bean.get("estimateSalePrice"));//单价
					entity.put("remark", bean.get("remark"));//备注
					entity.put("depotId", bean.get("depotId"));//仓库
					entity.put("mType", 0);//商品类型  0.普通  1.组合件  2.普通子件
					entity.put("tenantId", tenantId);
					entity.put("deleteFlag", 0);//删除标记，0未删除，1删除
					entitys.add(entity);
					//计算主单总价
					allPrice = allPrice.add(itemAllPrice);
				}
			}
			if(entitys.size() == 0){
				outputObject.setreturnMessage("请选择产品");
				return;
			}
			//单据主表对象
			Map<String, Object> depothead = new HashMap<>();
			depothead.put("id", useId);
			depothead.put("type", 2);//类型(1.出库/2.入库3.其他)
			depothead.put("subType", ErpConstants.DepoTheadSubType.PUT_IS_RETAIL_RETURNS.getNum());//零售退货
			ErpOrderNum erpOrderNum = new ErpOrderNum();
			String orderNum = erpOrderNum.getOrderNumBySubType(tenantId, ErpConstants.DepoTheadSubType.PUT_IS_RETAIL_RETURNS.getNum());
			depothead.put("defaultNumber", orderNum);//初始票据号
			depothead.put("number", orderNum);//票据号
			depothead.put("operPersonId", inputObject.getLogParams().get("id"));//操作员id
			depothead.put("operPersonName", inputObject.getLogParams().get("userName"));//操作员名字
			depothead.put("createTime", ToolUtil.getTimeAndToString());//创建时间
			depothead.put("operTime", map.get("operTime"));//零售退货时间即单据日期
			depothead.put("organId", map.get("supplierId"));//会员Id
			depothead.put("accountId", map.get("accountId"));//账户Id
			depothead.put("remark", map.get("remark"));//备注
			depothead.put("payType", map.get("payType"));//付款类型
			depothead.put("changeAmount", map.get("changeAmount"));//本次付款金额
			depothead.put("totalPrice", allPrice);//合计金额
			depothead.put("giveChange", map.get("giveChange"));//找零金额
			depothead.put("status", "2");//状态，0未审核、1.审核中、2.审核通过、3.审核拒绝、4.已转采购|销售
			depothead.put("tenantId", tenantId);
			depothead.put("deleteFlag", 0);//删除标记，0未删除，1删除
			retailReturnsDao.insertRetailReturnsMation(depothead);
			retailReturnsDao.insertRetailReturnsChildMation(entitys);
		}else{
			outputObject.setreturnMessage("数据格式错误");
		}
	}

	/**
     * 编辑零售退货信息时进行回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryRetailReturnsMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("tenantId", inputObject.getLogParams().get("tenantId"));
		//获取主表信息
		Map<String, Object> bean = retailReturnsDao.queryRetailReturnsMationToEditById(map);
		if(bean != null && !bean.isEmpty()){
			//获取子表信息
			List<Map<String, Object>> norms = retailReturnsDao.queryRetailReturnsItemMationToEditById(bean);
			bean.put("items", norms);
			outputObject.setBean(bean);
			outputObject.settotal(1);
		}else{
			outputObject.setreturnMessage("该数据已不存在.");
		}
	}

	/**
     * 编辑零售退货信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value="transactionManager")
	public void editRetailReturnsMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		String depotheadStr = map.get("depotheadStr").toString();//零售产品列表
		if(ToolUtil.isJson(depotheadStr)){
			String useId = map.get("id").toString();//单据主表id
			String tenantId = inputObject.getLogParams().get("tenantId").toString();
			//处理数据
			JSONArray jArray = JSONArray.fromObject(depotheadStr);
			//产品中间转换对象，单据子表存储对象
			Map<String, Object> bean, entity;
			List<Map<String, Object>> entitys = new ArrayList<>();//单据子表实体集合信息
			BigDecimal allPrice = new BigDecimal("0");//主单总价
			BigDecimal itemAllPrice = null;//子单对象
			for(int i = 0; i < jArray.size(); i++){
				bean = jArray.getJSONObject(i);
				entity = retailReturnsDao.queryMaterialsById(bean);
				if(entity != null && !entity.isEmpty()){
					//获取单价
					itemAllPrice = new BigDecimal(bean.get("estimateSalePrice").toString());
					entity.put("id", ToolUtil.getSurFaceId());
					entity.put("headerId", useId);//单据主表id
					entity.put("operNumber", bean.get("rkNum"));//数量
					//计算子单总价：单价*数量
					itemAllPrice = itemAllPrice.multiply(new BigDecimal(bean.get("rkNum").toString()));
					entity.put("allPrice", itemAllPrice);//单据子表总价
					entity.put("estimateSalePrice", bean.get("estimateSalePrice"));//单价
					entity.put("remark", bean.get("remark"));//备注
					entity.put("depotId", bean.get("depotId"));//仓库
					entity.put("mType", 0);//商品类型  0.普通  1.组合件  2.普通子件
					entity.put("tenantId", tenantId);
					entity.put("deleteFlag", 0);//删除标记，0未删除，1删除
					entitys.add(entity);
					//计算主单总价
					allPrice = allPrice.add(itemAllPrice);
				}
			}
			if(entitys.size() == 0){
				outputObject.setreturnMessage("请选择产品");
				return;
			}
			//单据主表对象
			Map<String, Object> depothead = new HashMap<>();
			depothead.put("id", useId);
			depothead.put("operTime", map.get("operTime"));//零售退货时间即单据日期
			depothead.put("organId", map.get("supplierId"));//会员Id
			depothead.put("accountId", map.get("accountId"));//账户Id
			depothead.put("remark", map.get("remark"));//备注
			depothead.put("payType", map.get("payType"));//付款类型
			depothead.put("changeAmount", map.get("changeAmount"));//本次付款金额
			depothead.put("totalPrice", allPrice);//合计金额
			depothead.put("giveChange", map.get("giveChange"));//找零金额
			depothead.put("tenantId", tenantId);
			//删除之前绑定的产品
			retailReturnsDao.deleteRetailReturnsChildMation(map);
			//重新添加
			retailReturnsDao.editRetailReturnsMationById(depothead);
			retailReturnsDao.insertRetailReturnsChildMation(entitys);
		}else{
			outputObject.setreturnMessage("数据格式错误");
		}
	}

	/**
     * 导出Excel
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("static-access")
	@Override
	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        List<Map<String, Object>> beans = retailReturnsDao.queryMationToExcel(params);
        String[] key = new String[]{"defaultNumber", "supplierName", "materialNames", "totalPrice", "changeAmount", "operPersonName", "operTime"};
        String[] column = new String[]{"单据编号", "会员", "关联产品", "合计金额", "退款", "操作人", "单据日期"};
        String[] dataType = new String[]{"", "data", "data", "data", "data", "data", "data"};
        //零售退货单信息导出
        ExcelUtil.createWorkBook("零售退货单", "零售退货单详细", beans, key, column, dataType, inputObject.getResponse());
	}
	
}
