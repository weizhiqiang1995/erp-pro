/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CompanyTaxRateDao {

    int insertCompanyTaxRate(@Param("list") List<Map<String, Object>> beans);

    /**
     * 根据公司id删除该公司拥有的个人所得税税率信息
     *
     * @param companyId 公司id
     * @return
     */
    int deleteCompanyTaxRateByCompanyId(@Param("companyId") String companyId);

    List<Map<String, Object>> queryCompanyTaxRateByCompanyId(@Param("companyIds") List<String> companyIds);

}
