/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.erp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {

	/**
	 * 获取年月日字符串
	 * @return
	 */
	public static String getTimeISDayStr() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowTime = df.format(dt);
		return nowTime;
	}
	
}
