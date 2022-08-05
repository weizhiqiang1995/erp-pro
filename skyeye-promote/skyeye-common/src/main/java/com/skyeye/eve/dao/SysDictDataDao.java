/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.dict.SysDictDataMation;
import com.skyeye.eve.entity.dict.SysDictDataQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: SysDictDataDao
 * @Description: 数据字典类型管理数据交互层
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:31
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysDictDataDao extends SkyeyeBaseMapper<SysDictDataMation> {

    List<SysDictDataMation> queryDictDataList(SysDictDataQueryDO sysDictDataQuery);

    List<SysDictDataMation> queryDictDataListByDictTypeCode(@Param("dictTypeCpde") String dictTypeCpde);
}