package com.skyeye.service.impl;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.StoreHouseDao;
import com.skyeye.service.StoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author 奈何繁华如云烟
 * @Description TODO
 * @Date 2019/9/14 10:44
 */
@Service
public class StoreHouseServiceImpl implements StoreHouseService {

    @Autowired
    private StoreHouseDao storeHouseDao;

    /**
     * 获取仓库信息列表
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryStoreHouseByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        List<Map<String, Object>> beans = storeHouseDao.queryStoreHouseByList(params);
        if(!beans.isEmpty()){
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 添加仓库信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void insertStoreHouse(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        Map<String, Object> bean = storeHouseDao.queryStoreHouseByName(params);
        if(bean == null){
            Map<String, Object> user = inputObject.getLogParams();
            params.put("id", ToolUtil.getSurFaceId());
            params.put("tenantId", user.get("id"));
            params.put("createTime", ToolUtil.getTimeAndToString());
            storeHouseDao.insertStoreHouse(params);
        }else{
            outputObject.setreturnMessage("该仓库信息已存在，请确认！");
        }

    }

    /**
     * 查询单个仓库信息，用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryStoreHouseById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = storeHouseDao.queryStoreHouseById(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到信息，请重试！");
        }else{
            outputObject.setBean(bean);
            outputObject.settotal(1);
        }
    }

    /**
     * 删除仓库信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void deleteStoreHouseById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        storeHouseDao.editStoreHouseBydeleteFlag(params);
    }

    /**
     * 编辑仓库信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void editStoreHouseById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        storeHouseDao.editStoreHouseById(params);
    }

    /**
     * 设置仓库为默认状态
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void editStoreHouseByDefault(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        storeHouseDao.editStoreHouseByDefault(params);
    }
}
