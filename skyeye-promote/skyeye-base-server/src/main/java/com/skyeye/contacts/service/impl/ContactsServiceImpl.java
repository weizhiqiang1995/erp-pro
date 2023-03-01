/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contacts.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeTeamAuthServiceImpl;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.enumeration.IsDefaultEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.contacts.classenum.ContactsAuthEnum;
import com.skyeye.contacts.dao.ContactsDao;
import com.skyeye.contacts.entity.Contacts;
import com.skyeye.contacts.service.ContactsService;
import com.skyeye.eve.entity.object.query.BaseServerQueryDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ContactsServiceImpl
 * @Description: 联系人管理
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:18
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "联系人管理", groupName = "基础模块", teamAuth = true)
public class ContactsServiceImpl extends SkyeyeTeamAuthServiceImpl<ContactsDao, Contacts> implements ContactsService {

    @Autowired
    private ContactsDao contactsDao;

    @Override
    public Class getAuthEnumClass() {
        return ContactsAuthEnum.class;
    }

    @Override
    public List<String> getAuthPermissionKeyList() {
        return Arrays.asList(ContactsAuthEnum.ADD.getKey(), ContactsAuthEnum.EDIT.getKey(), ContactsAuthEnum.DELETE.getKey(), ContactsAuthEnum.DETAILS.getKey());
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        BaseServerQueryDo baseServerQuery = inputObject.getParams(BaseServerQueryDo.class);
        baseServerQuery.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
        List<Map<String, Object>> beans = contactsDao.queryContactsList(baseServerQuery);
        return beans;
    }

    @Override
    public void writePostpose(Contacts entity, String userId) {
        super.writePostpose(entity, userId);
        if (entity.getIsDefault().equals(IsDefaultEnum.IS_DEFAULT.getKey())) {
            // 如果设置为默认联系人，则修改之前的联系人为非默认
            contactsDao.setContactsIsNotDefault(entity.getObjectId(), IsDefaultEnum.NOT_DEFAULT.getKey());
            contactsDao.setContactsIsDefault(entity.getId(), IsDefaultEnum.IS_DEFAULT.getKey());
        }
    }

}
