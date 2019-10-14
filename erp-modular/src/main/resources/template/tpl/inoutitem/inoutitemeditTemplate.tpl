{{#bean}}
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">名称<i class="red">*</i></label>
        <div class="layui-input-block">
            <input type="text" id="inoutitemName" name="inoutitemName" value="{{inoutitemName}}" win-verify="required" placeholder="请输入名称" class="layui-input" maxlength="25"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <select id="inoutitemType" name="inoutitemType" class="menuSysWinId" lay-search="">
                <option value="1">收入</option>
                <option value="2">支出</option>
            </select>
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