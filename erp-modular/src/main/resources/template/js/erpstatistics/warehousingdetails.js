
var rowId = "";

//单据的开始时间、结束时间
var startTime = "", endTime = "";

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
        
	//初始化统计时间
	startTime = getOneYMDFormatDate();//开始日期为本月一号
	endTime = getYMDFormatDate();//结束今天的日期
	
	//获取本月一号的日期
	function getOneYMDFormatDate(){
		 var date = new Date;
		 var year = date.getFullYear(); 
		 var month = date.getMonth() + 1;
		 month = (month < 10 ? "0" + month : month); 
		 return year.toString() + "-" + month.toString() + "-" + "01";
	}
	
	//获取今天的时间 
	function getYMDFormatDate(){
		var myDate = new Date();
	    var lw = new Date(myDate);
	    var lastY = lw.getFullYear();
	    var lastM = lw.getMonth() + 1;
	    var lastD = lw.getDate();
	    return lastY + "-" + (lastM < 10 ? "0" + lastM : lastM) + "-" + (lastD < 10 ? "0" + lastD : lastD);
	}
        
    laydate.render({
		elem: '#operTime', //指定元素
		range: '~',
		value: startTime + " ~ " + endTime//初始化统计日期
	});
	
	var selOption = getFileContent('tpl/template/select-option.tpl');
	
	initSupplierHtml();
	//初始化供应商
	function initSupplierHtml() {
		AjaxPostUtil.request({url: reqBasePath + "supplier009", params: {}, type: 'json', callback: function(json) {
			if(json.returnCode == 0) {
				//加载供应商数据
				$("#organId").html(getDataUseHandlebars(selOption, json)); 
				//初始化仓库
				initDepotHtml();
			} else {
				winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
			}
		}});
	}
	
	//初始化仓库
	function initDepotHtml() {
		AjaxPostUtil.request({url: reqBasePath + "storehouse008", params: {}, type: 'json', callback: function(json) {
			if(json.returnCode == 0) {
				//加载仓库数据
				$("#depotId").html(getDataUseHandlebars(selOption, json)); 
				//初始化表格数据
				initTable();
			} else {
				winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
			}
		}});
	}
    
	function initTable(){
		if(isNull($("#operTime").val())){//一定要记得，当createTime为空时
    		startTime = "";
    		endTime = "";
    	}else {
    		startTime = $("#operTime").val().split('~')[0].trim() + ' 00:00:00';
    		endTime = $("#operTime").val().split('~')[1].trim() + ' 23:59:59';
    	}
	    //表格渲染
	    table.render({
	        id: 'messageTable',
	        elem: '#messageTable',
	        method: 'post',
	        url: reqBasePath + 'statistics001',
	        where: {materialName: $("#materialName").val(), depotId: $("#depotId").val(), organId: $("#organId").val(), startTime: startTime, endTime: endTime},
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
	            { field: 'materialName', title: '商品名称', align: 'left', width: 150},
	            { field: 'materialModel', title: '商品型号', align: 'left', width: 100},
	            { field: 'unitPrice', title: '单价', align: 'left', width: 120},
	            { field: 'operNumber', title: '入库数量', align: 'left', width: 100},
	            { field: 'allPrice', title: '金额', align: 'left', width: 120 },
	            { field: 'supplierName', title: '供应商/客户/会员', align: 'left', width: 140 },
	            { field: 'depotName', title: '仓库', align: 'left', width: 140 },
	            { field: 'operTime', title: '入库日期', align: 'center', width: 140 }
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
	
    //搜索表单
    form.on('submit(formSearch)', function (data) {
        //表单验证
        if (winui.verifyForm(data.elem)) {
            loadTable();
        }
        return false;
    });

    //详情
	function details(data){
		rowId = data.headerId;
		var url = "";
		if(data.subType == '1'){//采购入库
			url = "../../tpl/purchaseput/purchaseputdetails.html";
		}else if(data.subType == '4'){//其他入库
			url = "../../tpl/otherwarehous/otherwarehousdetails.html";
		}else if(data.subType == '2'){//销售退货
			url = "../../tpl/salesreturns/salesreturnsdetails.html";
		}
		_openNewWindows({
			url: url, 
			title: "详情",
			pageId: "warehousingdetailschildpage",
			area: ['90vw', '90vh'],
			callBack: function(refreshCode){
                if (refreshCode == '0') {
                	winui.window.msg("操作成功", {icon: 1,time: 2000});
                	loadTable();
                } else if (refreshCode == '-9999') {
                	winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
			}});
	}

    $("body").on("click", "#reloadTable", function() {
        loadTable();
    });

    $("body").on("click", "#formSearch", function () {
        refreshTable();
    })
    
    //刷新
    function loadTable(){
    	if(isNull($("#operTime").val())){//一定要记得，当createTime为空时
    		startTime = "";
    		endTime = "";
    	}else {
    		startTime = $("#operTime").val().split('~')[0].trim() + ' 00:00:00';
    		endTime = $("#operTime").val().split('~')[1].trim() + ' 23:59:59';
    	}
        table.reload("messageTable", {where:{materialName: $("#materialName").val(), depotId: $("#depotId").val(), organId: $("#organId").val(), startTime: startTime, endTime: endTime}});
    }

    //搜索
    function refreshTable(){
    	if(isNull($("#operTime").val())){//一定要记得，当createTime为空时
    		startTime = "";
    		endTime = "";
    	}else {
    		startTime = $("#operTime").val().split('~')[0].trim() + ' 00:00:00';
    		endTime = $("#operTime").val().split('~')[1].trim() + ' 23:59:59';
    	}
        table.reload("messageTable", {page: {curr: 1}, where:{materialName: $("#materialName").val(), depotId: $("#depotId").val(), organId: $("#organId").val(), startTime: startTime, endTime: endTime}})
    }

    exports('warehousingdetails', {});
});
