/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.dao.DsFormDisplayTemplateDao;
import com.skyeye.dsform.entity.DsFormDisplayTemplate;
import com.skyeye.dsform.service.DsFormDisplayTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DsFormDisplayTemplateServiceImpl
 * @Description: 动态表单数据展示模板管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:22
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class DsFormDisplayTemplateServiceImpl extends SkyeyeBusinessServiceImpl<DsFormDisplayTemplateDao, DsFormDisplayTemplate> implements DsFormDisplayTemplateService {

    @Autowired
    private DsFormDisplayTemplateDao dsFormDisplayTemplateDao;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = dsFormDisplayTemplateDao.queryDsFormDisplayTemplateList(commonPageInfo);
        iSysDictDataService.setNameForMap(beans, "typeId", "typeName");
        return beans;
    }

    /**
     * 获取动态表单数据展示模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDisplayTemplateListToShow(InputObject inputObject, OutputObject outputObject) {
        List<DsFormDisplayTemplate> beans = list();
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

}
