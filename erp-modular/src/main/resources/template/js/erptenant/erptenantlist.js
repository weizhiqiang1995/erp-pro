
var clickId = "";//选中的租户组id
var name = ""; //租户组名
var userList = new Array();//选择租户返回的集合或者进行回显的集合
var userReturnList = new Array();//选择租户返回的集合或者进行回显的集合

layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'table', 'jquery', 'winui', 'form', 'contextMenu'], function (exports) {
	
	winui.renderColor();
	
	var $ = layui.$,
		form = layui.form,
		table = layui.table;
	
	authBtn('1572399592345');//新增租户
	authBtn('1572399563911');//新增租户组
	authBtn('1572399742234');//一键移除租户
	
	var userInfo = "";	//租户id
	showLeft();
	
	//初始化左侧菜单租户组数据
	function showLeft(){
		var userGroupTemplate = $('#userGroupTemplate').html();
	    AjaxPostUtil.request({url:reqBasePath + "erptenant002", params: {}, type:'json', callback:function(json){
			if(json.returnCode == 0){
				var str = getDataUseHandlebars(userGroupTemplate, json);
				$("#setting").html(str);
				if(json.rows.length > 0){
					clickId = json.rows[0].id;
					$("#setting").find("a[rowid='" + clickId + "']").addClass('selected');
				}
	 			showList();//展示租户组对应的租户列表
		 	    initRightMenu();//初始化右键
			}else{
				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
			}
		}});
	}
	
	//初始化右键
	function initRightMenu(){
		var arrayObj = new Array();//创建一个数组
		if(auth('1572399662664')){
			arrayObj.push({
				text: "删除",
				img: "../../assets/images/delete-icon.png",
				callback: function() {
					deleteUserGroup();
				}
			})
		}
		if(auth('1572399633886')){
			arrayObj.push({
				text: "重命名",
				img: "../../assets/images/rename-icon.png",
				callback: function() {
					var obj = $("#setting");
					var html = obj.find("a[rowid='" + clickId + "']").html();
					var newhtml = "<input value='" + html + "' class='layui-input setting-a-input' style='margin-top: 5px;'/>";
					obj.find("a[rowid='" + clickId + "']").html(newhtml);
				    obj.find("input").select();
			    	obj.find("input").blur(function(){
			    		var value = obj.find("input").val();
			    		if(!isNull(value)){
			    			if(html != value){
				    			AjaxPostUtil.request({url:reqBasePath + "erptenant004", params: {rowId: clickId, groupName: value}, type:'json', callback:function(json){
				    	   			if(json.returnCode == 0){
				    	   				obj.find("a[rowid='" + clickId + "']").html(value);
				    	   			}else{
				    					winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
				    				}
				    	   		}});
			    			}else{
			    				obj.find("a[rowid='" + clickId + "']").html(html);
			    			}
			    		}else{
							obj.find("a[rowid='" + clickId + "']").html(html);
						}
			    	});
				}
			})
		}
		if(arrayObj.length > 0){
			$("#setting").contextMenu({
				width: 190, // width
				itemHeight: 30, // 菜单项height
				bgColor: "#FFFFFF", // 背景颜色
				color: "#0A0A0A", // 字体颜色
				fontSize: 12, // 字体大小
				hoverBgColor: "#99CC66", // hover背景颜色
				target: function(ele) { // 当前元素
				},
				rightClass: 'setting-a,setting-a selected',
				menu: arrayObj
			});
		}
    };
 
	//删除租户组
	function deleteUserGroup(){
		var msg = '确认删除该租户组吗？';
		layer.confirm(msg, { icon: 3, title: '删除租户组' }, function (index) {
			layer.close(index);
			//向服务端发送删除指令
	        AjaxPostUtil.request({url:reqBasePath + "erptenant005", params:{rowId: clickId}, type:'json', callback:function(json){
				if(json.returnCode == 0){
					winui.window.msg("删除成功", {icon: 1,time: 2000});
					$("#setting").find("a[rowid='" + clickId + "']").remove();
					var _obj = $("#setting").find("a[class='setting-a']");
					if(_obj.length > 0){
						_obj.eq(0).click();
					}else{
						clickId = "";
						loadTable();
					}
				}else{
					winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
				}
			}});
		});
	};
	
	//租户组点击事件
	$("body").on("click", ".setting-a-input", function(e){
		e.stopPropagation();//阻止冒泡
	});
	$("body").on("contextmenu", ".setting-a-input", function(e){
		e.stopPropagation();//阻止冒泡
	});
	
	//租户组名右键效果
	$("body").on("contextmenu", "#setting a", function(e){
		clickId = $(this).attr("rowid");
		name = $(this).attr("rowname");
		$("#setting").find("a").removeClass("selected");
		$("#setting").find("a[rowid='" + clickId + "']").addClass("selected");
		clickId = $(this).attr("rowid");
		loadTable();
	});
	
	//新增租户组
	$("body").on("click", "#addBean", function(e){
		var obj = $("#setting");
		var newhtml = "<input value='新增租户组' class='layui-input setting-a-input' style='margin-top: 5px;'/>";
	    obj.append(newhtml);
	    obj.find("input").select();
    	obj.find("input").blur(function(){
    		var value = obj.find("input").val();
    		obj.find("input").remove();
    		if(isNull(value)){
    			value = '新增租户组';
    		}
			AjaxPostUtil.request({url:reqBasePath + "erptenant001", params: {groupName: value}, type:'json', callback:function(json){
	   			if(json.returnCode == 0){
	   				clickId = json.bean.id;
	   				var str = '<a rowid="' + clickId + '" rowname="' + value + '" class="setting-a">' + value + '</a>';
	   				$("#setting").append(str);
	   				$("#setting").find("a[rowid='" + clickId + "']").click();
	   			}else{
					winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
				}
	   		}});
    	});
	});
	
    //对左侧菜单项的点击事件
	$("body").on("click", "#setting a", function(e){
		$(".setting a").removeClass("selected");
		$(this).addClass("selected");
		clickId = $(this).attr("rowid");
		loadTable();
	});
	
	//展示租户组对应的租户列表
	function showList(){
		//表格渲染
		table.render({
		    id: 'messageTable',
		    elem: '#messageTable',
		    method: 'post',
		    url: reqBasePath + 'erptenant007',
		    where: {groupId: clickId, userName: $("#userName").val()},
		    even: true,  //隔行变色
		    page: true,
		    limits: [8, 16, 24, 32, 40, 48, 56],
		    limit: 8,
		    cols: [[
		        { title: '序号', type: 'numbers'},
		        { field: 'userName', title: '租户姓名', align: 'left', width: 100},
		        { field: 'phone', title: '手机号', align: 'center', width: 120 },
		        { field: 'email', title: '邮箱', align: 'left', width: 200 },
		        { title: '操作', fixed: 'right', align: 'center', width: 150, toolbar: '#tableBar'}
		    ]]
		});
    }
	
	//新增租户
	$("body").on("click", "#addUser", function(e){
		userReturnList = [].concat(userList);
		_openNewWindows({
			url: "../../tpl/common/sysusersel.html", 
			title: "人员选择",
			pageId: "sysuserselpage",
			area: ['80vw', '80vh'],
			callBack: function(refreshCode){
				if (refreshCode == '0') {
					userList = [].concat(userReturnList);
					$.each(userList, function (i, item) {
						userInfo += item.id + ',';
 	                })
					AjaxPostUtil.request({url:reqBasePath + "erptenant003", params:{rowId: clickId, userId: userInfo}, type:'json', callback:function(json){
	    				if(json.returnCode == 0){
	    					userList = [];
	    					userReturnList = [];
	    					userInfo = "";
	    					loadTable();
	    				}else{
	    					winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
	    				}
	    			}});
                } else if (refreshCode == '-9999') {
                	winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
			}});
	});
	
	//一键移除指定租户组下的所有租户
	$("body").on("click", "#delUser", function(e){
		if(!isNull(clickId)){
			var msg = '确认一键移除该租户组下的所有租户吗？';
			layer.confirm(msg, { icon: 3, title: '一键移除所有租户' }, function (index) {
				layer.close(index);
				//向服务端发送删除指令
				AjaxPostUtil.request({url:reqBasePath + "erptenant008", params:{rowId: clickId}, type:'json', callback:function(json){
					if(json.returnCode == 0){
						winui.window.msg("移除成功", {icon: 1,time: 2000});
	    				loadTable();
					}else{
						winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
					}
				}});
			});
		}else{
			winui.window.msg("没有可移除租户的租户组！", {icon: 2,time: 2000});
		}
	});
	
	//监听操作栏事件
	table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'del'){  //移除
        	del(data);
        }
    });
	
	//移除租户
	function del(data){
		var msg = '确认移除该租户吗？';
		layer.confirm(msg, { icon: 3, title: '删除租户' }, function (index) {
			layer.close(index);
            //向服务端发送删除指令
			var params = {
				rowId:  data.id
        	};
            AjaxPostUtil.request({url:reqBasePath + "erptenant006", params:params, type:'json', callback:function(json){
    			if(json.returnCode == 0){
    				winui.window.msg("删除成功", {icon: 1,time: 2000});
    				loadTable();
    			}else{
    				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
    			}
    		}});
		});
	}
	
	//同步人员数据
    $("body").on("click", "#syncData", function(){
    	AjaxPostUtil.request({url:reqBasePath + "activitimode015", params:{}, type:'json', callback:function(json){
			if(json.returnCode == 0){
            	winui.window.msg("同步成功", {icon: 1,time: 2000});
			}else{
				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
			}
		}});
    });
	
	form.render();
	//搜索租户
	$("body").on("click", "#formSearch", function(){
		refreshTable();
	});
	//搜索条件
    function loadTable(){
    	table.reload("messageTable", {where:{groupId: clickId, userName: $("#userName").val()}});
    }
    function refreshTable(){
    	table.reload("messageTable", {page: {curr: 1}, where:{groupId: clickId, userName: $("#userName").val()}});
    }
  	//刷新租户
	$("body").on("click", "#reloadTable", function(){
		loadTable();
	});
	//刷新租户组
	$("body").on("click", "#flashGroup", function(){
		showLeft();
	});
	exports('erptenantlist', {});
});
