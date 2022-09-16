/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.coderule.util;

import com.skyeye.common.script.ScriptUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: FeatureScriptUtil
 * @Description: 校验工具类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class FeatureScriptUtil {

    public static String invokeScript(String script, String key, Object value) {
        String fullScript = "function coed_feature_conversion(key,value){ " + script + "  }";
        Object invoke = ScriptUtil.invoke(fullScript, "coed_feature_conversion", key, value);
        return null == invoke ? "" : invoke.toString();
    }

    public static boolean testScript(String script) {
        try {
            invokeScript(script, "Test", "00");
        } catch (Exception e) {
            log.error("测试js脚本出错: ", e);
            return false;
        }
        return true;
    }
}
