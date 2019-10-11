package com.skyeye.erp.util;

public class ErpConstants {
	
	//产品类型在redis中的key
	public static final String SYS_MATERIAL_CATEGORY_REDIS_KEY = "sys_material_category_redis_key_";
	public static String getSysMaterialCategoryRedisKeyById(String userId, String id){
		return SYS_MATERIAL_CATEGORY_REDIS_KEY + userId + "_" + id;
	}
	
	//仓库在redis中存储的key
	public static final String STORE_HOUSE_REDIS_KEY = "store_house_redis_key_";
	public static String getStoreHouseRedisKeyByUserId(String userId){
		return STORE_HOUSE_REDIS_KEY + userId;
	}
	
}
