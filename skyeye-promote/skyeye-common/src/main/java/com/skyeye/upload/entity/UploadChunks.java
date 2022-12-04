/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UploadChunks
 * @Description: 文件合并块的信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:35
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("文件合并块的信息实体类")
public class UploadChunks implements Serializable {

    @ApiModelProperty(value = "文件名", required = "required")
    private String name;

    @ApiModelProperty(value = "文件大小", required = "required")
    private String size;

    @ApiModelProperty(value = "文件类型", required = "required,num")
    private Integer type;

    @ApiModelProperty(value = "文件唯一标示", required = "required")
    private String md5;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小单位
     */
    private String fileSizeType = "bytes";

    /**
     * 文件地址
     */
    private String fileAddress;

}
