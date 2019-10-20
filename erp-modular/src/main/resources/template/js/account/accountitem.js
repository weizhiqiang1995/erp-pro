
var rowId = "";

layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui'], function (exports) {
    winui.renderColor();
    var $ = layui.$,
        table = layui.table;
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'account008',
        where: {rowId:parent.rowId},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'number', title: '票据号', align: 'center',width: 200},
            { field: 'subType', title: '出入库分类', align: 'left',width: 100, templet: function(d){
                if(d.subType == '1'){
                    return "<span class='state-up'>采购入库</span>";
                }else if(d.subType == '2'){
                    return "<span class='state-up'>销售退货</span>";
                }else if(d.subType == '3'){
                    return "<span class='state-up'>零售退货</span>";
                }else if(d.subType == '4'){
                    return "<span class='state-up'>其他入库</span>";
                }else if(d.subType == '5'){
                    return "<span class='state-down'>销售出库</span>";
                }else if(d.subType == '6'){
                    return "<span class='state-down'>采购退货</span>";
                }else if(d.subType == '7'){
                    return "<span class='state-down'>调拨</span>";
                }else if(d.subType == '8'){
                    return "<span class='state-down'>零售</span>";
                }else if(d.subType == '9') {
                    return "<span class='state-down'>其他出库</span>";
                }
            }},
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
            { field: 'type', title: '类型', align: 'center',width: 80, templet: function (d) {
                if(d.type == '1'){
                    return "<span class='state-down'>出库</span>";
                }else if(d.type == '2'){
                    return "<span class='state-up'>入库</span>";
                }else{
                    return "<span class='state-error'>参数错误</span>";
                }
            }},
            { field: 'supplier', title: '单位信息', align: 'left',width: 100},
            { field: 'totalPrice', title: '合计金额', align: 'left',width: 100},
            { field: 'taxLastMoneyPrice', title: '合计价税', align: 'left',width: 100},
            { field: 'operTime', title: '出入库日期', align: 'center', width: 180 },
            { field: 'subTypeName', title: '单据类型', align: 'left',width: 100},
            { field: 'supplierName', title: '状态', align: 'left',width: 120},
            { field: 'totalPrice', title: '合计金额', align: 'left',width: 100},
            { field: 'supplierName', title: '供应商/客户/会员', align: 'left', width: 140 },
            { field: 'payType', title: '付款类型', align: 'center',width: 80, templet: function (d) {
                if(d.payType == '1'){
                    return "<span class='state-up'>现金</span>";
                }else if(d.payType == '2'){
                    return "<span class='state-down'>记账</span>";
                }else{
                    return "<span class='state-error'>其他</span>";
                }
            }},
            { field: 'operTime', title: '单据日期', align: 'center', width: 180 },
        ]]
    });
    table.on('tool(messageTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'details') { //详情
        	details(data);
        }
    });
    
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
		}
		_openNewWindows({
			url: url, 
			title: "详情",
			pageId: "warehousingdetailschildpage",
			area: ['100vw', '100vh'],
			callBack: function(refreshCode){
                if (refreshCode == '0') {
                	winui.window.msg("操作成功", {icon: 1,time: 2000});
                	loadTable();
                } else if (refreshCode == '-9999') {
                	winui.window.msg("操作失败", {icon: 2,time: 2000});
                }
			}});
	}
    
    exports('accountitem', {});
});
