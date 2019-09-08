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
	    
	    AjaxPostUtil.request({url:reqBasePath + "sysevemenuauthpoint003", params:{rowId: parent.rowId}, type:'json', callback:function(json){
   			if(json.returnCode == 0){
	   			$("#authMenuName").val(json.bean.authMenuName);
	   			$("#authMenu").val(json.bean.authMenu);
	   			form.render();
	   			
	   			form.on('submit(formEditBean)', function (data) {
	   				//表单验证
	   				if (winui.verifyForm(data.elem)) {
	   					var params = {
   							authMenuName: $("#authMenuName").val(),
   							authMenu: $("#authMenu").val(),
   							rowId: parent.rowId
	   					};
	   					
	   					AjaxPostUtil.request({url:reqBasePath + "sysevemenuauthpoint004", params:params, type:'json', callback:function(json){
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