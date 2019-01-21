<%--
  Created by IntelliJ IDEA.
  User: 白驹
  Date: 2018/12/27
  Time: 09:05
--%>
<%@ page language="java" import =" java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE>
<html>
<head>
    <title>核利阳光值系统</title>
    <link rel="icon" href="<%=basePath%>static/image/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=basePath%>static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>static/css/login.css" media="all">
    <script src="<%=basePath%>static/layui/layui.all.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
    <script src="<%=basePath%>static/js/base64.js?v=<%=Math.random()%>"></script>
</head>

<body>
<div class="layadmin-user-login layadmin-user-display-show"
     id="LAY-user-login" style="background-image: url(static/image/timg.jpg);background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;">

    <div class="layadmin-user-login-main" style="margin-top: 150px;">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>核利阳光值管理系统</h2>
            <p style="color: black;">核利电商，每天都会更精彩</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="account" id="LAY-user-login-username" lay-verify="required" placeholder="请输入中文花名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="pwd" id="LAY-user-login-password" lay-verify="required" placeholder="请输入密码" class="layui-input">
            </div>
<%--            <div class="layui-form-item">
                <input type="checkbox" name="autoLogin" lay-skin="primary" id="ifAutoLogin" title="记住密码，7天免登陆" style="color: black;">
            </div>--%>
            <div class="layui-form-item">
                <button id="userSubmit" class="layui-btn layui-btn-fluid">登 入</button>
            </div>
        </div>
    </div>
</div>

<script language="JavaScript">

    layui.use(['layer', 'form'], function () {
        var form = layui.form;
        form.render();
        /*todo 白驹 用户名是否存在实时验证*/
        //登入监听提交
        $("#userSubmit").click(function () {
            var i = 0;
            if ($("#LAY-user-login-username").val() != null && $("#LAY-user-login-username").val() != ""){
                i += 1;
            }
            if ($("#LAY-user-login-password").val() != null && $("#LAY-user-login-password").val() != ""){
                i += 1;
            }
            if (i > 1) {
                var signArray = new Array();
                var time = 2;
                var userNickname = $("#LAY-user-login-username").val();
                var userPassword = $("#LAY-user-login-password").val();
                var base = new Base64();
                userNickname = base.encode(userNickname);
                signArray.push(userNickname);
                signArray.push(userPassword);
                signArray.push(time);
                var sign = getDataCombinationTo(signArray);
                /*var ifAutoLogin = $("#ifAutoLogin").is(":checked");*/
                $.ajax({
                    url: '<%=basePath%>login.do',
                    type: 'post',
                    data: {'sign':sign
                        /*,"ifAutoLogin": ifAutoLogin*/
                    },
                    beforeSend: function () {
                        layer.msg('验证中', {icon: 16, shade: 0.01});
                    },
                    success: function (data) {
                        if(data.code == "200"){
                            layer.msg('验证成功', {icon: 1,time: 4000});
                            // 获取员工职位级别编号role1值
                            window.location.href = "index";/*跳转到管理员界面（总经理，人事总监）*/
                        }else{
                            layer.msg('验证失败，请检查用户名及密码,或联系管理员',{icon: 2});
                        }
                        //验证成功
                    },
                    error:function () {
                        //验证失败
                        layer.msg('验证失败，请检查用户名及密码,或联系管理员',{icon: 2});
                    }
                });
            }
        });

        //监听回车按键登陆
        $(document).keydown(function (event) {
            if (event.keyCode == 13) {
                $('#userSubmit').triggerHandler('click');
            }
        });
    });
</script>
</body>
</html>