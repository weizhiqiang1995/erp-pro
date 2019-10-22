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
import com.skyeye.dao.TransferDao;
import com.skyeye.erp.util.ErpConstants;
import com.skyeye.erp.util.ErpOrderNum;
import com.skyeye.service.TransferService;

import net.sf.json.JSONArray;

/**
 * @Author 卫志强
 * @Description TODO
 * @Date 2019/10/20 10:23
 */
@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferDao transferDao;

    /**
     * 查询转账单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryTransferByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = transferDao.queryTransferByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>) beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加转账单
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@Override
    @Transactional(value="transactionManager")
    public void insertTransfer(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        String initemStr = params.get("initemStr").toString();
        if(ToolUtil.isJson(initemStr)) {
            //财务主表ID
            String useId = ToolUtil.getSurFaceId();
            String userId = inputObject.getLogParams().get("id").toString();
            //处理数据
            JSONArray jArray = JSONArray.fromObject(initemStr);
            //转账单中间转换对象，财务子表存储对象
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
                entity.put("accountId", bean.get("accountId"));
                entity.put("eachAmount", bean.get("initemMoney"));
                entity.put("remark", bean.get("remark"));
                entity.put("userId", userId);
                entity.put("deleteFlag", 0);
                entitys.add(entity);
                //计算总金额
                allPrice = allPrice.add(itemAllPrice);
            }
            if(entitys.size() == 0){
                outputObject.setreturnMessage("请选择账户");
                return;
            }
            Map<String, Object> accountHead = new HashMap<>();
            ErpOrderNum erpOrderNum = new ErpOrderNum();
            String orderNum = erpOrderNum.getAccountOrderNumBySubType(userId, ErpConstants.AccountTheadSubType.TRANSFER_ORDER.getNum());
            accountHead.put("id", useId);
            accountHead.put("type", ErpConstants.AccountTheadSubType.TRANSFER_ORDER.getNum());//转账单
            accountHead.put("billNo", orderNum);
            accountHead.put("totalPrice", allPrice);
            accountHead.put("userId", userId);
            accountHead.put("accountId", params.get("accountId"));
            accountHead.put("operTime", params.get("operTime"));
            accountHead.put("handsPersonId", params.get("handsPersonId"));
            accountHead.put("remark", params.get("remark"));
            accountHead.put("changeAmount", params.get("changeAmount"));
            accountHead.put("deleteFlag", 0);
            transferDao.insertTransfer(accountHead);
            transferDao.insertTransferItem(entitys);
        }else{
            outputObject.setreturnMessage("数据格式错误");
        }
    }

    /**
     * 查询转账单用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryTransferToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = transferDao.queryTransferToEditById(params);
        if(bean != null && !bean.isEmpty()){
        	List<Map<String, Object>> beans = transferDao.queryTransferItemsToEditById(params);
        	bean.put("items", beans);
        	//获取经手人员
        	List<Map<String, Object>> userInfo = transferDao.queryUserInfoById(bean);
        	bean.put("userInfo", userInfo);
        	outputObject.setBean(bean);
        	outputObject.settotal(1);
        }else{
        	outputObject.setreturnMessage("未查询到信息！");
        }
    }

    /**
     * 编辑转账单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@Override
    @Transactional(value="transactionManager")
    public void editTransferById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        String initemStr = params.get("initemStr").toString();
        if(ToolUtil.isJson(initemStr)) {
        	String useId = params.get("id").toString();
        	String userId = inputObject.getLogParams().get("id").toString();
            //处理数据
            JSONArray jArray = JSONArray.fromObject(initemStr);
            //转账单中间转换对象，财务子表存储对象
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
                entity.put("accountId", bean.get("accountId"));
                entity.put("eachAmount", bean.get("initemMoney"));
                entity.put("remark", bean.get("remark"));
                entity.put("userId", userId);
                entity.put("deleteFlag", "0");
                entitys.add(entity);
                //计算总金额
                allPrice = allPrice.add(itemAllPrice);
            }
            if(entitys.size() == 0){
                outputObject.setreturnMessage("请选择账户");
                return;
            }
            Map<String, Object> accountHead = new HashMap<>();
            accountHead.put("id", useId);
            accountHead.put("userId", userId);
            accountHead.put("totalPrice", allPrice);
            accountHead.put("accountId", params.get("accountId"));
            accountHead.put("operTime", params.get("operTime"));
            accountHead.put("handsPersonId", params.get("handsPersonId"));
            accountHead.put("remark", params.get("remark"));
            accountHead.put("changeAmount", params.get("changeAmount"));
            transferDao.editTransferById(accountHead);
            //删除之前的绑定信息
            transferDao.deleteTransferItemById(params);
            transferDao.insertTransferItem(entitys);
        }else{
            outputObject.setreturnMessage("数据格式错误");
        }
    }

    /**
     * 删除转账单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteTransferById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("deleteFlag", 1);
        transferDao.editTransferByDeleteFlag(params);
        transferDao.editTransferItemsByDeleteFlag(params);
    }

    /**
     * 查看转账单详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryTransferByDetail(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        //获取财务主表信息
        Map<String, Object> bean = transferDao.queryTransferDetailById(params);
        if(bean != null && !bean.isEmpty()){
            //获取子表信息
            List<Map<String, Object>> beans = transferDao.queryTransferItemsDetailById(bean);
            bean.put("items", beans);
            outputObject.setBean(bean);
            outputObject.settotal(1);
        }else{
            outputObject.setreturnMessage("该数据已不存在.");
        }
    }
}
