/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.dao;

import com.skyeye.dsform.entity.DsFormPageContent;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @ClassName: DsFormPageContentDao
 * @Description: 动态表单页面内容项数据层
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/8 16:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface DsFormPageContentDao extends SkyeyeBaseMapper<DsFormPageContent> {

    Map<String, Object> queryDsFormPageOrderby(@Param("pageId") String pageId);

}
