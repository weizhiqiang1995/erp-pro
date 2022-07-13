/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface KnowledgeTypeDao {

    /**
     * 获取知识库类型列表
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> queryKnowledgeTypeList(Map<String, Object> map);

    /**
     * 根据名称&&ID查询该数据
     *
     * @param name     名称
     * @param parentId 所属父id
     * @param thisId   当前id
     * @return
     */
    Map<String, Object> queryKnowledgeTypeMationByName(@Param("name") String name, @Param("parentId") String parentId, @Param("thisId") String thisId);

    int insertKnowledgeTypeMation(Map<String, Object> map);

    int editKnowledgeTypeStateById(@Param("id") String id,
                                   @Param("state") int state,
                                   @Param("lastUpdateId") String lastUpdateId,
                                   @Param("lastUpdateTime") String lastUpdateTime);

    Map<String, Object> selectKnowledgeTypeById(Map<String, Object> map);

    int editKnowledgeTypeMationById(Map<String, Object> map);

    Map<String, Object> queryKnowledgeTypeStateById(Map<String, Object> map);

    List<Map<String, Object>> queryUpKnowledgeTypeTreeMation();
}
