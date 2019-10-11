
var rowId = "";
layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'table', 'jquery', 'winui', 'form'], function (exports) {
    winui.renderColor();
    authBtn('1569133228443');
    var $ = layui.$,
        form = layui.form,
        table = layui.table;
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'otherwarehous001',
        where: {defaultNumber: $("#defaultNumber").val(), material: $("#material").val()},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'defaultNumber', title: '单据编号', align: 'left', width: 150},
            { field: 'supplierName', title: '供应商', align: 'left', width: 150},
            { field: 'materialNames', title: '关联产品', align: 'left', width: 300},
            { field: 'totalPrice', title: '合计金额', align: 'left', width: 120},
            { field: 'supplierName', title: '操作人', align: 'left', width: 100},
            { field: 'operTime', title: '单据日期', align: 'center', width: 140 },
            { title: '操作', fixed: 'right', align: 'center', width: 200, toolbar: '#tableBar'}
        ]]
    });

    table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'edit') { //编辑
            editmember(data);
        }else if (layEvent === 'delete') { //删除
            deletemember(data);
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

    //编辑
    function editmember(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/member/memberedit.html",
            title: "编辑",
            pageId: "memberedit",
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

    //删除
    function deletemember(data){
        layer.confirm('确认要删除信息吗？', { icon: 3, title: '删除操作' }, function (index) {
            var params = {
                rowId: data.id
            };
            AjaxPostUtil.request({url:reqBasePath + "member004", params:params, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("删除成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }

    //添加
    $("body").on("click", "#addBean", function(){
        _openNewWindows({
            url: "../../tpl/otherwarehous/otherwarehousadd.html",
            title: "新增",
            pageId: "otherwarehousadd",
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
        table.reload("messageTable", {where:{defaultNumber: $("#defaultNumber").val(), material: $("#material").val()}});
    }

    //搜索
    function refreshTable(){
        table.reload("messageTable", {page: {curr: 1}, where:{defaultNumber: $("#defaultNumber").val(), material: $("#material").val()}})
    }

    exports('memberlist', {});
});
