package com.skyeye.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.InoutitemDao;
import com.skyeye.service.InoutitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author 奈何繁华如云烟
 * @Description TODO
 * @Date 2019/10/6 15:42
 */
@Service
public class InoutitemServiceImpl implements InoutitemService {

    @Autowired
    private InoutitemDao inoutitemDao;

    /**
     * 查询收支项目信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryInoutitemByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = inoutitemDao.queryInoutitemByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加收支项目信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void insertInoutitem(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> inoutitemName = inoutitemDao.queryInoutitemByName(params);
        if(inoutitemName != null){
            outputObject.setreturnMessage("名称已存在！");
            return;
        }
        params.put("id", ToolUtil.getSurFaceId());
        params.put("createTime", ToolUtil.getTimeAndToString());
        params.put("deleteFlag", 0);
        inoutitemDao.insertInoutitem(params);
    }

    /**
     * 查询单个收支项目信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryInoutitemById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = inoutitemDao.queryInoutitemById(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到该信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 删除收支项目信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteInoutitemById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("deleteFlag", 1);
        inoutitemDao.editInoutitemByDeleteFlag(params);
    }

    /**
     * 编辑收支项目信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editInoutitemById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = inoutitemDao.queryInoutitemByIdAndName(params);
        if(bean != null){
            outputObject.setreturnMessage("名称已存在！");
            return;
        }
        inoutitemDao.editInoutitemById(params);
    }

    /**
     * 查看收支项目详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryInoutitemByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = inoutitemDao.queryInoutitemByIdAndInfo(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 查看所有的支出项目展示为下拉框
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryInoutitemISExpenditureToSelect(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = inoutitemDao.queryInoutitemISExpenditureToSelect(params);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
	}
}
