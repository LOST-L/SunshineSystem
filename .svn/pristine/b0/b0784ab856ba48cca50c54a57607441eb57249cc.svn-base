<%--
  Created by IntelliJ IDEA.
  User: Loong
  Date: 2019/1/13
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.heli.oa.common.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    User u =(User)session.getAttribute("user");
%>
<html>
<head>
    <title>待办事项</title>
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
    <style type="text/css">
        .zhengti {
            overflow: hidden;
        }

        .zuo {
            width: 600px;
            height: 782px;
            border: 1px solid silver;
            float: left;

        }

        .backlogDiv1 {
            width: 500px;
            height: 390px;
            border: 1px solid silver;
            float: left;
        }

        .backlogDiv2 {
            width: 500px;
            height: 390px;
            border: 1px solid silver;
            float: left;
        }

        .backlogDiv3 {
            width: 500px;
            height: 390px;
            border: 1px solid silver;
            float: left;
        }

        .backlogDiv4 {
            width: 500px;
            height: 390px;
            border: 1px solid silver;
            float: left;
        }
        #newTaskForm .layui-form .layui-form-label{
            width: 150px;}
    </style>
</head>
<body style="background-color: white;">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">个人待办事项</li>
            <li onclick="updateDoneBacklog();">已完成事项</li>
        </ul>
        <div class="layui-tab-content" style="height: 100px;">
            <div class="layui-tab-item layui-show">
                <div class="zhengti">
                    <div class="zuo">
                        <button class="layui-btn layui-btn-normal addBacklog" style="margin: 20px">添加待办事宜</button>
                        <table class="layui-table" id="toadyBacklogTable" lay-filter="toadyBacklogTable"></table>
                    </div>
                    <div class="backlogDiv1">
                        <h3 style="margin: 10px">重要紧急</h3>
                        <table class="layui-table" id="backlogTable1" lay-filter="backlogTable"></table>
                    </div>
                    <div class="backlogDiv2">
                        <h3 style="margin: 10px">重要不紧急</h3>
                        <table class="layui-table" id="backlogTable2" lay-filter="backlogTable"></table>
                    </div>
                    <br>
                    <div class="backlogDiv3">
                        <h3 style="margin: 10px">紧急不重要</h3>
                        <table class="layui-table" id="backlogTable3" lay-filter="backlogTable"></table>
                    </div>
                    <div class="backlogDiv4">
                        <h3 style="margin: 10px">不重要不紧急</h3>
                        <table class="layui-table" id="backlogTable4" lay-filter="backlogTable"></table>
                    </div>
                </div>
            </div>

            <div class="layui-tab-item">
                <form class="layui-inline layui-form">
                    <div class="layui-form-item">

                        <label class="layui-form-label" >创建时间：</label>
                        <div class="layui-input-inline" style="width: 320px">
                            <input type="text" name="createTimeForSearch" id="createTimeForSearch" lay-verify="required"
                                   placeholder="yyyy-MM-dd HH:mm:ss-yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input" >
                        </div>
                        <label class="layui-form-label" >完成时间：</label>
                        <div class="layui-input-inline" style="width: 320px">
                            <input type="text" name="doneTimeForSearch" id="doneTimeForSearch" lay-verify="required"
                                   placeholder="yyyy-MM-dd HH:mm:ss-yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input" >
                        </div>

                        <label class="layui-form-label" >时间安排：</label>
                        <div class="layui-input-inline" style="width: 80px">
                            <input type="text" name="backlogStartTimeStr" id="backlogStartTimeStr" lay-verify="required" placeholder="HH:mm" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <div class="layui-input-inline" style="width: 30px;line-height: 35px;">
                            ——
                        </div>
                        <div class="layui-input-inline" style="width: 80px">
                            <input type="text" name="backlogEndTimeStr" id="backlogEndTimeStr" lay-verify="required" placeholder="HH:mm" autocomplete="off" class="layui-input">
                        </div>

                        <label class="layui-form-label" style="width: 100px;" >重要紧急程度：</label>
                        <div class="layui-input-inline">
                            <select name="priority" lay-search="" id="priority">
                                <option value="">全部事宜</option>
                                <option value="重要紧急">重要紧急</option>
                                <option value="重要不紧急">重要不紧急</option>
                                <option value="不重要紧急">不重要紧急</option>
                                <option value="不重要不紧急">不重要不紧急</option>
                            </select>
                        </div>
                        <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="search">搜索</button>
                    </div>
                </form>
                <div class="kuan" style="width: 95%;height: 90%;margin-left: 30px;">
                    <table class="layui-table" id="doneBacklogTable"></table>
                </div>
            </div>
        </div>

        <div id="detailsTableDiv" class="layui-tab-content" style="height: 100px;">
            <div class="layui-tab-item">
                <div style="width: 95%;height: 90%;margin-left: 30px;">
                    <table id="detailsTable" lay-filter="createTable" lay-skin="nob"></table>
                </div>
            </div>
        </div>
    </div>
    <form id="addBacklogDiv" class="layui-form" style="display:none;">
        <div class="layui-form-item">
            <label class="layui-form-label">重要程度:</label>
            <div class="layui-input-block">
                <input type="radio" name="important" value="重要" title="重要" id="important">
                <input type="radio" name="important" value="不重要" title="不重要" id="unimportant" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">紧急程度:</label>
            <div class="layui-input-block">
                <input type="radio" name="urgent" value="紧急" title="紧急" id="urgent">
                <input type="radio" name="urgent" value="不紧急" title="不紧急" id="noturgent" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">事宜内容:</label>
            <div class="layui-input-block">
                        <textarea id="backlogContent" lay-verify="required" placeholder="请输入内容" rows="3"
                                  cols="20" class="layui-textarea" style="width: 300px"></textarea>
            </div>
        </div>
    </form>

    <form id="setBacklogTime" class="layui-form" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 100px;">开始时间：</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="startTime" placeholder="HH:mm">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 100px;">结束时间：</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="endTime" placeholder="HH:mm">
                </div>
            </div>
        </div>
    </form>

    <script type="text/html" id="bar4">
        <a class="layui-btn layui-btn-xs setTime" lay-event="setTime">定时</a>
        <a class="layui-btn layui-btn-xs" lay-event="over">完成</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
    </script>
    <script type="text/html" id="bar5">
        <button class="layui-btn layui-btn-xs" lay-event="over">完成</button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="cancel">撤销</button>
    </script>
    <script type="text/html" id="bar7">
        <button class="layui-btn layui-btn-xs" lay-event="accept">接受</button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="refuse">拒绝</button>
    </script>
