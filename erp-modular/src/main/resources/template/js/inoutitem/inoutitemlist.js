
var rowId = "";
layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui', 'form'], function (exports) {
    winui.renderColor();
    authBtn('1570755543012');
    var $ = layui.$,
        form = layui.form,
        table = layui.table;
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'inoutitem001',
            where: {inoutitemName:$("#inoutitemName").val(),
            inoutitemType: $("#inoutitemType").val(),
            remark: $("#remark").val()},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'inoutitemName', title: '名称', align: 'left',width: 300},
            { field: 'inoutitemType', title: '类型', align: 'center',width: 200, templet: function(d){
                if(d.inoutitemType == '1'){
                    return "<span class='state-up'>收入</span>";
                }else if(d.inoutitemType == '2'){
                    return "<span class='state-down'>支出</span>";
                }else{
                    return "<span class='state-error'>参数错误</span>";
                }
            }},
            { field: 'remark', title: '备注', align: 'center',width: 400},
            { field: 'createTime', title: '创建时间', align: 'center', width: 180 },
            { title: '操作', fixed: 'right', align: 'center', width: 300, toolbar: '#tableBar'}
        ]]
    });

    table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'edit') { //编辑
            editInoutitem(data);
        }else if (layEvent === 'delete') { //删除
            deleteInoutitem(data);
        }else if (layEvent === 'select') {
            selectInoutitem(data);
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
    function editInoutitem(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/inoutitem/inoutitemedit.html",
            title: "编辑收支项目",
            pageId: "inoutitemedit",
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

    //删除收支项目
    function deleteInoutitem(data){
        layer.confirm('确认要删除该收支项目吗？', { icon: 3, title: '删除确认' }, function (index) {
            var params = {
                rowId: data.id,
            };
            AjaxPostUtil.request({url:reqBasePath + "inoutitem004", params:params, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("删除成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }

    function selectInoutitem(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/inoutitem/inoutiteminfo.html",
            title: "查看收支项目详情",
            pageId: "inoutiteminfo",
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

    //添加收支项目
    $("body").on("click", "#addBean", function(){
        _openNewWindows({
            url: "../../tpl/inoutitem/inoutitemadd.html",
            title: "新增收支项目",
            pageId: "inoutitemadd",
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
        table.reload("messageTable", {where:{inoutitemName:$("#inoutitemName").val(),
                inoutitemType: $("#inoutitemType").val(),
                remark: $("#remark").val()}});
    }

    //搜索
    function refreshTable(){
        table.reload("messageTable", {page: {curr: 1}, where:{inoutitemName:$("#inoutitemName").val(),
                inoutitemType: $("#inoutitemType").val(),
                remark: $("#remark").val()}})
    }

    exports('inoutitemlist', {});
});
