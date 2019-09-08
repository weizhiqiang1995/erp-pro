package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Repository
@Mapper
public interface SysEveWinDao {

	public List<Map<String, Object>> queryWinMationList(Map<String, Object> map, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryWinMationByNameOrUrl(Map<String, Object> map) throws Exception;

	public int insertWinMation(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryWinMationToEditById(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryWinMationByNameOrUrlAndId(Map<String, Object> map) throws Exception;

	public int editWinMationById(Map<String, Object> map) throws Exception;

	public int deleteWinMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryChildMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysEveWinNum(Map<String, Object> map) throws Exception;

	public int insertAuthorizationById(Map<String, Object> map) throws Exception;

	public int editCancleAuthorizationById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryWinMationListToShow(Map<String, Object> map, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryWinMationSynchronizationById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryWinMationImportantSynchronizationData(Map<String, Object> map) throws Exception;

	public int insertWinMationImportantSynchronization(List<Map<String, Object>> rows) throws Exception;

	public List<Map<String, Object>> queryWinMationSynchronizationByWinId(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryWinMationImportantSynchronizationPointData(Map<String, Object> map) throws Exception;

	public void insertWinMationImportantSynchronizationPoint(List<Map<String, Object>> rows) throws Exception;

}
