/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service.impl;

import cn.hutool.json.JSONUtil;
import com.app.wechat.util.WxchatUtil;
import com.skyeye.common.constans.SysUserAuthConstants;
import com.skyeye.common.object.GetUserToken;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.UserPhoneDao;
import com.skyeye.eve.authority.service.SysAuthorityService;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.organization.service.*;
import com.skyeye.personnel.dao.SysEveUserDao;
import com.skyeye.service.UserPhoneService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserPhoneServiceImpl implements UserPhoneService {

    @Autowired
    private UserPhoneDao userPhoneDao;

    @Autowired
    private JedisClientService jedisClient;

    @Autowired
    private SysEveUserDao sysEveUserDao;

    @Autowired
    private SysAuthorityService sysAuthorityService;

    @Autowired
    private ICompanyService iCompanyService;

    @Autowired
    private IDepmentService iDepmentService;

    @Autowired
    private ICompanyJobService iCompanyJobService;

    @Autowired
    private CompanyMationService companyMationService;

    @Autowired
    private CompanyDepartmentService companyDepartmentService;

    @Autowired
    private CompanyJobService companyJobService;

    /**
     * 账号状态
     */
    public enum STATE {
        SYS_USER_LOCK_STATE_ISUNLOCK(0, "未锁定"),
        SYS_USER_LOCK_STATE_ISLOCK(1, "锁定");

        private int state;
        private String name;

        STATE(int state, String name) {
            this.state = state;
            this.name = name;
        }

        public int getState() {
            return state;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 手机端用户登录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPhoneToLogin(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> userMation = userPhoneDao.queryMationByUserCode(map);
        if (userMation == null) {
            outputObject.setreturnMessage("请确保用户名输入无误！");
        } else {
            int pwdNum = Integer.parseInt(userMation.get("pwdNum").toString());
            String password = map.get("password").toString();
            for (int i = 0; i < pwdNum; i++) {
                password = ToolUtil.MD5(password);
            }
            if (password.equals(userMation.get("password").toString())) {
                int userLock = Integer.parseInt(userMation.get("userLock").toString());
                if (STATE.SYS_USER_LOCK_STATE_ISLOCK.getState() == userLock) {
                    outputObject.setreturnMessage("您的账号已被锁定，请联系管理员解除！");
                } else {
                    String userId = userMation.get("id").toString();
                    String roleIds = userMation.get("roleId").toString();
                    userMation.remove("roleId");

                    // 获取动态token
                    String userToken = GetUserToken.createNewToken(userId, password);
                    userMation.put("userToken", userToken);

                    String appUserId = userId + SysUserAuthConstants.APP_IDENTIFYING;
                    iDepmentService.setName(userMation, "departmentId", "departmentName");
                    iCompanyJobService.setName(userMation, "jobId", "jobName");
                    SysUserAuthConstants.setUserLoginRedisCache(appUserId, userMation);
                    jedisClient.set("allMenuMation:" + appUserId, roleIds);
                    jedisClient.set("authPointsMation:" + appUserId, roleIds);
                    // 获取用户权限点返回给前台
                    List<Map<String, Object>> authPoints = sysAuthorityService.getRoleHasMenuPointListByRoleIds(roleIds, userId);
                    outputObject.setBean(userMation);
                    outputObject.setBeans(authPoints);
                }
            } else {
                outputObject.setreturnMessage("密码输入错误！");
            }
        }
    }

    /**
     * 根据openId获取用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserMationByOpenId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String openId = map.get("openId").toString();
        //判断该微信用户在redis中是否存在数据
        String key = WxchatUtil.getWechatUserOpenIdMation(openId);
        if (ToolUtil.isBlank(jedisClient.get(key))) {
            //该用户没有绑定账号
            Map<String, Object> bean = userPhoneDao.queryWxUserMationByOpenId(openId);
            //判断该用户的openId是否存在于数据库
            if (bean != null && !bean.isEmpty()) {
                //存在数据库
                map.putAll(bean);
                //1.将微信和账号的绑定信息存入redis
                jedisClient.set(key, JSONUtil.toJsonStr(bean));
                //如果已经绑定用户，则获取用户信息
                if (bean.containsKey("userId") && !ToolUtil.isBlank(bean.get("userId").toString())) {
                    Map<String, Object> userMation = userPhoneDao.queryUserMationByOpenId(openId);
                    iCompanyService.setName(userMation, "companyId", "companyName");
                    iDepmentService.setName(userMation, "departmentId", "departmentName");
                    // 2.将账号的信息存入redis
                    SysUserAuthConstants.setUserLoginRedisCache(bean.get("userId").toString() + SysUserAuthConstants.APP_IDENTIFYING, userMation);
                    //3.将权限的信息存入redis
                    jedisClient.set("authPointsMation:" + bean.get("userId").toString() + SysUserAuthConstants.APP_IDENTIFYING, "");
                }
            } else {
                //不存在
                map.put("id", ToolUtil.getSurFaceId());
                map.put("joinTime", DateUtil.getTimeAndToString());
                map.put("openId", openId);
                map.put("userId", "");
                userPhoneDao.insertWxUserMation(map);
                //1.将微信和账号的绑定信息存入redis
                jedisClient.set(key, JSONUtil.toJsonStr(map));
            }
        } else {
            map = JSONUtil.toBean(jedisClient.get(key), null);
            //如果已经绑定用户，则获取用户信息
            if (map.containsKey("userId") && !ToolUtil.isBlank(map.get("userId").toString())) {
                Map<String, Object> userMation = userPhoneDao.queryUserMationByOpenId(openId);
                iCompanyService.setName(userMation, "companyId", "companyName");
                //2.将账号的信息存入redis
                SysUserAuthConstants.setUserLoginRedisCache(map.get("userId").toString() + SysUserAuthConstants.APP_IDENTIFYING, userMation);
                //3.将权限的信息存入redis
                jedisClient.set("authPointsMation:" + map.get("userId").toString() + SysUserAuthConstants.APP_IDENTIFYING, "");
            } else {
                outputObject.setreturnMessage("您还未绑定用户，请前往绑定.", "-9000");
            }
        }
        outputObject.setBean(map);
    }

    /**
     * openId绑定用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void insertUserMationByOpenId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userCode = map.get("userCode").toString();
        String password = map.get("password").toString();
        String openId = map.get("openId").toString();
        // 根据账号获取用户信息
        Map<String, Object> userMation = sysEveUserDao.queryMationByUserCode(userCode);
        // 判断该账号是否存在
        if (userMation != null && !userMation.isEmpty()) {
            int pwdNum = Integer.parseInt(userMation.get("pwdNum").toString());
            for (int i = 0; i < pwdNum; i++) {
                password = ToolUtil.MD5(password);
            }
            //判断密码是否正确
            if (password.equals(userMation.get("password").toString())) {
                //判断账号是否锁定
                int userLock = Integer.parseInt(userMation.get("userLock").toString());
                if (STATE.SYS_USER_LOCK_STATE_ISLOCK.getState() == userLock) {
                    outputObject.setreturnMessage("您的账号已被锁定，请联系管理员解除.");
                } else {
                    Map<String, Object> wxUserMation = userPhoneDao.queryWxUserMationByOpenId(openId);
                    //判断该用户的openId是否存在于数据库
                    if (wxUserMation != null && !wxUserMation.isEmpty()) {
                        //判断当前openId是否已经绑定账号
                        if (wxUserMation.containsKey("userId") && !ToolUtil.isBlank(wxUserMation.get("userId").toString())) {
                            outputObject.setreturnMessage("该微信用户已绑定账号.");
                        } else {
                            //判断该账号是否被别人绑定
                            Map<String, Object> isBindInWx = userPhoneDao.queryUserBindMationByUserId(userMation.get("id").toString());
                            if (isBindInWx != null && !isBindInWx.isEmpty()) {
                                outputObject.setreturnMessage("该账号已被绑定.");
                            } else {
                                iCompanyJobService.setName(userMation, "jobId", "jobName");
                                //构建绑定信息对象
                                map = new HashMap<>();
                                String userId = userMation.get("id").toString();
                                map.put("userId", userId);
                                map.put("bindTime", DateUtil.getTimeAndToString());
                                map.put("openId", openId);
                                userPhoneDao.updateBindUserMation(map);
                                //重新获取绑定信息，存入redis，返回前端
                                map = userPhoneDao.queryWxUserMationByOpenId(openId);
                                //1.将微信和账号的绑定信息存入redis
                                String key = WxchatUtil.getWechatUserOpenIdMation(openId);
                                jedisClient.set(key, JSONUtil.toJsonStr(map));
                                //2.将账号的信息存入redis
                                SysUserAuthConstants.setUserLoginRedisCache(userId + SysUserAuthConstants.APP_IDENTIFYING, userMation);
                                //3.将权限的信息存入redis
                                jedisClient.set("authPointsMation:" + userId + SysUserAuthConstants.APP_IDENTIFYING, "");
                                outputObject.setBean(map);
                            }
                        }
                    } else {
                        outputObject.setreturnMessage("该微信用户不存在.");
                    }
                }
            } else {
                outputObject.setreturnMessage("密码输入错误.");
            }
        } else {
            outputObject.setreturnMessage("该账号不存在，请核实后进行登录.");
        }
    }

    /**
     * 人员选择获取所有公司和人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map = compareSelUserListByParams(map, inputObject);
        List<Map<String, Object>> beans = userPhoneDao.queryAllPeopleToTree(map);
        beans.addAll(companyMationService.queryCompanyList(StringUtils.EMPTY));
        beans.addAll(companyDepartmentService.queryDepartmentList(new ArrayList<>(), new ArrayList<>()));
        beans.addAll(companyJobService.queryJobList(new ArrayList<>(), new ArrayList<>()));
        beans = ToolUtil.listToTree(beans, "id", "pId", "children");
        outputObject.setBeans(beans);
    }

    /**
     * 获取人员列表时的参数转换
     *
     * @param map
     * @param inputObject 入参以及用户信息等获取对象
     * @return
     */
    public Map<String, Object> compareSelUserListByParams(Map<String, Object> map, InputObject inputObject) {
        String chooseOrNotMy = map.get("chooseOrNotMy").toString();//人员列表中是否包含自己--1.包含；其他参数不包含
        if (!"1".equals(chooseOrNotMy)) {
            Map<String, Object> user = inputObject.getLogParams();
            map.put("userId", user.get("id"));
        }
        String chooseOrNotEmail = map.get("chooseOrNotEmail").toString();//人员列表中是否必须绑定邮箱--1.必须；其他参数没必要
        if ("1".equals(chooseOrNotEmail)) {
            map.put("hasEmail", "1");
        }
        return map;
    }

}
