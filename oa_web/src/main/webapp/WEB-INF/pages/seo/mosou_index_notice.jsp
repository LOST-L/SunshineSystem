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
<div class="layui-row"  style="padding-left: 25px;">
    <div class="layui-col-xs12 mosou-user-select">
        <div class="layui-col-xs2">
            <div class = "mosou_index_tool" data-url = "seo/mosou_index_notice_website">
                <i class="layui-icon layui-icon-dialogue" style="font-size: 30px;"></i>
                <br />
                发送通知
            </div>
        </div>
        <div class="layui-col-xs2">
            <div class = "mosou_index_tool" data-url = "seo/mosou_index_notice_website">
                <i class="layui-icon layui-icon-edit" style="font-size: 30px;"></i>
                <br />
                首页公告
            </div>
        </div>
        <div class="layui-col-xs2">
            <div class = "mosou_index_tool" data-url = "seo/mosou_index_notice_website">
                <i class="layui-icon layui-icon-fonts-code" style="font-size: 30px;"></i>
                <br />
                发送通知记录
            </div>
        </div>
        <div class="layui-col-xs2">
            <div class = "mosou_index_tool" data-url = "seo/mosou_index_notice_website">
                <i class="layui-icon layui-icon-survey" style="font-size: 30px;"></i>
                <br />
                首页公告记录
            </div>
        </div>
    </div>

    <div class="layui-col-xs12" style="margin-top: 35px;">
        <fieldset class="layui-elem-field">
            <legend>发送记录</legend>
            <div class="layui-field-box layui-col-space10">
                <div class="layui-col-md6">
                    <blockquote class="layui-elem-quote layui-quote-nm">发送通知记录（最近10条）</blockquote>
                    <table class="layui-table mosou-user-userTable">
                        <colgroup>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>标题</th>
                            <th>连接</th>
                            <th>发布者</th>
                            <th>发布时间</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                            <tr>
                                <td>0</td>
                                <td>测试测试测试标题</td>
                                <td>https://www.baidu.com</td>
                                <td>核利电商-小鱼</td>
                                <td>2018-12-29 11:01:03</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="layui-col-md6">
                    <blockquote class="layui-elem-quote layui-quote-nm">首页公告记录（最近10条）</blockquote>
                    <table class="layui-table mosou-user-userTable">
                        <colgroup>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>标题</th>
                            <th>连接</th>
                            <th>发布者</th>
                            <th>发布时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        <tr>
                            <td>0</td>
                            <td>测试测试测试标题</td>
                            <td>https://www.baidu.com</td>
                            <td>核利电商-小鱼</td>
                            <td>2018-12-29 11:01:03</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </fieldset>
    </div>
</div>

<script type="text/javascript">
    layui.use(['layer', 'form'], function () {
        var form = layui.form;
        $(".mosou_index_tool").click(function () {
            var url = $(this).attr("data-url");
            window.location.href = "http://localhost/returnUrl.do?returnUrl="+url;
        })
    })
</script>
</body>
</html>
