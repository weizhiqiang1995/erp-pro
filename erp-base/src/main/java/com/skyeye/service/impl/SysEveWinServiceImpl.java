package com.skyeye.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.HttpClient;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.SysEveWinDao;
import com.skyeye.service.SysEveWinService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SysEveWinServiceImpl implements SysEveWinService{
	
	@Autowired
	private SysEveWinDao sysEveWinDao;

	/**
	 * 
	     * @Title: queryWinMationList
	     * @Description: 获取系统信息列表
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryWinMationList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> beans = sysEveWinDao.queryWinMationList(map, 
				new PageBounds(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString())));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		int total = beansPageList.getPaginator().getTotalCount();
		outputObject.setBeans(beans);
		outputObject.settotal(total);
	}

	/**
	 * 
	     * @Title: insertWinMation
	     * @Description: 新增系统信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void insertWinMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveWinDao.queryWinMationByNameOrUrl(map);
		if(bean != null && !bean.isEmpty()){
			outputObject.setreturnMessage("存在相同的系统或系统地址，请更换");
		}else{
			Map<String, Object> user = inputObject.getLogParams();
			map.put("id", ToolUtil.getSurFaceId());
			map.put("createId", user.get("id"));
			map.put("createTime", ToolUtil.getTimeAndToString());
			sysEveWinDao.insertWinMation(map);
		}
	}

	/**
	 * 
	     * @Title: queryWinMationToEditById
	     * @Description: 编辑系统信息时进行回显
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryWinMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveWinDao.queryWinMationToEditById(map);
		outputObject.setBean(bean);
	}
	
	/**
	 * 
	     * @Title: editWinMationById
	     * @Description: 编辑系统信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editWinMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveWinDao.queryWinMationByNameOrUrlAndId(map);
		if(bean != null && !bean.isEmpty()){
			outputObject.setreturnMessage("存在相同的系统或系统地址，请更换");
		}else{
			sysEveWinDao.editWinMationById(map);
		}
	}

	/**
	 * 
	     * @Title: deleteWinMationById
	     * @Description: 删除系统信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void deleteWinMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveWinDao.queryChildMationById(map);
		if(Integer.parseInt(bean.get("menuNum").toString()) > 0 || Integer.parseInt(bean.get("useNum").toString()) > 0){
			outputObject.setreturnMessage("该系统存在功能菜单或者使用商户，请先进行菜单或商户操作。");
		}else{
			sysEveWinDao.deleteWinMationById(map);
		}
	}

	/**
	 * 
	     * @Title: editAuthorizationById
	     * @Description: 进行商户系统授权
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editAuthorizationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveWinDao.querySysEveWinNum(map);
		if(bean != null && !bean.isEmpty()){
			Map<String, Object> user = inputObject.getLogParams();
			map.put("id", ToolUtil.getSurFaceId());
			map.put("winNumId", bean.get("id"));
			map.put("createId", user.get("id"));
			map.put("createTime", ToolUtil.getTimeAndToString());
			sysEveWinDao.insertAuthorizationById(map);
		}else{
			outputObject.setreturnMessage("暂无可授权的商户。");
		}
	}

	/**
	 * 
	     * @Title: editCancleAuthorizationById
	     * @Description: 进行商户系统取消授权
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editCancleAuthorizationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		sysEveWinDao.editCancleAuthorizationById(map);
	}

	/**
	 * 
	     * @Title: queryWinMationListToShow
	     * @Description: 获取应用商店
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryWinMationListToShow(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> beans = sysEveWinDao.queryWinMationListToShow(map, 
				new PageBounds(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString())));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		int total = beansPageList.getPaginator().getTotalCount();
		outputObject.setBeans(beans);
		outputObject.settotal(total);
	}

	/**
	 * 
	     * @Title: insertWinMationImportantSynchronization
	     * @Description: 系统重要的同步操作
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@SuppressWarnings({ "static-access", "deprecation", "unchecked" })
	@Override
	public void insertWinMationImportantSynchronization(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveWinDao.queryWinMationSynchronizationById(map);//判断是否有权限
		if(bean == null){
			outputObject.setreturnMessage("您不具备该系统的同步权限。");
		}else{
			List<Map<String, Object>> hasRows = sysEveWinDao.queryWinMationSynchronizationByWinId(map);
			if(hasRows.isEmpty()){
				String url = map.get("url") + "/sysimportantsynchronization002?loginPCIp=" + inputObject.getRequest().getParameter("loginPCIp")
						+ "&userToken=" + inputObject.getRequest().getParameter("userToken") + "&winid=" + map.get("id");
				String str = HttpClient.doGet(url);
				JSONObject json = JSONObject.fromObject(str);
				if("0".equals(json.get("returnCode").toString())){
					Map<String, Object> user = inputObject.getLogParams();
					JSONObject jo = JSONObject.fromObject(json.get("bean").toString());
					//处理菜单
					JSONArray beans = JSONArray.fromObject(jo.get("menuBeans").toString());
					List<Map<String, Object>> rows = JSONArray.toList(beans, Map.class);
					for(Map<String, Object> row: rows){
						row.put("sysWinId", map.get("id"));
						row.put("createId", user.get("id"));
						row.put("createTime", ToolUtil.getTimeAndToString());
						if(!"--".equals(row.get("menuUrl").toString())){//一级菜单
							row.put("menuUrl", map.get("url").toString() + "/" + row.get("menuUrl").toString().replace("../../", ""));
						}
					}
					sysEveWinDao.insertWinMationImportantSynchronization(rows);
					//处理权限点
					JSONArray points = JSONArray.fromObject(jo.get("pointBeans").toString());
					List<Map<String, Object>> point = JSONArray.toList(points, Map.class);
					for(Map<String, Object> row: point){
						row.put("createId", user.get("id"));
						row.put("createTime", ToolUtil.getTimeAndToString());
					}
					sysEveWinDao.insertWinMationImportantSynchronizationPoint(point);
				}else{
					outputObject.setreturnMessage(json.get("returnMessage").toString());
				}
			}else{
				outputObject.setreturnMessage("系统菜单只能同步一次哦。");
			}
		}
	}

	/**
	 * 
	     * @Title: queryWinMationImportantSynchronizationData
	     * @Description: 系统重要的同步操作获取数据
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryWinMationImportantSynchronizationData(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> menuBeans = sysEveWinDao.queryWinMationImportantSynchronizationData(map);
		List<Map<String, Object>> pointBeans = sysEveWinDao.queryWinMationImportantSynchronizationPointData(map);
		if(!menuBeans.isEmpty() || !pointBeans.isEmpty()){
			Map<String, Object> bean = new HashMap<>();
			bean.put("menuBeans", menuBeans);
			bean.put("pointBeans", pointBeans);
			outputObject.setBean(bean);
		}else{
			outputObject.setreturnMessage("暂无可以同步的数据");
		}
	}
	
}
