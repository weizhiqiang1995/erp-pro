/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.MapUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SystemFoundationSettingsDao;
import com.skyeye.eve.service.SystemFoundationSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SystemFoundationSettingsServiceImpl
 * @Description: 系统基础设置服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/6 22:39
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SystemFoundationSettingsServiceImpl implements SystemFoundationSettingsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemFoundationSettingsServiceImpl.class);

    @Autowired
    private SystemFoundationSettingsDao systemFoundationSettingsDao;

    /**
     * 获取系统基础设置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySystemFoundationSettingsList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> bean = this.getSystemFoundationSettings();
        outputObject.setBean(bean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 编辑系统基础设置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSystemFoundationSettings(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        systemFoundationSettingsDao.editSystemFoundationSettings(map);
    }

    /**
     * 获取系统配置信息
     *
     * @return 系统配置信息
     */
    @Override
    public Map<String, Object> getSystemFoundationSettings() {
        Map<String, Object> map = systemFoundationSettingsDao.querySystemFoundationSettingsList();
        if (CollectionUtils.isEmpty(map)) {
            map = getBaseSettings();
            systemFoundationSettingsDao.insertSystemFoundationSettings(map);
        }
        judgeAndInitDefault(map);
        LOGGER.info("email server mation is: {}.", JSONUtil.toJsonStr(map));
        return map;
    }

    private Map<String, Object> getBaseSettings() {
        Map<String, Object> bean = new HashMap<>();
        bean.put("id", ToolUtil.getSurFaceId());
        bean.put("emailType", "imap");
        bean.put("emailReceiptServer", "imap.qq.com");
        bean.put("emailReceiptServerPort", "995");
        bean.put("emailSendServer", "smtp.qq.com");
        bean.put("emailSendServerPort", "25");
        bean.put("noDocumentaryDayNum", "30");
        bean.put("noChargeId", "2");
        bean.put("holidaysTypeJson", new ArrayList<>());
        bean.put("yearHolidaysMation", new ArrayList<>());
        bean.put("abnormalMation", new ArrayList<>());
        return bean;
    }

    /**
     * 为其中的一些字段设置默认值
     *
     * @param map
     */
    private void judgeAndInitDefault(Map<String, Object> map) {
        // 企业假期类型以及扣薪信息
        if (MapUtil.checkKeyIsNull(map, "holidaysTypeJson")) {
            map.put("holidaysTypeJson", new ArrayList<>());
        }
        // 年假信息
        if (MapUtil.checkKeyIsNull(map, "yearHolidaysMation")) {
            map.put("yearHolidaysMation", new ArrayList<>());
        }
        // 异常考勤制度管理信息
        if (MapUtil.checkKeyIsNull(map, "abnormalMation")) {
            map.put("abnormalMation", new ArrayList<>());
        }
        // 系统单据审核的一些设置
        if (MapUtil.checkKeyIsNull(map, "sysOrderBasicDesign")) {
            map.put("sysOrderBasicDesign", new ArrayList<>());
        }
    }

}
