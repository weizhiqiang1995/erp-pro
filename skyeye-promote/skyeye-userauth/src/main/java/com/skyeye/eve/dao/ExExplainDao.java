/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.Map;

public interface ExExplainDao {

    int insertExExplainMation(Map<String, Object> map);

    Map<String, Object> queryExExplainMation(Map<String, Object> map);

    int editExExplainMationById(Map<String, Object> map);

}
