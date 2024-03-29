/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @ClassName: SysEveModelTypeService
 * @Description: 系统模板分类业务层
 * @author: skyeye云系列
 * @date: 2021/11/13 11:13
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveModelTypeService {

    /**
     * 获取系统模板分类列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void querySysEveModelTypeList(InputObject inputObject, OutputObject outputObject);

    /**
     * 新增系统模板分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void insertSysEveModelType(InputObject inputObject, OutputObject outputObject);

    /**
     * 根据id查询系统模板分类详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void querySysEveModelTypeById(InputObject inputObject, OutputObject outputObject);

    /**
     * 通过parentId查找对应的系统模板分类列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void querySysEveModelTypeByParentId(InputObject inputObject, OutputObject outputObject);

    /**
     * 通过id编辑对应的系统模板分类信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void updateSysEveModelTypeById(InputObject inputObject, OutputObject outputObject);

    /**
     * 删除系统模板分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void delSysEveModelTypeById(InputObject inputObject, OutputObject outputObject);

}
