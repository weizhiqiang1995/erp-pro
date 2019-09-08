package com.skyeye.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.message.websocket.TalkWebSocket;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.CompanyChatDao;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.service.CompanyChatService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class CompanyChatServiceImpl implements CompanyChatService{
	
	@Autowired
	private CompanyChatDao companyChatDao;
	
	@Autowired
	private JedisClientService jedisService;
	
	/**
	 * 
	     * @Title: getList
	     * @Description: 获取好友列表，群聊信息，个人信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		//获取个人信息
		Map<String, Object> mine = null;
		if(ToolUtil.isBlank(jedisService.get(Constants.getSysTalkUserThisMainMationById(user.get("id").toString())))){
			mine = companyChatDao.queryUserMineByUserId(map);
			jedisService.set(Constants.getSysTalkUserThisMainMationById(user.get("id").toString()), JSON.toJSONString(mine));
		}else{
			mine = JSONObject.fromObject(jedisService.get(Constants.getSysTalkUserThisMainMationById(user.get("id").toString())));
		}
		
		//获取聊天组
		List<Map<String, Object>> group = null;
		if(ToolUtil.isBlank(jedisService.get(Constants.getSysTalkUserHasGroupListMationById(user.get("id").toString())))){
			group = companyChatDao.queryUserGroupByUserId(map);
			jedisService.set(Constants.getSysTalkUserHasGroupListMationById(user.get("id").toString()), JSON.toJSONString(group));
		}else{
			group = JSONArray.fromObject(jedisService.get(Constants.getSysTalkUserHasGroupListMationById(user.get("id").toString())));
		}
		
		//获取公司单位
		List<Map<String, Object>> companyDepartment = companyChatDao.queryCompanyDepartmentByUserId(map);
		
		//循环获取分组的人列表
		for(Map<String, Object> depart : companyDepartment){
			List<Map<String, Object>> userList = null;
			if(ToolUtil.isBlank(jedisService.get(Constants.getSysTalkGroupUserListMationById(depart.get("id").toString())))){
				userList = companyChatDao.queryDepartmentUserByDepartId(depart);
				jedisService.set(Constants.getSysTalkGroupUserListMationById(depart.get("id").toString()), JSON.toJSONString(userList));
			}else{
				userList = JSONArray.fromObject(jedisService.get(Constants.getSysTalkGroupUserListMationById(depart.get("id").toString())));
			}
			if(userList != null && !userList.isEmpty()){
				Set<String> uId = TalkWebSocket.getOnlineUserId();
				for(Map<String, Object> u : userList){
					if(uId.contains(u.get("id").toString())){
						u.put("status", "online");
					}else{
						u.put("status", "offline");
					}
				}
			}
			depart.put("list", userList);
		}
		map.clear();
		map.put("friend", companyDepartment);
		map.put("group", group);
		map.put("mine", mine);
		outputObject.setBean(map);
	}

	/**
	 * 
	     * @Title: editUserSignByUserId
	     * @Description: 编辑签名
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editUserSignByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		companyChatDao.editUserSignByUserId(map);
	}
	
}
