package com.skyeye.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ExcelUtil;
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
 * @Author 卫志强
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
    @SuppressWarnings("unchecked")
	@Override
    @Transactional(value="transactionManager")
    public void insertIncome(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        String initemStr = params.get("initemStr").toString();
        if(ToolUtil.isJson(initemStr)) {
            //财务主表ID
            String useId = ToolUtil.getSurFaceId();
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
            String orderNum = erpOrderNum.getAccountOrderNumBySubType(userId, ErpConstants.AccountTheadSubType.INCOME_ORDER.getNum());
            accountHead.put("id", useId);
            accountHead.put("type", ErpConstants.AccountTheadSubType.INCOME_ORDER.getNum());//收入单
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
            incomeDao.insertIncome(accountHead);
            incomeDao.insertIncomeItem(entitys);
        }else{
            outputObject.setreturnMessage("数据格式错误");
        }
    }

    /**
     * 查询收入单用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryIncomeToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = incomeDao.queryIncomeToEditById(params);
        if(bean != null && !bean.isEmpty()){
        	List<Map<String, Object>> beans = incomeDao.queryIncomeItemsToEditById(params);
        	bean.put("items", beans);
        	//获取经手人员
        	List<Map<String, Object>> userInfo = incomeDao.queryUserInfoById(bean);
        	bean.put("userInfo", userInfo);
        	outputObject.setBean(bean);
        	outputObject.settotal(1);
        }else{
        	outputObject.setreturnMessage("未查询到信息！");
        }
    }

    /**
     * 编辑收入单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@Override
    @Transactional(value="transactionManager")
    public void editIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        String initemStr = params.get("initemStr").toString();
        if(ToolUtil.isJson(initemStr)) {
        	String useId = params.get("id").toString();
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
            incomeDao.editIncomeById(accountHead);
            //删除之前的绑定信息
            incomeDao.deleteIncomeItemById(params);
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
        incomeDao.editIncomeItemsByDeleteFlag(params);
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
        Map<String, Object> bean = incomeDao.queryIncomeDetailById(params);
        if(bean != null && !bean.isEmpty()){
            //获取子表信息
            List<Map<String, Object>> beans = incomeDao.queryIncomeItemsDetailById(bean);
            bean.put("items", beans);
            outputObject.setBean(bean);
            outputObject.settotal(1);
        }else{
            outputObject.setreturnMessage("该数据已不存在.");
        }
    }

    /**
     * 导出Excel
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@SuppressWarnings("static-access")
	@Override
	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = incomeDao.queryMationToExcel(params);
        String[] key = new String[]{"billNo", "supplierName", "totalPrice", "hansPersonName", "billTime"};
        String[] column = new String[]{"单据编号", "往来单位", "合计金额", "经手人", "单据日期"};
        String[] dataType = new String[]{"", "data", "data", "data", "data"};
        //收入单信息导出
        ExcelUtil.createWorkBook("收入单", "收入单详细", beans, key, column, dataType, inputObject.getResponse());
	}
}
