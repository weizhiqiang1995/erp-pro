/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.eve.dao.BarCodeDao;
import com.skyeye.eve.entity.barcode.BarCodeApiMation;
import com.skyeye.eve.entity.barcode.BarCodeMation;
import com.skyeye.eve.service.BarCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 批量新增条形码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
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
}
