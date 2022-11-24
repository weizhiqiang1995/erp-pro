/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.sysmodel;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.search.CommonPageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysEveModelQueryDo
 * @Description: 系统编辑器模板查询条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:35
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("系统编辑器模板查询条件实体类")
public class SysEveModelQueryDo extends CommonPageInfo implements Serializable {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "模板类型  1.系统模板  2.个人模板")
    private String type;

    @ApiModelProperty(value = "所属一级分类")
    private String firstTypeId;

    @ApiModelProperty(value = "所属二级分类")
    private String secondTypeId;

    /**
     * 用户id
     */
    private String userId;

}
