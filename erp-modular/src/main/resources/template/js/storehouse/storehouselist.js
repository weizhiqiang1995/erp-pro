
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
        where: {houseName:$("#houseName").val()},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'houseName', title: '仓库名称', align: 'left',width: 200},
            { field: 'address', title: '仓库地址', align: 'left',width: 300},
            { field: 'warehousing', title: '仓储费', align: 'left',width: 100},
            { field: 'truckage', title: '搬运费', align: 'left',width: 100},
            { field: 'principal', title: '负责人', align: 'left',width: 150},
            { field: 'isDefault', title: '是否默认', align: 'center',width: 100, templet: function(d){
                if(d.isDefault == '1'){
                    return "<span class='state-up'>是</span>";
                }else if(d.isDefault == '2'){
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
            deleteHouse(data);
        }else if (layEvent === 'default') { //设置默认
            defaultHouse(data);
        }else if (layEvent === 'select') {
            selectHouse(data);
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
            url: "../../tpl/storehouse/storehouseedit.html",
            title: "编辑仓库",
            pageId: "storehouseedit",
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

    //删除仓库
    function deleteHouse(data){
        layer.confirm('确认删除该仓库吗？', { icon: 3, title: '删除仓库' }, function (index) {
            var params = {
                rowId: data.id,
            };
            AjaxPostUtil.request({url:reqBasePath + "storehouse004", params:params, type:'json', callback:function(json){
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
    function defaultHouse(data){
        layer.confirm('确认要设置该仓库为默认状态吗？', { icon: 3, title: '设置仓库状态' }, function (index) {
            var params = {
                rowId: data.id,
            };
            AjaxPostUtil.request({url:reqBasePath + "storehouse006", params:params, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("设置成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }
    function selectHouse(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/storehouse/storehouseinfo.html",
            title: "查看仓库详情",
            pageId: "storehouseinfo",
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

    //刷新
    function loadTable(){
        table.reload("messageTable", {where:{houseName:$("#houseName").val()}});
    }

    //搜索
    function refreshTable(){
        table.reload("messageTable", {page: {curr: 1}, where:{houseName:$("#houseName").val()}})
    }

    exports('storehouselist', {});
});
