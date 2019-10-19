
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
		 		var str = '无';
		 		var defaultNumber = json.bean.defaultNumber;
		        if(!isNull(json.bean.linkNumber)){
		        	str = '<a lay-event="details" class="notice-title-click">' + json.bean.linkNumber + '</a>';
		        	defaultNumber += '<span class="state-new">[转]</span>';
			        if(json.bean.status == 2){
			        	defaultNumber += '<span class="state-up"> [正常]</span>';
			        }else{
			        	defaultNumber += '<span class="state-down"> [预警]</span>';
			        }
		        }
		        $("#linkNumber").html(str);
		        $("#defaultNumber").html(defaultNumber);
		 		form.render();
		 	}
		});
	    
	});
});