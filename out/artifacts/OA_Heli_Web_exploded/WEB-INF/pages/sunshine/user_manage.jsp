<%--
  Created by IntelliJ IDEA.
  User: 白驹
  Date: 2019/01/11
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="com.heli.oa.common.entity.User"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
   User u = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>用户管理</title>
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
<body style="overflow: auto;height: 90%;width: 99%;background-color: white;">
    <div>
        <br><br>
        <div class="layui-btn-group" style="margin-left: 30px;">
            <button class="layui-btn layui-btn-normal insert" onclick="addUser()">新增</button>
        </div>
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this">全体员工</li>
                <li onclick="updateBeijingUserPage()">北京总部</li>
                <li onclick="updateBaoUserPage()">保定分公司</li>
            </ul>
            <div class="layui-tab-content" style="height: 100px;">
                <div class="layui-tab-item layui-show">
                    <div style="width: 95%;height: 90%;margin-left: 30px;">
                        <table id="allUserTable" lay-filter="userTable" lay-skin="nob"></table>
                    </div>
                </div>
                <div class="layui-tab-item">
                    <div style="width: 95%;height: 90%;margin-left: 30px;">
                        <table id="beijingTable" lay-filter="beijingTable" lay-skin="nob"></table>
                    </div>
                </div>
                <div class="layui-tab-item">
                    <div style="width: 95%;height: 90%;margin-left: 30px;">
                        <table id="baoTable" lay-filter="baoTable" lay-skin="nob"></table>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <form id="userInsert" class="layui-form" action="addUser" namespace="/" style="display: none;">
        <br><br>
        <div class="layui-form-item">
            <label class="layui-form-label">员工姓名:</label>
            <div class="layui-input-block">
                <input type="text" id="userName" required lay-verify="required" autocomplete="off" style="width: 230px;" placeholder="请输入员工姓名" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">员工花名:</label>
            <div class="layui-input-block">
                <input type="text" id="nickName"  autocomplete="off" style="width: 230px;" placeholder="请输入员工花名" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">员工部门:</label>
            <div class="layui-input-block" style="width: 230px;">
                <select id="department"  lay-verify="required">
                    <option></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">员工级别:</label>
            <div class="layui-input-block" style="width: 230px;">
                <select id="role"  lay-verify="required">
                        <option></option>
                        <option value="2">经理</option>
                    <option value="5">部门总监</option>
                    <option value="6">主管</option>
                    <option value="7">员工</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">员工城市:</label>
            <div class="layui-input-block" style="width: 230px;">
                <select id="city"  lay-verify="required">
                    <option>北京</option>
                    <option>保定</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">员工职位:</label>
            <div class="layui-input-block">
                <input type="text" id="position"  autocomplete="off" style="width: 230px;" placeholder="请输入员工职位" class="layui-input">
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
        <div class="layui-inline">
            <label class="layui-form-label">入职日期:</label>
            <div class="layui-input-block">
                <input type="text" name="joinDate" id="date" lay-verify="date"  autocomplete="off" style="width: 230px;" placeholder="yyyy-MM-dd" class="layui-input">
            </div>
        </div>
        <%--todo：员工照片项--%>
    </form>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="authority">权限管理</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
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
            updateAllUserPage();
            department();

            /*todo 监听删除不管用*/
            //监听行工具事件
            table.on('tool(userTable)', function(obj){
                var data = obj.data;
                var html1 = "<div align=\"center\">" +
                    "<p><label>是否删除此条员工记录：  </label>" + data.userName +"</p>" +
                    "</div>"        //待优化：调节垂直居中
                var html2 = "<div align=\"center\">" +
                    "<p><label>是否更新此条员工记录：  </label>" + data.userName +"-"+ data.userNickName + "</p>" +
                    "</div>"
                if(obj.event === 'del'){
                     if(data.userId == <%=u.getUserId()%>){
                        layer.msg("不能删除自己的账户！!!!");
                        return false;
                     }
                    layer.open({
                        type:1 ,
                        content:html1, //'是否确定删除此条部门记录'
                        area: ['400px', '200px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            $.ajax({
                                url: '<%=basePath%>deleteUser.do',
                                type: 'post',
                                async: false,
                                data: data,
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        updateAllUserPage();
                                        updateBaoUserPage();
                                        updateBeijingUserPage();
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
                     /*todo 改成弹窗形式*/
                } else if(obj.event === 'edit'){
                    layer.open({
                        type:1 ,
                        content:html2, //'是否确定更新此条员工记录'
                        area: ['400px', '200px'],  //指定弹出层尺寸
                        btn:["确定","取消"],
                        btnAlign: 'c',
                        btn1:function () {
                            $.ajax({
                                url: '<%=basePath%>updateUser.action',
                                type: 'post',
                                data: data,
                                success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                    if (data.code == 200) {
                                        updateAllUserPage();
                                        updateBaoUserPage();
                                        updateBeijingUserPage();
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

            table.on('tool(beijingTable)', function(obj){
            var data = obj.data;
            var html1 = "<div align=\"center\">" +
                "<p><label>是否删除此条员工记录：  </label>" + data.userName +"</p>" +
                "</div>"        //待优化：调节垂直居中
            var html2 = "<div align=\"center\">" +
                "<p><label>是否更新此条员工记录：  </label>" + data.userName +"-"+ data.userNickName + "</p>" +
                "</div>"
            //console.log(obj)
            if(obj.event === 'del'){
                layer.open({
                    type:1 ,
                    content:html1, //'是否确定删除此条部门记录'
                    area: ['400px', '200px'],  //指定弹出层尺寸
                    btn:["确定","取消"],
                    btnAlign: 'c',
                    btn1:function () {
                        $.ajax({
                            url: '<%=basePath%>user_delete.action',
                            type: 'post',
                            data: {"userId": data.userId},
                            success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                if (data.code == 200) {
                                    updateAllUserPage();
                                    updateBeijingUserPage();
                                    /* $("userInsert":visible).hide();*/
                                    layer.close(layer.index)  //关闭ID窗口
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
                            url: '<%=basePath%>user_update.action',
                            type: 'post',
                            data: data,
                            success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                if (data.code == 200) {
                                    updateAllUserPage();
                                    updateBeijingUserPage();
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

            table.on('tool(baoTable)', function(obj){
            var data = obj.data;
            var html1 = "<div align=\"center\">" +
                "<p><label>是否删除此条员工记录：  </label>" + data.userName +"</p>" +
                "</div>"        //待优化：调节垂直居中
            var html2 = "<div align=\"center\">" +
                "<p><label>是否更新此条员工记录：  </label>" + data.userName +"-"+ data.userNickName + "</p>" +
                "</div>"
            //console.log(obj)
            if(obj.event === 'del'){
                layer.open({
                    type:1 ,
                    content:html1,
                    area: ['400px', '200px'],
                    btn:["确定","取消"],
                    btnAlign: 'c',
                    btn1:function () {

                        $.ajax({
                            url: '<%=basePath%>user_delete.action',
                            type: 'post',
                            data: {"userId": data.userId},
                            success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                if (data.code == 200) {
                                    updateAllUserPage();
                                    updateBaoUserPage();
                                    layer.close(layer.index)  //关闭ID窗口
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
                            url: '<%=basePath%>user_update.action',
                            type: 'post',
                            data: data,
                            success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                                if (data.code == 200) {
                                    updateAllUserPage();
                                    updateBaoUserPage();
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

        /*todo 改成form提交，并添加非空验证*/
        function addUser(){
            layer.open({
                type: 1 ,
                area: ['380px', '600px'],  //指定弹出层尺寸
                content: $("#userInsert"),
                title:'请填写新增用户信息',
                btn:["提交"],
                btnAlign: 'c',

                btn1:function () {
                    var userName = $("#userName").val();
                   //等效于 var name=document.getElementById('name').value;
                    var nickName = $("#nickName").val();
                    var city = $("#city").val();
                    var department = $("#department").val();
                    var role = $("#role").val();
                    var position = $("#position").val();
                    var phone = $("#phone").val();
                    var mail = $("#mail").val();
                    var date = $("#date").val();
                    var qq =  $("#QQ").val();
                    var wechat =  $("#wechat").val();
                    $.ajax({
                        url: '<%=basePath%>addUser.action',
                        type: 'post',
                        data: {"userName": userName,
                            "userNickName": nickName,
                            "userCity": city,
                            "userDepartment": department,
                            "userRole":role,
                            "userPositionChinese": position,
                            "userPhone":phone,
                            "userMail":mail,
                            "userJoinDate": date,
                            "userQQ":qq,
                            "userWechat": wechat
                        },
                        success: function (data) {       //suceess是ajax回调，返回的是data ，data即code对象，code包含数组集合，分页集合等内容
                            if (data.code == 200) {
                                document.getElementById("userInsert").reset();
                                $("#userInsert").css("display","none");
                                $("#userName").empty();
                                updateBaoUserPage();
                                updateAllUserPage();
                                updateBeijingUserPage();
                                layer.close(layer.index)  //关闭弹出来的窗口

                            }
                        }
                    })
                 },
                cancel:function () {
                    document.getElementById("userInsert").reset();
                    $("#userInsert").css("display","none");
                }
            });
        }

        function department() {
            $.ajax({
                url:'<%=basePath%>listDepartment.action',
                success:function (data) {
                    $(data.paramList).each(function () {
                        $("#department").append("<option>"+ this.departmentName+ "</option>");
                    });
                    form.render("select");
                }
            });
        }

        function updateAllUserPage() {
            $.ajax({
                url:'<%=basePath%>listAllUser.do',
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#allUserTable', //指定原始表格元素选择器
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
                                ,limit:15
                            },
                            cols: [[ //表头
                                {align:'center',field: 'userName', title: '姓名',edit:'text',width:78}
                                ,{align:'center',field: 'userNickname', title: '花名',edit:'text',width:60}
                                ,{align:'center',field: 'userDepartment', title: '部门',edit:'text', sort: true,width:80}
                                ,{align:'center',field: 'userPositionChinese', title: '职位名称',edit:'text', sort: true,width:100}
                                ,{align:'center',field: 'userCity', title: '城市',edit:'text',width:60}
                                ,{align:'center',field: 'userPhone', title: '电话',edit:'text',width:120}
                                ,{align:'center',field: 'userPlusStarHtml', title: '正阳光值', sort: true}
                                ,{align:'center',field: 'userMinusStarHtml', title: '负阳光值', sort: true}
                                ,{align:'center',field: 'userMail', title: '邮件地址',width:220}
                                ,{align:'center',field: 'userJoinDate', title: '入职时间',edit:'text', sort: true,width:105}
                                /* ,{align:'center',field: 'howLong', title: '在职时长', sort: true}*/
                                ,{align:'center',field: 'userStatus', title: '在职状态',edit:'text', sort: true,width:93}
                                ,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right',width:115}
                            ]]
                        })
                    }
                }
            })
        }

        function updateBeijingUserPage() {
            $.ajax({
                url:'<%=basePath%>listUserByCity.do',
                type: 'post',
                data:{"userCity":"北京"},
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#beijingTable', //指定原始表格元素选择器
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
                                ,limit:15
                            },
                            cols: [[ //表头
                                {align:'center',field: 'userName', title: '姓名',edit:'text',width:78}
                                ,{align:'center',field: 'userNickname', title: '花名',edit:'text',width:60}
                                ,{align:'center',field: 'userDepartment', title: '部门',edit:'text', sort: true,width:80}
                                ,{align:'center',field: 'userPositionChinese', title: '职位名称',edit:'text', sort: true,width:100}
                                ,{align:'center',field: 'userPhone', title: '电话',edit:'text',width:120}
                                ,{align:'center',field: 'userPlusStarHtml', title: '正阳光值', sort: true}
                                ,{align:'center',field: 'userMinusStarHtml', title: '负阳光值', sort: true}
                                ,{align:'center',field: 'userMail', title: '邮件地址',width:220}
                                ,{align:'center',field: 'userJoinDate', title: '入职时间',edit:'text', sort: true,width:105}
                                /* ,{align:'center',field: 'howLong', title: '在职时长', sort: true}*/
                                ,{align:'center',field: 'userStatus', title: '在职状态',edit:'text', sort: true,width:93}
                                /*,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right',width:115}*/
                            ]]
                        })
                    }
                }
            })
        }

        function updateBaoUserPage() {
            $.ajax({
                url:'<%=basePath%>listUserByCity.do',
                type: 'post',
                data:{'userCity':"保定"},
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#baoTable', //指定原始表格元素选择器
                            data: data.paramList ,
                            toolbar: true,
                            defaultToolbar: ['filter', 'print', 'exports'],
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                ,count:100
                                ,curr: 1 //设定初始在第 5 页
                                ,groups: 3 //只显示 1 个连续页码
                                ,first: false //不显示首页
                                ,last: false //不显示尾页
                                ,limit:15
                            },
                            cols: [[ //表头
                                {align:'center',field: 'userName', title: '姓名',edit:'text',width:78}
                                ,{align:'center',field: 'userNickname', title: '花名',edit:'text',width:60}
                                ,{align:'center',field: 'userDepartment', title: '部门',edit:'text', sort: true,width:80}
                                ,{align:'center',field: 'userPositionChinese', title: '职位名称',edit:'text', sort: true,width:100}
                                ,{align:'center',field: 'userPhone', title: '电话',edit:'text',width:120}
                                ,{align:'center',field: 'userPlusStarHtml', title: '正阳光值', sort: true}
                                ,{align:'center',field: 'userMinusStarHtml', title: '负阳光值', sort: true}
                                ,{align:'center',field: 'userMail', title: '邮件地址',width:220}
                                ,{align:'center',field: 'userJoinDate', title: '入职时间',edit:'text', sort: true,width:105}
                                /* ,{align:'center',field: 'howLong', title: '在职时长', sort: true}*/
                                ,{align:'center',field: 'userStatus', title: '在职状态',edit:'text', sort: true,width:93}
                                /*,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right',width:115}*/
                            ]]
                        })
                    }
                }
            })
        }


    </script>
</body>
</html>
