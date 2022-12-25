/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.BarCodeDao;
import com.skyeye.eve.entity.barcode.BarCodeApiMation;
import com.skyeye.eve.entity.barcode.BarCodeMation;
import com.skyeye.eve.service.BarCodeService;
import com.skyeye.sdk.data.service.IDataService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BarCodeServiceImpl
 * @Description: 条形码服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/28 9:42
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class BarCodeServiceImpl extends ServiceImpl<BarCodeDao, BarCodeMation> implements BarCodeService {

    @Autowired
    private BarCodeDao barCodeDao;

    @Autowired
    private IDataService iDataService;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 批量新增条形码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeBarCode(InputObject inputObject, OutputObject outputObject) {
        BarCodeApiMation barCodeApiMation = inputObject.getParams(BarCodeApiMation.class);
        List<BarCodeMation> barCodeList = barCodeApiMation.getBarCodeList();
        String createTime = DateUtil.getTimeAndToString();
        barCodeList.forEach(barCodeMation -> {
            barCodeMation.setSpringApplicationName(barCodeApiMation.getSpringApplicationName());
            barCodeMation.setCodeImplClass(barCodeApiMation.getCodeImplClass());
            barCodeMation.setCreateTime(createTime);
        });
        this.saveBatch(barCodeList);
    }

    /**
     * 根据条形码code获取数据信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void getDataByBarCode(InputObject inputObject, OutputObject outputObject) {
        String barCode = inputObject.getParams().get("barCode").toString();
        QueryWrapper<BarCodeMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(BarCodeMation::getCodeNum), barCode);
        BarCodeMation barCodeMation = barCodeDao.selectOne(queryWrapper);

        // 根据服务名获取服务实例
        List<ServiceInstance> allInstances = discoveryClient.getInstances(barCodeMation.getSpringApplicationName());
        if (CollectionUtils.isEmpty(allInstances)) {
            outputObject.setreturnMessage("this service[{}] has no instance.", barCodeMation.getSpringApplicationName());
            return;
        }

        // 调用SDK服务获取数据信息
        Map<String, Object> result = iDataService.getDataByObjectId(allInstances.get(0).getUri(), barCodeMation.getObjectId(),
            barCodeMation.getCodeImplClass());
        outputObject.setBean(result);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据业务数据id获取条形码数据
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryBarCodeByObjectIds(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String springApplicationName = params.get("springApplicationName").toString();
        String codeImplClass = params.get("codeImplClass").toString();
        List<String> objectIds = JSONArray.fromObject(params.get("objectIds").toString());
        QueryWrapper<BarCodeMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(BarCodeMation::getSpringApplicationName), springApplicationName);
        queryWrapper.eq(MybatisPlusUtil.toColumns(BarCodeMation::getCodeImplClass), codeImplClass);
        queryWrapper.in(MybatisPlusUtil.toColumns(BarCodeMation::getObjectId), objectIds);
        List<BarCodeMation> barCodeMationList = super.list(queryWrapper);
        outputObject.setBeans(barCodeMationList);
        outputObject.settotal(barCodeMationList.size());
    }
}
