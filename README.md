# erp_pro

> 基于SpringBoot 2.X框架，为中小企业打造开源好用的ERP软件。主要模块有零售管理、采购管理、销售管理、仓库管理、财务管理、报表查询、系统管理等。支持预付款、收入支出、仓库调拨、组装拆卸、订单，生产等特色功能。拥有库存状况、出入库统计等报表。对权限进行精确划分，同时支持多系统集成方案，可与OA，CRM，知识库等多个系统进行集成使用。同时对角色和权限进行了细致全面控制，精确到每个按钮和菜单。集成apollo配置中心。

- 该项目企业版有体验地址，需要的加微信(账号独立，不免费)
- [软件更新资讯](https://gitee.com/doc_wei01/skyeye/blob/company_server/HISTORY_UPDATE.md)
- 开源版请下载`master`分支
- [项目功能结构](https://docs.qq.com/flowchart/DYUFQQnlCUm9Ua2FI)
- [企业版信息](https://docs.qq.com/doc/DQlRxcVRMWWVjbU1i?_from=1&disableReturnList=1)
- [开源版项目文档](https://gitee.com/doc_wei01/skyeye/blob/master/%E9%A1%B9%E7%9B%AE%E6%96%87%E6%A1%A3.md)
- `企业版源代码`以及`设计思路`获取方式：扫码进入知识星球
- 目前企业版开放代码：API，代码生成器，企业网盘，员工资料，邮件，考试，论坛，工作日志，知识库，轻应用，通讯录，公告，笔记，组织架构，工作计划，定时任务，小程序编辑器，日程，薪资，业务流程规划，问卷设计，员工账号信息等。


### 联系作者

| 作者微信/或者搜索 wzq_598748873 | QQ交流群/或者搜索 1016439713 | 更新资讯公众号 | 企业版设计思路知识星球 |
| ------ | ---- | ---- | ---- |
| ![      ](https://gitee.com/doc_wei01/skyeye/raw/company_server/web/src/main/resources/template/tpl/common/%E5%BE%AE%E4%BF%A1.jpg) | ![     ](https://gitee.com/doc_wei01/skyeye/raw/company_server/web/src/main/resources/template/tpl/common/skyeye%E7%B3%BB%E5%88%97QQ%E7%BE%A4%E8%81%8A%E4%BA%8C%E7%BB%B4%E7%A0%81.png)|![输入图片说明](https://images.gitee.com/uploads/images/2021/0320/091531_8c3ba4d8_1541735.jpeg "qrcode_for_gh_e7f97ff1beda_430.jpg")| ![输入图片说明](https://gitee.com/doc_wei01/skyeye/raw/company_server/web/src/main/resources/template/tpl/common/%E7%9F%A5%E8%AF%86%E6%98%9F%E7%90%83.png) |

#### 环境搭建

> 该项目是maven工程，如果遇到jar包没下载下来的问题，请更换镜像地址.

- 1.搭建apollo配置中心，将`docs/配置中心参数.md`复制到配置中心
- 2.搭建redis（3.2）集群
- 3.MySQL 5.5.X
- 4.最后修改配置中心参数，启动`SkyeyeErpApplication.java`项目

#### 开源版功能介绍

功能|描述|功能|描述
---|---|---|---
用户管理|用户是系统操作者，该功能主要完成系统用户配置|部门管理|配置系统组织机构（公司、部门、小组），树结构展现支持数据权限
岗位管理|配置系统用户所属担任职务|菜单管理|配置系统菜单，操作权限，按钮权限标识等
角色管理|角色菜单权限分配、设置角色按机构进行数据范围权限划分|基础设置|包含背景图片设置、锁屏图片设置等
计量单位|产品规格单位管理|结算账户|自定义账户的结算方式以及结算明细的查看
收支(科目)项目|自定义除了采购/销售等产生的费用外，其他产生费用的项目|基本资料|包含会员管理、供应商管理,商业版客户已抽离为CRM
采购管理|包含采购单、转入库单、采购入库、采购退货等功能，整改完成|销售管理|包含销售单、转出库单、销售出库、销售退货等功能，整改完成
其他入库管理|整改完成|库存盘点|对现有仓库的库存进行盘点
零售管理|包含零售出库、零售退货等功能，整改完成|拆分单|整改完成
调拨单|整改完成|仓库管理|管理用户所拥有的仓库,整改完成
商品管理|管理用户所拥有的产品信息,整改完成|其他单据管理|包含其他入库、其他出库、调拨、组装单、拆分单等功能
报表管理|包含入库/出库明细、入库/出库汇总、库存状况、客户/供应商/会员对账等报表|财务管理|收入单、支出单、收付款单、转账单等
库存管理|对比开源版新增库存管理，可以查看每个商品不同规格的库存数|工序管理|管理每个商品自产的流程工序
BOM表|为自产商品打造结构清单，清晰了解成本价等|生产计划|销售计划单直接生成生产计划（也可以手动下达生产计划），可根据BOM表自动生成半成品生产计划
加工单管理|根据生产计划单下达部门加工单，完成成品的加工|领料单|部门领料单管理，部门员工通过领料单从仓库进行领料
补料单|部门补料单管理|退料单|部门退料单管理
部门存量管理|部门拥有的物料存量管理|工艺路线管理|工艺路线设计
车间管理|生产车间管理|加工机器管理|工厂加工机器信息登记
工作流|单据审批结合工作流，可通过系统设置来决策是否需要审批||

#### 技术选型

##### 后端技术:

技术|名称|版本
---|---|---
[SpringBoot](http://spring.io/projects/spring-boot)|核心框架|2.0.3
[MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html)|ORM框架
[Druid](https://github.com/alibaba/druid)|数据库连接池|
[Maven](http://maven.apache.org/)|项目构建管理|
[redis](https://redis.io/)|key-value存储系统|3.2集群（不要问我单机的能不能行）
[webSocket](http://www.runoob.com/html/html5-websocket.html)|浏览器与服务器全双工(full-duplex)通信|
[quartz](http://www.quartz-scheduler.org/)|定时任务|2.2.2
[ActiveMQ](http://activemq.apache.org/replicated-leveldb-store.html)|消息队列|
[Java]()|Java|1.8
[MySQL]()|数据库|5.5.28

##### 前端技术：

技术|名称
---|---
[jQuery](http://jquery.com/)|函式库
[zTree](http://www.treejs.cn/v3/)|树插件
[layui](https://www.layui.com/)|模块化前端UI
[winui](https://gitee.com/doc_wei01/skyeye)|win10风格UI(自己做的前端架构)
[handlebars](http://www.ghostchina.com/introducing-the-handlebars-js-templating-engine/)|js模板引擎
[webSocket](http://www.runoob.com/html/html5-websocket.html)|浏览器与服务器全双工(full-duplex)通信

#### 效果图

|效果图|效果图|
| ------------- | ------------- |
|![](https://images.gitee.com/uploads/images/2019/1011/084458_e0c2e256_1541735.png "")|![](https://s2.ax1x.com/2019/10/11/uHSaY8.png "")|
|![](https://images.gitee.com/uploads/images/2019/1011/084630_9326c93d_1541735.png "")|![](https://s2.ax1x.com/2019/10/11/uHpkh8.png "")|
|![](https://images.gitee.com/uploads/images/2019/1011/084706_8c921d85_1541735.png "")|![](https://s2.ax1x.com/2019/10/11/uHpeXj.png "")|
|![](https://images.gitee.com/uploads/images/2019/1015/091741_4030a2f0_1541735.png "")|![](https://s2.ax1x.com/2019/10/15/K9meR1.png "")|
|![输入图片说明](https://images.gitee.com/uploads/images/2019/1022/165203_457e90a8_1541735.png "在这里输入图片标题")|![输入图片说明](https://images.gitee.com/uploads/images/2019/1022/165318_c88d74e3_1541735.png "在这里输入图片标题")|
|![输入图片说明](https://images.gitee.com/uploads/images/2019/1022/165355_a7090ee3_1541735.png "在这里输入图片标题")|![输入图片说明](https://images.gitee.com/uploads/images/2019/1022/165505_0f5c5162_1541735.png "在这里输入图片标题")|
|![输入图片说明](https://images.gitee.com/uploads/images/2019/1022/165537_fbb78be8_1541735.png "在这里输入图片标题")|![输入图片说明](https://images.gitee.com/uploads/images/2019/1023/152325_95480f53_1541735.png "在这里输入图片标题")|

