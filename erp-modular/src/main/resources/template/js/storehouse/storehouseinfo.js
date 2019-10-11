
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
        AjaxPostUtil.request({url:reqBasePath + "storehouse007", params:params, type:'json', callback:function(json){
            if(json.returnCode == 0){
                $("#houseName").html(json.bean.houseName);
                $("#address").html(json.bean.address);
                $("#warehousing").html(json.bean.warehousing);
                $("#truckage").html(json.bean.truckage);
                $("#principal").html(json.bean.principal);
                $("#isDefault").html(json.bean.isDefault == "1" ? "是" : "否");
                $("#remark").html(json.bean.remark);
            }else{
                winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
            }
        }});

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});