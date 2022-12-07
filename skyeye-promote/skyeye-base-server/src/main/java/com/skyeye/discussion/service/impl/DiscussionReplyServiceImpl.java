/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.discussion.dao.DiscussionReplyDao;
import com.skyeye.discussion.entity.DiscussionReply;
import com.skyeye.discussion.entity.DiscussionReplyQueryDo;
import com.skyeye.discussion.service.DiscussionReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: DiscussionReplyServiceImpl
 * @Description: 讨论帖回帖服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 22:07
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class DiscussionReplyServiceImpl extends SkyeyeBusinessServiceImpl<DiscussionReplyDao, DiscussionReply> implements DiscussionReplyService {

    @Autowired
    private DiscussionReplyDao discussionReplyDao;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        DiscussionReplyQueryDo pageInfo = inputObject.getParams(DiscussionReplyQueryDo.class);
        List<Map<String, Object>> beans = discussionReplyDao.queryDiscussionReplyList(pageInfo);
        List<String> ids = beans.stream().map(bean -> bean.get("id").toString()).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        // 查询子节点信息(包含当前节点)
        List<String> childIds = discussionReplyDao.queryAllChildIdsByParentId(ids);
        beans = discussionReplyDao.queryDiscussionReplyListByIds(childIds);
        return beans;
    }

    @Override
    public Boolean builderTree(List<Map<String, Object>> beans, OutputObject outputObject) {
        List<Tree<String>> treeNodes = TreeUtil.build(beans, String.valueOf(CommonNumConstants.NUM_ZERO), new TreeNodeConfig(),
            (treeNode, tree) -> {
                tree.setId(treeNode.get("id").toString());
                tree.setParentId(treeNode.get("replyId").toString());
                tree.setName(treeNode.get("content").toString());
                tree.putExtra("createName", treeNode.get("createName").toString());
                tree.putExtra("createTime", treeNode.get("createTime").toString());
            });
        outputObject.setBeans(treeNodes);
        outputObject.settotal(treeNodes.size());
        return true;
    }

    @Override
    public void deleteById(String id) {
        // 获取当前回帖和所有的子回帖信息
        List<String> ids = discussionReplyDao.queryAllChildIdsByParentId(Arrays.asList(id));
        super.deleteById(ids);
    }

}
