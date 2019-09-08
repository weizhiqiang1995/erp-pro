package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinDragDropService {

	public void insertWinCustomMenuBox(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertWinCustomMenu(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteWinMenuOrBoxById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editMenuParentIdById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMenuMationTypeById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryCustomMenuBoxMationEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editCustomMenuBoxMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryCustomMenuMationEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editCustomMenuMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editCustomMenuToDeskTopById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
