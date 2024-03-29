/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysTAreaDao;
import com.skyeye.eve.entity.userauth.area.SysTArea;
import com.skyeye.eve.service.SysTAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysTAreaServiceImpl
 * @Description: 区域管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/18 23:45
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysTAreaServiceImpl extends SkyeyeBusinessServiceImpl<SysTAreaDao, SysTArea> implements SysTAreaService {

    public static String SYS_ALL_T_AREA_LIST = "sys_all_t_area_list";

    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        TableSelectInfo selectInfo = inputObject.getParams(TableSelectInfo.class);
        return skyeyeBaseMapper.querySysTAreaList(selectInfo);
    }

    /**
     * 根据父id获取子节点信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAreaListByPId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String pId = map.get("pId").toString();
        String parentCode = "0";
        if (!StringUtils.equals("0", pId)) {
            // 如果父ID不是0，则查询的不是一级节点，则需要先获取编码code
            SysTArea sysTArea = selectById(pId);
            parentCode = sysTArea.getCodeId();
        }
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryAreaListByParentCode(parentCode);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 查询省市区数据
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPartAreaList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans;
        if (ToolUtil.isBlank(jedisClientService.get(SYS_ALL_T_AREA_LIST))) {
            beans = skyeyeBaseMapper.queryTAreaPhoneList(map);
            beans = ToolUtil.listToTree(beans, "codeId", "parentCodeId", "children");
            jedisClientService.set(SYS_ALL_T_AREA_LIST, JSONUtil.toJsonStr(beans));
        } else {
            beans = JSONUtil.toList(jedisClientService.get(SYS_ALL_T_AREA_LIST), null);
        }
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

}
