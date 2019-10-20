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
        var rowNum = 1; //表格的序号
        var initemHtml = "";//收支项目

        var usetableTemplate = $("#usetableTemplate").html();
        var selOption = getFileContent('tpl/template/select-option.tpl');

        //单据时间
        laydate.render({
            elem: '#billTime',
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
                    //初始化往来单位
                    initSupplierHtml();
                } else {
                    winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
                }
            }});
        }

        //初始化往来单位
        function initSupplierHtml() {
            AjaxPostUtil.request({url: reqBasePath + "supplier010", params: {}, type: 'json', callback: function(json) {
                    if(json.returnCode == 0) {
                        //加载往来单位数据
                        $("#organId").html(getDataUseHandlebars(selOption, json));
                        //初始化收入项目
                        initItemHtml();
                    } else {
                        winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
                    }
                }});
        }

        //初始化收入项目
        function initItemHtml() {
            AjaxPostUtil.request({url: reqBasePath + "inoutitem008", params: {}, type: 'json', callback: function(json) {
                if(json.returnCode == 0) {
                    //加载收入项目数据
                    initemHtml = getDataUseHandlebars(selOption, json);
                    //渲染
                    form.render();
                    //初始化一行数据
                    addRow();
                } else {
                    winui.window.msg(json.returnMessage, {icon: 2, time: 2000});
                }
            }});
        }

        //数量变化
        $("body").on("input", ".rkMoney", function() {
            //计算价格
            calculatedTotalPrice();
        });
        $("body").on("change", ".rkMoney", function() {
            calculatedTotalPrice();
        });

        //计算总价
        function calculatedTotalPrice(){
            var rowTr = $("#useTable tr");
            var allPrice = 0;
            $.each(rowTr, function(i, item) {
                //获取行坐标
                var rowNum = $(item).attr("trcusid").replace("tr", "");
                //获取金额
                var initemMoney = parseFloat(isNull($("#initemMoney" + rowNum).val()) ? "0" : $("#initemMoney" + rowNum).val());
                //输出金额
                $("#initemMoney" + rowNum).html((initemMoney).toFixed(2));
                allPrice += initemMoney;
            });
            $("#allPrice").html(allPrice.toFixed(2));
        }

        form.on('submit(formAddBean)', function(data) {
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
                    if(inTableDataArrayByAssetarId($("#initemId" + rowNum).val(), tableData)) {
                        $("#initemId" + rowNum).addClass("layui-form-danger");
                        $("#initemId" + rowNum).focus();
                        winui.window.msg('一张单中不允许出现相同收支项目信息.', {icon: 2, time: 2000});
                        noError = true;
                        return false;
                    }
                    var row = {
                        initemId: $("#initemId" + rowNum).val(),
                        initemMoney: $("#initemMoney" +rowNum).val(),
                        rkNum: $("#rkNum" + rowNum).val(),
                        remark: $("#remark" + rowNum).val()
                    };
                    tableData.push(row);
                });
                if(noError) {
                    return false;
                }

                var params = {
                    organId: $("#organId").val(),
                    // handsPersonId: $("#handsPersonId").val(),
                    handsPersonId: '000',
                    billTime: $("#billTime").val(),
                    accountId: $("#accountId").val(),
                    payType: $("#payType").val(),
                    remark: $("#remark").val(),
                    allPrice: allPrice,
                    changeAmount: $("#changeAmount").val(),
                    initemStr: JSON.stringify(tableData)
                };
                AjaxPostUtil.request({url: reqBasePath + "income002", params: params, type: 'json', callback: function(json) {
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

        //判断选中的收支项目是否也在数组中
        function inTableDataArrayByAssetarId(initemId, array) {
            var isIn = false;
            $.each(array, function(i, item) {
                if(item.initemId === initemId) {
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
                initemId: "initemId" + rowNum.toString(), //收入项目id
                initemMoney: "initemMoney"  + rowNum.toString(), //金额id
                remark: "remark" + rowNum.toString() //备注id
            };
            $("#useTable").append(getDataUseHandlebars(usetableTemplate, par));
            //赋值给收支项目
            $("#" + "initemId" + rowNum.toString()).html(initemHtml);
            form.render('select');
            form.render('checkbox');
            rowNum++;
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