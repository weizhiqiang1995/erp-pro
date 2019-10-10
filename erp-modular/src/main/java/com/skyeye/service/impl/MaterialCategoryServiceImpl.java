package com.skyeye.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.MaterialCategoryDao;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.service.MaterialCategoryService;
import net.sf.json.JSONArray;

@Service
public class MaterialCategoryServiceImpl implements MaterialCategoryService{

	@Autowired
	private MaterialCategoryDao materialCategoryDao;
	
	@Autowired
	private JedisClientService jedisClient;

	/**
     * 获取产品类型信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialCategoryList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		List<Map<String, Object>> beans = materialCategoryDao.queryMaterialCategoryList(map);
		if(!beans.isEmpty()){
			outputObject.setBeans(beans);
			outputObject.settotal(beans.size());
		}
	}
	
	/**
	 * 
     * @Description: 新增产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void insertMaterialCategoryMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		Map<String, Object> bean = materialCategoryDao.queryMaterialCategoryMationByName(map);//查询是否已经存在该产品类型名称
		if(bean != null && !bean.isEmpty()){
			outputObject.setreturnMessage("该类型已存在，请更换");
		}else{
			Map<String, Object> itemCount = materialCategoryDao.queryMaterialCategoryBySimpleLevel(map);
			int thisOrderBy = Integer.parseInt(itemCount.get("simpleNum").toString()) + 1;
			map.put("orderBy", thisOrderBy);
			map.put("id", ToolUtil.getSurFaceId());
			map.put("status", "1");//默认启用
			map.put("createId", user.get("id"));
			map.put("createName", user.get("userName"));
			map.put("createTime", ToolUtil.getTimeAndToString());
			jedisClient.del(Constants.getSysMaterialCategoryRedisKeyById(map.get("parentId").toString()));//删除上线产品类型的redis
			materialCategoryDao.insertMaterialCategoryMation(map);
		}
	}
	
	/**
	 * 
     * @Description: 删除产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void deleteMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = materialCategoryDao.queryMaterialCategoryStateById(map);
		if("2".equals(bean.get("status").toString())){//禁用状态下可以删除
			Map<String, Object> user = inputObject.getLogParams();
			map.put("userId", user.get("id"));
			materialCategoryDao.deleteMaterialCategoryById(map);
			materialCategoryDao.deleteMaterialCategoryByParentId(map);
		}else{
			outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
		}
		
	}

	/**
	 * 
     * @Description: 启用产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void updateUpMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = materialCategoryDao.queryMaterialCategoryStateById(map);
		if("2".equals(bean.get("status").toString())){//禁用状态下可以启用
			Map<String, Object> user = inputObject.getLogParams();
			map.put("userId", user.get("id"));
			materialCategoryDao.updateUpMaterialCategoryById(map);
			bean = materialCategoryDao.queryMaterialCategoryById(map);	//查询该产品类型的父级id
			jedisClient.del(Constants.getSysMaterialCategoryRedisKeyById(bean.get("parentId").toString()));//删除上线产品类型的redis
		}else{
			outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
		}
	}

	/**
	 * 
     * @Description: 禁用产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void updateDownMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = materialCategoryDao.queryMaterialCategoryStateById(map);
		if("1".equals(bean.get("status").toString())){//启用状态下可以禁用
			Map<String, Object> user = inputObject.getLogParams();
			map.put("userId", user.get("id"));
			materialCategoryDao.updateDownMaterialCategoryById(map);
			bean = materialCategoryDao.queryMaterialCategoryById(map);	//查询该产品类型的父级id
			jedisClient.del(Constants.getSysMaterialCategoryRedisKeyById(bean.get("parentId").toString()));//删除上线产品类型的redis
		}else{
			outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
		}
	}

	/**
	 * 
     * @Description: 通过id查找对应的产品类型信息用以编辑
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void selectMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		Map<String, Object>	bean = materialCategoryDao.selectMaterialCategoryById(map);
		outputObject.setBean(bean);
		outputObject.settotal(1);
	}

	/**
	 * 
     * @Description: 编辑产品类型信息
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void editMaterialCategoryMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = materialCategoryDao.queryMaterialCategoryStateById(map);//查询这条产品类型的状态
		if("2".equals(bean.get("status").toString())){//禁用状态下可以编辑
			Map<String, Object> b = materialCategoryDao.queryMaterialCategoryMationByNameAndId(map);//查询产品类型名称是否存在
			if(b != null && !b.isEmpty()){
				outputObject.setreturnMessage("该类型已存在，请更换");
			}else{
				Map<String, Object> user = inputObject.getLogParams();
				map.put("userId", user.get("id"));
				materialCategoryDao.editMaterialCategoryMationById(map);
			}
		}else{
			outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
		}
	}
	
	/**
	 * 
     * @Description: 产品类型上移
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void editMaterialCategoryMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		Map<String, Object> bean = materialCategoryDao.queryMaterialCategoryUpMationById(map);//获取当前数据的同级分类下的上一条数据
		if(bean == null){
			outputObject.setreturnMessage("当前类型已经是首位，无须进行上移。");
		}else{
			//进行位置交换
			map.put("upOrderBy", bean.get("prevOrderBy"));
			bean.put("upOrderBy", bean.get("thisOrderBy"));
			bean.put("userId", user.get("id"));
			materialCategoryDao.editMaterialCategoryMationOrderNumUpById(map);	//该产品类型与上一条数据互换orderBy
			materialCategoryDao.editMaterialCategoryMationOrderNumUpById(bean);
			bean = materialCategoryDao.queryMaterialCategoryById(map);	//查询该产品类型的父级id
			jedisClient.del(Constants.getSysMaterialCategoryRedisKeyById(bean.get("parentId").toString()));//删除上线产品类型的redis
		}
	}
	
	/**
	 * 
     * @Description: 产品类型下移
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void editMaterialCategoryMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		Map<String, Object> bean = materialCategoryDao.queryMaterialCategoryDownMationById(map);//获取当前数据的同级分类下的下一条数据
		if(bean == null){
			outputObject.setreturnMessage("当前类型已经是末位，无须进行下移。");
		}else{
			//进行位置交换
			map.put("upOrderBy", bean.get("prevOrderBy"));
			bean.put("upOrderBy", bean.get("thisOrderBy"));
			bean.put("userId", user.get("id"));
			materialCategoryDao.editMaterialCategoryMationOrderNumUpById(map);//该产品类型与下一条数据互换orderBy
			materialCategoryDao.editMaterialCategoryMationOrderNumUpById(bean);
			bean = materialCategoryDao.queryMaterialCategoryById(map);	//查询该产品类型的父级id
			jedisClient.del(Constants.getSysMaterialCategoryRedisKeyById(bean.get("parentId").toString()));//删除上线产品类型的redis
		}
	}

	/**
	 * 
     * @Description: 获取已经上线的一级类型列表
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void queryFirstMaterialCategoryUpStateList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		List<Map<String, Object>> beans = new ArrayList<>();
		if(ToolUtil.isBlank(jedisClient.get(Constants.getSysMaterialCategoryRedisKeyById("")))){	//若缓存中无值
			beans = materialCategoryDao.queryFirstMaterialCategoryUpStateList(map);	//从数据库中查询
			jedisClient.set(Constants.getSysMaterialCategoryRedisKeyById(""), JSON.toJSONString(beans));	//将从数据库中查来的内容存到缓存中
		}else{
			beans = JSONArray.fromObject(jedisClient.get(Constants.getSysMaterialCategoryRedisKeyById("")).toString());
		}
		if(!beans.isEmpty()){
			outputObject.setBeans(beans);
			outputObject.settotal(beans.size());
		}
	}

	/**
	 * 
     * @Description: 获取上线的一级类型对应的上线的二级类型列表
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void querySecondMaterialCategoryUpStateList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		List<Map<String, Object>> beans = new ArrayList<>();
		if(ToolUtil.isBlank(jedisClient.get(Constants.getSysMaterialCategoryRedisKeyById(map.get("parentId").toString())))){//若缓存中无值
			beans = materialCategoryDao.querySecondMaterialCategoryUpStateList(map);	//从数据库中查询
			jedisClient.set(Constants.getSysMaterialCategoryRedisKeyById(map.get("parentId").toString()), JSON.toJSONString(beans));//将从数据库中查来的内容存到缓存中
		}else{
			beans = JSONArray.fromObject(jedisClient.get(Constants.getSysMaterialCategoryRedisKeyById(map.get("parentId").toString())).toString());
		}
		if(!beans.isEmpty()){
			outputObject.setBeans(beans);
			outputObject.settotal(beans.size());
		}
	}
	
	/**
	 * 
     * @Description: 获取所有的一级类型列表用以搜索条件
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@Override
	public void queryAllFirstMaterialCategoryStateList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		List<Map<String, Object>> beans = materialCategoryDao.queryAllFirstMaterialCategoryStateList(map);
		if(!beans.isEmpty()){
			outputObject.setBeans(beans);
			outputObject.settotal(beans.size());
		}
	}
	
}
