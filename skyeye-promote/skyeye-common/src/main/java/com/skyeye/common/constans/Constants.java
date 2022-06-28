/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common.constans;

import cn.hutool.core.util.ArrayUtil;

import java.util.*;

/**
 * @ClassName: Constants
 * @Description: 系统公共常量类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/6 23:22
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class Constants {

    /**
     * 过滤器过滤内容请求项
     */
    public static final String[] FILTER_FILE_CATALOG_OPTION = {"/images", "/static", ".json", ".css", ".js", ".gif",
        ".jpg", ".eot", ".svg", ".ttf", ".woff", ".woff2", ".mp4", ".rmvb", ".avi", "3gp", ".html", ".less", ".otf",
        ".scss", ".ico", "/upload", "/actuator", "/service", "/talkwebsocket", "/phonetalkwebsocket"};

    /**
     * 中文
     */
    public static String LANGUAGE_ZH = "zh";

    /**
     * 英文
     */
    public static String LANGUAGE_CN = "cn";

    /**
     * 过滤器过滤请求类型项
     */
    public static final String[] FILTER_FILE_REQUEST_OPTION = {"/post", "/service", "/upload"};

    /**
     * 菜单类型
     */
    public static final String SYS_MENU_TYPE_IS_IFRAME = "win";
    public static final String SYS_MENU_TYPE_IS_HTML = "html";

    /**
     * 菜单链接打开类型，父菜单默认为1.1：打开iframe，2：打开html。
     */
    public static final String SYS_MENU_OPEN_TYPE_IS_IFRAME = "1";
    public static final String SYS_MENU_OPEN_TYPE_IS_HTML = "2";

    /**
     * 保存模板说明的redis的key
     */
    public static final String REDIS_CODEMODEL_EXPLAIN_EXEXPLAIN = "exexplaintocodemodel";

    public static String getSysExplainExexplainRedisKey(Integer type) {
        return REDIS_CODEMODEL_EXPLAIN_EXEXPLAIN + "_" + type;
    }

    // win系统桌面图片列表的redis的key
    public static final String SYS_WIN_BG_PIC_REDIS_KEY = "sys_win_bg_pic_redis_key";

    public static String getSysWinBgPicRedisKey() {
        return SYS_WIN_BG_PIC_REDIS_KEY;
    }

    // win系统锁屏桌面图片列表的redis的key
    public static final String SYS_WIN_LOCK_BG_PIC_REDIS_KEY = "sys_win_lock_bg_pic_redis_key";

    public static String getSysWinLockBgPicRedisKey() {
        return SYS_WIN_LOCK_BG_PIC_REDIS_KEY;
    }

    // win系统主题颜色列表的redis的key
    public static final String SYS_WIN_THEME_COLOR_REDIS_KEY = "sys_win_theme_color_redis_key";

    public static String getSysWinThemeColorRedisKey() {
        return SYS_WIN_THEME_COLOR_REDIS_KEY;
    }

    // 开发文档获取一级分类列表的redis的key
    public static final String SYS_DEVE_LOP_DOC_FIRST_TYPE = "sys_deve_lop_doc_first_type";

    public static String getSysDeveLopDocFirstType() {
        return SYS_DEVE_LOP_DOC_FIRST_TYPE;
    }

    // 开发文档获取二级分类列表的redis的key
    public static final String SYS_DEVE_LOP_DOC_SECOND_TYPE = "sys_deve_lop_doc_second_type_";

    public static String getSysDeveLopDocSecondType(String parentId) {
        return SYS_DEVE_LOP_DOC_SECOND_TYPE + parentId;
    }

    // 开发文档获取文档标题列表的redis的key
    public static final String SYS_DEVE_LOP_DOC_TITLE_LIST = "sys_deve_lop_doc_title_list_";

    public static String getSysDeveLopDocTitleList(String parentId) {
        return SYS_DEVE_LOP_DOC_TITLE_LIST + parentId;
    }

    // 开发文档获取文档内容的redis的key
    public static final String SYS_DEVE_LOP_DOC_CONTENT = "sys_deve_lop_doc_content_";

    public static String getSysDeveLopDocContent(String id) {
        return SYS_DEVE_LOP_DOC_CONTENT + id;
    }

    // 聊天获取当前登陆用户信息在redis中的key
    public static final String SYS_TALK_USER_THIS_MATN_MATION = "sys_talk_user_this_matn_mation_";

    public static String getSysTalkUserThisMainMationById(String id) {
        return SYS_TALK_USER_THIS_MATN_MATION + id;
    }

    // 聊天获取当前登陆用户拥有的群组列表在redis中的key
    public static final String SYS_TALK_USER_HAS_GROUP_LIST_MATION = "sys_talk_user_has_group_list_mation_";

    public static String getSysTalkUserHasGroupListMationById(String id) {
        return SYS_TALK_USER_HAS_GROUP_LIST_MATION + id;
    }

    // 聊天获取分组下的用户列表信息在redis中的key
    public static final String SYS_TALK_GROUP_USER_LIST_MATION = "sys_talk_group_user_list_mation_";

    public static String getSysTalkGroupUserListMationById(String id) {
        return SYS_TALK_GROUP_USER_LIST_MATION + id;
    }

    // 获取已经上线的图片类型列表的redis的key
    public static final String SYS_EVE_PIC_TYPE_UP_STATE_LIST = "sys_eve_pic_type_up_state_list";

    public static String sysEvePicTypeUpStateList() {
        return SYS_EVE_PIC_TYPE_UP_STATE_LIST;
    }

    // 获取已经上线的轻应用类型的redis的key
    public static final String CHECK_APP_LIGHTAPPTYPE_UP_LIST = "check_app_lightapptype_up_list";

    public static String checkAppLightAppTypeUpList() {
        return CHECK_APP_LIGHTAPPTYPE_UP_LIST;
    }

    // 获取已经上线的轻应用的redis的key
    public static final String CHECK_APP_LIGHTAPP_UP_LIST_BYID = "check_app_lightapp_up_list_byid_";

    public static String checkAppLightAppUpListById(String typeId) {
        return CHECK_APP_LIGHTAPP_UP_LIST_BYID + typeId;
    }

    // 获取我的附件0级列表
    public static final List<Map<String, Object>> getSysEnclosureZeroList() {
        List<Map<String, Object>> beans = new ArrayList<>();
        Map<String, Object> favorites = new HashMap<>();
        favorites.put("id", "1");
        favorites.put("name", "我的附件");
        favorites.put("pId", "0");
        favorites.put("isParent", 1);// 是否是文件夹 0否1是
        favorites.put("icon", "../../assets/images/my-folder-icon.png");// 图标
        beans.add(favorites);
        return beans;
    }

    // 附件分块上传时的分块集合存储key
    public static final String SYS_ENCLOSURE_FILE_MODULE_MD5 = "sys_enclosure_file_module_md5_";

    public static String getSysEnclosureFileModuleByMd5(String md5) {
        return SYS_ENCLOSURE_FILE_MODULE_MD5 + md5;
    }

    // 系统读取请求xml配置文件的key
    public static final String SYS_EVE_MAIN_XML_REQMAPPING_KEY = "sys_eve_main_xml_reqmapping_key";

    /**
     * 文件上传路径
     */
    public static enum FileUploadPath {
        SMPROPIC(new int[]{1}, "/smpropic", "/", "小程序上传"),
        WINBGPIC(new int[]{2, 4}, "/winbgpic", "/winbgpic/", "系统桌面背景自定义图片上传,系统桌面背景自定义图片上传用户自定义"),
        WINLOCKBGPIC(new int[]{3, 5}, "/winlockbgpic", "/winlockbgpic/", "系统桌面锁屏背景自定义图片上传,系统桌面锁屏背景自定义图片上传用户自定义"),
        USERPHOTO(new int[]{6}, "/userphoto", "/userphoto/", "用户头像"),
        TALKGROUP(new int[]{7}, "/talkgroup", "/talkgroup/", "聊天群组头像"),
        SYSWIN(new int[]{8}, "/syswin", "/syswin/", "系统图片"),
        TALKPIC(new int[]{9}, "/talkpic", "/talkpic/", "聊天图片"),
        TALKFILE(new int[]{10}, "/talkfile", "/talkfile/", "聊天附件"),
        EDIT(new int[]{11}, "/edit", "/edit/", "富文本内容图片"),
        MENULOGO(new int[]{12}, "/menulogo", "/menulogo/", "菜单logo图片"),
        LAYEDIT(new int[]{13}, "/layedit", "/layedit/", "富文本编辑图片"),
        ORDER(new int[]{14}, "/order", "/order/", "工单图片"),
        STUDENTPIC(new int[]{15}, "/studentPic", "/studentPic/", "学生照图片"),
        SCHOOLBANK(new int[]{16}, "/schoolBank", "/schoolBank/", "考试题库文件"),
        ACT_MODEL(new int[]{17}, "/actmodel", "/actmodel/", "流程配置图片"),
        REPORT_BG_IMAGE(new int[]{18}, "/reportBgImage", "/reportBgImage/", "报表基础设置背景图"),
        REPORT_WORD_MODEL_IMAGE(new int[]{19}, "/reportWordModel", "/reportWordModel/", "报表文字模型logo"),
        SYS_MODEL(new int[]{20}, "/sysmodel", "/sysmodel/", "编辑器素材logo图片"),
        VOUCHER_PATH(new int[]{21}, "/voucherPath", "/voucherPath/", "财务模块-凭证文件"),
        SHOP_MWAL_PATH(new int[]{22}, "/shopMealPath", "/shopMealPath/", "商城模块-套餐文件"),
        CODE_GENERATOR(new int[]{23}, "/codeGenerator", "/codeGenerator/", "代码生成器"),
        FILE_CONSOLE(new int[]{24}, "/fileconsole", "/fileconsole/", "网盘--文件管理"),
        REPORT_IMPORT_HISTORY(new int[]{25}, "/report", "/report/", "报表管理--echarts模型图片");

        private int[] type;
        // 保存地址
        private String savePath;
        // 访问地址
        private String visitPath;
        private String desc;

        FileUploadPath(int[] type, String savePath, String visitPath, String desc) {
            this.type = type;
            this.savePath = savePath;
            this.visitPath = visitPath;
            this.desc = desc;
        }

        public int[] getType() {
            return type;
        }

        public String getSavePath() {
            return savePath;
        }

        public String getVisitPath() {
            return visitPath;
        }

        public String getDesc() {
            return desc;
        }

        public static String getSavePath(int type) {
            for (FileUploadPath q : FileUploadPath.values()) {
                if (ArrayUtil.contains(q.getType(), type)) {
                    return "/upload" + q.getSavePath();
                }
            }
            return "/upload";
        }

        public static String getVisitPath(int type) {
            for (FileUploadPath q : FileUploadPath.values()) {
                if (ArrayUtil.contains(q.getType(), type)) {
                    return "/images/upload" + q.getVisitPath();
                }
            }
            return "/images/upload/";
        }
    }

    // 获取群组成员列表
    public static final String SYS_EVE_TALK_GROUP_USER_LIST = "sys_eve_talk_group_user_list_";

    public static String checkSysEveTalkGroupUserListByGroupId(String groupId) {
        return SYS_EVE_TALK_GROUP_USER_LIST + groupId;
    }

    // 我的个人通讯录类型列表
    public static final String PERSON_MAIL_TYPE_LIST = "person_mail_type_list_";

    public static String getPersonMailTypeListByUserId(String userId) {
        return PERSON_MAIL_TYPE_LIST + userId;
    }

    // 获取已经上线的论坛举报类型的redis的key
    public static final String FORUM_REPORT_TYPE_UP_LIST = "forum_report_type_up_list";

    public static String forumReportTypeUpList() {
        return FORUM_REPORT_TYPE_UP_LIST;
    }

    /**
     * @param orderNum 获取工单派工内容字符串
     * @param userName 接收人
     * @return
     */
    public static String getNoticeServiceUserContent(String orderNum, String userName) {
        return "尊敬的" + userName + "，您好：<br/>" + "您有一份待接单工单，工单号为：" + orderNum + "，请及时接单。";
    }

    // 协助人
    public static String getNoticeCooperationUserContent(String orderNum, String userName) {
        return "尊敬的" + userName + "，您好：<br/>" + "您有一份协助工单，工单号为：" + orderNum + "，请配合工单接收人完成该售后服务。";
    }

}
