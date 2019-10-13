package com.skyeye.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.MaterialUnitDao;
import com.skyeye.service.MaterialUnitService;

import net.sf.json.JSONArray;

@Service
public class MaterialUnitServiceImpl implements MaterialUnitService{
	
	@Autowired
	private MaterialUnitDao materialUnitDao;
	
	/**
     * 获取计量单位列表
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialUnitList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = materialUnitDao.queryMaterialUnitList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
	}

	/**
     * 新增计量单位
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value="transactionManager")
	public void insertMaterialUnitMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		Map<String, Object> nameBean = materialUnitDao.queryUnitGroupMationByName(map);
		if(nameBean == null || nameBean.isEmpty()){
			String userId = inputObject.getLogParams().get("id").toString();
			String groupId = ToolUtil.getSurFaceId();//计量单位组id
			String unitNameStr = map.get("unitNameStr").toString();
			//处理数据
			JSONArray jArray = JSONArray.fromObject(unitNameStr);
			Map<String, Object> entity;//计量单位信息
			List<Map<String, Object>> entitys = new ArrayList<>();//计量单位集合信息
			for(int i = 0; i < jArray.size(); i++){
				entity = jArray.getJSONObject(i);
				entity.put("id", ToolUtil.getSurFaceId());
				entity.put("groupId", groupId);
				entity.put("unitName", entity.containsKey("unitName") ? entity.get("unitName") : "未知");
				entity.put("unitNum", entity.containsKey("unitNum") ? entity.get("unitNum") : "1");
				entity.put("userId", userId);
				entity.put("baseUnit", 2);//默认不是基础单位
				entity.put("deleteFlag", 0);//默认未删除
				entitys.add(entity);
			}
			//加入基础单位
			entity = new HashMap<>();
			entity.put("id", ToolUtil.getSurFaceId());
			entity.put("groupId", groupId);
			entity.put("unitName", map.get("unitName"));
			entity.put("unitNum", 1);
			entity.put("userId", userId);
			entity.put("baseUnit", 1);//默认基础单位
			entity.put("deleteFlag", 0);//默认未删除
			entitys.add(entity);
			//组信息
			Map<String, Object> bean = new HashMap<>();
			bean.put("id", groupId);
			bean.put("userId", userId);
			bean.put("name", map.get("groupName"));
			bean.put("deleteFlag", 0);//默认未删除
			materialUnitDao.insertMaterialUnitGroupMation(bean);
			materialUnitDao.insertMaterialUnitMation(entitys);
		}else{
			outputObject.setreturnMessage("该组名已存在，请更换");
		}
	}

	/**
     * 删除计量单位
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	@Transactional(value="transactionManager")
	public void deleteMaterialUnitMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		materialUnitDao.deleteMaterialUnitGroupMationById(map);
		materialUnitDao.deleteMaterialUnitMationByGroupId(map);
	}

	/**
     * 编辑计量单位时进行回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialUnitMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		Map<String, Object> bean = materialUnitDao.queryMaterialUnitGroupMationToEditById(map);
		if(bean == null || bean.isEmpty()){
			outputObject.setreturnMessage("该数据不存在");
			return;
		}
		bean.put("unitList", materialUnitDao.queryMaterialUnitMationToEditById(map));
		outputObject.setBean(bean);
		outputObject.settotal(1);
	}

	/**
     * 编辑计量单位
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value="transactionManager")
	public void editMaterialUnitMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		Map<String, Object> nameBean = materialUnitDao.queryUnitGroupMationByNameAndId(map);
		if(nameBean == null || nameBean.isEmpty()){
			String userId = inputObject.getLogParams().get("id").toString();
			String groupId = map.get("id").toString();//计量单位组id
			String unitNameStr = map.get("unitNameStr").toString();
			//处理数据
			JSONArray jArray = JSONArray.fromObject(unitNameStr);
			Map<String, Object> entity;//计量单位信息
			List<Map<String, Object>> entitys = materialUnitDao.queryMaterialUnitMationToEditById(map);//计量单位集合信息
			//判断前端传来的副单位是否在数据库中存在
			int inSqlData = -1;
			for(Map<String, Object> en : entitys){
				if("1".equals(en.get("baseUnit").toString())){//如果计量单位是基础类型
					//如果即将修改的名称和数据库的名称一样，则不做修改，反之做修改
					if(!en.get("unitNameValue").toString().equals(map.get("unitName").toString())){
						map.put("unitNameValue", map.get("unitName"));
						materialUnitDao.editUnitMationById(map);
					}
				} else {//计量单位不是基础类型
					inSqlData = -1;
					for(int i = 0; i < jArray.size(); i++){
						entity = jArray.getJSONObject(i);
						//如果unitId为空，则新增
						if(ToolUtil.isBlank(entity.get("unitId").toString())){
							entity.put("id", ToolUtil.getSurFaceId());
							entity.put("groupId", groupId);
							entity.put("unitName", entity.containsKey("unitName") ? entity.get("unitName") : "未知");
							entity.put("unitNum", entity.containsKey("unitNum") ? entity.get("unitNum") : "1");
							entity.put("userId", userId);
							entity.put("baseUnit", 2);//默认不是基础单位
							entity.put("deleteFlag", 0);//默认未删除
							materialUnitDao.insertUnitMation(entity);
							jArray.remove(i);
						}else{
							//如果unitId不为空，判断是否在数据库中的集合内存在，不存在则删除，存在，则修改
							if(entity.get("unitId").toString().equals(en.get("unitIdValue").toString())){
								inSqlData = i;
								break;
							}
						}
					}
					//inSqlData!=-1说明在数据库中存在该数据，直接修改
					if(inSqlData != -1){
						entity = jArray.getJSONObject(inSqlData);
						en.put("unitNameValue", entity.containsKey("unitName") ? entity.get("unitName") : "未知");
						en.put("unitNum", entity.containsKey("unitNum") ? entity.get("unitNum") : "1");
						materialUnitDao.editUnitMationById(en);
						jArray.remove(inSqlData);
					}else{
						materialUnitDao.deleteUnitMationById(en);
					}
				}
			}
			
			//添加剩余副单位
			for(int i = 0; i < jArray.size(); i++){
				entity = jArray.getJSONObject(i);
				//如果unitId为空，则新增
				if(!entity.containsKey("unitId")){
					entity.put("id", ToolUtil.getSurFaceId());
					entity.put("groupId", groupId);
					entity.put("unitName", entity.containsKey("unitName") ? entity.get("unitName") : "未知");
					entity.put("unitNum", entity.containsKey("unitNum") ? entity.get("unitNum") : "1");
					entity.put("userId", userId);
					entity.put("baseUnit", 2);//默认不是基础单位
					entity.put("deleteFlag", 0);//默认未删除
					materialUnitDao.insertUnitMation(entity);
					jArray.remove(i);
				}
			}
			
			//组信息
			Map<String, Object> bean = new HashMap<>();
			bean.put("id", groupId);
			bean.put("name", map.get("groupName"));
			bean.put("userId", userId);
			materialUnitDao.editMaterialUnitGroupMationById(bean);
		}else{
			outputObject.setreturnMessage("该组名已存在，请更换");
		}
	}
	
}
