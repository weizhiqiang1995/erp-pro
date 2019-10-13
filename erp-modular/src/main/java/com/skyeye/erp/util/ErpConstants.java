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
	
	//单据主表类型
	public static enum DepoTheadSubType {
		//入库
    	PUT_IS_PURCHASE("采购入库", "CGRK", "1"),
    	PUT_IS_SALES_RETURNS("销售退货", "XSTH", "2"),
    	PUT_IS_RETAIL_RETURNS("零售退货", "LSTH", "3"),
    	PUT_IS_OTHERS("其他入库", "QTRK", "4"),
    	//出库
    	OUT_IS_SALES_OUTLET("销售出库", "XSCK", "5"),
    	OUT_IS_PURCHASE_RETURNS("采购退货", "CGTH", "6"),
    	OUT_IS_ALLOCATION("调拨", "DBCK", "7"),
    	OUT_IS_RETAIL("零售", "LSCK", "8"),
    	OUT_IS_OTHERS("其他出库", "QTCK", "9");
		
        private String nameCode;
        private String code;
        private String num;
		
        DepoTheadSubType(String nameCode, String code, String num){
            this.nameCode = nameCode;
            this.code = code;
            this.num = num;
        }
	
        public static String getClockInName(String num){
            for (DepoTheadSubType q : DepoTheadSubType.values()){
                if(q.getNum().equals(num)){
                    return q.getCode();
                }
            }
            return "";
        }
        
        public String getNameCode() {
			return nameCode;
		}

		public void setNameCode(String nameCode) {
			this.nameCode = nameCode;
		}

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		
    }
	
}
