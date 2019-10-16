{{#bean}}
    <div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">名称<i class="red">*</i></label>
        <div class="layui-input-block">
            <input type="text" id="accountName" name="accountName" value="{{accountName}}" win-verify="required" placeholder="请输入账户名称" class="layui-input" maxlength="25"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">编号<i class="red">*</i></label>
        <div class="layui-input-block">
            <input type="text" id="serialNo" name="serialNo" value="{{serialNo}}" placeholder="请输入编号" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">期初金额<i class="red">*</i></label>
        <div class="layui-input-block">
            <input type="text" id="initialAmount" name="initialAmount" value="{{initialAmount}}" win-verify="required|money" placeholder="请输入期初金额" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">当前余额</label>
        <div class="layui-input-block">
            <input type="text" id="currentAmount" name="currentAmount" value="{{currentAmount}}" win-verify="required|money" placeholder="0" class="layui-input" readonly="readonly"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">是否默认<i class="red">*</i></label>
        <div class="layui-input-block winui-radio">
            <input type="radio" name="isDefault" value="0" title="否" checked="checked"/>
            <input type="radio" name="isDefault" value="1" title="是"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="remark" name="remark" placeholder="请输入备注信息" maxlength="200" class="layui-textarea" style="height: 100px;">{{remark}}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs12">
        <div class="layui-input-block">
            <button class="winui-btn" id="cancle">取消</button>
            <button class="winui-btn" lay-submit lay-filter="formEditBean">保存</button>
        </div>
    </div>
{{/bean}}