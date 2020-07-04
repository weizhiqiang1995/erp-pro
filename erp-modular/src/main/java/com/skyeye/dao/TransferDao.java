/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 卫志强
 * @Description: TODO
 * @Date: 2019/10/20 10:23
 */
public interface TransferDao {
	
    public List<Map<String, Object>> queryTransferByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public int insertTransfer(Map<String, Object> params) throws Exception;

    public int insertTransferItem(List<Map<String, Object>> entitys) throws Exception;

    public Map<String, Object> queryTransferToEditById(Map<String, Object> params) throws Exception;

    public int editTransferById(Map<String, Object> params) throws Exception;

    public int editTransferByDeleteFlag(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryTransferDetailById(Map<String, Object> params) throws Exception;

    public List<Map<String, Object>> queryTransferItemsDetailById(Map<String, Object> bean) throws Exception;

    public List<Map<String, Object>> queryTransferItemsToEditById(Map<String, Object> params) throws Exception;

    public int editTransferItemsByDeleteFlag(Map<String, Object> params) throws Exception;

    public int deleteTransferItemById(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryUserInfoById(Map<String, Object> bean) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;
	
}
