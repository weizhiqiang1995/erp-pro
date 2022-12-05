/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveUserStaffCapitalDao;
import com.skyeye.eve.service.SysEveUserStaffCapitalService;
import com.skyeye.organization.service.ICompanyService;
import com.skyeye.organization.service.IDepmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveUserStaffCapitalServiceImpl
 * @Description: 员工非工资型的额外资金结算池服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/9/2 16:40
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysEveUserStaffCapitalServiceImpl implements SysEveUserStaffCapitalService {

    @Autowired
    private SysEveUserStaffCapitalDao sysEveUserStaffCapitalDao;

    @Autowired
    private ICompanyService iCompanyService;

    @Autowired
    private IDepmentService iDepmentService;

    /**
     * 新增员工待结算资金池信息(用于定时任务)
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void addMonthMoney2StaffCapital(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        String companyId = map.get("companyId").toString();
        String departmentId = map.get("departmentId").toString();
        String monthTime = map.get("monthTime").toString();
        Integer type = Integer.parseInt(map.get("type").toString());
        String money = map.get("money").toString();
        this.addMonthMoney2StaffCapital(staffId, companyId, departmentId, monthTime, type, money);
    }

    /**
     * 新增员工待结算资金池信息
     *
     * @param staffId      员工id
     * @param companyId    企业id
     * @param departmentId 部门id
     * @param monthTime    指定年月，格式为：yyyy-MM
     * @param type         该资金来源类型
     * @param money        金额
     */
    @Override
    public void addMonthMoney2StaffCapital(String staffId, String companyId, String departmentId, String monthTime, int type, String money) {
        synchronized (staffId) {
            Map<String, Object> staffCapital = sysEveUserStaffCapitalDao.queryStaffCapitalMation(staffId, monthTime);
            if (staffCapital == null || staffCapital.isEmpty()) {
                staffCapital = setStaffCapitalMation(staffId, companyId, departmentId, monthTime, type, money);
                sysEveUserStaffCapitalDao.insertStaffCapitalMation(staffCapital);
            } else {
                String oldMoney = staffCapital.get("money").toString();
                String newMoney = CalculationUtil.add(oldMoney, money, 2);
                staffCapital.put("money", newMoney);
                sysEveUserStaffCapitalDao.editStaffCapitalMoneyMation(staffCapital);
            }
        }
    }

    private Map<String, Object> setStaffCapitalMation(String staffId, String companyId, String departmentId, String monthTime, int type, String money) {
        Map<String, Object> staffCapital = new HashMap<>();
        staffCapital.put("id", ToolUtil.getSurFaceId());
        staffCapital.put("staffId", staffId);
        staffCapital.put("companyId", companyId);
        staffCapital.put("departmentId", departmentId);
        staffCapital.put("monthTime", monthTime);
        staffCapital.put("money", money);
        staffCapital.put("type", type);
        // 状态  1.待结算  2.批准在某月工资发放日结算  3.已结算
        staffCapital.put("state", 1);
        staffCapital.put("createTime", DateUtil.getTimeAndToString());
        return staffCapital;
    }

    /**
     * 根据月份以及部门查询未结算的额外资金
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryStaffCapitalWaitPayMonthList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveUserStaffCapitalDao.queryStaffCapitalWaitPayMonthList(map);
        iCompanyService.setNameForMap(beans, "companyId", "companyName");
        iDepmentService.setNameForMap(beans, "departmentId", "departmentName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
