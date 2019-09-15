
var rowId = "";
layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui', 'form'], function (exports) {
    winui.renderColor();
    authBtn('1568523956068');
    var $ = layui.$,
        form = layui.form,
        table = layui.table;
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'storehouse001',
        where: {houseName:$.trim($("#houseName").val())},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'houseName', title: '仓库名称', width: 150},
            { field: 'address', title: '仓库地址', width: 300},
            { field: 'warehousing', title: '仓储费', width: 130},
            { field: 'truckage', title: '搬运费', width: 130},
            { field: 'principal', title: '负责人', width: 150},
            { field: 'isDefault', title: '是否默认', width: 100, templet: function(d){
                    if(d.isDefault == '1'){
                        return "是";
                    }else if(d.isDefault == '2'){
                        return "否";
                    }else{
                        return "参数错误";
                    }
                }},
            { field: 'createTime', title: '创建时间', align: 'center', width: 150 },
            { title: '操作', fixed: 'right', align: 'center', width: 200, toolbar: '#tableBar'}
        ]]
    });

    table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'edit') { //编辑
            edit(data);
        }else if (layEvent === 'delete') { //删除
            deleteHouse(data);
        }else if (layEvent === 'default') { //设置默认
            defaultHouse(data);
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
    function edit(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/storehouse/storehouseedit.html",
            title: "编辑用户",
            pageId: "storehouseedit",
            area: ['950px', '90vh'],
            callBack: function(refreshCode){
                if (refreshCode == '0') {
                    winui.window.msg("操作成功", {icon: 1,time: 2000});
                    loadTable();
                } else if (refreshCode == '-9999') {
                    winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
            }});
    }

    //删除仓库
    function deleteHouse(data){
        var params = {
            rowId: data.id,
            deleteFlag: '1'
        };
        AjaxPostUtil.request({url:reqBasePath + "storehouse004", params:params, type:'json', callback:function(json){
            if(json.returnCode == 0){
                winui.window.msg("删除成功。", {icon: 1,time: 2000});
                loadTable();
            }else{
                winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
            }
        }});
    }

    //设置是否默认
    function defaultHouse(data){
        var params = {
            rowId: data.id,
            isDefault: "1",
        };
        AjaxPostUtil.request({url:reqBasePath + "storehouse006", params:params, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("设置成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
    }
    //添加仓库
    $("body").on("click", "#addBean", function(){
        _openNewWindows({
            url: "../../tpl/storehouse/storehouseadd.html",
            title: "新增仓库",
            pageId: "storehouseadd",
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
        table.reload("messageTable", {where:{houseName:$.trim($("#houseName").val())}});
    }

    //搜索
    function refreshTable(){
        table.reload("messageTable", {page: {curr: 1}, where:{houseName:$.trim($("#houseName").val())}})
    }

    exports('storehouselist', {});
});
