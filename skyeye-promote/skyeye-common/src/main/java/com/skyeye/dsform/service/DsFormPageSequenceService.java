/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.entity.DsFormPageSequence;

import java.util.List;

/**
 * @ClassName: DsFormPageSequenceService
 * @Description: 业务数据关联的表单布局服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/2 0:32
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface DsFormPageSequenceService extends SkyeyeBusinessService<DsFormPageSequence> {

    List<DsFormPageSequence> queryDsFormPageSequenceByObjectId(String objectId);

    void deleteByObjectId(String objectId);

    void saveDsFormData(InputObject inputObject, OutputObject outputObject);

    void queryDsFormDataByObjectId(InputObject inputObject, OutputObject outputObject);
}
