
layui.config({
	base: basePath, 
	version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'table', 'jquery', 'winui'], function (exports) {
	
	winui.renderColor();
	
	var $ = layui.$,
		table = layui.table;
	
	//表格渲染
	table.render({
	    id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'material012',
        where: {mUnitId: parent.mUnitId},
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
	        { field: 'unitPrice', title: '入库单价', align: 'left', width: 120 },
	        { field: 'operNumber', title: '数量', align: 'left', width: 120, templet: function(d){
		        if(d.type == 2){
		        	return d.operNumber;
		        }else{
		        	return "-" + d.operNumber;
		        }
		    }},
	        { field: 'taxLastMoney', title: '含税合计', align: 'left', width: 120 },
	        { field: 'operTime', title: '单据日期', align: 'center', width: 140 }
	    ]],
	    done: function(){
	    }
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
		if(data.subType == '1'){//采购入库
			url = "../../tpl/purchaseput/purchaseputdetails.html";
		}else if(data.subType == '4'){//其他入库
			url = "../../tpl/otherwarehous/otherwarehousdetails.html";
		}
		_openNewWindows({
			url: url, 
			title: "单据详情",
			pageId: "otherwarehousdetails",
			area: ['90vw', '90vh'],
			callBack: function(refreshCode){
			}});
	}
	
    exports('materialstocklist', {});
    
});
