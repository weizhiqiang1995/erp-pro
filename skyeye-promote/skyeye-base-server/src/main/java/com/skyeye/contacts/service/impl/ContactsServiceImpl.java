/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contacts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.enumeration.IsDefaultEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.contacts.dao.ContactsDao;
import com.skyeye.contacts.service.ContactsService;
import com.skyeye.eve.entity.contacts.ContactsMation;
import com.skyeye.eve.entity.object.query.BaseServerQueryDo;
import com.skyeye.eve.service.IAuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ContactsServiceImpl
 * @Description: 客户联系人管理
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:18
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactsServiceImpl.class);

    @Autowired
    private ContactsDao contactsDao;

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 获取联系人列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryContactsList(InputObject inputObject, OutputObject outputObject) {
        BaseServerQueryDo baseServerQuery = inputObject.getParams(BaseServerQueryDo.class);
        baseServerQuery.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
        if (ToolUtil.isBlank(baseServerQuery.getObjectId()) && ToolUtil.isBlank(baseServerQuery.getObjectKey())) {
            baseServerQuery.setCreateId(inputObject.getLogParams().get("id").toString());
        }
        Page pages = PageHelper.startPage(baseServerQuery.getPage(), baseServerQuery.getLimit());
        List<Map<String, Object>> beans = contactsDao.queryContactsList(baseServerQuery);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增/编辑联系人信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeContactsMation(InputObject inputObject, OutputObject outputObject) {
        ContactsMation contactsMation = inputObject.getParams(ContactsMation.class);
        // 2.新增/编辑数据
        if (StringUtils.isNotEmpty(contactsMation.getId())) {
            LOGGER.info("update customer data, id is {}", contactsMation.getId());
            contactsDao.updateById(contactsMation);
        } else {
            DataCommonUtil.setId(contactsMation);
            LOGGER.info("insert customer data, id is {}", contactsMation.getId());
            contactsDao.insert(contactsMation);
        }
        if (contactsMation.getIsDefault() == IsDefaultEnum.IS_DEFAULT.getKey()) {
            // 如果设置为默认联系人，则修改之前的联系人为非默认
            contactsDao.setContactsIsNotDefault(contactsMation.getObjectId(), IsDefaultEnum.NOT_DEFAULT.getKey());
            contactsDao.setContactsIsNotDefault(contactsMation.getId(), IsDefaultEnum.IS_DEFAULT.getKey());
        }
    }

    /**
     * 根据id获取联系人信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryContactsMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        QueryWrapper<ContactsMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstants.ID, id);
        queryWrapper.ne(MybatisPlusUtil.toColumns(ContactsMation::getDeleteFlag), DeleteFlagEnum.NOT_DELETE.getKey());
        ContactsMation contactsMation = contactsDao.selectOne(queryWrapper);
        outputObject.setBean(contactsMation);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据id删除联系人信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteContactsMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        String userId = inputObject.getLogParams().get("id").toString();
        // 删除
        UpdateWrapper<ContactsMation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        ContactsMation contactsMation = new ContactsMation();
        DataCommonUtil.setCommonLastUpdateDataByGenericity(contactsMation, userId);
        contactsMation.setDeleteFlag(DeleteFlagEnum.DELETED.getKey());
        contactsDao.update(contactsMation, updateWrapper);
    }

}
