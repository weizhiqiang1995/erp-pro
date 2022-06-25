/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.dao.SysTAreaDao;
import com.skyeye.eve.service.SysTAreaService;

@Service
public class SysTAreaServiceImpl implements SysTAreaService {

    @Autowired
    private SysTAreaDao sysTAreaDao;

    /**
     * 获取行政区划信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysTAreaList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysTAreaDao.querySysTAreaList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取一级省行政区划信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysTAreaProvinceList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysTAreaDao.querySysTAreaProvinceList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取二级市行政区划信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysTAreaCityList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysTAreaDao.querySysTAreaCityList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取三级县行政区划信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysTAreaChildAreaList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysTAreaDao.querySysTAreaChildAreaList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取四级镇行政区划信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysTAreaTownShipList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysTAreaDao.querySysTAreaTownShipList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

}
