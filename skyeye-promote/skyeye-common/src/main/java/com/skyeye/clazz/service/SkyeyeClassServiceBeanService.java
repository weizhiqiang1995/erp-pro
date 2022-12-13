/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.clazz.entity.classservice.SkyeyeClassServiceBean;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.net.URI;

/**
 * @ClassName: SkyeyeClassServiceBeanService
 * @Description: 所有实现了SkyeyeBusinessService的服务类的注册服务
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/29 22:30
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SkyeyeClassServiceBeanService extends SkyeyeBusinessService<SkyeyeClassServiceBean> {

    void registerServiceBean(InputObject inputObject, OutputObject outputObject);

    URI getServiceBean(String className);

    void queryServiceClassForTree(InputObject inputObject, OutputObject outputObject);

    void queryServiceClass(InputObject inputObject, OutputObject outputObject);
}
