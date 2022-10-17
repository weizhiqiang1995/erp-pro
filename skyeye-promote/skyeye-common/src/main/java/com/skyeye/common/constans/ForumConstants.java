/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common.constans;

/**
 * @ClassName: ForumConstants
 * @Description: 论坛系统常量类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/15 21:44
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class ForumConstants {

    // 获取论坛帖子浏览量的redis的key(实时的)
    public static final String FORUM_BROWSE_NUMS_BYFORUMID = "forum_browse_nums_by_";

    public static String forumBrowseNumsByForumId(String forumId) {
        return FORUM_BROWSE_NUMS_BYFORUMID + forumId;
    }

}
