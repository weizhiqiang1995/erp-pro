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
                var params = {
                    accountName: $("#accountName").val(),
                    serialNo: $("#serialNo").val(),
                    initialAmount: $("#initialAmount").val(),
                    isDefault: $("input[name='isDefault']:checked").val(),
                    remark: $("#remark").val(),
                };
                AjaxPostUtil.request({url:reqBasePath + "account002", params:params, type:'json', callback:function(json){
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

        $("body").on("change", "#initialAmount", function () {
            $("#currentAmount").val($(this).val());
        });

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});