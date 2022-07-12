/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.email.common.EmailQueryDo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserEmailDao
 * @Description: 邮箱模块数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 22:51
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface UserEmailDao {

    List<Map<String, Object>> queryEmailListByUserId(Map<String, Object> map);

    Map<String, Object> queryEmailISInByEmailAddressAndUserId(Map<String, Object> map);

    int insertEmailListByUserId(Map<String, Object> map);

    Map<String, Object> queryEmailCheckByUserId(Map<String, Object> map);

    Map<String, Object> queryEmailMationById(Map<String, Object> map);

    int insertEmailEnclosureListToServer(List<Map<String, Object>> beans);

    List<Map<String, Object>> queryInboxEmailListByEmailId(EmailQueryDo emailQuery);

    Map<String, Object> queryEmailMationByEmailId(Map<String, Object> map);

    List<Map<String, Object>> queryEnclosureBeansMationByEmailId(Map<String, Object> map);

    List<Map<String, Object>> querySendedEmailListByEmailId(EmailQueryDo emailQuery);

    List<Map<String, Object>> queryDeleteEmailListByEmailId(EmailQueryDo emailQuery);

    List<Map<String, Object>> queryDraftsEmailListByEmailId(EmailQueryDo emailQuery);

    List<Map<String, Object>> queryMyEnclusureListByUserIdAndIds(Map<String, Object> map);

    int insertToSendEmailMationByUserId(Map<String, Object> sendEmail);

    Map<String, Object> queryDraftsEmailMationToEditByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryDraftsEmailToPeopleMationToEditByUserId(Map<String, Object> bean);

    List<Map<String, Object>> queryDraftsEmailToCcMationToEditByUserId(Map<String, Object> bean);

    List<Map<String, Object>> queryDraftsEmailToBccMationToEditByUserId(Map<String, Object> bean);

    List<Map<String, Object>> queryDraftsEmailEnclosureMationToEditByUserId(Map<String, Object> bean);

    int deleteEmailEnclosureListByEmailId(Map<String, Object> map);

    int editToSendEmailMationByUserId(Map<String, Object> sendEmail);

    Map<String, Object> queryForwardEmailMationToEditByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryForwardEmailFromPeopleMationToEditByUserId(Map<String, Object> bean);

    List<Map<String, Object>> queryForwardEmailToCcMationToEditByUserId(Map<String, Object> bean);

    List<Map<String, Object>> queryForwardEmailToBccMationToEditByUserId(Map<String, Object> bean);

    List<Map<String, Object>> queryForwardEmailEnclosureMationToEditByUserId(Map<String, Object> bean);

}
