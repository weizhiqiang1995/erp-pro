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
import com.skyeye.eve.dao.SysStaffCertificateDao;
import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import com.skyeye.eve.service.ISysDictDataService;
import com.skyeye.eve.service.SysStaffCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysStaffCertificateServiceImpl
 * @Description: 员工证书管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysStaffCertificateServiceImpl implements SysStaffCertificateService {

    @Autowired
    private SysStaffCertificateDao sysStaffCertificateDao;

    @Autowired
    private SysEnclosureDao sysEnclosureDao;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    /**
     * 查询所有证书列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllSysStaffCertificateList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
        List<Map<String, Object>> beans = sysStaffCertificateDao.queryAllSysStaffCertificateList(params);
        iSysDictDataService.getDictDataNameByIdList(beans, "typeId", "certificateTypeName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 员工证书信息录入
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysStaffCertificateMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createTime", DateUtil.getTimeAndToString());
        sysStaffCertificateDao.insertSysStaffCertificateMation(map);
    }

    /**
     * 编辑员工证书信息时回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysStaffCertificateMationToEdit(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffCertificateDao.querySysStaffCertificateMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            iSysDictDataService.getDictDataNameByIdBean(certificate, "typeId", "certificateTypeName");
            // 附件
            if (certificate.containsKey("enclosure") && !ToolUtil.isBlank(certificate.get("enclosure").toString())) {
                List<Map<String, Object>> beans = sysEnclosureDao.queryEnclosureInfo(certificate.get("enclosure").toString());
                certificate.put("enclosureInfo", beans);
            }
            outputObject.setBean(certificate);
            outputObject.settotal(1);
        } else {
            outputObject.setreturnMessage("该证书信息不存在.");
        }
    }

    /**
     * 编辑员工证书信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysStaffCertificateMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffCertificateDao.querySysStaffCertificateMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            sysStaffCertificateDao.editSysStaffCertificateMationById(map);
        } else {
            outputObject.setreturnMessage("该证书信息不存在.");
        }
    }

    /**
     * 删除员工证书信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysStaffCertificateMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        sysStaffCertificateDao.deleteSysStaffCertificateMationById(id);
    }

    /**
     * 查询指定员工的证书列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPointStaffSysStaffCertificateList(InputObject inputObject, OutputObject outputObject) {
        PointStaffQueryDo pointStaffQuery = inputObject.getParams(PointStaffQueryDo.class);
        Page pages = PageHelper.startPage(pointStaffQuery.getPage(), pointStaffQuery.getLimit());
        List<Map<String, Object>> beans = sysStaffCertificateDao.queryPointStaffSysStaffCertificateList(pointStaffQuery);
        iSysDictDataService.getDictDataNameByIdList(beans, "typeId", "certificateTypeName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
