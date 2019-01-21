<%@ page import="com.heli.model.User" %><%--
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
    <link rel="icon" href="<%=basePath%>image/logo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=basePath%>page/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>page/style/login.css" media="all">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=basePath%>page/layui/layui.all.js"></script>
    <script src="<%=basePath%>page/layui/layui-xtree.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
    <script src="<%=basePath%>js/base64.js"></script>
</head>
<body style="background-color: white;">
<div >
    <div style="width: 95%;height: 90%;margin-left: 30px;">
        <table id="detailsTable" lay-filter="detailsTable" lay-skin="nob"></table>
    </div>
</div>
<form id="refuseTaskReport" class="layui-form" style="display: none;">
    <br><br>
    <div class="layui-form-item">
        <label class="layui-form-label">不合格原因:</label>
        <div class="layui-input-block" style="width: 230px;">
            <textarea id="comment"  lay-verify="required" placeholder="请输入审核不合格原因" class="layui-textarea"></textarea>
        </div>
    </div>
</form>
<script type="text/html" id="auditBar">
    <a class="layui-btn  layui-btn-xs" lay-event="pass">合格</a>
    <a class="layui-btn  layui-btn-xs layui-btn-danger" lay-event="no">不合格</a>
</script>


<script type="text/javascript">
    var taskId = <%=request.getParameter("taskId")%>;
    var table = null;
    layui.use(['table','form','layer'],function () {
        table = layui.table;
        layer = layui.layer;

        updateSonTask();

        table.on('tool(detailsTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event;
            if(data.taskStatus!="待审核"){
                layer.msg(" 抱歉，此任务非 “待审核” 状态！ ");
                return false;
            }

            if(layEvent === 'pass'){ //通过审核
                layer.confirm('确定审核通过？', function(index){
                    layer.close(index);
                    $.ajax({
                        url: '<%=basePath%>task_audit.action',
                        type: 'post',
                        data: {'recordId': data.recordId,
                            'taskStatus': "已完成"},
                        success: function () {updateSonTask();}
                    })
                });

            } else if(layEvent === 'no'){ //审核不通过，修改任务状态为未完成，并添加不合格原因
                layer.open({
                    type:1 ,
                    content:$("#refuseTaskReport"), //'是否确定删除此条部门记录'
                    area: ['400px', '300px'],  //指定弹出层尺寸
                    btn:["确定","取消"],
                    btnAlign: 'c',
                    closeBtn: 0,
                    btn1:function () {
                        var comment = $("#comment").val();
                        $.ajax({
                            url: '<%=basePath%>task_audit.action',
                            type: 'post',
                            data: {'recordId': data.recordId,
                                'taskStatus': "不合格",
                                "nopassComment":comment
                            },
                            success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                if (data.codeUtils == 200) {
                                    //empty方法不管用
                                    document.getElementById("refuseTaskReport").reset();
                                    $("#delMoneySunshine").css("display","none");
                                    updateSonTask();
                                    layer.close(layer.index)  //关闭ID窗口
                                }
                            }
                        })
                    },
                    btn2:function () {
                        document.getElementById("refuseTaskReport").reset();
                        $("#refuseTaskReport").css("display","none");
                        layer.close(layer.index)
                    },
                });
            }
        });
    })
    function updateSonTask() {
        $.ajax({
            url: '<%=basePath%>task_listSonByTaskId.action',
            type: 'post',
            data: {'taskId': taskId},
            success: function (data) {
                if (data.codeUtils == 200) {
                    table.render({
                        elem: '#detailsTable', //指定原始表格元素选择器
                        data: data.paramList,     //相当于code.paramlist
                        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                            , count: 100
                            , curr: 1 //设定初始在第 5 页
                            , groups: 3 //只显示 1 个连续页码
                            , first: false //不显示首页
                            , last: false //不显示尾页
                        },
                        cols: [[ //表头
                            {field: 'receiver', title: '接收人', width: 75, align: 'center'}
                            , {field: 'taskType', title: '任务类型', width: 90, align: 'center'}
                            , {field: 'repeatNum', title: '重复次数', width: 90, align: 'center'}
                            , {field: 'title', title: '标题', width: 150, align: 'center'}
                            , {field: 'content', title: '内容', width: 250, align: 'center'}
                            , {field: 'createTime', title: '创建时间', width: 165, align: 'center'}
                            , {field: 'taskLimitTime', title: '完成期限', width: 165, align: 'center'}
                            , {field: 'taskStatus', title: '任务状态', width: 90, align: 'center'}
                            , {field: 'refuseComment', title: '拒绝原因', width: 90, align: 'center'}
                            , {field: 'reportTime', title: '完成时间', width: 165, align: 'center'}
                            , {field: 'report', title: '任务报告', align: 'center'}
                            , {title: '任务审核',toolbar: '#auditBar', align: 'center'}
                        ]]
                    })
                }
            }
        })
    }
</script>
</body>
</html>
