/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SysEveDesktopDao;
import com.skyeye.eve.entity.userauth.desktop.SysEveDesktopMation;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.eve.service.SysEveDesktopService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysEveDesktopServiceImpl implements SysEveDesktopService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysEveDesktopServiceImpl.class);

    @Autowired
    private SysEveDesktopDao sysEveDesktopDao;

    @Autowired
    private IAuthUserService iAuthUserService;

    public enum State {
        START_DELETE(0, "删除"),
        START_NEW(1, "启用"),
        START_UP(2, "禁用");
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
     * 查出所有桌面列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDesktopList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        List<Map<String, Object>> beans = sysEveDesktopDao.querySysDesktopList(commonPageInfo);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增/编辑桌面信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeSysEveDesktopMation(InputObject inputObject, OutputObject outputObject) {
        SysEveDesktopMation sysEveDesktopMation = inputObject.getParams(SysEveDesktopMation.class);
        // 1.根据条件进行校验
        QueryWrapper<SysEveDesktopMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(MybatisPlusUtil.toColumns(SysEveDesktopMation::getState), State.START_DELETE.getState());
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.toColumns(SysEveDesktopMation::getDesktopName), sysEveDesktopMation.getDesktopName())
                .or().eq(MybatisPlusUtil.toColumns(SysEveDesktopMation::getDesktopCode), sysEveDesktopMation.getDesktopCode()));
        if (StringUtils.isNotEmpty(sysEveDesktopMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, sysEveDesktopMation.getId());
        }
        SysEveDesktopMation checkSysEveDesktop = sysEveDesktopDao.selectOne(queryWrapper);

        if (ObjectUtils.isEmpty(checkSysEveDesktop)) {
            String userId = inputObject.getLogParams().get("id").toString();
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(sysEveDesktopMation.getId())) {
                LOGGER.info("update sys desktop data, id is {}", sysEveDesktopMation.getId());
                DataCommonUtil.setCommonLastUpdateDataByGenericity(sysEveDesktopMation, userId);
                sysEveDesktopDao.updateById(sysEveDesktopMation);
            } else {
                // 获取序号
                Integer nextOrderBy = sysEveDesktopDao.querySysEveDesktopMaxOrderBum();
                sysEveDesktopMation.setOrderBy(nextOrderBy);
                DataCommonUtil.setCommonDataByGenericity(sysEveDesktopMation, userId);
                LOGGER.info("insert sys desktop data, id is {}", sysEveDesktopMation.getId());
                sysEveDesktopDao.insert(sysEveDesktopMation);
            }
        } else {
            outputObject.setreturnMessage("该桌面名称已存在，请更换.");
        }
    }

    /**
     * 删除桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopStateAndMenuNumById(id);
        if ("0".equals(bean.get("allNum").toString())) {
            // 该桌面下没有菜单可以删除
            sysEveDesktopDao.editSysDesktopStateById(id, State.START_DELETE.getState());
        } else {
            outputObject.setreturnMessage("该桌面下包含有子菜单，无法删除.");
        }
    }

    /**
     * 通过id查找对应的桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void selectSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        SysEveDesktopMation sysEveDesktop = sysEveDesktopDao.selectById(map.get("id").toString());
        outputObject.setBean(sysEveDesktop);
        outputObject.settotal(1);
    }

    /**
     * 桌面上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDesktopMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 获取当前数据的同级分类下的上一条数据
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopUpMationById(map);
        if (CollectionUtils.isEmpty(bean)) {
            outputObject.setreturnMessage("当前桌面名称已经是首位，无须进行上移。");
        } else {
            // 进行位置交换
            map.put("upOrderBy", bean.get("prevOrderBy"));
            bean.put("upOrderBy", bean.get("thisOrderBy"));
            sysEveDesktopDao.editSysDesktopMationOrderNumUpById(map);
            sysEveDesktopDao.editSysDesktopMationOrderNumUpById(bean);
        }
    }

    /**
     * 桌面下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDesktopMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 获取当前数据的同级分类下的下一条数据
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopDownMationById(map);
        if (CollectionUtils.isEmpty(bean)) {
            outputObject.setreturnMessage("当前桌面名称已经是末位，无须进行下移。");
        } else {
            //进行位置交换
            map.put("upOrderBy", bean.get("prevOrderBy"));
            bean.put("upOrderBy", bean.get("thisOrderBy"));
            sysEveDesktopDao.editSysDesktopMationOrderNumUpById(map);
            sysEveDesktopDao.editSysDesktopMationOrderNumUpById(bean);
        }
    }

    /**
     * 获取全部的桌面用于系统菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllSysDesktopList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String language = map.get("language").toString();
        List<Map<String, Object>> beans = sysEveDesktopDao.queryAllSysDesktopList(map);
        Map<String, Object> m = new HashMap<>();
        m.put("name", Constants.LANGUAGE_ZH.equals(language) ? "默认桌面" : "Default desktop");
        m.put("id", "winfixedpage00000000");
        beans.add(0, m);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 一键移除所有菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void removeAllSysEveMenuByDesktopId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("desktopId", "winfixedpage00000000");
        sysEveDesktopDao.removeAllSysEveMenuByDesktopId(map);
    }

}
