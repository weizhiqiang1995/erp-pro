/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.attr.service.AttrDefinitionService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.DsFormConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dsform.dao.DsFormPageDao;
import com.skyeye.dsform.entity.DsFormPage;
import com.skyeye.dsform.entity.DsFormPageContent;
import com.skyeye.dsform.entity.DsFormPageContentVo;
import com.skyeye.dsform.service.DsFormPageContentService;
import com.skyeye.dsform.service.DsFormPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: DsFormPageServiceImpl
 * @Description: 表单布局管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:36
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "表单布局管理", groupName = "动态表单")
public class DsFormPageServiceImpl extends SkyeyeBusinessServiceImpl<DsFormPageDao, DsFormPage> implements DsFormPageService {

    @Autowired
    private DsFormPageContentService dsFormPageContentService;

    @Autowired
    private AttrDefinitionService attrDefinitionService;

    @Override
    public void queryDsFormPageList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();

        QueryWrapper<DsFormPage> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(DsFormPage::getClassName), className);
        List<DsFormPage> dsFormPageList = list(wrapper);
        iAuthUserService.setName(dsFormPageList, "createId", "createName");
        iAuthUserService.setName(dsFormPageList, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(dsFormPageList);
        outputObject.settotal(dsFormPageList.size());
    }

    @Override
    public void createPrepose(DsFormPage entity) {
        Map<String, Object> business = BeanUtil.beanToMap(entity);
        String oddNumber = iCodeRuleService.getNextCodeByClassName(getClass().getName(), business);
        entity.setNumCode(oddNumber);
    }

    @Override
    public void deletePostpose(String id) {
        // 删除页面内容项信息
        dsFormPageContentService.deleteDsFormContentByPageId(id);
        jedisClientService.del(DsFormConstants.dsFormContentListByPageId(id));
    }

    @Override
    public DsFormPage selectById(String id) {
        DsFormPage dsFormPage = super.selectById(id);
        List<DsFormPageContent> dsFormPageContents = dsFormPageContentService.getDsFormPageContentByPageId(id);
        dsFormPage.setDsFormPageContents(dsFormPageContents);
        // 获取属性信息
        List<String> attrKeys = dsFormPageContents.stream().map(DsFormPageContent::getAttrKey).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(attrKeys)) {
            Map<String, AttrDefinition> attrDefinitionMap = attrDefinitionService.queryAttrDefinitionMap(dsFormPage.getClassName(), attrKeys);
            dsFormPageContents.forEach(dsFormPageContent -> {
                dsFormPageContent.setAttrDefinition(attrDefinitionMap.get(dsFormPageContent.getAttrKey()));
            });
        }
        return dsFormPage;
    }

    @Override
    public List<DsFormPage> selectByIds(String... ids) {
        List<DsFormPage> dsFormPageList = super.selectByIds(ids);
        Map<String, List<DsFormPageContent>> pageContentMap = dsFormPageContentService.getDsFormPageContentListByPageId(Arrays.asList(ids));
        dsFormPageList.forEach(dsFormPage -> {
            dsFormPage.setDsFormPageContents(pageContentMap.get(dsFormPage.getId()));
        });
        return dsFormPageList;
    }

    /**
     * 保存表单布局关联的组件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void writeDsFormPageContent(InputObject inputObject, OutputObject outputObject) {
        DsFormPageContentVo dsFormPageContentVo = inputObject.getParams(DsFormPageContentVo.class);
        List<DsFormPageContent> dsFormPageContentList = dsFormPageContentVo.getDsFormPageContentList();
        dsFormPageContentList.forEach(dsFormPageContent -> {
            dsFormPageContent.setPageId(dsFormPageContentVo.getPageId());
        });
        // 删除之前已经保存的
        dsFormPageContentService.deleteDsFormContentByPageId(dsFormPageContentVo.getPageId());
        // 保存新的组件关联信息
        String userId = inputObject.getLogParams().get("id").toString();
        dsFormPageContentService.createEntity(dsFormPageContentList, userId);
        // 刷新表单布局的缓存
        refreshCache(dsFormPageContentVo.getPageId());
    }

}
