layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui'], function (exports) {
    winui.renderColor();
    layui.use(['form'], function (form) {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        var $ = layui.$;
        showGrid({
            id: "showForm",
            url: reqBasePath + "customer003",
            params: {rowId:parent.rowId},
            pagination: false,
            template: getFileContent('tpl/customer/customereditTemplate.tpl'),
            ajaxSendLoadBefore: function(hdb){},
            ajaxSendAfter:function(json){
                form.render();
                form.on('submit(formEditBean)', function (data) {
                    //表单验证
                    if (winui.verifyForm(data.elem)) {
                        var params = {
                            rowId: parent.rowId,
                            customerName: $("#customerName").val(),
                            contacts: $("#contacts").val(),
                            phonenum: $("#phonenum").val(),
                            email: $("#email").val(),
                            description: $("#description").val(),
                            description: $("#description").val(),
                            advanceIn: isNull($("#advanceIn").val()) ? '0' : $("#advanceIn").val(),
                            beginNeedGet: isNull($("#beginNeedGet").val()) ? '0' : $("#beginNeedGet").val(),
                            beginNeedPay: isNull($("#beginNeedPay").val()) ? '0' : $("#beginNeedPay").val(),
                            allNeedGet: isNull($("#allNeedGet").val()) ? '0' : $("#allNeedGet").val(),
                            allNeedPay: isNull($("#allNeedPay").val()) ? '0' : $("#allNeedPay").val(),
                            fax: $("#fax").val(),
                            telephone: $("#telephone").val(),
                            address: $("#address").val(),
                            taxNum: $("#taxNum").val(),
                            bankName: $("#bankName").val(),
                            accountNumber: $("#accountNumber").val(),
                            taxRate: isNull($("#taxRate").val()) ? '0' : $("#taxRate").val(),
                        };
                        AjaxPostUtil.request({url:reqBasePath + "customer005", params:params, type:'json', callback:function(json){
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
            }
        });

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});