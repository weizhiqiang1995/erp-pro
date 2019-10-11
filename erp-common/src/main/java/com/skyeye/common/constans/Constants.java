package com.skyeye.common.constans;

import java.util.Map;

/**
 * 
 * @ClassName: Constans
 * @Description: 常量类
 * @author 卫志强
 * @date 2018年6月7日
 *
 */
public class Constants {

	/**
	 * 过滤器过滤文件目录请求项
	 */
	public static final String[] FILTER_FILE_CATALOG_OPTION = { "/html",
			"/css", "/js", "/assets", "/tpl", "/images", "/template", "/static" };

	/**
	 * 过滤器过滤文件后缀请求项
	 */
	public static final String[] FILTER_FILE_SUFFIX_OPTION = { ".json", ".css",
			".js", ".gif", ".jpg", ".eot", ".svg", ".ttf", ".woff", ".woff2",
			".mp4", ".rmvb", ".avi", "3gp", ".html", ".less", ".otf", ".scss",
			".ico" };
	
	/**
	 * 服务器远程
	 */
	public static int MACHINE_USER_USED = 0; //使用中
	public static int MACHINE_USER_USE = 1; //可以使用
	
	/**
	 * IP过滤
	 */
	public static final String[] FILTER_FILE_IP_OPTION = { "0:0:0:0:0:0:0:1", "127.0.0.1"};

	/**
	 * 过滤器过滤请求类型项
	 */
	public static final String[] FILTER_FILE_REQUEST_OPTION = { "/post", "/websocket", "/service" };
	public static final String[] FILTER_FILE_NO_SESSION_REQUEST_OPTION = { "/service", "/talkwebsocket" };

	/**
	 * 登录页面
	 */
	public static final String LOGIN_PAGE = "/tpl/index/login.html";
	
	/**
	 * 控制页面
	 */
	public static final String INDEX_PAGE = "/tpl/index/index.html";
	
	/**
	 * 404
	 */
	public static final String FZF_PAGE = "/tpl/sysmessage/404.html";
	
	/**
	 * 500
	 */
	public static final String FZZ_PAGE = "/tpl/sysmessage/500.html";
	
	/**
	 * 403
	 */
	public static final String FZT_PAGE = "/tpl/sysmessage/403.html";

	/**
	 * 系统请求参数集合
	 */
	public static Map<String, Map<String, Object>> REQUEST_MAPPING = null;

	/**
	 * 网页请求发送的contentType格式
	 */
	public static final String CONENT_TYPE_WEB_REQ = "application/x-www-form-urlencoded";

	/**
	 * json数据请求发送的数据格式
	 */
	public static final String CONENT_TYPE_JSON_REQ = "application/json";
	
	/**
	 * 用户状态
	 */
	public static final String SYS_USER_LOCK_STATE_ISUNLOCK = "0";//未锁定
	public static final String SYS_USER_LOCK_STATE_ISLOCK = "1";//锁定
	
	/**
	 * 菜单类型
	 */
	public static final String SYS_MENU_TYPE_IS_IFRAME = "iframe";
	public static final String SYS_MENU_TYPE_IS_HTML = "html";
	
	/**
	 * 菜单链接打开类型，父菜单默认为1.1：打开iframe，2：打开html。
	 */
	public static final String SYS_MENU_OPEN_TYPE_IS_IFRAME = "1";
	public static final String SYS_MENU_OPEN_TYPE_IS_HTML = "2";
	
	/**
	 * 可以设置长些，防止读到运行此次系统检查时的cpu占用率，就不准了
	 */
	public static final int CPUTIME = 5000;
	public static final int PERCENT = 100;
	public static final int FAULTLENGTH = 10;
	
	//win系统桌面图片列表的redis的key
	public static final String SYS_WIN_BG_PIC_REDIS_KEY = "sys_win_bg_pic_redis_key";
	public static String getSysWinBgPicRedisKey() {
		return SYS_WIN_BG_PIC_REDIS_KEY;
	}
	
	//win系统锁屏桌面图片列表的redis的key
	public static final String SYS_WIN_LOCK_BG_PIC_REDIS_KEY = "sys_win_lock_bg_pic_redis_key";
	public static String getSysWinLockBgPicRedisKey() {
		return SYS_WIN_LOCK_BG_PIC_REDIS_KEY;
	}
	
	//win系统主题颜色列表的redis的key
	public static final String SYS_WIN_THEME_COLOR_REDIS_KEY = "sys_win_theme_color_redis_key";
	public static String getSysWinThemeColorRedisKey() {
		return SYS_WIN_THEME_COLOR_REDIS_KEY;
	}
	
	//消息在redis中已读的key
	public static final String SYS_ALWAYS_READ_MESSAGE_BY_ID = "sys_always_read_message_by_id_";
	public static String getAlwaysReadMessageById(String id) {
		return SYS_ALWAYS_READ_MESSAGE_BY_ID + id;
	}
	
	//消息在redis中已删除的key
	public static final String SYS_ALWAYS_DEL_MESSAGE_BY_ID = "sys_always_del_message_by_id_";
	public static String getAlwaysDelMessageById(String id) {
		return SYS_ALWAYS_DEL_MESSAGE_BY_ID + id;
	}
	
	//消息在redis中内容的key
	public static final String SYS_ALWAYS_CONTENT_MESSAGE_BY_ID = "sys_always_content_message_by_id_";
	public static String getAlwaysContentMessageById(String id) {
		return SYS_ALWAYS_CONTENT_MESSAGE_BY_ID + id;
	}
	
	//聊天获取当前登陆用户信息在redis中的key
	public static final String SYS_TALK_USER_THIS_MATN_MATION = "sys_talk_user_this_matn_mation_";
	public static String getSysTalkUserThisMainMationById(String id){
		return SYS_TALK_USER_THIS_MATN_MATION + id;
	}
	
	//聊天获取当前登陆用户拥有的群组列表在redis中的key
	public static final String SYS_TALK_USER_HAS_GROUP_LIST_MATION = "sys_talk_user_has_group_list_mation_";
	public static String getSysTalkUserHasGroupListMationById(String id){
		return SYS_TALK_USER_HAS_GROUP_LIST_MATION + id;
	}
	
	//聊天获取分组下的用户列表信息在redis中的key
	public static final String SYS_TALK_GROUP_USER_LIST_MATION = "sys_talk_group_user_list_mation_";
	public static String getSysTalkGroupUserListMationById(String id){
		return SYS_TALK_GROUP_USER_LIST_MATION + id;
	}
	
	//获取已经上线的图片类型列表的redis的key
	public static final String SYS_EVE_PIC_TYPE_UP_STATE_LIST = "sys_eve_pic_type_up_state_list";
	public static String sysEvePicTypeUpStateList() {
		return SYS_EVE_PIC_TYPE_UP_STATE_LIST;
	}
	
}
