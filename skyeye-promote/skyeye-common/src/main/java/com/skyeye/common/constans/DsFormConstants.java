/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common.constans;

/**
 * @ClassName: DsFormConstants
 * @Description: 动态表单常量类
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/8 17:17
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class DsFormConstants {

    // 获取redis中的动态表单内容项
    public static final String DS_FORM_CONTENT_LIST = "ds_form_content_list_";

    public static String dsFormContentListByPageId(String pageId) {
        return DS_FORM_CONTENT_LIST + pageId;
    }

}
