var rowId = "";
layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'table', 'jquery', 'winui', 'form'], function (exports) {
    winui.renderColor();
    authBtn('1569133160398');
    var $ = layui.$,
        form = layui.form,
        table = layui.table;
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'customer001',
        where: {customerName:$("#customerName").val(),
            telephone: $("#telephone").val(),
            email: $("#email").val(),
            fax: $("#fax").val(),
            enabled: $("#enabled").val()},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'customerName', title: '客户名称', align: 'left',width: 140,templet: function(d){
                return '<a lay-event="select" class="notice-title-click">' + d.customerName + '</a>';
            }},
            { field: 'contacts', title: '联系人', align: 'left',width: 100},
            { field: 'phonenum', title: '联系电话', align: 'center',width: 100},
            { field: 'email', title: '电子邮箱', align: 'center',width: 120},
            { field: 'telephone', title: '手机号码', align: 'center',width: 150},
            { field: 'fax', title: '传真', align: 'left',width: 100},
            { field: 'advanceIn', title: '预收款', align: 'left',width: 100},
            { field: 'beginNeedGet', title: '期初应收', align: 'left',width: 100},
            { field: 'beginNeedPay', title: '期初应付', align: 'left',width: 100},
            { field: 'allNeedGet', title: '累计应收', align: 'left',width: 100},
            { field: 'allNeedPay', title: '累计应付', align: 'left',width: 100},
            { field: 'taxRate', title: '税率(%)', align: 'left',width: 100},
            { field: 'enabled', title: '状态', align: 'center',width: 80, templet: function(d){
                if(d.enabled == '1'){
                    return "<span class='state-up'>启用</span>";
                }else if(d.enabled == '2'){
                    return "<span class='state-down'>禁用</span>";
                }else{
                    return "<span class='state-error'>参数错误</span>";
                }
            }},
            { field: 'createTime', title: '创建时间', align: 'center', width: 140 },
            { title: '操作', fixed: 'right', align: 'center', width: 250, toolbar: '#tableBar'}
        ]]
    });

    table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent == 'edit') { //编辑
            editcustomer(data);
        }else if (layEvent == 'delete') { //删除
            deletecustomer(data);
        }else if (layEvent == 'enabled') { //启用
            editEnabled(data);
        }else if(layEvent == 'unenabled'){ //禁用
            editNotEnabled(data)
        }else if(layEvent == 'select'){ //详情
            selectCustomer(data)
        }else if(layEvent == 'excDocuments'){ //来往单据
            excDocuments(data)
        }
    });

    //搜索表单
    form.render();
    form.on('submit(formSearch)', function (data) {
        //表单验证
        if (winui.verifyForm(data.elem)) {
            loadTable();
        }
        return false;
    });
    
    //来往单据
    function excDocuments(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/customer/customeredit.html",
            title: "来往单据",
            pageId: "customerexcdocuments",
            area: ['90vw', '90vh'],
            callBack: function(refreshCode){
            }});
    }

    //编辑
    function editcustomer(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/customer/customeredit.html",
            title: "编辑",
            pageId: "customeredit",
            area: ['90vw', '90vh'],
            callBack: function(refreshCode){
                if (refreshCode == '0') {
                    winui.window.msg("操作成功", {icon: 1,time: 2000});
                    loadTable();
                } else if (refreshCode == '-9999') {
                    winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
            }});
    }

    //删除客户
    function deletecustomer(data){
        layer.confirm('确认要删除该客户信息吗？', { icon: 3, title: '删除客户' }, function (index) {
            AjaxPostUtil.request({url:reqBasePath + "customer004", params: {rowId: data.id}, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("删除成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }

    //设置启用状态
    function editEnabled(data){
        layer.confirm('确认要更改该客户为启用状态吗？', { icon: 3, title: '设置客户状态' }, function (index) {
            AjaxPostUtil.request({url:reqBasePath + "customer006", params: {rowId: data.id}, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("设置成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }
    
    //设置禁用状态
    function editNotEnabled(data){
        layer.confirm('确认要更改该客户为禁用状态吗？', { icon: 3, title: '设置客户状态' }, function (index) {
            AjaxPostUtil.request({url:reqBasePath + "customer007", params: {rowId: data.id}, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("设置成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }
    
    //详情
    function selectCustomer(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/customer/customerinfo.html",
            title: "详情",
            pageId: "customerinfo",
            area: ['90vw', '90vh'],
            callBack: function(refreshCode){
            }
        });
    }
    
    //添加客户
    $("body").on("click", "#addBean", function(){
        _openNewWindows({
            url: "../../tpl/customer/customeradd.html",
            title: "新增",
            pageId: "customeradd",
            area: ['90vw', '90vh'],
            callBack: function(refreshCode){
                if (refreshCode == '0') {
                    winui.window.msg("操作成功", {icon: 1,time: 2000});
                    loadTable();
                } else if (refreshCode == '-9999') {
                    winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
            }});
    });

    $("body").on("click", "#reloadTable", function() {
        loadTable();
    });

    $("body").on("click", "#formSearch", function () {
        refreshTable();
    })
    //刷新
    function loadTable(){
        table.reload("messageTable", {where:{customerName:$("#customerName").val(),
                telephone: $("#telephone").val(),
                email: $("#email").val(),
                fax: $("#fax").val(),
                enabled: $("#enabled").val()}});
    }

    //搜索
    function refreshTable(){
        table.reload("messageTable", {page: {curr: 1}, where:{customerName:$("#customerName").val(),
                telephone: $("#telephone").val(),
                email: $("#email").val(),
                fax: $("#fax").val(),
                enabled: $("#enabled").val()}})
    }

    exports('customerlist', {});
});
