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
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.OtherWareHousDao;
import com.skyeye.erp.util.ErpConstants;
import com.skyeye.erp.util.NumAdd;
import com.skyeye.erp.util.Tool;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.service.OtherWareHousService;

import net.sf.json.JSONArray;

@Service
public class OtherWareHousServiceImpl implements OtherWareHousService{
	
	@Autowired
	private OtherWareHousDao otherWareHousDao;
	
	@Autowired
	private JedisClientService jedisClient;

	/**
     * 获取其他入库列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryOtherWareHousToList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = otherWareHousDao.queryOtherWareHousToList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
	}

	/**
     * 新增其他入库信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value="transactionManager")
	public void insertOtherWareHousMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		String depotheadStr = map.get("depotheadStr").toString();
		if(ToolUtil.isJson(depotheadStr)){
			String useId = ToolUtil.getSurFaceId();//单据主表id
			String userId = inputObject.getLogParams().get("id").toString();
			//处理数据
			JSONArray jArray = JSONArray.fromObject(depotheadStr);
			//产品中间转换对象，单据子表存储对象
			Map<String, Object> bean, entity;
			List<Map<String, Object>> entitys = new ArrayList<>();//单据子表实体集合信息
			BigDecimal allPrice = new BigDecimal("0");//主单总价
			BigDecimal itemAllPrice = null;//子单对象
			for(int i = 0; i < jArray.size(); i++){
				bean = jArray.getJSONObject(i);
				entity = otherWareHousDao.queryMaterialsById(bean);
				if(entity != null && !entity.isEmpty()){
					//获取单价
					itemAllPrice = new BigDecimal(entity.get("estimatePurchasePrice").toString());
					entity.put("id", ToolUtil.getSurFaceId());
					entity.put("headerId", useId);//单据主表id
					entity.put("operNumber", bean.get("rkNum"));//数量
					//计算子单总价：单价*数量
					itemAllPrice = itemAllPrice.multiply(new BigDecimal(bean.get("rkNum").toString()));
					entity.put("allPrice", itemAllPrice);//单据子表总价
					entity.put("remark", bean.get("remark"));//备注
					entity.put("depotId", bean.get("depotId"));//仓库
					entity.put("mType", 0);//商品类型  0.普通  1.组合件  2.普通子件
					entity.put("userId", userId);
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
			depothead.put("type", 2);//类型(1.出库/2.入库)
			depothead.put("subType", ErpConstants.DepoTheadSubType.PUT_IS_OTHERS.getNum());//其他入库
			String orderNum = getOrderNumBySubType(userId, ErpConstants.DepoTheadSubType.PUT_IS_OTHERS.getNum());
			depothead.put("defaultNumber", orderNum);//初始票据号
			depothead.put("number", orderNum);//票据号
			depothead.put("operPersonId", userId);//操作员id
			depothead.put("operPersonName", inputObject.getLogParams().get("userName"));//操作员名字
			depothead.put("createTime", ToolUtil.getTimeAndToString());//创建时间
			depothead.put("operTime", map.get("operTime"));//出入库时间即单据日期
			depothead.put("organId", map.get("supplierId"));//供应商Id
			depothead.put("accountId", map.get("accountId"));//账户Id
			depothead.put("remark", map.get("remark"));//备注
			depothead.put("payType", map.get("payType"));//付款类型
			depothead.put("totalPrice", allPrice);//合计金额
			depothead.put("status", "1");//状态，0未审核、1已审核、2已转采购|销售
			depothead.put("userId", userId);
			depothead.put("deleteFlag", 0);//删除标记，0未删除，1删除
			otherWareHousDao.insertOtherWareHousMation(depothead);
			otherWareHousDao.insertOtherWareHousChildMation(entitys);
		}else{
			outputObject.setreturnMessage("数据格式错误");
		}
	}
	
	/**
	 * 根据类型获取订单编号
	 * @param subType
	 * @return
	 * @throws Exception 
	 */
	public String getOrderNumBySubType(String userId, String subType) throws Exception{
		//获取在redis中的key
		String key = ErpConstants.getSysDepotHeadRedisKeyById(userId, subType);
		if(ToolUtil.isBlank(jedisClient.get(key))){//若缓存中无值
			Map<String, Object> bean = new HashMap<>();
			bean.put("subType", subType);
			bean.put("userId", userId);
			bean = otherWareHousDao.queryOddNumberBySubType(bean);	//从数据库中查询
			if(bean == null || bean.isEmpty()){
				jedisClient.set(key, "1");//将从数据库中查来的内容存到缓存中
			}else{
				jedisClient.set(key, bean.get("num").toString());//将从数据库中查来的内容存到缓存中
			}
		}
		//获取当前最大的值
		String num = jedisClient.get(key);
		//1为int类型，0代表前面要补的字符 10代表字符串长度,d表示参数为整数类型 
		//类型 + 年月日 + num
		String orderNum = ErpConstants.DepoTheadSubType.getClockInName(subType) + Tool.getTimeISDayStr() + String.format("%010d", Integer.parseInt(num));
		//redis缓存+1
		jedisClient.set(key, NumAdd.BigNumAdd(jedisClient.get(key), "1"));
		return orderNum;
	}
	
}
