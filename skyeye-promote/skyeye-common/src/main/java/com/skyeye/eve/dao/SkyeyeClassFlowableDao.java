/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.github.pagehelper.Page;
import com.skyeye.eve.entity.classflowable.SkyeyeClassFlowableLinkMation;

import java.util.List;

/**
 * @ClassName: CodeRuleDao
 * @Description: 编码规则管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SkyeyeClassFlowableDao extends SkyeyeBaseMapper<SkyeyeClassFlowableLinkMation> {

    List<SkyeyeClassFlowableLinkMation> queryClassFlowableDataList(Page pages);

}