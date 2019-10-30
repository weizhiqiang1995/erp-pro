package com.skyeye.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.AccountDao;
import com.skyeye.service.AccountService;

/**
 * @Author 奈何繁华如云烟
 * @Description TODO
 * @Date 2019/10/6 15:43
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    /**
     * 查询账户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryAccountByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        List<Map<String, Object>> beans = accountDao.queryAccountByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>) beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加账户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void insertAccount(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        Map<String, Object> bean = accountDao.queryAccountByName(params);
        if(bean != null){
            outputObject.setreturnMessage("账户名称已存在！");
            return;
        }
        params.put("id", ToolUtil.getSurFaceId());
        if(params.get("isDefault").toString().equals("1")){
            params.put("isDefault", "0");
            accountDao.editAccountByIsDefault(params);
            params.put("isDefault", "1");
        }
        params.put("createTime", ToolUtil.getTimeAndToString());
        params.put("deleteFlag", 0);
        accountDao.insertAccount(params);
    }

    /**
     * 查询单个账户信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryAccountById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        Map<String, Object> bean = accountDao.queryAccountById(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 删除账户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteAccountById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        params.put("deleteFlag", 1);
        accountDao.editAccountByDeleteFlag(params);
    }

    /**
     * 编辑账户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editAccountById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        Map<String, Object> bean = accountDao.queryAccountByIdAndName(params);
        if(bean != null){
            outputObject.setreturnMessage("账户名称已存在！");
            return;
        }
        if(params.get("isDefault").toString().equals("1")){
            params.put("isDefault", "0");
            accountDao.editAccountByIsDefault(params);
            params.put("isDefault", "1");
        }
        accountDao.editAccountById(params);
    }

    /**
     * 设置默认
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editAccountByIdAndIsDefault(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        Map<String, Object> bean = accountDao.queryAccountByIdAndIsDeafault(params);
        if(bean != null){
            outputObject.setreturnMessage("状态以改变，请勿重复操作！");
            return;
        }
        params.put("isDefault", 0);
        accountDao.editAccountByIsDefault(params);
        params.put("isDefault", 1);
        accountDao.editAccountByIdAndIsDefault(params);
    }

    /**
     * 查看账户详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryAccountByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId").toString());
        Map<String, Object> bean = accountDao.queryAccountByIdAndInfo(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 查看账户流水
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryAccountStreamById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        List<Map<String, Object>> beans = accountDao.queryAccountStreamById(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>) beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 获取账户信息展示为下拉框
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryAccountListToSelect(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        List<Map<String, Object>> beans = accountDao.queryAccountListToSelect(params);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
	}
}
