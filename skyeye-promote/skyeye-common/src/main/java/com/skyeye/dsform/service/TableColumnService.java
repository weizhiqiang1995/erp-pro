/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.dsform.entity.TableColumn;

import java.util.List;

/**
 * @ClassName: TableColumnService
 * @Description: 表格布局的列属性服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/5 20:44
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TableColumnService extends SkyeyeBusinessService<TableColumn> {

    List<TableColumn> getTableColumnByPageId(String pageId);

    void createList(List<TableColumn> entitys, String userId, String pageId);


    void deleteByPageId(String pageId);

}
