var material = new Array(); //产品集合

layui.config({
	base: basePath,
	version: skyeyeVersion
}).extend({ //指定js别名
	window: 'js/winui.window'
}).define(['window', 'jquery', 'winui', 'laydate'], function(exports) {
	winui.renderColor();
	layui.use(['form'], function(form) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var $ = layui.$,
			laydate = layui.laydate;
		var enclosureInfo = ""; //附件id
		var rowNum = 1; //表格的序号
		var depotHtml = "", materialHtml = "", inoutitemHtml = "";//仓库,产品,支出项目
		var tockObject = new Array();//根据仓库和规格id查询出来的对应库存信息
		
		var cgddOrderNum = "";//采购订单编号

		var usetableTemplate = $("#usetableTemplate").html(),
			otherTemplate = $("#otherTemplate").html();
		var selOption = getFileContent('tpl/template/select-option.tpl');
		
		//事故时间
 		laydate.render({ 
 		  elem: '#operTime',
 		  type: 'datetime',
 	 	  trigger: 'click'
 		});
		
 		initAccountHtml();
 		//初始化账户
		function initAccountHtml() {
			AjaxPostUtil.request({url: reqBasePath + "account009", params: {}, type: 'json', callback: function(json) {
				if(json.returnCode == 0) {
					//加载账户数据
					$("#accountId").html(getDataUseHandlebars(selOption, json)); 
					//初始化支出项目
					initInoutitemHtml();
				} else {
					winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
				}
			}});
		}
		
		//初始化支出项目
		function initInoutitemHtml() {
			AjaxPostUtil.request({url: reqBasePath + "inoutitem007", params: {}, type: 'json', callback: function(json) {
				if(json.returnCode == 0) {
					//加载支出项目
					inoutitemHtml = getDataUseHandlebars(selOption, json); 
					//初始化供应商
					initSupplierHtml();
				} else {
					winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
				}
			}});
		}
 		
		//初始化供应商
		function initSupplierHtml() {
			AjaxPostUtil.request({url: reqBasePath + "supplier009", params: {}, type: 'json', callback: function(json) {
				if(json.returnCode == 0) {
					//加载供应商数据
					$("#supplierId").html(getDataUseHandlebars(selOption, json)); 
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
					depotHtml = getDataUseHandlebars(selOption, json); 
					//初始化产品
					initMaterialHtml();
				} else {
					winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
				}
			}});
		}
		
		//初始化产品
		function initMaterialHtml() {
			AjaxPostUtil.request({url: reqBasePath + "material010", params: {}, type: 'json', callback: function(json) {
				if(json.returnCode == 0) {
					material = json.rows;
					//加载产品数据
					materialHtml = getDataUseHandlebars(selOption, json);
					//初始化回显数据
					initDataShow();
				} else {
					winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
				}
			}});
		}
		
		//初始化回显数据
		function initDataShow(){
			AjaxPostUtil.request({url: reqBasePath + "purchaseorder008", params: {rowId: parent.rowId}, type: 'json', callback: function(json) {
				if(json.returnCode == 0) {
					if(isNull(json.bean)){
						$("#showForm").html("");
						winui.window.msg('数据不存在~', {icon: 2, time: 2000});
						return;
					}
					cgddOrderNum = json.bean.defaultNumber;
					$("#cgddOrderNum").html(json.bean.defaultNumber);
					$("#supplierId").val(json.bean.organId);
					$("#operTime").val(json.bean.operTime);
					$("#accountId").val(json.bean.accountId);
					$("#payType").val(json.bean.payType);
					$("#allPrice").html(json.bean.totalPrice.toFixed(2));
					$("#taxLastMoneyPrice").html(json.bean.taxLastMoneyPrice.toFixed(2));
					$("#remark").val(json.bean.remark);
					$("#discount").val(json.bean.discount.toFixed(2));
					$("#discountMoney").val(json.bean.discountMoney.toFixed(2));
					$("#discountLastMoney").html(json.bean.discountLastMoney.toFixed(2));
					$("#changeAmount").val(json.bean.changeAmount.toFixed(2));
					$("#arrears").html(json.bean.arrears.toFixed(2));
					$("#otherPriceTotal").html("费用合计：" + json.bean.otherMoney.toFixed(2));
					
					//加载列表项
					$.each(json.bean.items, function(i, item){
						addRow();
						$("#depotId" + (rowNum - 1)).val(item.depotId);
						$("#materialId" + (rowNum - 1)).val(item.materialId);
						
						$.each(material, function(j, bean) {
							if(item.materialId == bean.id){
								$("#unitId" + (rowNum - 1)).html(getDataUseHandlebars(selOption, {rows: bean.unitList}));
								$("#unitId" + (rowNum - 1)).val(item.mUnitId);//单位回显
								return false;
							}
						});
						$("#currentTock" + (rowNum - 1)).html(item.currentTock);
						
						$("#rkNum" + (rowNum - 1)).val(item.operNumber);
						$("#unitPrice" + (rowNum - 1)).val(item.unitPrice.toFixed(2));
						$("#amountOfMoney" + (rowNum - 1)).val(item.allPrice.toFixed(2));
						$("#taxRate" + (rowNum - 1)).val(item.taxRate.toFixed(2));
						$("#taxMoney" + (rowNum - 1)).val(item.taxMoney.toFixed(2));
						$("#taxUnitPrice" + (rowNum - 1)).val(item.taxUnitPrice.toFixed(2));
						$("#taxLastMoney" + (rowNum - 1)).val(item.taxLastMoney.toFixed(2));
						$("#remark" + (rowNum - 1)).val(item.remark);
					});
					
					//加载采购费用
					$.each(json.bean.otherMoneyList, function(i, item){
						addPriceRow();
						$("#inoutitemId" + (priceNum - 1)).val(item.inoutitemId);
						$("#otherPrice" + (priceNum - 1)).val(parseFloat(item.otherPrice).toFixed(2));
					});					
					
					//渲染
					form.render();
				} else {
					winui.window.msg(json.returnMessage, {icon: 2, time: 2000}, function(){
						parent.layer.close(index);
						parent.refreshCode = '-9999';
					});
				}
			}});
		}
		
		//仓库加载变化事件
		form.on('select(selectDepotProperty)', function(data) {
			var thisRowNum = data.elem.id.replace("depotId", "");//获取当前行
			var thisRowValue = data.value;
			loadTockByDepotAndMUnit(thisRowNum);
		});

		//产品加载变化事件
		form.on('select(selectMaterialProperty)', function(data) {
			var thisRowNum = data.elem.id.replace("materialId", "");//获取当前行
			var thisRowValue = data.value;
			if(!isNull(thisRowValue) && thisRowValue != '请选择') {
				$.each(material, function(i, item) {
					if(thisRowValue == item.id){
						$("#unitId" + thisRowNum).html(getDataUseHandlebars(selOption, {rows: item.unitList}));
						var rkNum = parseInt($("#rkNum" + thisRowNum).val());
						//设置默认选中
						if(item.unit == 2){//多单位
							$.each(item.unitList, function(j, bean) {
								if(item.firstInUnit == bean.unitId){
									$("#unitId" + thisRowNum).val(bean.id);
									$("#unitPrice" + thisRowNum).val(bean.estimatePurchasePrice.toFixed(2));//单价
									return false;
								}
							});
						}else{//不是多单位
							var firstItem = item.unitList[0];
							$("#unitId" + thisRowNum).val(firstItem.id);
							$("#unitPrice" + thisRowNum).val(firstItem.estimatePurchasePrice.toFixed(2));//单价
						}
						form.render('select');
						return false;
					}
				});
			} else {
				$("#unitId" + thisRowNum).html(""); //重置规格为空
				form.render('select');
			}
			//加载库存
			loadTockByDepotAndMUnit(thisRowNum);
			//计算价格
			calculatedTotalPrice();
		});
		
		//产品规格加载变化事件
		form.on('select(selectUnitProperty)', function(data) {
			var thisRowNum = data.elem.id.replace("unitId", "");//获取当前行
			var thisRowValue = data.value;
			//当前选中的产品id
			var chooseMaterialId = $("#materialId" + thisRowNum).val();
			if(!isNull(thisRowValue) && thisRowValue != '请选择') {
				$.each(material, function(i, item) {
					if(chooseMaterialId == item.id){//获取产品
						$.each(item.unitList, function(j, bean) {
							if(thisRowValue == bean.id){//获取规格
								//获取当前行数量
								var rkNum = parseInt($("#rkNum" + thisRowNum).val());
								$("#unitPrice" + thisRowNum).val(bean.estimatePurchasePrice.toFixed(2));//单价
								return false;
							}
						});
						return false;
					}
				});
			} else {
				$("#unitPrice" + thisRowNum).val("0.00");//重置单价为空
			}
			//加载库存
			loadTockByDepotAndMUnit(thisRowNum);
			//计算价格
			calculatedTotalPrice();
		});
		
		/**
		 * 根据仓库和规格加载库存
		 * @param rowNum 表格行坐标
		 */
		function loadTockByDepotAndMUnit(rowNum){
			//获取当前选中的仓库
			var chooseDepotId = $("#depotId" + rowNum).val();
			//获取当前选中的规格
			var chooseUnitId = $("#unitId" + rowNum).val();
			//当两个都不为空时
			if(!isNull(chooseDepotId) && !isNull(chooseUnitId)){
				var inTockObject = -1;
				$.each(tockObject, function(i, item){
					if(item.depotId == chooseDepotId && item.unitId == chooseUnitId){
						inTockObject = i;
						$("#currentTock" + rowNum).html(item.currentTock);
						return false;
					}
				});
				//如果数组中不包含对应的库存
				if(inTockObject < 0){
					//获取库存
					AjaxPostUtil.request({url: reqBasePath + "material011", params: {depotId: chooseDepotId, mUnitId: chooseUnitId}, type: 'json', callback: function(json) {
						if(json.returnCode == 0) {
							var currentTock = 0;
							if(!isNull(json.bean)){
								currentTock = json.bean.currentTock;
							}
							tockObject.push({
								depotId: chooseDepotId,
								unitId: chooseUnitId,
								currentTock: currentTock
							});
							$("#currentTock" + rowNum).html(currentTock);
						} else {
							winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
						}
					}});
				}
			}else{
				//否则重置库存为空
				$("#currentTock" + rowNum).html("0.00");
			}
		}
		
		var showTdByEdit = 'rkNum';//根据那一列的值进行变化,默认根据数量
		//数量变化,税率变化
		$("body").on("input", ".rkNum, .unitPrice, .amountOfMoney, .taxRate, .taxMoney, .taxUnitPrice, .taxLastMoney", function() {
			if($(this).attr("class").replace("layui-input change-input ", "") != showTdByEdit){
				showTdByEdit = $(this).attr("class").replace("layui-input change-input ", "");
				$(".change-input").parent().removeAttr("style");
				$("." + showTdByEdit).parent().css({'background-color': '#e6e6e6'});
			}
			calculatedTotalPrice();
		});
		$("body").on("change", ".rkNum, .unitPrice, .amountOfMoney, .taxRate, .taxMoney, .taxUnitPrice, .taxLastMoney", function() {
			if($(this).attr("class").replace("layui-input change-input ", "") != showTdByEdit){
				showTdByEdit = $(this).attr("class").replace("layui-input change-input ", "");
				$(".change-input").parent().removeAttr("style");
				$("." + showTdByEdit).parent().css({'background-color': '#e6e6e6'});
			}
			calculatedTotalPrice();
		});
		
		//计算价格
		function calculatedTotalPrice(){
			var rowTr = $("#useTable tr");
			var allPrice = 0, taxLastMoneyPrice = 0;
			$.each(rowTr, function(i, item) {
				//获取行坐标
				var rowNum = $(item).attr("trcusid").replace("tr", "");
				//获取数量
				var rkNum = parseInt(isNull($("#rkNum" + rowNum).val()) ? 0 : $("#rkNum" + rowNum).val());
				//获取单价
				var unitPrice = parseFloat(isNull($("#unitPrice" + rowNum).val()) ? 0 : $("#unitPrice" + rowNum).val());
				//获取税率
				var taxRate = parseFloat(isNull($("#taxRate" + rowNum).val()) ? 0 : $("#taxRate" + rowNum).val()) / 100;
				
				if('rkNum' === showTdByEdit){//数量
					//输出金额
					$("#amountOfMoney" + rowNum).val((rkNum * unitPrice).toFixed(2));
					//输出税额=数量*税率*单价
					$("#taxMoney" + rowNum).val((rkNum * taxRate * unitPrice).toFixed(2));
					//输出含税单价
					$("#taxUnitPrice" + rowNum).val((taxRate * unitPrice + unitPrice).toFixed(2));
					//输出合计价税
					$("#taxLastMoney" + rowNum).val((rkNum * taxRate * unitPrice + rkNum * unitPrice).toFixed(2));
				}else if('unitPrice' === showTdByEdit){//单价
					//输出金额
					$("#amountOfMoney" + rowNum).val((rkNum * unitPrice).toFixed(2));
					//输出税额=数量*税率*单价
					$("#taxMoney" + rowNum).val((rkNum * taxRate * unitPrice).toFixed(2));
					//输出含税单价
					$("#taxUnitPrice" + rowNum).val((taxRate * unitPrice + unitPrice).toFixed(2));
					//输出合计价税
					$("#taxLastMoney" + rowNum).val((rkNum * taxRate * unitPrice + rkNum * unitPrice).toFixed(2));
				}else if('amountOfMoney' === showTdByEdit){//金额
					//获取金额
					var amountOfMoney = parseFloat(isNull($("#amountOfMoney" + rowNum).val()) ? 0 : $("#amountOfMoney" + rowNum).val());
					//输出税额=金额*税率
					$("#taxMoney" + rowNum).val((amountOfMoney * taxRate).toFixed(2));
					//输出单价,含税单价,合计价税
					if(rkNum != 0){
						$("#unitPrice" + rowNum).val((amountOfMoney / rkNum).toFixed(2));
						$("#taxUnitPrice" + rowNum).val((amountOfMoney / rkNum * taxRate + amountOfMoney / rkNum).toFixed(2));
						$("#taxLastMoney" + rowNum).val((amountOfMoney * taxRate + amountOfMoney).toFixed(2));
					}else{
						$("#unitPrice" + rowNum).val('0.00');
						$("#taxUnitPrice" + rowNum).val('0.00');
						$("#taxLastMoney" + rowNum).val('0.00');
					}
				}else if('taxRate' === showTdByEdit){//税率
					//输出金额
					$("#amountOfMoney" + rowNum).val((rkNum * unitPrice).toFixed(2));
					//输出税额=数量*税率*单价
					$("#taxMoney" + rowNum).val((rkNum * taxRate * unitPrice).toFixed(2));
					//输出含税单价
					$("#taxUnitPrice" + rowNum).val((taxRate * unitPrice + unitPrice).toFixed(2));
					//输出合计价税
					$("#taxLastMoney" + rowNum).val((rkNum * taxRate * unitPrice + rkNum * unitPrice).toFixed(2));
				}else if('taxMoney' === showTdByEdit){//税额
					//获取税额
					var taxMoney = parseFloat(isNull($("#taxMoney" + rowNum).val()) ? 0 : $("#taxMoney" + rowNum).val());
					//输出金额
					$("#amountOfMoney" + rowNum).val((rkNum * unitPrice).toFixed(2));
					//获取金额
					var amountOfMoney = parseFloat(isNull($("#amountOfMoney" + rowNum).val()) ? 0 : $("#amountOfMoney" + rowNum).val());
					//输出含税单价,合计价税,税率
					if(rkNum != 0){
						if(unitPrice != 0){
							$("#taxUnitPrice" + rowNum).val((taxMoney / rkNum + unitPrice).toFixed(2));
							$("#taxRate" + rowNum).val((taxMoney / unitPrice / rkNum * 100).toFixed(2));
						}else{
							$("#taxUnitPrice" + rowNum).val('0.00');
							$("#taxRate" + rowNum).val('0.00');
							$("#unitPrice" + rowNum).val('0.00');
							$("#amountOfMoney" + rowNum).val('0.00');
						}
						if(amountOfMoney != 0){
							$("#taxLastMoney" + rowNum).val((amountOfMoney + taxMoney).toFixed(2));
						}else{
							$("#taxLastMoney" + rowNum).val('0.00');
						}
					}else{
						$("#taxUnitPrice" + rowNum).val('0.00');
						$("#taxLastMoney" + rowNum).val('0.00');
					}
				}else if('taxUnitPrice' === showTdByEdit){//含税单价
					//获取含税单价
					var taxUnitPrice = parseFloat(isNull($("#taxUnitPrice" + rowNum).val()) ? 0 : $("#taxUnitPrice" + rowNum).val());
					if(taxUnitPrice == 0){
						$("#taxLastMoney" + rowNum).val('0.00');
						$("#unitPrice" + rowNum).val('0.00');
						$("#amountOfMoney" + rowNum).val('0.00');
						$("#taxMoney" + rowNum).val('0.00');
						$("#taxRate" + rowNum).val('0.00');
						return;
					}
					//输出合计价税,税额,税率
					if(unitPrice != 0){
						if(rkNum != 0 ){
							$("#taxLastMoney" + rowNum).val((taxUnitPrice * rkNum).toFixed(2));
							$("#amountOfMoney" + rowNum).val((unitPrice * rowNum).toFixed(2));
						}else{
							$("#taxLastMoney" + rowNum).val('0.00');
							$("#amountOfMoney" + rowNum).val('0.00');
						}
						$("#taxMoney" + rowNum).val((taxUnitPrice - unitPrice).toFixed(2));
						$("#taxRate" + rowNum).val(((taxUnitPrice / unitPrice - 1) * 100).toFixed(2));
						
					}else{
						$("#taxLastMoney" + rowNum).val('0.00');
						$("#unitPrice" + rowNum).val('0.00');
						$("#amountOfMoney" + rowNum).val('0.00');
						$("#taxMoney" + rowNum).val('0.00');
						$("#taxRate" + rowNum).val('0.00');
					}
				}else if('taxLastMoney' === showTdByEdit){//合计价税
					//获取合计价税
					var taxLastMoney = parseFloat(isNull($("#taxLastMoney" + rowNum).val()) ? 0 : $("#taxLastMoney" + rowNum).val());
					if(taxLastMoney == 0){
						$("#taxUnitPrice" + rowNum).val('0.00');
						$("#unitPrice" + rowNum).val('0.00');
						$("#amountOfMoney" + rowNum).val('0.00');
						$("#taxMoney" + rowNum).val('0.00');
						$("#taxRate" + rowNum).val('0.00');
						return;
					}
					//输出含税单价,税额,税率
					if(rkNum != 0 ){
						if(unitPrice != 0){
							$("#taxUnitPrice" + rowNum).val((taxLastMoney / rkNum).toFixed(2));
							$("#taxMoney" + rowNum).val((taxLastMoney / rkNum - unitPrice).toFixed(2));
							$("#taxRate" + rowNum).val(((taxLastMoney / rkNum / unitPrice - 1 ) * 100).toFixed(2));
							$("#amountOfMoney" + rowNum).val((unitPrice * rkNum).toFixed(2));
						}else{
							$("#amountOfMoney" + rowNum).val('0.00');
							$("#taxMoney" + rowNum).val('0.00');
							$("#taxUnitPrice" + rowNum).val('0.00');
							$("#unitPrice" + rowNum).val('0.00');
						}
					}else{
						$("#taxUnitPrice" + rowNum).val('0.00');
						$("#unitPrice" + rowNum).val('0.00');
						$("#amountOfMoney" + rowNum).val('0.00');
						$("#taxMoney" + rowNum).val('0.00');
						$("#taxRate" + rowNum).val('0.00');
					}
				}
				allPrice += parseFloat($("#amountOfMoney" + rowNum).val());
				taxLastMoneyPrice += parseFloat($("#taxLastMoney" + rowNum).val());
			});
			$("#allPrice").html(allPrice.toFixed(2));
			$("#taxLastMoneyPrice").html(taxLastMoneyPrice.toFixed(2));
			
			//优惠率计算
			var discount = parseFloat(isNull($("#discount").val()) ? 0 : $("#discount").val());
			//输出优惠金额
			$("#discountMoney").val((taxLastMoneyPrice * discount / 100).toFixed(2));
			//输出优惠后的金额
			$("#discountLastMoney").html((taxLastMoneyPrice - (taxLastMoneyPrice * discount / 100)).toFixed(2));
			//输出本次付款
			$("#changeAmount").val((taxLastMoneyPrice - (taxLastMoneyPrice * discount / 100)).toFixed(2));
			//输出欠款金额
			$("#arrears").html('0.00');
		}
		
		//优惠率变化
		$("body").on("input", "#discount", function() {
			//获取价格合计
			var taxLastMoneyPrice = parseFloat(isNull($("#taxLastMoneyPrice").html()) ? 0 : $("#taxLastMoneyPrice").html());
			var discount = parseFloat(isNull($(this).val()) ? 0 : $(this).val());
			//输出优惠金额
			$("#discountMoney").val((taxLastMoneyPrice * discount / 100).toFixed(2));
			//输出优惠后的金额
			$("#discountLastMoney").html((taxLastMoneyPrice - (taxLastMoneyPrice * discount / 100)).toFixed(2));
			//输出本次付款
			$("#changeAmount").val((taxLastMoneyPrice - (taxLastMoneyPrice * discount / 100)).toFixed(2));
		});
		$("body").on("change", "#discount", function() {
			//获取价格合计
			var taxLastMoneyPrice = parseFloat(isNull($("#taxLastMoneyPrice").html()) ? 0 : $("#taxLastMoneyPrice").html());
			var discount = parseFloat(isNull($(this).val()) ? 0 : $(this).val());
			//输出优惠金额
			$("#discountMoney").val((taxLastMoneyPrice * discount / 100).toFixed(2));
			//输出优惠后的金额
			$("#discountLastMoney").html((taxLastMoneyPrice - (taxLastMoneyPrice * discount / 100)).toFixed(2));
			//输出本次付款
			$("#changeAmount").val((taxLastMoneyPrice - (taxLastMoneyPrice * discount / 100)).toFixed(2));
		});
		
		//本次付款变化
		$("body").on("input", "#changeAmount", function() {
			//获取优惠后的金额
			var discountLastMoney = parseFloat(isNull($("#discountLastMoney").html()) ? 0 : $("#discountLastMoney").html());
			var changeAmount = parseFloat(isNull($("#changeAmount").val()) ? 0 : $("#changeAmount").val());
			//输出欠款金额
			$("#arrears").html((discountLastMoney - changeAmount).toFixed(2));
		});
		$("body").on("change", "#changeAmount", function() {
			//获取优惠后的金额
			var discountLastMoney = parseFloat(isNull($("#discountLastMoney").html()) ? 0 : $("#discountLastMoney").html());
			var changeAmount = parseFloat(isNull($("#changeAmount").val()) ? 0 : $("#changeAmount").val());
			//输出欠款金额
			$("#arrears").html((discountLastMoney - changeAmount).toFixed(2));
		});
		
		form.on('submit(formEditBean)', function(data) {
			//表单验证
			if(winui.verifyForm(data.elem)) {
				//获取已选用品数据
				var rowTr = $("#useTable tr");
				if(rowTr.length == 0) {
					winui.window.msg('请选择产品.', {icon: 2, time: 2000});
					return false;
				}
				var tableData = new Array();
				var noError = false; //循环遍历表格数据时，是否有其他错误信息
				$.each(rowTr, function(i, item) {
					//获取行编号
					var rowNum = $(item).attr("trcusid").replace("tr", "");
					if(parseInt($("#rkNum" + rowNum).val()) == 0) {
						$("#rkNum" + rowNum).addClass("layui-form-danger");
						$("#rkNum" + rowNum).focus();
						winui.window.msg('数量不能为0', {icon: 2, time: 2000});
						noError = true;
						return false;
					}
					if(inTableDataArrayByAssetarId($("#materialId" + rowNum).val(), $("#depotId" + rowNum).val(), $("#unitId" + rowNum).val(), tableData)) {
						$("#depotId" + rowNum).addClass("layui-form-danger");
						$("#depotId" + rowNum).focus();
						winui.window.msg('一张单中不允许出现相同当库的产品信息，且单位不能重复.', {icon: 2, time: 2000});
						noError = true;
						return false;
					}
					var row = {
						depotId: $("#depotId" + rowNum).val(),
						materialId: $("#materialId" + rowNum).val(),
						mUnitId: $("#unitId" + rowNum).val(),
						rkNum: $("#rkNum" + rowNum).val(),
						unitPrice: $("#unitPrice" + rowNum).val(),
						taxRate: $("#taxRate" + rowNum).val(),
						taxMoney: $("#taxMoney" + rowNum).val(),
						taxUnitPrice: $("#taxUnitPrice" + rowNum).val(),
						taxLastMoney: $("#taxLastMoney" + rowNum).val(),
						remark: $("#remark" + rowNum).val()
					};
					tableData.push(row);
				});
				if(noError) {
					return false;
				}
				//获取采购费用
				var rowPriceTr = $("#otherPriceTable tr");
				var tablePriceData = new Array();
				var otherMoney = 0;
				$.each(rowPriceTr, function(i, item) {
					//获取行编号
					var rowNum = $(item).attr("trcusid").replace("tr", "");
					var row = {
						inoutitemId: $("#inoutitemId" + rowNum).val(),
						otherPrice: $("#otherPrice" + rowNum).val()
					};
					otherMoney += parseFloat(isNull($("#otherPrice" + rowNum).val()) ? 0 : $("#otherPrice" + rowNum).val());
					tablePriceData.push(row);
				});

				var params = {
					supplierId: $("#supplierId").val(),
					operTime: $("#operTime").val(),
					accountId: $("#accountId").val(),
					payType: $("#payType").val(),
					remark: $("#remark").val(),
					discount: isNull($("#discount").val()) ? "0.00" : $("#discount").val(),
					discountMoney: isNull($("#discountMoney").val()) ? "0.00" : $("#discountMoney").val(),
					changeAmount: isNull($("#changeAmount").val()) ? "0.00" : $("#changeAmount").val(),
					depotheadStr: JSON.stringify(tableData),
					otherMoney: otherMoney.toFixed(2),
					otherMoneyList: JSON.stringify(tablePriceData),
					cgddOrderNum: cgddOrderNum,
					rowId: parent.rowId
				};
				AjaxPostUtil.request({url: reqBasePath + "purchaseorder009", params: params, type: 'json', callback: function(json) {
					if(json.returnCode == 0) {
						parent.layer.close(index);
						parent.refreshCode = '0';
					} else {
						winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
					}
				}});
			}
			return false;
		});
		
		//判断选中的产品是否也在数组中
		function inTableDataArrayByAssetarId(materialId, depotId, unitId, array) {
			var isIn = false;
			$.each(array, function(i, item) {
				if(item.depotId === depotId && item.mUnitId === unitId && item.materialId === materialId) {
					isIn = true;
					return false;
				}
			});
			return isIn;
		}
/*********************** 产品表格操作 start ****************************/
		//新增行
		$("body").on("click", "#addRow", function() {
			addRow();
		});

		//删除行
		$("body").on("click", "#deleteRow", function() {
			deleteRow();
		});

		//新增行
		function addRow() {
			var par = {
				id: "row" + rowNum.toString(), //checkbox的id
				trId: "tr" + rowNum.toString(), //行的id
				depotId: "depotId" + rowNum.toString(), //仓库id
				materialId: "materialId" + rowNum.toString(), //产品id
				unitId: "unitId" + rowNum.toString(), //规格id
				currentTock: "currentTock" + rowNum.toString(), //库存id
				rkNum: "rkNum" + rowNum.toString(), //数量id
				unitPrice: "unitPrice"  + rowNum.toString(), //单价id
				amountOfMoney: "amountOfMoney"  + rowNum.toString(), //金额id
				taxRate: "taxRate"  + rowNum.toString(), //税率id
				taxMoney: "taxMoney"  + rowNum.toString(), //税额id
				taxUnitPrice: "taxUnitPrice"  + rowNum.toString(), //含税单价id
				taxLastMoney: "taxLastMoney"  + rowNum.toString(), //含税合计id
				remark: "remark" + rowNum.toString() //备注id
			};
			$("#useTable").append(getDataUseHandlebars(usetableTemplate, par));
			//赋值给仓库
			$("#" + "depotId" + rowNum.toString()).html(depotHtml);
			//赋值给产品
			$("#" + "materialId" + rowNum.toString()).html(materialHtml);
			form.render('select');
			form.render('checkbox');
			rowNum++;
			//设置根据某列变化的颜色
			$("." + showTdByEdit).parent().css({'background-color': '#e6e6e6'});
		}

		//删除行
		function deleteRow() {
			var checkRow = $("#useTable input[type='checkbox'][name='tableCheckRow']:checked");
			if(checkRow.length > 0) {
				$.each(checkRow, function(i, item) {
					$(item).parent().parent().remove();
				});
			} else {
				winui.window.msg('请选择要删除的行', {icon: 2, time: 2000});
			}
		}
/*********************** 产品表格操作 end ****************************/

/*********************** 采购费用表格操作 start ****************************/
		
		//采购费用变化
		$("body").on("input", ".otherPrice", function() {
			//计算价格
			calculationPrice();
		});
		$("body").on("change", ".otherPrice", function() {
			//计算价格
			calculationPrice();
		});
		
		//计算采购费用总价格
		function calculationPrice(){
			var rowTr = $("#otherPriceTable tr");
			var allPrice = 0;
			$.each(rowTr, function(i, item) {
				//获取行坐标
				var rowNum = $(item).attr("trcusid").replace("tr", "");
				//获取
				var otherPrice = parseFloat(isNull($("#otherPrice" + rowNum).val()) ? 0 : $("#otherPrice" + rowNum).val());
				allPrice += otherPrice;
			});
			$("#otherPriceTotal").html("费用合计：" + allPrice.toFixed(2));
		}
		
		var priceNum = 1;
		//新增行
		$("body").on("click", "#addPriceRow", function() {
			addPriceRow();
		});

		//删除行
		$("body").on("click", "#deletePriceRow", function() {
			deletePriceRow();
		});

		//新增行
		function addPriceRow() {
			var par = {
				id: "row" + priceNum.toString(), //checkbox的id
				trId: "tr" + priceNum.toString(), //行的id
				inoutitemId: "inoutitemId" + priceNum.toString(), //支出项目id
				otherPrice: "otherPrice" + priceNum.toString() //金额id
			};
			$("#otherPriceTable").append(getDataUseHandlebars(otherTemplate, par));
			//赋值给仓库
			$("#" + "inoutitemId" + priceNum.toString()).html(inoutitemHtml);
			form.render('select');
			form.render('checkbox');
			priceNum++;
		}

		//删除行
		function deletePriceRow() {
			var checkRow = $("#otherPriceTable input[type='checkbox'][name='tableCheckRow']:checked");
			if(checkRow.length > 0) {
				$.each(checkRow, function(i, item) {
					$(item).parent().parent().remove();
				});
			} else {
				winui.window.msg('请选择要删除的行', {icon: 2, time: 2000});
			}
			//计算价格
			calculationPrice();
		}
/*********************** 采购费用表格操作 end ****************************/
		
		$("body").on("click", "#cancle", function() {
			parent.layer.close(index);
		});
	});
});