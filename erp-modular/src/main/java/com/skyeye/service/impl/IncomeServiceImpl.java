package com.skyeye.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.IncomeDao;
import com.skyeye.erp.util.ErpConstants;
import com.skyeye.erp.util.ErpOrderNum;
import com.skyeye.service.IncomeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 奈何繁华如云烟
 * @Description TODO
 * @Date 2019/10/20 10:23
 */
@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeDao incomeDao;

    /**
     * 查询收入单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryIncomeByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = incomeDao.queryIncomeByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>) beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加收入单
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void insertIncome(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        String initemStr = params.get("initemStr").toString();
        if(ToolUtil.isJson(initemStr)) {
            //财务主表ID
            String incomeId = ToolUtil.getSurFaceId();
            String userId = inputObject.getLogParams().get("id").toString();
            //处理数据
            JSONArray jArray = JSONArray.fromObject(initemStr);
            //收入单中间转换对象，财务子表存储对象
            Map<String, Object> bean;
            List<Map<String, Object>> entitys = new ArrayList<>();//财务子表实体集合信息
            BigDecimal allPrice = new BigDecimal("0");//主单总价
            BigDecimal itemAllPrice = null;//子单对象
            for(int i = 0; i < jArray.size(); i++){
                bean = jArray.getJSONObject(i);
                Map<String, Object> entity = new HashMap<>();
                //获取子项金额
                itemAllPrice = new BigDecimal(bean.get("initemMoney").toString());
                entity.put("id", ToolUtil.getSurFaceId());
                entity.put("headerId", incomeId);
                entity.put("accountId", params.get("accountId"));
                entity.put("inOutItemId", bean.get("initemId"));
                entity.put("eachAmount", bean.get("initemMoney"));
                entity.put("remark", bean.get("remark"));
                entity.put("userId", userId);
                entity.put("deleteFlag", 0);
                entitys.add(entity);
                //计算总金额
                allPrice = allPrice.add(itemAllPrice);
            }
            if(entitys.size() == 0){
                outputObject.setreturnMessage("请选择收入项目");
                return;
            }
            Map<String, Object> income = new HashMap<>();
            ErpOrderNum erpOrderNum = new ErpOrderNum();
            String orderNum = erpOrderNum.getOrderNumBySubType(userId, ErpConstants.DepoTheadSubType.PURCHASE_ORDER.getNum());
            income.put("billNo", orderNum);
            income.put("totalPrice", allPrice);
            income.put("userId", userId);
            income.put("incomeId", incomeId);
            income.put("type", params.get("type"));
            income.put("organId", params.get("organId"));
            income.put("billTime", params.get("billTime"));
            income.put("accountId", params.get("accountId"));
            income.put("handsPersonId", params.get("handsPersonId"));
            income.put("remark", params.get("remark"));
            income.put("changeAmount", params.get("changeAmount"));
            income.put("deleteFlag", 0);
            incomeDao.insertIncome(income);
            incomeDao.insertIncomeItem(entitys);
        }else{
            outputObject.setreturnMessage("数据格式错误");
        }
    }

    /**
     * 查询单个收入单，用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = incomeDao.queryIncomeById(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到信息！");
            return;
        }
        List<Map<String, Object>> beans = incomeDao.queryIncomeItemsById(params);

        bean.put("items", beans);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑收入单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        String incomeId = params.get("id").toString();
        String userId = inputObject.getLogParams().get("id").toString();
        String initemStr = params.get("initemStr").toString();
        if(ToolUtil.isJson(initemStr)) {
            //处理数据
            JSONArray jArray = JSONArray.fromObject(initemStr);
            //收入单中间转换对象，财务子表存储对象
            Map<String, Object> bean;
            List<Map<String, Object>> entitys = new ArrayList<>();//财务子表实体集合信息
            BigDecimal allPrice = new BigDecimal("0");//主单总价
            BigDecimal itemAllPrice = null;//子单对象
            for(int i = 0; i < jArray.size(); i++){
                bean = jArray.getJSONObject(i);
                Map<String, Object> entity = new HashMap<>();
                //获取子项金额
                itemAllPrice = new BigDecimal(bean.get("initemMoney").toString());
                entity.put("id", ToolUtil.getSurFaceId());
                entity.put("headerId", incomeId);
                entity.put("accountId", params.get("accountId"));
                entity.put("inOutItemId", bean.get("initemId"));
                entity.put("eachAmount", bean.get("initemMoney"));
                entity.put("remark", bean.get("remark"));
                entity.put("userId", userId);
                entity.put("deleteFlag", "0");
                entitys.add(entity);
                //计算总金额
                allPrice = allPrice.add(itemAllPrice);
            }
            if(entitys.size() == 0){
                outputObject.setreturnMessage("请选择收入项目");
                return;
            }
            Map<String, Object> income = new HashMap<>();
            income.put("totalPrice", allPrice);
            income.put("id", params.get("id"));
            income.put("userId", userId);
            income.put("incomeId", incomeId);
            income.put("type", params.get("type"));
            income.put("organId", params.get("organId"));
            income.put("billTime", params.get("billTime"));
            income.put("accountId", params.get("accountId"));
            income.put("handsPersonId", params.get("handsPersonId"));
            income.put("remark", params.get("remark"));
            income.put("changeAmount", params.get("changeAmount"));
            incomeDao.editIncomById(income);
            incomeDao.deleteIncomItemById(params);
            incomeDao.insertIncomeItem(entitys);
        }else{
            outputObject.setreturnMessage("数据格式错误");
        }
    }

    /**
     * 删除收入单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("deleteFlag", 1);
        incomeDao.editIncomeByDeleteFlag(params);
        incomeDao.editIncomesByDeleteFlag(params);
    }

    /**
     * 查看收入单详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryIncomeByDetail(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        //获取财务主表信息
        Map<String, Object> bean = incomeDao.queryIncomeByDetail(params);
        if(bean != null && !bean.isEmpty()){
            //获取子表信息
            List<Map<String, Object>> beans = incomeDao.queryIncomeItemsByDetail(bean);
            bean.put("items", beans);
            outputObject.setBean(bean);
            outputObject.settotal(1);
        }else{
            outputObject.setreturnMessage("该数据已不存在.");
        }
    }
}
