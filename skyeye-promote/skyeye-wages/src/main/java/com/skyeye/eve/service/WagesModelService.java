/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.List;
import java.util.Map;

public interface WagesModelService {

    void queryWagesModelList(InputObject inputObject, OutputObject outputObject);

    void insertWagesModelMation(InputObject inputObject, OutputObject outputObject);

    void queryWagesModelMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editWagesModelMationById(InputObject inputObject, OutputObject outputObject);

    void deleteWagesModelMationById(InputObject inputObject, OutputObject outputObject);

    void enableWagesModelMationById(InputObject inputObject, OutputObject outputObject);

    void disableWagesModelMationById(InputObject inputObject, OutputObject outputObject);

    void queryWagesModelDetailMationById(InputObject inputObject, OutputObject outputObject);

    /**
     * 根据模板id获取要素字段
     *
     * @param modeId 模板id
     * @return
     */
    List<Map<String, Object>> getWagesModelFieldsByModelId(String modeId);
}
