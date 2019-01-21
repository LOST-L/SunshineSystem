<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/9
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.heli.oa.common.entity.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    User u = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>个人中心</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="icon" href="<%=basePath%>static/image/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=basePath%>static/layui/css/layui.css" media="all">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=basePath%>static/layui/layui.all.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
    <script src="<%=basePath%>static/js/base64.js"></script>
</head>

<body style="overflow: auto;height: 90%;width: 99%;background-color: white;">
    <div>
        <div class="layui-btn-group" style="margin-left: 30px;">
            <button class="layui-btn layui-btn-normal insert" onclick="updateUserPassword()">修改密码</button>
        </div>
        <div style="width: 95%;height: 90%;margin-left: 30px;">
            <table id="employeeTable" lay-filter="employeeTable" lay-skin="nob"></table>
            <table id="sunshineTable" lay-filter="sunshineTable" lay-skin="nob"></table>
        </div>

        <form id="updatePassword" class="layui-form" style="display: none;">

            <div class="layui-form-item">
                <label class="layui-form-label">新密码:</label>
                <div class="layui-input-block">
                    <input type="password" id="password1" name="password1" lay-verify="required"
                           autocomplete="off" style="width: 230px;" placeholder="请输入新密码" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">确认密码:</label>
                <div class="layui-input-block">
                    <input type="password" id="password2" name="password2" lay-verify="required"
                           autocomplete="off" style="width: 230px;" placeholder="请输入新密码" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="submitNewPassword" id="submitNewPassword">提交</button>
                </div>
            </div>

        </form>
    </div>

    <script type="text/javascript">
        var table = null;
        var form = null;
        var layer = null;
        layui.use(['table','form','layer'],function () {
            table = layui.table;
            layer = layui.layer;
            form = layui.form;//引入layui.form,然后对其render渲染，被渲染的部分一定要包裹在class= layui-form下
            form.render();
            updatePersonalPage();
            updateSunshinePage();
        })

        /** 修改用户密码*/
        function updateUserPassword(){
            layer.open({
                type: 1 ,
                area: ['380px', '300px'],  //指定弹出层尺寸
                content: $("#updatePassword"),
                title:'请填写新增用户信息',
                cancel:function () {
                    $("#updatePassword").css("display","none");
                    document.getElementById("updatePassword").reset();
                }
            });

            form.on('submit(submitNewPassword)',function(data){
                if(!(data.field.password1 === data.field.password2)){
                    layer.msg("两次输入不一致，请重新输入");
                    document.getElementById("updatePassword").reset();
                }else{
                    $.ajax({
                        url: '<%=basePath%>updateUser.do',
                        type: 'post',
                        data: {
                            "userId": <%=u.getUserId()%>,
                            "userPassword": data.field.password2,
                        },
                        success: function (data) {
                            if (data.code == 200) {
                                layer.close(layer.index);
                                $("#updatePassword").css("display","none");
                                document.getElementById("updatePassword").reset();
                                layer.msg("密码修改成功,请重新登陆", {icon: 16,shade:[0.4,'#000'],time:false});

                                window.parent.logout();
                            }
                        }
                    })
                }
                return false;
            })
        }

        /** 刷新个人信息页面 */
        function updatePersonalPage() {
            $.ajax({
                url:'<%=basePath%>getUserByUserId.do',
                type: 'post',
                data: {"userId":<%=u.getUserId()%>},
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#employeeTable', //指定原始表格元素选择器
                            data: [data.paramObject],  //传入layui table必须是数组；
                           /* toolbar: true,
                            defaultToolbar: ['filter', 'print', 'exports'],*/
                            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: [/*'limit',*/ /*'count',*/ /*'prev', 'page', 'next', 'skip'*/] //自定义分页布局
                                ,count:100
                                ,curr: 1 //设定初始在第 5 页
                                ,groups: 3 //只显示 1 个连续页码
                                ,first: false //不显示首页
                                ,last: false //不显示尾页
                            },
                            cols: [[ //表头
                                 {align:'center',field: 'userName', title: '姓名',edit:'text',width:88}
                                ,{align:'center',field: 'userNickname', title: '花名',edit:'text',width:60}
                                ,{align:'center',field: 'userDepartment', title: '部门',edit:'text', width:80}
                                ,{align:'center',field: 'userPositionChinese', title: '职位',edit:'text',width:88}
                                ,{align:'center',field: 'userPlusStarHtml', title: '正阳光值'}
                                ,{align:'center',field: 'userMinusStarHtml', title: '负阳光值'}
                                ,{align:'center',field: 'userMoney', title: '阳光值扣除金额'}
                                ,{align:'center',field: 'userJoinDate', title: '入职时间',edit:'text'}
                                /*,{align:'center',field: 'howLong', title: '在职时长', sort: true}*/
                                /*,{align:'center',title: '操作',toolbar: '#barDemo', fixed: 'right',width:115}*/
                            ]]
                        })
                    }
                }
            })
        }

        /** 刷新个人阳光值增减记录表格*/
        function updateSunshinePage() {
            $.ajax({
                url:'<%=basePath%>listSunshineByUserId.do',
                type: 'post',
                data: {"userId":<%=u.getUserId()%>},
                success:function (data) {
                    if (data.code == 200) {
                        table.render({
                            elem: '#sunshineTable', //指定原始表格元素选择器
                            data: data.paramList,  //传入layui table必须是数组；
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
                                {align:'center',field: 'sunshineRecordId', title: '记录ID',width:80, sort: true}
                                ,{align:'center',field: 'sunshineStarHtml', title: '阳光值增减',width:100}
                                ,{align:'center',field: 'adminNickname', title: '操作者',width:75}
                                ,{align:'center',field: 'sunshineTime', title: '操作时间', sort: true,width:170}
                                ,{align:'center',field: 'sunshineCommentType', title: '增减类型',width:100}
                                ,{align:'center',field: 'sunshineTotalMoney', title: '应缴金额',width:100}
                                ,{align:'center',field: 'sunshineMoneyStatus', title: '付款状态',width:117}
                                ,{align:'center',field: 'sunshineComment', title: '操作原因',width:545}
                            ]]
                        })
                    }
                }
            })
        }
    </script>
</body>
</html>
