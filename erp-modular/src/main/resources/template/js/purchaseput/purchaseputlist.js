
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
    authBtn('1571813042536');//新增
    authBtn('1571988895735');//导出
        
    laydate.render({
		elem: '#operTime', //指定元素
		range: '~'
	});
        
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'purchaseput001',
        where: {defaultNumber: $("#defaultNumber").val(), material: $("#material").val(), startTime: startTime, endTime: endTime},
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
            { field: 'supplierName', title: '供应商', align: 'left', width: 150},
            { field: 'materialNames', title: '关联产品', align: 'left', width: 300},
            { field: 'totalPrice', title: '合计金额', align: 'left', width: 120},
            { field: 'taxMoney', title: '含税合计', align: 'left', width: 120 },
            { field: 'discountLastMoney', title: '优惠后金额', align: 'left', width: 120 },
            { field: 'changeAmount', title: '付款', align: 'left', width: 120 },
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

    //删除
    function deletemember(data){
        layer.confirm('确认要删除信息吗？', { icon: 3, title: '删除操作' }, function (index) {
            AjaxPostUtil.request({url:reqBasePath + "member004", params: {rowId: data.id}, type:'json', callback:function(json){
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
	function edit(data){
		rowId = data.id;
		_openNewWindows({
			url: "../../tpl/purchaseput/purchaseputedit.html", 
			title: "编辑",
			pageId: "purchaseputedit",
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
    
    //详情
	function details(data){
		rowId = data.id;
		_openNewWindows({
			url: "../../tpl/purchaseput/purchaseputdetails.html", 
			title: "详情",
			pageId: "purchaseputdetails",
			area: ['90vw', '90vh'],
			callBack: function(refreshCode){
			}});
	}

    //添加
    $("body").on("click", "#addBean", function(){
        _openNewWindows({
            url: "../../tpl/purchaseput/purchaseputadd.html",
            title: "新增",
            pageId: "purchaseputadd",
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
    });
    
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
    
    //导出excel
    $("body").on("click", "#downloadExcel", function () {
    	if(isNull($("#operTime").val())){//一定要记得，当createTime为空时
    		startTime = "";
    		endTime = "";
    	}else {
    		startTime = $("#operTime").val().split('~')[0].trim() + ' 00:00:00';
    		endTime = $("#operTime").val().split('~')[1].trim() + ' 23:59:59';
    	}
    	postDownLoadFile({
			url : reqBasePath + 'purchaseput005?userToken=' + getCookie('userToken') + '&loginPCIp=' + returnCitySN["cip"],
			params: {defaultNumber: $("#defaultNumber").val(), material: $("#material").val(), startTime: startTime, endTime: endTime},
			method : 'post'
		});
    });

    exports('purchaseputlist', {});
});
