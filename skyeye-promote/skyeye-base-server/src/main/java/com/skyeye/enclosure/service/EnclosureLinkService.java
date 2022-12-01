/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.enclosure.entity.EnclosureLink;

/**
 * @ClassName: EnclosureLinkService
 * @Description: 附件信息与业务对象关系的服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:00
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface EnclosureLinkService extends SkyeyeBusinessService<EnclosureLink> {

    void writeEnclosureLink(InputObject inputObject, OutputObject outputObject);

    void queryEnclosureLinkList(InputObject inputObject, OutputObject outputObject);

    void deleteEnclosureLink(InputObject inputObject, OutputObject outputObject);
}
