{{#bean}}
    <div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">类型名称<i class="red">*</i></label>
        <div class="layui-input-block">
            <input type="text" id="typeName" name="typeName" win-verify="required" placeholder="请输入产品类型名称" class="layui-input" value="{{name}}"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">类型级别<i class="red">*</i></label>
        <div class="layui-input-block ver-center">
            {{pId}}
        </div>
    </div>
    <div class="layui-form-item layui-hide layui-col-xs12" id="parentIdBox">
        <label class="layui-form-label">上级类型<i class="red">*</i></label>
        <div class="layui-input-block ver-center">
        	{{pName}}
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