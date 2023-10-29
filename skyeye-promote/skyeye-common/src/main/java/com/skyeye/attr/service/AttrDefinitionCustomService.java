/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service;

import com.skyeye.attr.entity.AttrDefinitionCustom;
import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AttrDefinitionCustomService
 * @Description: 用户自定义服务类属性服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/30 10:56
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AttrDefinitionCustomService extends SkyeyeBusinessService<AttrDefinitionCustom> {

    List<AttrDefinitionCustom> queryAttrDefinitionCustomList(String className, List<String> attrKey);

    Map<String, AttrDefinitionCustom> queryAttrDefinitionCustomMap(String className, List<String> attrKey);

    AttrDefinitionCustom queryAttrDefinitionCustom(String className, String attrKey);

    void queryAttrDefinitionCustom(InputObject inputObject, OutputObject outputObject);

    void deleteAttrDefinitionCustom(InputObject inputObject, OutputObject outputObject);

    void setDsFormComponentUseNum(List<Map<String, Object>> beans);

    void queryAttrByComponentId(InputObject inputObject, OutputObject outputObject);
}
