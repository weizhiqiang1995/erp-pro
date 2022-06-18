/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEvePicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEvePicTypeController {

    @Autowired
    private SysEvePicTypeService sysEvePicTypeService;

    /**
     * 获取系统图片类型列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/querySysPicTypeList")
    public void querySysPicTypeList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.querySysPicTypeList(inputObject, outputObject);
    }


    /**
     * 添加系统图片类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/insertSysPicTypeMation")
    public void insertSysPicTypeMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.insertSysPicTypeMation(inputObject, outputObject);
    }

    /**
     * 删除系统图片类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/deleteSysPicTypeById")
    public void deleteSysPicTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.deleteSysPicTypeById(inputObject, outputObject);
    }

    /**
     * 上线系统图片类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/updateUpSysPicTypeById")
    public void updateUpSysPicTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.updateUpSysPicTypeById(inputObject, outputObject);
    }

    /**
     * 下线系统图片类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/updateDownSysPicTypeById")
    public void updateDownSysPicTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.updateDownSysPicTypeById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的图片类型信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/selectSysPicTypeById")
    public void selectSysPicTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.selectSysPicTypeById(inputObject, outputObject);
    }

    /**
     * 通过id编辑对应的图片类型信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/editSysPicTypeMationById")
    public void editSysPicTypeMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.editSysPicTypeMationById(inputObject, outputObject);
    }

    /**
     * 图片类型上移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/editSysPicTypeMationOrderNumUpById")
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.editSysPicTypeMationOrderNumUpById(inputObject, outputObject);
    }

    /**
     * 图片类型下移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/editSysPicTypeMationOrderNumDownById")
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.editSysPicTypeMationOrderNumDownById(inputObject, outputObject);
    }

    /**
     * 获取已经上线的图片类型列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEvePicTypeController/querySysPicTypeUpStateList")
    public void querySysPicTypeUpStateList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEvePicTypeService.querySysPicTypeUpStateList(inputObject, outputObject);
    }

}
