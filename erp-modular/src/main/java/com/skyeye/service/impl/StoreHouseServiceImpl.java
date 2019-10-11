package com.skyeye.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.StoreHouseDao;
import com.skyeye.service.StoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Map<String, Object>> beans = storeHouseDao.queryStoreHouseByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加仓库信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void insertStoreHouse(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        params.put("userId", user.get("id"));
        Map<String, Object> bean = storeHouseDao.queryStoreHouseByName(params);
        if(bean == null){
            params.put("id", ToolUtil.getSurFaceId());
            if(params.get("isDefault").toString().equals("1")){
                params.put("isDefault", "2");
                storeHouseDao.editStoreHouseByDefaultAll(params);
                params.put("isDefault", "1");
            }
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
    @Transactional(value="transactionManager")
    public void deleteStoreHouseById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("deleteFlag", "1");
        storeHouseDao.editStoreHouseByDeleteFlag(params);
    }

    /**
     * 编辑仓库信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editStoreHouseById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        params.put("userId", user.get("id"));
        Map<String, Object> houseName = storeHouseDao.queryStoreHouseByIdAndName(params);
        if(houseName != null){
            outputObject.setreturnMessage("仓库名称已存在！");
            return;
        }
        if(params.get("isDefault").toString().equals("1")){
            params.put("isDefault", "2");
            storeHouseDao.editStoreHouseByDefaultAll(params);
            params.put("isDefault", "1");
        }
        storeHouseDao.editStoreHouseById(params);
    }

    /**
     * 设置仓库为默认状态
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editStoreHouseByDefault(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        params.put("userId", user.get("id"));
        Map<String, Object> bean = storeHouseDao.queryStoreHouseByIsDefault(params);
        if(bean != null){
            outputObject.setreturnMessage("状态已改变，请勿重复操作！");
            return;
        }
        params.put("isDefault", "2");
        storeHouseDao.editStoreHouseByDefaultAll(params);
        params.put("isDefault", "1");
        storeHouseDao.editStoreHouseByDefault(params);
    }

    /**
     * 查看仓库想详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryStoreHouseByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = storeHouseDao.queryStoreHouseByIdAndInfo(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }
}
