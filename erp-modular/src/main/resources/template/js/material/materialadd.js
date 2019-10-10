
//多单位数组列表
var unitGroupList = new Array();

layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'jquery', 'winui'], function (exports) {
	winui.renderColor();
	layui.use(['form'], function (form) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    var $ = layui.$;
	    
	    //多单位计数器
	    var unitIndex = 1;
	    //多单位模板
	    var beanTemplate = $("#beanTemplate").html();
	    //下拉框模板
	    var selTemplate = getFileContent('tpl/template/select-option.tpl');
	    
	    //默认隐藏多单位选项内容
	    $(".many-term").hide();
	    initFirstType();
		//初始化一级类型
		function initFirstType(){
			showGrid({
			 	id: "categoryId",
			 	url: reqBasePath + "materialcategory012",
			 	params: {},
			 	pagination: false,
			 	template: selTemplate,
			 	ajaxSendLoadBefore: function(hdb){},
			 	ajaxSendAfter:function(json){
			 		form.render('select');
			 		//加载多单位下拉框
			 		initManyUnitSelect();
			 	}
		    });
		}
		//一级类型监听事件
		form.on('select(categoryId)', function(data){
			if(isNull(data.value)){
	    		$("#categoryIdSec").html("");
	    		form.render('select');
	    	}else{
		    	showGrid({
				 	id: "categoryIdSec",
				 	url: reqBasePath + "materialcategory013",
				 	params: {parentId: data.value},
				 	pagination: false,
				 	template: selTemplate,
				 	ajaxSendLoadBefore: function(hdb){
				 	},
				 	ajaxSendAfter:function(json){
				 		form.render('select');
				 	}
				});
	    	}
		});
		
		//加载多单位下拉框
		function initManyUnitSelect(){
			showGrid({
			 	id: "unitGroupId",
			 	url: reqBasePath + "material002",
			 	params: {},
			 	pagination: false,
			 	template: selTemplate,
			 	ajaxSendLoadBefore: function(hdb){},
			 	ajaxSendAfter:function(json){
			 		form.render('select');
			 		unitGroupList = json.rows;
			 	}
		    });
		    
		    form.on('select(unitGroupId)', function(data){
				if(isNull(data.value)){
		    		$("#firstInUnit").html("");
		    		$("#firstOutUnit").html("");
		    		form.render('select');
		    	}else{
			    	$.each(unitGroupList, function(i, item){
			    		if(item.id == data.value){
			    			var str = getDataUseHandlebars(selTemplate, {rows: item.unitList});
			    			$("#firstInUnit").html(str);
		    				$("#firstOutUnit").html(str);
		    				form.render('select');
		    				$("#useTable").html("");
		    				$.each(item.unitList, function(j, bean){
		    					addRow(bean);
		    				});
			    			return false;
			    		}
			    	});
		    	}
			});
		}
	    
 		form.render();
 	    form.on('submit(formAddBean)', function (data) {
 	    	//提交前进行制空操作，防止多余的校验
 	    	if($("#unit").val() === 'true'){//多单位
 	        	$(".single-term").find("input").val("");
        	}else{//单单位
        		$(".many-term").find("select").val("");
        		form.render('select');
        		$("#useTable").html("");
        	}
 	    	//表单验证
 	        if (winui.verifyForm(data.elem)) {
				var tableData = new Array();
 	        	if($("#unit").val() === 'true'){//多单位
 	        		if(!subVerifyForm("unitGroupId"))return false;//单位非空校验
 	        		if(!subVerifyForm("firstInUnit"))return false;//首选入库单位校验
 	        		if(!subVerifyForm("firstOutUnit"))return false;//首选出库单位非空校验
 	        		//价格表校验
 	        		var rowTr = $("#useTable tr");
					if(rowTr.length == 0) {
						winui.window.msg('请填写价格表~', {icon: 2, time: 2000});
						return false;
					}
					$.each(rowTr, function(i, item) {
						var rowNum = $(item).attr("trcusid").replace("tr", "");
						var unitId = $(item).attr("unitid");//数据库存储的id
						if(!subVerifyForm("safetyTock" + rowNum))return false;//安全存量非空校验
	 	        		if(!subVerifyForm("retailPrice" + rowNum))return false;//零售价非空校验
	 	        		if(!subVerifyForm("lowPrice" + rowNum))return false;//最低售价非空校验
	 	        		if(!subVerifyForm("estimatePurchasePrice" + rowNum))return false;//预计采购价非空校验
	 	        		if(!subVerifyForm("salePrice" + rowNum))return false;//销售价非空校验
						var row = {
							unitId: unitId,
							safetyTock: $("#safetyTock" + rowNum).val(),
							retailPrice: $("#retailPrice" + rowNum).val(),
							lowPrice: $("#lowPrice" + rowNum).val(),
							estimatePurchasePrice: $("#estimatePurchasePrice" + rowNum).val(),
							salePrice: $("#salePrice" + rowNum).val()
						};
						tableData.push(row);
					});
					if(tableData.length < rowTr.length){
						return false;
					}
 	        	}else{//单单位
 	        		if(!subVerifyForm("safetyTock"))return false;//安全存量非空校验
 	        		if(!subVerifyForm("unitName"))return false;//单位非空校验
 	        		if(!subVerifyForm("retailPrice"))return false;//零售价非空校验
 	        		if(!subVerifyForm("lowPrice"))return false;//最低售价非空校验
 	        		if(!subVerifyForm("estimatePurchasePrice"))return false;//预计采购价非空校验
 	        		if(!subVerifyForm("salePrice"))return false;//销售价非空校验
 	        		var row = {
						unitName: $("#unitName").val(),
						safetyTock: $("#safetyTock").val(),
						retailPrice: $("#retailPrice").val(),
						lowPrice: $("#lowPrice").val(),
						estimatePurchasePrice: $("#estimatePurchasePrice").val(),
						salePrice: $("#salePrice").val()
					};
					tableData.push(row);
 	        	}
				var params = {
        			materialName: $("#materialName").val(),
 	        		model: $("#model").val(),
 	        		categoryId: $("#categoryId").val(),
 	        		categoryIdSec: $("#categoryIdSec").val(),
 	        		remark: $("#remark").val(),
 	        		unit: $("#unit").val() === 'true' ? '2' : '1',
 	        		unitGroupId: $("#unitGroupId").val(),
 	        		firstInUnit: $("#firstInUnit").val(),
 	        		firstOutUnit: $("#firstOutUnit").val(),
	 	        	materialNormsStr: JSON.stringify(tableData)
 	        	};
 	        	AjaxPostUtil.request({url:reqBasePath + "material003", params: params, type:'json', callback:function(json){
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
 	    
 	    //自定义校验是否必填
 	    function subVerifyForm(id){
 	    	if(isNull($("#" + id).val())){
 	    		$("#" + id).addClass("layui-form-danger");
				$("#" + id).focus();
				winui.window.msg('必填项不能为空', {icon: 5, shift: 6});
 	    	}else{
 	    		return true;
 	    	}
 	    }
 	    
 	    //多单位开关
 		form.on('switch(unit)', function (data) {
 			//同步开关值
 			$(data.elem).val(data.elem.checked);
 			if(data.elem.checked){//多单位
				$(".many-term").show();
				$(".single-term").hide();
 			}else{//单单位
 				$(".many-term").hide();
				$(".single-term").show();
 			}
 		});
 	    
		//添加副单位模板
 	    function addRow(bean){
 	    	var j = {
 	    		trId: "tr" + unitIndex.toString(), //行的id
 	    		unitId: bean.id, //数据库单位id
 	    		unitNameType: bean.baseUnit == 1 ? "基础单位" : "副单位", //单位类型
 	    		unitName: bean.name, //单位
 	    		safetyTock: "safetyTock" + unitIndex.toString(), //安全存量
 	    		retailPrice: "retailPrice" + unitIndex.toString(), //零售价
 	    		lowPrice: "lowPrice" + unitIndex.toString(), //最低售价
 	    		estimatePurchasePrice: "estimatePurchasePrice" + unitIndex.toString(), //预计采购价
 	    		salePrice: "salePrice" + unitIndex.toString() //销售价
 	    	};
 	    	$("#useTable").append(getDataUseHandlebars(beanTemplate, j));
 	    	unitIndex++;
 	    }
 	    
	    $("body").on("click", "#cancle", function(){
	    	parent.layer.close(index);
	    });
	});
});