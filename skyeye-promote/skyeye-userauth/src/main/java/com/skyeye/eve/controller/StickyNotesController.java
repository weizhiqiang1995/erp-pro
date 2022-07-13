/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.StickyNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StickyNotesController {

    @Autowired
    private StickyNotesService stickyNotesService;

    /**
     * 新增便签
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/StickyNotesController/insertStickyNotesMation")
    public void insertStickyNotesMation(InputObject inputObject, OutputObject outputObject) {
        stickyNotesService.insertStickyNotesMation(inputObject, outputObject);
    }

    /**
     * 查询便签
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/StickyNotesController/selectStickyNotesMation")
    public void selectStickyNotesMation(InputObject inputObject, OutputObject outputObject) {
        stickyNotesService.selectStickyNotesMation(inputObject, outputObject);
    }

    /**
     * 编辑便签
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/StickyNotesController/editStickyNotesMation")
    public void editStickyNotesMation(InputObject inputObject, OutputObject outputObject) {
        stickyNotesService.editStickyNotesMation(inputObject, outputObject);
    }

    /**
     * 删除便签
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/StickyNotesController/deleteStickyNotesMation")
    public void deleteStickyNotesMation(InputObject inputObject, OutputObject outputObject) {
        stickyNotesService.deleteStickyNotesMation(inputObject, outputObject);
    }

}
