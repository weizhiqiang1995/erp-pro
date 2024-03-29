/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.entity;

import com.alibaba.fastjson.TypeReference;
import com.skyeye.common.util.mybatisplus.ListTypeHandler;

import java.util.List;

/**
 * @ClassName: OperateListTypeHandler
 * @Description: 操作按钮显示条件对应的监听器集合转换处理类
 * @author: skyeye云系列--卫志强
 * @date: 2023/6/11 23:11
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class OperateListTypeHandler extends ListTypeHandler<OperateShowCondition> {

    @Override
    protected TypeReference<List<OperateShowCondition>> specificType() {
        return new TypeReference<List<OperateShowCondition>>() {
        };
    }

}
