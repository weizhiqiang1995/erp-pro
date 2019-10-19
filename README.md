# erp_pro

> win10风格的一套系统，前端采用layui作为前端框架，后端采用SpringBoot作为服务框架，采用自封装的xml对所有请求进行参数校验，以保证接口安全性。`项目长期更新`，觉得不错的点下star吧

> 注：开源社区版只限学习，内设授权码，默认十天删除所有非基础数据

#### 介绍
基于SpringBoot框架和SaaS模式，立志为中小企业提供开源好用的多租户ERP软件，目前专注进销存+财务功能。主要模块有零售管理、采购管理、销售管理、仓库管理、财务管理、报表查询、系统管理等。支持预付款、收入支出、仓库调拨、组装拆卸、订单等特色功能。拥有库存状况、出入库统计等报表。同时对角色和权限进行了细致全面控制，精确到每个按钮和菜单。

> 系统新增传统风格界面，layui左菜单右内容风格。

#### 启动方式
直接运行com.SkyeyeErpApplication即可，启动完成后，访问`http://localhost:8086`即可。 初始化账号密码：root/123456

#### 软件架构

- Spring Boot 2.X
- Layui 
- MySql 5.5.28
- JDK 1.8
- Redis 3.2集群（不要问我单机的能不能行）

#### 声明
如有定制需求，可入群或将需求发送至邮箱`598748873@qq.com`。

#### 功能介绍

功能|功能|功能|功能
-------|-------|-------|-------
菜单管理|员工管理|用户管理|角色管理
权限管理|资源图标|日志管理|多桌面管理
系统基础设置|系统的基础信息设置|计量单位|产品分类
产品信息|客户管理|供应商管理|会员管理
仓库管理|账户信息|收支项目|结算账户
账户流水|采购单|其他入库|入库明细
出库明细|||

#### 技术选型

##### 后端技术:

技术|名称
---|---
[SpringBoot](http://spring.io/projects/spring-boot)|核心框架
[MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html)|ORM框架
[Druid](https://github.com/alibaba/druid)|数据库连接池
[Maven](http://maven.apache.org/)|项目构建管理
[redis](https://redis.io/)|key-value存储系统
[webSocket](http://www.runoob.com/html/html5-websocket.html)|浏览器与服务器全双工(full-duplex)通信
[quartz 2.2.2](http://www.quartz-scheduler.org/)|定时任务
[ActiveMQ](http://activemq.apache.org/replicated-leveldb-store.html)|消息队列

##### 前端技术：

技术|名称
---|---
[jQuery](http://jquery.com/)|函式库
[zTree](http://www.treejs.cn/v3/)|树插件
[layui](https://www.layui.com/)|模块化前端UI
[winui](https://gitee.com/doc_wei01_admin/skyeye)|win10风格UI
[handlebars](http://www.ghostchina.com/introducing-the-handlebars-js-templating-engine/)|js模板引擎
[webSocket](http://www.runoob.com/html/html5-websocket.html)|浏览器与服务器全双工(full-duplex)通信

#### 代码描述
##### 前后台接口映射

```
<url id="前端请求id" path="后台接口" val="备注" allUse="是否需要登录">
	<property id="前端请求key" name="后台接收key" ref="限制条件（参考项目内文档）" var="key含义"/>
</url>
```

##### 后台代码编写规范

###### 控制层

```
@RequestMapping("后台接口")
public void 方法名(InputObject inputObject, OutputObject outputObject) throws Exception{
	服务层接口对象.方法名(inputObject, outputObject);
}
```

###### 服务层

```
@Override
public void 方法名(InputObject inputObject, OutputObject outputObject) throws Exception {
	Map<String, Object> map = inputObject.getParams();//接收参数
	Map<String, Object> user = inputObject.getLogParams();//获取当前登录用户信息
	/**
	 * 业务逻辑
	 */
	outputObject.setBean(bean);//返回单个实体Bean
	outputObject.setBeans(beans);//返回集合
	outputObject.settotal(total);//返回数量
	outputObject.setreturnMessage("信息");//返回前端的错误信息
	outputObject.setreturnMessage("信息", 错误码);//返回前端的错误信息，同时抛出异常（不常用）
}
```

#### 效果图

|效果图|效果图|
| ------------- | ------------- |
|![](https://images.gitee.com/uploads/images/2019/1011/084458_e0c2e256_1541735.png "")|![](https://s2.ax1x.com/2019/10/11/uHSaY8.png "")|
|![](https://images.gitee.com/uploads/images/2019/1011/084630_9326c93d_1541735.png "")|![](https://s2.ax1x.com/2019/10/11/uHpkh8.png "")|
|![](https://images.gitee.com/uploads/images/2019/1011/084706_8c921d85_1541735.png "")|![](https://s2.ax1x.com/2019/10/11/uHpeXj.png "")|
|![](https://images.gitee.com/uploads/images/2019/1015/091741_4030a2f0_1541735.png "")|![](https://s2.ax1x.com/2019/10/15/K9meR1.png "")|

#### 环境搭建
##### 开发工具:

- MySql: 数据库</br>
- Tomcat: 应用服务器</br>
- SVN|Git: 版本管理</br>
- Nginx: 反向代理服务器</br>
- Varnish: HTTP加速器</br>
- IntelliJ IDEA|Eclipse: 开发IDE</br>
- Navicat for MySQL: 数据库客户端</br>
- Redis Manager：redis视图工具</br>

#### 资源下载

- [JDK8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](http://maven.apache.org/download.cgi)
- [Redis](https://redis.io/download)
- [Nginx](http://nginx.org/en/download.html)

#### 在线文档

- [JDK8中文文档](https://blog.fondme.cn/apidoc/jdk-1.8-youdao/)
- [Spring4.x文档](http://spring.oschina.mopaas.com/)
- [Mybatis3官网](http://www.mybatis.org/mybatis-3/zh/index.html)
- [Nginx中文文档](http://tool.oschina.net/apidocs/apidoc?api=nginx-zh)
- [Git官网中文文档](https://git-scm.com/book/zh/v2)

#### 项目交流：

QQ群号：[696070023](http://shang.qq.com/wpa/qunwpa?idkey=e9aace2bf3e05f37ed5f0377c3827c6683d970ac0bcc61b601f70dc861053229)

> 需要了解的请加微信或者进群：wzq_598748873，备注：码云-公司（姓名）。

|QQ群|公众号|
|-------|-------|
|![](https://images.gitee.com/uploads/images/2018/1205/145236_4fce6966_1541735.jpeg "微信图片_20181205145217.jpg")|![](https://images.gitee.com/uploads/images/2018/1207/083137_48330589_1541735.jpeg "qrcode_for_gh_e7f97ff1beda_258.jpg")|