</body>
    <script type="text/javascript">
        var table = null;
        var form = null;
        layui.use(['table', 'form', 'layer', 'laydate','element'], function () {
            table = layui.table;
            form = layui.form;
            var laydate = layui.laydate;
            var layer = layui.layer;
            var element = layui.element;

            updateBacklogNotSetTime();
            updateTodayBacklog();

            //HH:mm
            function timeStamp2HourMinutesStr(time) {
                var datetime = new Date();
                datetime.setTime(time);
                var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
                var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
                var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
                return hour + ":" + minute + ":" + second;
            }

            date = new Date();
            laydate.render({
                elem: '#createTimeForSearch'
                ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
                ,type: 'datetime'
                ,min: '2018-12-7 00:00:00'
                ,max:'date'
                ,range: true
            });

            laydate.render({
                elem: '#doneTimeForSearch'
                ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
                ,type: 'datetime'
                ,min: '2018-12-7 00:00:00'
                ,max: 'date'
                ,range: true
            });

            laydate.render({
                elem: '#backlogStartTimeStr'
                ,format: 'HH:mm:ss'
                ,type: 'time'
            });

            laydate.render({
                elem: '#backlogEndTimeStr'
                ,format: 'HH:mm'
                ,type: 'time'

            });

            //新建待办事宜弹出
            $(".addBacklog").click(function(){
                $("#addBacklogDiv input[name=urgent]")[0].checked = true;
                $("#addBacklogDiv input[name=important]")[0].checked = true;
                form.render();
                layer.open({
                    type:1 ,
                    title: '新建待办事宜',
                    area :['450px','350px'],
                    content:$("#addBacklogDiv"),
                    btn:["提交","取消"],
                    btnAlign: 'c',
                    btn1:function () {
                        var important = null;
                        var urgent = null;
                        if($("#addBacklogDiv input[name=important]")[0].checked == true){
                            important = "重要"}else{
                            important = "不重要"
                        }
                        if($("#addBacklogDiv input[name=urgent]")[0].checked == true){
                            urgent = "紧急"}else{
                            urgent = "不紧急"
                        }

                        var priority = important + urgent ;
                        var content = $("#backlogContent").val();
                        $.ajax({
                            url: '<%=basePath%>addBacklog.action',
                            type: 'post',
                            data: {
                                "backlogPriority": priority,
                                "backlogContent": content,
                                "userId":<%=u.getUserId()%>
                            },
                            success: function (data) {
                                if (data.code == 200) {
                                    document.getElementById("addBacklogDiv").reset();
                                    $("#addBacklogDiv").css("display","none");
                                    updateBacklogNotSetTime();
                                    layer.close(layer.index)  //关闭弹出来的窗口

                                }
                            }
                        })
                    },
                    btn2:function(){
                        document.getElementById("addBacklogDiv").reset();
                        $("#addBacklogDiv").css("display","none");
                    },
                    cancel:function () {
                        document.getElementById("addBacklogDiv").reset();
                        $("#addBacklogDiv").css("display","none");
                    }
                });
                form.render();
            });

            //监听待办事项四象限按钮
            table.on('tool(backlogTable)', function (obj) {
                var data = obj.data;
                if (obj.event === 'setTime') {

                    laydate.render({
                        elem: '#startTime'
                        ,format: 'HH:mm'
                        ,min: timeStamp2HourMinutesStr(new Date().getTime() + 120*1000)
                        ,type: 'time'
                    });

                    laydate.render({
                        elem: '#endTime'
                        ,format: 'HH:mm' //可任意组合
                        ,min: timeStamp2HourMinutesStr(new Date().getTime() + 120*1000)
                        ,type: 'time'
                    });

                    layer.open({
                        type:1,
                        title: '定时',
                        area :['350px','250px'],
                        content: $("#setBacklogTime"),
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        async: false,
                        btn1:function () {
                            var startTime = $("#startTime").val();
                            var endTime = $("#endTime").val();

                            if(startTime>=endTime){
                                layer.msg("开始时间不能大于结束时间，请重新填写。");
                                document.getElementById("setBacklogTime").reset();
                                return;
                            }

                            $.ajax({
                                url: '<%=basePath%>setBacklogTime.action',
                                type: 'post',
                                async: false,
                                data: {
                                    "backlogRecordId":data.backlogRecordId,
                                    "backlogStartTimeStr": startTime,
                                    "backlogEndTimeStr": endTime,
                                },
                                success: function (data1) {
                                    if (data1.code == 200) {
                                        document.getElementById("setBacklogTime").reset();
                                        $("#setBacklogTime").css("display","none");
                                        updateBacklog();
                                        layer.close(layer.index)  //关闭弹出来的窗口
                                    }else if(data1.code == 500){
                                        var userId = <%=u.getUserId()%>;
                                        insertBacklog(userId,data.backlogRecordId,startTime,endTime);
                                        document.getElementById("setBacklogTime").reset();
                                        $("#setBacklogTime").css("display","none");
                                    }
                                }
                            })
                        },
                        btn2:function(){
                            document.getElementById("setBacklogTime").reset();
                            $("#setBacklogTime").css("display","none");
                        },
                        cancel:function () {
                            document.getElementById("setBacklogTime").reset();
                            $("#setBacklogTime").css("display","none");
                        }
                    });
                } else if (obj.event === 'over') {
                    layer.open({
                        type: 1,
                        content: '是否确定完成此待办事项', //'是否确定删除此条部门记录'
                        area: ['400px', '250px'],  //指定弹出层尺寸
                        btn: ["确定", "取消"],
                        btnAlign: 'c',
                        btn1: function () {
                            $.ajax({
                                url: '<%=basePath%>overBacklog.action',
                                type: 'post',
                                data: data
                                /*{
                                    taskId : data.taskId,
                                }*/,
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        layer.close(layer.index);  //关闭ID窗口
                                        layer.msg("待办事项已完成");
                                        updateBacklogNotSetTime();
                                    }
                                }
                            })
                        },
                        btn2: function () {
                            layer.close(layer.index)
                        }
                    });
                } else if (obj.event === 'delete') {
                    layer.open({
                        type: 1,
                        content: '是否确定删除此待办事项', //'是否确定删除此条部门记录'
                        area: ['400px', '250px'],  //指定弹出层尺寸
                        btn: ["确定", "取消"],
                        btnAlign: 'c',
                        btn1: function () {
                            $.ajax({
                                url: '<%=basePath%>deleteBacklog.action',
                                type: 'post',
                                data: data,
                                success: function (data) {
                                    if (data.code == 200) {
                                        layer.close(layer.index);
                                        layer.msg("待办事项已删除");
                                        updateBacklogNotSetTime();
                                    }
                                }
                            })
                        },
                        btn2: function () {
                            layer.close(layer.index)
                        }
                    });
                }
            });

            //监听待办事项今日已定时表
            table.on('tool(toadyBacklogTable)', function (obj) {
                var data = obj.data;
                if (obj.event === 'over') {
                    layer.open({
                        type: 1,
                        content: '是否确定完成此待办事项', //'是否确定删除此条部门记录'
                        area: ['400px', '250px'],  //指定弹出层尺寸
                        btn: ["确定", "取消"],
                        btnAlign: 'c',
                        btn1: function () {
                            $.ajax({
                                url: '<%=basePath%>overBacklog.action',
                                type: 'post',
                                data: {
                                    backlogRecordId : data.backlogRecordId,
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        layer.close(layer.index);  //关闭ID窗口
                                        layer.msg("待办事项已完成");
                                        updateTodayBacklog();
                                    }
                                }
                            })
                        },
                        btn2: function () {
                            layer.close(layer.index)
                        }
                    });
                } else if (obj.event === 'cancel') {
                    layer.open({
                        type: 1,
                        content: '是否确定撤销此待办事项定时', //'是否确定删除此条部门记录'
                        area: ['400px', '250px'],  //指定弹出层尺寸
                        btn: ["确定", "取消"],
                        btnAlign: 'c',
                        btn1: function () {
                            $.ajax({
                                url: '<%=basePath%>cancelBacklogTime.action',
                                type: 'post',
                                data: {
                                    backlogRecordId : data.backlogRecordId,
                                },
                                success: function (data) {
                                    if (data.code == 200) {
                                        layer.close(layer.index);
                                        layer.msg("待办事项已取消定时");
                                        updateBacklog();
                                    }
                                }
                            })
                        },
                        btn2: function () {
                            layer.close(layer.index)
                        }
                    });
                }
            });

            //监听已完成待办事项的搜索提交
            form.on('submit(search)', function(data){
                var obj = data.field;
                if(obj.backlogStartTimeStr >= obj.backlogEndTimeStr) {
                    layer.msg("时间安排：开始时间不能大于结束时间，请重新填写。");
                    return;
                }
                obj.userId = <%=u.getUserId()%>;
                $.ajax({
                    url: '<%=basePath%>searchDoneBacklog.action',
                    type: 'post',
                    async: false,
                    data: obj,
                    success: function (data1) {
                        if (data1.code == 200) {

                            table.render({
                                elem: '#doneBacklogTable',
                                data: data1.paramList,
                                cols: [[
                                    {field: 'backlogCreateTime', title: '创建时间', align: "center", width: 180}
                                    ,{field: 'backlogDoneTime', title: '完成时间', align: "center", width: 180}
                                    ,{field: 'backlogTimeSlot', title: '时间安排', align: "center", width: 117}
                                    ,{field: 'backlogPriority', title: '重要紧急程度', align: "center", width: 120}
                                    ,{field: 'backlogContent', title: '内容', align: "center"}
                                ]]
                            })

                        }
                    }
                });
                return false;
            });

            form.render();
        });

        function updateBacklog(){
            updateBacklogNotSetTime();
            updateTodayBacklog();
        }

        //刷新四象限待安排表
        function updateBacklogNotSetTime() {
            $.ajax({
                url: '<%=basePath%>listBacklogByPriority.do',
                type: 'post',
                data: {"backlogPriority": '重要紧急',
                    "userId":<%=u.getUserId()%>},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#backlogTable1', //指定原始表格元素选择器
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
                                {field: 'backlogCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'backlogContent', title: '内容', align: 'center'}
                                , {title: '操作', toolbar: '#bar4', width: 160, align: 'center'}
                            ]]
                        })
                    }
                }
            })
            $.ajax({
                url: '<%=basePath%>listBacklogByPriority.do',
                type: 'post',
                data: {"backlogPriority": '重要不紧急',
                    "userId":<%=u.getUserId()%>},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#backlogTable2', //指定原始表格元素选择器
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
                                {field: 'backlogCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'backlogContent', title: '内容', align: 'center'}
                                , {title: '操作', toolbar: '#bar4', width: 160, align: 'center'}
                            ]]
                        })
                    }
                }
            })
            $.ajax({
                url: '<%=basePath%>listBacklogByPriority.do',
                type: 'post',
                data: {"backlogPriority": '不重要紧急',
                    "userId":<%=u.getUserId()%>},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#backlogTable3', //指定原始表格元素选择器
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
                                {field: 'backlogCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'backlogContent', title: '内容', align: 'center'}
                                , {title: '操作', toolbar: '#bar4', width: 160, align: 'center'}
                            ]]
                        })
                    }
                }
            })
            $.ajax({
                url: '<%=basePath%>listBacklogByPriority.do',
                type: 'post',
                data: {"backlogPriority": '不重要不紧急',
                    "userId":<%=u.getUserId()%>},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#backlogTable4', //指定原始表格元素选择器
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
                                {field: 'backlogCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'backlogContent', title: '内容', align: 'center'}
                                , {title: '操作', toolbar: '#bar4', width: 160, align: 'center'}
                            ]]
                        })
                    }
                }
            })
        }

        //刷新今日安排表
        function updateTodayBacklog(){
            $.ajax({
                url: '<%=basePath%>listSetTimeBacklogByUserId.do',
                type: 'post',
                data: {
                    "userId":<%=u.getUserId()%>},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#toadyBacklogTable',
                            data: data.paramList,
                            cols: [[
                                {field: 'backlogTimeSlot', title: '时间安排', align: "center", width: 110, sort: true}
                                , {field: 'backlogPriority', title: '重要紧急程度', align: "center", width: 120}
                                , {field: 'backlogContent', title: '内容', align: "center"}
                                , {title: '操作', toolbar: '#bar5', width: 120, align: 'center'}
                            ]]
                        })
                    }
                }
            })
        }

        //刷新已完成待办事项表
        function updateDoneBacklog(){
            $.ajax({
                id:"updateDoneBacklog",
                url: '<%=basePath%>listBacklogByStatus.action',
                type: 'post',
                data: {"backlogStatus":"已完成",
                    "userId":<%=u.getUserId()%>},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#doneBacklogTable',
                            data: data.paramList,
                            cols: [[
                                {field: 'backlogCreateTime', title: '创建时间', align: "center", width: 180}
                                ,{field: 'backlogDoneTime', title: '完成时间', align: "center", width: 180}
                                ,{field: 'backlogTimeSlot', title: '时间安排', align: "center", width: 117}
                                ,{field: 'backlogPriority', title: '重要紧急程度', align: "center", width: 120}
                                ,{field: 'backlogContent', title: '内容', align: "center"}
                            ]]
                        })
                    }
                }
            })
        }

        //待办事项定时冲突后，强行插入
        function insertBacklog(userId,backlogRecordId,startTime,endTime) {
            layer.open({
                title: '待办事项插入确认',
                area :['260px','220px'],
                content: "此待办事项时间设定与现有日程表时间冲突，是否插入此事项，将冲突事项时间顺延？",
                btn: ["确定", "取消"],
                btnAlign: 'c',
                closeBtn: 0,
                btn1: function () {
                    $.ajax({
                        url: '<%=basePath%>insertBacklog.action',
                        type: 'post',
                        data: {
                            "userId": userId,
                            "backlogRecordId":backlogRecordId,
                            "backlogStartTimeStr": startTime,
                            "backlogEndTimeStr": endTime,
                        },
                        success: function (data) {
                            if (data.code == 200) {
                                layer.closeAll();
                                layer.msg("待办事项插入成功");
                                updateBacklog();
                            }
                        }
                    })
                },
                btn2: function () {
                    layer.closeAll();
                    layer.msg("时间设定与现有日程表冲突，请重新设置。");
                    document.getElementById("setBacklogTime").reset();
                }
            });
        }

    </script>
</html>
