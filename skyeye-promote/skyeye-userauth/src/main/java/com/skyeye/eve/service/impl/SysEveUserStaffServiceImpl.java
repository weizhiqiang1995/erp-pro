/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveUserDao;
import com.skyeye.eve.dao.SysEveUserStaffDao;
import com.skyeye.eve.dao.WagesFieldTypeDao;
import com.skyeye.eve.entity.userauth.user.SysUserStaffQueryDo;
import com.skyeye.eve.service.SysEveUserService;
import com.skyeye.eve.service.SysEveUserStaffService;
import com.skyeye.exception.CustomException;
import com.skyeye.jedis.JedisClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: SysEveUserStaffServiceImpl
 * @Description: 员工管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 12:02
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysEveUserStaffServiceImpl implements SysEveUserStaffService {

    @Autowired
    private SysEveUserStaffDao sysEveUserStaffDao;

    @Autowired
    private SysEveUserDao sysEveUserDao;

    @Autowired
    private SysEveUserService sysEveUserService;

    @Autowired
    public JedisClientService jedisClient;

    @Autowired
    private WagesFieldTypeDao wagesFieldTypeDao;

    @Value("${skyeye.jobNumberPrefix}")
    private String jobNumberPrefix;

    /**
     * 员工状态
     */
    public enum State {
        ON_THE_JOB(1, "在职(转正的员工)"),
        QUIT(2, "离职"),
        PROBATION(3, "见习(用于实习生)"),
        PROBATION_PERIOD(4, "试用期(用于未转正的员工)"),
        RETIRE(5, "退休");

        private int state;
        private String name;

        State(int state, String name) {
            this.state = state;
            this.name = name;
        }

        public int getState() {
            return state;
        }

        public String getName() {
            return name;
        }

        public static String getNameByState(Integer state) {
            for (State bean : State.values()) {
                if (state == bean.getState()) {
                    return bean.getName();
                }
            }
            return StringUtils.EMPTY;
        }
    }

    /**
     * 查出所有员工列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserStaffList(InputObject inputObject, OutputObject outputObject) {
        SysUserStaffQueryDo sysUserStaffQuery = inputObject.getParams(SysUserStaffQueryDo.class);
        Page pages = PageHelper.startPage(sysUserStaffQuery.getPage(), sysUserStaffQuery.getLimit());
        List<Map<String, Object>> beans = sysEveUserStaffDao.querySysUserStaffList(sysUserStaffQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增员工
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysUserStaffMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userIdCard = map.get("userIdCard").toString();
        Map<String, Object> bean = null;
        if (!ToolUtil.isBlank(userIdCard)) {
            // 身份证不为空时进行校验
            bean = sysEveUserStaffDao.querySysUserStaffMationByIdCard(map);
        }
        if (bean != null && !bean.isEmpty()) {
            outputObject.setreturnMessage("该员工身份证已存在，不能重复添加！");
        } else {
            insertNewUserMation(map);
        }
    }

    /**
     * 新增员工信息
     *
     * @param map
     */
    @Override
    public void insertNewUserMation(Map<String, Object> map) {
        String staffId;
        staffId = ToolUtil.getSurFaceId();
        map.put("id", staffId);
        map.put("jobNumberPrefix", jobNumberPrefix);
        // 1.新增员工信息
        sysEveUserStaffDao.insertSysUserStaffMation(map);
        // 2.新增员工考勤时间段
        createUserStaffCheckWorkTime(map, staffId);
        // 3.新增员工薪资字段信息
        createUserStaffWagesFieldType(staffId);
    }

    /**
     * 新增员工薪资字段信息
     *
     * @param staffId
     */
    private void createUserStaffWagesFieldType(String staffId) {
        List<Map<String, Object>> fieldType = wagesFieldTypeDao.queryAllWagesFieldTypeList();
        if (fieldType != null && !fieldType.isEmpty()) {
            fieldType.stream().forEach(bean -> {
                bean.put("id", staffId);
            });
            wagesFieldTypeDao.insertWagesFieldTypeKeyToStaff(fieldType);
        }
    }

    /**
     * 新增员工考勤时间段
     *
     * @param map
     * @param staffId
     */
    private void createUserStaffCheckWorkTime(Map<String, Object> map, String staffId) {
        // 逗号隔开的多班次考勤
        String str = map.containsKey("checkTimeStr") ? map.get("checkTimeStr").toString() : "";
        if (!ToolUtil.isBlank(str)) {
            List<String> timeIds = Arrays.asList(str.split(","));
            // 校验多班次考勤是否有重复时间段
            boolean repeat = judgeRepeatShift(timeIds);
            if (repeat) {
                // 存在冲突的工作时间段
                throw new CustomException("Conflicting working hours.");
            }
            List<Map<String, Object>> staffTimeMation = new ArrayList<>();
            timeIds.stream().forEach(timeId -> {
                if (!ToolUtil.isBlank(timeId)) {
                    Map<String, Object> bean = new HashMap<>();
                    bean.put("staffId", staffId);
                    bean.put("timeId", timeId);
                    staffTimeMation.add(bean);
                }
            });
            if (!staffTimeMation.isEmpty()) {
                sysEveUserStaffDao.insertStaffCheckWorkTimeRelation(staffTimeMation);
            }
        }
    }

    private boolean judgeRepeatShift(List<String> timeIds) {
        // 1.获取班次的上下班打卡时间
        List<Map<String, Object>> timeMation = sysEveUserStaffDao.queryCheckTimeMationByTimeIds(timeIds);
        // 2.获取班次的工作日
        List<Map<String, Object>> timeDayMation = sysEveUserStaffDao.queryCheckTimeDaysMationByTimeIds(timeIds);
        timeMation.forEach(bean -> {
            List<Map<String, Object>> thisDayMation = timeDayMation.stream()
                .filter(item -> item.get("timeId").toString().equals(bean.get("timeId").toString()))
                .collect(Collectors.toList());
            bean.put("days", thisDayMation);
        });
        // 3.校验工作日是否冲突
        return judgeRepeatWorking(timeMation);
    }

    private boolean judgeRepeatWorking(List<Map<String, Object>> timeMation) {
        if (timeMation.size() > 1) {
            for (int i = 0; i < timeMation.size(); i++) {
                for (int j = (i + 1); j < timeMation.size(); j++) {
                    List<String> times = new ArrayList<>();
                    times.add(timeMation.get(i).get("startTime").toString() + "-"
                        + timeMation.get(i).get("endTime").toString());
                    times.add(timeMation.get(j).get("startTime").toString() + "-"
                        + timeMation.get(j).get("endTime").toString());
                    // 1.首先判断每天的工作日的开始时间和结束时间是否有重复
                    boolean flag = DateUtil.checkOverlap(times);
                    if (flag) {
                        // 开始时间和结束时间是否有重复
                        List<Map<String, Object>> iDayMation = (List<Map<String, Object>>) timeMation.get(i)
                            .get("days");
                        List<Map<String, Object>> jDayMation = (List<Map<String, Object>>) timeMation.get(j)
                            .get("days");
                        // 求这两个班次的工作日冲突的天数，根据类型和工作日(周几)判断
                        int size = iDayMation.stream()
                            .map(t -> jDayMation.stream()
                                .filter(s -> (Objects.equals(t.get("type").toString(), s.get("type").toString())
                                    || Objects.equals(t.get("type").toString(), "1")
                                    || Objects.equals(s.get("type").toString(), "1"))
                                    && Objects.equals(t.get("day").toString(), s.get("day").toString()))
                                .findAny().orElse(null)).filter(Objects::nonNull).collect(Collectors.toList()).size();
                        if (size > 0) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * 通过id查询一条员工信息回显编辑
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserStaffById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("id").toString();
        Map<String, Object> bean = sysEveUserStaffDao.querySysUserStaffById(staffId);
        if (bean != null && !bean.isEmpty()) {
            bean.put("stateName", State.getNameByState(Integer.parseInt(bean.get("state").toString())));
            // 1.员工考勤时间段信息
            List<Map<String, Object>> staffTimeMation = sysEveUserStaffDao
                .queryStaffCheckWorkTimeRelationByStaffId(bean.get("id").toString());
            bean.put("checkTimeStr", staffTimeMation);
            outputObject.setBean(bean);
            outputObject.settotal(1);
        } else {
            outputObject.setreturnMessage("The data does not exist");
        }
    }

    /**
     * 编辑员工信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysUserStaffById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("id").toString();
        String userIdCard = map.get("userIdCard").toString();
        if (!ToolUtil.isBlank(userIdCard)) {
            Map<String, Object> bean = sysEveUserStaffDao.querySysUserStaffMationByIdCardAndId(map);
            if (bean != null && !bean.isEmpty()) {
                throw new CustomException("该员工身份证已存在，不能重复添加！");
            }
        }

        Map<String, Object> userMation = sysEveUserStaffDao.querySysUserStaffById(staffId);
        if (CollectionUtils.isEmpty(userMation)) {
            throw new CustomException("该员工不存在！");
        }
        // 1.编辑员工信息
        sysEveUserStaffDao.editSysUserStaffById(map);
        // 2.删除员工所在部门的缓存
        jedisClient.delKeys(Constants.getSysTalkGroupUserListMationById(userMation.get("departmentId").toString()) + "*");
        // 2.1删除用户在redis中存储的好友组信息
        jedisClient.delKeys(Constants.getSysTalkGroupUserListMationById(map.get("departmentId").toString()) + "*");
        // 3.删除员工考勤时间段信息再重新添加
        sysEveUserStaffDao.deleteStaffCheckWorkTimeRelationByStaffId(staffId);
        // 3.1.新增员工考勤时间段
        createUserStaffCheckWorkTime(map, staffId);
    }

    /**
     * 通过id查询一条员工信息展示详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserStaffByIdToDetails(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("id").toString();
        Map<String, Object> bean = sysEveUserStaffDao.querySysUserStaffByIdToDetails(staffId);
        if (bean != null && !bean.isEmpty()) {
            bean.put("stateName", State.getNameByState(Integer.parseInt(bean.get("state").toString())));
            // 1.员工考勤时间段信息
            List<Map<String, Object>> staffTimeMation = sysEveUserStaffDao
                .queryStaffCheckWorkTimeRelationNameByStaffId(staffId);
            bean.put("checkTimeStr", staffTimeMation);
            outputObject.setBean(bean);
            outputObject.settotal(1);
        } else {
            outputObject.setreturnMessage("The data does not exist");
        }
    }

    /**
     * 员工离职
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysUserStaffState(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("state", State.QUIT.getState());
        sysEveUserStaffDao.editSysUserStaffState(map);
        String staffId = map.get("id").toString();
        Map<String, Object> staffMation = sysEveUserStaffDao.querySysUserStaffByIdToDetails(staffId);
        String userId = staffMation.get("userId").toString();
        if (!ToolUtil.isBlank(userId)) {
            String departmentId = staffMation.get("departmentId").toString();
            // 删除redis中缓存的单位下的用户
            jedisClient.delKeys(Constants.getSysTalkGroupUserListMationById(departmentId) + "*");
            // 锁定帐号
            sysEveUserDao.editSysUserLock(userId, SysEveUserServiceImpl.STATE.SYS_USER_LOCK_STATE_ISLOCK.getState());
            // 退出登录
            sysEveUserService.removeLogin(userId);
        }
    }

    /**
     * 普通员工转教职工
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editTurnTeacher(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();

        // 员工类型判断
        Map<String, Object> staffType = sysEveUserStaffDao.querySysUserStaffByIdToDetails(staffId);
        if (staffType != null && !staffType.isEmpty()) {
            //如果是普通员工，则允许转教职工
            if ("1".equals(staffType.get("staffType").toString())) {
                //修改类型
                sysEveUserStaffDao.editStaffTypeById(staffId);
                //添加教职工学校绑定信息
                Map<String, Object> schoolStaff = new HashMap<>();
                schoolStaff.put("id", ToolUtil.getSurFaceId());
                schoolStaff.put("staffId", staffId);
                schoolStaff.put("schoolId", map.get("schoolId"));
                sysEveUserStaffDao.insertSchoolStaffMation(schoolStaff);
            } else {
                outputObject.setreturnMessage("该员工无法转教职工。");
            }
        } else {
            outputObject.setreturnMessage("The data does not exist");
        }
    }

    /**
     * 查看所有员工列表展示为表格供其他选择
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserStaffListToTable(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveUserStaffDao.querySysUserStaffListToTable(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 根据员工ids获取员工信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserStaffListByIds(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<String> idsList = Arrays.asList(map.get("ids").toString().split(","));
        List<Map<String, Object>> beans = new ArrayList<>();
        if (!idsList.isEmpty()) {
            beans = sysEveUserStaffDao.queryStaffNameListByIdList(idsList);
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        } else {
            outputObject.setBeans(beans);
        }
    }

    /**
     * 获取当前登录员工的信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserStaffLogin(InputObject inputObject, OutputObject outputObject) {
        String staffId = inputObject.getLogParams().get("staffId").toString();
        Map<String, Object> bean = sysEveUserStaffDao.querySysUserStaffByIdToDetails(staffId);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 根据用户ids获取用户信息集合
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserNameList(InputObject inputObject, OutputObject outputObject) {
        String userIds = inputObject.getParams().get("userIds").toString();
        List<Map<String, Object>> beans = sysEveUserStaffDao.queryUserNameList(userIds);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 通过用户id查询一条员工信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserStaffDetailsByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = map.get("userId").toString();
        Map<String, Object> bean = sysEveUserStaffDao.querySysUserStaffDetailsByUserId(userId);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 修改员工剩余年假信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void editSysUserStaffAnnualLeaveById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        String quarterYearHour = map.get("quarterYearHour").toString();
        String annualLeaveStatisTime = map.get("annualLeaveStatisTime").toString();
        sysEveUserStaffDao.editSysUserStaffAnnualLeaveById(staffId, quarterYearHour, annualLeaveStatisTime);
    }

    /**
     * 修改员工的补休池剩余补休信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void updateSysUserStaffHolidayNumberById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        String holidayNumber = map.get("holidayNumber").toString();
        String holidayStatisTime = map.get("holidayStatisTime").toString();
        sysEveUserStaffDao.updateSysUserStaffHolidayNumberById(staffId, holidayNumber, holidayStatisTime);
    }

    /**
     * 修改员工的补休池已休补休信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void updateSysUserStaffRetiredHolidayNumberById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        String retiredHolidayNumber = map.get("retiredHolidayNumber").toString();
        String retiredHolidayStatisTime = map.get("retiredHolidayStatisTime").toString();
        sysEveUserStaffDao.updateSysUserStaffHolidayNumberById(staffId, retiredHolidayNumber, retiredHolidayStatisTime);
    }

    /**
     * 根据员工id获取该员工的考勤时间段
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryStaffCheckWorkTimeRelationNameByStaffId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        // 员工考勤时间段信息
        List<Map<String, Object>> staffTimeMation = sysEveUserStaffDao.queryStaffCheckWorkTimeRelationNameByStaffId(staffId);
        outputObject.setBeans(staffTimeMation);
        outputObject.settotal(1);
    }

}
