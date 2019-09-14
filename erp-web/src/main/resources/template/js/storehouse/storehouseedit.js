
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
        showGrid({
            id: "showForm",
            url: reqBasePath + "storehouse003",
            params: {rowId:parent.rowId},
            pagination: false,
            template: getFileContent('tpl/storehouse/storehouseeditTemplate.tpl'),
            ajaxSendLoadBefore: function(hdb){
                hdb.registerHelper("compare1", function(v1, options){
                    if(isNull(v1)){
                        return path + "assets/img/uploadPic.png";
                    }else{
                        return basePath + v1;
                    }
                });
            },
            ajaxSendAfter:function(json){
                //设置是否默认
                $("#houseName").val(json.bean.houseName);
                $("#address").val(json.bean.address);
                $("#warehousing").val(json.bean.warehousing);
                $("#truckage").val(json.bean.truckage);
                $("#principal").val(json.bean.principal);
                $("#remark").val(json.bean.remark);
                $("input:radio[name=is_default][value=" + json.bean.is_default + "]").attr("checked", true);


                form.render();
                form.on('submit(formEditBean)', function (data) {
                    //表单验证
                    if (winui.verifyForm(data.elem)) {
                        if(isNull($.trim($("#houseName").val()))){
                            winui.window.msg('请输入仓库名称', {icon: 2,time: 2000});
                            return false;
                        }
                        var params = {
                            rowId: parent.rowId,
                            houseName: $.trim($("#houseName").val()),
                            address: $.trim($("#address").val()),
                            warehousing: $.trim($("#warehousing").val()),
                            truckage: $.trim($("#truckage").val()),
                            is_default: $("input[name='is_default']:checked").val(),
                            principal: $.trim($("#principal").val()),
                            remark: $.trim($("#remark").val()),
                        };

                        AjaxPostUtil.request({url:reqBasePath + "storehouse005", params:params, type:'json', callback:function(json){
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
                    warehousing : function(value, item){
                        var reg = /(^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$)/;//正则表达式
                        if(!reg.test(value)){
                            return "请输入正确的金额, 可保留小数点后两位";
                        }
                    },
                    truckage : function(value, item){
                        var reg = /(^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$)/;
                        if(!reg.test(value)){
                            return "请输入正确的金额, 可保留小数点后两位";
                        }
                    },

                });
            }
        });

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});