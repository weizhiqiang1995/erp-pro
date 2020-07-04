/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.erp.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常捕获
 * @author Lenovo
 *
 */
@RestControllerAdvice
public class CustomExtHanlder {
	
	public static final Logger log = LoggerFactory.getLogger(CustomExtHanlder.class);
	
	/**
	 * 捕获全局异常进行统一处理
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value=Exception.class)
	public Object handleException(Exception e, HttpServletRequest request){
		StackTraceElement stackTraceElement= e.getStackTrace()[0];// 得到异常棧的首个元素
		log.error("url {}, msg {}, method {}, line {}", request.getRequestURI(), e.getMessage(), stackTraceElement.getClassName(), stackTraceElement.getLineNumber());
		e.printStackTrace();
		Map<String, Object> map = new HashMap<>();
		map.put("returnCode", -9999);
		map.put("returnMessage", e.getMessage());
		return map;
	}
	
}
