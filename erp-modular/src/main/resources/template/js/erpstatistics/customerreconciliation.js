
var rowId = "";

//单据的时间
var operTime = "";

layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'table', 'jquery', 'winui', 'form', 'laydate'], function (exports) {
    winui.renderColor();

    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        table = layui.table;
    
    var selOption = getFileContent('tpl/template/select-option.tpl');
        
    //初始化统计时间
	operTime = getOneYMFormatDate();
	
	//获取本月日期
	function getOneYMFormatDate(){
		 var date = new Date;
		 var year = date.getFullYear(); 
		 var month = date.getMonth() + 1;
		 month = (month < 10 ? "0" + month : month); 
		 return year.toString() + "-" + month.toString();
	}
	
    laydate.render({
		elem: '#operTime', //指定元素
		type: 'month',
		value: operTime
	});
	
	initSupplierHtml();
	//初始化客户
	function initSupplierHtml() {
		AjaxPostUtil.request({url: reqBasePath + "customer009", params: {}, type: 'json', callback: function(json) {
			if(json.returnCode == 0) {
				//加载客户数据
				$("#supplierId").html(getDataUseHandlebars(selOption, json)); 
				//初始化数据
				initTable();
			} else {
				winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
			}
		}});
	}
	
	function initTable(){
	    //表格渲染
	    table.render({
	        id: 'messageTable',
	        elem: '#messageTable',
	        method: 'post',
	        url: reqBasePath + 'statistics005',
	        where: {operTime: operTime, customerId: $("#supplierId").val()},
	        even: true,  //隔行变色
	        page: true,
	        limits: [8, 16, 24, 32, 40, 48, 56],
	        limit: 8,
	        cols: [[
	            { title: '序号', type: 'numbers'},
	            { field: 'defaultNumber', title: '单据编号', align: 'left', width: 250, templet: function(d){
			        var str = '<a lay-event="details" class="notice-title-click">' + d.defaultNumber + '</a>';
			        if(!isNull(d.linkNumber)){
			        	str += '<span class="state-new">[转]</span>';
				        if(d.status == 2){
				        	str += '<span class="state-up"> [正常]</span>';
				        }else{
				        	str += '<span class="state-down"> [预警]</span>';
				        }
			        }
			        return str;
			    }},
			    { field: 'subTypeName', title: '单据类型', align: 'left', width: 100},
	            { field: 'supplierName', title: '客户名称', align: 'left', width: 120},
	            { field: 'totalPrice', title: '合计金额', align: 'left', width: 100},
	            { field: 'changeAmount', title: '实际支付', align: 'left', width: 120},
	            { field: 'operTime', title: '退货数量', align: 'center', width: 150}
	        ]]
	    });
	    table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	        var data = obj.data; //获得当前行数据
	        var layEvent = obj.event; //获得 lay-event 对应的值
	        if (layEvent === 'details') { //详情
	        	details(data);
	        }
	    });
	    form.render();
	}
	
	//详情
	function details(data){
		rowId = data.id;
		var url = "";
		if(data.subType == 1){//采购入库
			url = "../../tpl/purchaseput/purchaseputdetails.html";
		}else if(data.subType == 4){//其他入库
			url = "../../tpl/otherwarehous/otherwarehousdetails.html";
		}else if(data.subType == 2){//销售退货
			url = "../../tpl/salesreturns/salesreturnsdetails.html";
		}else if(data.subType == 6){//采购退货
			url = "../../tpl/purchasereturns/purchasereturnsdetails.html";
		}else if(data.subType == 9){//其他出库
			url = "../../tpl/otheroutlets/otheroutletsdetails.html";
		}else if(data.subType == 5){//销售出库
			url = "../../tpl/salesoutlet/salesoutletdetails.html";
		}else if(data.subType == 8){//零售出库
			url = "../../tpl/retailoutlet/retailoutletdetails.html";
		}else if(data.subType == 3){//零售退货
			url = "../../tpl/retailreturns/retailreturnsdetails.html";
		}else if(data.subType == 12){//拆分单
			url = "../../tpl/splitlist/splitlistdetails.html";
		}else if(data.subType == 13){//组装单
			url = "../../tpl/assemblysheet/assemblysheetdetails.html";
		}
		_openNewWindows({
			url: url, 
			title: "单据详情",
			pageId: "otherwarehousdetails",
			area: ['90vw', '90vh'],
			callBack: function(refreshCode){
			}});
	}
	
    //搜索表单
    form.on('submit(formSearch)', function (data) {
        //表单验证
        if (winui.verifyForm(data.elem)) {
            loadTable();
        }
        return false;
    });

    $("body").on("click", "#reloadTable", function() {
        loadTable();
    });

    $("body").on("click", "#formSearch", function () {
        refreshTable();
    })
    
    //刷新
    function loadTable(){
    	if(isNull($("#operTime").val())){//一定要记得，当createTime为空时
    		winui.window.msg("请选择日期.", {icon: 2,time: 2000});
    	}else {
    		operTime = $("#operTime").val();
	        table.reload("messageTable", {where:{operTime: operTime, customerId: $("#supplierId").val()}});
    	}
    }

    //搜索
    function refreshTable(){
    	if(isNull($("#operTime").val())){//一定要记得，当createTime为空时
    		winui.window.msg("请选择日期.", {icon: 2,time: 2000});
    	}else {
    		operTime = $("#operTime").val();
	        table.reload("messageTable", {page: {curr: 1}, where:{operTime: operTime, customerId: $("#supplierId").val()}})
    	}
    }

    exports('customerreconciliation', {});
});
