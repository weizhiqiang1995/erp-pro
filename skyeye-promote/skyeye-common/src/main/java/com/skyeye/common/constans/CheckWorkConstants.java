/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common.constans;

import io.netty.util.internal.StringUtil;

/**
 * @ClassName: CheckWorkConstants
 * @Description: 考勤相关的常量类
 * @author: skyeye云系列--卫志强
 * @date: 2021/4/24 11:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public class CheckWorkConstants {

    /**
     * 日程插件上的类型(包含日程的)
     */
    public static enum CheckDayType {
        DAY_IS_PERSONAL(1, "个人", StringUtil.EMPTY_STRING),
        DAY_IS_WORK(2, "工作", StringUtil.EMPTY_STRING),
        DAY_IS_HOLIDAY(3, "节假日", "xiu"),
        DAY_IS_BIRTHDAY(4, "生日", StringUtil.EMPTY_STRING),
        DAY_IS_CUSTOM(5, "自定义", StringUtil.EMPTY_STRING),
        DAY_IS_WORKING(6, "工作日", "work-time"),
        DAY_IS_WORK_OVERTIME(7, "加班", "work-overtime"),
        DAY_IS_LEAVE(8, "请假", "leave"),
        DAY_IS_BUSINESS_TRAVEL(9, "出差", "business-travel");

        private int type;
        private String name;
        private String className;

        CheckDayType(int type, String name, String className) {
            this.type = type;
            this.name = name;
            this.className = className;
        }

        public int getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getClassName() {
            return className;
        }
    }

}
