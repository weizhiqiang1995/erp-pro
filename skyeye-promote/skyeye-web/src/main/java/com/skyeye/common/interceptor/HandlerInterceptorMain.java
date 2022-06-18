/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/
package com.skyeye.common.interceptor;

import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.object.ObjectConstant;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.exception.CustomException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 卫志强
 * @ClassName: HandlerInterceptorMain
 * @Description: 后台拦截器
 * @date 2018年6月8日
 */
@Component
public class HandlerInterceptorMain implements HandlerInterceptor {

    // 在进入Handler方法之前执行
    // 用于身份认证、身份授权、
    // 比如身份认证，IP黑名单过滤，如果认证不通过表示当前用户没有登录，需要此方法拦截不再向下执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        // 请求类型
        String sessionKey = request.getParameter("sessionKey");
        String method = request.getMethod();
        // 如果请求为OPTIONS请求，让 OPTIONS 请求返回一个200状态码，表示可以正常访问，然后就会收到真正的GET/POST请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        }
        Map<String, Object> apiMation = RedisConstants.getApiMationByUrlId(sessionKey);
        if (!method.toUpperCase().equals(apiMation.get("method").toString())) {
            throw new CustomException("request method is wrong, request is 404.");
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        new PutObject(request, response);
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            // 如果是ajax请求响应头会有x-requested-with
            return true;
        } else {
            // 非ajax请求时
            return true;
        }
    }

    // 进入Handler方法之后，在返回ModelAndView之前执行
    // 应用场景从modelAndView对象出发：将公用的模型数据(比如菜单的导航)在这里传到视图，也可以在这里面来统一指定视图
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            // 如果是ajax请求响应头会有x-requested-with
            if (OutputObject.getCode() == ObjectConstant.WRONG) {
                OutputObject.setCode(0);
                if (OutputObject.getMessage().equals(ObjectConstant.WRONG_MESSAGE)) {
                    OutputObject.setMessage("成功");
                } else {
                    OutputObject.setCode(ObjectConstant.WRONG);
                }
            }
            OutputObject.put();
        } else {
            // 非ajax请求时
            if (OutputObject.getCode() == ObjectConstant.WRONG) {
                OutputObject.setCode(0);
                if (OutputObject.getMessage().equals(ObjectConstant.WRONG_MESSAGE)) {
                    OutputObject.setMessage("成功");
                } else {
                    OutputObject.setCode(ObjectConstant.WRONG);
                }
            }
            OutputObject.put();
        }
    }

    // 执行Handler完成后执行此方法
    // 应用场景：统一的异常处理，统一的日志处理
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        PutObject.remove();
    }

}
