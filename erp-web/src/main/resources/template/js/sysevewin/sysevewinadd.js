
layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'jquery', 'winui', 'fileUpload'], function (exports) {
	winui.renderColor();
	layui.use(['form', 'layedit'], function (form) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    var $ = layui.$,
		    form = layui.form,
		    layedit = layui.layedit;
	    
	    //初始化上传
 		$("#sysPic").upload({
            "action": reqBasePath + "common003",
            "data-num": "1",
            "data-type": "PNG,JPG,jpeg,gif",
            "uploadType": 8,
            "function": function (_this, data) {
                show("#sysPic", data);
            }
        });
	    
	    var layContent = layedit.build('content', {
	    	tool: [
    	       'strong' //加粗
    	       ,'italic' //斜体
    	       ,'underline' //下划线
    	       ,'del' //删除线
    	       ,'|' //分割线
    	       ,'left' //左对齐
    	       ,'center' //居中对齐
    	       ,'right' //右对齐
    	       ,'link' //超链接
    	       ,'unlink' //清除链接
    	       ,'face' //表情
    	       ,'code'
    	     ]
	    });
	    
	    //加载一级分类
		showGrid({
		 	id: "sysFirstType",
		 	url: reqBasePath + "sysevewintype012",
		 	params: {},
		 	pagination: false,
		 	template: getFileContent('tpl/template/select-option.tpl'),
		 	ajaxSendLoadBefore: function(hdb){
		 	},
		 	ajaxSendAfter:function(json){
		 		form.render('select');
		 	}
		});
	    
	    form.render();
	    
	    form.on('select(sysFirstType)', function(data){
	    	var firstSelTypeId;
	    	if(isNull(data.value)){
	    		firstSelTypeId = '111';
	    	}else{
	    		firstSelTypeId = data.value;
	    	}
	    	showGrid({
			 	id: "sysSecondType",
			 	url: reqBasePath + "sysevewintype013",
			 	params: {rowId: firstSelTypeId},
			 	pagination: false,
			 	template: getFileContent('tpl/template/select-option.tpl'),
			 	ajaxSendLoadBefore: function(hdb){
			 	},
			 	ajaxSendAfter:function(json){
			 		form.render('select');
			 	}
			});
		});
		
	    form.on('submit(formAddBean)', function (data) {
	    	//表单验证
	        if (winui.verifyForm(data.elem)) {
        		var params = {
    				sysName: $("#sysName").val(),
    				sysUrl: $("#sysUrl").val(),
    				sysDesc: encodeURI(layedit.getContent(layContent)),
    				sysType: $("#sysType").val(),
    				sysFirstType: data.field.sysFirstType,
    				sysSecondType: data.field.sysSecondType,
        		};
        		params.sysPic = $("#sysPic").find("input[type='hidden'][name='upload']").attr("oldurl");
 	        	if(isNull(params.sysPic)){
 	        		winui.window.msg('请上传系统图片', {icon: 2,time: 2000});
 	        		return false;
 	        	}
    			AjaxPostUtil.request({url:reqBasePath + "sysevewin002", params:params, type:'json', callback:function(json){
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
	    
	    //取消
	    $("body").on("click", "#cancle", function(){
	    	parent.layer.close(index);
	    });
	    
	});
	    
});