
layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui'], function (exports) {
	winui.renderColor();
	layui.use(['form'], function (form) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    var $ = layui.$,
	    	form = layui.form;
	    
	    var id = "";
	    if(!isNull(parent.rowId) || !isNull(parent.parentRowId)){
	    	if(!isNull(parent.rowId))
	    		id = parent.rowId;
	    	if(!isNull(parent.parentRowId))
	    		id = parent.parentRowId;
	    	showGrid({
	    		id: "notice-content",
	    		url: reqBasePath + "syseveusernotice007",
	    		params: {rowId: id},
	    		pagination: false,
	    		template: getFileContent('tpl/index/noticeDetailTemplate.tpl'),
	    		ajaxSendLoadBefore: function(hdb){
	    		},
	    		ajaxSendAfter:function(json){
	    		}
	    	});
	    }
	    
		
	});
	    
});