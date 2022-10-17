/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.win.dao.SysEveWinDao;
import com.skyeye.eve.entity.userauth.win.SysEveWinMation;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.win.service.SysEveWinService;
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
 * @ClassName: SysEveWinServiceImpl
 * @Description: 服务信息管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:26
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysEveWinServiceImpl implements SysEveWinService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysEveWinServiceImpl.class);

    @Autowired
    private SysEveWinDao sysEveWinDao;

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 获取服务信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryWinMationList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        List<Map<String, Object>> beans = sysEveWinDao.queryWinMationList(commonPageInfo);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增/编辑服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertWinMation(InputObject inputObject, OutputObject outputObject) {
        SysEveWinMation sysEveWinMation = inputObject.getParams(SysEveWinMation.class);
        // 1.根据条件进行校验
        QueryWrapper<SysEveWinMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SysEveWinMation::getSysName), sysEveWinMation.getSysName());
        if (StringUtils.isNotEmpty(sysEveWinMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, sysEveWinMation.getId());
        }
        SysEveWinMation checkSysEveWin = sysEveWinDao.selectOne(queryWrapper);

        if (ObjectUtils.isEmpty(checkSysEveWin)) {
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(sysEveWinMation.getId())) {
                LOGGER.info("update sys win data, id is {}", sysEveWinMation.getId());
                sysEveWinDao.updateById(sysEveWinMation);
            } else {
                DataCommonUtil.setId(sysEveWinMation);
                LOGGER.info("insert sys win data, id is {}", sysEveWinMation.getId());
                sysEveWinDao.insert(sysEveWinMation);
            }
        } else {
            outputObject.setreturnMessage("该服务已存在，请更换.");
        }
    }

    /**
     * 编辑服务信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryWinMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        SysEveWinMation sysEveWinMation = sysEveWinDao.selectById(id);
        outputObject.setBean(sysEveWinMation);
        outputObject.settotal(1);
    }

    /**
     * 删除服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteWinMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = sysEveWinDao.queryChildMationById(id);
        if (Integer.parseInt(bean.get("menuNum").toString()) > 0) {
            outputObject.setreturnMessage("该服务存在功能菜单，请先进行菜单移除操作。");
        } else {
            sysEveWinDao.deleteById(id);
        }
    }

    /**
     * 获取所有的服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveWinList(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> beans = sysEveWinDao.querySysEveWinList();
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

}
