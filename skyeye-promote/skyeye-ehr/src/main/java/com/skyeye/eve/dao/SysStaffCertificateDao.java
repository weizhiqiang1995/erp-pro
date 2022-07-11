/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysStaffCertificateDao
 * @Description: 员工证书管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysStaffCertificateDao {

    List<Map<String, Object>> queryAllSysStaffCertificateList(Map<String, Object> params);

    int insertSysStaffCertificateMation(Map<String, Object> map);

    Map<String, Object> querySysStaffCertificateMationToEdit(@Param("id") String id);

    int editSysStaffCertificateMationById(Map<String, Object> map);

    int deleteSysStaffCertificateMationById(@Param("id") String id);

    List<Map<String, Object>> queryPointStaffSysStaffCertificateList(PointStaffQueryDo pointStaffQuery);

}
