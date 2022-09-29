/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.coderule.SkyeyeClassCodeRuleMation;

/**
 * @ClassName: SkyeyeClassCodeRuleService
 * @Description: 需要获取编码的服务类服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:07
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SkyeyeClassCodeRuleService extends IService<SkyeyeClassCodeRuleMation> {

    void writeClassCodeRule(InputObject inputObject, OutputObject outputObject);

    void getClassCodeRuleData(InputObject inputObject, OutputObject outputObject);

    void queryClassCodeRuleList(InputObject inputObject, OutputObject outputObject);

    void editClassCodeRuleConfig(InputObject inputObject, OutputObject outputObject);
}