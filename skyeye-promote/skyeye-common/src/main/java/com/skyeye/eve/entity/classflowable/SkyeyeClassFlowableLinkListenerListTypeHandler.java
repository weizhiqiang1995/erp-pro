/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.classflowable;

import com.alibaba.fastjson.TypeReference;
import com.skyeye.common.base.handler.ListTypeHandler;

import java.util.List;

/**
 * @ClassName: SkyeyeClassFlowableLinkListenerListTypeHandler
 * @Description: 工作流业务对象服务对应的监听器集合转换处理类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/20 13:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class SkyeyeClassFlowableLinkListenerListTypeHandler extends ListTypeHandler<ListenerMation> {

    @Override
    protected TypeReference<List<ListenerMation>> specificType() {
        return new TypeReference<List<ListenerMation>>() {
        };
    }

}
