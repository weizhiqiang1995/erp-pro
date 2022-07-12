/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEnclosureDao;
import com.skyeye.eve.dao.SysStaffRewardPunishDao;
import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import com.skyeye.eve.service.SysDictDataService;
import com.skyeye.eve.service.SysStaffRewardPunishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysStaffRewardPunishServiceImpl
 * @Description: 员工奖惩管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:41
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysStaffRewardPunishServiceImpl implements SysStaffRewardPunishService {

    @Autowired
    private SysStaffRewardPunishDao sysStaffRewardPunishDao;

    @Autowired
    private SysEnclosureDao sysEnclosureDao;

    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 查询所有奖惩列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryAllSysStaffRewardPunishList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
        List<Map<String, Object>> beans = sysStaffRewardPunishDao.queryAllSysStaffRewardPunishList(params);
        sysDictDataService.getDictDataNameByIdList(beans, "typeId", "rewardPunishTypeName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 员工奖惩信息录入
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysStaffRewardPunishMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createTime", DateUtil.getTimeAndToString());
        if (ToolUtil.isBlank(map.get("price").toString())) {
            map.put("price", null);
        }
        sysStaffRewardPunishDao.insertSysStaffRewardPunishMation(map);
    }

    /**
     * 编辑员工奖惩信息时回显
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysStaffRewardPunishMationToEdit(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffRewardPunishDao.querySysStaffRewardPunishMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            sysDictDataService.getDictDataNameByIdBean(certificate, "typeId", "rewardPunishTypeName");
            // 附件
            if (certificate.containsKey("enclosure") && !ToolUtil.isBlank(certificate.get("enclosure").toString())) {
                List<Map<String, Object>> beans = sysEnclosureDao.queryEnclosureInfo(certificate.get("enclosure").toString());
                certificate.put("enclosureInfo", beans);
            }
            outputObject.setBean(certificate);
            outputObject.settotal(1);
        } else {
            outputObject.setreturnMessage("该教育背景信息不存在.");
        }
    }

    /**
     * 编辑员工奖惩信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysStaffRewardPunishMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffRewardPunishDao.querySysStaffRewardPunishMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            if (ToolUtil.isBlank(map.get("price").toString())) {
                map.put("price", null);
            }
            sysStaffRewardPunishDao.editSysStaffRewardPunishMationById(map);
        } else {
            outputObject.setreturnMessage("该教育背景信息不存在.");
        }
    }

    /**
     * 删除员工奖惩信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysStaffRewardPunishMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        sysStaffRewardPunishDao.deleteSysStaffRewardPunishMationById(id);
    }

    /**
     * 查询指定员工的奖惩列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryPointStaffSysStaffRewardPunishList(InputObject inputObject, OutputObject outputObject) {
        PointStaffQueryDo pointStaffQuery = inputObject.getParams(PointStaffQueryDo.class);
        Page pages = PageHelper.startPage(pointStaffQuery.getPage(), pointStaffQuery.getLimit());
        List<Map<String, Object>> beans = sysStaffRewardPunishDao.queryPointStaffSysStaffRewardPunishList(pointStaffQuery);
        sysDictDataService.getDictDataNameByIdList(beans, "typeId", "rewardPunishTypeName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
