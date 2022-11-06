/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.dict.SysDictDataMation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysDictDataDao
 * @Description: 数据字典类型管理数据交互层
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:31
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysDictDataDao extends SkyeyeBaseMapper<SysDictDataMation> {

    List<Map<String, Object>> queryDictDataList(Map<String, Object> map);

    List<SysDictDataMation> queryDictDataListByDictTypeCode(@Param("dictTypeCpde") String dictTypeCpde, @Param("enabled") Integer enabled);

    /**
     * 根据子id查询所有的父节点信息(包含子节点信息)
     *
     * @param ids 子id
     * @return
     */
    List<Map<String, Object>> queryAllParentNodeById(@Param("ids") List<String> ids);

    /**
     * 根据父id查询所有的子节点信息(包含父id)
     *
     * @param ids        父id
     * @return
     */
    List<String> queryAllChildIdsByParentId(@Param("ids") List<String> ids);

}
