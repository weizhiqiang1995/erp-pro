/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.operate.dao.OperateDao;
import com.skyeye.operate.entity.Operate;
import com.skyeye.operate.service.OperateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: OperateServiceImpl
 * @Description: 操作管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/29 18:07
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class OperateServiceImpl extends SkyeyeBusinessServiceImpl<OperateDao, Operate> implements OperateService {

    /**
     * 获取操作列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryOperateList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();

        QueryWrapper<Operate> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(Operate::getClassName), className);
        List<Operate> operateList = list(wrapper);
        iAuthUserService.setName(operateList, "createId", "createName");
        iAuthUserService.setName(operateList, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(operateList);
        outputObject.settotal(operateList.size());
    }

}
