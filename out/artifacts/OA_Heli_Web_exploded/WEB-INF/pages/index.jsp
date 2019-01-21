<%--
  Created by IntelliJ IDEA.
  User: 白驹
  Date: 2018/12/27
  Time: 10:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="com.heli.oa.common.entity.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>核利阳光值系统</title>
    <link rel="stylesheet" href="static/layui/css/layui.css" media="all">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=basePath%>static/layui/layui.all.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
    <script src="<%=basePath%>static/js/base64.js"></script>
    <style type="text/css">
        body{
            -webkit-user-select:none;
            -moz-user-select:none;
            -ms-user-select:none;
            user-select:none;
        }
        .function{
            width: 95px;
            height: 94px;
            /*margin-left: 10px;
            margin-top: 15px;*/
            padding: 5px;
            cursor: pointer;
        }
        .function span{
            font-size: 12px;
        }
        .lie{
            /*width: 102px;*/
            overflow: auto;
            float: left;
            text-align: center;
        }
    </style>
</head>
<body style="overflow-x:hidden">
<div id = "topImage" style="height:100%;min-height:800px;width:100%;background-image: url('static/image/back.jpg');">
    <div id="left" style="width: 50%;height: 100%;background-color: black;position: absolute;left: 0;"></div>
    <div id="right" style="width: 50%;height: 100%;background-color: black;position: absolute;left: 50%;"></div>
    <div class="lie">
        <div class="function" name = "userInfo" data-url="sunshine/personal">
            <img src="static/image/user.png" width="60" height="60"><br>
            <span style="color: white;">个人中心</span>
        </div>
        <div class="function" name = "ygzInfo" data-url="sunshine/sunshine_manage">
            <img src="static/image/Sun.png" width="60" height="60"><br>
            <span style="color: white;">阳光值管理</span>
        </div>
        <div class="function" name = "ygzRank" data-url="sunshine/sunshine_rank">
            <img src="static/image/Sun.png" width="60" height="60"><br>
            <span style="color: white;">阳光值排行</span>
        </div>
        <div class="function" name = "depSystem" data-url="sunshine/task_manage">
            <img src="static/image/gdSystem.png" width="60" height="60"><br>
            <span style="color: white;">任务系统</span>
        </div>
        <div class="function" name = "empSystem" data-url="sunshine/user_manage">
            <img src="static/image/userSystem.png" width="60" height="60"><br>
            <span style="color: white;">员工管理</span>
        </div>
        <div class="function" name = "depSystem" data-url="sunshine/department_manage"><%----%>
            <img src="static/image/depSystem.png" width="60" height="60"><br>
            <span style="color: white;">部门管理</span>
        </div>

<%--        <div class="function" data-url="<%=basePath%>page/background/BackGround.jsp">
            <img src="static/image/picture.png" width="60" height="60"><br>
            <span style="color: white;">更换背景</span>
        </div>
              <div class="function" data-url="http://www.baidu.com">
            <img src="static/image/Record.png" width="60" height="60"><br>
            <span style="color: white;">操作记录</span>
        </div>
--%>
        <%User user = (User) session.getAttribute("user");%>
        <span style="font-size:16px; color:greenyellow;">欢迎您：<%=user.getUserNickname()%></span>
        <br>
        <br>
        <u><a href="<%=basePath%>user_logout.action" style="font-size:16px; color:bisque; " >退出系统</a></u>
    </div>
    <div class="lie">

        <div class="function" name = "depSystem" data-url="count/CountUserList">
            <img src="static/image/gdSystem.png" width="60" height="60"><br>
            <span style="color: white;">京云策划-用户管理（勿动）</span>
        </div>
        <div class="function" name = "depSystem" data-url="plan/CategoryList">
            <img src="static/image/gdSystem.png" width="60" height="60"><br>
            <span style="color: white;">京云策划-类目管理（勿动）</span>
        </div>
    </div>
    <div class="lie">
        <div class="function" name = "depSystem" data-url="<%=basePath%>page/yingxiao/AdvertiseList.jsp">
            <img src="static/image/gdSystem.png" width="60" height="60"><br>
            <span style="color: white;">营销网站-广告管理（勿动）</span>
        </div>
        <div class="function" name = "depSystem" data-url="<%=basePath%>page/yingxiao/CoursesList.jsp">
            <img src="static/image/gdSystem.png" width="60" height="60"><br>
            <span style="color: white;">营销网站-课程管理（勿动）</span>
        </div>
        <div class="function" name = "depSystem" data-url="<%=basePath%>page/yingxiao/BarrageList.jsp">
            <img src="static/image/gdSystem.png" width="60" height="60"><br>
            <span style="color: white;">营销网站-弹幕管理（勿动）</span>
        </div>
        <div class="function" name = "depSystem" data-url="<%=basePath%>page/yingxiao/TeacherList.jsp">
            <img src="static/image/gdSystem.png" width="60" height="60"><br>
            <span style="color: white;">营销网站-讲师管理（勿动）</span>
        </div>
    </div>
    <div class="lie">
        <div class="function" name = "depSystem" data-url="page/seoadmin/">
            <img src="static/image/gdSystem.png" width="60" height="60"><br>
            <span style="color: white;">魔搜用户管理</span>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $(".function").mouseover(function () {
                $(this).css("border","0.1px solid white");
                $(this).css("background-color","#ffffff38");
            })
            $(".function").mouseout(function () {
                $(this).css("border","none");
                $(this).css("background-color","");
            })
            $(".function").dblclick(function(){
                var title = $(this).children("span").text();
                var url = $(this).attr("data-url");
                var name = $(this).attr("name");
                    layer.open({
                        type: 2,
                        title:title,
                        area: ['90%', '97%'],
                        offset: 'rt',
                        anim: 0,
                        isOutAnim:true,
                        maxmin: true,
                        shade: 0,
                        moveOut:true,
                        scrollbar: false,
                        shadeClose: false, //开启遮罩关闭
                        id:name,
                        content: url,
/*                        zIndex: layer.zIndex, //重点1
                        success: function(layero){
                            layer.setTop(layero); //重点2
                        }*/
                    });
            });
            $("#left").animate({left:'-50%'},1000);
            $("#right").animate({left:'100%'},1000);
            function backImage(src){
                $("#topImage").css("background-image","url('"+topImage+"')")
            }
        })
        function logout(){
            setTimeout(function(){
                location.href="<%=basePath%>logout.do"
            }, 3000)
        }
    </script>
</div>
</body>
</html>
