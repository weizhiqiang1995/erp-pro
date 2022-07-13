/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.dao.SysDataBaseDao;
import com.skyeye.eve.service.SysDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysDataBaseServiceImpl implements SysDataBaseService {

    @Autowired
    private SysDataBaseDao sysDataBaseDao;

    @Value("${jdbc.database.name}")
    private String dbName;

    /**
     * 获取数据库表名信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysDataBaseSelectList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("dbName", dbName);
        List<Map<String, Object>> beans = sysDataBaseDao.querySysDataBaseSelectList(map);
        outputObject.setBeans(beans);
        if (!beans.isEmpty()) {
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取数据库表备注信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysDataBaseDescSelectList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("dbName", dbName);
        List<Map<String, Object>> beans = sysDataBaseDao.querySysDataBaseDescSelectList(map);
        outputObject.setBeans(beans);
        if (!beans.isEmpty()) {
            outputObject.settotal(beans.size());
        }
    }

}
