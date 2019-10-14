
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
		 	}
		});
	    
	});
});