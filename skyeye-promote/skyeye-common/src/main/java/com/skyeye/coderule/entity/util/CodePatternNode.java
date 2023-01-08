/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coderule.entity.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: CodePatternNode
 * @Description: 编码节点对象
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class CodePatternNode {

    /**
     * 编码流水号模式
     */
    private String pattern = "";
    /**
     * 编码值（流水码或日期模式生成的值）
     */
    private String value = "";
    /**
     * 编码规则节点类型枚举
     */
    private Type type;


    public CodePatternNode() {
    }

    public CodePatternNode(String pattern, Type type) {
        this.pattern = pattern;
        this.type = type;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CodePatternNode{" +
            "pattern='" + pattern + '\'' +
            ", value='" + value + '\'' +
            ", type=" + type +
            '}';
    }

    /**
     * 编码规则节点类型枚举
     */
    enum Type {
        /**
         * 常量
         */
        CONSTANT,
        /**
         * 变量
         */
        VARIABLE,
        /**
         * 时间格式
         */
        DATE,
        /**
         * 序列码
         */
        SERIAL_NUM
    }
}
