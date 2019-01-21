<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/15
  Time: 15:15
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
    <title>任务详情</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="<%=basePath%>static/layui/css/layui.css" media="all">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
    <script src="<%=basePath%>static/layui/layui.js"></script>
    <script src="<%=basePath%>static/js/base64.js"></script>
</head>
<body style="background-color: white;">
<div >
    <div style="width: 95%;height: 90%;margin-left: 30px;">
        <table id="detailsTable" lay-filter="detailsTable" lay-skin="nob"></table>
    </div>
</div>

<script type="text/javascript">
    var taskId = <%=request.getParameter("taskId")%>;
    var table = null;
    layui.use(['table','form','layer'],function () {
        table = layui.table;
        layer = layui.layer;

        updateSonTask();
    })

    function updateSonTask() {
        $.ajax({
            url: '<%=basePath%>listSonTaskByTaskId.do',
            type: 'post',
            data: {'taskId': taskId},
            success: function (data) {
                if (data.code == 200) {
                    table.render({
                        elem: '#detailsTable', //指定原始表格元素选择器
                        data: data.paramList,     //相当于code.paramlist
                        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                            layout: [ 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                            , count: 100
                            , curr: 1 //设定初始在第 5 页
                            , groups: 3 //只显示 1 个连续页码
                            , first: false //不显示首页
                            , last: false //不显示尾页
                            ,limit:15
                        },
                        cols: [[ //表头
                            {field: 'taskRecordId', title: '接收人', width: 75, align: 'center'}
                            , {field: 'taskReceiver', title: '接收人', width: 75, align: 'center'}
                            , {field: 'taskType', title: '任务类型', width: 90, align: 'center'}
                            , {field: 'taskRepeatNum', title: '重复次数', width: 90, align: 'center'}
                            , {field: 'taskTitle', title: '标题', width: 150, align: 'center'}
                            , {field: 'taskContent', title: '内容', width: 250, align: 'center'}
                            , {field: 'taskCreateTime', title: '创建时间', width: 165, align: 'center'}
                            , {field: 'taskLimitTime', title: '完成期限', width: 165, align: 'center'}
                            , {field: 'taskStatus', title: '任务状态', width: 90, align: 'center'}
                            , {field: 'taskRefuseComment', title: '拒绝原因', width: 90, align: 'center'}
                            , {field: 'taskReportTime', title: '完成时间', width: 165, align: 'center'}
                            , {field: 'taskReport', title: '任务报告', align: 'center'}
                        ]]
                    })
                }
            }
        })
    }
</script>
</body>
</html>
