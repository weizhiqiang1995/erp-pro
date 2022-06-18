/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 *
 * @ClassName: KnowledgeContentService
 * @Description: 知识库管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/21 15:30
 *
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface KnowledgeContentService {

	void queryKnowledgeContentList(InputObject inputObject, OutputObject outputObject) throws Exception;

	void insertKnowledgeContentMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	void selectKnowledgeContentById(InputObject inputObject, OutputObject outputObject) throws Exception;

	void editKnowledgeContentById(InputObject inputObject, OutputObject outputObject) throws Exception;

	void deleteKnowledgeContentById(InputObject inputObject, OutputObject outputObject) throws Exception;

	void queryKnowledgeContentMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

	void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

	void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject) throws Exception;

	void queryUnCheckedKnowledgeContentList(InputObject inputObject, OutputObject outputObject) throws Exception;

	void editKnowledgeContentToCheck(InputObject inputObject, OutputObject outputObject) throws Exception;

	void queryKnowledgeContentByIdToCheck(InputObject inputObject, OutputObject outputObject) throws Exception;

	void queryCheckedKnowledgeContentList(InputObject inputObject, OutputObject outputObject) throws Exception;

	void queryUncheckedKnowledgeContent(InputObject inputObject, OutputObject outputObject) throws Exception;

	void queryCheckedKnowledgeContent(InputObject inputObject, OutputObject outputObject) throws Exception;

	void queryAllPassKnowledgeContentList(InputObject inputObject, OutputObject outputObject) throws Exception;
}
