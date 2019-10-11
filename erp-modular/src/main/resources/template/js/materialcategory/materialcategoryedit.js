
layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'jquery', 'winui'], function (exports) {
	winui.renderColor();
	layui.use(['form'], function (form) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    var $ = layui.$;
	    
	    showGrid({
		 	id: "showForm",
		 	url: reqBasePath + "materialcategory006",
		 	params: {rowId: parent.rowId},
		 	pagination: false,
		 	template: getFileContent('tpl/materialcategory/materialcategoryeditTemplate.tpl'),
		 	ajaxSendAfter:function(json){
		 		if(json.bean.pId === '二级类型'){	//该类型为二级类型
		 			$("#parentIdBox").removeClass("layui-hide");
 	        	}
				form.render();
		 	    form.on('submit(formEditBean)', function (data) {
		 	    	//表单验证
		 	        if (winui.verifyForm(data.elem)) {
		 	        	var params = {
		 	        		rowId: parent.rowId,
		 	        		name: $("#typeName").val(),
		 	        		remark: $("#remark").val()
		 	        	};
		 	        	AjaxPostUtil.request({url:reqBasePath + "materialcategory007", params:params, type:'json', callback:function(json){
		 	        		if(json.returnCode == 0){
		 	        			parent.layer.close(index);
		 	        			parent.refreshCode = '0';
		 	        		}else{
		 	        			winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
		 	        		}
		 	        	}});
		 	        }
		 	        return false;
		 	    });
		 	}
		});
	    
	    $("body").on("click", "#cancle", function(){
	    	parent.layer.close(index);
	    });
	});
});