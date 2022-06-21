/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common.constans;

import com.skyeye.common.util.ToolUtil;

import java.util.*;

/**
 * @ClassName: DiskCloudConstants
 * @Description: 文件系统相关的常量类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/14 11:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class DiskCloudConstants {

    /**
     * 文件管理---目录logo图片
     */
    public static final String SYS_FILE_CONSOLE_IS_FOLDER_LOGO_PATH = "../../assets/images/folder-show.png";

    public static enum FileMation {
        IMAGE_IS_PNG("png", 1, ""),
        IMAGE_IS_JPG("jpg", 1, ""),
        IMAGE_IS_XBM("xbm", 1, ""),
        IMAGE_IS_BMP("bmp", 1, ""),
        IMAGE_IS_WEBP("webp", 1, ""),
        IMAGE_IS_JPEG("jpeg", 1, ""),
        IMAGE_IS_SVGZ("svgz", 1, ""),
        IMAGE_IS_GIT("git", 1, ""),
        IMAGE_IS_ICO("ico", 1, ""),
        IMAGE_IS_TIFF("tiff", 1, ""),
        IMAGE_IS_SVG("svg", 1, ""),
        IMAGE_IS_JIFF("jiff", 1, ""),
        IMAGE_IS_PJPEG("pjpeg", 1, ""),
        IMAGE_IS_PJP("pjp", 1, ""),
        IMAGE_IS_TIF("tif", 1, ""),

        OFFICE_IS_DOCX("docx", 2, "../../assets/images/doc.png"),
        OFFICE_IS_DOC("doc", 2, "../../assets/images/doc.png"),
        OFFICE_IS_XLS("xls", 2, "../../assets/images/xls.png"),
        OFFICE_IS_XLSX("xlsx", 2, "../../assets/images/xls.png"),
        OFFICE_IS_PPT("ppt", 2, "../../assets/images/ppt.png"),
        OFFICE_IS_PPTX("pptx", 2, "../../assets/images/pptx.png"),
        OFFICE_IS_WPS("wps", 2, "../../assets/images/wps-icon.png"),
        OFFICE_IS_ET("et", 2, "../../assets/images/ppt.png"),
        OFFICE_IS_DPS("dps", 2, "../../assets/images/xls.png"),
        OFFICE_IS_CSV("csv", 2, "../../assets/images/csv.png"),
        OFFICE_IS_PDF("pdf", 2, "../../assets/images/pdf.png"),

        VEDIO_IS_MP4("mp4", 3, ""),
        VEDIO_IS_RM("rm", 3, ""),
        VEDIO_IS_RMVB("rmvb", 3, ""),
        VEDIO_IS_WMV("wmv", 3, ""),
        VEDIO_IS_AVI("avi", 3, ""),
        VEDIO_IS3GP("3gp", 3, ""),
        VEDIO_IS_MKV("mkv", 3, ""),

        PACKAGE_IS_ZIP("zip", 4, ""),
        PACKAGE_IS_RAR("rar", 4, ""),

        ACE_IS_TXT("txt", 5, "../../assets/images/txt.png"),
        ACE_IS_SQL("sql", 5, "../../assets/images/sql-icon.png"),
        ACE_IS_JAVA("java", 5, "../../assets/images/java-icon.png"),
        ACE_IS_CSS("css", 5, "../../assets/images/css-icon.png"),
        ACE_IS_HTML("html", 5, "../../assets/images/html.png"),
        ACE_IS_HTM("htm", 5, "../../assets/images/html.png"),
        ACE_IS_JSON("json", 5, "../../assets/images/json.png"),
        ACE_IS_JS("js", 5, "../../assets/images/js.png"),
        ACE_IS_TPL("tpl", 5, "../../assets/images/tpl.png"),

        EPUB_IS("epub", 6, "");

        private String fileExt;
        // 1.图片  2.办公文件  3.视频  4.压缩包  5.普通文件  6.电子书
        private int type;
        private String defaultLogoIcon;

        FileMation(String fileExt, int type, String defaultLogoIcon) {
            this.fileExt = fileExt;
            this.type = type;
            this.defaultLogoIcon = defaultLogoIcon;
        }

        /**
         * 根据文件后缀获取封面logo
         *
         * @param fileExt 文件后缀
         * @return 封面logo
         */
        public static String getIconByFileExt(String fileExt) {
            FileMation item = Arrays.stream(FileMation.values())
                .filter(bean -> fileExt.equalsIgnoreCase(bean.getFileExt())).findFirst().orElse(null);
            if (item == null) {
                return "";
            }
            return item.getDefaultLogoIcon();
        }

        /**
         * 判断是否是文件系统允许的文件后缀类型
         *
         * @param fileExt 文件后缀
         * @param type    文件类型
         * @return false:系统不允许的文件类型；true:系统允许的文件类型
         */
        public static Boolean judgeIsAllowedFileType(String fileExt, int type) {
            FileMation item = Arrays.stream(FileMation.values())
                .filter(bean -> fileExt.equalsIgnoreCase(bean.getFileExt()) && type == bean.getType()).findFirst().orElse(null);
            if (item == null) {
                return false;
            }
            return true;
        }

        public String getFileExt() {
            return fileExt;
        }

        public int getType() {
            return type;
        }

        public String getDefaultLogoIcon() {
            return defaultLogoIcon;
        }
    }

    // 文件分享路径
    public static final String getFileShareUrl(String id) {
        return "tpl/shareFile/shareFilepwd.html?id=" + id;
    }

    // 文件管理目录集合
    public static final String SYS_FILE_MATION_FOLDER_LIST_MATION = "sys_file_mation_folder_list_mation_";

    public static String getSysFileMationFolderListMation(String folderId, String userId) {
        if (ToolUtil.isBlank(folderId) && ToolUtil.isBlank(userId)) {
            return SYS_FILE_MATION_FOLDER_LIST_MATION;
        }
        return SYS_FILE_MATION_FOLDER_LIST_MATION + folderId + "_" + userId;
    }

    /**
     * 获取文件管理默认的文件夹
     *
     * @return 文件管理默认的文件夹
     */
    public static final List<Map<String, Object>> getFileConsoleISDefaultFolder() {
        List<Map<String, Object>> beans = new ArrayList<>();
        beans.add(fileConsoleNode("1", "收藏夹", "0", 1, "../../assets/images/my-favorites-icon.png"));
        beans.add(fileConsoleNode("2", "我的文档", "0", 1, "../../assets/images/my-folder-icon.png"));
        beans.add(fileConsoleNode("3", "企业网盘", "0", 1, "../../assets/images/skydrive-icon.png"));
        return beans;
    }

    /**
     * 构造文件树节点对象
     *
     * @param nodeId   节点id
     * @param nodeName 节点名称
     * @param pId      父id
     * @param isParent 是否是文件夹 0否1是
     * @param icon     图标
     * @return
     */
    public static Map<String, Object> fileConsoleNode(String nodeId, String nodeName, String pId, Integer isParent, String icon) {
        Map<String, Object> node = new HashMap<>();
        node.put("id", nodeId);
        node.put("name", nodeName);
        node.put("pId", pId);
        node.put("isParent", isParent);
        node.put("icon", icon);
        return node;
    }

    public static enum FOLDER_TYPE {
        ENTERPRISE_NETWORK_DISK("1", "企业网盘"),
        PRIVATE_NETWORK_DISK("2", "私人网盘");
        private String folderType;
        private String name;

        FOLDER_TYPE(String folderType, String name) {
            this.folderType = folderType;
            this.name = name;
        }

        public String getFolderType() {
            return folderType;
        }

        public String getName() {
            return name;
        }
    }

}
