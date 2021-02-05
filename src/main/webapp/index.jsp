<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="http://localhost:8080/ssm/">
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <link rel="stylesheet" href="static/notice.css">
    <style type="text/css">
        .layui-form-select dl {
            max-height: 120px;
        }
    </style>
</head>
<body>
<!-- 你的HTML代码 -->
<table id="demo" lay-filter="test"></table>
<div class="site-text" style="margin: 5%; display: none" id="updateForm">
    <form class="layui-form" id="formTable" lay-filter="form-Table">

        <input id="distinguish" type="hidden" value="添加">
        <input name="empId" type="hidden" value="0">
        <input type="hidden" id="method" name="_method">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="lastName" lay-verify="required" autocomplete="off" placeholder="请输入姓名"
                       class="layui-input" lay-reqtext="用户名是必填项，岂能为空？">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="gender" value="1" title="男" checked="">
                <input type="radio" name="gender" value="2" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">部门</label>
            <div class="layui-input-block">
                <select name="deptId" lay-verify="required" lay-search=""
                        id="selectDepartment">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email" lay-filter="email"
                       placeholder="请输入邮箱"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <button id="submit" class="layui-btn" lay-submit lay-filter="*" style="display: none">立即提交</button>
    </form>
</div>

<script src="static/layui/layui.js"></script>
<script src="static/notice.js"></script>

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

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">
        <i class="layui-icon">&#xe642;</i>编辑
    </a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="remove">
        <i class="layui-icon">&#xe640;</i>删除
    </a>
</script>

<script>
    layui.use(['table', 'form', 'notice'], function () {
        var table = layui.table;
        var form = layui.form;
        var notice = layui.notice;
        var $ = layui.$;

        //第一个实例
        table.render({
            elem: '#demo'
            , url: 'getAllEmployee' //数据接口
            , page: true //开启分页
            , limit: 10
            , id: 'test'
            , limits: [10, 20, 30]
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , cols: [[ //表头
                {
                    type: 'checkbox'
                }, {
                    field: 'empId',
                    title: '员工编号',
                    sort: true
                }, {
                    field: 'lastName',
                    title: '姓名'
                }, {
                    field: 'gender',
                    title: '性别',
                    sort: true,
                    templet: function (d) {
                        return d.gender == 1 ? "男" : "女";
                    }
                }, {
                    field: 'email',
                    title: '邮箱'
                }, {
                    field: 'deptName',
                    title: '部门名称',
                    templet: function (d) {
                        return d.department.deptName;
                    }
                }, {
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#barDemo',
                    width: 150
                }
            ]]
        });

        function dynamic() {
            var select = '<option value="">请选择或搜索部门</option>';
            // 在$.post()前把ajax设置为同步：$.ajaxSettings.async = false;
            $.ajaxSettings.async = false;
            $.get("getAllDepartment", function (result) {
                for (let x in result) {
                    select += '<option value = "' + result[x].deptId + '">'
                        + result[x].deptName + '</option>';
                }
                $("#selectDepartment").html(select);
            }, "json")
            form.render('select'); //需要渲染一下
        }


        function update(type) {
            $("#distinguish").val(type);
            layer.open({
                type: 1,
                title: type,
                skin: 'layui-layer-lan', //加上边框
                btn: ['确定', '关闭'],
                area: ['520px', '420px'], //宽高
                content: $("#updateForm"),
                yes: function (index, layero) {
                    $("#submit").click();
                    return false;
                }
                , btn2: function (index, layero) {
                    // 清空form表单文本框的值
                    document.getElementById("formTable").reset();
                    layer.close(index);
                    return false;
                }
            });
        }

        //监听头部工具栏事件
        table.on('toolbar(test)', function (obj) {
            //获取选中行的数据
            var checkStatus = table.checkStatus(obj.config.id).data;
            switch (obj.event) {
                case 'add':
                    dynamic();
                    update("添加");
                    break;
                case 'deleteBatches' :
                    let arr = new Array();
                    for (let x in checkStatus) {
                        arr.push(checkStatus[x].empId);
                    }
                    layer.confirm('真的删除行么', function (index) {
                        $.post("removeEmployeeInfoInBulk", {
                            '_method': 'DELETE',
                            'empIds': JSON.stringify(arr)
                        }, function (result) {
                            if (result) {
                                table.reload('test', {
                                    page: {
                                        curr: 1
                                        //重新从第 1 页开始
                                    }
                                });
                                layer.closeAll();
                                notice.warning("操作成功");
                            } else {
                                notice.error("操作失败");
                            }
                        }, 'json');
                    });
                    break;
            }
        });

        form.on('submit(*)', function (data) {
            let url = "";
            if ($("#distinguish").val() === '添加') {
                url = "addEmployeeInfo";
                $("#method").val("POST");
            } else {
                url = "editEmployeeInfo";
                $("#method").val("PUT");
            }
            $.post(url, data.field, function (result) {
                if (result) {
                    table.reload('test', {
                        page: {
                            curr: 1
                            //重新从第 1 页开始
                        }
                    });
                    // 清空form表单文本框的值
                    document.getElementById("formTable").reset();
                    layer.closeAll();
                    notice.warning("操作成功");
                } else {
                    notice.error("操作失败");
                }
            }, "json");
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        //监听工具条
        //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        table.on('tool(test)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'remove') { //删除
                layer.confirm('真的删除行么', function (index) {
                    $.post("removeEmployeeInfo", {
                        'empId': data.empId,
                        '_method': 'DELETE'
                    }, function (result) {
                        if (result) {
                            table.reload('test', {
                                page: {
                                    curr: 1
                                    //重新从第 1 页开始
                                }
                            });
                            layer.closeAll();
                            notice.warning("操作成功");
                        } else {
                            notice.error("操作失败");
                        }
                    }, 'json');
                    layer.close(index);
                });
            } else if (layEvent === 'edit') { //编辑
                dynamic();
                //给表单赋值
                form.val("form-Table", data);
                update("修改");
            }
        });
    })
</script>


</body>
</html>
