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
import com.skyeye.dao.ErpTenantDao;
import com.skyeye.service.ErpTenantService;

@Service
public class ErpTenantServiceImpl implements ErpTenantService{
	
	@Autowired
	private ErpTenantDao erpTenantDao;
	
	/**
	 * 
	     * @Title: insertErpTenantGroupMation
	     * @Description: 新增租户组
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	@Transactional(value="transactionManager")
	public void insertErpTenantGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		map.put("id", ToolUtil.getSurFaceId());
		map.put("createId", inputObject.getLogParams().get("id"));
		map.put("createTime", ToolUtil.getTimeAndToString());
		erpTenantDao.insertErpTenantGroupMation(map);
		outputObject.setBean(map);
	}

	/**
	 * 
	     * @Title: selectAllErpTenantGroupMation
	     * @Description: 遍历所有的租户组
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void selectAllErpTenantGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> beans = erpTenantDao.selectAllErpTenantGroupMation(map);
		if(!beans.isEmpty()){
			outputObject.setBeans(beans);
			outputObject.settotal(beans.size());
		}
	}

	/**
	 * 
	     * @Title: insertErpTenantGroupUserByGroupId
	     * @Description: 给租户组新增租户
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	@Transactional(value="transactionManager")
	public void insertErpTenantGroupUserByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> beans = new ArrayList<>();
		String[] userId = map.get("userId").toString().split(",");//把字符串以","分截成字符数组
		if(userId.length > 0){//如果数组长度大于0
			Map<String, Object> item;
			for(String str : userId){//遍历数组
				if(!ToolUtil.isBlank(str)){
					item = new HashMap<>();
					item.put("userId", str);
					//判断该用户是否已经属于某个租户组,如果为空，说明不输入任何租户组
					if(erpTenantDao.queryUserInGroupByUserId(item).isEmpty()){
						item.put("id", ToolUtil.getSurFaceId());
						item.put("groupId", map.get("groupId"));
						item.put("createId", inputObject.getLogParams().get("id"));
						item.put("createTime", ToolUtil.getTimeAndToString());
						beans.add(item);//把一个个item对象放入集合beans
					}
				}
			}
			if(!beans.isEmpty()){
				erpTenantDao.insertErpTenantGroupUserByGroupId(beans); //在数据库中插入集合beans
			}
		}else{
			outputObject.setreturnMessage("请选择要新增进组的租户！");
		}
	}

	/**
	 * 
	     * @Title: editErpTenantGroupNameByGroupId
	     * @Description: 编辑租户组名
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	@Transactional(value="transactionManager")
	public void editErpTenantGroupNameByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		erpTenantDao.editErpTenantGroupNameByGroupId(map);
	}

	/**
	 * 
	     * @Title: deleteErpTenantGroupByGroupId
	     * @Description: 删除租户组
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	@Transactional(value="transactionManager")
	public void deleteErpTenantGroupByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		erpTenantDao.deleteErpTenantGroupByGroupId(map);
		erpTenantDao.deleteErpTenantGroupUserByGroupId(map);
	}

	/**
	 * 
	     * @Title: deleteErpTenantGroupUserByGroupIdAndUserId
	     * @Description: 移除租户组中的某个租户
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	@Transactional(value="transactionManager")
	public void deleteErpTenantGroupUserByGroupIdAndUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		erpTenantDao.deleteErpTenantGroupUserByGroupIdAndUserId(map);
	}

	/**
	 * 
	     * @Title: selectUserInfoOnErpTenantGroup
	     * @Description: 展示租户组的租户信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void selectUserInfoOnErpTenantGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> beans = erpTenantDao.selectUserInfoOnErpTenantGroup(map, 
				new PageBounds(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString())));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		int total = beansPageList.getPaginator().getTotalCount();
		outputObject.setBeans(beans);
		outputObject.settotal(total);
	}

	/**
	 * 
	     * @Title: deleteAllErpTenantGroupUserByGroupId
	     * @Description: 一键移除指定租户组下的所有租户
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	@Transactional(value="transactionManager")
	public void deleteAllErpTenantGroupUserByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		erpTenantDao.deleteErpTenantGroupUserByGroupId(map);
	}
	
}
