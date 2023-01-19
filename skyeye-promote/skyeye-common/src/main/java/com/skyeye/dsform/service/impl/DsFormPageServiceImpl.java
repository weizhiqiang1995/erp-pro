/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.DsFormConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.HttpClient;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dsform.dao.DsFormPageDao;
import com.skyeye.dsform.entity.DsFormPage;
import com.skyeye.dsform.service.DsFormPageContentService;
import com.skyeye.dsform.service.DsFormPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryDsFormPageList(commonPageInfo);
        return beans;
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
    public DsFormPage getDataFromDb(String id) {
        DsFormPage dsFormPage = super.getDataFromDb(id);
        // todo 后续获取页面关联的组件信息
        return dsFormPage;
    }

    @Override
    public List<DsFormPage> getDataFromDb(List<String> idList) {
        List<DsFormPage> dsFormPageList = super.getDataFromDb(idList);
        // todo 后续获取页面关联的组件信息
        return dsFormPageList;
    }

    /**
     * 验证接口是否正确
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryInterfaceIsTrueOrNot(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("loginPCIp", PutObject.getRequest().getParameter("loginPCIp"));
        map.put("userToken", PutObject.getRequest().getParameter("userToken"));
        String str = HttpClient.doPost(map.get("interfa").toString(), map);
        if (!ToolUtil.isBlank(str)) {
            if (ToolUtil.isJson(str)) {
                Map<String, Object> json = JSONUtil.toBean(str, null);
                if ("0".equals(json.get("returnCode").toString())) {
                    if (!ToolUtil.isBlank(json.get("rows").toString())) {
                        map.put("aData", json.get("rows").toString());
                        outputObject.setBean(map);
                        outputObject.settotal(CommonNumConstants.NUM_ONE);
                    } else {
                        outputObject.setreturnMessage("该接口没有拿到数据，请重新填写接口！");
                    }
                } else {
                    outputObject.setreturnMessage("该接口无效，请重新填写接口!");
                }
            } else {
                outputObject.setreturnMessage("接口拿到的不是json串，请重新填写接口!");
            }
        } else {
            outputObject.setreturnMessage("该接口无效，请重新填写接口!");
        }
    }

    /**
     * 获取接口中的值
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryInterfaceValue(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("loginPCIp", PutObject.getRequest().getParameter("loginPCIp"));
        map.put("userToken", PutObject.getRequest().getParameter("userToken"));
        String str = HttpClient.doPost(map.get("interfa").toString(), map);
        Map<String, Object> json = JSONUtil.toBean(str, null);
        map.put("aData", json.get("rows").toString());
        outputObject.setBean(map);
    }

}
