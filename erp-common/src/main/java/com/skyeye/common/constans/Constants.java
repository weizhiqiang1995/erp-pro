package com.skyeye.common.constans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	 * 保存模板说明的redis的key
	 */
	public static final String REDIS_CODEMODEL_EXPLAIN_EXEXPLAINTOCODEMODEL = "exexplaintocodemodel";//代码生成器模板规范说明key
	public static final String REDIS_CODEMODEL_EXPLAIN_EXEXPLAINTODSFORMCONTENT = "exexplaintodsformcontent";//动态表单内容项模板规范说明key
	public static final String REDIS_CODEMODEL_EXPLAIN_EXEXPLAINTORMPROPERTY = "exexplaintormproperty";//小程序标签属性模板规范说明key
	public static final String REDIS_CODEMODEL_EXPLAIN_EXEXPLAINTODSFORMDISPLAYTEMPLATE = "exexplaintodsformdisplaytemplate";//动态表单数据展示模板规范说明key
	
	/**
	 * 微信小程序页面id的序列号
	 */
	public static final String REDIS_PROJECT_PAGE_FILE_PATH = "projectpagefilepath";//页面路径的序列号key
	public static final String REDIS_PROJECT_PAGE_FILE_NAME = "projectpagefilename";//页面名称的序列号key
	public static final String REDIS_PROJECT_PAGE_FILE_PATH_NUM = "1000";//页面路径的序列号默认值
	public static final String REDIS_PROJECT_PAGE_FILE_NAME_NUM = "1000";//页面名称的序列号默认值
	
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
	
	//开发文档获取一级分类列表的redis的key
	public static final String SYS_DEVE_LOP_DOC_FIRST_TYPE = "sys_deve_lop_doc_first_type";
	public static String getSysDeveLopDocFirstType(){
		return SYS_DEVE_LOP_DOC_FIRST_TYPE;
	}
	
	//开发文档获取二级分类列表的redis的key
	public static final String SYS_DEVE_LOP_DOC_SECOND_TYPE = "sys_deve_lop_doc_second_type_";
	public static String getSysDeveLopDocSecondType(String parentId) {
		return SYS_DEVE_LOP_DOC_SECOND_TYPE + parentId;
	}
	
	//开发文档获取文档标题列表的redis的key
	public static final String SYS_DEVE_LOP_DOC_TITLE_LIST = "sys_deve_lop_doc_title_list_";
	public static String getSysDeveLopDocTitleList(String parentId) {
		return SYS_DEVE_LOP_DOC_TITLE_LIST + parentId;
	}
	
	//开发文档获取文档内容的redis的key
	public static final String SYS_DEVE_LOP_DOC_CONTENT = "sys_deve_lop_doc_content_";
	public static String getSysDeveLopDocContent(String id) {
		return SYS_DEVE_LOP_DOC_CONTENT + id;
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
	
	//获取已经上线的申诉原因的redis的key
	public static final String CHECK_WORK_REASON_UP_STATE_LIST = "check_work_reason_up_state_list";
	public static String checkWorkReasonUpStateList() {
		return CHECK_WORK_REASON_UP_STATE_LIST;
	}
	
	//获取文件管理默认的文件夹
	public static final List<Map<String, Object>> getFileConsoleISDefaultFolder(){
		List<Map<String, Object>> beans = new ArrayList<>();
		Map<String, Object> favorites = new HashMap<>();
		favorites.put("id", "1");
		favorites.put("name", "收藏夹");
		favorites.put("pId", "0");
		favorites.put("isParent", 1);//是否是文件夹  0否1是
		favorites.put("icon", "../../assets/images/my-favorites-icon.png");//图标
		beans.add(favorites);
		Map<String, Object> documents = new HashMap<>();
		documents.put("id", "2");
		documents.put("name", "我的文档");
		documents.put("pId", "0");
		documents.put("isParent", 1);//是否是文件夹  0否1是
		documents.put("icon", "../../assets/images/my-folder-icon.png");//图标
		beans.add(documents);
		Map<String, Object> skyDrive = new HashMap<>();
		skyDrive.put("id", "3");
		skyDrive.put("name", "企业网盘");
		skyDrive.put("pId", "0");
		skyDrive.put("isParent", 1);//是否是文件夹  0否1是
		skyDrive.put("icon", "../../assets/images/skydrive-icon.png");//图标
		beans.add(skyDrive);
		return beans;
	}
	
	//文件管理---目录logo图片
	public static final String SYS_FILE_CONSOLE_IS_FOLDER_LOGO_PATH = "../../assets/images/folder-show.png";
	//文件管路---图片类型
	public static final String[] SYS_FILE_CONSOLE_IS_IMAGES = {"png", "jpg", "xbm", "bmp", "webp", "jpeg", "svgz", "git", "ico", "tiff", "svg", "jiff", "pjpeg", "pjp", "tif"};
	//文件管理---office文件
	public static final String[] SYS_FILE_CONSOLE_IS_OFFICE = {"docx", "doc", "xls", "xlsx", "ppt", "pptx", "wps", "et", "dps", "csv", "pdf"};
	public static final String[] SYS_FILE_CONSOLE_IS_OFFICE_ICON = {"../../assets/images/doc.png", "../../assets/images/doc.png", "../../assets/images/xls.png",
																	"../../assets/images/xls.png", "../../assets/images/ppt.png", "../../assets/images/pptx.png",
																	"../../assets/images/wps-icon.png", "../../assets/images/ppt.png", "../../assets/images/xls.png",
																	"../../assets/images/csv.png", "../../assets/images/pdf.png"};
	//文件管理---视频文件
	public static final String[] SYS_FILE_CONSOLE_IS_VEDIO = {"mp4", "rm", "rmvb", "wmv", "avi", "3gp", "mkv"};
	//文件管理---压缩包
	public static final String[] SYS_FILE_CONSOLE_IS_PACKAGE = {"zip", "rar"};
	public static final String[] SYS_FILE_CONSOLE_IS_ACE = {"txt", "sql", "java", "css", "html", "htm", "json", "js", "tpl"};
	public static final String[] SYS_FILE_CONSOLE_IS_ACE_ICON = {"../../assets/images/txt.png", "../../assets/images/sql-icon.png", "../../assets/images/java-icon.png", "../../assets/images/css-icon.png",
																"../../assets/images/html.png", "../../assets/images/html.png", "../../assets/images/json.png", "../../assets/images/js.png",
																"../../assets/images/tpl.png"};
	//文件管理---电子书
	public static final String[] SYS_FILE_CONSOLE_IS_EPUB = {"epub"};
	
	//文件分享路径
	public static final String getFileShareUrl(String id){
		return "tpl/shareFile/shareFilepwd.html?id=" + id;
	}
	
	//我的日程的redis的key
	public static final String SYS_USER_SCHEDULE_DAY_LIST = "sys_user_schedule_day_list_";
	public static String getSysUserScheduleDayList(String userId){
		return SYS_USER_SCHEDULE_DAY_LIST + userId;
	}
	
	//文件管理目录集合
	public static final String SYS_FILE_MATION_FOLDER_LIST_MATION = "sys_file_mation_folder_list_mation_";
	public static String getSysFileMationFolderListMation(String folderId, String userId){
		return SYS_FILE_MATION_FOLDER_LIST_MATION + folderId + "_" + userId;
	}
	
	//获取已经上线的轻应用类型的redis的key
	public static final String CHECK_APP_LIGHTAPPTYPE_UP_LIST = "check_app_lightapptype_up_list";
	public static String checkAppLightAppTypeUpList() {
		return CHECK_APP_LIGHTAPPTYPE_UP_LIST;
	}
	
	//获取已经上线的轻应用的redis的key
	public static final String CHECK_APP_LIGHTAPP_UP_LIST_BYID = "check_app_lightapp_up_list_byid_";
	public static String checkAppLightAppUpListById(String typeId) {
		return CHECK_APP_LIGHTAPP_UP_LIST_BYID + typeId;
	}
	
	//获取我的笔记默认的文件夹
	public static final List<Map<String, Object>> getFileMyNoteDefaultFolder(){
		List<Map<String, Object>> beans = new ArrayList<>();
		Map<String, Object> newnotes = new HashMap<>();
		newnotes.put("id", "1");
		newnotes.put("name", "最新笔记");
		newnotes.put("pId", "0");
		newnotes.put("isParent", 0);//是否是文件夹  0否1是
		newnotes.put("icon", "../../assets/images/note-folder.png");//图标
		beans.add(newnotes);
		Map<String, Object> myfiles = new HashMap<>();
		myfiles.put("id", "2");
		myfiles.put("name", "我的文件夹");
		myfiles.put("pId", "0");
		myfiles.put("isParent", 1);//是否是文件夹  0否1是
		myfiles.put("icon", "../../assets/images/my-folder-icon.png");//图标
		beans.add(myfiles);
		return beans;
	}
	
	//笔记文件夹目录集合
	public static final String SYS_FILE_MYNOTE_LIST_MATION = "sys_file_mynote_list_mation_";
	public static String getSysFileMyNoteListMation(String folderId, String userId){
		return SYS_FILE_MYNOTE_LIST_MATION + folderId + "_" + userId;
	}
	
	//获取我的附件0级列表
	public static final List<Map<String, Object>> getSysEnclosureZeroList(){
		List<Map<String, Object>> beans = new ArrayList<>();
		Map<String, Object> favorites = new HashMap<>();
		favorites.put("id", "1");
		favorites.put("name", "我的附件");
		favorites.put("pId", "0");
		favorites.put("isParent", 1);//是否是文件夹  0否1是
		favorites.put("icon", "../../assets/images/my-folder-icon.png");//图标
		beans.add(favorites);
		return beans;
	}
	
	//附件分块上传时的分块集合存储key
	public static final String SYS_ENCLOSURE_FILE_MODULE_MD5 = "sys_enclosure_file_module_md5_";
	public static String getSysEnclosureFileModuleByMd5(String md5) {
		return SYS_ENCLOSURE_FILE_MODULE_MD5 + md5;
	}
	
	//系统读取请求配置文件的key
    public static final String SYS_EVE_MAIN_REQMAPPING_KEY = "sys_eve_main_reqmapping_key_skyeye";
    
    /**
     * 
         * @Title: WeekDay
         * @Description: 日期获取星期
     */
    public static enum WeekDay {
        MON("星期一", 1),
        TUE("星期二", 2),
        WED("星期三", 3),
        THU("星期四", 4),
        FRI("星期五", 5),
        SAT("星期六", 6),
        SUN("星期日", 7);
		
        private String name;
        private int day;
		
        WeekDay(String name, int day){
            this.name = name;
            this.day = day;
        }
		
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getDay() {
            return day;
        }
        public void setDay(int day) {
            this.day = day;
        }
        public static String getWeekName(Date date){
            Calendar cal = Calendar.getInstance();
            // 一周第一天是否为星期天
            boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
            cal.setTime(date);
            int weekDay = cal.get(Calendar.DAY_OF_WEEK);// 获取星期
            // 若一周第一天为星期天，则-1
            if (isFirstSunday) {
                weekDay = weekDay - 1;
                if (weekDay == 0) {
                    weekDay = 7;
                }
            }
            for (WeekDay q : WeekDay.values()){
                if(q.getDay() == weekDay){
                    return q.getName();
                }
            }
            return null;
        }
    }
	
    /**
     * 
         * @Title: ClockInTime
         * @Description: 获取上班打卡状态
     */
    public static enum ClockInTime {
        System("系统填充", "0"),
        Normal("正常", "1"),
        Late("迟到", "2"),
        Notclock("未打卡", "3");
		
        private String name;
        private String state;
		
        ClockInTime(String name, String state){
            this.name = name;
            this.state = state;
        }
	
        public static String getClockInState(String str){
			
            for (ClockInTime q : ClockInTime.values()){
                if(q.getState().equals(str)){
                    return q.getName();
                }
            }
            return "";
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
    }
	
    /**
     * 
         * @Title: ClockInTime
         * @Description: 获取下班打卡状态
     */
    public static enum ClockOutTime {
        System("系统填充", "0"),
        Normal("正常", "1"),
        Leaveearly("早退", "2"),
        Notclock("未打卡", "3");
		
        private String name;
        private String state;
		
        ClockOutTime(String name, String state){
            this.name = name;
            this.state = state;
        }
	
        public static String getClockOutState(String str){
            for (ClockOutTime q : ClockOutTime.values()){
                if(q.getState().equals(str)){
                    return q.getName();
                }
            }
            return "";
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
    }
    /**
     * 
         * @Title: ClockInTime
         * @Description: 获取考勤状态
     */
    public static enum ClockState {
        Start("早卡", "0"),
        Normal("全勤", "1"),
        Absence("缺勤", "2"),
        Insufficient("工时不足", "3"),
        Notstart("缺早卡","4"),
        Notend("缺晚卡","5");
		
        private String name;
        private String state;
		
        ClockState(String name, String state){
            this.name = name;
            this.state = state;
        }
	
        public static String getClockState(String str){
            for (ClockState q : ClockState.values()){
                if(q.getState().equals(str)){
                    return q.getName();
                }
            }
            return "";
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
    }
    
    //获取群组成员列表
    public static final String SYS_EVE_TALK_GROUP_USER_LIST = "sys_eve_talk_group_user_list_";
    public static String checkSysEveTalkGroupUserListByGroupId(String groupId) {
        return SYS_EVE_TALK_GROUP_USER_LIST + groupId;
    }
    
    //获取上线的申请类型下的上线的类型实体
    public static final String ACT_MODLE_UP_STATE_LIST = "act_modle_up_state_list";
    
    //获取上线的论坛标签
    public static final String FORUM_TAG_UP_STATE_LIST = "forum_tag_up_state_list";
    
    //获取上线的用品类别
  	public static final String ASSET_ARTICLES_TYPE_UP_STATE_LIST = "asset_articles_type_up_state_list";
  	
  	//获取已经上线的资产类型的redis的key
    public static final String GET_ASSETTYPE_UP_LIST_ALL = "get_assettype_up_list_all";
    public static String getAssettypeUpListAll() {
        return GET_ASSETTYPE_UP_LIST_ALL;
    }
	
    //获取已经上线的资产来源的redis的key
    public static final String GET_ASSETFROM_UP_LIST_ALL = "get_assetfrom_up_list_all";
    public static String getAssetfromUpListAll() {
        return GET_ASSETFROM_UP_LIST_ALL;
    }
  	
    //工作计划-计划周期类型
    public static enum SysWorkPlan {
    	DAY_PLAN("day", "1"),
        WEEK_PLAN("week", "2"),
        MONTH_PLAN("month", "3"),
        QUARTER_PLAN("quarter", "4"),
        HALFYEAR_PLAN("halfyear", "5"),
        YEAR_PLAN("year", "6");
		
        private String nameCode;
        private String num;
		
        SysWorkPlan(String nameCode, String num){
            this.nameCode = nameCode;
            this.num = num;
        }
	
        public static String getClockInState(String nameCode){
            for (SysWorkPlan q : SysWorkPlan.values()){
                if(q.getNameCode().equals(nameCode)){
                    return q.getNum();
                }
            }
            return "";
        }
        
        public static String getClockInName(String num){
            for (SysWorkPlan q : SysWorkPlan.values()){
                if(q.getNum().equals(num)){
                    return q.getNameCode();
                }
            }
            return "";
        }
        
        public String getNameCode() {
			return nameCode;
		}

		public void setNameCode(String nameCode) {
			this.nameCode = nameCode;
		}

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
		}

    }
    
    //获取论坛敏感词的redis的key  
    public static final String FORUM_SENSITIVE_WORDS_ALL = "forum_sensitive_words_all";
    public static String forumSensitiveWordsAll() {
        return FORUM_SENSITIVE_WORDS_ALL;
    }
	
    //获取论坛帖子浏览量的redis的key  
    public static final String FORUM_BROWSE_NUMS_BYFORUMID = "forum_browse_nums_by_";
    public static String forumBrowseNumsByForumId(String forumId) {
        return FORUM_BROWSE_NUMS_BYFORUMID + forumId;
    }
	
    //获取用户论坛帖子浏览信息的redis的key  
    public static final String FORUM_BROWSE_MATION_BYUSERID = "forum_browse_mation_by_";
    public static String forumBrowseMationByUserid(String userId) {
        return FORUM_BROWSE_MATION_BYUSERID + userId;
    }
    
    //获取solr同步数据的时间的redis的key  
    public static final String FORUM_SOLR_SYNCHRONOUSTIME = "forum_solr_synchronoustime";
    public static String forumSolrSynchronoustime() {
        return FORUM_SOLR_SYNCHRONOUSTIME;
    }
    
    //我的个人通讯录类型列表
    public static final String PERSON_MAIL_TYPE_LIST = "person_mail_type_list_";
    public static String getPersonMailTypeListByUserId(String userId){
    	return PERSON_MAIL_TYPE_LIST + userId;
    }
    
}
