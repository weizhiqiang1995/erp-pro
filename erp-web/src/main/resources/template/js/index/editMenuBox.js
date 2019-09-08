layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'jquery', 'winui'], function (exports) {
	winui.renderColor();
	layui.use(['form'], function (form) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    var $ = layui.$,
	    	form = layui.form;
	    
	    AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop006", params:{rowId: parent.parentRowId}, type:'json', callback:function(json){
   			if(json.returnCode == 0){
   				$("#menuBoxName").val(json.bean.menuBoxName);
   				form.on('submit(formEditBean)', function (data) {
   			    	//表单验证
   			        if (winui.verifyForm(data.elem)) {
   			        	var params = {
   		        			menuBoxName: $("#menuBoxName").val(),
   		        			rowId: parent.parentRowId
   			        	};
   			        	
   			        	AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop007", params:params, type:'json', callback:function(json){
   			 	   			if(json.returnCode == 0){
   			 	   				parent.childParams = params;
   				 	   			parent.layer.close(index);
   				 	        	parent.refreshCode = '0';
   			 	   			}else{
   			 	   				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
   			 	   			}
   			 	   		}});
   			        }
   			        return false;
   			    });
   			}else{
   				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
   			}
   		}});
	    
	    //取消
	    $("body").on("click", "#cancle", function(){
	    	parent.layer.close(index);
	    });
	});
	    
});