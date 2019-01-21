<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>类目列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="<%=basePath%>static/layui/css/layui.css" media="all">
    <script src="<%=basePath%>static/js/jquery-2.1.1.min.js"></script>
    <style type="text/css">
        .countUserMain{
            min-width: 1690px;
            max-width: 1690px;
            margin: 0px auto;
            background-color: #ffffff;
        }
    </style>
</head>
<body style="width: 100%;height: 100%;">
    <div class="countUserMain" style="width: 100%;height: 100%;">
        <div class="countUserTopList" style="margin: auto;width: 95%;height: 95%;">
            <button class="layui-btn layui-btn-sm layui-btn-normal addCountUser" style="margin-top: 8px;">新增用户</button>
            <input class = "layui-input-inline layui-input" style = "width: 200px"type="text" name="search" autocomplete="off" id="userInfo" placeholder="请输入用户信息"><%--手机号、微信号、QQ号、用户名--%>
            <button class="layui-btn " style="margin-top: 8px;" onclick="search()">搜索用户</button>
            <table id="countUserTable" class="layui-hide" lay-filter="countUserTable"></table>
        </div>
    </div>
    <div id="countUserEdit" class="layui-form" style="display: none;">
        <div style="margin: 10px">
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">用户名称</label>
                <div class="layui-input-inline" style="width: 300px">
                    <input type="text" name="userName" autocomplete="off" class="layui-input" style="border: 0;" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">手机号</label>
                <div class="layui-input-inline" style="width: 300px">
                    <input type="text" name="accounts" autocomplete="off" class="layui-input" style="border: 0;" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">QQ</label>
                <div class="layui-input-inline" style="width: 300px">
                    <input type="text" name="qqId" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">微信</label>
                <div class="layui-input-inline" style="width: 300px">
                    <input type="text" name="wxId" autocomplete="off" class="layui-input" style="border: 0;" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">邮箱</label>
                <div class="layui-input-inline" style="width: 300px">
                    <input type="text" name="postBox" autocomplete="off" class="layui-input" style="border: 0;" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">学习卡</label>
                <div class="layui-input-inline" style="width: 300px">
                    <input type="text" name="stId" autocomplete="off" class="layui-input" style="border: 0;" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">学习卡到期时间</label>
                <div class="layui-input-inline" style="width: 300px">
                    <input type="text" name="stStopTime" autocomplete="off" class="layui-input" style="border: 0;" disabled>
                </div>
            </div>
        </div>
    </div>
    <div id="countUserPowerEdit" class="layui-form" style="display: none;">
        <div style="margin: 10px;">
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">权限类型</label>
                <div class="layui-input-inline" style="width: 300px">
                    <input id="userId" type="hidden">
                    <select id="powerType" lay-filter="powerTypeFilter">
                        <option value="0">暂无权限</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">开通权限</label>
                <div id="powerId" class="layui-input-inline" style="width: 590px">

                </div>
            </div>
        </div>
    </div>
    <script type="text/html" id="tools">
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="editUserBtn">编辑用户</button>
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="editUserPowerBtn">编辑权限</button>
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="userAddShop">加绑店铺</button>
    </script>
    <script src="<%=basePath%>static/layui/layui.js"></script>
    <script type="text/javascript">
        var table = null;
        var form = null;
        var userPowerTypeId = 0;
        var userPowerList = "";
        var userShopType = 0;
        layui.use(['table', 'form'],function(){
            table = layui.table;
            form = layui.form;

            showCountUserList();
            table.on('tool(countUserTable)', function(obj){
                var data = obj.data;

                if(obj.event === 'editUserBtn'){
                    alertUserInfo(data);
                } else if (obj.event === 'editUserPowerBtn'){
                    alertUserPowerInfo(data);
                } else if (obj.event === 'userAddShop'){
                    alertUserAddShop(data);
                }
            });

            form.on('select(powerTypeFilter)', function(data) {
                userPowerTypeId = data.value;
                getCountUserPowerByTypeId();
            });

            form.on('radio(userShopTypeFilter)', function(data){
                userShopType = data.value;
            });

            form.render();
        });
        /* 用户加绑店铺-start */
        function alertUserAddShop(paramData){
            $.ajax({
                url:"<%=basePath%>plan/shop/getUserShop.do",
                type:"get",
                data:{
                    userId:paramData.userId
                },
                beforeSend:function(){
                    layer.msg('正在获取用户已绑定店铺数量请稍后...', {icon: 16,shade:[0.4,'#000'],time:false});
                },
                success:function(dataShopNumber){
                    layer.close(layer.index);
                    var parsShopNumber = JSON.parse(dataShopNumber);
                    if(parsShopNumber.code == 200){
                        var openHtml = "<div id=\"countUserAddShop\" class=\"layui-form\">" +
                            "        <div style=\"margin: 10px;\">" +
                            "            <div class=\"layui-form-item\">" +
                            "                <label class=\"layui-form-label\" style=\"width: 100px;\">已绑定数量</label>" +
                            "                <div class=\"layui-input-inline\" style=\"width: 300px\">" +
                            "                    <input type=\"text\" class=\"layui-input\" name=\"shopNumber\" value=\""+parsShopNumber.paramObject+"\" style=\"border:0;\" disabled>" +
                            "                </div>" +
                            "            </div>" +
                            "            <div class=\"layui-form-item\">" +
                            "                <label class=\"layui-form-label\" style=\"width: 100px;\">店铺类型</label>" +
                            "                <div class=\"layui-input-inline\" style=\"width: 300px\">" +
                            "                    <input type=\"radio\" name=\"shopType\" value=\"0\" title=\"非全球购\" lay-filter=\"userShopTypeFilter\" checked>" +
                            "                    <input type=\"radio\" name=\"shopType\" value=\"1\" title=\"全球购\" lay-filter=\"userShopTypeFilter\">" +
                            "                </div>" +
                            "            </div>" +
                            "            <div class=\"layui-form-item\">" +
                            "                <label class=\"layui-form-label\" style=\"width: 100px;\">店铺链接</label>" +
                            "                <div class=\"layui-input-inline\" style=\"width: 300px\">" +
                            "                    <input type=\"text\" name=\"shopUrl\" autocomplete=\"off\" class=\"layui-input\">" +
                            "                </div>" +
                            "            </div>" +
                            "        </div>" +
                            "    </div>";
                        var countUserAddShopOpenIndex = layer.open({
                            type: 1,
                            area: ['480px', '275px'],  //指定弹出层尺寸
                            content: openHtml,
                            title:'用户加绑店铺',
                            btn:["保存"],
                            btnAlign: 'c',
                            btn1:function () {
                                var shopUrl = $("#countUserAddShop input[name=shopUrl]").val()
                                if(shopUrl != ""){
                                    $.ajax({
                                        url:"<%=basePath%>plan/shop/bindingShop.do",
                                        type:"post",
                                        data:{
                                            url:shopUrl,
                                            userId:paramData.userId,
                                            linkType:userShopType
                                        },
                                        beforeSend:function(){
                                            layer.msg('正在绑定店铺请稍后...', {icon: 16,shade:[0.4,'#000'],time:false});
                                        },
                                        success:function(data){
                                            var pars = JSON.parse(data);
                                            layer.close(layer.index);
                                            if(pars.code == 200){
                                                layer.close(countUserAddShopOpenIndex);
                                                layer.msg('店铺绑定成功！');
                                            } else {
                                                layer.msg(pars.paramObject, {
                                                    icon : 2,
                                                    time : 2000
                                                });
                                            }
                                        }
                                    })
                                } else {
                                    layer.msg("店铺地址不可为空", {
                                        icon : 7,
                                        time : 2000
                                    });
                                }
                            }
                        });
                        form.render();
                    } else {
                        layer.msg(pars.paramObject, {
                            icon : 2,
                            time : 2000
                        });
                    }
                }
            });

        }
        /* 用户加绑店铺-end */

        /* 用户编辑-start */
        function alertUserInfo(paramData){
            $("#countUserEdit input[name=userName]").val(paramData.userNickname);
            $("#countUserEdit input[name=accounts]").val(paramData.userAccounts);
            $("#countUserEdit input[name=qqId]").val(paramData.userQqId);
            $("#countUserEdit input[name=wxId]").val(paramData.userWcId);
            $("#countUserEdit input[name=postBox]").val(paramData.userPostbox);
            $("#countUserEdit input[name=stId]").val(paramData.userStId);
            $("#countUserEdit input[name=stStopTime]").val(paramData.userStTime);

            layer.open({
                type: 1 ,
                area: ['480px', '500px'],  //指定弹出层尺寸
                content: $("#countUserEdit"),
                title:'用户详情',
                btn:["保存"],
                btnAlign: 'c',
                btn1:function () {
                    var qqId = $("#countUserEdit input[name=qqId]").val(paramData.userQqId);
                    alert(qqId);
                }
            });
        }
        /* 用户编辑-end */


        /* 用户权限编辑-start */
        // 显示用户弹窗
        function alertUserPowerInfo(paramData){
            $("#userId").val(paramData.userId);

            getCountUserPowerTypeList();

            var userPowerOpenIndex = layer.open({
                type: 1 ,
                area: ['750px', '300px'],  //指定弹出层尺寸
                content: $("#countUserPowerEdit"),
                title:'权限编辑',
                btn:["保存"],
                btnAlign: 'c',
                btn1:function () {
                    var inputCheckbox = $("#powerId input[name=checkboxPowerId]");
                    var powers = new Array();
                    $.each(inputCheckbox,function (i,item) {
                        if(item.checked == true){
                            powers.push(item.value);
                        }
                    });
                    $.ajax({
                        url:"<%=basePath%>count/user/power/edit.do",
                        type:"post",
                        data:{
                            "userPowerTypeId":userPowerTypeId,
                            "userId":paramData.userId,
                            "powers":JSON.stringify(powers)
                        },
                        beforeSend:function(){
                            layer.msg('正在加载权限类型..', {icon: 16,shade:[0.4,'#000'],time:false});
                        },
                        success:function (data) {
                            data = JSON.parse(data);
                            layer.close(layer.index);
                            if(data.code == 200){
                                layer.close(userPowerOpenIndex);
                                layer.msg('权限修改成功！');
                            }else{
                                layer.msg(data.paramObject, {
                                    icon : 2,
                                    time : 2000
                                });
                            }
                        }
                    });

                }
            });
        }

        // 加载用户类型下拉框
        function getCountUserPowerTypeList(){
            userPowerTypeId = 0;
            $("#powerType").html("<option value=\"0\">暂无权限</option>");
            form.render();
            $.ajax({
                url:"<%=basePath%>count/user/power/type/getList.do",
                type:"get",
                beforeSend:function(){
                    layer.msg('正在加载权限类型..', {icon: 16,shade:[0.4,'#000'],time:false});
                },
                success:function (data) {
                    data = JSON.parse(data);
                    if(data.code == 200){
                        var result = data.paramObject;
                        $("#powerType").html("");
                        $.each(result,function(i,item){
                            if(i == 0){
                                userPowerTypeId = item.id;
                            }
                            $("#powerType").append("<option value=\""+item.id+"\">"+item.name+"</option>");
                        });
                        form.render();
                        getCountUserPowerByTypeId();
                    }else{
                        layer.msg(data.paramObject, {
                            icon : 2,
                            time : 2000
                        });
                    }
                }
            });
        }

        // 加载对应权限类型下所有权限
        function getCountUserPowerByTypeId(){
            // 加载用户已有权限
            $.ajax({
                url:"<%=basePath%>count/user/getCountUser.do",
                type:"get",
                data:{
                    "userId":$("#userId").val()
                },
                beforeSend:function(){
                    layer.msg('正在加载用户详情..', {icon: 16,shade:[0.4,'#000'],time:false});
                },
                success:function (data) {
                    data = JSON.parse(data);
                    if(data.code == 200){
                        var result = data.paramObject;
                        if(result != null && result.length != 0){
                            userPowerList = result.powerList;
                            // 加载权限下拉框
                            // getCountUserPowerTypeList();
                        }else{
                            userPowerList = "";
                        }

                    }else{
                        layer.msg(data.paramObject, {
                            icon : 2,
                            time : 2000
                        });
                    }
                }
            });
            // form.render();

            $("#powerId").html("");
            form.render();
            if(userPowerTypeId != 0){
                $.ajax({
                    url:"<%=basePath%>count/user/power/getList.do",
                    type:"get",
                    data:{
                        "typeId":userPowerTypeId
                    },
                    beforeSend:function(){
                        layer.msg('正在加载所有权限..', {icon: 16,shade:[0.4,'#000'],time:false});
                    },
                    success:function (data) {
                        data = JSON.parse(data);
                        if(data.code == 200){
                            layer.close(layer.index);
                            var result = data.paramObject;
                            $.each(result,function(i,item){
                                if(userPowerList != ""){
                                    var powerFlag = false;
                                    $.each(userPowerList,function (j, itemt) {
                                        if(item.id == itemt.id){
                                            powerFlag = true;
                                        }
                                    });
                                    // 选中用户已有权限
                                    if(powerFlag){
                                        $("#powerId").append("<input type=\"checkbox\" name=\"checkboxPowerId\" lay-skin=\"primary\" title=\""+item.name+"\" value=\""+item.id+"\">");
                                        var inputCheckbox = $("#powerId input[name=checkboxPowerId]");
                                        var lastInput = inputCheckbox[(inputCheckbox.length - 1)];
                                        lastInput.checked = true;
                                    }else{
                                        $("#powerId").append("<input type=\"checkbox\" name=\"checkboxPowerId\" lay-skin=\"primary\" title=\""+item.name+"\" value=\""+item.id+"\">");
                                    }
                                }else{
                                    $("#powerId").append("<input type=\"checkbox\" name=\"checkboxPowerId\" lay-skin=\"primary\" title=\""+item.name+"\" value=\""+item.id+"\">");
                                }
                            });
                            form.render();
                        }else{
                            layer.msg(data.paramObject, {
                                icon : 2,
                                time : 2000
                            });
                        }
                    }
                });
            }else{

            }

            form.render();
        }
        /* 用户权限编辑-end */

        // 获取用户列表
        function showCountUserList() {
            table.render({
                elem: '#countUserTable'
                ,url:"<%=basePath%>count/user/getList.do"
                ,even: true
                ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                    layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                    ,groups: 3 //只显示 1 个连续页码
                }
                ,limit: 17 //每页默认显示的数量
                ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {field:'userId',title: 'ID', width: 80}
                    ,{field:'userNickname',title: '昵称'}
                    ,{field:'userAccounts',title: '账号', width: 120}
                    ,{field:'stName',title: '学习卡'}
                    ,{field:'userStTime', title: '学习卡到期时间'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'userQqId', title: 'QQ'}
                    ,{field:'userWcId', title: '微信'}
                    ,{field:'tool', title: '操作',toolbar: '#tools', width: 260}
                ]]
            });
        }

        function search() {
            $.ajax({
                id:"updateDoneBacklog",
                url:"<%=basePath%>count/user/searchCountUser.do",
                type: 'post',
                data: {"userAccounts":$("#userInfo").val()},
                success: function (data) {
                    data = JSON.parse(data);
                    if (data.code == 200) {
                        table.render({
                            elem: '#countUserTable'
                            ,even: true
                            ,data: data.paramList
                            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                                ,groups: 3 //只显示 1 个连续页码
                            }
                            ,limit: 17 //每页默认显示的数量
                            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                            ,cols: [[
                                {field:'userId',title: 'ID', width: 80}
                                ,{field:'userNickname',title: '昵称'}
                                ,{field:'userAccounts',title: '账号', width: 120}
                                ,{field:'stName',title: '学习卡'}
                                ,{field:'userStTime', title: '学习卡到期时间'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                                ,{field:'userQqId', title: 'QQ'}
                                ,{field:'userWcId', title: '微信'}
                                ,{field:'tool', title: '操作',toolbar: '#tools', width: 260}
                            ]]
                        });
                    }else{
                        layer.msg(data.paramObject, {
                            icon : 2,
                            time : 2000
                        });
                    }
                }
            })
        }
    </script>
</body>
</html>
