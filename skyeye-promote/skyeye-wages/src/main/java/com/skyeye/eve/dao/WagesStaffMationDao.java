/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WagesStaffMationDao
 * @Description: 员工信息设定管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:18
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface WagesStaffMationDao {

    public List<Map<String, Object>> queryWagesStaffWaitAllocatedMationList(Map<String, Object> map);

    public Map<String, Object> querySysUserStaffMationById(@Param("staffId") String staffId);

    public void saveStaffWagesModelFieldMation(@Param("list") List<Map<String, Object>> wagesModelFieldMation);

    public void editStaffDesignWagesByStaffId(@Param("staffId") String staffId, @Param("state") int state, @Param("actMoney") String actMoney);

    public List<Map<String, Object>> queryWagesStaffDesignMationList(Map<String, Object> map);

    /**
     * 获取一条还未计算上个月薪资的员工信息(不包含本月刚入职的新员工)
     *
     * @param lastMonthDate 上个月的年月
     * @param staffId       不包含的员工id
     * @return
     */
    public Map<String, Object> queryNoWagesLastMonthByLastMonthDate(@Param("lastMonthDate") String lastMonthDate, @Param("list") List<String> staffId);

    /**
     * 获取上个月指定员工的所有考勤记录信息
     *
     * @param staffId       员工id
     * @param lastMonthDate 上个月的年月
     * @return
     */
    public List<Map<String, Object>> queryLastMonthCheckWork(@Param("staffId") String staffId, @Param("lastMonthDate") String lastMonthDate);

    /**
     * 获取上个月指定员工的所有审批通过请假记录信息
     *
     * @param staffId       员工id
     * @param lastMonthDate 上个月的年月
     * @return
     */
    public List<Map<String, Object>> queryLastMonthLeaveTime(@Param("staffId") String staffId, @Param("lastMonthDate") String lastMonthDate);

    /**
     * 获取上个月指定员工的所有审批通过销假记录信息
     *
     * @param staffId       员工id
     * @param lastMonthDate 上个月的年月
     * @return
     */
    public List<Map<String, Object>> queryLastMonthCancleLeaveTime(@Param("staffId") String staffId, @Param("lastMonthDate") String lastMonthDate);

    /**
     * 将指定员工月度清零的薪资字段设置为0
     *
     * @param staffId 员工id
     */
    public void editStaffMonthlyClearingWagesByStaffId(@Param("staffId") String staffId);

    /**
     * 获取员工薪资条信息
     *
     * @param staffId  员工id
     * @param payMonth 月份
     * @return
     */
    public Map<String, Object> queryWagesStaffPaymentDetail(@Param("staffId") String staffId, @Param("payMonth") String payMonth);

}
