package com.skyeye.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Repository
@Mapper
public interface SysEveMenuAuthPointDao {

	public List<Map<String, Object>> querySysEveMenuAuthPointListByMenuId(Map<String, Object> map, PageBounds pageBounds) throws Exception;

	public Map<String, Object> querySysEveMenuAuthPointMationByAuthName(Map<String, Object> map) throws Exception;

	public int insertSysEveMenuAuthPointMation(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysEveMenuAuthPointMationToEditById(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysEveMenuAuthPointMationByAuthNameAndId(Map<String, Object> map) throws Exception;

	public int editSysEveMenuAuthPointMationById(Map<String, Object> map) throws Exception;

	public int deleteSysEveMenuAuthPointMationById(Map<String, Object> map) throws Exception;
	
	
}
