<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="com.heli.oa.common.entity.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="static/layui/css/layui.css" media="all">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=basePath%>static/layui/layui.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
    <script src="<%=basePath%>static/js/base64.js"></script>
    <style type="text/css">

        .layui-col-xs12 {
            width: 99%;
        }
        .mosou-user-select{
            margin-top: 35px;
        }
    </style>
</head>
<body>
<!-- 评价管理 -->
<div class="layui-row"  style="padding-left: 25px;">
    <div class="layui-col-xs12 mosou-user-select">
        <div class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">查询方式:</label>
                    <div class="layui-input-inline">
                        <select name="interest" lay-filter="aihao">
                            <option value="none" selected=""></option>
                            <option value="count">查询所有评价信息</option>
                            <option value="yesAdopt">查询已审核的评价信息</option>
                            <option value="noAdopt">查询未审核的评价信息</option>
                            <option value="shop">根据商家查询评价信息</option>
                            <option value="user">根据用户查询评价信息</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">请输入：</label>
                    <div class="layui-input-inline">
                        <input type="tel" name="phone" lay-verify="required" placeholder="请输入信息" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">
                        <button class="layui-btn layui-btn-normal mosou-user-selectInfo">点我查询</button>
                    </label>
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-normal mosou-user-insertInfo">新增用户</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-col-xs12" style="margin-top: 35px;">

    </div>
</div>

<script type="text/javascript">
    layui.use(['layer', 'form'], function () {
        var form = layui.form;
       /* $(".mosou_index_tool").click(function () {
            var url = $(this).attr("data-url");
            window.location.href = "http://localhost/returnUrl.do?returnUrl="+url;
        })*/
    })
</script>
</body>
</html>
