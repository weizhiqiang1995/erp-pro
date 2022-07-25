/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.AppWorkPageDao;
import com.skyeye.eve.entity.userauth.menu.AppWorkPageMation;
import com.skyeye.eve.service.AppWorkPageService;
import com.skyeye.eve.service.IAuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AppWorkPageServiceImpl
 * @Description: 手机端菜单以及目录功能服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/4/10 23:18
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
public class AppWorkPageServiceImpl implements AppWorkPageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppWorkPageServiceImpl.class);

    @Autowired
    private AppWorkPageDao appWorkPageDao;

    @Autowired
    private IAuthUserService iAuthUserService;

    public enum State {
        START_NEW(1, "新建"),
        START_UP(2, "上线"),
        START_DOWN(3, "下线"),
        START_DELETE(4, "删除");
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
    }

    /**
     * 获取菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAppWorkPageList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        List<Map<String, Object>> beans = appWorkPageDao.queryAppWorkPageList(commonPageInfo);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增/编辑手机端菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeAppWorkPageMation(InputObject inputObject, OutputObject outputObject) {
        AppWorkPageMation appWorkPageMation = inputObject.getParams(AppWorkPageMation.class);
        // 1.根据条件进行校验
        QueryWrapper<AppWorkPageMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(AppWorkPageMation.class, "parentId"), appWorkPageMation.getParentId());
        queryWrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(AppWorkPageMation.class, "title"), appWorkPageMation.getTitle());
        if (StringUtils.isNotEmpty(appWorkPageMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, appWorkPageMation.getId());
        }
        AppWorkPageMation checkSysMenuAuthPointMation = appWorkPageDao.selectOne(queryWrapper);

        if (ObjectUtils.isEmpty(checkSysMenuAuthPointMation)) {
            String userId = inputObject.getLogParams().get("id").toString();
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(appWorkPageMation.getId())) {
                // 判断parentId是否发生变化，如果发生变化，则需要重新获取orderBy排序字段
                AppWorkPageMation appWorkPage = appWorkPageDao.selectById(appWorkPageMation.getId());
                if (!appWorkPage.getParentId().equals(appWorkPageMation.getParentId())) {
                    Integer nextOrderBy = appWorkPageDao.queryAppWorkPageMaxOrderBumByParentId(appWorkPageMation.getParentId());
                    appWorkPageMation.setOrderBy(nextOrderBy);
                }
                LOGGER.info("update app work page data, id is {}", appWorkPageMation.getId());
                DataCommonUtil.setCommonLastUpdateDataByGenericity(appWorkPageMation, userId);
                appWorkPageDao.updateById(appWorkPageMation);
            } else {
                Integer nextOrderBy = appWorkPageDao.queryAppWorkPageMaxOrderBumByParentId(appWorkPageMation.getParentId());
                appWorkPageMation.setOrderBy(nextOrderBy);
                appWorkPageMation.setState(State.START_NEW.getState());
                DataCommonUtil.setCommonDataByGenericity(appWorkPageMation, userId);
                LOGGER.info("insert app work page data, id is {}", appWorkPageMation.getId());
                appWorkPageDao.insert(appWorkPageMation);
            }
        } else {
            outputObject.setreturnMessage("存在相同的菜单，请进行更改.");
        }
    }

    /**
     * 获取菜单信息进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAppWorkPageMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        AppWorkPageMation appWorkPageMation = appWorkPageDao.selectById(id);
        outputObject.setBean(appWorkPageMation);
        outputObject.settotal(1);
    }

    private void setUpdateUserMation(InputObject inputObject, Map<String, Object> map) {
        map.put("last_update_id", inputObject.getLogParams().get("id"));
        map.put("last_update_time", DateUtil.getTimeAndToString());
    }

    /**
     * 删除菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteAppWorkPageMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        AppWorkPageMation appWorkPageMation = appWorkPageDao.selectById(id);
        int state = appWorkPageMation.getState();
        if (State.START_NEW.getState() == state || State.START_DOWN.getState() == state) {
            setUpdateUserMation(inputObject, map);
            // 新建或者下线的状态可以删除
            map.put("state", State.START_DELETE.getState());
            appWorkPageDao.editAppWorkPageStateById(map);
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
        }
    }

    /**
     * 菜单上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editAppWorkPageSortTopById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 根据同一级排序获取这条数据的上一条数据
        Map<String, Object> topBean = appWorkPageDao.queryAppWorkPageISTopByThisId(map);
        if (topBean == null) {
            outputObject.setreturnMessage("已经是最靠前的菜单，无法移动。");
        } else {
            map.put("orderBy", topBean.get("orderBy"));
            topBean.put("orderBy", topBean.get("thisOrderBy"));
            setUpdateUserMation(inputObject, map);
            appWorkPageDao.editAppWorkPageSortById(map);
            setUpdateUserMation(inputObject, topBean);
            appWorkPageDao.editAppWorkPageSortById(topBean);
        }
    }

    /**
     * 菜单下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editAppWorkPageSortLowerById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 根据同一级排序获取这条数据的下一条数据
        Map<String, Object> topBean = appWorkPageDao.queryAppWorkPageISLowerByThisId(map);
        if (topBean == null) {
            outputObject.setreturnMessage("已经是最靠后的菜单，无法移动。");
        } else {
            map.put("orderBy", topBean.get("orderBy"));
            topBean.put("orderBy", topBean.get("thisOrderBy"));
            setUpdateUserMation(inputObject, map);
            appWorkPageDao.editAppWorkPageSortById(map);
            setUpdateUserMation(inputObject, topBean);
            appWorkPageDao.editAppWorkPageSortById(topBean);
        }
    }

    /**
     * 菜单上线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editAppWorkPageUpById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        AppWorkPageMation appWorkPageMation = appWorkPageDao.selectById(id);
        int state = appWorkPageMation.getState();
        if (State.START_NEW.getState() == state || State.START_DOWN.getState() == state) {
            setUpdateUserMation(inputObject, map);
            // 新建或者下线的状态可以上线
            map.put("state", State.START_UP.getState());
            appWorkPageDao.editAppWorkPageStateById(map);
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
        }
    }

    /**
     * 菜单下线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editAppWorkPageDownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        AppWorkPageMation appWorkPageMation = appWorkPageDao.selectById(id);
        if (State.START_UP.getState() == appWorkPageMation.getState()) {
            setUpdateUserMation(inputObject, map);
            // 上线状态可以下线
            map.put("state", State.START_DOWN.getState());
            appWorkPageDao.editAppWorkPageStateById(map);
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
        }
    }

    /**
     * 根据父目录id获取子目录集合
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAppWorkPageListByParentId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String parentId = map.get("parentId").toString();
        String desktopId = map.get("desktopId").toString();
        QueryWrapper<AppWorkPageMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(AppWorkPageMation.class, "parentId"), parentId);
        queryWrapper.ne(MybatisPlusUtil.getDeclaredFieldsInfo2(AppWorkPageMation.class, "state"), State.START_DELETE.getState());
        if (StringUtils.isNotEmpty(desktopId)) {
            queryWrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(AppWorkPageMation.class, "desktopId"), desktopId);
        }
        List<AppWorkPageMation> appWorkPageMationList = appWorkPageDao.selectList(queryWrapper);
        appWorkPageMationList.forEach(bean -> {
            bean.setName(bean.getTitle());
        });
        outputObject.setBeans(appWorkPageMationList);
        outputObject.settotal(appWorkPageMationList.size());
    }

}
