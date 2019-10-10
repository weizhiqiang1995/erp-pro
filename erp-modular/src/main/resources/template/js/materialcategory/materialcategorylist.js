var firstType = "";
var rowId = "";
layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'treeGrid', 'jquery', 'winui', 'form'], function (exports) {
	
	winui.renderColor();
	
	var $ = layui.$,
		form = layui.form,
		treeGrid = layui.treeGrid;
	
	authBtn('1568558491168');
	
	//表格渲染
	treeGrid.render({
	    id: 'messageTable',
	    elem: '#messageTable',
	    method: 'post',
	    idField: 'id',
	    url: reqBasePath + 'materialcategory001',
	    cellMinWidth: 100,
	    where: {name: $("#name").val(), parentId:$("#firstType").val()},
	    treeId: 'id',//树形id字段名称
        treeUpId: 'pId',//树形父id字段名称
        treeShowName: 'name',//以树形式显示的字段
	    cols: [[
	        { title: '序号', type: 'numbers'},
	        { field: 'name', title: '名称', align: 'center', width: 120 },
	        { field: 'orderBy', title: '序号', align: 'center', width: 80 },
	        { field: 'status', title: '状态', width: 80, align: 'center', templet: function(d){
	        	if(d.status == '2'){
	        		return "<span class='state-down'>禁用</span>";
	        	}else if(d.status == '1'){
	        		return "<span class='state-up'>启用</span>";
	        	}
	        }},
	        { field: 'remark', title: '备注', align: 'left', width: 300 },
	        { title: '操作', fixed: 'right', align: 'center', width: 257, toolbar: '#tableBar'}
	    ]],
	    isPage:false,
	    done: function(){
	    	if(!loadFirstType){
	    		initFirstType();
	    	}
	    }
	});
	
	treeGrid.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
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
        }else if (layEvent === 'upMove') { //上移
        	upMove(data);
        }else if (layEvent === 'downMove') { //下移
        	downMove(data);
        }
    });
	
	var loadFirstType = false;
	//初始化一级类型
	function initFirstType(){
		loadFirstType = true;
		showGrid({
		 	id: "firstType",
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
	form.on('select(firstType)', function(data){
		firstType = data.value;
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
			url: "../../tpl/materialcategory/materialcategoryadd.html", 
			title: "新增类型",
			pageId: "materialcategoryadd",
			area: ['80vw', '80vh'],
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
		layer.confirm(msg, { icon: 3, title: '删除公告类型' }, function (index) {
			layer.close(index);
            //向服务端发送删除指令
            AjaxPostUtil.request({url:reqBasePath + "materialcategory003", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("删除成功", {icon: 1,time: 2000});
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
		layer.confirm(msg, { icon: 3, title: '启用类型' }, function (index) {
			layer.close(index);
            //向服务端发送上线指令
            AjaxPostUtil.request({url:reqBasePath + "materialcategory004", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("启用成功", {icon: 1,time: 2000});
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
		layer.confirm(msg, { icon: 3, title: '禁用类型' }, function (index) {
			layer.close(index);
            //向服务端发送下线指令
            AjaxPostUtil.request({url:reqBasePath + "materialcategory005", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("禁用成功", {icon: 1,time: 2000});
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
			url: "../../tpl/materialcategory/materialcategoryedit.html", 
			title: "编辑类型",
			pageId: "materialcategoryedit",
			area: ['80vw', '80vh'],
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
	
	//上移
	function upMove(data){
        //向服务端发送上移指令
        AjaxPostUtil.request({url:reqBasePath + "materialcategory008", params:{rowId: data.id}, type:'json', callback:function(json){
			if(json.returnCode == 0){
				winui.window.msg("上移成功", {icon: 1,time: 2000});
				loadTable();
			}else{
				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
			}
		}});
	}
	
	//下移
	function downMove(data){
        //向服务端发送下移指令
        AjaxPostUtil.request({url:reqBasePath + "materialcategory009", params:{rowId: data.id}, type:'json', callback:function(json){
			if(json.returnCode == 0){
				winui.window.msg("下移成功", {icon: 1,time: 2000});
				loadTable();
			}else{
				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
			}
		}});
	}
    
	//刷新数据
    $("body").on("click", "#reloadTable", function(){
    	loadTable();
    });
    
    function loadTable(){
    	treeGrid.query("messageTable", {where:{name: $("#name").val(), parentId: $("#firstType").val()}});
    }
    
    function refreshloadTable(){
    	treeGrid.query("messageTable", {page: {curr: 1}, where:{name: $("#name").val(), parentId: $("#firstType").val()}});
    }
    
    exports('materialcategorylist', {});
});
