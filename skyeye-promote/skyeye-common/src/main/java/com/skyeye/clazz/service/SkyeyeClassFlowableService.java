/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.clazz.entity.classflowable.SkyeyeClassFlowableLinkMation;

/**
 * @ClassName: SkyeyeClassFlowableService
 * @Description: 工作流业务对象服务服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SkyeyeClassFlowableService extends IService<SkyeyeClassFlowableLinkMation> {
    void writeClassFlowable(InputObject inputObject, OutputObject outputObject);

    void getClassFlowableData(InputObject inputObject, OutputObject outputObject);

    void queryClassFlowableDataList(InputObject inputObject, OutputObject outputObject);
}
