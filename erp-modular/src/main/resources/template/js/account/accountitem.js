layui.config({
    base: basePath,
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
}).define(['window', 'table', 'jquery', 'winui'], function (exports) {
    winui.renderColor();
    var $ = layui.$,
        table = layui.table;
    //表格渲染
    table.render({
        id: 'messageTable',
        elem: '#messageTable',
        method: 'post',
        url: reqBasePath + 'account008',
        where: {rowId:parent.rowId},
        even: true,  //隔行变色
        page: true,
        limits: [8, 16, 24, 32, 40, 48, 56],
        limit: 8,
        cols: [[
            { title: '序号', type: 'numbers'},
            { field: 'number', title: '票据号', align: 'center',width: 200},
            { field: 'type', title: '类型', align: 'center',width: 150, templet: function (d) {
                if(d.type == '1'){
                    return "<span class='state-down'>出库</span>";
                }else if(d.type == '2'){
                    return "<span class='state-up'>入库</span>";
                }else{
                    return "<span class='state-error'>参数错误</span>";
                }
            }},
            { field: 'subType', title: '出入库分类', align: 'left',width: 150, templet: function(d){
                if(d.subType == '1'){
                    return "<span class='state-up'>入库</span>";
                }else if(d.subType == '2'){
                    return "<span class='state-up'>销售退货</span>";
                }else if(d.subType == '3'){
                    return "<span class='state-up'>零售退货</span>";
                }else if(d.subType == '4'){
                    return "<span class='state-up'>其他入库</span>";
                }else if(d.subType == '5'){
                    return "<span class='state-down'>销售</span>";
                }else if(d.subType == '6'){
                    return "<span class='state-down'>采购退货</span>";
                }else if(d.subType == '7'){
                    return "<span class='state-down'>调拨</span>";
                }else if(d.subType == '8'){
                    return "<span class='state-down'>零售</span>";
                }else if(d.subType == '9'){
                    return "<span class='state-down'>其他出库</span>";
                }else if(d.subType == '10'){
                    return "采购单";
                }else{
                    return "<span class='state-error'>参数错误</span>";
                }
            }},
            { field: 'status', title: '状态', align: 'left',width: 150, templet: function (d) {
                    if(d.status == '0'){
                        return "<span class='state-error'>未审核</span>";
                    }else if(d.status == '1'){
                        return "<span class='state-down'>审核中</span>";
                    }else if(d.status == '2'){
                        return "<span class='state-up'>审核通过</span>";
                    }else if(d.status == '3'){
                        return "<span class='state-error'>审核拒绝</span>";
                    }else if(d.status == '4'){
                        return "<span class='state-down'>已转采购|销售</span>";
                    }
                }},
            { field: 'totalPrice', title: '合计金额', align: 'left',width: 100},
            { field: 'payType', title: '付款类型', align: 'center',width: 100, templet: function (d) {
                if(d.payType == '1'){
                    return "<span class='state-up'>现金</span>";
                }else if(d.payType == '2'){
                    return "<span class='state-down'>记账</span>";
                }else{
                    return "<span class='state-error'>其他</span>";
                }
            }},
            { field: 'remark', title: '备注', align: 'left',width: 200},
            { field: 'operTime', title: '出入库日期', align: 'center', width: 180 },
        ]]
    });
    exports('accountitem', {});
});
