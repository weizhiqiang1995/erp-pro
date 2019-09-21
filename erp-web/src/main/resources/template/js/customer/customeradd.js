
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
        form.render();
        form.on('submit(formAddBean)', function (data) {
            //表单验证
            if (winui.verifyForm(data.elem)) {
                if(isNull($("#customerName").val())){
                    winui.window.msg('请输入客户名称', {icon: 2,time: 2000});
                    return false;
                }
                var params = {
                    customerName: $("#customerName").val(),
                    contacts: $("#contacts").val(),
                    phonenum: $("#phonenum").val(),
                    email: $("#email").val(),
                    description: $("#description").val(),
                    advanceIn: $("#advanceIn").val(),
                    beginNeedGet: $("#beginNeedGet").val(),
                    beginNeedPay: $("#beginNeedPay").val(),
                    allNeedGet: $("#allNeedGet").val(),
                    allNeedPay: $("#allNeedPay").val(),
                    fax: $("#fax").val(),
                    telephone: $("#telephone").val(),
                    address: $("#address").val(),
                    taxNum: $("#taxNum").val(),
                    bankName: $("#bankName").val(),
                    accountNumber: $("#accountNumber").val(),
                    taxRate: $("#taxRate").val(),
                };
                AjaxPostUtil.request({url:reqBasePath + "customer002", params:params, type:'json', callback:function(json){
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

        //自定义表单验证（金额）
        form.verify({
            advanceIn : function(value, item){
                var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/;//正则表达式
                if(!isNull(value) && !reg.test(value)){
                    return "请输入正确的金额, 可保留小数点后两位";
                }
            },
            beginNeedGet : function(value, item){
                var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/;
                if(!isNull(value) && !reg.test(value)){
                    return "请输入正确的金额, 可保留小数点后两位";
                }
            },
            beginNeedPay : function(value, item){
                var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/;
                if(!isNull(value) && !reg.test(value)){
                    return "请输入正确的金额, 可保留小数点后两位";
                }
            },
            allNeedGet : function(value, item){
                var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/;
                if(!isNull(value) && !reg.test(value)){
                    return "请输入正确的金额, 可保留小数点后两位";
                }
            },
            allNeedPay : function(value, item){
                var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/;
                if(!isNull(value) && !reg.test(value)){
                    return "请输入正确的金额, 可保留小数点后两位";
                }
            },
        });

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});