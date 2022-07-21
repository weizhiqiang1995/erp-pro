/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveWinTypeDao;
import com.skyeye.eve.service.SysEveWinTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class SysEveWinTypeServiceImpl implements SysEveWinTypeService {

    @Autowired
    private SysEveWinTypeDao sysEveWinTypeDao;

    /**
     * 获取分类列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysWinTypeList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveWinTypeDao.querySysWinTypeList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取所有一级分类展示为下拉选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void querySysWinFirstTypeList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveWinTypeDao.querySysWinFirstTypeList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 新增系统分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysWinTypeMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinTypeDao.querySysWinTypeByNameANDLevel(map);
        if (!CollectionUtils.isEmpty(bean)) {
            outputObject.setreturnMessage("该分类名称已存在，请更换");
        } else {
            // 获取同级分类数量
            Map<String, Object> itemCount = sysEveWinTypeDao.querySysWinTypeBySimpleLevel(map);
            int thisOrderBy = Integer.parseInt(itemCount.get("simpleNum").toString()) + 1;
            map.put("orderBy", thisOrderBy);
            // 默认新建
            map.put("state", "1");
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysEveWinTypeDao.insertSysWinTypeMation(map);
        }
    }

    /**
     * 编辑系统分类时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysWinTypeMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinTypeDao.querySysWinTypeMationToEditById(map);
        outputObject.setBean(bean);
    }

    /**
     * 编辑系统分类时
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysWinTypeMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinTypeDao.querySysWinTypeByNameANDLevelAndId(map);
        if (!CollectionUtils.isEmpty(bean)) {
            outputObject.setreturnMessage("该分类名称已存在，请更换");
        } else {
            bean = sysEveWinTypeDao.querySysWinTypeParentMationById(map);
            if (Integer.parseInt(bean.get("winNum").toString()) > 0 && !map.get("parentId").toString().equals(map.get("pId").toString())) {
                outputObject.setreturnMessage("该分类分类下存在系统，无法进行级别修改");
            } else {
                sysEveWinTypeDao.editSysWinTypeMationById(map);
            }
        }
    }

    /**
     * 获取所有不是当前分类的一级分类展示为下拉选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysWinFirstTypeListNotIsThisId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveWinTypeDao.querySysWinFirstTypeListNotIsThisId(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 删除系统分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysWinTypeMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        sysEveWinTypeDao.deleteSysWinTypeMationById(map);
        sysEveWinTypeDao.deleteSysWinTypeChildMationById(map);
    }

    /**
     * 系统分类上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinTypeDao.querySysWinTypeUpMationById(map);//获取当前数据的同级分类下的上一条数据
        if (bean == null) {
            outputObject.setreturnMessage("当前分类已经是首位，无须进行上移。");
        } else {
            //进行位置交换
            map.put("upOrderBy", bean.get("prevOrderBy"));
            bean.put("upOrderBy", bean.get("thisOrderBy"));
            sysEveWinTypeDao.editSysWinTypeMationOrderNumUpById(map);
            sysEveWinTypeDao.editSysWinTypeMationOrderNumUpById(bean);
        }
    }

    /**
     * 系统分类下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinTypeDao.querySysWinTypeDownMationById(map);//获取当前数据的同级分类下的下一条数据
        if (bean == null) {
            outputObject.setreturnMessage("当前分类已经是末位，无须进行下移。");
        } else {
            //进行位置交换
            map.put("upOrderBy", bean.get("prevOrderBy"));
            bean.put("upOrderBy", bean.get("thisOrderBy"));
            sysEveWinTypeDao.editSysWinTypeMationOrderNumUpById(map);
            sysEveWinTypeDao.editSysWinTypeMationOrderNumUpById(bean);
        }
    }

    /**
     * 系统分类上线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysWinTypeMationStateUpById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinTypeDao.querySysWinTypeStateById(map);
        if ("1".equals(bean.get("state").toString()) || "3".equals(bean.get("state").toString())) {//新建状态和下线状态可以上线
            sysEveWinTypeDao.editSysWinTypeMationStateUpById(map);
        } else {
            outputObject.setreturnMessage("数据状态已被更改，请刷新页面。");
        }
    }

    /**
     * 系统分类下线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysWinTypeMationStateDownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveWinTypeDao.querySysWinTypeStateById(map);
        if ("2".equals(bean.get("state").toString())) {//正常状态可以下线
            sysEveWinTypeDao.editSysWinTypeMationStateDownById(map);
        } else {
            outputObject.setreturnMessage("数据状态已被更改，请刷新页面。");
        }
    }

    /**
     * 获取已经上线的一级分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysWinTypeFirstMationStateIsUp(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveWinTypeDao.querySysWinTypeFirstMationStateIsUp(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        } else {
            outputObject.settotal(1);
        }
    }

    /**
     * 获取已经上线的二级分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysWinTypeSecondMationStateIsUp(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveWinTypeDao.querySysWinTypeSecondMationStateIsUp(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        } else {
            outputObject.settotal(1);
        }
    }

}
