<%--
  Created by IntelliJ IDEA.
  User: 白驹
  Date: 2018/11/15
  Time: 15:15
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
        <title>任务系统</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="icon" href="<%=basePath%>static/image/favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" href="<%=basePath%>static/layui/css/layui.css" media="all">
        <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
        <script src="<%=basePath%>static/layui/layui.all.js"></script>
        <script src="<%=basePath%>static/layui/layui-xtree.js"></script>
        <script src="<%=basePath%>static/js/base64.js"></script>
        <style type="text/css">
            .double {
                width: 600px;
                float: left;
            }
            #newTaskForm .layui-form .layui-form-label{
                width: 150px;
            }
        </style>
    </head>
    <body style="background-color: white;">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">新建任务</li>
            <li onclick="updateToAccept()">待接收任务</li>
            <li onclick="updateToCreate()">待发布任务</li>
            <li onclick="updateDoing()">进行中任务</li>
            <li onclick="updateDone();updateTimeOut()">超时或已完成任务</li>
            <li onclick="updateToAudit();updateAppealToAudit()">待审核任务</li>
            <li onclick="updateCreated()">我发布的任务</li>
        </ul>
        <div class="layui-tab-content" style="height: 100px;">
            <div class="layui-tab-item layui-show">
                <div class="double">
                    <form id="newTaskForm" class="layui-form" style="width: 800px;">
                        <br><br>
                        <div class="layui-form-item">
                            <label class="layui-form-label">任务标题:</label>
                            <div class="layui-input-block">
                                <input type="text" id="taskTitle" name="taskTitle" lay-verify="required" autocomplete="off"
                                       style="width: 400px;" placeholder="请输入任务标题" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">任务类别：</label>
                            <div class="layui-input-block">
                                <input type="radio" name="taskType" value="1" title="单次任务" lay-filter="taskType" checked="">
                                <input type="radio" name="taskType" value="2" title="重复任务" lay-filter="taskType">
                                <input type="radio" name="taskType" value="3" title="限期完成" lay-filter="taskType">
                            </div>
                        </div>
                        <div class="layui-form-item" id="limitTaskDateTimeDiv" style="display: none">
                            <label class="layui-form-label">执行时间</label>
                            <div class="layui-input-inline" style="width: 190px;">
                                <input type="text" class="layui-input" id="limitTaskDateTime" name="limitTaskDateTime"
                                       autocomplete="off" placeholder="yyyy-MM-dd HH:mm:ss">
                            </div>
                            <div style="color: #cc0000;font-size: large">请选择时间</div>
                        </div>
                        <div class="layui-form-item" id="repeatCreateTimeDiv" style="display: none">
                            <label class="layui-form-label">任务发布时间:</label>
                            <div class="layui-input-inline" style="width: 150px;">
                                <select name="repeatWay" id="repeatWay" lay-filter="repeatWay" style="width: 100px;">
                                    <option value="4">请选择重复方式</option>
                                    <option value="1">每周</option>
                                    <option value="2">每月</option>
                                    <option value="3">每天</option>
                                </select>
                            </div>
                            <div class="layui-input-inline" id="selectWeekdayDiv" style="display: none;width: 80px;">
                                <select name="selectWeekday" id="selectWeekday" lay-filter="selectWeekday">
                                    <option value="MON">周一</option>
                                    <option value="TUE">周二</option>
                                    <option value="WED">周三</option>
                                    <option value="THU">周四</option>
                                    <option value="FRI">周五</option>
                                    <option value="SAT">周六</option>
                                    <option value="SUN">周日</option>
                                </select>
                            </div>
                            <div class="layui-input-inline" id="selectDayDiv" style="display: none;width: 80px;">
                                <select name="selectDay" id="selectDay" lay-filter="selectDay">
                                    <option value="1">1日</option>
                                    <option value="2">2日</option>
                                    <option value="3">3日</option>
                                    <option value="4">4日</option>
                                    <option value="5">5日</option>
                                    <option value="6">6日</option>
                                    <option value="7">7日</option>
                                    <option value="8">8日</option>
                                    <option value="9">9日</option>
                                    <option value="10">10日</option>
                                    <option value="11">11日</option>
                                    <option value="12">12日</option>
                                    <option value="13">13日</option>
                                    <option value="14">14日</option>
                                    <option value="15">15日</option>
                                    <option value="16">16日</option>
                                    <option value="17">17日</option>
                                    <option value="18">18日</option>
                                    <option value="19">19日</option>
                                    <option value="20">20日</option>
                                    <option value="21">21日</option>
                                    <option value="22">22日</option>
                                    <option value="23">23日</option>
                                    <option value="24">24日</option>
                                    <option value="25">25日</option>
                                    <option value="26">26日</option>
                                    <option value="27">27日</option>
                                    <option value="28">28日</option>
                                    <option value="29">29日</option>
                                    <option value="30">30日</option>
                                </select>
                            </div>
                            <div class="layui-input-inline" id="repeatTaskTimeDiv" style="width: 100px; display: none">
                                <input type="text" class="layui-input" id="repeatTaskTimeInput" autocomplete="off"
                                       placeholder="HH:mm:ss">
                            </div>
                            <div></div>
                        </div>
                        <div class="layui-form-item" id="repeatTaskLimitTimeDiv" style="display: none">
                            <label class="layui-form-label">任务提交期限:</label>
                            <div class="layui-input-inline" style="width: 300px;">
                                <div class="layui-input-inline" style="width: 60px;float:left;">任务发布</div>
                                <input type="text" class="layui-input-inline" id="day" name="day" autocomplete="off"
                                       style="width: 50px;float:left;">
                                <div class="layui-input-inline" style="width: 15px;float:left;">天</div>
                                <input type="text" class="layui-input-inline" id="hour" name="hour" autocomplete="off"
                                       style="width: 50px;float:left;">
                                <div class="layui-input-inline" style="width: 50px;float:left;">小时后</div>
                            </div>
                        </div>
                        <div class="layui-form-item" id="repeatTaskRemindTimeDiv" style="display: none">
                            <label class="layui-form-label">任务到期前提醒:</label>
                            <div class="layui-input-inline" style="width: 400px;">
                                <input type="text" class="layui-input-inline" id="remindHour" name="hour" autocomplete="off" value = "1"
                                       style="width: 50px;float:left;">
                                <div class="layui-input-inline" style="width: 300px;float:left;">小时</div>
                            </div>
                        </div>
                        <div class="layui-form-item" id="repeatTaskEndTimeDiv" style="display: none">
                            <label class="layui-form-label">重复任务周期结束时间:</label>
                            <div id="setNewDate" class="layui-input-inline" style="width: 190px;">
                                <input type="text" class="layui-input" id="repeatTaskEndTimeInput"
                                       name="repeatTaskEndTimeInput" autocomplete="off" placeholder="yyyy-MM-dd">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">任务内容:</label>
                            <div class="layui-input-block" style="width: 400px;">
                                <textarea id="content" name="content" lay-verify="required" placeholder="请输入任务内容" rows="3"
                                          cols="20" class="layui-textarea"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">人员选择:</label>
                            <div class="layui-input-block" style="width: 400px;">
                                <div id="userTree" class="xtree_contianer"
                                     style="width:400px;">
                                </div>
                            </div>
                        </div>

                        <div align="center">
                            <button class="layui-btn" id="createTask" lay-submit lay-filter="createTask">发布任务</button>
                        </div>

                    </form>
                </div>
            </div>

            <div class="layui-tab-item ">
                <div style="width: 95%;height: 90%;margin-left: 30px;">
                    <table id="toAcceptTable" lay-filter="toAcceptTable" lay-skin="nob"></table>
                </div>
            </div>

            <div class="layui-tab-item ">
                <div style="width: 95%;height: 90%;margin-left: 30px;">
                    <table id="toCreateTable" lay-filter="toCreateTable" lay-skin="nob"></table>
                </div>
            </div>

            <div class="layui-tab-item ">
                <div style="width: 95%;height: 90%;margin-left: 30px;">
                    <table id="doingTable" lay-filter="doingTable" lay-skin="nob"></table>
                </div>
            </div>

            <div class="layui-tab-item ">

                <div style="width: 95%;margin-left: 30px;">
                    <div>已完成任务</div>
                    <table id="doneTable" lay-filter="doneTable" lay-skin="nob"></table>
                </div>

                <div style="width: 95%;margin-left: 30px;">
                    <div>超时任务</div>
                    <table id="timeOutTable" lay-filter="timeOutTable" lay-skin="nob"></table>
                </div>

            </div>

            <div class="layui-tab-item ">
                <div style="width: 95%;margin-left: 30px;">
                    <div>待审核任务报告</div>
                    <table id="toAuditTable" lay-filter="toAuditTable" lay-skin="nob"></table>
                </div>
                <div style="width: 95%;margin-left: 30px;">
                    <div>待审核任务申诉</div>
                    <table id="appealToAuditTable" lay-filter="appealToAuditTable" lay-skin="nob"></table>
                </div>
            </div>

            <div class="layui-tab-item ">
                <div style="width: 95%;height: 90%;margin-left: 30px;">
                    <table id="createTable" lay-filter="createTable" lay-skin="nob"></table>
                </div>
            </div>
        </div>
    </div>

    <form id="refuseTaskReport" class="layui-form" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">拒绝任务原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment"  lay-verify="required" placeholder="请输入拒绝任务原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>

    <form id="nopassTaskReport" class="layui-form" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">不合格原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="nopassComment"  lay-verify="required" placeholder="请输入审核不合格原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>

    <form id="nopassTaskAppeal" class="layui-form" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">不合格原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="nopassComment1"  lay-verify="required" placeholder="请输入申诉原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>

    <form id="nopassTaskAppea2" class="layui-form" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">不合格原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="nopassComment2"  lay-verify="required" placeholder="请输入申诉不通过原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>

    <script type="text/html" id="bar">
        <a class="layui-btn layui-btn-xs" lay-event="over">提交报告</a>
    </script>
    <script type="text/html" id="bar2">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="cancel">取消任务</a>
    </script>
    <script type="text/html" id="bar3">
        <a class="layui-btn layui-btn layui-btn-xs" lay-event="details">任务详情</a>
    </script>
    <script type="text/html" id="bar5">
        <button class="layui-btn layui-btn-xs" lay-event="over">完成</button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="cancel">撤销</button>
    </script>
    <script type="text/html" id="bar7">
        <button class="layui-btn layui-btn-xs" lay-event="accept">接受</button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="refuse">拒绝</button>
    </script>
    <script type="text/html" id="auditBar">
        <a class="layui-btn  layui-btn-xs" lay-event="pass">合格</a>
        <a class="layui-btn  layui-btn-xs layui-btn-danger" lay-event="no">不合格</a>
    </script>
    <script type="text/html" id="appealAuditBar">
        <a class="layui-btn  layui-btn-xs" lay-event="pass">同意</a>
        <a class="layui-btn  layui-btn-xs layui-btn-danger" lay-event="no">不同意</a>
    </script>
    <script type="text/html" id="timeOutBar">
        <a class="layui-btn  layui-btn-xs" lay-event="appeal">申诉</a>
    </script>

    </body>
    <script type="text/javascript">
        var table = null;
        var form = null;
        layui.use(['table', 'form', 'layer', 'laydate'], function () {
            table = layui.table;
            form = layui.form;
            var laydate = layui.laydate;
            var layer = layui.layer;

            form.render();

            //格式化日期时间为yyyy-MM-dd HH:mm:ss
            function timeStamp2String(time) {
                var datetime = new Date();
                datetime.setTime(time);
                var year = datetime.getFullYear();
                var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
                var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
                var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
                var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
                var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
                return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
            }

            laydate.render({
                elem: '#limitTaskDateTime',
                type: 'datetime',
                min: timeStamp2String(new Date().getTime())
            });
            laydate.render({ //发布时间
                elem: '#date'
            });
            laydate.render({
                elem: '#repeatTaskTimeInput'
                , type: 'time'
            });

            //根据任务类型，显示隐藏对应div
            var taskType = '单次任务';
            form.on('radio(taskType)', function (data) {
                taskType = data.elem.title;
                switch (parseInt(data.value)) {
                    case 1 :
                        $("#limitTaskDateTimeDiv").hide();   
                        $("#repeatCreateTimeDiv").hide();
                        $("#repeatTaskLimitTimeDiv").hide();
                        $("#repeatTaskRemindTimeDiv").hide();
                        $("#repeatTaskEndTimeDiv").hide();
                        $("#limitTaskDateTime").removeAttr("lay-verify");
                        $("#repeatTaskTimeInput").removeAttr("lay-verify");
                        $("#day").removeAttr("lay-verify");
                        $("#hour").removeAttr("lay-verify");
                        $("#repeatTaskEndTimeInput").removeAttr("lay-verify");
                        break;
                    case 2 :
                        $("#limitTaskDateTimeDiv").hide();
                        $("#repeatCreateTimeDiv").show();
                        $("#repeatTaskRemindTimeDiv").show();
                        $("#repeatTaskLimitTimeDiv").show();
                        $("#repeatTaskEndTimeDiv").show();
                        $("#repeatTaskTimeInput").attr("lay-verify", "required");
                        $("#day").attr("lay-verify", "required");
                        $("#hour").attr("lay-verify", "required");
                        $("#repeatTaskEndTimeInput").attr("lay-verify", "required");
                        $("#limitTaskDateTime").removeAttr("lay-verify");
                        break;
                    case 3 :
                        $("#limitTaskDateTimeDiv").show();
                        $("#repeatTaskRemindTimeDiv").show();
                        $("#repeatCreateTimeDiv").hide();
                        $("#repeatTaskLimitTimeDiv").hide();
                        $("#repeatTaskEndTimeDiv").hide();
                        $("#limitTaskDateTime").attr("lay-verify", "required");
                        $("#repeatTaskTimeInput").removeAttr("lay-verify");
                        $("#day").removeAttr("lay-verify");
                        $("#hour").removeAttr("lay-verify");
                        $("#repeatTaskEndTimeInput").removeAttr("lay-verify");
                        break;
                }
            });

            //监听重复方式复选框，根据重复方式，显示或隐藏对应div
            form.on('select(repeatWay)', function (data) {
                //laydate.render()未生效，先removeinput，再加入
                $("#repeatTaskEndTimeInput").remove();
                $("#setNewDate").html("<input type=\"text\" class=\"layui-input\" id=\"repeatTaskEndTimeInput\" name =\"repeatTaskEndTimeInput\" autocomplete=\"off\"  placeholder=\"yyyy-MM-dd\" >");
                switch (parseInt(data.value)) {
                    //周
                    case 1 :
                        $("#selectWeekdayDiv").show();
                        $("#selectDayDiv").hide();
                        $("#repeatTaskTimeDiv").show();
                        $("#limitTaskDateTime").removeAttr("lay-verify");
                        laydate.render({
                            elem: '#repeatTaskEndTimeInput',
                            type: 'datetime',
                            min: timeStamp2String(new Date().getTime() + 604800000)
                        });
                        break;
                    //月
                    case 2 :
                        $("#selectWeekdayDiv").hide();
                        $("#selectDayDiv").show();
                        $("#repeatTaskTimeDiv").show();
                        $("#limitTaskDateTime").removeAttr("lay-verify");
                        laydate.render({
                            elem: '#repeatTaskEndTimeInput',
                            type: 'datetime',
                            min: timeStamp2String(new Date().getTime() + 2419200000)
                        });
                        break;
                    //天
                    case 3 :
                        $("#selectWeekdayDiv").hide();
                        $("#selectDayDiv").hide();
                        $("#repeatTaskTimeDiv").show();
                        $("#limitTaskDateTime").removeAttr("lay-verify");
                        laydate.render({
                            elem: '#repeatTaskEndTimeInput',
                            type: 'datetime',
                            min: timeStamp2String(new Date().getTime() + 86400000)
                        });
                        break;
                    case 4 :
                        $("#selectWeekdayDiv").hide();
                        $("#selectDayDiv").hide();
                        $("#repeatTaskTimeDiv").hide();
                        $("#limitTaskDateTime").removeAttr("lay-verify");
                }
            });

            //新建任务提交监听
            form.on('submit(createTask)', function (data) {
                //防止按钮二次触发
                $("#createTask").addClass("layui-btn-disabled");

                var obj = data.field;

                if ((obj.repeatWay == "4") && (taskType == "重复任务")) {
                    layer.msg("任务发布失败，请选择重复方式");
                    return false;
                }

                var oCks = xtree.GetChecked(); //获取人员管理数中选中的所有的人员
                var taskReceiver = '';
                //未选任务接收人，默认为当前用户自己
                if (oCks.length > 0) {
                    for (var i = 0; i < oCks.length; i++) {
                        taskReceiver = taskReceiver + oCks[i].value + ',';
                    }
                    taskReceiver = taskReceiver.substr(0, taskReceiver.length - 1);
                } else {
                    taskReceiver = '<%=u.getUserNickname()%>';
                }

                //拼接成字符串，后台解析成数组
                var taskRepeatWay = obj.repeatWay + ',' + obj.selectWeekday + ',' + obj.selectDay + ',' + $("#repeatTaskTimeInput").val();
                //换算成毫秒
                var day = $("#day").val();
                var hour = $("#hour").val();
                var time1 = day * 86400000 + hour * 3600000;
                var remindHour = $("#remindHour").val();

                $.ajax({
                    url: '<%=basePath%>addTask.do',
                    type: 'post',
                    async: false,
                    data: {
                        taskType: taskType,
                        taskTitle: obj.taskTitle,
                        taskCreator: '<%=u.getUserNickname()%>',
                        taskReceiver: taskReceiver,
                        taskContent: obj.content,
                        taskLimitTime: obj.limitTaskDateTime,
                        repeatTaskEndTime: obj.repeatTaskEndTimeInput,
                        taskRepeatWay: taskRepeatWay,
                        repeatTaskLimitTime: time1,
                        taskRemindHour: remindHour
                    },
                    beforeSend: function () {
                        layer.msg('任务发布中，请稍等。。。');
                    },
                    success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                        if (data.code == 200) {
                            layer.msg("任务发布成功，已通知相关人员");
                            document.getElementById("newTaskForm").reset();
                            taskType = "单次任务";
                            updateCreated();
                            updateDoing();
                            $("#limitTaskDateTimeDiv").hide();
                            $("#repeatCreateTimeDiv").hide();
                            $("#repeatTaskLimitTimeDiv").hide();
                            $("#repeatTaskEndTimeDiv").hide();
                            $("#createTask").removeClass("layui-btn-disabled");

                        }
                    }
                });
            });

            //待接受表格工具列监听
            table.on('tool(toAcceptTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event;

                if (layEvent === 'accept') { //通过审核
                    layer.confirm('确定接受任务？', function (index) {
                        layer.close(index);
                        $.ajax({
                            url: '<%=basePath%>acceptOrRefuseTask.do',
                            type: 'post',
                            data: {
                                'taskRecordId': data.taskRecordId,
                                'taskStatus': "已接受"
                            },
                            success: function () {
                                updateToAccept();
                            }
                        })
                    });

                } else if (layEvent === 'refuse') { //审核不通过，修改任务状态为未完成，并添加不合格原因
                    layer.open({
                        type: 1,
                        content: $("#refuseTaskReport"), //'是否确定删除此条部门记录'
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn: ["确定", "取消"],
                        btnAlign: 'c',
                        closeBtn: 0,
                        btn1: function () {
                            var comment = $("#comment").val();
                            $.ajax({
                                url: '<%=basePath%>acceptOrRefuseTask.do',
                                type: 'post',
                                data: {
                                    'taskRecordId': data.taskRecordId,
                                    'taskStatus': "已拒绝",
                                    "refuseComment": comment
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        //empty方法不管用
                                        document.getElementById("refuseTaskReport").reset();
                                        $("#refuseTaskReport").css("display", "none");
                                        updateToAccept();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2: function () {
                            document.getElementById("refuseTaskReport").reset();
                            $("#refuseTaskReport").css("display", "none");
                            layer.close(layer.index)
                        },
                    });
                }
            });

            //进行中任务工具栏按钮
            table.on('tool(doingTable)', function (obj) {
                var data = obj.data;
                var html1 = "<form id=\"taskReport\" class=\"layui-form\" " +
                    "    <br>\n" +
                    "    <div class=\"layui-form-item\" id=\"taskReport1\">\n" +
                    "      <br>  <label class=\"layui-form-label\">任务报告:</label>\n" +
                    "        <div class=\"layui-input-block\" style=\"width: 230px;\">\n" +
                    "            <textarea id=\"report\"  lay-verify=\"required\" placeholder=\"请输入任务报告\" class=\"layui-textarea\"></textarea>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</form>"
                if (obj.event === 'over') {
                    layer.open({
                        type: 1,
                        content: html1, //'是否确定删除此条部门记录'
                        area: ['400px', '250px'],  //指定弹出层尺寸
                        btn: ["提交", "取消"],
                        btnAlign: 'c',
                        btn1: function () {
                            var report = $("#report").val();
                            $.ajax({
                                url: '<%=basePath%>reportTaskByTaskRecordId.do',
                                type: 'post',
                                data: {
                                    "taskRecordId": data.taskRecordId
                                    ,'taskReport': report
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        layer.close(layer.index);  //关闭ID窗口
                                        layer.msg("任务报告提交成功");
                                        updateDoing();
                                        updateDone();
                                        updateCreated();
                                    }
                                }
                            })
                        },
                        btn2: function () {
                            document.getElementById("report").reset();
                            layer.close(layer.index)
                        }
                    });
                }

            });

            //报告待审核表格监听
            table.on('tool(toAuditTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
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
                            url: '<%=basePath%>auditTask.do',
                            type: 'post',
                            data: {'recordId': data.recordId,
                                'taskStatus': "已完成"},
                            success: function () {updateToAudit();}
                        })
                    });

                } else if(layEvent === 'no'){ //审核不通过，修改任务状态为未完成，并添加不合格原因
                    layer.open({
                        type:1 ,
                        content:$("#nopassTaskReport"), //'是否确定删除此条部门记录'
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        closeBtn: 0,
                        btn1:function () {
                            var comment = $("#nopassComment").val();
                            $.ajax({
                                    url: '<%=basePath%>auditTask.action',
                                type: 'post',
                                data: {'recordId': data.recordId,
                                    'taskStatus': "不通过",
                                    "nopassComment":comment
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        //empty方法不管用
                                        document.getElementById("nopassTaskReport").reset();
                                        $("#nopassTaskReport").css("display","none");
                                        updateToAudit();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("nopassTaskReport").reset();
                            $("#nopassTaskReport").css("display","none");
                            layer.close(layer.index)
                        },
                    });
                }
            });

            //超时表格监听
            table.on('tool(timeOutTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event;
                /*if(data.taskStatus ==="申诉中"){
                    layer.msg(" 抱歉，此任务已经申诉过，请耐心等待申诉结果。 ");
                    return false;
                }*/
                if(layEvent === 'appeal'){
                   /* layer.confirm('是否对该任务进行申诉？', function(index){
                        layer.close(index);
                        $.ajax({
                            url: '<%=basePath%>appealTask.do',
                            type: 'post',
                            data: {
                                'taskRecordId': data.taskRecordId,
                                'taskNoPassComment':comment
                                                            },
                            success: function () {updateTimeOut();}
                        })
                    });*/

                    layer.open({
                        type:1 ,
                        content:$("#nopassTaskAppeal"), //'是否确定删除此条部门记录'
                        area: ['400px', '250px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        closeBtn: 0,
                        btn1:function () {
                            var comment = $("#nopassComment1").val();
                            $.ajax({
                                url: '<%=basePath%>appealTask.do',
                                type: 'post',
                                data: {'taskRecordId': data.taskRecordId,
                                    'taskStatus': "已完成",
                                    "taskNoPassComment":comment
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        //empty方法不管用
                                        document.getElementById("nopassTaskAppeal").reset();
                                        $("#nopassTaskAppeal").css("display","none");
                                        updateAppealToAudit();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("nopassTaskAppeal").reset();
                            $("#nopassTaskAppeal").css("display","none");
                            layer.close(layer.index)
                        },
                    });
                }
            });

            //申诉待审核表格监听
            table.on('tool(appealToAuditTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event;

                if(layEvent === 'pass'){ //通过审核
                    layer.confirm('是否同意该申诉通过并恢复阳光值？', function(index){
                        layer.close(index);
                        $.ajax({
                            url: '<%=basePath%>auditAppealTask.do',
                            type: 'post',
                            data: {'taskRecordId': data.taskRecordId,
                                'taskStatus': "已完成"},
                            success: function () {updateAppealToAudit();}
                        })
                    });

                } else if(layEvent === 'no'){ //审核不通过，修改任务状态为未完成，并添加不合格原因
                    layer.open({
                        type:1 ,
                        content:$("#nopassTaskAppea2"), //'是否确定删除此条部门记录'
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        closeBtn: 0,
                        btn1:function () {
                            var comment = $("#nopassComment2").val();
                            $.ajax({
                                url: '<%=basePath%>auditAppealTask.do',
                                type: 'post',
                                data: {'taskRecordId': data.taskRecordId,
                                    'taskStatus': "不通过",
                                    "taskNoPassComment":comment
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        //empty方法不管用
                                        document.getElementById("nopassTaskAppea2").reset();
                                        $("#nopassTaskAppea2").css("display","none");
                                        updateAppealToAudit();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("nopassTaskAppea2").reset();
                            $("#nopassTaskAppea2").css("display","none");
                            layer.close(layer.index)
                        },
                    });
                }
            });

            /*todo 加入取消单个子任务功能*/
            //取消任务工具栏按钮
            table.on('tool(createTable)', function (obj) {
                var data = obj.data;
                if (obj.event === 'details') {

                    layer.open({
                        type: 2,
                        content: '<%=basePath%>sunshine/task_details?taskId=' + data.taskId, //'是否确定删除此条部门记录'
                        area: ['1700px', '600px'],  //指定弹出层尺寸
                        btn: ["确定"],
                        btnAlign: 'c',
                        data: data,
                        btn1: function () {
                            layer.close(layer.index);
                        },
                    });
                } else if (obj.event === 'cancel') {
                    layer.open({
                        type: 1,
                        content: '是否确定取消此任务', //'是否确定删除此条部门记录'
                        area: ['400px', '250px'],  //指定弹出层尺寸
                        btn: ["确定", "取消"],
                        btnAlign: 'c',
                        btn1: function () {
                            $.ajax({
                                url: '<%=basePath%>cancelTaskByTaskId.do',
                                type: 'post',
                                data:
                                {
                                    taskId : data.taskId,
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        layer.close(layer.index);  //关闭ID窗口
                                        layer.msg("任务已取消");
                                        updateCreated();
                                        updateDoing();
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
        })

        //创建任务页面，待选员工读取，用了layui第三方框架 Xtree
        var users = null;
        $.ajax({
            url: '<%=basePath%>listAllUserByDep.do',
            type: 'post',
            async: false,
            success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                if (data.code == 200) {
                    users = data.paramObject;
                }
            }
        });
        var json3 = null;
        var json4 = '';
        for (var key in users) {
            var json1 = '';
            for (var i = 0; i < users[key].length; i++) {
                var json2 = '{ "title": "' + users[key][i].userNickname + '", "value": "' + users[key][i].userNickname + '", "data": [] },'
                json1 = json1 + json2;
            };
            json1 = json1.substr(0, json1.length - 1);
            json3 = '{"title":"' + key + '", "value": "' + key + '", "data": [' + json1 + ' ]},';
            json4 = json4 + json3;
        }
        json4 = JSON.parse('[' + json4.substr(0, json4.length - 1) + ']');

        var xtree = new layuiXtree({
            elem: 'userTree'   //(必填) 放置xtree的容器id，不要带#号
            , form: form     //(必填) layui 的 from
            , data: json4    //(必填) json数组（数据格式在下面）
            , isopen: false
            , icon: {        //三种图标样式，更改几个都可以，用的是layui的图标
                end: "&#xe6af;"      //末尾节点的图标
            }
        });

        //刷新待接收任务
        function updateToAccept() {
            $.ajax({
                url: '<%=basePath%>listToAcceptTaskByReceiver.do',
                type: 'post',
                data: {"taskReceiver": '<%=u.getUserNickname()%>'},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#toAcceptTable', //指定原始表格元素选择器
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
                                {field: 'taskRecordId', title: '任务ID',width:75,align:'center'}
                                ,{field: 'taskCreator', title: '创建者',width:75,align:'center'}
                                ,{field: 'taskReceiver', title: '接收人',width:75,align:'center'}
                                ,{field: 'taskCreateTime', title: '创建时间',width:165,align:'center'}
                                ,{field: 'taskTitle', title: '标题',width:200,align:'center'}
                                ,{field: 'taskLimitTime', title: '完成期限',width:165,align:'center'}
                                ,{field: 'taskType', title: '任务类型',width:90,align:'center'}
                                ,{field: 'taskStatus', title: '任务状态',width:90,align:'center'}
                                ,{field: 'taskContent', title: '内容',width:400,align:'center'}
                                ,{title: '操作',toolbar: '#bar7', width:150,align:'center'}
                            ]]
                        })
                    }
                }
            })
        }

        //刷新已接收，待发布的任务
        function updateToCreate() {
            $.ajax({
                url: '<%=basePath%>listToCreateTaskByReceiver.do',
                type: 'post',
                data: {"taskReceiver": '<%=u.getUserNickname()%>'},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#toCreateTable', //指定原始表格元素选择器
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
                                {field: 'taskRecordId', title: '任务ID',width:75,align:'center'}
                                ,{field: 'taskCreator', title: '创建者',width:75,align:'center'}
                                ,{field: 'taskReceiver', title: '接收人',width:75,align:'center'}
                                ,{field: 'taskCreateTime', title: '创建时间',width:165,align:'center'}
                                ,{field: 'taskRepeatChinese', title: '发布时间',width:165,align:'center'}
                                ,{field: 'taskType', title: '任务类型',width:90,align:'center'}
                                ,{field: 'taskStatus', title: '任务状态',width:90,align:'center'}
                                ,{field: 'taskTitle', title: '标题',width:200,align:'center'}
                                ,{field: 'taskContent', title: '内容',width:400,align:'center'}
                            ]]
                        })
                    }
                }
            })
        }

        //刷新进行中任务
        function updateDoing() {
            $.ajax({
                url: '<%=basePath%>listDoingTaskByReceiver.do',
                type: 'post',
                data: {"taskReceiver": '<%=u.getUserNickname()%>'},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#doingTable', //指定原始表格元素选择器
                            data: data.paramList,     //相当于code.paramlist
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                , count: 100
                                , curr: 1 //设定初始在第 5 页
                                , groups: 3 //只显示 1 个连续页码
                                , first: false //不显示首页
                                , last: false //不显示尾页
                                , limit : 15
                            },
                            cols: [[ //表头
                                {field: 'taskRecordId', title: '任务ID',width:75,align:'center'}
                                ,{field: 'taskCreator', title: '创建者',width:75,align:'center'}
                                ,{field: 'taskReceiver', title: '接收人',width:75,align:'center'}
                                ,{field: 'taskCreateTime', title: '创建时间',width:165,align:'center'}
                                ,{field: 'taskTitle', title: '标题',width:200,align:'center'}
                                ,{field: 'taskLimitTime', title: '完成期限',width:165,align:'center'}
                                ,{field: 'taskType', title: '任务类型',width:90,align:'center'}
                                ,{field: 'taskStatus', title: '任务状态',width:90,align:'center'}
                                ,{field: 'taskContent', title: '内容',width:400,align:'center'}
                                ,{field: 'taskNoPassComment', title: '不合格原因',width:160,align:'center'}
                                ,{title: '操作',toolbar: '#bar', width:100,align:'center'}

                            ]]
                        })
                    }
                }
            })

        }

        //刷新已完成任务
        function updateDone() {
            $.ajax({
                url: '<%=basePath%>listDoneTaskByReceiver.do',
                type: 'post',
                data: {"taskReceiver": '<%=u.getUserNickname()%>'},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#doneTable', //指定原始表格元素选择器
                            data: data.paramList,     //相当于code.paramlist
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                , count: 100
                                , curr: 1 //设定初始在第 5 页
                                , groups: 3 //只显示 1 个连续页码
                                , first: false //不显示首页
                                , last: false //不显示尾页
                                , limit : 15
                            },
                            cols: [[ //表头
                                {field: 'taskRecordId', title: '任务ID', width: 75, align: 'center'}
                                , {field: 'taskCreator', title: '创建者', width: 75, align: 'center'}
                                , {field: 'taskReceiver', title: '接收人', width: 75, align: 'center'}
                                , {field: 'taskCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'taskType', title: '任务类型', width: 90, align: 'center'}
                                , {field: 'taskTitle', title: '标题', width: 150, align: 'center'}
                                , {field: 'taskContent', title: '内容', width: 210, align: 'center'}
                                , {field: 'taskStatus', title: '任务状态',width:90,align:'center'}
                                , {field: 'taskReportTime', title: '完成时间', width: 165, align: 'center'}
                                , {field: 'taskReport', title: '任务报告', width: 590, align: 'center'}

                            ]]
                        })
                    }
                }
            })
        }

        //刷新超时任务
        function updateTimeOut() {
            $.ajax({
                url: '<%=basePath%>listTimeOutTaskByReceiver.do',
                type: 'post',
                data: {"taskReceiver": '<%=u.getUserNickname()%>'},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#timeOutTable', //指定原始表格元素选择器
                            data: data.paramList,     //相当于code.paramlist
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                , count: 100
                                , curr: 1 //设定初始在第 5 页
                                , groups: 3 //只显示 1 个连续页码
                                , first: false //不显示首页
                                , last: false //不显示尾页
                                , limit : 15
                            },
                            cols: [[ //表头
                                {field: 'taskRecordId', title: '任务ID', width: 75, align: 'center'}
                                , {field: 'taskCreator', title: '创建者', width: 75, align: 'center'}
                                , {field: 'taskReceiver', title: '接收人', width: 75, align: 'center'}
                                , {field: 'taskCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'taskType', title: '任务类型', width: 90, align: 'center'}
                                , {field: 'taskTitle', title: '标题', width: 100, align: 'center'}
                                , {field: 'taskContent', title: '内容', width: 260, align: 'center'}
                                , {field: 'taskStatus', title: '任务状态', width: 100, align: 'center'}
                                , {field: 'taskReportTime', title: '完成时间', width: 150, align: 'center'}
                                , {field: 'taskReport', title: '任务报告', width: 350, align: 'center'}
                                , {title: '申诉',toolbar: '#timeOutBar', align: 'center'}
                            ]]
                        })
                    }
                }
            })
        }

        //刷新待审核任务
        function updateToAudit(){
            $.ajax({
                url: '<%=basePath%>listToAuditTaskByCreator.do',
                type: 'post',
                data: {"taskCreator": '<%=u.getUserNickname()%>'},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#toAuditTable', //指定原始表格元素选择器
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
                                /*{type: 'checkbox', fixed: 'left'}
                                ,*/
                                {field: 'taskReceiver', title: '接收人', width: 75, align: 'center'}
                                , {field: 'taskType', title: '任务类型', width: 90, align: 'center'}
                                , {field: 'taskTitle', title: '标题', width: 150, align: 'center'}
                                , {field: 'taskContent', title: '内容', width: 250, align: 'center'}
                                , {field: 'taskCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'taskLimitTime', title: '完成期限', width: 165, align: 'center'}
                                , {field: 'taskStatus', title: '任务状态', width: 90, align: 'center'}
                                , {field: 'taskReportTime', title: '完成时间', width: 165, align: 'center'}
                                , {field: 'taskReport', title: '任务报告', align: 'center'}
                                , {title: '任务审核',toolbar: '#auditBar', align: 'center'}
                            ]]
                        })
                    }
                }
            })
        }

        //根据任务创建人，刷新申诉待审核任务
        function updateAppealToAudit() {
            $.ajax({
                url: '<%=basePath%>listAppealToAuditTaskByCreator.do',
                type: 'post',
                data: {"taskCreator": '<%=u.getUserNickname()%>'},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#appealToAuditTable', //指定原始表格元素选择器
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
                                /*{type: 'checkbox', fixed: 'left'}
                                ,*/
                                {field: 'taskReceiver', title: '接收人', width: 75, align: 'center'}
                                , {field: 'taskType', title: '任务类型', width: 90, align: 'center'}
                                , {field: 'taskTitle', title: '标题', width: 150, align: 'center'}
                                , {field: 'taskContent', title: '内容', width: 250, align: 'center'}
                                , {field: 'taskCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'taskLimitTime', title: '完成期限', width: 165, align: 'center'}
                                , {field: 'taskStatus', title: '任务状态', width: 90, align: 'center'}
                                , {field: 'taskReportTime', title: '完成时间', width: 165, align: 'center'}
                                , {field: 'taskReport', title: '任务报告', align: 'center'}
                                , {field: 'taskNoPassComment', title: '申诉原因', align: 'center'}
                                , {title: '申诉审核',toolbar: '#appealAuditBar', align: 'center'}
                            ]]
                        })
                    }
                }
            })
        }

        //刷新我创建的任务
        function updateCreated() {
            $.ajax({
                url: '<%=basePath%>listFatherTaskByCreator.do',
                type: 'post',
                data: {"taskCreator": '<%=u.getUserNickname()%>'},
                success: function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#createTable', //指定原始表格元素选择器
                            data: data.paramList,     //相当于code.paramlist
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: [ 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                , count: 100
                                , curr: 1 //设定初始在第 5 页
                                , groups: 3 //只显示 1 个连续页码
                                , first: false //不显示首页
                                , last: false //不显示尾页
                                , limit : 15
                            },
                            cols: [[ //表头
                                /*{type: 'checkbox', fixed: 'left'}
                                ,*/
                                {field: 'taskId', title: '任务ID', width: 75, align: 'center'}
                                , {field: 'taskReceiver', title: '接收人', width: 167, align: 'center'}
                                , {field: 'taskCreateTime', title: '创建时间', width: 165, align: 'center'}
                                , {field: 'taskTitle', title: '标题', width: 200, align: 'center'}
                                , {field: 'taskType', title: '任务类型', width: 90, align: 'center'}
                                , {field: 'taskRepeatChinese', title: '重复方式', width: 152, align: 'center'}
                                , {field: 'taskContent', title: '内容', width: 400, align: 'center'}
                                , {field: 'taskLimitTime', title: '完成期限', width: 165, align: 'center'}
                                , {title: '任务状态', toolbar: '#bar3', width: 90, align: 'center'}
                                , {title: '取消任务', toolbar: '#bar2', width: 90, align: 'center'}
                            ]]
                        })
                    }
                }
            })
        }
    </script>
</html>
