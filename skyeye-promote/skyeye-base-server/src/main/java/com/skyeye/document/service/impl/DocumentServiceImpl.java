/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.document.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeTeamAuthServiceImpl;
import com.skyeye.catalog.service.CatalogService;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.document.classenum.DocumentAuthEnum;
import com.skyeye.document.dao.DocumentDao;
import com.skyeye.document.entity.Document;
import com.skyeye.document.service.DocumentService;
import com.skyeye.eve.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DocumentServiceImpl
 * @Description: 文档管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/7/24 8:19
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "文档管理", groupName = "基础模块", teamAuth = true)
public class DocumentServiceImpl extends SkyeyeTeamAuthServiceImpl<DocumentDao, Document> implements DocumentService {

    @Autowired
    private SysDictDataService sysDictDataService;

    @Autowired
    private CatalogService catalogService;

    @Override
    public Class getAuthEnumClass() {
        return DocumentAuthEnum.class;
    }

    @Override
    public List<String> getAuthPermissionKeyList() {
        return Arrays.asList(DocumentAuthEnum.ADD_DOCUMENT.getKey(), DocumentAuthEnum.EDIT_DOCUMENT.getKey(), DocumentAuthEnum.DELETE_DOCUMENT.getKey(), DocumentAuthEnum.DETAILS_DOCUMENT.getKey());
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryDocumentList(pageInfo);
        sysDictDataService.setMationForMap(beans, "typeId", "typeMation");
        catalogService.setMationForMap(beans, "catalogId", "catalogMation");
        return beans;
    }

    @Override
    public Document selectById(String id) {
        Document document = super.selectById(id);
        sysDictDataService.setDataMation(document, Document::getTypeId);
        catalogService.setDataMation(document, Document::getCatalogId);
        return document;
    }
}
