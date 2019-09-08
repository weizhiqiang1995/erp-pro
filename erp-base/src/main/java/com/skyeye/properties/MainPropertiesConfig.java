package com.skyeye.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:main.properties")//注意路径
public class MainPropertiesConfig {
	
	@Value("${MESSAGE_POST_URL}")
	private String messagePostUrl;//消息系统的地址
	
	@Value("${MESSAGE_POST_NOTICE_LIST_IS_EIGHT_BY_USER_ID}")
	private String messagePostNoticeListIsEightByUserId;//人员选择获取所有公司和人
	public String getMessagePostNoticeListIsEightByUserId(){
		return messagePostUrl + messagePostNoticeListIsEightByUserId;
	}
	
	@Value("${MESSAGE_POST_NOTICE_LIST_IS_THIS_COMPANY_BY_USER_ID}")
	private String messagePostNoticeListIsThisCompanyByUserId;//人员选择根据当前用户所属公司获取这个公司的人
	public String getMessagePostNoticeListIsThisCompanyByUserId(){
		return messagePostUrl + messagePostNoticeListIsThisCompanyByUserId;
	}
	
	@Value("${MESSAGE_POST_NOTICE_LIST_IS_THIS_DEPARTMENT_BY_USER_ID}")
	private String messagePostNoticeListIsThisDepartmentByUserId;//人员选择根据当前用户所属公司获取这个公司部门展示的人
	public String getMessagePostNoticeListIsThisDepartmentByUserId(){
		return messagePostUrl + messagePostNoticeListIsThisDepartmentByUserId;
	}
	
	@Value("${MESSAGE_POST_NOTICE_LIST_IS_THIS_JOB_BY_USER_ID}")
	private String messagePostNoticeListIsThisJobByUserId;//人员选择根据当前用户所属公司获取这个公司岗位展示的人
	public String getMessagePostNoticeListIsThisJobByUserId(){
		return messagePostUrl + messagePostNoticeListIsThisJobByUserId;
	}
	
	@Value("${MESSAGE_POST_NOTICE_LIST_IS_THIS_SIMPLE_DEP_BY_USER_ID}")
	private String messagePostNoticeListIsThisSimpleDepByUserId;//人员选择根据当前用户所属公司获取这个公司同级部门展示的人
	public String getMessagePostNoticeListIsThisSimpleDepByUserId(){
		return messagePostUrl + messagePostNoticeListIsThisSimpleDepByUserId;
	}
	
	@Value("${MESSAGE_POST_NOTICE_LIST_IS_THIS_TALK_GROUP_BY_USER_ID}")
	private String messagePostNoticeListIsThisTalkGroupByUserId;//根据聊天组展示用户
	public String getMessagePostNoticeListIsThisTalkGroupByUserId(){
		return messagePostUrl + messagePostNoticeListIsThisTalkGroupByUserId;
	}
	
	@Value("${MESSAGE_POST_NOTICE_LIST_IS_INCUMBENCY_BY_USER_ID}")
	private String messagePostNoticeListIsIncumbencyByUserId;//获取所有在职的，拥有账号的员工
	public String getMessagePostNoticeListIsIncumbencyByUserId(){
		return messagePostUrl + messagePostNoticeListIsIncumbencyByUserId;
	}
}
