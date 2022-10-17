/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menuapp.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.entity.userauth.menu.AppWorkPageAuthPointMation;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AppWorkPageAuthPointDao
 * @Description: APP菜单权限点管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AppWorkPageAuthPointDao extends SkyeyeBaseMapper<AppWorkPageAuthPointMation> {

    List<Map<String, Object>> queryAppWorkPageAuthPointListByMenuId(Map<String, Object> map);

}
