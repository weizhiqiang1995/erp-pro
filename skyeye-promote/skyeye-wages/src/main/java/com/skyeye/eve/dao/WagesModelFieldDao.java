/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WagesModelFieldDao {

    public int insertWagesModelField(@Param("list") List<Map<String, Object>> beans);

    public int deleteWagesModelFieldByModelId(@Param("modelId") String modelId);

    public List<Map<String, Object>> queryWagesModelFieldByModelId(@Param("modelId") String modelId);

    /**
     * 获取指定员工用有的薪资模板对应的薪资要素字段信息，如果薪资模板中有重复的薪资要素字段，则根据薪资要素字段的key进行分组
     *
     * @param modelIds   该员工拥有的薪资模板id集合
     * @param staffId    员工id
     * @param jobScoreId 职位定级id
     * @return 薪资要素字段信息，该员工的薪资信息以及对应的薪资描述薪资
     */
    public List<Map<String, Object>> queryWagesModelFieldByModelIdsAndStaffId(@Param("list") List<String> modelIds,
                                                                              @Param("staffId") String staffId,
                                                                              @Param("jobScoreId") String jobScoreId);
}
