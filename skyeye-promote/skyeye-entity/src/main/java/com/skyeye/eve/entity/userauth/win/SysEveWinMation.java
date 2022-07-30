/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.win;

import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysEveWinMation
 * @Description: 服务管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:09
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_eve_win")
@ApiModel("服务管理实体类")
public class SysEveWinMation extends CommonOperatorUserInfo implements Serializable {



}
