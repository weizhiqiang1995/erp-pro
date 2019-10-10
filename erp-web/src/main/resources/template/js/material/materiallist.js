
var rowId = "";
var firstType = "";

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
        url: reqBasePath + 'material001',
        where: {materialName: $("#materialName").val(), model: $("#model").val(), categoryId: $("#categoryId").val(), categoryIdSec: $("#categoryIdSec").val(), enabled: $("#enabled").val()},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
	    cols: [[
	        { title: '序号', type: 'numbers'},
	        { field: 'name', title: '产品名称', align: 'left', width: 250, templet: function(d){
		        	return '<a lay-event="details" class="notice-title-click">' + d.name + '</a>';
		    }},
	        { field: 'model', title: '型号', align: 'left', width: 200 },
	        { field: 'firstTypeName', title: '一级类型', align: 'left', width: 100 },
	        { field: 'secondTypeName', title: '二级类型', align: 'left', width: 100 },
	        { field: 'enabled', title: '状态', align: 'center', width: 80, templet: function(d){
	        	if(d.enabled == '0'){
	        		return "<span class='state-down'>禁用</span>";
	        	}else if(d.enabled == '1'){
	        		return "<span class='state-up'>启用</span>";
	        	}
	        }},
	        { field: 'createTime', title: '创建时间', align: 'center', width: 150 },
	        { title: '操作', fixed: 'right', align: 'center', width: 260, toolbar: '#tableBar'}
	    ]],
	    done: function(){
	    	if(!loadFirstType){
	    		initFirstType();
	    	}
	    }
	});
	
	table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'edit') { //编辑
        	edit(data);
        }else if (layEvent === 'delet') { //删除
        	delet(data);
        }else if (layEvent === 'up') { //启用
        	up(data);
        }else if (layEvent === 'down') { //禁用
        	down(data);
        }else if (layEvent === 'details') { //详情
        	details(data);
        }
    });
    
    var loadFirstType = false;
	//初始化一级类型
	function initFirstType(){
		loadFirstType = true;
		showGrid({
		 	id: "categoryId",
		 	url: reqBasePath + "materialcategory012",
		 	params: {},
		 	pagination: false,
		 	template: getFileContent('tpl/template/select-option.tpl'),
		 	ajaxSendLoadBefore: function(hdb){},
		 	ajaxSendAfter:function(json){
		 		form.render('select');
		 	}
	    });
	}
	//一级类型监听事件
	form.on('select(categoryId)', function(data){
		firstType = data.value;
		if(isNull(firstType)){
    		$("#categoryIdSec").html("");
    		form.render('select');
    	}else{
	    	showGrid({
			 	id: "categoryIdSec",
			 	url: reqBasePath + "materialcategory013",
			 	params: {parentId: firstType},
			 	pagination: false,
			 	template: getFileContent('tpl/template/select-option.tpl'),
			 	ajaxSendLoadBefore: function(hdb){
			 	},
			 	ajaxSendAfter:function(json){
			 		form.render('select');
			 	}
			});
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
    
    //详情
	function details(data){
		rowId = data.id;
		_openNewWindows({
			url: "../../tpl/material/materialdetails.html", 
			title: "详情",
			pageId: "licencedetails",
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
	
	//添加
	$("body").on("click", "#addBean", function(){
    	_openNewWindows({
			url: "../../tpl/material/materialadd.html", 
			title: "新增",
			pageId: "materialadd",
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
	
	//删除
	function delet(data){
		var msg = '确认删除选中数据吗？';
		layer.confirm(msg, { icon: 3, title: '删除操作' }, function (index) {
			layer.close(index);
            //向服务端发送删除指令
            AjaxPostUtil.request({url:reqBasePath + "material006", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("删除成功", {icon: 1,time: 2000});
    				loadTable();
    			}else{
    				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
    			}
    		}});
		});
	}
	
	//禁用
	function down(data){
		var msg = '确认禁用选中数据吗？';
		layer.confirm(msg, { icon: 3, title: '禁用操作' }, function (index) {
			layer.close(index);
            //向服务端发送删除指令
            AjaxPostUtil.request({url:reqBasePath + "material004", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("禁用成功", {icon: 1,time: 2000});
    				loadTable();
    			}else{
    				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
    			}
    		}});
		});
	}
	
	//启用
	function up(data){
		var msg = '确认启用选中数据吗？';
		layer.confirm(msg, { icon: 3, title: '启用操作' }, function (index) {
			layer.close(index);
            //向服务端发送删除指令
            AjaxPostUtil.request({url:reqBasePath + "material005", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("启用成功", {icon: 1,time: 2000});
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
			url: "../../tpl/material/materialedit.html", 
			title: "编辑",
			pageId: "materialunitedit",
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
	
	//刷新数据
    $("body").on("click", "#reloadTable", function(){
    	loadTable();
    });
    
    function loadTable(){
    	table.reload("messageTable", {where:{materialName: $("#materialName").val(), model: $("#model").val(), categoryId: $("#categoryId").val(), categoryIdSec: $("#categoryIdSec").val(), enabled: $("#enabled").val()}});
    }
    
    function refreshloadTable(){
    	table.reload("messageTable", {page: {curr: 1}, where:{materialName: $("#materialName").val(), model: $("#model").val(), categoryId: $("#categoryId").val(), categoryIdSec: $("#categoryIdSec").val(), enabled: $("#enabled").val()}});
    }
    
    exports('materiallist', {});
});
