
layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui', 'fileUpload', 'dtree'], function (exports) {
    winui.renderColor();
    layui.use(['form'], function (form) {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        var $ = layui.$,
            dtree = layui.dtree;
        form.render();
        form.on('submit(formAddBean)', function (data) {
            //表单验证
            if (winui.verifyForm(data.elem)) {


                if(isNull($.trim($("#houseName").val()))){
                    winui.window.msg('请输入仓库名称', {icon: 2,time: 2000});
                    return false;
                }

                var params = {
                    houseName: $.trim($("#houseName").val()),
                    address: $.trim($("#address").val()),
                    warehousing: $.trim($("#warehousing").val()),
                    truckage: $.trim($("#truckage").val()),
                    is_default: $("input[name='is_default']:checked").val(),
                    principal: $.trim($("#principal").val()),
                    remark: $.trim($("#remark").val()),
                };
                AjaxPostUtil.request({url:reqBasePath + "storehouse002", params:params, type:'json', callback:function(json){
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
            warehousing: [
                /(^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$)/  //正则表达式
                ,'请输入正确的金额, 可保留小数点后两位'  //提示信息
            ],
            truckage: [
                /(^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$)/  //正则表达式
                ,'请输入正确的金额, 可保留小数点后两位'  //提示信息
            ],
        });

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});