
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
            url: reqBasePath + "inoutitem003",
            params: {rowId:parent.rowId},
            pagination: false,
            template: getFileContent('tpl/inoutitem/inoutitemeditTemplate.tpl'),
            ajaxSendLoadBefore: function(hdb){},
            ajaxSendAfter:function(json){
                // $("#inoutitemType").val(json.bean.inoutitemType);
                form.render();
                form.on('submit(formEditBean)', function (data) {
                    //表单验证
                    if (winui.verifyForm(data.elem)) {
                        var params = {
                            rowId: parent.rowId,
                            inoutitemName: $("#inoutitemName").val(),
                            inoutitemType: $("#inoutitemType").val(),
                            remark: $("#remark").val(),
                        };
                        AjaxPostUtil.request({url:reqBasePath + "inoutitem005", params:params, type:'json', callback:function(json){
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