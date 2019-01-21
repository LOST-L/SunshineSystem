<%--
  Created by IntelliJ IDEA.
  User: 白驹
  Date: 2019/1/16
  Time: 15:46
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
    <title>官网对外联系方式查询接口</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="icon" href="<%=basePath%>static/image/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=basePath%>static/layui/css/layui.css" media="all">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
    <script src="<%=basePath%>static/layui/layui.js"></script>
    <script src="<%=basePath%>static/js/base64.js"></script>
</head>
<body style="overflow: auto;height: 90%;width: 99%;background-color: white;">
<div>
    <br>
    <div class="layui-btn-group" style="margin-left: 30px;">
        <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="addEmployeeInfo()">新增</button>
        <input class = "layui-input-inline layui-input" style = "width: 200px;" name="search" autocomplete="off" id="employeeInfo" placeholder="请输入用户信息">
        <button class="layui-btn " style="margin-top: 8px;" onclick="search()">搜索用户</button>
    </div>
    <div style="width: 65%;height: 90%;margin-left: 30px;">
        <table id="employeeInfoTable" lay-filter="employeeInfoTable" lay-skin="nob"></table>
    </div>
</div>

<form id="employeeInfoInsert" class="layui-form"style="display: none;">
    <br><br>
    <div class="layui-form-item">
        <label class="layui-form-label">员工花名:</label>
        <div class="layui-input-block">
            <input type="text" id="nickName"  autocomplete="off" style="width: 230px;" placeholder="请输入员工花名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号码:</label>
        <div class="layui-input-block">
            <input type="text" id="phone"  autocomplete="off" style="width: 230px;" placeholder="请输入员工电话" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">企业QQ:</label>
        <div class="layui-input-block">
            <input type="text" id="QQ"  autocomplete="off" style="width: 230px;" placeholder="请输入员工企业QQ" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">企业微信:</label>
        <div class="layui-input-block">
            <input type="text" id="wechat"  autocomplete="off" style="width: 230px;" placeholder="请输入员工企业微信" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮件地址:</label>
        <div class="layui-input-block">
            <input type="text" id="mail"  autocomplete="off" style="width: 230px;" placeholder="请输入邮件地址" class="layui-input">
        </div>
    </div>
