<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/9
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>部门管理</title>
    <link rel="stylesheet" href="<%=basePath%>static/layui/css/layui.css" media="all">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=basePath%>static/layui/layui.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
    <script src="<%=basePath%>static/js/base64.js"></script>
</head>
<body style="overflow: auto;height: 90%;width: 99%;background-color: white;">
<div>
    <br><br><br><br><br>
    <div class="layui-btn-group" style="margin-left: 30px;">
        <button class="layui-btn layui-btn-normal insert"  onclick="addDepartment()">新增</button>
    </div>
    <div style="width: 95%;height: 90%;margin-left: 30px;">
        <table id="departmentTable" lay-filter="departmentTable" lay-skin="nob"></table>
    </div>
    <script type="text/html" id="delBar">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>

<form id = "depInsert" style="display: none;" >
    <br/><br/>
    <div class="layui-form-item" align="center">
        <label class="layui-form-label">部门名称：</label>
        <div class="layui-input-inline">
            <input type="text" name="" style="width: 180px;" autocomplete="off"
                   placeholder="请输入部门名称" class="layui-input" name="dName"  id="dName"/> <%--待优化：label，input未居中--%>
        </div>
    </div>
</form>

    <script type="text/javascript">
        var table = null;
        layui.use(['table','layer'],function () {
            table = layui.table;
            var layer = layui.layer;
            updateDepartmentPage();

               table.on('tool(departmentTable)', function(obj) { //注：tool是工具条事件名，departmentTable是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                   var html = "<div align=\"center\" style=\"line-height: 100px\" >" +
                                "<p><label>是否删除此条部门记录：</label>" + data.departmentName +"</p>" +
                                "</div>"        //优化：调节垂直居中:调节行高
                if(obj.event == "del"){
                    layer.open({
                        type:1 ,
                        content:html, //'是否确定删除此条部门记录'
                        area: ['400px', '200px'],  //指定弹出层尺寸
                        btn:["确定"],
                        btnAlign: 'c',

                        btn1:function () {
                            $.ajax({
                                url: '<%=basePath%>deleteDepartment.do',
                                type: 'post',
                                data: {"departmentName": data.departmentName
                                    ,"departmentId": data.departmentId},
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        updateDepartmentPage();
                                        layer.close(layer.index);  //关闭弹出的窗口
                                        layer.msg("删除成功");
                                    }else if (data.code == 500){
                                        layer.close(layer.index);
                                        layer.msg("此部门下员工数不为0，不能删除此部门！");
                                    }
                                }
                            })
                        }
                    });
                }
            })
        })

    function addDepartment(){
        /*showNotice();*/
        layer.open({
            type:1 , //弹出层为iframe
            area: ['400px', '200px'],  //指定弹出层尺寸
            content: $("#depInsert"),
            title:'请填写新增部门信息',
            btn:["提交"],
            btnAlign: 'c',
            btn1:function () {

                 var dName = $("#dName").val();
                $.ajax({
                    url: '<%=basePath%>addDepartment.do',
                    type: 'post',
                    data: {"departmentName": dName },
                    beforeSend: function () {
                        layer.msg('添加部门中，请稍等。。。',{icon: 16,shade:[0.4,'#000'],time:false});
                    },
                    success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                        if (data.code == 200) {
                            layer.closeAll();
                            layer.msg("部门添加成功");
                            document.getElementById("depInsert").reset();
                            $("#depInsert").css("display","none");
                            updateDepartmentPage();
                        }
                    }
                })
            },
            cancel:function () {
                document.getElementById("depInsert").reset();
                $("#depInsert").css("display","none");
            }
        });
    }
/*
        function showNotice() {
            Notification.requestPermission(function (perm) {
                if (perm == "granted") {
                    var notification = new Notification("这是一个通知撒:", {
                        dir: "auto",
                        lang: "hi",
                        tag: "testTag",
                        icon: "https://static.cnblogs.com/images/adminlogo.gif",
                        body: "通知content"
                    });
                }
            })
        }*/
    function updateDepartmentPage() {  //需要调整表格尺寸
        $.ajax({
            url:'<%=basePath%>listDepartment.do',
            success:function (data) {
                if (data.code == 200) {
                    table.render({
                        elem: '#departmentTable', //指定原始表格元素选择器
                        data: data.paramList ,
                        cols: [[ //表头
                            {field: 'departmentName', title: '部门名称',width:'80%',align:'center'}
                            ,{title: '操作',toolbar: '#delBar', width:'20%',align:'center'}
                        ]]
                    })
                }
            }
        })
    }

</script>
</body>
</html>
