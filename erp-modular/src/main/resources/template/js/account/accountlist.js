
var rowId = "";
layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui', 'form'], function (exports) {
    winui.renderColor();
    authBtn('1570756544366');
    var $ = layui.$,
        form = layui.form,
        table = layui.table;
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'account001',
        where: {accountName:$("#accountName").val(), serialNo: $("#serialNo").val(), remark: $("#remark").val()},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'accountName', title: '名称', align: 'left',width: 200,templet: function(d){
                return '<a lay-event="select" class="notice-title-click">' + d.accountName + '</a>';
            }},
            { field: 'serialNo', title: '编号', align: 'center',width: 150},
            { field: 'initialAmount', title: '期初金额', align: 'left',width: 100},
            { field: 'currentAmount', title: '当前余额', align: 'left',width: 100},
            { field: 'remark', title: '备注', align: 'left',width: 200},
            { field: 'isDefault', title: '是否默认', align: 'center',width: 100, templet: function(d){
                    if(d.isDefault == '1'){
                        return "<span class='state-up'>是</span>";
                    }else if(d.isDefault == '0'){
                        return "<span class='state-down'>否</span>";
                    }else{
                        return "<span class='state-error'>参数错误</span>";
                    }
                }},
            { field: 'createTime', title: '创建时间', align: 'center', width: 180 },
            { title: '操作', fixed: 'right', align: 'center', width: 300, toolbar: '#tableBar'}
        ]]
    });

    table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'edit') { //编辑
            edit(data);
        }else if (layEvent === 'delete') { //删除
            deleteAccount(data);
        }else if (layEvent === 'default') { //设置默认
            defaultAccount(data);
        }else if (layEvent === 'select'){//查看详情
            selectAccount(data);
        }else if (layEvent === 'item'){
            selectItem(data);
        }
    });

    //搜索表单
    form.render();
    form.on('submit(formSearch)', function (data) {
        //表单验证
        if (winui.verifyForm(data.elem)) {
            refreshTable();
        }
        return false;
    });

    //编辑
    function edit(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/account/accountedit.html",
            title: "编辑结算账户",
            pageId: "accountedit",
            area: ['90vw', '90vh'],
            callBack: function(refreshCode){
                if (refreshCode == '0') {
                    winui.window.msg("操作成功", {icon: 1,time: 2000});
                    loadTable();
                } else if (refreshCode == '-9999') {
                    winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
            }
        });
    }

    //删除结算账户
    function deleteAccount(data){
        layer.confirm('确认删除该结算账户吗？', { icon: 3, title: '删除结算账户' }, function (index) {
            var params = {
                rowId: data.id,
            };
            AjaxPostUtil.request({url:reqBasePath + "account004", params:params, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("删除成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }

    //设置是否默认
    function defaultAccount(data){
        layer.confirm('确认要设置该结算账户为默认状态吗？', { icon: 3, title: '设置结算账户状态' }, function (index) {
            var params = {
                rowId: data.id,
            };
            AjaxPostUtil.request({url:reqBasePath + "account006", params:params, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("设置成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }

    function selectAccount(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/account/accountinfo.html",
            title: "查看结算账户详情",
            pageId: "accountinfo",
            area: ['60vw', '60vh'],
            callBack: function(refreshCode){
                if (refreshCode == '0') {
                    winui.window.msg("操作成功", {icon: 1,time: 2000});
                    loadTable();
                } else if (refreshCode == '-9999') {
                    winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
            }
        });
    }

    //添加结算账户
    $("body").on("click", "#addBean", function(){
        _openNewWindows({
            url: "../../tpl/account/accountadd.html",
            title: "新增结算账户",
            pageId: "accountadd",
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

    //查看流水
    function selectItem(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/account/accountitem.html",
            title: "结算账户流水",
            pageId: "accountitem",
            area: ['90vw', '90vh'],
            callBack: function(refreshCode){
                if (refreshCode == '0') {
                    winui.window.msg("操作成功", {icon: 1,time: 2000});
                    loadTable();
                } else if (refreshCode == '-9999') {
                    winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
            }
        });
    }
    $("body").on("click", "#reloadTable", function() {
        loadTable();
    });

    //刷新
    function loadTable(){
        table.reload("messageTable", {where:{accountName:$("#accountName").val(), serialNo: $("#serialNo").val(), remark: $("#remark").val()}});
    }

    //搜索
    function refreshTable(){
        table.reload("messageTable", {page: {curr: 1}, where:{accountName:$("#accountName").val(), serialNo: $("#serialNo").val(), remark: $("#remark").val()}})
    }

    exports('accountlist', {});
});