</form>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">
    var table = null;
    var form = null;
    layui.use(['table','form','layer'],function () {
        table = layui.table;
        form = layui.form;//引入layui.form,然后对其render渲染，被渲染的部分一定要包裹在class= layui-form下
        var layer = layui.layer;

        form.render();
     
        updateEmployeeInfoPage();
        
        //监听行工具事件
        table.on('tool(employeeInfoTable)', function(obj){
            var data = obj.data;
            console.log(data);
            var html1 = "<div align=\"center\">" +
                "<p><label>是否删除此条员工记录：  </label>"+ data.employeeInfoNickname +"</p>" +
                "</div>"  
            var html2 = "<div align=\"center\">" +
                "<p><label>是否更新此条员工记录：  </label>"+ data.employeeInfoNickname + "</p>" +
                "</div>"
            if(obj.event === 'del'){
                layer.open({
                    type:1 ,
                    content:html1, //'是否确定删除此条部门记录'
                    area: ['400px', '200px'],  //指定弹出层尺寸
                    btn:["确定","取消"],
                    btnAlign: 'c',
                    btn1:function () {
                        $.ajax({
                            url: '<%=basePath%>deleteEmployeeInfo.do',
                            type: 'post',
                            async: false,
                            data: {
                                employeeInfoId:data.employeeInfoId
                            },
                            success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                if (data.code == 200) {
                                    updateEmployeeInfoPage();
                                    layer.close(layer.index)  //关闭ID窗口
                                }else if(data.code == 500){
                                    layer.close(layer.index);
                                    layer.msg("不能删除自己的账户！");
                                }
                            }
                        })
                    },
                    btn2:function () {
                        layer.close(layer.index)
                    }
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type:1 ,
                    content:html2, //'是否确定更新此条员工记录'
                    area: ['400px', '200px'],  //指定弹出层尺寸
                    btn:["确定","取消"],
                    btnAlign: 'c',
                    btn1:function () {
                        $.ajax({
                            url: '<%=basePath%>updateEmployeeInfo.do',
                            type: 'post',
                            data: JSON.parse(JSON.stringify(data)),
                            success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                if (data.code == 200) {
                                    updateEmployeeInfoPage();
                                    layer.close(layer.index)  //关闭ID为1的窗口
                                }
                            }
                        })
                    },
                    btn2:function () {
                        layer.close(layer.index)
                    }
                });
            }
        });
        
    })
    
    function addEmployeeInfo(){
        layer.open({
            type: 1 ,
            area: ['380px', '400px'],  //指定弹出层尺寸
            content: $("#employeeInfoInsert"),
            title:'请填写新增用户信息',
            btn:["提交"],
            btnAlign: 'c',

            btn1:function () {
                var nickName = $("#nickName").val();
                var phone = $("#phone").val();
                var mail = $("#mail").val();
                var qq =  $("#QQ").val();
                var wechat =  $("#wechat").val();
                $.ajax({
                    url: '<%=basePath%>addEmployeeInfo.do',
                    type: 'post',
                    data: {
                        "employeeInfoNickname": nickName,
                        "employeeInfoPhone":phone,
                        "employeeInfoMail":mail,
                        "employeeInfoQQ":qq,
                        "employeeInfoWechat": wechat
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            document.getElementById("employeeInfoInsert").reset();
                            $("#employeeInfoInsert").css("display","none");
                            $("#userName").empty();
                            updateEmployeeInfoPage();
                            layer.close(layer.index)
                        }
                    }
                })
            },
            cancel:function () {
                document.getElementById("employeeInfoInsert").reset();
                $("#employeeInfoInsert").css("display","none");
            }
        });
    }

    function updateEmployeeInfoPage() {
        $.ajax({
            url:'<%=basePath%>listEmployeeInfo.do',
            success:function (data) {
                if (data.code == 200) {
                    table.render({
                        elem: '#employeeInfoTable', //指定原始表格元素选择器
                        data: data.paramList ,
                        toolbar: true,
                        defaultToolbar: ['filter', 'print', 'exports'],
                        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                            layout: [ 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                            ,count:100
                            ,curr: 1 //设定初始在第 5 页
                            ,groups: 3 //只显示 1 个连续页码
                            ,first: false //不显示首页
                            ,last: false //不显示尾页
                            ,limit:17
                        },
                        cols: [[ //表头
                            {align:'center',field: 'employeeInfoId', title: '记录ID',width:100}
                            ,{align:'center',field: 'employeeInfoNickname', title: '花名',edit:'text',width:100}
                            ,{align:'center',field: 'employeeInfoPhone', title: '电话',edit:'text',width:150}
                            ,{align:'center',field: 'employeeInfoQQ', title: '企业QQ',edit:'text',width:150}
                            ,{align:'center',field: 'employeeInfoWechat', title: '工作微信',edit:'text',width:200}
                            ,{align:'center',field: 'employeeInfoMail', title: '邮件地址',edit:'text',width:200}
                            ,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right'}
                        ]]
                    })
                }
            }
        })
    }
    
    function search() {
        $.ajax({
            url:'<%=basePath%>searchEmployeeInfo.do',
            type: 'post',
            data: {
                "employeeInfoNickname": $("#employeeInfo").val(),
            },
            success:function (data) {
                if (data.code == 200) {
                    table.render({
                        elem: '#employeeInfoTable', //指定原始表格元素选择器
                        data: data.paramList ,
                        toolbar: true,
                        defaultToolbar: ['filter', 'print', 'exports'],
                        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                            layout: [ 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                            ,count:100
                            ,curr: 1 //设定初始在第 5 页
                            ,groups: 3 //只显示 1 个连续页码
                            ,first: false //不显示首页
                            ,last: false //不显示尾页
                            ,limit:17
                        },
                        cols: [[ //表头
                            {align:'center',field: 'employeeInfoId', title: '记录ID',width:100}
                            ,{align:'center',field: 'employeeInfoNickname', title: '花名',edit:'text',width:100}
                            ,{align:'center',field: 'employeeInfoPhone', title: '电话',edit:'text',width:150}
                            ,{align:'center',field: 'employeeInfoQQ', title: '企业QQ',edit:'text',width:150}
                            ,{align:'center',field: 'employeeInfoWechat', title: '工作微信',edit:'text',width:200}
                            ,{align:'center',field: 'employeeInfoMail', title: '邮件地址',edit:'text',width:200}
                            ,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right'}
                        ]]
                    })
                }
            }
        })
    }
</script>
</body>
</html>

