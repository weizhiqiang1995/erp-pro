
var rowId = "";
layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui', 'form'], function (exports) {
    winui.renderColor();
    authBtn('1569133219314');
    var $ = layui.$,
        form = layui.form,
        table = layui.table;
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'member001',
        where: {memberName:$("#memberName").val()},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'memberName', title: '会员名称', width: 200},
            { field: 'contacts', title: '联系人', width: 150},
            { field: 'phonenum', title: '联系电话', width: 150},
            { field: 'email', title: '电子邮箱', width: 150},
            { field: 'telephone', title: '手机号码', width: 150},
            { field: 'fax', title: '传真', width: 150},
            { field: 'advanceIn', title: '预收款', width: 150},
            { field: 'beginNeedGet', title: '期初应收', width: 150},
            { field: 'beginNeedPay', title: '期初应付', width: 150},
            { field: 'allNeedGet', title: '累计应收', width: 150},
            { field: 'allNeedPay', title: '累计应付', width: 150},
            { field: 'taxRate', title: '税率(%)', width: 150},
            { field: 'enabled', title: '状态', width: 100, templet: function(d){
                    if(d.enabled == '1'){
                        return "启用";
                    }else if(d.enabled == '2'){
                        return "禁用";
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
            editmember(data);
        }else if (layEvent === 'delete') { //删除
            deletemember(data);
        }else if (layEvent === 'enabled') { //设置状态
            editEnabled(data);
        }else if(layEvent == 'unenabled'){
            editNotEnabled(data)
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
            title: "编辑会员",
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

    //删除会员
    function deletemember(data){
        layer.confirm('确认要删除该会员信息吗？', { icon: 3, title: '删除会员' }, function (index) {
            var params = {
                rowId: data.id,
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

    //设置启用状态
    function editEnabled(data){
        layer.confirm('确认要更改该会员为启用状态吗？', { icon: 3, title: '设置会员状态' }, function (index) {
            var params = {
                rowId: data.id,
            };
            AjaxPostUtil.request({url:reqBasePath + "member006", params:params, type:'json', callback:function(json){
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
        layer.confirm('确认要更改该会员为禁用状态吗？', { icon: 3, title: '设置会员状态' }, function (index) {
            var params = {
                rowId: data.id,
            };
            AjaxPostUtil.request({url:reqBasePath + "member007", params:params, type:'json', callback:function(json){
                    if(json.returnCode == 0){
                        winui.window.msg("设置成功。", {icon: 1,time: 2000});
                        loadTable();
                    }else{
                        winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                    }
                }});
        });
    }
    //添加会员
    $("body").on("click", "#addBean", function(){
        _openNewWindows({
            url: "../../tpl/member/memberadd.html",
            title: "新增会员",
            pageId: "memberadd",
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
        table.reload("messageTable", {where:{memberName:$("#memberName").val()}});
    }

    //搜索
    function refreshTable(){
        table.reload("messageTable", {page: {curr: 1}, where:{memberName:$("#memberName").val()}})
    }

    exports('memberlist', {});
});
