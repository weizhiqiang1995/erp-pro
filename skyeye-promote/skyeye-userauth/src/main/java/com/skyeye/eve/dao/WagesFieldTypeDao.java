/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WagesFieldTypeDao
 * @Description: 薪资要素字段
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/8 22:23
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface WagesFieldTypeDao {

    /**
     * 获取所有的薪资要素字段，包括启用，禁用，以及删除的要素字段key；相同的key，该SQL语句会根据key进行分组
     *
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> queryAllWagesFieldTypeList();

    int insertWagesFieldTypeKeyToStaff(@Param("list") List<Map<String, Object>> staff);

}
