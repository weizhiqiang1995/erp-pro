/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysDeveLopDocService {

   void querySysDeveLopTypeList(InputObject inputObject, OutputObject outputObject);

   void insertSysDeveLopType(InputObject inputObject, OutputObject outputObject);

   void querySysDeveLopTypeByIdToEdit(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopTypeById(InputObject inputObject, OutputObject outputObject);

   void deleteSysDeveLopTypeById(InputObject inputObject, OutputObject outputObject);

   void querySysDeveLopTypeByFirstType(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopTypeStateISupById(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopTypeStateISdownById(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopTypeOrderByISupById(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopTypeOrderByISdownById(InputObject inputObject, OutputObject outputObject);

   void querySysDeveLopDocList(InputObject inputObject, OutputObject outputObject);

   void addSysDeveLopDoc(InputObject inputObject, OutputObject outputObject);

   void querySysDeveLopDocByIdToEdit(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopDocById(InputObject inputObject, OutputObject outputObject);

   void deleteSysDeveLopDocById(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopDocStateISupById(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopDocStateISdownById(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopDocOrderByISupById(InputObject inputObject, OutputObject outputObject);

   void editSysDeveLopDocOrderByISdownById(InputObject inputObject, OutputObject outputObject);

   void querySysDeveLopFirstTypeToShow(InputObject inputObject, OutputObject outputObject);

   void querySysDeveLopSecondTypeToShow(InputObject inputObject, OutputObject outputObject);

   void querySysDeveLopDocToShow(InputObject inputObject, OutputObject outputObject);

   void querySysDeveLopDocContentToShow(InputObject inputObject, OutputObject outputObject);

}
