/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skyeye.clazz.entity.dsformlink.SkyeyeClassDsFormLinkMation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @ClassName: SkyeyeClassDsFormLinkService
 * @Description: 动态表单的服务类注册服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SkyeyeClassDsFormLinkService extends IService<SkyeyeClassDsFormLinkMation> {

    void writeDsFormLink(InputObject inputObject, OutputObject outputObject);

    void queryDsFormLinkList(InputObject inputObject, OutputObject outputObject);

    void queryDsFormLinkMationById(InputObject inputObject, OutputObject outputObject);

    void editDsFormLinkMationById(InputObject inputObject, OutputObject outputObject);

    void queryDsFormLinkListByCode(InputObject inputObject, OutputObject outputObject);

}
