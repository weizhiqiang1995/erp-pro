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
import com.skyeye.eve.dao.SysStaffLanguageDao;
import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import com.skyeye.eve.entity.ehr.language.SysStaffLanguageQueryDo;
import com.skyeye.eve.service.SysDictDataService;
import com.skyeye.eve.service.SysStaffLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysStaffLanguageServiceImpl
 * @Description: 员工语言能力管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:40
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysStaffLanguageServiceImpl implements SysStaffLanguageService {

    @Autowired
    private SysStaffLanguageDao sysStaffLanguageDao;

    @Autowired
    private SysEnclosureDao sysEnclosureDao;

    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 查询所有语言能力列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryAllSysStaffLanguageList(InputObject inputObject, OutputObject outputObject) {
        SysStaffLanguageQueryDo sysStaffLanguageQuery = inputObject.getParams(SysStaffLanguageQueryDo.class);
        Page pages = PageHelper.startPage(sysStaffLanguageQuery.getPage(), sysStaffLanguageQuery.getLimit());
        List<Map<String, Object>> beans = sysStaffLanguageDao.queryAllSysStaffLanguageList(sysStaffLanguageQuery);
        sysDictDataService.getDictDataNameByIdList(beans, "languageId", "languageTypeName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 员工语言能力信息录入
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysStaffLanguageMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createTime", DateUtil.getTimeAndToString());
        sysStaffLanguageDao.insertSysStaffLanguageMation(map);
    }

    /**
     * 编辑员工语言能力信息时回显
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysStaffLanguageMationToEdit(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffLanguageDao.querySysStaffLanguageMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            sysDictDataService.getDictDataNameByIdBean(certificate, "languageId", "languageTypeName");
            // 附件
            if (certificate.containsKey("enclosure") && !ToolUtil.isBlank(certificate.get("enclosure").toString())) {
                List<Map<String, Object>> beans = sysEnclosureDao.queryEnclosureInfo(certificate.get("enclosure").toString());
                certificate.put("enclosureInfo", beans);
            }
            outputObject.setBean(certificate);
            outputObject.settotal(1);
        } else {
            outputObject.setreturnMessage("该语言能力信息不存在.");
        }
    }

    /**
     * 编辑员工语言能力信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysStaffLanguageMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffLanguageDao.querySysStaffLanguageMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            sysStaffLanguageDao.editSysStaffLanguageMationById(map);
        } else {
            outputObject.setreturnMessage("该语言能力信息不存在.");
        }
    }

    /**
     * 删除员工语言能力信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysStaffLanguageMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        sysStaffLanguageDao.deleteSysStaffLanguageMationById(id);
    }

    /**
     * 查询指定员工的语言能力列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryPointStaffSysStaffLanguageList(InputObject inputObject, OutputObject outputObject) {
        PointStaffQueryDo pointStaffQuery = inputObject.getParams(PointStaffQueryDo.class);
        Page pages = PageHelper.startPage(pointStaffQuery.getPage(), pointStaffQuery.getLimit());
        List<Map<String, Object>> beans = sysStaffLanguageDao.queryPointStaffSysStaffLanguageList(pointStaffQuery);
        sysDictDataService.getDictDataNameByIdList(beans, "languageId", "languageTypeName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
