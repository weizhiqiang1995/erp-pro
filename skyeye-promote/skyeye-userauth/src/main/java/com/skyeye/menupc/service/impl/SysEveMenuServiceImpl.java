/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.eve.entity.userauth.menu.SysMenuMation;
import com.skyeye.eve.entity.userauth.menu.SysMenuQueryDo;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.menupc.dao.SysEveMenuDao;
import com.skyeye.menupc.service.SysEveMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SysEveMenuServiceImpl
 * @Description: 菜单管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/3 11:24
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysEveMenuServiceImpl implements SysEveMenuService {

    @Autowired
    private SysEveMenuDao sysEveMenuDao;

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 菜单链接打开类型，父菜单默认为1.1：打开iframe，2：打开html。
     */
    public static final Integer SYS_MENU_OPEN_TYPE_IS_IFRAME = 1;
    public static final Integer SYS_MENU_OPEN_TYPE_IS_HTML = 2;

    /**
     * 菜单类型
     */
    public static final String SYS_MENU_TYPE_IS_IFRAME = "win";
    public static final String SYS_MENU_TYPE_IS_HTML = "html";

    /**
     * 获取菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysMenuList(InputObject inputObject, OutputObject outputObject) {
        SysMenuQueryDo sysMenuQuery = inputObject.getParams(SysMenuQueryDo.class);
        Page pages = PageHelper.startPage(sysMenuQuery.getPage(), sysMenuQuery.getLimit());
        List<Map<String, Object>> beans = sysEveMenuDao.querySysMenuList(sysMenuQuery);
        List<String> ids = beans.stream().map(bean -> bean.get("id").toString()).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        // 查询子节点信息(包含当前节点)
        List<String> childIds = sysEveMenuDao.queryAllChildIdsByParentId(ids);
        beans = sysEveMenuDao.querySysMenuListByIds(childIds);

        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        beans.forEach(bean -> {
            bean.put("lay_is_open", true);
        });
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 添加菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysMenuMation(InputObject inputObject, OutputObject outputObject) {
        SysMenuMation sysMenuMation = inputObject.getParams(SysMenuMation.class);
        // 设置菜单链接打开类型
        setOpenType(sysMenuMation);
        // 设置菜单级别
        setMenuLevel(sysMenuMation);
        // 设置培训序号
        setOrderNum(sysMenuMation);
        DataCommonUtil.setId(sysMenuMation);
        sysEveMenuDao.insert(sysMenuMation);
    }

    private void setOrderNum(SysMenuMation sysMenuMation) {
        Map<String, Object> orderNum = sysEveMenuDao.querySysMenuAfterOrderBumByParentId(sysMenuMation.getParentId());
        if (orderNum == null) {
            sysMenuMation.setOrderNum(0);
        } else {
            if (orderNum.containsKey("orderNum")) {
                sysMenuMation.setOrderNum(Integer.parseInt(orderNum.get("orderNum").toString()) + 1);
            } else {
                sysMenuMation.setOrderNum(0);
            }
        }
    }

    private void setMenuLevel(SysMenuMation sysMenuMation) {
        if ("0".equals(sysMenuMation.getParentId())) {
            sysMenuMation.setMenuLevel(0);
        } else {
            sysMenuMation.setMenuLevel(CommonNumConstants.NUM_ONE);
        }
    }

    private void setOpenType(SysMenuMation sysMenuMation) {
        if (SYS_MENU_TYPE_IS_IFRAME.equals(sysMenuMation.getMenuType())) {
            // iframe
            sysMenuMation.setOpenType(SYS_MENU_OPEN_TYPE_IS_IFRAME);
        } else if (SYS_MENU_TYPE_IS_HTML.equals(sysMenuMation.getMenuType())) {
            // html
            sysMenuMation.setOpenType(SYS_MENU_OPEN_TYPE_IS_HTML);
        }
    }

    /**
     * 根据父菜单ID查看子菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysMenuMationBySimpleLevel(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveMenuDao.querySysMenuMationBySimpleLevel(map);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 编辑菜单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysMenuMationById(InputObject inputObject, OutputObject outputObject) {
        SysMenuMation sysMenuMation = inputObject.getParams(SysMenuMation.class);
        // 设置菜单链接打开类型
        setOpenType(sysMenuMation);
        // 设置菜单级别
        setMenuLevel(sysMenuMation);
        Map<String, Object> oldParent = sysEveMenuDao.querySysEveMenuBySysId(sysMenuMation.getId());
        if (!oldParent.get("parentId").toString().equals(sysMenuMation.getParentId())) {
            // 修改之后不再是之前父类的子菜单，设置培训序号
            setOrderNum(sysMenuMation);
        }
        sysEveMenuDao.updateById(sysMenuMation);
    }

    /**
     * 删除菜单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysMenuMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        // 判断菜单有没有角色使用，没有则可以删除
        Map<String, Object> menuBean = sysEveMenuDao.queryUseThisMenuRoleById(map);
        if (menuBean == null) {
            // 删除子菜单
            sysEveMenuDao.deleteSysMenuChildMationById(map);
            // 删除自身菜单
            sysEveMenuDao.deleteById(id);
        } else {
            if (Integer.parseInt(menuBean.get("roleNum").toString()) == 0) {
                // 删除子菜单
                sysEveMenuDao.deleteSysMenuChildMationById(map);
                // 删除自身菜单
                sysEveMenuDao.deleteById(id);
            } else {
                outputObject.setreturnMessage("该菜单正在被一个或多个角色使用，无法删除。");
            }
        }
    }

    /**
     * 菜单展示顺序上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysEveMenuSortTopById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 根据同一级排序获取这条数据的上一条数据
        Map<String, Object> topBean = sysEveMenuDao.querySysEveMenuISTopByThisId(map);
        if (topBean == null) {
            outputObject.setreturnMessage("已经是最靠前菜单，无法移动。");
        } else {
            map.put("orderNum", topBean.get("orderNum"));
            topBean.put("orderNum", topBean.get("thisOrderNum"));
            setUpdateUserMation(inputObject, map);
            sysEveMenuDao.editSysEveMenuSortTopById(map);
            setUpdateUserMation(inputObject, topBean);
            sysEveMenuDao.editSysEveMenuSortTopById(topBean);
        }
    }

    private void setUpdateUserMation(InputObject inputObject, Map<String, Object> map) {
        map.put("lastUpdateId", inputObject.getLogParams().get("id"));
        map.put("lastUpdateTime", DateUtil.getTimeAndToString());
    }

    /**
     * 菜单展示顺序下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysEveMenuSortLowerById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 根据同一级排序获取这条数据的下一条数据
        Map<String, Object> topBean = sysEveMenuDao.querySysEveMenuISLowerByThisId(map);
        if (topBean == null) {
            outputObject.setreturnMessage("已经是最靠后菜单，无法移动。");
        } else {
            map.put("orderNum", topBean.get("orderNum"));
            topBean.put("orderNum", topBean.get("thisOrderNum"));
            setUpdateUserMation(inputObject, map);
            sysEveMenuDao.editSysEveMenuSortLowerById(map);
            setUpdateUserMation(inputObject, topBean);
            sysEveMenuDao.editSysEveMenuSortLowerById(topBean);
        }
    }

    /**
     * 系统菜单详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveMenuBySysId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = sysEveMenuDao.querySysEveMenuBySysId(id);
        outputObject.setBean(bean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

}
