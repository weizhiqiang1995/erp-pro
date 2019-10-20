
var mUnitId = "";

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
	    
	    var simpleTemplate = $("#simpleTemplate").html();
	    var manyTemplate = $("#manyTemplate").html();
	    
	    showGrid({
		 	id: "showForm",
		 	url: reqBasePath + "material007",
		 	params: {rowId: parent.rowId},
		 	pagination: false,
		 	template: getFileContent('tpl/material/materialdetailsTemplate.tpl'),
		 	ajaxSendAfter:function(json){
		 		if(json.bean.unit == '1'){//非多单位
		 			var item = json.bean.norms[0];
		 			item.unitName = json.bean.unitName;
		 			$("#showForm").append(getDataUseHandlebars(simpleTemplate, item));
		 		}else{//多单位
		 			var item = new Array();
		 			item.unitGroupName = json.bean.unitGroupName;
		 			item.firstInUnit = json.bean.firstInUnit;
		 			item.firstOutUnit = json.bean.firstOutUnit;
		 			item.norms = json.bean.norms;
		 			$("#showForm").append(getDataUseHandlebars(manyTemplate, item));
		 		}
		 		form.render();
		 	}
		});
		
		$("body").on("click", ".notice-title-click", function(e){
			mUnitId = $(this).attr("rowid");
			_openNewWindows({
				url: "../../tpl/material/materialstocklist.html", 
				title: "库存明细",
				pageId: "materialstocklist",
				area: ['100vw', '100vh'],
				callBack: function(refreshCode){
				}});
		});
	    
	});
});