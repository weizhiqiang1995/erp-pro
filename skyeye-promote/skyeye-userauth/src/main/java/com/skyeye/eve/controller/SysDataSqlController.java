/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysDataSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysDataSqlController {

    @Autowired
    private SysDataSqlService sysDataSqlService;

    /**
     * 获取历史备份列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDataSqlController/querySysDataSqlBackupsList")
    public void querySysDataSqlBackupsList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDataSqlService.querySysDataSqlBackupsList(inputObject, outputObject);
    }

    /**
     * 获取所有表的列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDataSqlController/queryAllTableMationList")
    public void queryAllTableMationList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDataSqlService.queryAllTableMationList(inputObject, outputObject);
    }

    /**
     * 开始备份
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDataSqlController/insertTableBackUps")
    public void insertTableBackUps(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDataSqlService.insertTableBackUps(inputObject, outputObject);
    }

    /**
     * 开始还原
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDataSqlController/insertTableReduction")
    public void insertTableReduction(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDataSqlService.insertTableReduction(inputObject, outputObject);
    }

}
