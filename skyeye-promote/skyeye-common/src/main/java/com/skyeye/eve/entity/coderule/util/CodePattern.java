/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.coderule.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.skyeye.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: CodePattern
 * @Description: 编码规则
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class CodePattern {

    private boolean isValid = false;

    private int datePatternSize = 0;

    private int serialNumPatternSize = 0;

    /**
     * 序列码位数
     */
    private int serialNumDigits = 0;

    private String pattern;

    private String dateSubPattern = "";

    private List<CodePatternNode> nodes = new ArrayList<>();

    private static List<NodePatten> rules = new ArrayList<>(4);


    static {
        rules.add(new NodePatten(CodePatternNode.Type.CONSTANT.name(), Pattern.compile("^\".*?\"")));
        rules.add(new NodePatten(CodePatternNode.Type.VARIABLE.name(), Pattern.compile("^\\{.*?\\}")));
        rules.add(new NodePatten(CodePatternNode.Type.DATE.name(), Pattern.compile("^yyyyMMddHHmmss|^yyyyMMddHHmm|^yyyyMMddHH|^yyyyMMdd|^yyyyMM|^yyyy")));
        rules.add(new NodePatten(CodePatternNode.Type.SERIAL_NUM.name(), Pattern.compile("^n+")));
    }

    public boolean isValid() {
        return isValid;
    }

    public int getSerialNumDigits() {
        return serialNumDigits;
    }

    public List<CodePatternNode> getNodes() {
        return nodes;
    }


    @Override
    public String toString() {
        return "CodePattern{" +
            "isValid=" + isValid +
            ", serialNumDigits=" + serialNumDigits +
            ", pattern='" + pattern + '\'' +
            ", nodes=" + nodes +
            '}';
    }

    public CodePattern(String pattern) {
        this.pattern = pattern;
        parseCodePattern();
    }

    private void parseCodePattern() {
        String temp = pattern;
        boolean anyMatch;
        do {
            anyMatch = false;
            for (NodePatten nodePatten : rules) {
                Matcher matcher = nodePatten.pattern.matcher(temp);
                if (matcher.find(0)) {
                    anyMatch = true;
                    String group = matcher.group();
                    nodes.add(new CodePatternNode(group, CodePatternNode.Type.valueOf(nodePatten.type)));
                    if (CodePatternNode.Type.SERIAL_NUM.name().equals(nodePatten.type)) {
                        serialNumDigits = group.length();
                        serialNumPatternSize++;
                    } else if (CodePatternNode.Type.DATE.name().equals(nodePatten.type)) {
                        dateSubPattern = group;
                        datePatternSize++;
                    }
                    int end = matcher.end();
                    temp = temp.substring(end);
                }
            }
        } while (anyMatch);

        if (temp.length() == 0 && serialNumDigits > 0) {
            isValid = true;
        }
    }

    /**
     * 规则节点值解析
     *
     * @param featureCodeMap 特征码map
     * @param featureScript  特征码脚本规则
     */
    public void setFeatureCode(Map<String, Object> featureCodeMap, String featureScript) {
        log.debug("setFeatureCode() featureCodeMap=======" + featureCodeMap);
        //验证输入编码是否符合规则。lov格式跳过－－前面已经验证，此处不进行验证
        for (CodePatternNode patternNode : this.nodes) {
            if (CodePatternNode.Type.DATE.equals(patternNode.getType())) {
                patternNode.setValue(DateUtil.format(new Date(), patternNode.getPattern()));
            } else if (CodePatternNode.Type.CONSTANT.equals(patternNode.getType())) {
                patternNode.setValue(patternNode.getPattern().replaceAll("\"", ""));
            } else if (CodePatternNode.Type.VARIABLE.equals(patternNode.getType())) {
                String regex = "\\{(.*?)}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(patternNode.getPattern());
                if (!matcher.find()) {
                    break;
                }
                String cPattern = matcher.group(1);
                String feature = getMapFeatureVal(featureCodeMap, cPattern);
                feature = getFeatureVal(cPattern, feature, featureScript);
                patternNode.setValue(feature);
            } else {

            }
        }
    }


    private String getFeatureVal(String key, String value, String featureScript) {
        if (StringUtils.isBlank(featureScript)) {
            return value;
        }
        JSONObject jsonObject = JSONUtil.parseObj(featureScript);
        if (jsonObject.containsKey(key)) {
            JSONObject obj = jsonObject.getJSONObject(key);
            String type = obj.getStr("type");
            if ("map".equalsIgnoreCase(type)) {
                if (obj.getJSONObject("content").containsKey(value)) {
                    return obj.getJSONObject("content").getStr(value);
                }
            } else {
                return FeatureScriptUtil.invokeScript(obj.getStr("content"), key, value);
            }
        } else {
            return value;
        }
        return "";
    }

    /**
     * 获取特征码值（只针对流水码判断忽略字符）
     *
     * @param featureCodeMap 特征码表
     * @param key            特征码key
     * @return 值
     */
    private String getMapFeatureVal(Map<String, Object> featureCodeMap, String key) {
        String featureValue;
        if (CollectionUtils.isEmpty(featureCodeMap)) {
            return StringUtils.EMPTY;
        }
        if (featureCodeMap.get(key) != null) {
            featureValue = StringUtils.EMPTY + featureCodeMap.get(key);
            return featureValue;
        }
        return StringUtils.EMPTY;

    }

    public String getFeatureCode() {
        StringBuilder sb = new StringBuilder();
        this.nodes.forEach(item -> {
            if (CodePatternNode.Type.SERIAL_NUM.equals(item.getType())) {
                sb.append("{serialNo}");
            } else {
                sb.append(item.getValue());
            }
        });
        return sb.toString();
    }

    public String getDateSubPattern() {
        return this.dateSubPattern;
    }

    /**
     * 根据流水码，获取完整编码
     *
     * @param serialCode 流水码
     * @return 完整编码
     */
    public String getCode(long serialCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.EMPTY);
        this.nodes.forEach(item -> {
            if (CodePatternNode.Type.SERIAL_NUM.equals(item.getType())) {
                int patternLength = item.getPattern().length();
                sb.append(String.format("%0" + patternLength + "d", serialCode));
            } else {
                sb.append(item.getValue());
            }
        });
        return sb.toString();
    }

    public static void validationCodeRulePatten(String pattern) {
        CodePattern codePattern = new CodePattern(pattern);
        if (!codePattern.isValid) {
            throw new CustomException("规则无法解析完成或规则中不包含序列码规则");
        }
        if (codePattern.serialNumPatternSize != 1) {
            throw new CustomException("规则中必须且只能包含一个序列码规则");
        }
        if (codePattern.datePatternSize > 1) {
            throw new CustomException("规则中最多只能包含一个日期规则");
        }
    }
}
