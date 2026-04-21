/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.mq.job.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.skyeye.common.constans.FileConstants;
import com.skyeye.common.constans.MqConstants;
import com.skyeye.common.tenant.context.TenantContext;
import com.skyeye.common.util.ExcelUtil;
import com.skyeye.service.JobMateMationService;
import com.skyeye.exception.CustomException;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取 DataApi 落盘的 JSON（仅传访问路径 filePath），在本地拼绝对路径后转 Excel。
 */
@Component
@RocketMQMessageListener(
    topic = "${topic.import-export-json-to-excel-service}",
    consumerGroup = "${topic.import-export-json-to-excel-service}",
    selectorExpression = "${spring.profiles.active}")
public class ImportExportJsonToExcelConsume implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportExportJsonToExcelConsume.class);

    private static final int SXSSF_ROW_ACCESS_WINDOW = 500;

    @Autowired
    private JobMateMationService jobMateMationService;

    @Value("${IMAGES_PATH}")
    private String tPath;

    @Value("${skyeye.tenant.enable}")
    private boolean tenantEnable;

    @Override
    public void onMessage(String data) {
        Map<String, Object> map = JSONUtil.toBean(data, null);
        String jobId = map.get("jobMateId").toString();
        Map<String, Object> mation = new HashMap<>();
        try {
            String tenantId = map.getOrDefault("tenantId", StrUtil.EMPTY).toString();
            if (tenantEnable) {
                TenantContext.setTenantId(tenantId);
            }
            jobMateMationService.comMQJobMation(jobId, MqConstants.JOB_TYPE_IS_PROCESSING, StrUtil.EMPTY);

            String visitJsonPath = map.get("filePath").toString();
            Path jsonPath = resolveAbsolutePathFromVisitFilePath(visitJsonPath);
            if (!Files.isRegularFile(jsonPath)) {
                throw new CustomException("导出 JSON 文件不存在: " + jsonPath);
            }

            String[] keys = toStringArray(map.get("keys"));
            String[] columnNames = toStringArray(map.get("columnNames"));
            if (keys.length == 0 || columnNames.length == 0 || keys.length != columnNames.length) {
                throw new CustomException("导入导出异步任务列配置无效.");
            }

            int exportType = FileConstants.FileUploadPath.EXPORT_DATA.getType()[0];
            String saveDir = tPath + FileConstants.FileUploadPath.getSavePath(exportType);
            Files.createDirectories(Paths.get(saveDir));

            String excelFileName = "export-excel-" + System.currentTimeMillis() + ".xlsx";
            Path outPath = Paths.get(saveDir).resolve(excelFileName);
            ExcelUtil.SheetExportStyle exportStyle = parseExportStyle(map.get("exportStyleJson"));
            ExcelUtil.createSxssfExcelFromJsonArrayFile(jsonPath.toFile(), "导出数据", keys, columnNames,
                outPath.toFile(), SXSSF_ROW_ACCESS_WINDOW, exportStyle);
            Files.deleteIfExists(jsonPath);

            String visitExcel = FileConstants.FileUploadPath.getVisitPath(exportType) + excelFileName;
            mation.put("filePath", visitExcel);
            jobMateMationService.comMQJobMation(jobId, MqConstants.JOB_TYPE_IS_SUCCESS, JSONUtil.toJsonStr(mation));
        } catch (Exception e) {
            LOGGER.info("import export json to excel job fail, message is {}", e.getMessage(), e);
            mation.put("message", e.getMessage());
            jobMateMationService.comMQJobMation(jobId, MqConstants.JOB_TYPE_IS_FAIL, JSONUtil.toJsonStr(mation));
        }
    }

    private Path resolveAbsolutePathFromVisitFilePath(String visitFilePath) {
        if (StrUtil.isBlank(visitFilePath)) {
            throw new CustomException("filePath 为空.");
        }
        String relative = visitFilePath.replace("/images/", "");
        if (StrUtil.isBlank(relative)) {
            throw new CustomException("无法从访问路径解析本地路径: " + visitFilePath);
        }
        if (relative.startsWith("/")) {
            relative = relative.substring(1);
        }
        return Paths.get(tPath.trim()).resolve(relative);
    }

    private static ExcelUtil.SheetExportStyle parseExportStyle(Object raw) {
        if (raw == null) {
            return null;
        }
        String jsonStr;
        if (raw instanceof String) {
            jsonStr = (String) raw;
        } else if (raw instanceof Map) {
            jsonStr = JSONUtil.toJsonStr(raw);
        } else {
            jsonStr = String.valueOf(raw);
        }
        if (StrUtil.isBlank(jsonStr) || "{}".equals(jsonStr.trim())) {
            return null;
        }
        try {
            JSONObject o = JSONUtil.parseObj(jsonStr);
            ExcelUtil.SheetExportStyle s = new ExcelUtil.SheetExportStyle();
            if (o.containsKey("headerRowHeight")) {
                Float h = o.getFloat("headerRowHeight");
                if (h != null && h > 0) {
                    s.headerRowHeight = h;
                }
            }
            if (o.containsKey("dataRowHeight")) {
                Float h = o.getFloat("dataRowHeight");
                if (h != null && h > 0) {
                    s.dataRowHeight = h;
                }
            }
            JSONArray cw = o.getJSONArray("columnWidths");
            if (cw != null && !cw.isEmpty()) {
                s.columnWidths = new int[cw.size()];
                for (int i = 0; i < cw.size(); i++) {
                    Object v = cw.get(i);
                    if (v == null) {
                        s.columnWidths[i] = -1;
                    } else if (v instanceof Number) {
                        s.columnWidths[i] = ((Number) v).intValue();
                    } else {
                        s.columnWidths[i] = -1;
                    }
                }
            }
            JSONArray bg = o.getJSONArray("headerBackgroundColors");
            if (bg != null && !bg.isEmpty()) {
                s.headerBackgroundColors = new String[bg.size()];
                for (int i = 0; i < bg.size(); i++) {
                    Object v = bg.get(i);
                    s.headerBackgroundColors[i] = v == null || StrUtil.isBlank(String.valueOf(v)) ? null : String.valueOf(v);
                }
            }
            JSONArray fg = o.getJSONArray("headerFontColors");
            if (fg != null && !fg.isEmpty()) {
                s.headerFontColors = new String[fg.size()];
                for (int i = 0; i < fg.size(); i++) {
                    Object v = fg.get(i);
                    s.headerFontColors[i] = v == null || StrUtil.isBlank(String.valueOf(v)) ? null : String.valueOf(v);
                }
            }
            return s;
        } catch (Exception e) {
            return null;
        }
    }

    private static String[] toStringArray(Object o) {
        if (o == null) {
            return new String[0];
        }
        if (o instanceof String[]) {
            return (String[]) o;
        }
        if (o instanceof List) {
            List<?> list = (List<?>) o;
            String[] arr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i) == null ? "" : list.get(i).toString();
            }
            return arr;
        }
        return new String[0];
    }
}
