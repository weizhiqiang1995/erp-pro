/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contacts.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.contacts.entity.ContactsMation;
import com.skyeye.eve.entity.object.query.BaseServerQueryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ContactsDao
 * @Description: 客户联系人管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:43
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ContactsDao extends SkyeyeBaseMapper<ContactsMation> {

    List<Map<String, Object>> queryContactsList(BaseServerQueryDo baseServerQuery);

    int setContactsIsNotDefault(@Param("objectId") String objectId, @Param("isDefault") Integer isDefault);

    int setContactsIsDefault(@Param("id") String id, @Param("isDefault") Integer isDefault);

}
