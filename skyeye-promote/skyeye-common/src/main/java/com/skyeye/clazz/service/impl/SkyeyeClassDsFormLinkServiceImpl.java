/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.clazz.dao.SkyeyeClassDsFormLinkDao;
import com.skyeye.clazz.entity.dsformlink.SkyeyeClassDsFormLinkApiMation;
import com.skyeye.clazz.entity.dsformlink.SkyeyeClassDsFormLinkMation;
import com.skyeye.clazz.rest.DsFormPageContentService;
import com.skyeye.clazz.rest.DsFormPageService;
import com.skyeye.clazz.service.SkyeyeClassDsFormLinkService;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.service.IAuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SkyeyeClassDsFormLinkServiceImpl
 * @Description: 动态表单的服务类注册服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SkyeyeClassDsFormLinkServiceImpl extends ServiceImpl<SkyeyeClassDsFormLinkDao, SkyeyeClassDsFormLinkMation> implements SkyeyeClassDsFormLinkService {

    @Autowired
    private SkyeyeClassDsFormLinkDao skyeyeClassDsFormLinkDao;

    @Autowired
    private DsFormPageService dsFormPageService;

    @Autowired
    private DsFormPageContentService dsFormPageContentService;

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 动态表单的服务类注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeDsFormLink(InputObject inputObject, OutputObject outputObject) {
        SkyeyeClassDsFormLinkApiMation skyeyeClassDsFormLinkApiMation = inputObject.getParams(SkyeyeClassDsFormLinkApiMation.class);

        // 获取数据库中的数据
        QueryWrapper<SkyeyeClassDsFormLinkMation> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassDsFormLinkMation::getAppId), skyeyeClassDsFormLinkApiMation.getAppId());
        List<SkyeyeClassDsFormLinkMation> oldList = super.list(wrapper);
        Map<String, String> classNameToDsFormId = oldList.stream().filter(bean -> !ToolUtil.isBlank(bean.getDsFormPageIds())).collect(Collectors.toMap(SkyeyeClassDsFormLinkMation::getClassName, SkyeyeClassDsFormLinkMation::getDsFormPageIds));
        List<String> oldKeys = oldList.stream().map(bean -> bean.getClassName() + bean.getServiceName()).collect(Collectors.toList());

        // 获取入参的数据
        List<SkyeyeClassDsFormLinkMation> classNameList = skyeyeClassDsFormLinkApiMation.getClassNameList();
        for (SkyeyeClassDsFormLinkMation classNameBean : classNameList) {
            classNameBean.setAppId(skyeyeClassDsFormLinkApiMation.getAppId());
            classNameBean.setAppName(skyeyeClassDsFormLinkApiMation.getAppName());
            DataCommonUtil.setCommonDataByGenericity(classNameBean, "0dc9dd4cd4d446ae9455215fe753c44e");
            DataCommonUtil.setId(classNameBean);
        }
        List<String> newKeys = classNameList.stream().map(bean -> bean.getClassName() + bean.getServiceName()).collect(Collectors.toList());

        // (旧数据 - 新数据) 从数据库删除
        List<SkyeyeClassDsFormLinkMation> deleteBeans = oldList.stream().filter(item -> !newKeys.contains(item.getClassName() + item.getServiceName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteBeans)) {
            List<String> classNames = deleteBeans.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());
            QueryWrapper<SkyeyeClassDsFormLinkMation> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassDsFormLinkMation::getAppId), skyeyeClassDsFormLinkApiMation.getAppId());
            deleteWrapper.in(MybatisPlusUtil.toColumns(SkyeyeClassDsFormLinkMation::getClassName), classNames);
            remove(deleteWrapper);
        }

        // (新数据 - 旧数据) 添加到数据库
        List<SkyeyeClassDsFormLinkMation> addBeans = classNameList.stream().filter(item -> !oldKeys.contains(item.getClassName() + item.getServiceName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addBeans)) {
            addBeans.forEach(bean -> {
                bean.setDsFormPageIds(classNameToDsFormId.get(bean.getClassName()));
            });
            saveBatch(addBeans);
        }
    }

    /**
     * 获取业务逻辑表单关联列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDsFormLinkList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        List<Map<String, Object>> beans = skyeyeClassDsFormLinkDao.queryDsFormLinkList(commonPageInfo);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 获取业务逻辑表单关联信息详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDsFormLinkMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> bean = skyeyeClassDsFormLinkDao.queryDsFormLinkMationById(id, StringUtils.EMPTY);
        if (CollectionUtils.isEmpty(bean)) {
            outputObject.setreturnMessage("this data is non-exits.");
            return;
        }
        String dsFormPageIdsStr = bean.get("dsFormPageIds").toString();
        if (!ToolUtil.isBlank(dsFormPageIdsStr)) {
            String[] dsFormPageIds = dsFormPageIdsStr.split(",");
            List<Map<String, Object>> dsFormPageMations = new ArrayList<>();
            Arrays.asList(dsFormPageIds).forEach(dsFormPageId -> {
                // 获取表单页面信息
                Map<String, Object> formPageMation = ExecuteFeignClient.get(() -> dsFormPageService.queryDsFormPageById(dsFormPageId)).getBean();
                if (!CollectionUtils.isEmpty(formPageMation)) {
                    // 表单页面信息不为空
                    dsFormPageMations.add(formPageMation);
                }
            });
            bean.put("dsFormPageMations", dsFormPageMations);
        }
        iAuthUserService.setNameByIdBean(bean, "createId", "createName");
        iAuthUserService.setNameByIdBean(bean, "lastUpdateId", "lastUpdateName");
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑业务逻辑表单关联信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editDsFormLinkMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("userId", inputObject.getLogParams().get("id"));
        map.put("lastUpdateTime", DateUtil.getTimeAndToString());
        skyeyeClassDsFormLinkDao.editDsFormLinkMationById(map);
    }

    /**
     * 根据code/id获取关联的动态表单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDsFormLinkListByCode(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("dsFormObjectRelationId").toString();
        String code = map.get("dsFormObjectRelationCode").toString();
        if (ToolUtil.isBlank(id) && ToolUtil.isBlank(code)) {
            outputObject.setreturnMessage("错误的参数.");
            return;
        }
        // 获取与动态表单的关联关系
        Map<String, Object> bean = skyeyeClassDsFormLinkDao.queryDsFormLinkMationById(id, code);
        if (CollectionUtils.isEmpty(bean)) {
            return;
        }
        String dsFormPageIdsStr = bean.get("dsFormPageIds").toString();
        if (!ToolUtil.isBlank(dsFormPageIdsStr)) {
            String[] dsFormPageIds = dsFormPageIdsStr.split(",");
            // 返回给前台的值
            List<Map<String, Object>> dsFormPageMations = new ArrayList<>();
            for (String dsFormPageId : Arrays.asList(dsFormPageIds)) {
                // 获取表单页面信息
                Map<String, Object> formPageMation = ExecuteFeignClient.get(() -> dsFormPageService.queryDsFormPageById(dsFormPageId)).getBean();
                if (!CollectionUtils.isEmpty(formPageMation)) {
                    // 表单页面信息不为空
                    formPageMation.put("content", ExecuteFeignClient.get(() -> dsFormPageContentService.queryFormPageContentByPageId(formPageMation.get("id").toString())).getRows());
                    dsFormPageMations.add(formPageMation);
                }
            }
            outputObject.setBeans(dsFormPageMations);
            outputObject.settotal(dsFormPageMations.size());
        }
    }

}
