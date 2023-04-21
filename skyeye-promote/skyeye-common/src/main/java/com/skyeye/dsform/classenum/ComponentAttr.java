/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ComponentAttr
 * @Description: 组件关联的属性
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/28 11:05
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ComponentAttr implements SkyeyeEnumClass {

    ATTR_KEY("attrKeyBox", "关联属性", false, true, false),
    TITLE("titleBox", "标题", false, true, false),
    PLACEHOLDER("placeholderBox", "提示语", false, true, false),
    REMARK("remarkBox", "备注", false, true, false),
    CLASS_NAME("classNameBox", "class属性", false, true, false),
    REQUIRE("requireBox", "限制条件", false, true, false),
    WIDTH("widthBox", "宽度", true, true, true),
    DEFAULT_VALUE("defaultValueBox", "默认值", false, true, false),
    UPLOAD_DATA_TYPE("uploadDataTypeBox", "文件后缀类型", false, true, false),
    UPLOAD_TYPE("uploadTypeBox", "文件上传类型", false, true, false),
    UPLOAD_NUM("uploadNumBox", "文件数量", false, true, false),
    DATA_SHOW_TYPE("dataShowTypeBox", "枚举/数据字典展示类型", false, true, false),
    TEAM_OBJECT_TYPE("teamObjectTypeBox", "团队适用对象", false, true, false),
    IS_EDIT("isEditBox", "是否可以编辑", false, true, false),
    DATE_TIME_TYPE("dateTimeTypeBox", "日期类型", false, true, false),
    USER_SEL("userSelBox", "用户选择配置", false, true, false),
    TABLE_ATTR("tableAttrBox", "表格属性配置", false, true, false),
    MIN_DATA("minDataBox", "表格组件最小数据行数", false, true, false),
    DELETE_ROW_CALLBACK("deleteRowCallbackBox", "删除行之后的回调函数", false, true, false),
    ADD_ROW_CALLBACK("addRowCallbackBox", "新增行之后的回调函数", false, true, false),
    BEFORE_SCRIPT("beforeScriptBox", "组件加载前执行的脚本", false, true, false),
    AFTER_SCRIPT("afterScriptBox", "组件加载完成后执行的脚本", false, true, false),
    EDIT_ECHO_SCRIPT("editEchoScriptBox", "数据编辑回显时执行的脚本", false, true, false),
    DATA_ECHO_AFTER_SCRIPT("dataEchoAfterScriptBox", "数据回显完之后执行的脚本", false, true, false),
    AFTER_HTML("afterHtmlBox", "组件加载完成后执行的HTML脚本", false, true, false);

    private String key;

    private String value;

    /**
     * 必选(默认选中，不可取消)
     */
    private Boolean disabled;

    private Boolean show;

    private Boolean isDefault;

}
