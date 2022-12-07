/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.discussion.entity.DiscussionReply;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DiscussionReplyDao
 * @Description: 讨论帖回帖数据层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 22:07
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface DiscussionReplyDao extends SkyeyeBaseMapper<DiscussionReply> {

    List<Map<String, Object>> queryDiscussionReplyList(CommonPageInfo pageInfo);

    /**
     * 根据父id查询所有的子节点信息(包含父id)
     *
     * @param ids        父id
     * @return
     */
    List<String> queryAllChildIdsByParentId(@Param("ids") List<String> ids);

    List<Map<String, Object>> queryDiscussionReplyListByIds(@Param("ids") List<String> ids);

}
