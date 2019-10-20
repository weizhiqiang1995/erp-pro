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
		var depotHtml = "", materialHtml = "";//仓库
		var tockObject = new Array();//根据仓库和规格id查询出来的对应库存信息

		var usetableTemplate = $("#usetableTemplate").html();
		var beanTemplate = $("#beanTemplate").html();
		var selOption = getFileContent('tpl/template/select-option.tpl');
		
 		//加载单据数据
 		var orderObject = [];
		showGrid({
		 	id: "showForm",
		 	url: reqBasePath + "purchaseorder004",
		 	params: {rowId: parent.rowId},
		 	pagination: false,
		 	template: beanTemplate,
		 	ajaxSendAfter:function(json){
		 		//单据时间
		 		laydate.render({ 
		 		  elem: '#operTime',
		 		  type: 'datetime',
		 	 	  trigger: 'click'
		 		});
		 		orderObject = json;
		 		initAccountHtml();
		 	}
		});
 		
 		//初始化账户
		function initAccountHtml() {
			AjaxPostUtil.request({url: reqBasePath + "account009", params: {}, type: 'json', callback: function(json) {
				if(json.returnCode == 0) {
					//加载账户数据
					$("#accountId").html(getDataUseHandlebars(selOption, json)); 
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
					//渲染数据到页面
					initDataToShow();
				} else {
					winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
				}
			}});
		}
		
		//渲染数据到页面
		function initDataToShow(){
			$("#supplierId").val(orderObject.bean.supplierId);//供应商
			$("#accountId").val(orderObject.bean.accountId);//账户
			$("#payType").val(orderObject.bean.payType);//付款类型
			//渲染列表项
			$.each(orderObject.bean.norms, function(i, item){
				addRow();
				$.each(material, function(j, bean) {
					if(item.materialId == bean.id){
						$("#unitId" + (rowNum - 1)).html(getDataUseHandlebars(selOption, {rows: bean.unitList}));
						$("#unitId" + (rowNum - 1)).val(item.mUnitId);//单位回显
						return false;
					}
				});
				$("#depotId" + (rowNum - 1)).val(item.depotId);//仓库回显
				$("#materialId" + (rowNum - 1)).val(item.materialId);//产品回显
				$("#currentTock" + (rowNum - 1)).html(item.currentTock);//库存回显
				$("#rkNum" + (rowNum - 1)).val(item.operNum);//数量回显
				$("#unitPrice" + (rowNum - 1)).val(item.unitPrice.toFixed(2));//单价回显
				$("#amountOfMoney" + (rowNum - 1)).val(item.allPrice.toFixed(2));//金额回显
				$("#remark" + (rowNum - 1)).val(item.remark);//备注回显
				//设置标识
				$("tr[trcusid='tr" + (rowNum - 1) + "']").attr("thisid", item.id);
			});
			//渲染
 			form.render();
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
									$("#amountOfMoney" + thisRowNum).val((rkNum * parseFloat(bean.estimatePurchasePrice)).toFixed(2));//金额
									return false;
								}
							});
						}else{//不是多单位
							var firstItem = item.unitList[0];
							$("#unitId" + thisRowNum).val(firstItem.id);
							$("#unitPrice" + thisRowNum).val(firstItem.estimatePurchasePrice.toFixed(2));//单价
							$("#amountOfMoney" + thisRowNum).val((rkNum * parseFloat(firstItem.estimatePurchasePrice)).toFixed(2));//金额
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
								$("#amountOfMoney" + thisRowNum).val((rkNum * parseFloat(bean.estimatePurchasePrice)).toFixed(2));//金额
								return false;
							}
						});
						return false;
					}
				});
			} else {
				$("#unitPrice" + thisRowNum).val("0.00");//重置单价为空
				$("#amountOfMoney" + thisRowNum).val("0.00");//重置金额为空
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
				$("#currentTock" + rowNum).html("");
			}
		}
		
		var showTdByEdit = 'rkNum';//根据那一列的值进行变化,默认根据数量
		//数量变化
		$("body").on("input", ".rkNum, .unitPrice, .amountOfMoney", function() {
			if($(this).attr("class").replace("layui-input change-input ", "") != showTdByEdit){
				showTdByEdit = $(this).attr("class").replace("layui-input change-input ", "");
				$(".change-input").parent().removeAttr("style");
				$("." + showTdByEdit).parent().css({'background-color': '#e6e6e6'});
			}
			calculatedTotalPrice();
		});
		$("body").on("change", ".rkNum, .unitPrice, .amountOfMoney", function() {
			if($(this).attr("class").replace("layui-input change-input ", "") != showTdByEdit){
				showTdByEdit = $(this).attr("class").replace("layui-input change-input ", "");
				$(".change-input").parent().removeAttr("style");
				$("." + showTdByEdit).parent().css({'background-color': '#e6e6e6'});
			}
			calculatedTotalPrice();
		});
		
		//计算总价
		function calculatedTotalPrice(){
			var rowTr = $("#useTable tr");
			var allPrice = 0;
			$.each(rowTr, function(i, item) {
				//获取行坐标
				var rowNum = $(item).attr("trcusid").replace("tr", "");
				//获取数量
				var rkNum = parseInt(isNull($("#rkNum" + rowNum).val()) ? "0" : $("#rkNum" + rowNum).val());
				//获取单价
				var unitPrice = parseFloat(isNull($("#unitPrice" + rowNum).val()) ? "0" : $("#unitPrice" + rowNum).val());
				//获取单价
				var amountOfMoney = parseFloat(isNull($("#amountOfMoney" + rowNum).val()) ? "0" : $("#amountOfMoney" + rowNum).val());
				if("rkNum" === showTdByEdit){//数量
					//输出金额
					$("#amountOfMoney" + rowNum).val((rkNum * unitPrice).toFixed(2));
				}else if("unitPrice" === showTdByEdit){//单价
					//输出金额
					$("#amountOfMoney" + rowNum).val((rkNum * unitPrice).toFixed(2));
				}else if("amountOfMoney" === showTdByEdit){//金额
					//输出单价
					$("#unitPrice" + rowNum).val((amountOfMoney / rkNum).toFixed(2));
				}
				allPrice += parseFloat($("#amountOfMoney" + rowNum).val());
			});
			$("#allPrice").html(allPrice.toFixed(2));
		}

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
						estimatePurchasePrice: $("#unitPrice" + rowNum).val(),
						thisId: isNull($(item).attr("thisid")) ? "" : $(item).attr("thisid"),
						remark: $("#remark" + rowNum).val()
					};
					tableData.push(row);
				});
				if(noError) {
					return false;
				}

				var params = {
					supplierId: $("#supplierId").val(),
					operTime: $("#operTime").val(),
					accountId: $("#accountId").val(),
					payType: $("#payType").val(),
					remark: $("#remark").val(),
					depotheadStr: JSON.stringify(tableData),
					rowId: parent.rowId
				};
				AjaxPostUtil.request({url: reqBasePath + "purchaseorder005", params: params, type: 'json', callback: function(json) {
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

		$("body").on("click", "#cancle", function() {
			parent.layer.close(index);
		});
	});
});