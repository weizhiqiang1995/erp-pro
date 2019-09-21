
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
            url: reqBasePath + "member003",
            params: {rowId:parent.rowId},
            pagination: false,
            template: getFileContent('tpl/member/membereditTemplate.tpl'),
            ajaxSendLoadBefore: function(hdb){},
            ajaxSendAfter:function(json){
                form.render();
                form.on('submit(formEditBean)', function (data) {
                    //表单验证
                    if (winui.verifyForm(data.elem)) {
                        if(isNull($("#memberName").val())){
                            winui.window.msg('请输入会员名称', {icon: 2,time: 2000});
                            return false;
                        }
                        var params = {
                            rowId: parent.rowId,
                            memberName: $("#memberName").val(),
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
                        console.log(params)
                        AjaxPostUtil.request({url:reqBasePath + "member005", params:params, type:'json', callback:function(json){
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
            }
        });

        $("body").on("click", "#cancle", function(){
            parent.layer.close(index);
        });
    });
});