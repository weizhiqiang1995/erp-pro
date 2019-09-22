package com.skyeye.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.MemberDao;
import com.skyeye.service.MemberService;
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
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    /**
     * 获取会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryMemberByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        List<Map<String, Object>> beans = memberDao.queryMemberByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void insertMember(InputObject inputObject, OutputObject outputObject) throws Exception {
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
        //验证某一租户下会员信息是否存
        Map<String, Object> bean = memberDao.queryMemberByUserIdAndMember(params);
        if(bean != null){
            outputObject.setreturnMessage("该会员信息已存在！");
            return;
        }
        params.put("memberId", ToolUtil.getSurFaceId());
        params.put("createTime", ToolUtil.getTimeAndToString());
        params.put("memberType", 3);
        params.put("enabled", 1);
        params.put("isystem", 1);
        params.put("deleteFlag", 0);
        memberDao.insertMember(params);
    }

    /**
     * 根据ID查询会员信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryMemberById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> bean = memberDao.queryMemberById(params);
        if (bean == null){
            outputObject.setreturnMessage("未查询到该会员信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 删除会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteMemberById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("deleteFlag", 1);
        int result = memberDao.editMemberByDeleteFlag(params);
        if(result != 1){
            outputObject.setreturnMessage("删除失败!");
            return;
        }
    }

    /**
     * 编辑会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editMemberById(InputObject inputObject, OutputObject outputObject) throws Exception {
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
        Map<String, Object> memberName = memberDao.queryMemberByIdAndName(params);
        if(memberName == null){
            Map<String, Object> bean = memberDao.queryMemberByUserIdAndMember(params);
            if(bean != null){
                outputObject.setreturnMessage("该会员信息已存在！");
                return;
            }
        }
        int result = memberDao.editMemberById(params);
        if(result != 1){
            outputObject.setreturnMessage("编辑信息失败！");
            return;
        }
    }

    /**
     * 会员状态改为启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editMemberByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("enabled", 1);
        int result = memberDao.editMemberByEnabled(params);
        if(result != 1){
            outputObject.setreturnMessage("修改状态失败！");
        }
    }

    /**
     * 会员状态改为未启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editMemberByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("userId", inputObject.getLogParams().get("id"));
        params.put("enabled", 2);
        int result = memberDao.editMemberByNotEnabled(params);
        if(result != 1){
            outputObject.setreturnMessage("修改状态失败！");
        }
    }
}
