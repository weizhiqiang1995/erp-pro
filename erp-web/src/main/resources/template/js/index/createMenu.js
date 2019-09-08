layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'jquery', 'winui', 'colorpicker', 'fileUpload'], function (exports) {
	winui.renderColor();
	layui.use(['form'], function (form) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    var $ = layui.$,
	    	form = layui.form;
	    var colorpicker = layui.colorpicker;
	    
	    colorpicker.render({
 		    elem: '#menuIconBg',
 		    color: '#1c97f5',
 		    done: function(color){
 		        $('#menuIconBginput').val(color);
 		        $("#iconShow").parent().css({'background-color': color});
 		    },
 		    change: function(color){
 		    	$("#iconShow").parent().css({'background-color': color});
 		    }
 		});
 		
 		colorpicker.render({
 		    elem: '#menuIconColor',
 		    color: '#1c97f5',
 		    done: function(color){
 		        $('#menuIconColorinput').val(color);
 		        $("#iconShow").css({'color': color});
 		    },
 		    change: function(color){
 		    	$("#iconShow").css({'color': color});
 		    }
 		});
 		
 		$("#menuIconPic").upload({
            "action": reqBasePath + "common003",
            "data-num": "1",
            "data-type": "PNG,JPG,jpeg,gif",
            "uploadType": 12,
            "function": function (_this, data) {
                show("#menuIconPic", data);
            }
        });
 		
 		form.render();
 		
 		//菜单图标类型变化事件
 		form.on('radio(menuIconType)', function (data) {
 			var val = data.value;
	    	if(val == '1'){//icon
	    		$(".menuIconTypeIsTwo").addClass("layui-hide");
	    		$(".menuIconTypeIsOne").removeClass("layui-hide");
	    	}else if(val == '2'){//图片
	    		$(".menuIconTypeIsTwo").removeClass("layui-hide");
	    		$(".menuIconTypeIsOne").addClass("layui-hide");
	    	}else{
	    		winui.window.msg('状态值错误', {icon: 2,time: 2000});
	    	}
        });
	    
	    form.on('submit(formAddBean)', function (data) {
	    	//表单验证
	        if (winui.verifyForm(data.elem)) {
	        	var params = {
        			menuName: $("#menuName").val(),
        			titleName: $("#menuTitle").val(),
        			menuIcon: $("#menuIcon").val(),
        			menuUrl: $("#menuUrl").val(),
        			menuIconType: data.field.menuIconType,
        			menuIconBg: $('#menuIconBginput').val(),
        			menuIconColor: $('#menuIconColorinput').val(),
 	        	};
	        	
	        	if(data.field.menuIconType == '1'){
 	        		if(isNull($("#menuIcon").val())){
 	        			winui.window.msg("请选择菜单图标", {icon: 2,time: 2000});
 	 	        		return false;
 	        		}
 	        		params.menuIconPic = '';
 	        	}else if(data.field.menuIconType == '2'){
 	        		params.menuIconPic = $("#menuIconPic").find("input[type='hidden'][name='upload']").attr("oldurl");
 	 	        	if(isNull(params.menuIconPic)){
 	 	        		winui.window.msg('请上传菜单logo', {icon: 2,time: 2000});
 	 	        		return false;
 	 	        	}
 	 	        	params.menuIcon = '';
 	 	        	params.menuIconBg = '';
 	 	        	params.menuIconColor = '';
 	        	}else{
 	        		winui.window.msg("状态值错误。", {icon: 2,time: 2000});
 	        		return false;
 	        	}
	        	
	        	AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop002", params:params, type:'json', callback:function(json){
	 	   			if(json.returnCode == 0){
	 	   				parent.childParams = json.bean;
		 	   			parent.layer.close(index);
		 	        	parent.refreshCode = '0';
	 	   			}else{
	 	   				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
	 	   			}
	 	   		}});
	        }
	        return false;
	    });
	    
	    //菜单图标选中事件
 	    $("body").on("focus", "#menuIcon", function(e){
 	    	_openNewWindows({
 				url: "../../tpl/sysevemenu/icon.html", 
 				title: "选择ICON图标",
 				pageId: "icon",
 				area: ['640px', '360px'],
 				callBack: function(refreshCode){
 	                if (refreshCode == '0') {
 	                	$("#menuIcon").val(childIcon);
 	                	$("#iconShow").css({'color': 'white'});
 	                	$("#iconShow").attr("class", "fa fa-fw " + $("#menuIcon").val());
 	                } else if (refreshCode == '-9999') {
 	                	winui.window.msg("操作失败", {icon: 2,time: 2000});
 	                }
 				}});
 	    });
	    
	    //取消
	    $("body").on("click", "#cancle", function(){
	    	parent.layer.close(index);
	    });
	});
	    
});