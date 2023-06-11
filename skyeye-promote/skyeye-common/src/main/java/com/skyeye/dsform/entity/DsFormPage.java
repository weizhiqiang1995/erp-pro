/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.business.entity.BusinessApi;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.operate.entity.Operate;
import com.skyeye.server.entity.ServiceBeanCustom;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: DsFormPage
 * @Description: 表单布局实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/10 17:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField({"name", "className"})
@RedisCacheField(name = "dsForm:page", value = {"id", "numCode"}, cacheTime = RedisConstants.A_YEAR_SECONDS)
@TableName(value = "ds_form_page", autoResultMap = true)
@ApiModel("表单布局实体类")
public class DsFormPage extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("`name`")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "简介")
    private String remark;

    @TableField("num_code")
    @Property("页面编码")
    private String numCode;

    @TableField("`type`")
    @ApiModelProperty(value = "表单布局的类型，可以参考#DsFormPageType", required = "required")
    private String type;

    @TableField("class_name")
    @ApiModelProperty(value = "服务类的className", required = "required")
    private String className;

    @TableField("is_page")
    @ApiModelProperty(value = "表格时拥有，是否分页，参考#WhetherEnum")
    private Integer isPage;

    @TableField("search_tips")
    @ApiModelProperty(value = "表格时拥有，搜索框提示语")
    private String searchTips;

    @TableField(exist = false)
    @Property(value = "服务类的信息")
    private ServiceBeanCustom serviceBeanCustom;

    @TableField(exist = false)
    @ApiModelProperty(value = "表单布局关联的接口信息", required = "required,json")
    private BusinessApi businessApi;

    @TableField(exist = false)
    @Property("表单布局关联的组件信息，非列表布局才拥有关联的组件信息")
    private List<DsFormPageContent> dsFormPageContents;

    @TableField(exist = false)
    @Property("表单布局关联的操作按钮信息，列表布局才拥有操作按钮信息")
    private List<Operate> operateList;

    @TableField(exist = false)
    @Property("表单布局(表格类型关联的列信息)")
    private List<TableColumn> tableColumnList;

    @TableField("whether_choose")
    @ApiModelProperty(value = "表格类型的布局是否开启选择功能")
    private String whetherChoose;

    @TableField(value = "operate_id_list", typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "页面关联的操作信息，可为空", required = "json")
    private List<String> operateIdList;

    @TableField("is_data_auth")
    @ApiModelProperty(value = "表格时拥有，是否开启数据权限，参考#WhetherEnum")
    private Integer isDataAuth;

    @TableField("data_auth_point_num")
    @ApiModelProperty(value = "开启数据权限后，需要填写权限点编号(列表接口的权限点编号)")
    private String dataAuthPointNum;

    @TableField("act_flow_id")
    @ApiModelProperty(value = "工作流模型id")
    private String actFlowId;

    @TableField("is_flowable")
    @ApiModelProperty(value = "是否开启工作流，只有开启了工作流的业务对象可以设置这个字段，参考#WhetherEnum", required = "num")
    private Integer isFlowable;

}
