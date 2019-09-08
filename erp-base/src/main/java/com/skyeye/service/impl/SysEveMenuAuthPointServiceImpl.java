package com.skyeye.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.SysEveMenuAuthPointDao;
import com.skyeye.service.SysEveMenuAuthPointService;

@Service
public class SysEveMenuAuthPointServiceImpl implements SysEveMenuAuthPointService{
	
	@Autowired
	private SysEveMenuAuthPointDao sysEveMenuAuthPointDao;
	
	/**
	 * 
	     * @Title: querySysEveMenuAuthPointListByMenuId
	     * @Description: 获取菜单权限点列表根据菜单id
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> beans = sysEveMenuAuthPointDao.querySysEveMenuAuthPointListByMenuId(map, 
				new PageBounds(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString())));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		int total = beansPageList.getPaginator().getTotalCount();
		outputObject.setBeans(beans);
		outputObject.settotal(total);
		
	}

	/**
	 * 
	     * @Title: insertSysEveMenuAuthPointMation
	     * @Description: 添加菜单权限点
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void insertSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveMenuAuthPointDao.querySysEveMenuAuthPointMationByAuthName(map);
		if(bean == null){
			Map<String, Object> user = inputObject.getLogParams();
			map.put("id", ToolUtil.getSurFaceId());
			map.put("createId", user.get("id"));
			map.put("createTime", ToolUtil.getTimeAndToString());
			map.put("menuNum", ToolUtil.getTimeStampAndToString());
			sysEveMenuAuthPointDao.insertSysEveMenuAuthPointMation(map);
		}else{
			outputObject.setreturnMessage("该菜单下已存在该名称的权限点，请进行更改.");
		}
	}

	/**
	 * 
	     * @Title: querySysEveMenuAuthPointMationToEditById
	     * @Description: 编辑菜单权限点时进行回显
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void querySysEveMenuAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveMenuAuthPointDao.querySysEveMenuAuthPointMationToEditById(map);
		outputObject.setBean(bean);
	}

	/**
	 * 
	     * @Title: editSysEveMenuAuthPointMationById
	     * @Description: 编辑菜单权限点
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = sysEveMenuAuthPointDao.querySysEveMenuAuthPointMationByAuthNameAndId(map);
		if(bean == null){
			sysEveMenuAuthPointDao.editSysEveMenuAuthPointMationById(map);
		}else{
			outputObject.setreturnMessage("该菜单下已存在该名称的权限点，请进行更改.");
		}
	}

	/**
	 * 
	     * @Title: deleteSysEveMenuAuthPointMationById
	     * @Description: 删除菜单权限点
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void deleteSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		sysEveMenuAuthPointDao.deleteSysEveMenuAuthPointMationById(map);
	}
	
	
	
}
