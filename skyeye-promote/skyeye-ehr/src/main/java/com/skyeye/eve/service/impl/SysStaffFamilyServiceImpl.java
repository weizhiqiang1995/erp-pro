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
import com.skyeye.eve.dao.SysStaffFamilyDao;
import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import com.skyeye.eve.service.IEnclosureService;
import com.skyeye.eve.service.ISysDictDataService;
import com.skyeye.eve.service.SysStaffFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysStaffFamilyServiceImpl
 * @Description: 员工家庭情况管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:39
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysStaffFamilyServiceImpl implements SysStaffFamilyService {

    @Autowired
    private SysStaffFamilyDao sysStaffFamilyDao;

    @Autowired
    private IEnclosureService iEnclosureService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    /**
     * 查询所有家庭情况列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllSysStaffFamilyList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
        List<Map<String, Object>> beans = sysStaffFamilyDao.queryAllSysStaffFamilyList(params);
        iSysDictDataService.getDictDataNameByIdList(beans, "relationshipId", "relationshipName");
        iSysDictDataService.getDictDataNameByIdList(beans, "cardType", "cardTypeName");
        iSysDictDataService.getDictDataNameByIdList(beans, "politicId", "politicName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 员工家庭情况信息录入
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysStaffFamilyMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createTime", DateUtil.getTimeAndToString());
        sysStaffFamilyDao.insertSysStaffFamilyMation(map);
    }

    /**
     * 编辑员工家庭情况信息时回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysStaffFamilyMationToEdit(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffFamilyDao.querySysStaffFamilyMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            iSysDictDataService.getDictDataNameByIdBean(certificate, "relationshipId", "relationshipName");
            iSysDictDataService.getDictDataNameByIdBean(certificate, "cardType", "cardTypeName");
            iSysDictDataService.getDictDataNameByIdBean(certificate, "politicId", "politicName");
            // 附件
            if (certificate.containsKey("enclosure") && !ToolUtil.isBlank(certificate.get("enclosure").toString())) {
                List<Map<String, Object>> beans = iEnclosureService.queryEnclosureInfoByIds(certificate.get("enclosure").toString());
                certificate.put("enclosureInfo", beans);
            }
            outputObject.setBean(certificate);
            outputObject.settotal(1);
        } else {
            outputObject.setreturnMessage("该家庭情况信息不存在.");
        }
    }

    /**
     * 编辑员工家庭情况信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysStaffFamilyMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffFamilyDao.querySysStaffFamilyMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            sysStaffFamilyDao.editSysStaffFamilyMationById(map);
        } else {
            outputObject.setreturnMessage("该家庭情况信息不存在.");
        }
    }

    /**
     * 删除员工家庭情况信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysStaffFamilyMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        sysStaffFamilyDao.deleteSysStaffFamilyMationById(id);
    }

    /**
     * 查询指定员工的家庭情况列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPointStaffSysStaffFamilyList(InputObject inputObject, OutputObject outputObject) {
        PointStaffQueryDo pointStaffQuery = inputObject.getParams(PointStaffQueryDo.class);
        Page pages = PageHelper.startPage(pointStaffQuery.getPage(), pointStaffQuery.getLimit());
        List<Map<String, Object>> beans = sysStaffFamilyDao.queryPointStaffSysStaffFamilyList(pointStaffQuery);
        iSysDictDataService.getDictDataNameByIdList(beans, "relationshipId", "relationshipName");
        iSysDictDataService.getDictDataNameByIdList(beans, "cardType", "cardTypeName");
        iSysDictDataService.getDictDataNameByIdList(beans, "politicId", "politicName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
