
var rowId = "";

layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui', 'form'], function (exports) {
	winui.renderColor();
	
	var $ = layui.$,
		form = layui.form,
		table = layui.table;
	
	authBtn('1552961221651');
	
	//搜索表单
	form.render();
	form.on('submit(formSearch)', function (data) {
    	//表单验证
        if (winui.verifyForm(data.elem)) {
        	loadTable();
        }
        return false;
	});
	
	//表格渲染
	table.render({
	    id: 'messageTable',
	    elem: '#messageTable',
	    method: 'post',
	    url: reqBasePath + 'sysevewin001',
	    where:{sysName: $("#sysName").val()},
	    even:true,  //隔行变色
	    page: true,
	    limits: [8, 16, 24, 32, 40, 48, 56],
	    limit: 8,
	    cols: [[
	        { title: '序号', type: 'numbers'},
	        { field: 'sysName', title: '系统名称', width: 180 },
	        { field: 'sysPic', title: '系统图片', align: 'center', width: 180, templet: function(d){
	        	if(isNull(d.sysPic)){
	        		return '<img src="../../assets/images/os_windows.png" class="photo-img">';
	        	}else{
	        		return '<img src="' + fileBasePath + d.sysPic + '" class="photo-img" lay-event="sysPic">';
	        	}
	        }},
	        { field: 'sysUrl', title: '系统地址', width: 240 },
	        { field: 'firstTypeName', title: '一级分类', width: 120 },
	        { field: 'secondTypeName', title: '二级分类', width: 120 },
	        { field: 'menuNum', title: '菜单数量', width: 120 },
	        { field: 'useNum', title: '客户数量', width: 120 },
	        { field: 'createTime', title: '创建时间', width: 170 },
	        { title: '操作', fixed: 'right', align: 'center', width: 240, toolbar: '#tableBar'}
	    ]]
	});
	
	table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') { //删除
        	del(data, obj);
        }else if (layEvent === 'edit') { //编辑
        	edit(data);
        }else if (layEvent === 'authorization'){//授权
        	authorization(data, obj);
        }else if (layEvent === 'cancleauthorization'){//取消授权
        	cancleauthorization(data, obj);
        }else if (layEvent === 'sysPic'){
        	layer.open({
        		type:1,
        		title:false,
        		closeBtn:0,
        		skin: 'demo-class',
        		shadeClose:true,
        		content:'<img src="' + fileBasePath + data.sysPic + '" style="max-height:600px;max-width:100%;">',
        		scrollbar:false
            });
        }
    });
	
	//删除
	function del(data, obj){
		var msg = obj ? '确认删除系统【' + obj.data.sysName + '】吗？' : '确认删除选中数据吗？';
		layer.confirm(msg, { icon: 3, title: '删除系统' }, function (index) {
			layer.close(index);
            //向服务端发送删除指令
            AjaxPostUtil.request({url:reqBasePath + "sysevewin005", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("删除成功", {icon: 1,time: 2000});
    				loadTable();
    			}else{
    				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
    			}
    		}});
		});
	}
	
	//授权
	function authorization(data, obj){
		var msg = '确认授权于该商户吗？';
		layer.confirm(msg, { icon: 3, title: '系统授权' }, function (index) {
			layer.close(index);
            AjaxPostUtil.request({url:reqBasePath + "sysevewin006", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("授权成功", {icon: 1,time: 2000});
    				loadTable();
    			}else{
    				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
    			}
    		}});
		});
	}
	
	//取消授权
	function cancleauthorization(data, obj){
		var msg = '确认取消该商户的授权吗？';
		layer.confirm(msg, { icon: 3, title: '取消授权' }, function (index) {
			layer.close(index);
            AjaxPostUtil.request({url:reqBasePath + "sysevewin007", params:{rowId: data.id}, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("取消授权成功", {icon: 1,time: 2000});
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
			url: "../../tpl/sysevewin/sysevewinedit.html", 
			title: "编辑系统",
			pageId: "sysevewinedit",
			area: ['650px', '90vh'],
			callBack: function(refreshCode){
                if (refreshCode == '0') {
                	winui.window.msg("操作成功", {icon: 1,time: 2000});
                	loadTable();
                } else if (refreshCode == '-9999') {
                	winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
			}});
	}
	
	//新增系统
	$("body").on("click", "#addBean", function(){
		_openNewWindows({
			url: "../../tpl/sysevewin/sysevewinadd.html", 
			title: "新增系统",
			pageId: "sysevewinadd",
			area: ['650px', '90vh'],
			callBack: function(refreshCode){
                if (refreshCode == '0') {
                	winui.window.msg("操作成功", {icon: 1,time: 2000});
                	loadTable();
                } else if (refreshCode == '-9999') {
                	winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
			}});
    });
	
	//刷新数据
    $("body").on("click", "#reloadTable", function(){
    	loadTable();
    });
    
    function loadTable(){
    	table.reload("messageTable", {where:{sysName: $("#sysName").val()}});
    }
    
    exports('sysevewinlist', {});
});
