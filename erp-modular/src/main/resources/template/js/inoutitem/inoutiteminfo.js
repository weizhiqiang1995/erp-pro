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
        var simpleTemplate = $("#simpleTemplate").html();
        showGrid({
            id: "showForm",
            url: reqBasePath + "inoutitem006",
            params: {rowId: parent.rowId},
            pagination: false,
            template: simpleTemplate,
            ajaxSendAfter:function(json){
                $("#inoutitemType").html((json.bean.inoutitemType == "1") ? '<span class="state-up">收入</span>' : '<span class="state-down">支出</span>');
                form.render();
            }
        });

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});