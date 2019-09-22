package com.skyeye.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.SupplierDao;
import com.skyeye.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author 奈何繁华如云烟
 * @Description TODO
 * @Date 2019/9/16 21:24
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    /**
     * 获取供应商信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySupplierByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = supplierDao.querySupplierByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加供应商信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void insertSupplier(InputObject inputObject, OutputObject outputObject) throws Exception {
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
        //查询某一租户下是否存在相同供应商的信息
        Map<String, Object> bean = supplierDao.querySupplierByUserIdAndSupplier(params);
        if(bean != null){
            outputObject.setreturnMessage("该供应商信息已存在！");
            return;
        }
        params.put("supplierId", ToolUtil.getSurFaceId());
        params.put("createTime", ToolUtil.getTimeAndToString());
        params.put("supplierType", 1);
        params.put("enabled", 1);
        params.put("isystem", 1);
        params.put("deleteFlag", 0);
        supplierDao.insertSupplier(params);
    }

    /**
     * 根据ID查询供应商信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySupplierById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = supplierDao.querySupplierById(params);
        if (bean == null){
            outputObject.setreturnMessage("未查询到供应商信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 删除供应商信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteSupplierById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("deleteFlag", 1);
        int result = supplierDao.editSupplierByDeleteFlag(params);
        if(result != 1){
            outputObject.setreturnMessage("删除失败!");
            return;
        }
    }

    /**
     * 编辑供应商信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editSupplierById(InputObject inputObject, OutputObject outputObject) throws Exception {
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
        Map<String, Object> supplierName = supplierDao.querySupplierByIdAndName(params);
        if(supplierName == null){
            Map<String, Object> bean = supplierDao.querySupplierByUserIdAndSupplier(params);
            if(bean != null){
                outputObject.setreturnMessage("供应商信息已存在！");
                return;
            }
        }
        int result = supplierDao.editSupplierById(params);
        if(result != 1){
            outputObject.setreturnMessage("编辑信息失败！");
            return;
        }
    }

    /**
     * 供应商状态改为启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editSupplierByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("enabled", 1);
        int result = supplierDao.editSupplierByEnabled(params);
        if(result != 1){
            outputObject.setreturnMessage("修改状态失败！");
        }
    }

    /**
     * 供应商状态改为未启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editSupplierByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("enabled", 2);
        int result = supplierDao.editSupplierByNotEnabled(params);
        if(result != 1){
            outputObject.setreturnMessage("修改状态失败！");
        }
    }
}
