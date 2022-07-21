/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveDesktopDao;
import com.skyeye.eve.service.SysEveDesktopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysEveDesktopServiceImpl implements SysEveDesktopService {

    @Autowired
    private SysEveDesktopDao sysEveDesktopDao;

    public static enum STATE {
        START_DELETE(0, "删除"),
        START_NEW(1, "新建"),
        START_UP(2, "上线"),
        START_DOWN(3, "下线");
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
     * 查出所有桌面列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDesktopList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveDesktopDao.querySysDesktopList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysDesktopMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveDesktopDao.checkSysDesktopMation(map);
        if (!CollectionUtils.isEmpty(bean)) {
            outputObject.setreturnMessage("该桌面名称已存在，请更换");
        } else {
            Map<String, Object> itemCount = sysEveDesktopDao.querySysDesktopBySimpleLevel(map);
            int thisOrderBy = Integer.parseInt(itemCount.get("simpleNum").toString()) + 1;
            map.put("orderBy", thisOrderBy);
            // 默认新建
            map.put("state", STATE.START_NEW.getState());
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysEveDesktopDao.insertSysDesktopMation(map);
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
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopStateAndMenuNumById(map);
        Integer state = Integer.parseInt(bean.get("state").toString());
        if (state == STATE.START_NEW.getState() || state == STATE.START_DOWN.getState()) {
            // 新建或者下线可以删除
            if ("0".equals(bean.get("allNum").toString())) {
                // 该桌面下没有菜单可以删除
                sysEveDesktopDao.editSysDesktopStateById(id, STATE.START_DELETE.getState());
            } else {
                outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
            }
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
        }
    }

    /**
     * 上线桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void updateUpSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopStateById(map);
        Integer state = Integer.parseInt(bean.get("state").toString());
        if (state == STATE.START_NEW.getState() || state == STATE.START_DOWN.getState()) {
            // 新建或者下线可以上线
            sysEveDesktopDao.editSysDesktopStateById(id, STATE.START_UP.getState());
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
        }
    }

    /**
     * 下线桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void updateDownSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopStateById(map);
        Integer state = Integer.parseInt(bean.get("state").toString());
        if (state == STATE.START_UP.getState()) {
            // 上线状态可以下线
            sysEveDesktopDao.editSysDesktopStateById(id, STATE.START_DOWN.getState());
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
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
        Map<String, Object> bean = sysEveDesktopDao.selectSysDesktopById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 通过id编辑对应的桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDesktopMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopStateById(map);
        Integer state = Integer.parseInt(bean.get("state").toString());
        if (state == STATE.START_NEW.getState() || state == STATE.START_DOWN.getState()) {
            // 新建或者下线可以编辑
            Map<String, Object> item = sysEveDesktopDao.checkSysDesktopMation(map);
            if (!CollectionUtils.isEmpty(item)) {
                outputObject.setreturnMessage("该桌面名称已存在，请更换");
            } else {
                map.put("lastUpdateId", inputObject.getLogParams().get("id"));
                map.put("lastUpdateTime", DateUtil.getTimeAndToString());
                sysEveDesktopDao.editSysDesktopMationById(map);
            }
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
        }
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
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopUpMationById(map);//获取当前数据的同级分类下的上一条数据
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
        Map<String, Object> bean = sysEveDesktopDao.querySysDesktopDownMationById(map);//获取当前数据的同级分类下的下一条数据
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
