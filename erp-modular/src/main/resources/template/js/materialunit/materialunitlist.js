
var rowId = "";

layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'table', 'jquery', 'winui', 'form'], function (exports) {
	
	winui.renderColor();
	
	var $ = layui.$,
		form = layui.form,
		table = layui.table;
	
	authBtn('1569160351079');
	
	//表格渲染
	table.render({
	    id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'materialunit001',
        where: {groupName: $("#groupName").val()},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
	    cols: [[
	        { title: '序号', type: 'numbers'},
	        { field: 'groupName', title: '组名', align: 'left', width: 120 },
	        { field: 'unitName', title: '计量单位', align: 'left', width: 300 },
	        { title: '操作', fixed: 'right', align: 'center', width: 257, toolbar: '#tableBar'}
	    ]],
	    done: function(){
	    }
	});
	
	table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'edit') { //编辑
        	edit(data);
        }else if (layEvent === 'delet') { //删除
        	delet(data);
        }
    });
	
	form.render();
	
	//搜索表单
	form.on('submit(formSearch)', function (data) {
        //表单验证
        if (winui.verifyForm(data.elem)) {
        	refreshloadTable();
        }
        return false;
    });
	
	//添加
	$("body").on("click", "#addBean", function(){
    	_openNewWindows({
			url: "../../tpl/materialunit/materialunitadd.html", 
			title: "新增",
			pageId: "materialunitadd",
			area: ['70vw', '70vh'],
			callBack: function(refreshCode){
                if (refreshCode == '0') {
                	winui.window.msg("操作成功", {icon: 1,time: 2000});
                	loadTable();
                } else if (refreshCode == '-9999') {
                	winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
			}});
    });
	
	//删除
	function delet(data){
		var msg = '确认删除选中数据吗？';
		layer.confirm(msg, { icon: 3, title: '删除计量单位' }, function (index) {
			layer.close(index);
            //向服务端发送删除指令
            AjaxPostUtil.request({url:reqBasePath + "materialunit003", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("删除成功", {icon: 1,time: 2000});
    				loadTable();
    			}else{
    				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
    			}
    		}});
		});
	}
	
	//编辑
	function edit(data){
		rowId = data.id;
		_openNewWindows({
			url: "../../tpl/materialunit/materialunitedit.html", 
			title: "编辑",
			pageId: "materialunitedit",
			area: ['70vw', '70vh'],
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
	
	//刷新数据
    $("body").on("click", "#reloadTable", function(){
    	loadTable();
    });
    
    function loadTable(){
    	table.reload("messageTable", {where:{groupName: $("#groupName").val()}});
    }
    
    function refreshloadTable(){
    	table.reload("messageTable", {page: {curr: 1}, where:{groupName: $("#groupName").val()}});
    }
    
    exports('materialunitlist', {});
});
