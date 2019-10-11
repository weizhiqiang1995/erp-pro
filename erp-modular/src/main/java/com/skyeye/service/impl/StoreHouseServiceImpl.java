package com.skyeye.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.StoreHouseDao;
import com.skyeye.erp.util.ErpConstants;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.service.StoreHouseService;

import net.sf.json.JSONArray;

/**
 * @Author 奈何繁华如云烟
 * @Description TODO
 * @Date 2019/9/14 10:44
 */
@Service
public class StoreHouseServiceImpl implements StoreHouseService {

    @Autowired
    private StoreHouseDao storeHouseDao;
    
    @Autowired
	private JedisClientService jedisClient;

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
        String userId = inputObject.getLogParams().get("id").toString();
        params.put("userId", userId);
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
            jedisClient.del(jedisClient.get(ErpConstants.getStoreHouseRedisKeyByUserId(userId)));
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
        String userId = inputObject.getLogParams().get("id").toString();
        params.put("userId", userId);
        params.put("deleteFlag", "1");
        storeHouseDao.editStoreHouseByDeleteFlag(params);
        jedisClient.del(jedisClient.get(ErpConstants.getStoreHouseRedisKeyByUserId(userId)));
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
        String userId = inputObject.getLogParams().get("id").toString();
        params.put("userId", userId);
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
        jedisClient.del(jedisClient.get(ErpConstants.getStoreHouseRedisKeyByUserId(userId)));
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
        String userId = inputObject.getLogParams().get("id").toString();
        params.put("userId", userId);
        Map<String, Object> bean = storeHouseDao.queryStoreHouseByIsDefault(params);
        if(bean != null){
            outputObject.setreturnMessage("状态已改变，请勿重复操作！");
            return;
        }
        params.put("isDefault", "2");
        storeHouseDao.editStoreHouseByDefaultAll(params);
        params.put("isDefault", "1");
        storeHouseDao.editStoreHouseByDefault(params);
        jedisClient.del(jedisClient.get(ErpConstants.getStoreHouseRedisKeyByUserId(userId)));
    }

    /**
     * 获取所有仓库展示为下拉框
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	public void queyrStoreHouseListToSelect(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		String userId = inputObject.getLogParams().get("id").toString();
		map.put("userId", userId);
		List<Map<String, Object>> beans;
		if(ToolUtil.isBlank(jedisClient.get(ErpConstants.getStoreHouseRedisKeyByUserId(userId)))){//若缓存中无值
			beans = storeHouseDao.queyrStoreHouseListToSelect(map);	//从数据库中查询
			jedisClient.set(ErpConstants.getStoreHouseRedisKeyByUserId(userId), JSON.toJSONString(beans));//将从数据库中查来的内容存到缓存中
		}else{
			beans = JSONArray.fromObject(jedisClient.get(ErpConstants.getStoreHouseRedisKeyByUserId(userId)).toString());
		}
		if(!beans.isEmpty()){
			outputObject.setBeans(beans);
			outputObject.settotal(beans.size());
		}
	}

	/**
	 * 查看仓库详情
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
