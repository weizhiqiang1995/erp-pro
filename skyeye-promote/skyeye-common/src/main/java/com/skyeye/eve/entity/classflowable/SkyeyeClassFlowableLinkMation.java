/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.classflowable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Joiner;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: SkyeyeClassFlowableLinkMation
 * @Description: 工作流业务对象服务关系的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/20 23:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "skyeye_class_flowable_link", autoResultMap = true)
@ApiModel("工作流业务对象服务关系的实体类")
public class SkyeyeClassFlowableLinkMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    private String id;

    /**
     * 服务的APPID
     */
    @TableField("app_id")
    private String appId;

    @TableField("class_name")
    @ApiModelProperty(value = "需要做工作流的service服务的className", required = "required")
    private String className;

    @TableField("service_name")
    @ApiModelProperty(value = "className对应的服务名称", required = "required")
    private String serviceName;

    @TableField("flowable_factory")
    @ApiModelProperty(value = "工作流工厂类", required = "required")
    private String flowableFactory;

    @TableField("transform_class_bean")
    @ApiModelProperty(value = "需要提交到工作流的属性获取的Entity类", required = "required")
    private String transformClassBean;

    @TableField(value = "listener_list", typeHandler = SkyeyeClassFlowableLinkListenerListTypeHandler.class)
    @ApiModelProperty(value = "该工作流对应的监听器")
    private List<ListenerMation> listenerList;

    public String getListenerClassStr() {
        if (CollectionUtils.isEmpty(this.listenerList)) {
            return StringUtils.EMPTY;
        }
        List<String> collect = this.listenerList.stream()
            .map(bean -> bean.getClassName() + bean.getValue()).collect(Collectors.toList());
        return Joiner.on(CommonCharConstants.COMMA_MARK).join(collect);
    }

}
