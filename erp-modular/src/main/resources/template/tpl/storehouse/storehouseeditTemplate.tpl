{{#bean}}
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">仓库名称<i class="red">*</i></label>
        <div class="layui-input-block">
            <input type="text" id="houseName" name="houseName" win-verify="required" placeholder="请输入仓库名称" class="layui-input" maxlength="25" value="{{houseName}}"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">仓库地址</label>
        <div class="layui-input-block">
            <input type="text" id="address" name="address" placeholder="请输入仓库地址" class="layui-input" value="{{address}}"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">仓储费</label>
        <div class="layui-input-block">
            <input type="text" id="warehousing" name="warehousing" lay-verify="warehousing" placeholder="请输入仓储费" class="layui-input" value="{{warehousing}}"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">搬运费</label>
        <div class="layui-input-block">
            <input type="text" id="truckage" name="truckage" lay-verify="truckage" placeholder="请输入搬运费" class="layui-input" value="{{truckage}}"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">负责人</label>
        <div class="layui-input-block">
            <input type="text" id="principal" name="principal" placeholder="请输入负责人名称" class="layui-input" value="{{principal}}"/>
        </div>
    </div>
    <div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">是否默认<i class="red">*</i></label>
        <div class="layui-input-block winui-radio">
            <input type="radio" name="isDefault" value="1" title="是" />
            <input type="radio" name="isDefault" value="2" title="否" />
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