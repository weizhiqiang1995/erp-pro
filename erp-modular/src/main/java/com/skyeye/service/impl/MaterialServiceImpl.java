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
import com.skyeye.dao.MaterialDao;
import com.skyeye.service.MaterialService;

import net.sf.json.JSONArray;

@Service
public class MaterialServiceImpl implements MaterialService{
	
	@Autowired
	private MaterialDao materialDao;

	/**
     * 获取产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = materialDao.queryMaterialListByUserId(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
	}

	/**
     * 获取租户拥有的产品计量单位
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialUnitListToSelectByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = materialDao.queryMaterialUnitListToSelectByUserId(params);
        for(Map<String, Object> bean : beans){
        	bean.put("unitList", materialDao.queryMaterialUnitById(bean));
        }
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
	}

	/**
     * 新增产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value="transactionManager")
	public void insertMaterialMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		String materialNormsStr = map.get("materialNormsStr").toString();
		if(ToolUtil.isJson(materialNormsStr)){
			map.put("tenantId", inputObject.getLogParams().get("id"));//租户id
			Map<String, Object> inSql = materialDao.queryMaterialByNameAndModel(map);
			if(inSql != null && !inSql.isEmpty()){
				outputObject.setreturnMessage("同种型号的产品已经存在.");
				return;
			}
			//产品对象
			Map<String, Object> material = new HashMap<>();
			//产品id
			String materialId = ToolUtil.getSurFaceId();
			//处理数据
			JSONArray jArray = JSONArray.fromObject(materialNormsStr);
			//产品单位，是多个还是单个,  1.单个  2.多个
			int unit = Integer.parseInt(map.get("unit").toString());
			//计量单位名称  当unit=1时，必填，当不是多单位时，必填，定义在这方便下面产品信息使用
			String unitName = "";
			//计量单位组id  当unit=2时，必填
			String unitGroupId = "";
			//首选出库单位，首选入库单位
			String firstOutUnit = "", firstInUnit = "";
			if(unit == 1){//非多单位
				if(jArray.isEmpty()){
					outputObject.setreturnMessage("产品规格信息不能为空.");
					return;
				}
				Map<String, Object> bean = jArray.getJSONObject(0);
				unitName = bean.get("unitName").toString();
				if(ToolUtil.isBlank(unitName)){
					outputObject.setreturnMessage("计量单位不能为空.");
					return;
				}
				Map<String, Object> entity = new HashMap<>();
				entity.put("id", ToolUtil.getSurFaceId());
				entity.put("materialId", materialId);//产品id
				entity.put("safetyTock", bean.get("safetyTock"));//安全存量
				entity.put("retailPrice", bean.get("retailPrice"));//零售价
				entity.put("lowPrice", bean.get("lowPrice"));//最低售价
				entity.put("estimatePurchasePrice", bean.get("estimatePurchasePrice"));//预计采购价
				entity.put("salePrice", bean.get("salePrice"));//销售价
				entity.put("deleteFlag", 0);//默认状态
				entity.put("createTime", ToolUtil.getTimeAndToString());//创建时间
				materialDao.insertMaterialNorms(entity);
			}else if(unit == 2){//多单位
				unitGroupId = map.get("unitGroupId").toString();
				firstOutUnit = map.get("firstOutUnit").toString();
				firstInUnit = map.get("firstInUnit").toString();
				if(ToolUtil.isBlank(unitGroupId)){
					outputObject.setreturnMessage("请选择单位.");
					return;
				}
				if(ToolUtil.isBlank(firstOutUnit)){
					outputObject.setreturnMessage("请选择首选出库单位.");
					return;
				}
				if(ToolUtil.isBlank(firstInUnit)){
					outputObject.setreturnMessage("请选择首选入库单位.");
					return;
				}
				//产品规格集合对象
				List<Map<String, Object>> materialNorms = new ArrayList<>();
				//产品规格实体对象，jsonArray实体对象
				Map<String, Object> entity, bean;
				for(int i = 0; i < jArray.size(); i++){
					bean = jArray.getJSONObject(i);
					entity = materialDao.queryMaterialUnitByUnitId(bean);
					if(entity != null && !entity.isEmpty()){
						entity.put("id", ToolUtil.getSurFaceId());
						entity.put("unitId", bean.get("unitId"));//计量单位id
						entity.put("materialId", materialId);//产品id
						entity.put("safetyTock", bean.get("safetyTock"));//安全存量
						entity.put("retailPrice", bean.get("retailPrice"));//零售价
						entity.put("lowPrice", bean.get("lowPrice"));//最低售价
						entity.put("estimatePurchasePrice", bean.get("estimatePurchasePrice"));//预计采购价
						entity.put("salePrice", bean.get("salePrice"));//销售价
						entity.put("deleteFlag", 0);//默认状态  删除标记，0未删除，1删除
						entity.put("createTime", ToolUtil.getTimeAndToString());//创建时间
						materialNorms.add(entity);
					}
				}
				if(materialNorms.isEmpty()){
					outputObject.setreturnMessage("产品规格信息不能为空.");
					return;
				}
				materialDao.insertMaterialNormsList(materialNorms);
			}else{
				outputObject.setreturnMessage("参数错误.");
				return;
			}
			
			material.put("id", materialId);
			material.put("materialName", map.get("materialName"));//产品名称
			material.put("categoryId", map.get("categoryId"));//一级分类id
			material.put("categoryIdSec", map.get("categoryIdSec"));//二级分类id
			material.put("model", map.get("model"));//型号
			material.put("remark", map.get("remark"));//备注
			material.put("unit", unit);//产品单位，是多个还是单个,  1.单个  2.多个
			material.put("unitName", unitName);//计量单位  当unit=1时，必填
			material.put("unitGroupId", unitGroupId);//计量单位组id  当unit=2时，必填
			material.put("firstInUnit", firstInUnit);//首选入库单位
			material.put("firstOutUnit", firstOutUnit);//首选出库单位
			material.put("enabled", 1);//默认状态  启用 0-禁用  1-启用
			material.put("tenantId", inputObject.getLogParams().get("id"));//租户id
			material.put("deleteFlag", 0);//默认状态  删除标记，0未删除，1删除
			material.put("createTime", ToolUtil.getTimeAndToString());//创建时间
			materialDao.insertMaterialMation(material);
		} else {
			outputObject.setreturnMessage("数据格式错误");
		}
	}

	/**
     * 禁用产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	@Transactional(value="transactionManager")
	public void editMaterialEnabledToDisablesById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		Map<String, Object> bean = materialDao.queryMaterialEnabledByIdAndUserId(map);
		if(bean != null && !bean.isEmpty()){
			if("1".equals(bean.get("enabled").toString())){//启用状态下可以禁用
				//禁用
				materialDao.editMaterialEnabledToDisablesById(map);
			}
		}else{
			outputObject.setreturnMessage("该数据不存在.");
		}
	}

	/**
     * 启用产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	@Transactional(value="transactionManager")
	public void editMaterialEnabledToEnablesById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		Map<String, Object> bean = materialDao.queryMaterialEnabledByIdAndUserId(map);
		if(bean != null && !bean.isEmpty()){
			if("0".equals(bean.get("enabled").toString())){//禁用状态下可以启用
				//启用
				materialDao.editMaterialEnabledToEnablesById(map);
			}
		}else{
			outputObject.setreturnMessage("该数据不存在.");
		}
	}

	/**
     * 删除产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	@Transactional(value="transactionManager")
	public void deleteMaterialMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		Map<String, Object> bean = materialDao.queryMaterialDeleteFlagByIdAndUserId(map);
		if(bean != null && !bean.isEmpty()){//未删除状态下可以删除
			//删除产品
			materialDao.deleteMaterialMationById(map);
			//删除产品规格
			materialDao.deleteMaterialNormsMationById(map);
		}else{
			outputObject.setreturnMessage("该数据不存在.");
		}
	}

	/**
     * 产品信息详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialMationDetailsById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		//获取产品信息
		Map<String, Object> bean = materialDao.queryMaterialMationDetailsById(map);
		if(bean != null && !bean.isEmpty()){
			//获取产品规格参数信息
			List<Map<String, Object>> norms = materialDao.queryMaterialNormsMationDetailsById(bean);
			bean.put("norms", norms);
			outputObject.setBean(bean);
			outputObject.settotal(1);
		}else{
			outputObject.setreturnMessage("该数据已不存在.");
		}
	}

	/**
     * 编辑产品信息进行回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("userId", inputObject.getLogParams().get("id"));
		//获取产品信息
		Map<String, Object> bean = materialDao.queryMaterialMationToEditById(map);
		if(bean != null && !bean.isEmpty()){
			//获取产品规格参数信息
			List<Map<String, Object>> norms = materialDao.queryMaterialNormsMationToEditById(bean);
			bean.put("norms", norms);
			outputObject.setBean(bean);
			outputObject.settotal(1);
		}else{
			outputObject.setreturnMessage("该数据已不存在.");
		}
	}

	/**
     * 编辑产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value="transactionManager")
	public void editMaterialMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		String materialNormsStr = map.get("materialNormsStr").toString();
		if(ToolUtil.isJson(materialNormsStr)){
			map.put("userId", inputObject.getLogParams().get("id"));//租户id
			Map<String, Object> inSql = materialDao.queryMaterialByNameAndModelAndId(map);
			if(inSql != null && !inSql.isEmpty()){
				outputObject.setreturnMessage("同种型号的产品已经存在.");
				return;
			}
			//产品对象
			Map<String, Object> material = new HashMap<>();
			//产品id
			String materialId = map.get("id").toString();
			//处理数据
			JSONArray jArray = JSONArray.fromObject(materialNormsStr);
			//产品单位，是多个还是单个,  1.单个  2.多个
			int unit = Integer.parseInt(map.get("unit").toString());
			//计量单位名称  当unit=1时，必填，当不是多单位时，必填，定义在这方便下面产品信息使用
			String unitName = "";
			//计量单位组id  当unit=2时，必填
			String unitGroupId = "";
			//首选出库单位，首选入库单位
			String firstOutUnit = "", firstInUnit = "";
			
			//获取原始的产品数据
			Map<String, Object> baseMation = materialDao.queryMaterialById(map);
			List<Map<String, Object>> baseNormsMation = materialDao.queryMaterialNormsById(map);
			if(unit == 1){//非多单位
				if(jArray.isEmpty()){
					outputObject.setreturnMessage("产品规格信息不能为空.");
					return;
				}
				Map<String, Object> bean = jArray.getJSONObject(0);
				unitName = bean.get("unitName").toString();
				if(ToolUtil.isBlank(unitName)){
					outputObject.setreturnMessage("计量单位不能为空.");
					return;
				}
				Map<String, Object> entity = new HashMap<>();
				entity.put("safetyTock", bean.get("safetyTock"));//安全存量
				entity.put("retailPrice", bean.get("retailPrice"));//零售价
				entity.put("lowPrice", bean.get("lowPrice"));//最低售价
				entity.put("estimatePurchasePrice", bean.get("estimatePurchasePrice"));//预计采购价
				entity.put("salePrice", bean.get("salePrice"));//销售价
				
				//即将修改的单位类型和原始的单位类型不一样
				if(!String.valueOf(unit).equals(baseMation.get("unit").toString())){
					//删除产品规格
					materialDao.deleteMaterialNormsMationById(map);
					entity.put("id", ToolUtil.getSurFaceId());
					entity.put("deleteFlag", 0);//默认状态
					entity.put("createTime", ToolUtil.getTimeAndToString());//创建时间
					entity.put("materialId", materialId);//产品id
					materialDao.insertMaterialNorms(entity);
				}else{
					entity.put("id", baseNormsMation.get(0).get("id"));
					materialDao.editMaterialNormsById(entity);
				}
			}else if(unit == 2){//多单位
				unitGroupId = map.get("unitGroupId").toString();
				firstOutUnit = map.get("firstOutUnit").toString();
				firstInUnit = map.get("firstInUnit").toString();
				if(ToolUtil.isBlank(unitGroupId)){
					outputObject.setreturnMessage("请选择单位.");
					return;
				}
				if(ToolUtil.isBlank(firstOutUnit)){
					outputObject.setreturnMessage("请选择首选出库单位.");
					return;
				}
				if(ToolUtil.isBlank(firstInUnit)){
					outputObject.setreturnMessage("请选择首选入库单位.");
					return;
				}
				//即将修改的单位类型和原始的单位类型不一样
				if(!String.valueOf(unit).equals(baseMation.get("unit").toString())){
					//删除产品规格
					materialDao.deleteMaterialNormsMationById(map);
				}
				//即将修改的单位组和原始的不一样
				if(!unitGroupId.equals(baseMation.get("unitGroupId").toString())){
					//删除产品规格
					materialDao.deleteMaterialNormsMationById(map);
					//产品规格集合对象
					List<Map<String, Object>> materialNorms = new ArrayList<>();
					//产品规格实体对象，jsonArray实体对象
					Map<String, Object> entity, bean;
					for(int i = 0; i < jArray.size(); i++){
						bean = jArray.getJSONObject(i);
						entity = materialDao.queryMaterialUnitByUnitId(bean);
						if(entity != null && !entity.isEmpty()){
							entity.put("id", ToolUtil.getSurFaceId());
							entity.put("unitId", bean.get("unitId"));//计量单位id
							entity.put("materialId", materialId);//产品id
							entity.put("safetyTock", bean.get("safetyTock"));//安全存量
							entity.put("retailPrice", bean.get("retailPrice"));//零售价
							entity.put("lowPrice", bean.get("lowPrice"));//最低售价
							entity.put("estimatePurchasePrice", bean.get("estimatePurchasePrice"));//预计采购价
							entity.put("salePrice", bean.get("salePrice"));//销售价
							entity.put("deleteFlag", 0);//默认状态  删除标记，0未删除，1删除
							entity.put("createTime", ToolUtil.getTimeAndToString());//创建时间
							materialNorms.add(entity);
						}
					}
					if(!materialNorms.isEmpty()){
						materialDao.insertMaterialNormsList(materialNorms);
					}
				}else{
					//一样的话，则修改
					//产品规格实体对象，jsonArray实体对象
					Map<String, Object> entity, bean;
					for(int i = 0; i < jArray.size(); i++){
						bean = jArray.getJSONObject(i);
						entity = materialDao.queryMaterialUnitByUnitId(bean);
						if(entity != null && !entity.isEmpty()){
							entity.put("unitId", bean.get("unitId"));//计量单位id
							entity.put("safetyTock", bean.get("safetyTock"));//安全存量
							entity.put("retailPrice", bean.get("retailPrice"));//零售价
							entity.put("lowPrice", bean.get("lowPrice"));//最低售价
							entity.put("estimatePurchasePrice", bean.get("estimatePurchasePrice"));//预计采购价
							entity.put("salePrice", bean.get("salePrice"));//销售价
							String id = "";
							for(Map<String, Object> item : baseNormsMation){
								if(bean.get("unitId").toString().equals(item.get("unitId").toString())){
									id = item.get("id").toString();
									break;
								}
							}
							entity.put("id", id);
							materialDao.editMaterialNormsById(entity);
						}
					}
				}
			}else{
				outputObject.setreturnMessage("参数错误.");
				return;
			}
			
			material.put("id", materialId);
			material.put("materialName", map.get("materialName"));//产品名称
			material.put("categoryId", map.get("categoryId"));//一级分类id
			material.put("categoryIdSec", map.get("categoryIdSec"));//二级分类id
			material.put("model", map.get("model"));//型号
			material.put("remark", map.get("remark"));//备注
			material.put("unit", unit);//产品单位，是多个还是单个,  1.单个  2.多个
			material.put("unitName", unitName);//计量单位  当unit=1时，必填
			material.put("unitGroupId", unitGroupId);//计量单位组id  当unit=2时，必填
			material.put("firstInUnit", firstInUnit);//首选入库单位
			material.put("firstOutUnit", firstOutUnit);//首选出库单位
			material.put("userId", inputObject.getLogParams().get("id"));//租户id
			materialDao.editMaterialMationById(material);
		} else {
			outputObject.setreturnMessage("数据格式错误");
		}
	}

	/**
     * 获取产品列表信息展示为下拉框
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialListToSelect(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = materialDao.queryMaterialListToSelect(params);
        List<Map<String, Object>> unitList;
        for(Map<String, Object> bean : beans){
        	unitList = materialDao.queryMaterialUnitByIdToSelect(bean);
        	if("1".equals(bean.get("unit").toString())){//不是多单位
        		unitList.get(0).put("name", bean.get("unitName").toString());
            }
        	bean.put("unitList", unitList);
        }
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
	}

	/**
     * 根据产品规格id和仓库id获取库存
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMaterialTockByNormsIdAndDepotId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = materialDao.queryMaterialTockByNormsIdAndDepotId(params);
        outputObject.setBean(bean);
	}
	
}
