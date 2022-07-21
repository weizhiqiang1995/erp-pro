/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service.impl;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.ApiMationDao;
import com.skyeye.service.ApiMationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ClassName: ApiMationServiceImpl
 * @Description: api接口服务类
 * @author: skyeye云系列
 * @date: 2021/11/28 12:31
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ApiMationServiceImpl implements ApiMationService {

    @Autowired
    private ApiMationDao apiMationDao;

    @Value("${skyeye.appid}")
    private String appId;

    /**
     * 通过id查找对应的api接口信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void selectApiMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String urlId = map.get("urlId").toString();
        Map<String, Object> bean = apiMationDao.queryApiMationToEditById(urlId);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑api接口信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editApiMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String urlId = map.get("urlId").toString();
        Map<String, Object> bean = apiMationDao.queryApiMationToEditById(urlId);
        map.put("userId", inputObject.getLogParams().get("id"));
        map.put("currentTime", DateUtil.getTimeAndToString());
        if (bean != null && !bean.isEmpty()) {
            apiMationDao.editApiMationById(map);
        } else {
            map.put("id", ToolUtil.getSurFaceId());
            map.put("appId", appId);
            apiMationDao.insertApiMation(map);
        }
    }

}

