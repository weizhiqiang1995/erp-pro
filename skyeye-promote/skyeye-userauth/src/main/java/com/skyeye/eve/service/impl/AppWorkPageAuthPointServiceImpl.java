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
import com.skyeye.eve.dao.AppWorkPageAuthPointDao;
import com.skyeye.eve.entity.userauth.menu.AppWorkPageAuthPointMation;
import com.skyeye.eve.service.AppWorkPageAuthPointService;
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
 * @ClassName: AppWorkPageAuthPointServiceImpl
 * @Description: APP菜单权限点管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class AppWorkPageAuthPointServiceImpl implements AppWorkPageAuthPointService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppWorkPageAuthPointServiceImpl.class);

    @Autowired
    private AppWorkPageAuthPointDao appWorkPageAuthPointDao;

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
            for (SysEveMenuAuthPointServiceImpl.Type bean : SysEveMenuAuthPointServiceImpl.Type.values()) {
                if (bean.getType() == type) {
                    return bean.getName();
                }
            }
            return "";
        }
    }

    /**
     * 根据菜单id获取菜单权限点列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAppWorkPageAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = appWorkPageAuthPointDao.queryAppWorkPageAuthPointListByMenuId(map);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        beans.forEach(bean -> {
            bean.put("typeName", SysEveMenuAuthPointServiceImpl.Type.getTypeName(Integer.parseInt(bean.get("type").toString())));
        });
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 新增/编辑APP菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeAppWorkPageAuthPointMation(InputObject inputObject, OutputObject outputObject) {
        AppWorkPageAuthPointMation appWorkPageAuthPointMation = inputObject.getParams(AppWorkPageAuthPointMation.class);
        // 1.根据条件进行校验
        QueryWrapper<AppWorkPageAuthPointMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.toColumns(AppWorkPageAuthPointMation::getTitle), appWorkPageAuthPointMation.getTitle())
                .or().eq(MybatisPlusUtil.toColumns(AppWorkPageAuthPointMation::getAuthMenu), appWorkPageAuthPointMation.getAuthMenu()));
        queryWrapper.eq(MybatisPlusUtil.toColumns(AppWorkPageAuthPointMation::getMenuId), appWorkPageAuthPointMation.getMenuId());
        if (StringUtils.isNotEmpty(appWorkPageAuthPointMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, appWorkPageAuthPointMation.getId());
        }
        AppWorkPageAuthPointMation checkAppWorkPageAuthPoint = appWorkPageAuthPointDao.selectOne(queryWrapper);

        if (ObjectUtils.isEmpty(checkAppWorkPageAuthPoint)) {
            String userId = inputObject.getLogParams().get("id").toString();
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(appWorkPageAuthPointMation.getId())) {
                LOGGER.info("update app menu auth point data, id is {}", appWorkPageAuthPointMation.getId());
                DataCommonUtil.setCommonLastUpdateDataByGenericity(appWorkPageAuthPointMation, userId);
                appWorkPageAuthPointDao.updateById(appWorkPageAuthPointMation);
            } else {
                appWorkPageAuthPointMation.setMenuNum(String.valueOf(DateUtil.getTimeStampAndToString()));
                DataCommonUtil.setCommonDataByGenericity(appWorkPageAuthPointMation, userId);
                LOGGER.info("insert app menu auth point data, id is {}", appWorkPageAuthPointMation.getId());
                appWorkPageAuthPointDao.insert(appWorkPageAuthPointMation);
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
    public void queryAppWorkPageAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        AppWorkPageAuthPointMation appWorkPageAuthPointMation = appWorkPageAuthPointDao.selectById(id);
        outputObject.setBean(appWorkPageAuthPointMation);
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
    public void deleteAppWorkPageAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        appWorkPageAuthPointDao.deleteById(id);
    }

}
