/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.win.entity.SysDesktop;

/**
 * @ClassName: SysEveDesktopService
 * @Description: 桌面信息管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/22 19:23
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveDesktopService extends SkyeyeBusinessService<SysDesktop> {

    void editSysDesktopMationOrderNumUpById(InputObject inputObject, OutputObject outputObject);

    void editSysDesktopMationOrderNumDownById(InputObject inputObject, OutputObject outputObject);

    void queryAllSysDesktopList(InputObject inputObject, OutputObject outputObject);

}
