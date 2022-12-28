/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service;

import com.skyeye.attr.entity.AttrTransformTable;
import com.skyeye.base.business.service.SkyeyeBusinessService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AttrTransformTableService
 * @Description: 表格模型属性管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AttrTransformTableService extends SkyeyeBusinessService<AttrTransformTable> {

    void saveAttrTransformTable(String serviceClassName, String parentAttrKey, List<AttrTransformTable> attrTransformTableList);

    void deleteAttrTransformTable(String serviceClassName, String parentAttrKey);

    List<AttrTransformTable> queryAttrTransformTable(String serviceClassName, String parentAttrKey);

    Map<String, List<AttrTransformTable>> queryAttrTransformTable(String serviceClassName, List<String> parentAttrKey);

}
