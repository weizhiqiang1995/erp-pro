
layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'table', 'jquery', 'winui'], function (exports) {
	winui.renderColor();
	layui.use(['form'], function (form) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    var $ = layui.$;
	    
	    var beanTemplate = $("#beanTemplate").html();
	    
	    showGrid({
		 	id: "showForm",
		 	url: reqBasePath + "erpcommon001",
		 	params: {rowId: parent.rowId},
		 	pagination: false,
		 	template: beanTemplate,
		 	ajaxSendAfter:function(json){
		 		if(json.bean.status == 0){
		 			$("#statusName").html("<span class='state-down'>未审核</span>");
		 		}else if(json.bean.status == 1){
		 			$("#statusName").html("<span class='state-up'>审核中</span>");
		 		}else if(json.bean.status == 2){
		 			$("#statusName").html("<span class='state-new'>审核通过</span>");
		 		}else if(json.bean.status == 3){
		 			$("#statusName").html("<span class='state-down'>拒绝通过</span>");
		 		}else if(json.bean.status == 4){
		 			$("#statusName").html("<span class='state-new'>已转采购</span>");
		 		}
		 		form.render();
		 		form.on('submit(formAddBean)', function (data) {
			    	//表单验证
			        if (winui.verifyForm(data.elem)) {
			        	var msg = '确认提交吗？';
			    		layer.confirm(msg, { icon: 3, title: '提交审批' }, function (i) {
			    			layer.close(i);
			    			var jStr = {
				    			content: $("#opinion").val(),
				    			status: $("input[name='flag']:checked").val(),
				    			rowId: parent.rowId
				            };
				            AjaxPostUtil.request({url:reqBasePath + "purchaseorder007", params: jStr, type:'json', callback:function(json){
				 	   			if(json.returnCode == 0){
			                    	parent.layer.close(index);
			                    	parent.refreshCode = '0';
				 	   			}else{
				 	   				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
				 	   			}
				 	   		}});
			    		});
			        }
			        return false;
			    });
		 	}
		});
		
		//取消
	    $("body").on("click", "#cancle", function(){
	    	parent.layer.close(index);
	    });
	    
	});
});