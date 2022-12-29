/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.dict.SysDictData;

/**
 * @ClassName: SysDictDataService
 * @Description: 数据字典服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/2 13:18
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysDictDataService extends SkyeyeBusinessService<SysDictData> {

    void queryDictDataListByDictTypeCode(InputObject inputObject, OutputObject outputObject);

    void queryDictDataListByDictTypeCodeAndNotId(InputObject inputObject, OutputObject outputObject);

    void setDictDataParent(InputObject inputObject, OutputObject outputObject);
}
