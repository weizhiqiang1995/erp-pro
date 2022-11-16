/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.team.TeamTemplate;

/**
 * @ClassName: TeamTemplateService
 * @Description: 团队模板管理服务接口类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:24
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TeamTemplateService extends SkyeyeBusinessService<TeamTemplate> {

    void queryTeamTemplateMation(InputObject inputObject, OutputObject outputObject);

    /**
     * 设置为使用中
     *
     * @param id
     */
    void setUsed(String id);

}
