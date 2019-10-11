
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
        var params = {
            rowId:parent.rowId
        };
        AjaxPostUtil.request({url:reqBasePath + "customer008", params:params, type:'json', callback:function(json){
            if(json.returnCode == 0){
                $("#customerName").html(json.bean.customerName);
                $("#contacts").html(json.bean.contacts);
                $("#phonenum").html(json.bean.phonenum);
                $("#email").html(json.bean.email);
                $("#telephone").html(json.bean.telephone);
                $("#fax").html(json.bean.fax);
                $("#advanceIn").html(json.bean.advanceIn);
                $("#beginNeedGet").html(json.bean.beginNeedGet);
                $("#beginNeedPay").html(json.bean.beginNeedPay);
                $("#allNeedGet").html(json.bean.allNeedGet);
                $("#allNeedPay").html(json.bean.allNeedPay);
                $("#taxNum").html(json.bean.taxNum);
                $("#taxRate").html(json.bean.taxRate);
                $("#bankName").html(json.bean.bankName);
                $("#accountNumber").html(json.bean.accountNumber);
                $("#address").html(json.bean.address);
                $("#enabled").html(json.bean.enabled == "1" ? " 启用" : "禁用");
                $("#description").html(json.bean.description);
            }else{
                winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
            }
        }});

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});