{{#bean}}
	<div class="layui-form-item layui-col-xs12">
		<span class="hr-title">基础信息</span><hr>
	</div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">产品名称：</label>
        <div class="layui-input-block ver-center">
        	{{materialName}}
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">型号：</label>
        <div class="layui-input-block ver-center">
            {{model}}
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label">一级类型：</label>
        <div class="layui-input-block ver-center">
            {{categoryName}}
        </div>
    </div>
    <div class="layui-form-item layui-col-xs6">
        <label class="layui-form-label ver-center">二级类型：</label>
        <div class="layui-input-block ver-center">
            {{categorySecName}}
        </div>
    </div>
    <div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">备注：</label>
        <div class="layui-input-block ver-center">
        	{{remark}}
        </div>
    </div>
    <div class="layui-form-item layui-col-xs12">
		<span class="hr-title">价格信息</span><hr>
	</div>
	<div class="layui-form-item layui-col-xs12">
        <label class="layui-form-label">单位类型：</label>
        <div class="layui-input-block ver-center">
            {{unitType}}
        </div>
    </div>
{{/bean}}