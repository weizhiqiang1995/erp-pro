/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.OrganizationConstants;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.entity.organization.department.Department;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.exception.CustomException;
import com.skyeye.organization.dao.CompanyDepartmentDao;
import com.skyeye.organization.service.CompanyDepartmentService;
import com.skyeye.organization.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CompanyDepartmentServiceImpl
 * @Description: 公司部门信息管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:56
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class CompanyDepartmentServiceImpl extends SkyeyeBusinessServiceImpl<CompanyDepartmentDao, Department> implements CompanyDepartmentService {

    @Autowired
    private CompanyDepartmentDao companyDepartmentDao;

    @Autowired
    private ICompanyService iCompanyService;

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 获取公司部门信息列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     */
    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = companyDepartmentDao.queryCompanyDepartmentList(commonPageInfo);
        iCompanyService.setName(beans, "companyId", "companyName");
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        beans.forEach(bean -> {
            Integer overtimeSettlementType = Integer.parseInt(bean.get("overtimeSettlementType").toString());
            bean.put("overtimeSettlementTypeName", OrganizationConstants.OvertimeSettlementType.getTitleByType(overtimeSettlementType));
        });
        return beans;
    }

    @Override
    public void deletePreExecution(String id) {
        // 判断是否有员工
        Map<String, Object> bean = companyDepartmentDao.queryCompanyDepartmentUserMationById(id);
        if (Integer.parseInt(bean.get("childsNum").toString()) > 0) {
            throw new CustomException("该部门下存在员工，无法直接删除。");
        }
        // 判断是否有职位
        bean = companyDepartmentDao.queryCompanyJobNumMationById(id);
        if (Integer.parseInt(bean.get("companyJobNum").toString()) > 0) {
            throw new CustomException("该部门下存在职位，无法直接删除。");
        }
    }

    /**
     * 获取公司部门信息列表展示为树根据公司id
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCompanyDepartmentListTreeByCompanyId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = companyDepartmentDao.queryCompanyDepartmentListTreeByCompanyId(map);
        beans = ToolUtil.listToTree(beans, "id", "parentId", "children");
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 根据公司id获取部门列表展示为下拉选择框
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCompanyDepartmentListByCompanyIdToSelect(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String companyId = map.get("companyId").toString();
        List<Map<String, Object>> beans = companyDepartmentDao.queryDepartmentListByCompanyId(companyId);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 获取部门列表展示为表格供其他选择
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */

    @Override
    public void queryCompanyDepartmentListToChoose(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        List<Map<String, Object>> beans = queryPageDataList(inputObject);
        iCompanyService.setName(beans, "companyId", "companyName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 获取当前登录用户所属企业的所有部门信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDepartmentListByCurrentUserBelong(InputObject inputObject, OutputObject outputObject) {
        String companyId = inputObject.getLogParams().get("companyId").toString();
        List<Map<String, Object>> list = companyDepartmentDao.queryDepartmentListByCompanyId(companyId);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }

}
