/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface AppWorkPageService {

    void queryAppWorkPageList(InputObject inputObject, OutputObject outputObject);

    void insertAppWorkPageMation(InputObject inputObject, OutputObject outputObject);

    void queryAppWorkPageListById(InputObject inputObject, OutputObject outputObject);

    void insertAppWorkPageMationById(InputObject inputObject, OutputObject outputObject);

    void queryAppWorkPageMationById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkPageMationById(InputObject inputObject, OutputObject outputObject);

    void deleteAppWorkPageMationById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkPageSortTopById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkPageSortLowerById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkPageUpById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkPageDownById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkPageTitleById(InputObject inputObject, OutputObject outputObject);

    void deleteAppWorkPageById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkUpById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkDownById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkSortTopById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkSortLowerById(InputObject inputObject, OutputObject outputObject);

}
