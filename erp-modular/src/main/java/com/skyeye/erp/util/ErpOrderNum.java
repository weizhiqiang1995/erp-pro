/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.erp.util;

import java.util.HashMap;
import java.util.Map;

import com.skyeye.common.util.SpringUtils;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.ErpCommonDao;
import com.skyeye.jedis.JedisClientService;

/**
 * 获取订单编号
 * @author 卫志强
 *
 */
public class ErpOrderNum {
	
	/**
	 * 根据类型获取订单编号
	 * @param subType
	 * @return
	 * @throws Exception 
	 */
	public String getOrderNumBySubType(String userId, String subType) throws Exception{
		//获取在redis中的key
		String key = ErpConstants.getSysDepotHeadRedisKeyById(userId, subType);
		JedisClientService jedisClient = SpringUtils.getBean(JedisClientService.class);
		if(ToolUtil.isBlank(jedisClient.get(key))){//若缓存中无值
			Map<String, Object> bean = new HashMap<>();
			bean.put("subType", subType);
			bean.put("userId", userId);
			ErpCommonDao erpCommonDao = SpringUtils.getBean(ErpCommonDao.class);
			bean = erpCommonDao.queryOddNumberBySubType(bean);	//从数据库中查询
			if(bean == null || bean.isEmpty()){
				jedisClient.set(key, "1");//将从数据库中查来的内容存到缓存中
			}else{
				//将从数据库中查来的内容存到缓存中
				if("0".equals(bean.get("num").toString())){
					jedisClient.set(key, "1");
				}else{
					jedisClient.set(key, bean.get("num").toString());
				}
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
	
	/**
	 * 根据类型获取财务订单编号
	 * @param subType
	 * @return
	 * @throws Exception 
	 */
	public String getAccountOrderNumBySubType(String userId, String subType) throws Exception{
		//获取在redis中的key
		String key = ErpConstants.getSysAccountHeadRedisKeyById(userId, subType);
		JedisClientService jedisClient = SpringUtils.getBean(JedisClientService.class);
		if(ToolUtil.isBlank(jedisClient.get(key))){//若缓存中无值
			Map<String, Object> bean = new HashMap<>();
			bean.put("type", subType);
			bean.put("userId", userId);
			ErpCommonDao erpCommonDao = SpringUtils.getBean(ErpCommonDao.class);
			bean = erpCommonDao.getAccountOrderNumBySubType(bean);	//从数据库中查询
			if(bean == null || bean.isEmpty()){
				jedisClient.set(key, "1");//将从数据库中查来的内容存到缓存中
			}else{
				//将从数据库中查来的内容存到缓存中
				if("0".equals(bean.get("num").toString())){
					jedisClient.set(key, "1");
				}else{
					jedisClient.set(key, bean.get("num").toString());
				}
			}
		}
		//获取当前最大的值
		String num = jedisClient.get(key);
		//1为int类型，0代表前面要补的字符 10代表字符串长度,d表示参数为整数类型 
		//类型 + 年月日 + num
		String orderNum = ErpConstants.AccountTheadSubType.getClockInName(subType) + Tool.getTimeISDayStr() + String.format("%010d", Integer.parseInt(num));
		//redis缓存+1
		jedisClient.set(key, NumAdd.BigNumAdd(jedisClient.get(key), "1"));
		return orderNum;
	}
	
}
