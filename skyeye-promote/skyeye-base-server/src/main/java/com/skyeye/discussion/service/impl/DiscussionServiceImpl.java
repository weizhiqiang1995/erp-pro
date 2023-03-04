/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.service.impl;

import com.skyeye.base.business.service.impl.SkyeyeTeamAuthServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.discussion.classenum.DisCussionAuthEnum;
import com.skyeye.discussion.dao.DiscussionDao;
import com.skyeye.discussion.entity.Discussion;
import com.skyeye.discussion.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DiscussionServiceImpl
 * @Description: 讨论帖管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:18
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class DiscussionServiceImpl extends SkyeyeTeamAuthServiceImpl<DiscussionDao, Discussion> implements DiscussionService {

    @Autowired
    private DiscussionDao discussionDao;

    @Override
    public Class getAuthEnumClass() {
        return DisCussionAuthEnum.class;
    }

    @Override
    public List<String> getAuthPermissionKeyList() {
        return Arrays.asList(DisCussionAuthEnum.ADD.getKey(), DisCussionAuthEnum.EDIT.getKey(), DisCussionAuthEnum.DELETE.getKey(), DisCussionAuthEnum.DETAILS.getKey());
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = discussionDao.queryDiscussionList(pageInfo);
        return beans;
    }

}
