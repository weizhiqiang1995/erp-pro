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
            url: reqBasePath + "account003",
            params: {rowId:parent.rowId},
            pagination: false,
            template: getFileContent('tpl/account/accounteditTemplate.tpl'),
            ajaxSendLoadBefore: function(hdb){

            },
            ajaxSendAfter:function(json){
                //设置是否默认
                $("input:radio[name=isDefault][value=" + json.bean.isDefault + "]").attr("checked", true);
                form.render();
                form.on('submit(formEditBean)', function (data) {
                    //表单验证
                    if (winui.verifyForm(data.elem)) {
                        var params = {
                            rowId: parent.rowId,
                            accountName: $("#accountName").val(),
                            serialNo: $("#serialNo").val(),
                            initialAmount: $("#initialAmount").val(),
                            isDefault: $("input[name='isDefault']:checked").val(),
                            remark: $("#remark").val(),
                        };
                        AjaxPostUtil.request({url:reqBasePath + "account005", params:params, type:'json', callback:function(json){
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