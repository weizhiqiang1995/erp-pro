/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dsform.entity.DsFormPage;
import com.skyeye.dsform.service.DsFormPageService;
import com.skyeye.exception.CustomException;
import com.skyeye.menupc.dao.SysEveMenuDao;
import com.skyeye.menupc.entity.SysMenu;
import com.skyeye.menupc.entity.SysMenuQueryDo;
import com.skyeye.menupc.service.SysEveMenuService;
import com.skyeye.server.entity.ServiceBeanCustom;
import com.skyeye.server.service.ServiceBeanCustomService;
import com.skyeye.win.entity.SysDesktop;
import com.skyeye.win.entity.SysWin;
import com.skyeye.win.service.SysEveDesktopService;
import com.skyeye.win.service.SysEveWinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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
public class SysEveMenuServiceImpl extends SkyeyeBusinessServiceImpl<SysEveMenuDao, SysMenu> implements SysEveMenuService {

    @Autowired
    private SysEveMenuDao sysEveMenuDao;

    @Autowired
    private SysEveDesktopService sysEveDesktopService;

    @Autowired
    private SysEveWinService sysEveWinService;

    @Autowired
    private DsFormPageService dsFormPageService;

    @Autowired
    private ServiceBeanCustomService serviceBeanCustomService;

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

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        SysMenuQueryDo sysMenuQuery = inputObject.getParams(SysMenuQueryDo.class);
        List<Map<String, Object>> beans = sysEveMenuDao.querySysMenuList(sysMenuQuery);
        List<String> ids = beans.stream().map(bean -> bean.get("id").toString()).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(ids)) {
            return beans;
        }
        // 查询子节点信息(包含当前节点)
        List<String> childIds = sysEveMenuDao.queryAllChildIdsByParentId(ids);
        beans = selectMapByIds(childIds).values().stream().map(bean -> BeanUtil.beanToMap(bean)).collect(Collectors.toList());
        beans = beans.stream()
            .sorted(Comparator.comparing(bean -> Integer.parseInt(bean.get("orderNum").toString()))).collect(Collectors.toList());
        beans.forEach(bean -> {
            bean.put("lay_is_open", true);
        });
        return beans;
    }

    @Override
    public void createPrepose(SysMenu entity) {
        // 设置菜单链接打开类型
        setOpenType(entity);
        // 设置菜单级别
        setMenuLevel(entity);
        // 设置培训序号
        setOrderNum(entity);
    }

    @Override
    public void updatePrepose(SysMenu entity) {
        // 设置菜单链接打开类型
        setOpenType(entity);
        // 设置菜单级别
        setMenuLevel(entity);
        SysMenu oldParent = selectById(entity.getId());
        if (!oldParent.getParentId().equals(entity.getParentId())) {
            // 修改之后不再是之前父类的子菜单，设置培训序号
            setOrderNum(entity);
        }
    }

    private void setOrderNum(SysMenu sysMenu) {
        Map<String, Object> orderNum = sysEveMenuDao.querySysMenuAfterOrderBumByParentId(sysMenu.getParentId());
        if (orderNum == null) {
            sysMenu.setOrderNum(0);
        } else {
            if (orderNum.containsKey("orderNum")) {
                sysMenu.setOrderNum(Integer.parseInt(orderNum.get("orderNum").toString()) + 1);
            } else {
                sysMenu.setOrderNum(0);
            }
        }
    }

    private void setMenuLevel(SysMenu sysMenu) {
        if ("0".equals(sysMenu.getParentId())) {
            sysMenu.setLevel(0);
        } else {
            sysMenu.setLevel(CommonNumConstants.NUM_ONE);
        }
    }

    private void setOpenType(SysMenu sysMenu) {
        if (SYS_MENU_TYPE_IS_IFRAME.equals(sysMenu.getType())) {
            // iframe
            sysMenu.setOpenType(SYS_MENU_OPEN_TYPE_IS_IFRAME);
        } else if (SYS_MENU_TYPE_IS_HTML.equals(sysMenu.getType())) {
            // html
            sysMenu.setOpenType(SYS_MENU_OPEN_TYPE_IS_HTML);
        }
    }

    @Override
    public void deletePreExecution(String id) {
        // 判断菜单有没有角色使用，没有则可以删除
        Map<String, Object> useMenuBean = sysEveMenuDao.queryUseThisMenuRoleById(id);
        if (CollectionUtil.isNotEmpty(useMenuBean)) {
            if (Integer.parseInt(useMenuBean.get("roleNum").toString()) > 0) {
                throw new CustomException("该菜单正在被一个或多个角色使用，无法删除。");
            }
        }
    }

    @Override
    public void deletePostpose(String id) {
        // 删除子菜单
        deleteByParentId(id);
    }

    private void deleteByParentId(String parentId) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(SysMenu::getParentId), parentId);
        remove(queryWrapper);
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
        // 桌面信息
        List<String> desktopIdList = beans.stream()
            .filter(bean -> StrUtil.isNotEmpty(bean.get("desktopId").toString())).map(bean -> bean.get("desktopId").toString()).distinct().collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(desktopIdList)) {
            Map<String, SysDesktop> sysDesktopMap = sysEveDesktopService.selectMapByIds(desktopIdList);
            beans.forEach(sysMenu -> {
                if (StrUtil.isNotEmpty(sysMenu.get("desktopId").toString())) {
                    sysMenu.put("sysDesktop", sysDesktopMap.get(sysMenu.get("desktopId").toString()));
                }
            });
        }
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @Override
    public SysMenu selectById(String id) {
        SysMenu sysMenu = super.selectById(id);

        SysDesktop sysDesktop = sysEveDesktopService.selectById(sysMenu.getDesktopId());
        sysMenu.setSysDesktop(sysDesktop);

        SysWin sysWin = sysEveWinService.selectById(sysMenu.getSysWinId());
        sysMenu.setSysWin(sysWin);

        if (!sysMenu.getPageType()) {
            // 表单布局
            DsFormPage dsFormPage = dsFormPageService.getDataFromDb(sysMenu.getPageUrl());
            ServiceBeanCustom serviceBeanCustom = serviceBeanCustomService.selectById(dsFormPage.getClassName());
            dsFormPage.setServiceBeanCustom(serviceBeanCustom);
            sysMenu.setDsFormPage(dsFormPage);
        }
        if (!sysMenu.getParentId().equals("0")) {
            sysMenu.setParentMenu(selectById(sysMenu.getParentId()));
        }
        return sysMenu;
    }

    /**
     * 根据id批量查询数据
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysMenu> selectByIds(String... ids) {
        List<SysMenu> sysMenuList = super.selectByIds(ids);
        // 桌面信息
        List<String> desktopIdList = sysMenuList.stream()
            .filter(bean -> StrUtil.isNotEmpty(bean.getDesktopId())).map(SysMenu::getDesktopId).distinct().collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(desktopIdList)) {
            Map<String, SysDesktop> sysDesktopMap = sysEveDesktopService.selectMapByIds(desktopIdList);
            sysMenuList.forEach(sysMenu -> {
                if (StrUtil.isNotEmpty(sysMenu.getDesktopId())) {
                    sysMenu.setSysDesktop(sysDesktopMap.get(sysMenu.getDesktopId()));
                }
            });
        }
        // 服务信息
        List<String> sysWinList = sysMenuList.stream()
            .filter(bean -> StrUtil.isNotEmpty(bean.getSysWinId())).map(SysMenu::getSysWinId).distinct().collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(sysWinList)) {
            Map<String, SysWin> sysWinMap = sysEveWinService.selectMapByIds(sysWinList);
            sysMenuList.forEach(sysMenu -> {
                if (StrUtil.isNotEmpty(sysMenu.getSysWinId())) {
                    sysMenu.setSysWin(sysWinMap.get(sysMenu.getSysWinId()));
                }
            });
        }
        return sysMenuList;
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
            sysEveMenuDao.editSysEveMenuSortTopById(map);
            sysEveMenuDao.editSysEveMenuSortTopById(topBean);
            refreshCache(map.get("id").toString());
            refreshCache(topBean.get("id").toString());
        }
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
            sysEveMenuDao.editSysEveMenuSortLowerById(map);
            sysEveMenuDao.editSysEveMenuSortLowerById(topBean);
            refreshCache(map.get("id").toString());
            refreshCache(topBean.get("id").toString());
        }
    }

}
