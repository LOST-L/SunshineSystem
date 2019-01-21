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

        .mosou-user-insertInfo {
            margin-top: 9px;
            margin-left: 15px;
        }

        .mosou-user-userPicture {
            border-radius: 50%;
            box-shadow: 0px 0px 20px 2px #000;
            z-index: 999999999;
            margin-left: 40%;
            margin-top: 55px;
        }
        .mosou-user-text-right{
            text-align: right;
            font-size: 20px;
            margin-top: 25px;
        }

        .mosou-user-text-center{
            text-align: center;
            font-size: 20px;
            margin-top: 28px;
            color: red;
        }

        .mosou-user-text-index{
            margin-top: 35px;
        }
        .mosou-user-userName{
            overflow: auto;
        }
        .mosou-user-userInfo{
            border: 1px solid #b8b8b8;
            width: 75%;
            margin: 0px auto;
            height: 75%;
        }
        .mosou-user-userTable{
            border: 1px solid #b8b8b8;
            width: 95%;
            margin: 0px auto;
            height: 69%;
            padding-top: 50px;
        }
        .mosou-user-select{
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="layui-row"  style="padding-left: 25px;">
    <div class="layui-col-xs7 mosou-user-select">
        <div class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">查询方式:</label>
                    <div class="layui-input-inline">
                        <select name="interest" lay-filter="aihao">
                            <option value="none" selected=""></option>
                            <option value="account">按账号查询</option>
                            <option value="name">按昵称查询</option>
                            <option value="qq">按QQ查询</option>
                            <option value="wx">按微信查询</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">请输入：</label>
                    <div class="layui-input-inline">
                        <input type="tel" name="phone" lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">
                        <button class="layui-btn layui-btn-normal mosou-user-selectInfo">点我查询</button>
                    </label>
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-normal mosou-user-insertInfo">新增用户</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-xs7">
        <div class="mosou-user-userTable">
            <table class="layui-table mosou-user-userTable">
                <colgroup>
                </colgroup>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>昵称</th>
                    <th>账号</th>
                    <th>学习卡</th>
                    <th>学习卡到期时间</th>
                    <th>QQ</th>
                    <th>微信</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                <tr>
                    <td>0</td>
                    <td>测试数据1</td>
                    <td>15803263606</td>
                    <td>VIP</td>
                    <td>2019-12-28</td>
                    <th>1878321819</th>
                    <th>xj15803263608</th>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="layui-col-xs5">
        <div class="mosou-user-userInfo">
            <img width="150" height="150" class="mosou-user-userPicture"
                 src="http://www.jdseo.com/img/1535958900897.jpeg"/>
            <div class="mosou-user-userName">
                <div class="layui-col-xs4">
                    <div class="mosou-user-text-right mosou-user-text-index">用户名：</div>
                    <div class="mosou-user-text-right">账号：</div>
                    <div class="mosou-user-text-right">QQ：</div>
                    <div class="mosou-user-text-right">微信：</div>
                    <div class="mosou-user-text-right">学习卡：</div>
                </div>
                <div class="layui-col-xs8">
                    <div class="mosou-user-text-center mosou-user-text-index">核利电商-小鱼</div>
                    <div class="mosou-user-text-center">15803263606</div>
                    <div class="mosou-user-text-center">1878321819</div>
                    <div class="mosou-user-text-center">xj15803263608</div>
                    <div class="mosou-user-text-center">到期时间：2019-10-24 14:43:06.0</div>
                </div>
            </div>
            <div style="text-align: center;margin-top: 35px;">
                <button class="layui-btn layui-btn-normal mosou-user-update-power">修改权限</button>
                <button class="layui-btn layui-btn-danger mosou-user-yesDelete-power">冻结用户</button>
                <button class="layui-btn layui-btn-disabled mosou-user-noDelete-power">解冻用户</button>
                <br/><br/><br/><br/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.use(['layer', 'form'], function () {
        var form = layui.form;
    })
</script>
</body>
</html>
