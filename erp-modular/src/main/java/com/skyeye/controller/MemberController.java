package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 奈何繁华如云烟
 * @Description 会员管理
 * @Date 2019/9/16 21:21
 */
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 获取会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MemberController/queryMemberByList")
    @ResponseBody
    public void queryMemberByList(InputObject inputObject, OutputObject outputObject) throws Exception{
        memberService.queryMemberByList(inputObject, outputObject);
    }

    /**
     * 添加会员
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MemberController/insertMember")
    @ResponseBody
    public void insertMember(InputObject inputObject, OutputObject outputObject) throws Exception{
        memberService.insertMember(inputObject, outputObject);
    }

    /**
     * 根据ID查询会员信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MemberController/queryMemberById")
    @ResponseBody
    public void queryMemberById(InputObject inputObject, OutputObject outputObject) throws Exception{
        memberService.queryMemberById(inputObject, outputObject);
    }

    /**
     * 删除会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MemberController/deleteMemberById")
    @ResponseBody
    public void deleteMemberById(InputObject inputObject, OutputObject outputObject) throws Exception{
        memberService.deleteMemberById(inputObject, outputObject);
    }

    /**
     * 编辑会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MemberController/editMemberById")
    @ResponseBody
    public void editMemberById(InputObject inputObject, OutputObject outputObject) throws Exception{
        memberService.editMemberById(inputObject, outputObject);
    }

    /**
     * 会员状态改为启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MemberController/editMemberByEnabled")
    @ResponseBody
    public void editMemberByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception{
        memberService.editMemberByEnabled(inputObject, outputObject);
    }

    /**
     * 会员状态改为未启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MemberController/editMemberByNotEnabled")
    @ResponseBody
    public void editMemberByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception{
        memberService.editMemberByNotEnabled(inputObject, outputObject);
    }
}
