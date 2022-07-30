/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SysEveMenuAuthPointDao;
import com.skyeye.eve.entity.userauth.menu.SysMenuAuthPointMation;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.eve.service.SysEveMenuAuthPointService;
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
 * @ClassName: SysEveMenuAuthPointServiceImpl
 * @Description: 菜单权限点管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/23 19:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysEveMenuAuthPointServiceImpl implements SysEveMenuAuthPointService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysEveMenuAuthPointServiceImpl.class);

    @Autowired
    private SysEveMenuAuthPointDao sysEveMenuAuthPointDao;

    @Autowired
    private IAuthUserService iAuthUserService;

    public enum Type {
        AUTH_POINT(1, "权限点"),
        DATA_GROUP(2, "数据分组"),
        DATA_POINT(3, "数据权限");
        private int type;
        private String name;

        Type(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public static String getTypeName(int type) {
            for (Type bean : Type.values()) {
                if (bean.getType() == type) {
                    return bean.getName();
                }
            }
            return "";
        }
    }

    /**
     * 获取菜单权限点列表根据菜单id
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveMenuAuthPointDao.querySysEveMenuAuthPointListByMenuId(map);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        beans.forEach(bean -> {
            bean.put("typeName", Type.getTypeName(Integer.parseInt(bean.get("type").toString())));
        });
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());

    }

    /**
     * 新增/编辑菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject) {
        SysMenuAuthPointMation sysMenuAuthPointMation = inputObject.getParams(SysMenuAuthPointMation.class);
        // 1.根据条件进行校验
        QueryWrapper<SysMenuAuthPointMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(SysMenuAuthPointMation.class, "authMenuName"), sysMenuAuthPointMation.getAuthMenuName())
                .or().eq(MybatisPlusUtil.getDeclaredFieldsInfo2(SysMenuAuthPointMation.class, "authMenu"), sysMenuAuthPointMation.getAuthMenu()));
        queryWrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(SysMenuAuthPointMation.class, "menuId"), sysMenuAuthPointMation.getMenuId());
        if (StringUtils.isNotEmpty(sysMenuAuthPointMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, sysMenuAuthPointMation.getId());
        }
        SysMenuAuthPointMation checkSysMenuAuthPoint = sysEveMenuAuthPointDao.selectOne(queryWrapper);

        if (ObjectUtils.isEmpty(checkSysMenuAuthPoint)) {
            String userId = inputObject.getLogParams().get("id").toString();
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(sysMenuAuthPointMation.getId())) {
                LOGGER.info("update menu auth point data, id is {}", sysMenuAuthPointMation.getId());
                DataCommonUtil.setCommonLastUpdateDataByGenericity(sysMenuAuthPointMation, userId);
                sysEveMenuAuthPointDao.updateById(sysMenuAuthPointMation);
            } else {
                sysMenuAuthPointMation.setMenuNum(String.valueOf(DateUtil.getTimeStampAndToString()));
                DataCommonUtil.setCommonDataByGenericity(sysMenuAuthPointMation, userId);
                LOGGER.info("insert menu auth point data, id is {}", sysMenuAuthPointMation.getId());
                sysEveMenuAuthPointDao.insert(sysMenuAuthPointMation);
            }
        } else {
            outputObject.setreturnMessage("该菜单下已存在该名称的权限点，请进行更改.");
        }
    }

    /**
     * 编辑菜单权限点时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveMenuAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        SysMenuAuthPointMation sysMenuAuthPointMation = sysEveMenuAuthPointDao.selectById(id);
        outputObject.setBean(sysMenuAuthPointMation);
        outputObject.settotal(1);
    }

    /**
     * 删除菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        sysEveMenuAuthPointDao.deleteById(id);
    }

}
