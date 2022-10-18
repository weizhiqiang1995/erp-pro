/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.clazz.entity.classenum.SkyeyeClassEnumMation;

/**
 * @ClassName: SkyeyeClassEnumService
 * @Description: 基本框架---具备某个特征的枚举类管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/11 19:52
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SkyeyeClassEnumService extends IService<SkyeyeClassEnumMation> {

    void writeClassEnum(InputObject inputObject, OutputObject outputObject);

    void getEnumDataByClassName(InputObject inputObject, OutputObject outputObject);
}
