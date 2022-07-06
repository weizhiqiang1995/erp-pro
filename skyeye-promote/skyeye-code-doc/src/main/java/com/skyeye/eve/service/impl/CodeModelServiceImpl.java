/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.CodeModelDao;
import com.skyeye.eve.entity.codedoc.model.CodeModelQueryDo;
import com.skyeye.eve.service.CodeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CodeModelServiceImpl
 * @Description: 模板信息管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class CodeModelServiceImpl implements CodeModelService {

    @Autowired
    private CodeModelDao codeModelDao;

    /**
     * 获取模板列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryCodeModelList(InputObject inputObject, OutputObject outputObject) {
        CodeModelQueryDo codeModelQuery = inputObject.getParams(CodeModelQueryDo.class);
        Page pages = PageHelper.startPage(codeModelQuery.getPage(), codeModelQuery.getLimit());
        List<Map<String, Object>> beans = codeModelDao.queryCodeModelList(codeModelQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增模板列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertCodeModelMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = codeModelDao.queryCodeModelMationByName(map);
        if (bean == null) {
            Map<String, Object> user = inputObject.getLogParams();
            map.put("id", ToolUtil.getSurFaceId());
            map.put("createId", user.get("id"));
            map.put("createTime", DateUtil.getTimeAndToString());
            codeModelDao.insertCodeModelMation(map);
        } else {
            outputObject.setreturnMessage("该模板已存在，请更换。");
        }
    }

    /**
     * 删除模板信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteCodeModelById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        codeModelDao.deleteCodeModelById(map);
    }

    /**
     * 编辑模板信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryCodeModelMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = codeModelDao.queryCodeModelMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑模板信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editCodeModelMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = codeModelDao.queryCodeModelMationByIdAndName(map);
        if (CollectionUtils.isEmpty(bean)) {
            Map<String, Object> user = inputObject.getLogParams();
            map.put("lastUpdateId", user.get("id"));
            map.put("lastUpdateTime", DateUtil.getTimeAndToString());
            codeModelDao.editCodeModelMationById(map);
        } else {
            outputObject.setreturnMessage("该模板已存在，请更换。");
        }
    }

}
