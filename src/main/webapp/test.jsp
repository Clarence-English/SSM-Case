<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="http://localhost:8080/ssm/">
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/layui/css/layui.css">
</head>
<body>
<!-- 你的HTML代码 -->
<table id="demo" lay-filter="test"></table>

<script src="static/layui/layui.js"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">
        <i class="layui-icon">&#xe642;</i>编辑
    </a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="remove">
        <i class="layui-icon">&#xe640;</i>删除
    </a>
</script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">
            <i class="layui-icon">&#xe654;</i>新增
        </button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="deleteBatches">
            <i class="layui-icon">&#xe640;</i>批量删除
        </button>
    </div>
</script>
<script>
    //layui模块的使用，多个模块使用数组的方式，["table","from",....]
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#demo'
            , url: 'getAllEmployee' //数据接口
            , page: true //开启分页
            , limit: 10
            , limits: [10, 20, 30]
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , cols: [[ //表头
                {type: 'checkbox'},
                {field: 'empId', title: '员工编号', sort: true},
                {field: 'lastName', title: '姓名'},
                {field: 'gender', title: '性别', sort: true},
                {field: 'email', title: '邮箱'},
                {field: 'deptName', title: '部门名称'},
                {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
            ]]
        });
    });
</script>

</body>
</html>
