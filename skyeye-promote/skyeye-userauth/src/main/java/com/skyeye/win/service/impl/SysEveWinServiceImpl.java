/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exception.CustomException;
import com.skyeye.win.dao.SysEveWinDao;
import com.skyeye.win.entity.SysWin;
import com.skyeye.win.service.SysEveWinService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysEveWinServiceImpl
 * @Description: 服务信息管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:26
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "前台服务管理", groupName = "基础模块")
public class SysEveWinServiceImpl extends SkyeyeBusinessServiceImpl<SysEveWinDao, SysWin> implements SysEveWinService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryWinMationList(commonPageInfo);
        return beans;
    }

    @Override
    protected void deletePreExecution(String id) {
        Map<String, Object> bean = skyeyeBaseMapper.queryChildMationById(id);
        if (Integer.parseInt(bean.get("menuNum").toString()) > 0) {
            throw new CustomException("该服务存在功能菜单，请先进行菜单移除操作。");
        }
    }

    /**
     * 获取所有的服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveWinList(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> beans = skyeyeBaseMapper.querySysEveWinList();
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

}
