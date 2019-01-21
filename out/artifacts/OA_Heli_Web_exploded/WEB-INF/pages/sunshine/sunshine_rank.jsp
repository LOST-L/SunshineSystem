<%--
  Created by IntelliJ IDEA.
  User: 白驹
  Date: 2018/12/25
  Time: 15:04
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
    <title>阳光值排行榜</title>
    <link rel="stylesheet" href="<%=basePath%>page/layui/css/layui.css" media="all">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=basePath%>page/layui/layui.all.js"></script>
    <script src="https://cdn.bootcss.com/jsencrypt/3.0.0-beta.1/jsencrypt.js"></script>
</head>
<style>
    .lie{
        width: 600px;
        overflow: auto;
        float: left;
        text-align: center;
    }
</style>
<body style="background-color: white;">
<%--<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">

    <ul class="layui-tab-title">
        <li class="layui-this">奖励榜</li>
        <li >扣除榜</li>
    </ul>
    <div id = "plusTableDiv" class="layui-tab-content" style="height: 100px;">
        <div class="layui-tab-item layui-show">
            <table id="plusTable" lay-filter="plusTable" lay-skin="nob"></table>
        </div>
        <div class="layui-tab-item">
            <table id="minusTable" lay-filter="minusTable" lay-skin="nob"></table>
        </div>
    </div>
</div>--%>
<div>
    <div class="lie" style="margin-left: 100px;">
        <table id="plusTable" lay-filter="plusTable" lay-skin="nob" ></table>
    </div>

    <div class="lie" style="margin-left: 100px;">
        <table id="minusTable" lay-filter="minusTable" lay-skin="nob"></table>
    </div>
</div>
<script type="text/javascript">
    var table = null;
    layui.use(['table'],function () {
        table = layui.table;
        listUserByPlus();
        listUserByMinus();
    })

    function listUserByPlus() {
        $.ajax({
            url: '<%=basePath%>user_listByPlusStar.action',
            type: 'post',
            success: function (data) {
                if (data.code == 200) {
                    table.render({
                        elem: '#plusTable', //指定原始表格元素选择器
                        data: data.paramList.sort(),     //相当于code.paramlist
                        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                            layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                            , count: 100
                            , curr: 1 //设定初始在第 5 页
                            , limit: 20
                            , groups: 3 //只显示 1 个连续页码
                            , first: false //不显示首页
                            , last: false //不显示尾页
                        },
                        cols: [[ //表头
                            {field: 'userNickName', title: '花名', width: 75, align: 'center'}
                            , {field: 'userDepartment', title: '部门', width: 90, align: 'center'}
                            , {field: 'userPlusStarHtml', title: '奖励阳光值数量',align: 'center'}

                        ]]
                    })
                }
            }
        })
    }

    // 集合排序
    function jsonListDesc(columnName){
        //对json进行降序排序函数
        return function(x,y) {
            var columnItemX = parseInt(x[columnName].replace(/,/g, '').replace(/ /g, '').replace(/¥/g, ''));
            var columnItemY = parseInt(y[columnName].replace(/,/g, '').replace(/ /g, '').replace(/¥/g, ''));
            return (columnItemX < columnItemY) ? 1 : -1
        }

    }
    function jsonListAsc(columnName){
        //对json进行升序排序函数
        return function(x,y)
        {
            var columnItemX = parseInt(x[columnName].replace(/,/g, '').replace(/ /g, '').replace(/¥/g, ''));
            var columnItemY = parseInt(y[columnName].replace(/,/g, '').replace(/ /g, '').replace(/¥/g, ''));
            return (columnItemX > columnItemY) ? 1 : -1
        }
    }


    function listUserByMinus() {
        $.ajax({
            url: '<%=basePath%>user_listByMinusStar.action',
            type: 'post',
            success: function (data) {
                if (data.code == 200) {
                    table.render({
                        elem: '#minusTable', //指定原始表格元素选择器
                        data: data.paramList,     //相当于code.paramlist
                        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                            layout: [ 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                            , count: 100
                            , limit: 20
                            , curr: 1 //设定初始在第 5 页
                            , groups: 3 //只显示 1 个连续页码
                            , first: false //不显示首页
                            , last: false //不显示尾页
                        },
                        cols: [[ //表头
                            {field: 'userNickName', title: '花名', width: 75, align: 'center'}
                            , {field: 'userDepartment', title: '部门', width: 90, align: 'center'}
                            , {field: 'userMinusStarHtml', title: '扣除阳光值数量',align: 'center'}
                        ]]
                    })
                }
            }
        })
    }
</script>
</body>
</html>

