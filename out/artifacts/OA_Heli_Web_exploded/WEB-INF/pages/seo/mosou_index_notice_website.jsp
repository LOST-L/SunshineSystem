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
        .mosou_index_tool{
            width: 90%;
            height: 70px;
            background-color: #efefef;
            margin-top: 15px;
            text-align: center;
            padding-top: 20px;

            -webkit-user-select:none;
            -moz-user-select:none;
            -ms-user-select:none;
            user-select:none;
            cursor: pointer;
        }
        .mosou_index_tool:hover{
            background-color: #e2e2e2;
        }
        .layui-col-xs12 {
            width: 99%;
        }
        .mosou-user-select{
            margin-top: 35px;
        }
    </style>
</head>
<body>
<!-- 首页公告 弹窗通知 -->
<div class="layui-fluid">
    <div class="layui-row layui-col-space12">
        <div style="background-color: white;width: 50%;height: 90%;margin: 0px auto;box-shadow:0px 0px 20px 2px #bdb9b9;margin-top: 40px;">
            <img id="close" alt="" src="http://admin.jdseo.com:80/image/close.png" height="50" width="50" style="border-radius: 50%;box-shadow:0px 0px 20px 2px #bdb9b9;margin-left: 97%;margin-top: -25px;background-color: white;cursor: pointer;">
            <div class="layui-card-body" style="text-align: center;font-size: 25px;margin-top: 30px;">添加公告</div>
            <div class="layui-card-body">
                <div class="layui-form layui-form-pane" style="width: 80%;margin-left:100px;margin-top: 35px;">
                    <div class="layui-form-item">
                        <label class="layui-form-label">标题：</label>
                        <div class="layui-input-block">
                            <input type="text" id="hpTitle" name="hpTitle" required="" lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">链接地址：</label>
                        <div class="layui-input-block">
                            <input type="text" id="hpUrl" name="hpUrl" placeholder="例如:https://www.baidu.com" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">作者：</label>
                        <div class="layui-input-block">
                            <input type="text" id="hpAuthor" name="hpAuthor" style="width: 50%;" required="" lay-verify="required" value="核利电商-小鱼" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">授课平台：</label>
                        <div class="layui-input-block" style="width: 42%;">
                            <select id="pt" name="pt" lay-verify="">
                                <option value=""></option>
                                <option value="腾讯课堂">腾讯课堂</option>
                                <option value="荔枝微课">荔枝微课</option>
                                <option value="京东商家学习中心">京东商家学习中心</option>
                            </select><div class="layui-unselect layui-form-select"><div class="layui-select-title"><input type="text" placeholder="请选择" value="" readonly="" class="layui-input layui-unselect"><i class="layui-edge"></i></div><dl class="layui-anim layui-anim-upbit"><dd lay-value="" class="layui-select-tips">请选择</dd><dd lay-value="腾讯课堂" class="">腾讯课堂</dd><dd lay-value="荔枝微课" class="">荔枝微课</dd><dd lay-value="京东商家学习中心" class="">京东商家学习中心</dd></dl></div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">时间：</label>
                        <div class="layui-input-block">
                            <input type="text" id="hpTime" name="hpTime" required="" lay-verify="required" class="layui-input" autocomplete="off" lay-key="1">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">内容：</label>
                        <div class="layui-input-block">
                            <textarea name="hpVal" id="demo" style="display: none;" class="layui-hide"></textarea><div class="layui-layedit"><div class="layui-unselect layui-layedit-tool"><i class="layui-icon layedit-tool-b" title="加粗" lay-command="Bold" layedit-event="b" "=""></i><i class="layui-icon layedit-tool-i" title="斜体" lay-command="italic" layedit-event="i" "=""></i><i class="layui-icon layedit-tool-u" title="下划线" lay-command="underline" layedit-event="u" "=""></i><i class="layui-icon layedit-tool-d" title="删除线" lay-command="strikeThrough" layedit-event="d" "=""></i><span class="layedit-tool-mid"></span><i class="layui-icon layedit-tool-left" title="左对齐" lay-command="justifyLeft" layedit-event="left" "=""></i><i class="layui-icon layedit-tool-center" title="居中对齐" lay-command="justifyCenter" layedit-event="center" "=""></i><i class="layui-icon layedit-tool-right" title="右对齐" lay-command="justifyRight" layedit-event="right" "=""></i><span class="layedit-tool-mid"></span><i class="layui-icon layedit-tool-link" title="插入链接" layedit-event="link" "=""></i><i class="layui-icon layedit-tool-unlink layui-disabled" title="清除链接" lay-command="unlink" layedit-event="unlink" "=""></i><i class="layui-icon layedit-tool-face" title="表情" layedit-event="face" "=""></i><i class="layui-icon layedit-tool-image" title="图片" layedit-event="image"><input type="file" name="file"></i></div><div class="layui-layedit-iframe"><iframe id="LAY_layedit_1" name="LAY_layedit_1" textarea="demo" frameborder="0" style="height: 180px;"></iframe></div></div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-block" style="">
                            <button class="layui-btn layui-btn-normal submit">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['layer', 'form'], function () {
        var form = layui.form;
    })
</script>
</body>
</html>
