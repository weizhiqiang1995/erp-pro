package com.skyeye.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.CompanyTalkGroupDao;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.service.CompanyTalkGroupService;

@Service
public class CompanyTalkGroupServiceImpl implements CompanyTalkGroupService{
	
	@Autowired
	private CompanyTalkGroupDao companyTalkGroupDao;
	
	@Autowired
	private JedisClientService jedisService;

	/**
	 * 
	     * @Title: insertGroupMation
	     * @Description: 添加群组信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void insertGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		String[] invites = map.get("userIds").toString().split(",");
		if(invites.length > 0){
			Map<String, Object> user = inputObject.getLogParams();
			//插入群组信息
			String id = ToolUtil.getSurFaceId();//群组id
			map.put("id", id);
			map.put("groupUserNum", 200);//默认每个群组的人数最多200人
			map.put("state", 1);//群状态，正常
			map.put("groupNum", ToolUtil.getTalkGroupNum());//群号
			map.put("groupHistroyImg", map.get("groupImg").toString() + ",");//群历史logo
			map.put("createId", user.get("id"));
			map.put("createTime", ToolUtil.getTimeAndToString());
			companyTalkGroupDao.insertGroupMation(map);
			//插入被邀请人信息
			List<Map<String, Object>> inviteBeans = new ArrayList<>();
			for(String str : invites){
				if(!ToolUtil.isBlank(str)){
					Map<String, Object> inviteBean = new HashMap<>();
					inviteBean.put("id", ToolUtil.getSurFaceId());
					inviteBean.put("inviteUserId", str);//被邀请人id
					inviteBean.put("groupId", id);//群组id
					inviteBean.put("state", 0);//等待查看
					inviteBean.put("inGroupType", 1);//进群方式  1被邀请进群
					inviteBean.put("createId", user.get("id"));
					inviteBean.put("createTime", ToolUtil.getTimeAndToString());
					inviteBeans.add(inviteBean);
				}
			}
			companyTalkGroupDao.insertGroupInviteMation(inviteBeans);
			//插入创建人入群数据
			Map<String, Object> groupUser = new HashMap<>();
			groupUser.put("id", ToolUtil.getSurFaceId());
			groupUser.put("userId", user.get("id"));
			groupUser.put("groupId", id);
			groupUser.put("createTime", ToolUtil.getTimeAndToString());
			companyTalkGroupDao.insertMakeGroupUserMation(groupUser);
			jedisService.del(Constants.getSysTalkUserHasGroupListMationById(user.get("id").toString()));//删除该用户在redis中存储的群组列表信息
			outputObject.setBean(map);
		}else{
			outputObject.setreturnMessage("群组中最少拥有两名成员。");
		}
	}

	/**
	 * 
	     * @Title: queryGroupInvitationMation
	     * @Description: 获取邀请信息/入群信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryGroupInvitationMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		List<Map<String, Object>> beans = companyTalkGroupDao.queryGroupInvitationMation(map, 
				new PageBounds(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString())));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		int total = beansPageList.getPaginator().getTotalCount();
		outputObject.setBeans(beans);
		outputObject.settotal(total);
	}

	/**
	 * 
	     * @Title: editAgreeInGroupInvitationMation
	     * @Description: 同意入群
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editAgreeInGroupInvitationMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		Map<String, Object> bean = companyTalkGroupDao.queryGroupInvitationMationById(map);
		if("0".equals(bean.get("state").toString())){//未查看等待审核
			if(user.get("id").toString().equals(bean.get("inviteUserId").toString())){//当前审批人和设定的审批人一致
				map.put("userId", user.get("id"));
				companyTalkGroupDao.editAgreeInGroupInvitationMation(map);
				Map<String, Object> createGroupUser = companyTalkGroupDao.queryCreateGroupUserByGroupId(bean);//获取创建该群聊的用户id和人数作比较
				if(Integer.parseInt(createGroupUser.get("groupUserNum").toString())
						> Integer.parseInt(createGroupUser.get("newGroupNum").toString())){//当前群聊人数小于总人数限制
					//插入入群数据
					Map<String, Object> groupUser = new HashMap<>();
					groupUser.put("id", ToolUtil.getSurFaceId());
					if("1".equals(bean.get("inGroupType").toString())){//被邀请进群
						groupUser.put("userId", user.get("id"));
					}else if("2".equals(bean.get("inGroupType").toString())){//搜索账号进群
						groupUser.put("userId", bean.get("createId"));
					}
					jedisService.del(Constants.getSysTalkUserHasGroupListMationById(groupUser.get("userId").toString()));//删除该用户在redis中存储的群组列表信息
					groupUser.put("groupId", bean.get("groupId"));
					groupUser.put("createTime", ToolUtil.getTimeAndToString());
					companyTalkGroupDao.insertMakeGroupUserMation(groupUser);
					Map<String, Object> groupMation = companyTalkGroupDao.queryGroupMationByGroupId(bean);
					groupMation.put("inGroupType", bean.get("inGroupType"));//进群方式 1.被邀请进群 2.搜索账号进群
					groupMation.put("userId", groupUser.get("userId"));
					outputObject.setBean(groupMation);
				}else{
					outputObject.setreturnMessage("该群聊人数已满。");
				}
			}else{
				outputObject.setreturnMessage("该数据不在您的审批范围内。");
			}
		}else{
			outputObject.setreturnMessage("该数据已被操作，请刷新页面。");
		}
	}

	/**
	 * 
	     * @Title: editRefuseInGroupInvitationMation
	     * @Description: 拒绝入群
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editRefuseInGroupInvitationMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		Map<String, Object> bean = companyTalkGroupDao.queryGroupInvitationMationById(map);
		if("0".equals(bean.get("state").toString())){//未查看等待审核
			if(user.get("id").toString().equals(bean.get("inviteUserId").toString())){//当前审批人和设定的审批人一致
				map.put("userId", user.get("id"));
				companyTalkGroupDao.editRefuseInGroupInvitationMation(map);
			}else{
				outputObject.setreturnMessage("该数据不在您的审批范围内。");
			}
		}else{
			outputObject.setreturnMessage("该数据已被操作，请刷新页面。");
		}
	}

	/**
	 * 
	     * @Title: queryGroupMationList
	     * @Description: 搜索群组列表
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryGroupMationList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		List<Map<String, Object>> beans = companyTalkGroupDao.queryGroupMationList(map, 
				new PageBounds(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString())));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		int total = beansPageList.getPaginator().getTotalCount();
		outputObject.setBeans(beans);
		outputObject.settotal(total);
	}

	/**
	 * 
	     * @Title: insertGroupMationToTalk
	     * @Description: 申请加入群聊
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void insertGroupMationToTalk(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> user = inputObject.getLogParams();
		map.put("userId", user.get("id"));
		Map<String, Object> inGroup = companyTalkGroupDao.queryInGroupByUserAndGroupId(map);//判断是否已在该群聊
		if(inGroup == null || inGroup.isEmpty()){
			Map<String, Object> inGroupInvite = companyTalkGroupDao.queryInGroupInviteByUserAndGroupId(map);//判断是否有该用户的未审批的群聊申请信息
			if(inGroupInvite == null || inGroupInvite.isEmpty()){
				Map<String, Object> createGroupUser = companyTalkGroupDao.queryCreateGroupUserByGroupId(map);//获取创建该群聊的用户id和人数作比较
				if(Integer.parseInt(createGroupUser.get("groupUserNum").toString())
						> Integer.parseInt(createGroupUser.get("newGroupNum").toString())){//当前群聊人数小于总人数限制
					Map<String, Object> inviteBean = new HashMap<>();
					inviteBean.put("id", ToolUtil.getSurFaceId());
					inviteBean.put("inviteUserId", createGroupUser.get("createId"));//审批人id
					inviteBean.put("groupId", map.get("groupId"));//群组id
					inviteBean.put("state", 0);//等待查看
					inviteBean.put("inGroupType", 2);//进群方式  2搜索账号进群
					inviteBean.put("createId", user.get("id"));
					inviteBean.put("createTime", ToolUtil.getTimeAndToString());
					companyTalkGroupDao.insertInGroupInviteByUserAndGroupId(inviteBean);
					outputObject.setBean(inviteBean);
					outputObject.settotal(1);
				}else{
					outputObject.setreturnMessage("该群聊人数已满。");
				}
			}
		}else{
			outputObject.setreturnMessage("您已在该群聊。");
		}
	}

	/**
	 * 
	     * @Title: queryGroupMemberByGroupId
	     * @Description: 获取群成员
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryGroupMemberByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> owner = companyTalkGroupDao.queryGroupCreaterById(map);//获取群主信息
		List<Map<String, Object>> list = companyTalkGroupDao.queryGroupMemberByGroupId(map);//获取群成员
		map.clear();
		map.put("owner", owner);
		map.put("members", list.size());
		map.put("list", list);
		outputObject.setBean(map);
	}

	/**
	 * 
	     * @Title: queryChatLogByType
	     * @Description: 获取聊天记录
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryChatLogByType(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		if("friend".equals(map.get("chatType").toString())){//个人对个人
			Map<String, Object> user = inputObject.getLogParams();
			map.put("userId", user.get("id"));
			List<Map<String, Object>> beans = companyTalkGroupDao.queryChatLogByPerToPer(map, 
					new PageBounds(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString())));
			PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
			int total = beansPageList.getPaginator().getTotalCount();
			outputObject.setBeans(beans);
			outputObject.settotal(total);
		}else if("group".equals(map.get("chatType").toString())){//个人对群组
			Map<String, Object> user = inputObject.getLogParams();
			map.put("userId", user.get("id"));
			List<Map<String, Object>> beans = companyTalkGroupDao.queryChatLogByPerToGroup(map, 
					new PageBounds(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString())));
			PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
			int total = beansPageList.getPaginator().getTotalCount();
			outputObject.setBeans(beans);
			outputObject.settotal(total);
		}else{
			outputObject.setreturnMessage("参数错误");
		}
	}

	/**
	 * 
	     * @Title: editUserToExitGroup
	     * @Description: 退出群聊
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editUserToExitGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> groupMation = companyTalkGroupDao.queryGroupCreateIdById(map);
		if(groupMation != null && !groupMation.isEmpty()){
			Map<String, Object> user = inputObject.getLogParams();
			if(!user.get("id").toString().equals(groupMation.get("createId"))){//退群的人不是群创建人，允许退群
				map.put("userId", user.get("id"));
				companyTalkGroupDao.deleteUserToExitGroup(map);
				jedisService.del(Constants.getSysTalkUserHasGroupListMationById(user.get("id").toString()));//删除该用户在redis中存储的群组列表信息
			}else{
				outputObject.setreturnMessage("您是该群聊的创建人，无法退群，请进行解散群聊操作。");
			}
		}else{
			outputObject.setreturnMessage("群信息不存在，请核实后进行操作。");
		}
	}

	/**
	 * 
	     * @Title: editCreateToExitGroup
	     * @Description: 解散群聊
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editCreateToExitGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> groupMation = companyTalkGroupDao.queryGroupCreateIdById(map);
		if(groupMation != null && !groupMation.isEmpty()){
			Map<String, Object> user = inputObject.getLogParams();
			if(user.get("id").toString().equals(groupMation.get("createId"))){//退群的人是群创建人，允许解散群
				List<Map<String, Object>> list = companyTalkGroupDao.queryGroupMemberByGroupId(map);//获取群成员
				for(Map<String, Object> bean : list){
					jedisService.del(Constants.getSysTalkUserHasGroupListMationById(bean.get("id").toString()));//删除该用户在redis中存储的群组列表信息
				}
				jedisService.del(Constants.getSysTalkUserHasGroupListMationById(user.get("id").toString()));//删除该用户在redis中存储的群组列表信息
				map.put("userId", user.get("id"));
				companyTalkGroupDao.editCreateToExitGroup(map);
			}else{
				outputObject.setreturnMessage("您不是该群聊的创建人，无法解散，请进行退出群聊操作。");
			}
		}else{
			outputObject.setreturnMessage("群信息不存在，请核实后进行操作。");
		}
	}
	
	
}
