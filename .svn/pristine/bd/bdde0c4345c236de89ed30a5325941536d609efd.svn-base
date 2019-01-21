<%@ page import="com.heli.oa.common.entity.User" %><%--
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
    User u = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>阳光值管理</title>
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
            <button class="layui-btn layui-btn-normal" onclick="addSunshine()">奖励一颗星星</button>
        </div>
        <div class="layui-btn-group" style="margin-left: 30px;">
            <button class="layui-btn layui-btn-normal" onclick="minusSunshine()">扣除一颗星星</button>
        </div>
        <div class="layui-btn-group" style="margin-left: 30px;">
            <button class="layui-btn layui-btn-normal" onclick="minusMoney()">提现</button>
        </div>
        <div class="layui-inline" style="margin-left: 30px;">
            <label>总扣款金额为：</label>
            <label id="sumMoney" style="color: #04cf0f"></label>
        </div>
        <div class="layui-inline" style="margin-left: 30px;">
            <label>可提现（已缴扣款）金额：</label>
            <label id="sumReceivedMoney" style="color: #cc0000"></label>
        </div>
        <div class="layui-tab layui-tab-brief" lay-fi；lter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this">奖励记录</li>
                <li onclick="updateMinusPage()">扣除记录</li>
                <li onclick="updateMoneyMinusPage()">提现记录</li>
                <li onclick="updateActionPage()">操作记录</li>
            </ul>
            <div class="layui-tab-content" style="height: 100px;">
                <div class="layui-tab-item layui-show">
                    <div style="width: 95%;height: 90%;margin-left: 30px;">
                        <table id="sunshinePlusTable" lay-filter="sunshinePlusTable" lay-skin="nob"></table>
                    </div>
                </div>
                <div class="layui-tab-item">
                    <div style="width: 95%;height: 90%;margin-left: 30px;">
                        <table id="sunshineMinusTable" lay-filter="sunshineMinusTable" lay-skin="nob"></table>
                    </div>
                </div>
                <div class="layui-tab-item">
                    <div style="width: 95%;height: 90%;margin-left: 30px;">
                        <table id="moneyMinusTable" lay-filter="moneyMinusTable" lay-skin="nob"></table>
                    </div>
                </div>
                <div class="layui-tab-item">
                    <div style="width: 95%;height: 90%;margin-left: 30px;">
                        <table id="actionTable" lay-filter="actionTable" lay-skin="nob"></table>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
        <script type="text/html" id="barMoney">
            <a class="layui-btn layui-btn layui-btn-xs" lay-event="getMoney">扣款到账点我</a>
        </script>
    </div>

    <form id="sunshineAdd" class="layui-form" action="addSunshine" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">员工部门:</label>
            <div class="layui-input-block" style="width: 230px;">
                <select id="department" lay-filter = "department" lay-verify="required">
                    <option></option>
                </select>  <%--暂时人工选择，后期应该实现和花名连接调用--%>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">员工花名:</label>
            <div class="layui-input-block" style="width: 230px;">
                <select id="userNickname"  lay-verify="required" lay-search="">
                    <option></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">奖励类别:</label>
        <div class="layui-input-block" style="width: 230px;">
            <select id="commentType1"  lay-verify="required">
                <option>个人贡献</option>
                <option>个人专长</option>
                <option>个人业绩</option>
            </select>
        </div>
    </div>
        <div class="layui-form-item">
            <label class="layui-form-label">奖励原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment" name="desc" lay-verify="required" placeholder="请输入阳光值增加原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <form id="sunshineMinus" class="layui-form" action="addSunshine" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">员工部门:</label>
            <div class="layui-input-block" style="width: 230px;">
                <select id="department1"  lay-filter="department1" lay-verify="required">
                    <option></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">员工花名:</label>
            <div class="layui-input-block" style="width: 230px;">
                <select id="userNickname1"  lay-verify="required" lay-search="">
                    <option></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">扣除类别:</label>
            <div class="layui-input-block" style="width: 230px;">
                <select id="commentType2"  lay-verify="required">
                    <option>业绩未达标</option>
                    <option>任务未达标</option>
                    <option>任务超时</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">扣除原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment1" name="desc" lay-verify="required" placeholder="请输入阳光值扣除原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <form id="minusMoney" class="layui-form" action="minusMoney" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">提现金额:</label>
            <input type="text" id="money" lay-verify="required" oninput="this.value=this.value.replace(/\D/g,'')" autocomplete="off" style="width: 230px;" placeholder="请输入提现金额" class="layui-input">
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">取现原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment2" name="minusMoney" lay-verify="required" placeholder="请输入取现原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <form id="delSunshine" class="layui-form" action="delSunshine" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">删除原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment3"  lay-verify="required" placeholder="请输入删除记录原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <form id="delMinusSunshine" class="layui-form" action="delMinusSunshine" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">删除原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment4"  lay-verify="required" placeholder="请输入删除记录原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <form id="delMoneySunshine" class="layui-form" action="delMoneySunshine" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">删除原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment5"  lay-verify="required" placeholder="请输入删除记录原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <form id="editSunshine" class="layui-form" action="editSunshine" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">编辑原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment6"  lay-verify="required" placeholder="请输入编辑记录原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <form id="editMinusSunshine" class="layui-form" action="deitMinusSunshine" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">编辑原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment7"  lay-verify="required" placeholder="请输入编辑记录原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <form id="editMoneySunshine" class="layui-form" action="editMoneySunshine" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">编辑原因:</label>
            <div class="layui-input-block" style="width: 230px;">
                <textarea id="comment8"  lay-verify="required" placeholder="请输入编辑记录原因" class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
    <script type="text/javascript">
        var table = null;
        var form = null;
        layui.use(['table','form','layer','laydate','element'],function () {
            table = layui.table;
            form = layui.form;//引入layui.form,然后对其render渲染，被渲染的部分一定要包裹在class= layui-form下
            var layer = layui.layer;
            var laydate = layui.laydate;
            var element = layui.element;

            form.render();
            laydate.render({
                elem: '#date'
            });
            updatePlusPage();
            sumMoney();
            listDepartment();

            //监听奖励阳光值弹窗中部门选择，然后列出对应部门员工花名
            form.on('select(department)', function(data){
                $("#userNickname").empty(); //清空上次传入select的option；
                $.ajax({
                    url:'<%=basePath%>listUserByDepartment.do',
                    type: 'post',
                    data:{
                        "userDepartment": data.value,
                    },
                    success:function (data) {
                        $(data.paramList).each(function () {
                            $("#userNickname").append("<option>"+ this.userNickname+ "</option>");
                        });
                        form.render('select');
                    }
                });
            });

            //监听扣除阳光值弹窗中部门选择，然后列出对应部门员工花名
            form.on('select(department1)', function(data){
                $("#userNickname1").empty(); //清空上次传入select的option；

                $.ajax({
                    url:'<%=basePath%>listUserByDepartment.do',
                    type: 'post',
                    data:{
                        "userDepartment": data.value,
                    },
                    success:function (data) {
                        $(data.paramList).each(function () {
                            $("#userNickname1").append("<option>"+ this.userNickname+ "</option>");
                        });
                        form.render('select');
                    }
                });
            });

            //监听奖励阳光值选项卡中对记录的编辑删除操作
            table.on('tool(sunshinePlusTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.open({
                        type:1 ,
                        content:$("#delSunshine"), //'是否确定删除此条部门记录'
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            var comment = $("#comment3").val();
                            if(comment == ""){
                                layer.msg("原因不能为空");
                                return false;
                            }
                            $.ajax({
                                url: '<%=basePath%>deletePlusSunshine.do',
                                type: 'post',
                                data: {
                                    "sunshineRecordId": data.sunshineRecordId,
                                    "sunshineNewAdminNickname":'<%=u.getUserNickname()%>',
                                    "sunshineActionComment":comment,
                                        },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        document.getElementById("delSunshine").reset();
                                        $("#delSunshine").css("display","none");
                                        updatePlusPage();
                                        updateActionPage();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("delSunshine").reset();
                            $("#delSunshine").css("display","none");
                            layer.close(layer.index)
                        },
                        cancel:function () {
                            document.getElementById("delSunshine").reset();
                            $("#delSunshine").css("display","none");
                        }
                    });
                } else if(obj.event === 'edit'){
                    layer.open({
                        type:1 ,
                        content:$("#editSunshine"), //'是否确定更新此条员工记录'
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            var comment = $("#comment6").val();
                            if(comment == ""){
                                layer.msg("原因不能为空");
                                return false;
                            }
                            $.ajax({
                                url: '<%=basePath%>updateSunshineRecord.do',
                                type: 'post',
                                data: {
                                    "sunshineRecordId": data.sunshineRecordId,
                                    "sunshineNewAdminNickname":'<%=u.getUserNickname()%>',
                                    "sunshineActionComment":comment,
                                    "sunshineComment":data.sunshineComment,
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        document.getElementById("editSunshine").reset();
                                        $("#editSunshine").css("display","none");
                                        updatePlusPage();
                                        updateActionPage();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("editSunshine").reset();
                            $("#editSunshine").css("display","none");
                            layer.close(layer.index)
                        },
                        cancel:function () {
                            document.getElementById("editSunshine").reset();
                            $("#editSunshine").css("display","none");
                        }
                    });
                }
            });

            //监听阳光值扣除记录table toolBar
            table.on('tool(sunshineMinusTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.open({
                        type:1 ,
                        content:$("#delMinusSunshine"), //'是否确定删除此条部门记录'
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            var comment = $("#comment4").val();
                            if(comment == ""){
                                layer.msg("原因不能为空");
                                return false;
                            }
                            $.ajax({
                                url: '<%=basePath%>deleteMinusSunshine.do',
                                type: 'post',
                                data: {
                                    "sunshineRecordId": data.sunshineRecordId,
                                    "sunshineNewAdminNickname":'<%=u.getUserNickname()%>',
                                    "sunshineActionComment":comment,
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        document.getElementById("delMinusSunshine").reset();
                                        $("#delMinusSunshine").css("display","none");
                                        updateMinusPage();
                                        updateActionPage();
                                        sumMoney();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("delMinusSunshine").reset();
                            $("#delMinusSunshine").css("display","none");
                            layer.close(layer.index)
                        },
                        cancel:function () {
                            document.getElementById("delMinusSunshine").reset();
                            $("#delMinusSunshine").css("display","none");
                        }
                    });
                } else if(obj.event === 'edit'){
                    layer.open({
                        type:1 ,
                        content:$("#editMinusSunshine"), //'是否确定更新此条员工记录'
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            var comment = $("#comment7").val();
                            if(comment == ""){
                                layer.msg("原因不能为空");
                                return false;
                            }
                            $.ajax({
                                url: '<%=basePath%>updateSunshineRecord.do',
                                type: 'post',
                                data: {
                                    "sunshineRecordId": data.sunshineRecordId,
                                    "sunshineNewAdminNickname":'<%=u.getUserNickname()%>',
                                    "sunshineActionComment":comment,
                                    "sunshineComment":data.sunshineComment,
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        document.getElementById("editMinusSunshine").reset();
                                        $("#editMinusSunshine").css("display","none");
                                        updateMinusPage();
                                        updateActionPage();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("editMinusSunshine").reset();
                            $("#editMinusSunshine").css("display","none");
                            layer.close(layer.index)
                        },
                        cancel:function () {
                            document.getElementById("editMinusSunshine").reset();
                            $("#editMinusSunshine").css("display","none");
                        }
                    });
                }else if(obj.event === 'getMoney'){
                    layer.open({
                        type:1 ,
                        content:"是否确定  "+ data.userNickname +"  此次扣款已到账？",
                        area: ['400px', '200px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            $.ajax({
                                url: '<%=basePath%>updateSunshineRecord.do',
                                type: 'post',
                                data: {
                                    "sunshineRecordId": data.sunshineRecordId,
                                    "sunshineNewAdminNickname":'<%=u.getUserNickname()%>',
                                    "sunshineMoneyStatus":"已缴款",
                                    "sunshineActionComment":"缴款确认",
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        updateMinusPage();
                                        sumMoney();
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

            //监听提现
            table.on('tool(moneyMinusTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.open({
                        type:1 ,
                        content:$("#delMoneySunshine"),
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            var comment = $("#comment5").val();
                            if(comment == ""){
                                layer.msg("原因不能为空");
                                return false;
                            }
                            $.ajax({
                                url: '<%=basePath%>deleteMinusMoneySunshine.do',
                                type: 'post',
                                data: {
                                    "sunshineRecordId": data.sunshineRecordId,
                                    "sunshineNewAdminNickname":'<%=u.getUserNickname()%>',
                                    "sunshineActionComment":comment,
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        $("#comment5").empty();
                                        document.getElementById("delMoneySunshine").reset();
                                        $("#delMoneySunshine").css("display","none");
                                        updateMoneyMinusPage();
                                        updateActionPage();
                                        sumMoney();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("delMoneySunshine").reset();
                            $("#delMoneySunshine").css("display","none");
                            layer.close(layer.index)
                        },
                        cancel:function () {
                            document.getElementById("delMoneySunshine").reset();
                            $("#delMoneySunshine").css("display","none");
                        }
                    });
                } else if(obj.event === 'edit'){
                    layer.open({
                        type:1 ,
                        content:$("#editMoneySunshine"), //'是否确定更新此条员工记录'
                        area: ['400px', '300px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            var comment = $("#comment8").val();
                            if(comment == ""){
                                layer.msg("原因不能为空");
                                return false;
                            }
                            $.ajax({
                                url: '<%=basePath%>updateSunshineRecord.do',
                                type: 'post',
                                data: {
                                    "sunshineRecordId": data.sunshineRecordId,
                                    "sunshineNewAdminNickname":'<%=u.getUserNickname()%>',
                                    "sunshineActionComment":comment,
                                    "sunshineComment":data.sunshineComment,
                                },
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        document.getElementById("editMoneySunshine").reset();
                                        $("#editMoneySunshine").css("display","none");
                                        updateMoneyMinusPage();
                                        updateActionPage();
                                        layer.close(layer.index)  //关闭ID窗口
                                    }
                                }
                            })
                        },
                        btn2:function () {
                            document.getElementById("editMoneySunshine").reset();
                            $("#editMoneySunshine").css("display","none");
                            layer.close(layer.index)
                        },
                        cancel:function () {
                            document.getElementById("editMoneySunshine").reset();
                            $("#editMoneySunshine").css("display","none");
                        }
                    });

                }
            });
        })


        function addSunshine(){
            layer.open({
                type: 1 ,
                area: ['380px', '420px'],  //指定弹出层尺寸
                content: $("#sunshineAdd"),
                title:'请填写阳光值增加信息',
                btn:["提交"],
                btnAlign: 'c',

                btn1:function () {
                    var userNickname = $("#userNickname").val();
                    var department = $("#department").val();
                    var commentType = $("#commentType1").val();
                    var comment = $("#comment").val();
                    if(comment == ""){
                        layer.msg("奖励原因不能为空");
                        return false;
                    }
                    $.ajax({
                        url: '<%=basePath%>addSunshine.do',
                        type: 'post',
                        async: false,
                        data: {
                            "userNickname": userNickname,
                            "adminId": <%=u.getUserId()%>,
                            "adminNickName": "<%=u.getUserNickname()%>",
                            "userDepartment": department,
                            "sunshineCommentType":commentType,
                            "sunshineComment":comment,
                        },
                        beforeSend: function () {
                            layer.msg('奖励添加中，请稍等。。。',{icon: 16,shade:[0.4,'#000'],time:false});
                        },
                        success: function (data) {//suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                            if (data.code == 200) {
                                layer.closeAll();
                                layer.msg("奖励添加成功");
                                document.getElementById("sunshineAdd").reset();
                                $("#userNickname").empty();
                                $("#sunshineAdd").css("display","none");
                                sumMoney();
                                updatePlusPage();
                            }else if(data.code == 500){
                                layer.closeAll();
                                layer.msg("奖励添加失败，请联系管理员");
                                document.getElementById("sunshineAdd").reset();
                                $("#userNickname").empty();
                                $("#sunshineAdd").css("display","none");
                                sumMoney();
                                updatePlusPage();
                            }
                        }
                    })
                 },
                cancel:function () {
                    document.getElementById("sunshineAdd").reset();
                    $("#sunshineAdd").css("display","none");
                    $("#userNickname").empty();
                    updatePlusPage();
                }
            });
        }

        function minusSunshine(){
            layer.open({
                type: 1 ,
                area: ['380px', '420px'],  //指定弹出层尺寸
                content: $("#sunshineMinus"),
                title:'请填写阳光值扣除信息',
                btn:["提交"],
                btnAlign: 'c',

                btn1:function () {
                    var userNickname = $("#userNickname1").val();
                    var department = $("#department1").val();
                    var commentType = $("#commentType2").val();
                    var comment = $("#comment1").val();
                    if(comment == ""){
                        layer.msg("扣除原因不能为空");
                        return false;
                    }
                    $.ajax({
                        url: '<%=basePath%>minusSunshine.do',
                        type: 'post',
                        data: {
                            "userNickname": userNickname,
                            "adminId": <%=u.getUserId()%>,
                            "adminNickName": "<%=u.getUserNickname()%>",
                            "userDepartment": department,
                            "sunshineCommentType":commentType,
                            "sunshineComment":comment,
                        },
                        beforeSend: function () {
                            layer.msg('阳光值扣除中，请稍等。。。',{icon: 16,shade:[0.4,'#000'],time:false});
                        },
                        success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                            if (data.code == 200) {
                                layer.closeAll();
                                layer.msg('阳光值扣除成功');
                                document.getElementById("sunshineMinus").reset();
                                $("#sunshineMinus").css("display","none");
                                $("#userNickname1").empty();
                                sumMoney();
                                updateMinusPage();
                            }
                        }
                    })
                },
                cancel:function () {
                    document.getElementById("sunshineMinus").reset();
                    $("#sunshineMinus").css("display","none");
                    $("#userNickname1").empty();
                }
            });
        }

        function minusMoney() {
            layer.open({
                type: 1 ,
                area: ['380px', '300px'],  //指定弹出层尺寸
                content: $("#minusMoney"),
                title:'提现',
                btn:["提交"],
                btnAlign: 'c',

                btn1:function () {
                    var miusMoney = $("#money").val();
                    var comment = $("#comment2").val();

                    if(minusMoney == ''){
                        layer.msg("提现金额不能为空");
                        return false;
                    }else if(comment == ''){
                        layer.msg("提现原因不能为空");
                        return false;
                    }
                    $.ajax({
                        url: '<%=basePath%>minusSunshineMoney.do',
                        type: 'post',
                        async: false,
                        data: {
                            "adminId":<%=u.getUserId()%>,
                            "adminNickname": "<%=u.getUserNickname()%>",
                            "sunshineMoney":miusMoney,
                            "sunshineComment":comment,
                        },
                        beforeSend: function () {
                            layer.msg('取现操作中，请稍等。。。',{icon: 16,shade:[0.4,'#000'],time:false});
                        },
                        success: function (data) {

                            if (data.code == 500) {
                                layer.msg("取现失败：超出可取现最大金额");
                            }
                            if (data.code == 200) {
                                layer.closeAll();
                                document.getElementById("minusMoney").reset();
                                $("#minusMoney").css("display","none");
                                sumMoney();
                                updateMoneyMinusPage();
                                layer.msg("取现成功",{time: 2000});
                            }
                        }
                    })
                },
                cancel:function () {
                    document.getElementById("minusMoney").reset();
                    $("#minusMoney").css("display","none");
                }
            });
        }

        var moneySum = null;
        var receivedMoneySum = null;
        function sumMoney() {
            $.ajax({
                url: '<%=basePath%>sumSunshineMoney.do',
                type: 'post',
                success: function (data) {
                    if (data.code == 200) {
                        moneySum = data.paramList[0];
                        receivedMoneySum = data.paramList[1];
                        $("#sumMoney").html(moneySum);
                        $("#sumReceivedMoney").html(receivedMoneySum);
                    }
                }
            })

        }

        //向增减阳光值弹窗中的部门选择列表填充部门名称
        function listDepartment() {
            $.ajax({
                url:'<%=basePath%>listDepartment.do',
                success:function (data) {
                    $(data.paramList).each(function () {
                        $("#department").append("<option>"+ this.departmentName+ "</option>");
                        $("#department1").append("<option>"+ this.departmentName+ "</option>");
                    });
                    form.render("select");
                }
            });
        }

        function updatePlusPage() {
            $.ajax({
                url:'<%=basePath%>listPlusSunshine.do',
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#sunshinePlusTable', //指定原始表格元素选择器
                            data: data.paramList ,
                            toolbar: true,
                            defaultToolbar: ['filter', 'print', 'exports'],
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                ,count:100
                                ,curr: 1 //设定初始在第 5 页
                                ,groups: 3 //只显示 1 个连续页码
                                ,first: false //不显示首页
                                ,last: false //不显示尾页
                            },
                            cols: [[ //表头
                                {align:'center',field: 'sunshineRecordId', title: '记录ID',width:80, sort: true}
                                ,{align:'center',field: 'userNickname', title: '员工花名',width:100,sort: true}
                                ,{align:'center',field: 'userDepartment', title: '部门',width:75,sort: true}
                                ,{align:'center',field: 'sunshineStarHtml', title: '阳光值增减',width:100}
                                ,{align:'center',field: 'adminNickname', title: '操作者',width:75}
                                ,{align:'center',field: 'sunshineMoney', title: '金额变动',width:90}
                                ,{align:'center',field: 'sunshineTime', title: '操作时间', sort: true,width:200}
                                ,{align:'center',field: 'sunshineCommentType', title: '奖励类型',width:100}
                                ,{align:'center',field: 'sunshineComment', title: '操作原因',edit:'text',width:545}
                                ,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right',width:115}
                            ]]
                        })
                    }
                }
            })
        }

        function updateMinusPage() {
            $.ajax({
                url:'<%=basePath%>listMinusSunshine.do',
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#sunshineMinusTable', //指定原始表格元素选择器
                            data: data.paramList ,
                            toolbar: true,
                            defaultToolbar: ['filter', 'print', 'exports'],
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                ,count:100
                                ,curr: 1 //设定初始在第 5 页
                                ,groups: 3 //只显示 1 个连续页码
                                ,first: false //不显示首页
                                ,last: false //不显示尾页
                            },
                            cols: [[ //表头
                                {align:'center',field: 'sunshineRecordId', title: '记录ID',edit:'text',width:75}
                                ,{align:'center',field: 'userNickname', title: '员工花名',width:90}
                                ,{align:'center',field: 'userDepartment', title: '部门',width:75}
                                ,{align:'center',field: 'adminNickname', title: '操作者',width:75}
                                ,{align:'center',field: 'sunshineMoney', title: '扣款金额',width:90}
                                ,{align:'center',field: 'sunshineOverdueFine', title: '滞纳金',width:72}
                                ,{align:'center',field: 'sunshineTotalMoney', title: '应缴金额',width:90}
                                ,{align:'center',field: 'sunshineTime', title: '操作时间', sort: true,width:170}
                                ,{align:'center',field: 'sunshineCommentType', title: '扣除类型',width:100}
                                ,{align:'center',field: 'sunshineComment', title: '操作原因',edit:'text',width:435}
                                ,{align:'center',field: 'sunshineMoneyStatus', title: '缴款状态',width:90}
                                ,{align:'center',title: '缴费确认',toolbar: '#barMoney', fixed: 'right',width:115}
                                ,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right',width:115}
                            ]]
                        })
                    }
                }
            })
        }

        /** 刷新提现记录表格*/
        function updateMoneyMinusPage() {
            $.ajax({
                url:'<%=basePath%>listSunshineMoney.do',
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#moneyMinusTable', //指定原始表格元素选择器
                            data: data.paramList ,
                            toolbar: true,
                            defaultToolbar: ['filter', 'print', 'exports'],
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                ,count:100
                                ,curr: 1 //设定初始在第 5 页
                                ,groups: 3 //只显示 1 个连续页码
                                ,first: false //不显示首页
                                ,last: false //不显示尾页
                            },
                            cols: [[ //表头
                                 {align:'center',field: 'sunshineRecordId', title: '记录ID',width:80, sort: true}
                                ,{align:'center',field: 'adminNickname', title: '操作者',width:75}
                                ,{align:'center',field: 'sunshineTotalMoney', title: '金额变动',width:90}
                                ,{align:'center',field: 'sunshineTime', title: '操作时间', sort: true,width:200}
                                ,{align:'center',field: 'sunshineComment', title: '操作原因',edit:'text',width:660}
                                ,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right',width:115}
                                ]]
                        })
                    }
                }
            })
        }

        function updateActionPage() {
            $.ajax({
                url:'<%=basePath%>listSunshineAction.do',
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#actionTable', //指定原始表格元素选择器
                            data: data.paramList ,
                            toolbar: true,
                            defaultToolbar: ['filter', 'print', 'exports'],
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                ,count:100
                                ,curr: 1 //设定初始在第 5 页
                                ,groups: 3 //只显示 1 个连续页码
                                ,first: false //不显示首页
                                ,last: false //不显示尾页
                            },
                            cols: [[ //表头
                                {align:'center',field: 'sunshineRecordId', title: '被操作记录ID',width:122, sort: true}
                                ,{align:'center',field: 'userNickname', title: '原记录员工',width:103}
                                ,{align:'center',field: 'sunshineStarHtml', title: '原记录阳光值增减',width:145}
                                ,{align:'center',field: 'sunshineAdminNickname', title: '原记录操作者',width:115}
                                ,{align:'center',field: 'sunshineNewAdminNickname', title: '本次操作者',width:105}
                                ,{align:'center',field: 'sunshineOldTime', title: '原记录时间',width:175}
                                ,{align:'center',field: 'sunshineOldComment', title: '原记录备注',width:175}
                                ,{align:'center',field: 'sunshineAction', title: '编辑/删除',width:94}
                                ,{align:'center',field: 'sunshineActionComment', title: '本次操作原因',width:175}
                                ,{align:'center',field: 'sunshineActionTime', title: '操作时间', sort: true,width:167}
                            ]]
                        })
                    }
                }
            })
        }
    </script>
</body>
</html>
