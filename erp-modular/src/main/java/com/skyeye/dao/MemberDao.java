/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/9/16 21:23
 */
public interface MemberDao {

    public List<Map<String, Object>> queryMemberByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public Map<String, Object> queryMemberByUserIdAndMember(Map<String, Object> params) throws Exception;

    public void insertMember(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryMemberById(Map<String, Object> params) throws Exception;

    public int editMemberByDeleteFlag(Map<String, Object> params) throws Exception;

    public int editMemberById(Map<String, Object> params) throws Exception;

    public int editMemberByEnabled(Map<String, Object> params) throws Exception;

    public int editMemberByNotEnabled(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryMemberByIdAndName(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryMemberByEnabled(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryMemberByIdAndInfo(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryMemberListToSelect(Map<String, Object> params) throws Exception;
}
