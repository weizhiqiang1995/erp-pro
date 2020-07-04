/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
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
    	OUT_IS_RETAIL("零售出库", "LSCK", "8"),
    	OUT_IS_OTHERS("其他出库", "QTCK", "9"),
		//采购单
    	PURCHASE_ORDER("采购订单", "CGDD", "10"),
		//销售单
		OUTCHASE_ORDER("销售订单", "XSDD", "11"),
		//拆分单
		SPLIT_LIST_ORDER("拆分订单", "CFDD", "12"),
		//组装单
		ASSEMBLY_SHEET_ORDER("组装订单", "ZZDD", "13"),
		//调拨单
		ALLOCATION_FORM_ORDER("调拨订单", "DBDD", "14");
		
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
	
	//财务单据主表类型
	public static enum AccountTheadSubType {
		EXPENDITURE_ORDER("支出订单", "ZCDD", "1"),
		INCOME_ORDER("收入订单", "SRDD", "2"),
		RECEIVABLES_ORDER("收款订单", "SKDD", "3"),
		PAYMENT_ORDER("付款订单", "FKDD", "4"),
		TRANSFER_ORDER("转账订单", "ZZDD", "5"),
		ADVANCE_ORDER("收预付款", "YFDD", "6");
		
        private String nameCode;
        private String code;
        private String num;
		
        AccountTheadSubType(String nameCode, String code, String num){
            this.nameCode = nameCode;
            this.code = code;
            this.num = num;
        }
	
        public static String getClockInName(String num){
            for (AccountTheadSubType q : AccountTheadSubType.values()){
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
	
	//单据编号在redis中的key
	public static final String SYS_DEPOTHEAD_REDIS_KEY = "sys_depothead_redis_key";
	public static String getSysDepotHeadRedisKeyById(String userId, String subType){
		return SYS_DEPOTHEAD_REDIS_KEY + userId + "_" + subType;
	}
	
	//财务单据编号在redis中的key
	public static final String SYS_ACCOUNTHEAD_REDIS_KEY = "sys_accounthead_redis_key";
	public static String getSysAccountHeadRedisKeyById(String userId, String subType){
		return SYS_ACCOUNTHEAD_REDIS_KEY + userId + "_" + subType;
	}
	
}
