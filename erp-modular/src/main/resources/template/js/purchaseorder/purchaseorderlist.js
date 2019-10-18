
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
        
    laydate.render({
		elem: '#operTime', //指定元素
		range: '~'
	});
        
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'purchaseorder001',
        where: {defaultNumber: $("#defaultNumber").val(), material: $("#material").val(), startTime: startTime, endTime: endTime},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'defaultNumber', title: '单据编号', align: 'left', width: 200, templet: function(d){
		        return '<a lay-event="details" class="notice-title-click">' + d.defaultNumber + '</a>';
		    }},
            { field: 'supplierName', title: '供应商', align: 'left', width: 150},
            { field: 'materialNames', title: '关联产品', align: 'left', width: 300},
            { field: 'status', title: '状态', align: 'left', width: 80, templet: function(d){
		        if(d.status == '0'){
	        		return "<span class='state-down'>未审核</span>";
	        	}else if(d.status == '1'){
	        		return "<span class='state-up'>审核中</span>";
	        	}else if(d.status == '2'){
	        		return "<span class='state-new'>审核通过</span>";
	        	}else if(d.status == '3'){
	        		return "<span class='state-down'>拒绝通过</span>";
	        	}else if(d.status == '4'){
	        		return "<span class='state-new'>已转采购</span>";
	        	}else{
	        		return "参数错误";
	        	}
		    }},
            { field: 'totalPrice', title: '合计金额', align: 'left', width: 120},
            { field: 'operPersonName', title: '操作人', align: 'left', width: 100},
            { field: 'operTime', title: '单据日期', align: 'center', width: 140 },
            { title: '操作', fixed: 'right', align: 'center', width: 200, toolbar: '#tableBar'}
        ]]
    });

    table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'delete') { //删除
            deletemember(data);
        }else if (layEvent === 'details') { //详情
        	details(data);
        }else if (layEvent === 'edit') { //编辑
        	edit(data);
        }else if (layEvent === 'subExamine') { //提交审核
        	subExamine(data);
        }else if (layEvent === 'examine') { //审核
        	examine(data);
        }else if (layEvent === 'turnPurchase') { //转采购单
        	turnPurchase(data);
        }
    });

    //搜索表单
    form.render();
    form.on('submit(formSearch)', function (data) {
        //表单验证
        if (winui.verifyForm(data.elem)) {
            loadTable();
        }
        return false;
    });
    
    //编辑
    function edit(data){
        rowId = data.id;
        _openNewWindows({
            url: "../../tpl/purchaseorder/purchaseorderedit.html",
            title: "编辑",
            pageId: "purchaseorderedit",
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

    //删除
    function deletemember(data){
        layer.confirm('确认要删除信息吗？', { icon: 3, title: '删除操作' }, function (index) {
            AjaxPostUtil.request({url:reqBasePath + "purchaseorder003", params: {rowId: data.id}, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("删除成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }
    
    //详情
	function details(data){
		rowId = data.id;
		_openNewWindows({
			url: "../../tpl/purchaseorder/purchaseorderdetails.html", 
			title: "详情",
			pageId: "purchaseorderdetails",
			area: ['90vw', '90vh'],
			callBack: function(refreshCode){
			}});
	}
	
	//提交审批
	function subExamine(data){
        layer.confirm('确认要提交审核吗？', { icon: 3, title: '提交审核操作' }, function (index) {
            AjaxPostUtil.request({url:reqBasePath + "purchaseorder006", params: {rowId: data.id}, type:'json', callback:function(json){
                if(json.returnCode == 0){
                    winui.window.msg("提交成功。", {icon: 1,time: 2000});
                    loadTable();
                }else{
                    winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
                }
            }});
        });
    }
    
    //审核
	function examine(data){
		rowId = data.id;
		_openNewWindows({
			url: "../../tpl/purchaseorder/purchaseorderexamine.html", 
			title: "审核",
			pageId: "purchaseorderdetails",
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
    
    //转采购入库
	function turnPurchase(data){
		rowId = data.id;
		_openNewWindows({
			url: "../../tpl/purchaseorder/purchaseorderpurchase.html", 
			title: "转采购入库",
			pageId: "purchaseorderpurchase",
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
	
    //添加
    $("body").on("click", "#addBean", function(){
        _openNewWindows({
            url: "../../tpl/purchaseorder/purchaseorderadd.html",
            title: "新增",
            pageId: "purchaseorderadd",
            area: ['90vw', '90vh'],
            callBack: function(refreshCode){
                if (refreshCode == '0') {
                    winui.window.msg("操作成功", {icon: 1,time: 2000});
                    loadTable();
                } else if (refreshCode == '-9999') {
                    winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
            }});
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
    		startTime = "";
    		endTime = "";
    	}else {
    		startTime = $("#operTime").val().split('~')[0].trim() + ' 00:00:00';
    		endTime = $("#operTime").val().split('~')[1].trim() + ' 23:59:59';
    	}
        table.reload("messageTable", {where:{defaultNumber: $("#defaultNumber").val(), material: $("#material").val(), startTime: startTime, endTime: endTime}});
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
        table.reload("messageTable", {page: {curr: 1}, where:{defaultNumber: $("#defaultNumber").val(), material: $("#material").val(), startTime: startTime, endTime: endTime}})
    }

    exports('otherwarehouslist', {});
});
