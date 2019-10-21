package com.skyeye.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.skyeye.dao.ExpenditureDao;
import com.skyeye.erp.util.ErpConstants;
import com.skyeye.erp.util.ErpOrderNum;
import com.skyeye.service.ExpenditureService;

import net.sf.json.JSONArray;

/**
 * @Author 卫志强
 * @Description TODO
 * @Date 2019/10/20 10:23
 */
@Service
public class ExpenditureServiceImpl implements ExpenditureService {

    @Autowired
    private ExpenditureDao expenditureDao;

    /**
     * 查询支出单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryExpenditureByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = expenditureDao.queryExpenditureByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>) beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加支出单
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@Override
    @Transactional(value="transactionManager")
    public void insertExpenditure(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        String initemStr = params.get("initemStr").toString();
        if(ToolUtil.isJson(initemStr)) {
            //财务主表ID
            String useId = ToolUtil.getSurFaceId();
            String userId = inputObject.getLogParams().get("id").toString();
            //处理数据
            JSONArray jArray = JSONArray.fromObject(initemStr);
            //支出单中间转换对象，财务子表存储对象
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
                entity.put("headerId", useId);
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
            Map<String, Object> accountHead = new HashMap<>();
            ErpOrderNum erpOrderNum = new ErpOrderNum();
            String orderNum = erpOrderNum.getAccountOrderNumBySubType(userId, ErpConstants.AccountTheadSubType.EXPENDITURE_ORDER.getNum());
            accountHead.put("id", useId);
            accountHead.put("type", ErpConstants.AccountTheadSubType.EXPENDITURE_ORDER.getNum());//支出单
            accountHead.put("billNo", orderNum);
            accountHead.put("totalPrice", allPrice);
            accountHead.put("userId", userId);
            accountHead.put("organId", params.get("organId"));
            accountHead.put("operTime", params.get("operTime"));
            accountHead.put("accountId", params.get("accountId"));
            accountHead.put("handsPersonId", params.get("handsPersonId"));
            accountHead.put("remark", params.get("remark"));
            accountHead.put("changeAmount", params.get("changeAmount"));
            accountHead.put("deleteFlag", 0);
            expenditureDao.insertExpenditure(accountHead);
            expenditureDao.insertExpenditureItem(entitys);
        }else{
            outputObject.setreturnMessage("数据格式错误");
        }
    }

    /**
     * 查询支出单用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryExpenditureToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = expenditureDao.queryExpenditureToEditById(params);
        if(bean != null && !bean.isEmpty()){
        	List<Map<String, Object>> beans = expenditureDao.queryExpenditureItemsToEditById(params);
        	bean.put("items", beans);
        	//获取经手人员
        	List<Map<String, Object>> userInfo = expenditureDao.queryUserInfoById(bean);
        	bean.put("userInfo", userInfo);
        	outputObject.setBean(bean);
        	outputObject.settotal(1);
        }else{
        	outputObject.setreturnMessage("未查询到信息！");
        }
    }

    /**
     * 编辑支出单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@Override
    @Transactional(value="transactionManager")
    public void editExpenditureById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        String initemStr = params.get("initemStr").toString();
        if(ToolUtil.isJson(initemStr)) {
        	String useId = params.get("id").toString();
        	String userId = inputObject.getLogParams().get("id").toString();
            //处理数据
            JSONArray jArray = JSONArray.fromObject(initemStr);
            //支出单中间转换对象，财务子表存储对象
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
                entity.put("headerId", useId);
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
            Map<String, Object> accountHead = new HashMap<>();
            accountHead.put("id", useId);
            accountHead.put("userId", userId);
            accountHead.put("totalPrice", allPrice);
            accountHead.put("organId", params.get("organId"));
            accountHead.put("operTime", params.get("operTime"));
            accountHead.put("accountId", params.get("accountId"));
            accountHead.put("handsPersonId", params.get("handsPersonId"));
            accountHead.put("remark", params.get("remark"));
            accountHead.put("changeAmount", params.get("changeAmount"));
            expenditureDao.editExpenditureById(accountHead);
            //删除之前的绑定信息
            expenditureDao.deleteExpenditureItemById(params);
            expenditureDao.insertExpenditureItem(entitys);
        }else{
            outputObject.setreturnMessage("数据格式错误");
        }
    }

    /**
     * 删除支出单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteExpenditureById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("deleteFlag", 1);
        expenditureDao.editExpenditureByDeleteFlag(params);
        expenditureDao.editExpenditureItemsByDeleteFlag(params);
    }

    /**
     * 查看支出单详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryExpenditureByDetail(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        //获取财务主表信息
        Map<String, Object> bean = expenditureDao.queryExpenditureDetailById(params);
        if(bean != null && !bean.isEmpty()){
            //获取子表信息
            List<Map<String, Object>> beans = expenditureDao.queryExpenditureItemsDetailById(bean);
            bean.put("items", beans);
            outputObject.setBean(bean);
            outputObject.settotal(1);
        }else{
            outputObject.setreturnMessage("该数据已不存在.");
        }
    }
}
