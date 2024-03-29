/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysDeveLopDocDao;
import com.skyeye.eve.entity.codedoc.develop.SysDeveLopDocQueryDo;
import com.skyeye.eve.service.SysDeveLopDocService;
import com.skyeye.jedis.JedisClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysDeveLopDocServiceImpl implements SysDeveLopDocService {

    @Autowired
    private SysDeveLopDocDao sysDeveLopDocDao;

    @Autowired
    public JedisClientService jedisClient;

    /**
     * 获取开发文档目录信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopTypeList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysDeveLopDocDao.querySysDeveLopTypeList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 新增开发文档目录信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysDeveLopType(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopByName(map);
        if (CollectionUtils.isEmpty(bean)) {
            // 获取同级下的排位序号最大的数据
            Map<String, Object> item = sysDeveLopDocDao.queryMaxSysDeveLopBySimpleParentId(map);
            if (item == null) {
                map.put("orderBy", 1);
            } else {
                map.put("orderBy", Integer.parseInt(item.get("orderBy").toString()) + 1);
            }
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysDeveLopDocDao.insertSysDeveLopType(map);
        } else {
            outputObject.setreturnMessage("该目录已存在，请更换。");
        }
    }

    /**
     * 编辑开发文档目录信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopTypeByIdToEdit(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopTypeByIdToEdit(map);
        outputObject.setBean(bean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 编辑开发文档目录信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopTypeById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopByNameAndId(map);
        if (CollectionUtils.isEmpty(bean)) {
            sysDeveLopDocDao.editSysDeveLopTypeById(map);
        } else {
            outputObject.setreturnMessage("该目录已存在，请更换。");
        }
    }

    /**
     * 删除开发文档目录信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysDeveLopTypeById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopTypeContentNumById(map);
        if (bean == null || (Integer.parseInt(bean.get("contentNum").toString()) == 0 && Integer.parseInt(bean.get("childNum").toString()) == 0)) {
            sysDeveLopDocDao.deleteSysDeveLopTypeById(map);
        } else {
            outputObject.setreturnMessage("该目录下存在子目录或文档，请先进行子目录或者文档的删除。");
        }
    }

    /**
     * 获取一级文档目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopTypeByFirstType(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysDeveLopDocDao.querySysDeveLopTypeByFirstType(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 开发文档目录上线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopTypeStateISupById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopTypeStateById(map);
        if ("0".equals(bean.get("state").toString()) || "2".equals(bean.get("state").toString())) {//新建或者下线可以上线
            sysDeveLopDocDao.editSysDeveLopTypeStateISupById(map);
            if ("0".equals(bean.get("parentId").toString())) {
                jedisClient.del(Constants.getSysDeveLopDocFirstType());
            } else {
                jedisClient.del(Constants.getSysDeveLopDocSecondType(bean.get("parentId").toString()));
            }
        } else {
            outputObject.setreturnMessage("该目录状态已改变，请刷新页面。");
        }
    }

    /**
     * 开发文档目录下线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopTypeStateISdownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopTypeStateById(map);
        if ("1".equals(bean.get("state").toString())) {//上线状态可以下线
            sysDeveLopDocDao.editSysDeveLopTypeStateISdownById(map);
            if ("0".equals(bean.get("parentId").toString())) {
                jedisClient.del(Constants.getSysDeveLopDocFirstType());
            } else {
                jedisClient.del(Constants.getSysDeveLopDocSecondType(bean.get("parentId").toString()));
            }
        } else {
            outputObject.setreturnMessage("该目录状态已改变，请刷新页面。");
        }
    }

    /**
     * 开发文档目录上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopTypeOrderByISupById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopTypeOrderByISupById(map);
        if (bean != null && !bean.isEmpty()) {
            map.put("orderBy", bean.get("newOrderBy"));
            bean.put("orderBy", bean.get("oldOrderBy"));
            sysDeveLopDocDao.editSysDeveLopTypeOrderByISupById(map);
            sysDeveLopDocDao.editSysDeveLopTypeOrderByISupById(bean);
            if ("0".equals(bean.get("parentId").toString())) {
                jedisClient.del(Constants.getSysDeveLopDocFirstType());
            } else {
                jedisClient.del(Constants.getSysDeveLopDocSecondType(bean.get("parentId").toString()));
            }
        } else {
            outputObject.setreturnMessage("已经是最首位的目录。");
        }
    }

    /**
     * 开发文档目录下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopTypeOrderByISdownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopTypeOrderByISdownById(map);
        if (bean != null && !bean.isEmpty()) {
            map.put("orderBy", bean.get("newOrderBy"));
            bean.put("orderBy", bean.get("oldOrderBy"));
            sysDeveLopDocDao.editSysDeveLopTypeOrderByISdownById(map);
            sysDeveLopDocDao.editSysDeveLopTypeOrderByISdownById(bean);
            if ("0".equals(bean.get("parentId").toString())) {
                jedisClient.del(Constants.getSysDeveLopDocFirstType());
            } else {
                jedisClient.del(Constants.getSysDeveLopDocSecondType(bean.get("parentId").toString()));
            }
        } else {
            outputObject.setreturnMessage("已经是最末位的目录。");
        }
    }

    /**
     * 获取开发文档信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopDocList(InputObject inputObject, OutputObject outputObject) {
        SysDeveLopDocQueryDo sysDeveLopDocQuery = inputObject.getParams(SysDeveLopDocQueryDo.class);
        Page pages = PageHelper.startPage(sysDeveLopDocQuery.getPage(), sysDeveLopDocQuery.getLimit());
        List<Map<String, Object>> beans = sysDeveLopDocDao.querySysDeveLopDocList(sysDeveLopDocQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增开发文档信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addSysDeveLopDoc(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopDocByNameAndParentId(map);
        if (CollectionUtils.isEmpty(bean)) {
            // 获取同级下的排位序号最大的数据
            Map<String, Object> item = sysDeveLopDocDao.queryMaxSysDeveLopDocBySimpleParentId(map);
            if (item == null) {
                map.put("orderBy", 1);
            } else {
                map.put("orderBy", Integer.parseInt(item.get("orderBy").toString()) + 1);
            }
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            sysDeveLopDocDao.insertSysDeveLopDoc(map);
        } else {
            outputObject.setreturnMessage("该文档已存在，请更换。");
        }
    }

    /**
     * 编辑开发文档信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopDocByIdToEdit(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopDocByIdToEdit(map);
        outputObject.setBean(bean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 编辑开发文档信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopDocById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopDocByNameAndId(map);
        if (CollectionUtils.isEmpty(bean)) {
            sysDeveLopDocDao.editSysDeveLopDocById(map);
        } else {
            outputObject.setreturnMessage("该文档标题已存在，请更换。");
        }
    }

    /**
     * 删除开发文档信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysDeveLopDocById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        sysDeveLopDocDao.deleteSysDeveLopDocById(map);
    }

    /**
     * 开发文档上线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopDocStateISupById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopDocStateById(map);
        if ("0".equals(bean.get("state").toString()) || "2".equals(bean.get("state").toString())) {
            // 新建或者下线可以上线
            sysDeveLopDocDao.editSysDeveLopDocStateISupById(map);
            // 删除父分类的redis
            jedisClient.del(Constants.getSysDeveLopDocTitleList(bean.get("typeId").toString()));
            // 删除开发文档的redis
            jedisClient.del(Constants.getSysDeveLopDocContent(map.get("id").toString()));
        } else {
            outputObject.setreturnMessage("该文档状态已改变，请刷新页面。");
        }
    }

    /**
     * 开发文档下线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopDocStateISdownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopDocStateById(map);
        if ("1".equals(bean.get("state").toString())) {
            // 上线状态可以下线
            sysDeveLopDocDao.editSysDeveLopDocStateISdownById(map);
            // 删除父分类的redis
            jedisClient.del(Constants.getSysDeveLopDocTitleList(bean.get("typeId").toString()));
            // 删除开发文档的redis
            jedisClient.del(Constants.getSysDeveLopDocContent(map.get("id").toString()));
        } else {
            outputObject.setreturnMessage("该文档状态已改变，请刷新页面。");
        }
    }

    /**
     * 开发文档上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopDocOrderByISupById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopDocOrderByISupById(map);
        if (bean != null && !bean.isEmpty()) {
            map.put("orderBy", bean.get("newOrderBy"));
            bean.put("orderBy", bean.get("oldOrderBy"));
            sysDeveLopDocDao.editSysDeveLopDocOrderByISupById(map);
            sysDeveLopDocDao.editSysDeveLopDocOrderByISupById(bean);
            jedisClient.del(Constants.getSysDeveLopDocTitleList(bean.get("typeId").toString()));//删除父分类的redis
        } else {
            outputObject.setreturnMessage("已经是最首位的文档。");
        }
    }

    /**
     * 开发文档下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysDeveLopDocOrderByISdownById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysDeveLopDocDao.querySysDeveLopDocOrderByISdownById(map);
        if (bean != null && !bean.isEmpty()) {
            map.put("orderBy", bean.get("newOrderBy"));
            bean.put("orderBy", bean.get("oldOrderBy"));
            sysDeveLopDocDao.editSysDeveLopDocOrderByISdownById(map);
            sysDeveLopDocDao.editSysDeveLopDocOrderByISdownById(bean);
            jedisClient.del(Constants.getSysDeveLopDocTitleList(bean.get("typeId").toString()));//删除父分类的redis
        } else {
            outputObject.setreturnMessage("已经是最末位的文档。");
        }
    }

    /**
     * 获取一级分类列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopFirstTypeToShow(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = new ArrayList<>();
        if (ToolUtil.isBlank(jedisClient.get(Constants.getSysDeveLopDocFirstType()))) {
            beans = sysDeveLopDocDao.querySysDeveLopFirstTypeToShow(map);
            jedisClient.set(Constants.getSysDeveLopDocFirstType(), JSONUtil.toJsonStr(beans));
        } else {
            beans = JSONUtil.toList(jedisClient.get(Constants.getSysDeveLopDocFirstType()), null);
        }
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取二级分类列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopSecondTypeToShow(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = new ArrayList<>();
        if (ToolUtil.isBlank(jedisClient.get(Constants.getSysDeveLopDocSecondType(map.get("parentId").toString())))) {
            beans = sysDeveLopDocDao.querySysDeveLopSecondTypeToShow(map);
            jedisClient.set(Constants.getSysDeveLopDocSecondType(map.get("parentId").toString()), JSONUtil.toJsonStr(beans));
        } else {
            beans = JSONUtil.toList(jedisClient.get(Constants.getSysDeveLopDocSecondType(map.get("parentId").toString())), null);
        }
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取文档标题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopDocToShow(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans;
        if (ToolUtil.isBlank(jedisClient.get(Constants.getSysDeveLopDocTitleList(map.get("parentId").toString())))) {
            beans = sysDeveLopDocDao.querySysDeveLopDocToShow(map);
            jedisClient.set(Constants.getSysDeveLopDocTitleList(map.get("parentId").toString()), JSONUtil.toJsonStr(beans));
        } else {
            beans = JSONUtil.toList(jedisClient.get(Constants.getSysDeveLopDocTitleList(map.get("parentId").toString())), null);
        }
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取文档内容
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeveLopDocContentToShow(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean;
        if (ToolUtil.isBlank(jedisClient.get(Constants.getSysDeveLopDocContent(map.get("id").toString())))) {
            bean = sysDeveLopDocDao.querySysDeveLopDocContentToShow(map);
            jedisClient.set(Constants.getSysDeveLopDocContent(map.get("id").toString()), JSONUtil.toJsonStr(bean));
        } else {
            bean = JSONUtil.toBean(jedisClient.get(Constants.getSysDeveLopDocContent(map.get("id").toString())), null);
        }
        outputObject.setBean(bean);

    }

}
