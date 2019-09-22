package com.skyeye.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.CustomerDao;
import com.skyeye.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author 奈何繁华如云烟
 * @Description TODO
 * @Date 2019/9/16 21:26
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 获取客户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryCustomerByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = customerDao.queryCustomerByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加客户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void insertCustomer(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        if(!ToolUtil.isBlank(params.get("email").toString())){
            if(!ToolUtil.isEmail(params.get("email").toString())){
                outputObject.setreturnMessage("邮箱格式不正确！");
                return;
            }
        }
        if(!ToolUtil.isBlank(params.get("telephone").toString())){
            if(!ToolUtil.isPhone(params.get("telephone").toString())){
                outputObject.setreturnMessage("手机号码格式不正确！");
                return;
            }
        }
        //验证某租户下客户信息是否存在
        Map<String, Object> bean = customerDao.queryCustomerByUserIdAndCustomer(params);
        if(bean != null){
            outputObject.setreturnMessage("该客户信息已存在！");
            return;
        }
        params.put("customerId", ToolUtil.getSurFaceId());
        params.put("createTime", ToolUtil.getTimeAndToString());
        params.put("customerType", 2);
        params.put("enabled", 1);
        params.put("isystem", 1);
        params.put("deleteFlag", 0);
        customerDao.insertCustomer(params);
    }

    /**
     * 根据ID查询客户信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = customerDao.queryCustomerById(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到客户信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 删除客户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("deleteFlag", 1);
        int result = customerDao.editCustomerByDeleteFlag(params);
        if(result != 1){
            outputObject.setreturnMessage("删除失败!");
            return;
        }
    }

    /**
     * 编辑客户信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        if(!ToolUtil.isBlank(params.get("email").toString())){
            if(!ToolUtil.isEmail(params.get("email").toString())){
                outputObject.setreturnMessage("邮箱格式不正确！");
                return;
            }
        }
        if(!ToolUtil.isBlank(params.get("telephone").toString())){
            if(!ToolUtil.isPhone(params.get("telephone").toString())){
                outputObject.setreturnMessage("手机号码格式不正确！");
                return;
            }
        }
        Map<String, Object> customerName = customerDao.queryCustomerByIdAndName(params);
        if(customerName == null){
            Map<String, Object> bean = customerDao.queryCustomerByUserIdAndCustomer(params);
            if(bean != null){
                outputObject.setreturnMessage("该客户信息已存在！");
                return;
            }
        }
        int result = customerDao.editCustomerById(params);
        if(result != 1){
            outputObject.setreturnMessage("编辑信息失败！");
            return;
        }
    }

    /**
     * 客户状态改为启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editCustomerByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("enabled", 1);
        int result = customerDao.editCustomerByEnabled(params);
        if(result != 1){
            outputObject.setreturnMessage("修改状态失败！");
        }
    }

    /**
     * 客户状态改为未启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editCustomerByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("enabled", 2);
        int result = customerDao.editCustomerByNotEnabled(params);
        if(result != 1){
            outputObject.setreturnMessage("修改状态失败！");
        }
    }
}
