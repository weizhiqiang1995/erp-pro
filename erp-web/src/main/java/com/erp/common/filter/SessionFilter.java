package com.erp.common.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skyeye.common.constans.Constants;
import com.skyeye.common.util.SpringUtils;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.jedis.impl.JedisClientServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
    * @ClassName: SessionFilter
    * @Description: 系统过滤器
    * @author 卫志强
    * @date 2018年6月7日
    *
 */
public class SessionFilter implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	/**
	 * 过滤器
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		servletResponse.setCharacterEncoding("UTF-8");  
		servletResponse.setContentType("text/html;charset=UTF-8");
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		//获取请求路径
		String url = servletRequest.getContextPath() + servletRequest.getServletPath();
		
		//1.文件目录过滤
		for(String str : Constants.FILTER_FILE_CATALOG_OPTION){
			if (url.indexOf(str) != -1) {
				chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
				return;
			}
		}
		
		//2.文件后缀过滤
		for(String str : Constants.FILTER_FILE_SUFFIX_OPTION){
			if (url.contains(str)) {
				chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
				return;
			}
		}
		
		//3.转换请求过滤
		if(request.getParameter("sessionKey") != null){
			for(String str : Constants.FILTER_FILE_REQUEST_OPTION){
				if (url.contains(str)) {
					chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
					return;
				}
			}
		}else{
			for(String str : Constants.FILTER_FILE_NO_SESSION_REQUEST_OPTION){
				if (url.contains(str)) {
					chain.doFilter(request, response);
					return;
				}else if(url.indexOf(str) != -1){
					chain.doFilter(request, response);
					return;
				}
			}
		}
		
		//4.请求过滤
		//如果不是设定的请求类型，则根据mapping.xml配置信息转化为请求信息
		if(ToolUtil.isBlank(url.replaceAll("/", ""))){
			servletResponse.sendRedirect(Constants.LOGIN_PAGE);
		}else{
			if(Constants.REQUEST_MAPPING == null){
				servletResponse.sendRedirect(Constants.LOGIN_PAGE);
			}else{
				if(Constants.REQUEST_MAPPING.containsKey(url.replaceAll("/", ""))){
					String key = url.replaceAll("/", "");
					String allUse = Constants.REQUEST_MAPPING.get(key).get("allUse").toString();
					if("1".equals(allUse) || "2".equals(allUse)){//是否需要登录才能使用   1是   0否  2需要登陆才能访问，但无需授权    默认为否
						JedisClientServiceImpl jedisClient = SpringUtils.getBean(JedisClientServiceImpl.class);
						if(ToolUtil.isBlank(request.getParameter("userToken")) 
								|| !jedisClient.exists("userMation:" + request.getParameter("userToken").toString())){
							servletResponse.setHeader("SESSIONSTATUS", "TIMEOUT");
							return;
						}else{
							//重置redis时间
							Map<String, Object> userMation = JSONObject.fromObject(jedisClient.get("userMation:" + request.getParameter("userToken").toString()));//用户信息
							List<Map<String, Object>> authPoints = JSONArray.fromObject(jedisClient.get("authPointsMation:" + request.getParameter("userToken").toString()));//所有权限信息
							if(authPoints(authPoints, key) || "2".equals(allUse)){//权限校验通过
								
								url = Constants.REQUEST_MAPPING.get(key).get("path").toString();
								
								//设置日志
								MDC.put("userName", userMation.get("userCode"));
								MDC.put("realPath", url);
								MDC.put("ip", ToolUtil.getIpByRequest(servletRequest));
								LOGGER.info(Constants.REQUEST_MAPPING.get(key).get("val").toString());
								request.getRequestDispatcher(url + "?sessionKey=" + key + "&allUse=" + allUse).forward(request, response);
							}else{
								System.out.println(key + "被强行访问.");
								servletResponse.setHeader("SESSIONSTATUS", "NOAUTHPOINT");
								return;
							}
						}
					}else{
						url = Constants.REQUEST_MAPPING.get(key).get("path").toString();
						request.getRequestDispatcher(url + "?sessionKey=" + key + "&allUse=" + allUse).forward(request, response);
					}
				}else{
					servletResponse.sendRedirect(Constants.LOGIN_PAGE);
				}
			}
		}
		return;
	}
	
	/**
	 * 权限点校验
	 * @param authPoints
	 * @param key
	 * @return
	 */
	public boolean authPoints(List<Map<String, Object>> authPoints, String key){
		for(Map<String, Object> bean : authPoints){
			if(key.equals(bean.get("menuUrl").toString())){
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
		System.out.println("结束");
	}
	
	
}
