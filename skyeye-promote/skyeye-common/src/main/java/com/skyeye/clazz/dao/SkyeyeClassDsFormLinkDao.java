/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.dao;

import com.skyeye.clazz.entity.dsformlink.SkyeyeClassDsFormLinkMation;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SkyeyeClassDsFormLinkDao
 * @Description: 动态表单的服务类注册数据层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SkyeyeClassDsFormLinkDao extends SkyeyeBaseMapper<SkyeyeClassDsFormLinkMation> {

    List<Map<String, Object>> queryDsFormLinkList(CommonPageInfo commonPageInfo);

    Map<String, Object> queryDsFormLinkMationById(@Param("id") String id, @Param("code") String code);

    void editDsFormLinkMationById(Map<String, Object> map);

}
