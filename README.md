# erp_pro

> 基于SpringBoot 2.X框架，为中小企业打造开源好用的ERP软件。主要模块有零售管理、采购管理、销售管理、仓库管理、财务管理、报表查询、系统管理等。支持预付款、收入支出、仓库调拨、组装拆卸、订单，生产等特色功能。拥有库存状况、出入库统计等报表。对权限进行精确划分，同时支持多系统集成方案，可与OA，CRM，知识库等多个系统进行集成使用。同时对角色和权限进行了细致全面控制，精确到每个按钮和菜单。集成apollo配置中心。

- 该项目企业版有体验地址，需要的加微信
- 最新资讯：[2022-08-08 智能制造云办公 v3.7.25 发布，微服务拆分](https://www.bilibili.com/read/cv17978946)
- [软件更新资讯](https://gitee.com/doc_wei01/skyeye/blob/company_server/HISTORY_UPDATE.md)
- 开源版请下载`master`分支
- [项目功能结构](https://docs.qq.com/flowchart/DYUFQQnlCUm9Ua2FI)
- [企业版信息以及价格表](https://docs.qq.com/doc/DQlRxcVRMWWVjbU1i?_from=1&disableReturnList=1)
- [开源版项目文档](https://gitee.com/doc_wei01/skyeye/blob/master/%E9%A1%B9%E7%9B%AE%E6%96%87%E6%A1%A3.md)
- `企业版所有功能部署包`以及`设计思路`获取方式：扫码进入知识星球
- **最低1.8W即可获得企业版所有源代码**
- 作者本人承诺，知识星球人数达到1000人，即开放所有功能模块源代码(仅供星球内部成员使用)

### 演示视频

- 视频一：[多桌面任务](https://www.bilibili.com/video/BV1yb41127oB/)
- 视频二：[聊天](https://www.bilibili.com/video/BV11b41127FV)
- 视频三：[日程](https://www.bilibili.com/video/BV1vb411i75M)
- 视频四：[ERP + 生产 202007版](https://www.bilibili.com/video/BV1yA411e7mm)
- 视频五：[ERP + 生产 202109升级模块](https://www.bilibili.com/video/BV1vR4y1p7ka)

### 联系作者

| QQ交流群/或者搜索 1016439713(禁止发广告，进群后自行修改备注：岗位-城市-备注，不改者五分钟内自动请出。) | 企业版设计思路知识星球 | 扫码进微信群畅聊(禁止发广告，进群后自行修改备注：岗位-城市-备注，不改者五分钟内自动请出。)|
| ---- | ---- |---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![](https://gitee.com/doc_wei01/skyeye/raw/company_server/web/src/main/resources/template/tpl/common/skyeye%E7%B3%BB%E5%88%97QQ%E7%BE%A4%E8%81%8A%E4%BA%8C%E7%BB%B4%E7%A0%81.png)| ![](https://gitee.com/doc_wei01/skyeye/raw/company_server/web/src/main/resources/template/tpl/common/%E7%9F%A5%E8%AF%86%E6%98%9F%E7%90%83.png) | ![](https://gitee.com/doc_wei01/skyeye/raw/company_server/web/src/main/resources/template/tpl/common/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20220626095834.jpg) |

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
工作流|单据审批结合工作流，可通过系统设置来决策是否需要审批|[我的日程](https://gitee.com/doc_wei01/skyeye/blob/master/%E5%8A%9F%E8%83%BD%E6%96%87%E6%A1%A3%E4%BB%8B%E7%BB%8D/%E6%97%A5%E7%A8%8B%E6%A8%A1%E5%9D%97.md)(企业版)|[演示](https://www.bilibili.com/video/av45854959)|APP菜单管理(企业版)|手机端菜单以及权限管理
小程序管理(企业版)|微信小程序、H5手机自适应页面拖拽生成，可自定义配置小程序组件|多系统集成(企业版)|可以将多个系统进行应用集成，无需多次登陆，无需记录多个网址
流程图规划(企业版)|规划项目的流程图|问卷调查(企业版)|拖拽式生成问卷，可分页、复制、查看统计信息等
轻应用(企业版)|系统中提供各种小应用，如快递查询、高德地图等，用户可添加到自己的桌面上|开发文档(企业版)|系统支持二次开发，包含开发文档
工作日志(企业版)|记录每个员工的日报，周报，月报等，可同时发送多人，按时间轴查看等|多班次考勤管理(企业版)|记录每个员工的考勤打卡信息，包含报表，打卡，班次设置等
[我的笔记](https://gitee.com/doc_wei01/skyeye/blob/master/%E5%8A%9F%E8%83%BD%E6%96%87%E6%A1%A3%E4%BB%8B%E7%BB%8D/%E7%AC%94%E8%AE%B0%E6%A8%A1%E5%9D%97.md)(企业版)|员工可记录自己日常的笔记，目前支持MD，富文本，表格操作|报表管理(企业版)|统计功能信息，可根据客户自定义免费定制
[文件管理](https://gitee.com/doc_wei01/skyeye/blob/master/%E5%8A%9F%E8%83%BD%E6%96%87%E6%A1%A3%E4%BB%8B%E7%BB%8D/%E6%96%87%E4%BB%B6%E7%AE%A1%E7%90%86.md)(企业版)|公司内部、员工个人的文件管理，支持多格式文件在线查看，文档多人协作，在线解压缩等|附件管理(企业版)|保留员工所有上传过的附件，方便下次使用
邮件管理(企业版)|目前打通与QQ邮箱的交互，可以发邮件，收邮件，保存为草稿等|[工作流管理](https://gitee.com/doc_wei01/skyeye/blob/master/%E5%8A%9F%E8%83%BD%E6%96%87%E6%A1%A3%E4%BB%8B%E7%BB%8D/%E5%B7%A5%E4%BD%9C%E6%B5%81%E4%BB%8B%E7%BB%8D%E6%96%87%E6%A1%A3.md)(企业版)|动态表单结合工作流生成自定义业务流程审核,可进行审批、撤回、回退、节点化表单项编辑设置、驳回、终止转办等功能，目前已支持四十多种流程管理
论坛(企业版)|包括标签管理，关键词管理，举报审核等操作，用户可自由发表文章，系统通过过滤算法进行关键词过滤|计划管理(企业版)|方便公司进行公司计划、部门计划、个人计划的规划，可根据类型（日计划、周计划、月计划、季度计划等）进行定义
动态表单(企业版)|通过自定义的方式生成提交表单页，可与动态数据进行结合，目前已和工作流结合|行政管理(企业版)|包含车辆管理、会议室管理、用品管理、印章管理、财产管理、证照管理。所有功能审核已和工作流结合
内部公告(企业版)|系统内部公告通知，可设置邮件通知，定时通知，人员选择等|通讯录(企业版)|记录个人、公司内部、公共通讯录信息
知识库(企业版)|企业文化支柱；[效果地址](https://gitee.com/doc_wei01/knowlg-pro)|[CRM客户管理](https://gitee.com/doc_wei01/skyeye/blob/master/%E5%8A%9F%E8%83%BD%E6%96%87%E6%A1%A3%E4%BB%8B%E7%BB%8D/CRM%E5%AE%A2%E6%88%B7%E7%AE%A1%E7%90%86%E6%A8%A1%E5%9D%97.md)(企业版)|包含客户、商机、跟单、合同等多个模块化功能
[ERP进销存管理](https://gitee.com/doc_wei01/erp-pro)(企业版)|包含采购、销售、零售、客户、供应商等多个模块化功能；[效果地址](https://gitee.com/doc_wei01/erp-pro)|[项目管理](https://gitee.com/doc_wei01/skyeye/blob/master/%E5%8A%9F%E8%83%BD%E6%96%87%E6%A1%A3%E4%BB%8B%E7%BB%8D/%E9%A1%B9%E7%9B%AE%E7%AE%A1%E7%90%86.md)(企业版)|包含项目、工作量、讨论帖、项目文档、成本费用等功能
[云售后管理系统](https://gitee.com/doc_wei01/skyeye/blob/master/%E5%8A%9F%E8%83%BD%E6%96%87%E6%A1%A3%E4%BB%8B%E7%BB%8D/%E5%94%AE%E5%90%8E%E5%B7%A5%E5%8D%95%E6%A8%A1%E5%9D%97.md)(企业版)|包含工单的派工，接单，签到，配件申领审批，完工，评价，审核等操作|生产模块(企业版)|已完成，[演示视频](https://www.bilibili.com/video/BV1yA411e7mm/)
|学校模块以及考试模块(企业版)|[地址](https://gitee.com/doc_wei01/schoolExam)|EHR模块(企业版)|管理企业员工的基础信息|
|会员模块(企业版)|支持会员的操作以及会员订单的操作|门店模块(企业版)|支持门店管理|
|部署平台(企业版)|支持界面操作一键部署功能|||
|薪资模块(企业版)|员工薪资管理，支持多种类型设定|财务模块|狂吃狂吃开发中|
|招聘模块|支持面试流程，入职申请，转岗申请，离职申请等|||

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
xxl-job|定时任务|https://gitee.com/xuxueli0323/xxl-job?_from=gitee_search/
[RocketMQ](https://rocketmq.apache.org/dowloading/releases/)|消息队列|
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

