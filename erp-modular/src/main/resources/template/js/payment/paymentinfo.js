
layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window'
}).define(['window', 'table', 'jquery', 'winui'], function (exports) {
    winui.renderColor();
    layui.use(['form'], function (form) {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        var $ = layui.$;

        var beanTemplate = $("#beanTemplate").html();

        showGrid({
            id: "showForm",
            url: reqBasePath + "payment006",
            params: {rowId: parent.rowId},
            pagination: false,
            template: beanTemplate,
            ajaxSendAfter:function(json){

                form.render();
            }
        });

    });
});