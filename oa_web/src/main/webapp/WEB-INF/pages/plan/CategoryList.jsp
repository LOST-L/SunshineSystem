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
        .categoryMain{
            min-width: 1690px;
            max-width: 1690px;
            margin: 0px auto;
            background-color: #ffffff;
        }
        #categoryAdd .newCategoryWidth{
            width: 93px;
            margin-top: 5px;
        }
        #categoryAdd .add-icon-btn-close{
            position: absolute;
            left: 96px;
            color: red;
            bottom: 7px;
            font-size: 19px;
            font-weight: 700;
            cursor: pointer;
        }
        #categoryAdd .layui-unselect dl { max-height:225px; }
        #categoryEdit .newCategoryWidth{
            width: 93px;
            margin-top: 5px;
        }
        .errorInput{
            border: 1px solid red;
            box-shadow: 0 0 5px red;
        }
        #categoryEdit .icon-btn-close{
            position: absolute;
            left: 96px;
            color: red;
            bottom: 7px;
            font-size: 19px;
            font-weight: 700;
            cursor: pointer;
        }
    </style>
</head>
<body style="width: 100%;height: 100%;">
    <div class="categoryMain" style="width: 100%;height: 100%;">
        <div class="categoryTopList" style="margin: auto;width: 95%;height: 95%;">
            <button class="layui-btn layui-btn-sm layui-btn-normal addCategory" style="margin-top: 8px;">新增类目</button>
            <button class="layui-btn layui-btn-sm layui-btn-disabled returnLevel" style="margin-top: 8px;">返回上级</button>
            <table id="categoryTable" class="layui-hide" lay-filter="categoryTable"></table>
        </div>
    </div>
    <div id="categoryEdit" class="layui-form" style="display: none;">
        <div style="margin: 10px">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 60px;">类目名称</label>
                    <div class="layui-input-inline" style="width: 300px">
                        <input type="text" name="categoryName" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 60px;">主控词</label>
                    <div id="keyWords" class="layui-input-block" style="margin-left: 90px;">
                        <div class="categoryKeyWordItem">
                            <div class="layui-input-inline newCategoryWidth">
                                <input type="text" name="email" lay-verify="email" placeholder="主控词" autocomplete="off" class="layui-input keyWord">
                            </div>
                            <div class="layui-input-inline newCategoryWidth">
                                <input type="text" name="email" lay-verify="email" placeholder="单量" autocomplete="off" class="layui-input singleAmount">
                            </div>
                            <div class="layui-input-inline newCategoryWidth">
                                <input type="text" name="email" lay-verify="email" placeholder="销售额" autocomplete="off" class="layui-input salesVolume">
                                <i class="layui-icon layui-icon-close icon-btn-close"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <label class="layui-form-label" style="width: 60px;"></label>
                    <div class="layui-input-block" style="margin-left: 90px;">
                        <a class="addKeyWordGroup" style="cursor: pointer;">添加主控词</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <script type="text/html" id="toolOne">
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="detailOne">子级类目</button>
    </script>
    <script type="text/html" id="toolTwo">
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="detailTwo">子级类目</button>
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="editTwo">编辑</button>
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="delTwo">删除</button>
    </script>
    <script type="text/html" id="toolThree">
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="editThree">编辑</button>
        <button class = 'layui-btn layui-btn-sm layui-btn-normal hoverImg' lay-event="delThree">删除</button>
    </script>
    <script src="<%=basePath%>static/layui/layui.js"></script>
    <script type="text/javascript">
        var table;
        var urlFlag = "0";
        var lastPid = 0;
        var categoryLevelTypeId = 1;
        var parentCategoryId = 0;
        layui.use(['table', 'form'],function(){
            table = layui.table;
            var form = layui.form;
            getTable();

            // 新增类目-start
            $(".addCategory").click(function () {
                removeErrorInput();
                var addCategoryHtml = "" +
                    "<div id=\"categoryAdd\" class=\"layui-form\">" +
                    "   <div style=\"margin: 10px\">" +
                    "       <div class=\"layui-form-item\">" +
                    "           <div class=\"layui-inline\">" +
                    "               <label class=\"layui-form-label\" style=\"width: 60px;\">类目ID</label>" +
                    "               <div class=\"layui-input-inline\" style=\"width: 300px\">" +
                    "                   <input type=\"text\" maxlength=\"10\" oninput=\"this.value=this.value.replace(/\\D/g,'')\" name=\"categoryId\" autocomplete=\"off\" class=\"layui-input\">" +
                    "               </div>" +
                    "           </div>" +
                    "       </div>" +
                    "       <div class=\"layui-form-item\">" +
                    "           <div class=\"layui-inline\">" +
                    "               <label class=\"layui-form-label\" style=\"width: 60px;\">类目名称</label>" +
                    "               <div class=\"layui-input-inline\" style=\"width: 300px\">" +
                    "                   <input type=\"text\" name=\"categoryName\" autocomplete=\"off\" class=\"layui-input\">" +
                    "               </div>" +
                    "           </div>" +
                    "       </div>" +
                    "       <div class=\"layui-form-item\">" +
                    "           <div class=\"layui-inline\">" +
                    "               <label class=\"layui-form-label\" style=\"width: 60px;\">类目级别</label>" +
                    "               <div class=\"layui-input-inline\" style=\"width: 300px\">" +
                    "                   <select lay-filter=\"categoryLevelType\">" +
                    "                       <option value=\"1\">一级类目</option>" +
                    "                       <option value=\"2\">二级类目</option>" +
                    "                       <option value=\"3\">三级类目</option>" +
                    "                   </select>" +
                    "               </div>" +
                    "           </div>" +
                    "       </div>" +
                    "       <div class=\"layui-form-item\">" +
                    "           <div class=\"layui-inline\">" +
                    "               <label class=\"layui-form-label\" style=\"width: 60px;\">父级类目</label>" +
                    "               <div class=\"layui-input-inline\" style=\"width: 300px\">" +
                    "                   <select id=\"parentCategory\" lay-filter=\"parentCategory\" lay-search=\"\">" +
                    "                       <option value=\"0\">暂无父级类目</option>" +
                    "                   </select>" +
                    "               </div>" +
                    "           </div>" +
                    "       </div>" +
                    "       <div class=\"layui-form-item\">" +
                    "           <div class=\"layui-inline\">" +
                    "               <label class=\"layui-form-label\" style=\"width: 60px;\">主控词</label>" +
                    "               <div id=\"add-keyWords\" class=\"layui-input-block\" style=\"margin-left: 90px;\">" +
                    /*"                   <div class=\"categoryKeyWordItem\">" +
                    "                       <div class=\"layui-input-inline newCategoryWidth\">" +
                    "                           <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"主控词\" autocomplete=\"off\" class=\"layui-input keyWord\">" +
                    "                       </div>" +
                    "                       <div class=\"layui-input-inline newCategoryWidth\">" +
                    "                           <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"单量\" autocomplete=\"off\" class=\"layui-input singleAmount\">" +
                    "                       </div>" +
                    "                       <div class=\"layui-input-inline newCategoryWidth\">" +
                    "                           <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"销售额\" autocomplete=\"off\" class=\"layui-input salesVolume\">" +
                    "                           <i class=\"layui-icon layui-icon-close add-icon-btn-close\"></i>" +
                    "                       </div>" +
                    "                   </div>" +*/
                    "               </div>" +
                    "           </div>" +
                    "       </div>" +
                    "       <div class=\"layui-form-item\">" +
                    "           <div class=\"layui-input-inline\">" +
                    "               <label class=\"layui-form-label\" style=\"width: 60px;\"></label>" +
                    "               <div class=\"layui-input-block\" style=\"margin-left: 90px;\">" +
                    "                   <a class=\"add-addKeyWordGroup\" style=\"cursor: pointer;\">添加主控词</a>" +
                    "               </div>" +
                    "           </div>" +
                    "       </div>" +
                    "   </div>" +
                    "</div>";
                var addCategoryLayerIndex = layer.open({
                    type: 1 ,
                    area: ['450px', '500px'],  //指定弹出层尺寸
                    content: addCategoryHtml,
                    title:'新增类目',
                    btn:["立即新增"],
                    btnAlign: 'c',
                    btn1:function () {
                        var categoryId = $("#categoryAdd input[name=categoryId]").val();
                        var name = $("#categoryAdd input[name=categoryName]").val();

                        if(categoryId == ""){
                            layer.msg("类目ID不可为空。", {
                                icon : 2,
                                time : 2000
                            });
                        }else if(categoryId.length > 10){
                            layer.msg("类目ID请控制在10位以内", {
                                icon : 2,
                                time : 2000
                            });
                        }else if(categoryId.length > 10){
                            layer.msg("类目ID请控制在10位以内", {
                                icon : 2,
                                time : 2000
                            });
                        }else if(name == ""){
                            layer.msg("类目名称不可为空。", {
                                icon : 2,
                                time : 2000
                            });
                        }else{

                            var result = new Array();
                            var keyWords = $("#add-keyWords .keyWord");
                            var singleAmounts = $("#add-keyWords .singleAmount");
                            var salesVolumes = $("#add-keyWords .salesVolume");
                            $("#add-keyWords .keyWord").each(function (i,item) {
                                var keyword = $(keyWords[i]).val();
                                var orders = $(singleAmounts[i]).val();
                                var sales = $(salesVolumes[i]).val();
                                if(keyword != "" && orders != ""){
                                    var obj = {};
                                    obj.mainkey = keyword;
                                    obj.orders = orders;
                                    obj.sales = sales;
                                    result.push(obj);
                                }
                                if(keyword == ""){
                                    $(keyWords[i]).addClass("errorInput");
                                }
                                if(orders == ""){
                                    $(singleAmounts[i]).addClass("errorInput");
                                }
                            });
                            if(result.length == keyWords.length){
                                var resultStr = JSON.stringify(result);
                                $.ajax({
                                    url:"<%=basePath%>plan/category/addCategory.do",
                                    type:"post",
                                    data:{
                                        "wid":categoryId,
                                        "name": name,
                                        "grade":categoryLevelTypeId,
                                        "pid":parentCategoryId,
                                        "cmeta":resultStr
                                    },
                                    beforeSend:function(){
                                        layer.msg('正在新增类目...', {icon: 16,shade:[0.4,'#000'],time:false});
                                    },
                                    success:function(data2){
                                        if(data2.code == 200){
                                            layer.msg("新增类目信息成功");
                                            layer.close(addCategoryLayerIndex);
                                            $(".returnLevel").removeClass("layui-btn-normal");
                                            $(".returnLevel").addClass("layui-btn-disabled");
                                            urlFlag = "0";
                                            getTable();
                                        }else{
                                            layer.msg(data2.paramObject, {
                                                icon : 2,
                                                time : 2000
                                            });
                                        }
                                    }
                                });
                            }else{
                                layer.msg("保存失败！请检查填写内容。", {
                                    icon : 2,
                                    time : 2000
                                });
                            }
                        }
                    }
                });

                form.on('select(categoryLevelType)', function(data) {
                    categoryLevelTypeId = data.value;
                    var level = data.value - 1;
                    $("#parentCategory").html("<option value=\"0\">暂无父级类目</option>");
                    parentCategoryId = 0;
                    form.render();
                    if(level != 0){
                        $.ajax({
                            url:"<%=basePath%>plan/category/getListCategory.do",
                            type:'get',
                            data:{
                                "grade":level
                            },
                            beforeSend:function(){
                                layer.msg('正在加载类目信息...', {icon: 16,shade:[0.4,'#000'],time:false});
                            },
                            success:function (data) {
                                layer.close(layer.index);
                                if(data.count != 0){
                                    $("#parentCategory").html("");
                                    var result = data.data;
                                    $.each(result,function(i,item){
                                        if(i == 0){
                                            parentCategoryId = item.wid;
                                        }
                                        $("#parentCategory").append("<option value=\""+item.wid+"\">"+item.name+"</option>");
                                    });
                                    form.render();
                                }else{
                                    layer.msg("暂无类目显示请联系管理员！", {
                                        icon : 2,
                                        time : 2000
                                    });
                                }
                            }
                        })
                    }
                });

                form.on('select(parentCategory)', function(data) {
                    parentCategoryId = data.value;
                });

                form.render();
            });

            $("body").delegate(".add-addKeyWordGroup","click",function(){
                var keyWordsHtml = "" +
                    "<div class=\"categoryKeyWordItem\">" +
                    "   <div class=\"layui-input-inline newCategoryWidth\">" +
                    "     <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"主控词\" autocomplete=\"off\" class=\"layui-input keyWord\">" +
                    "   </div>" +
                    "   <div class=\"layui-input-inline newCategoryWidth\">" +
                    "      <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"单量\" autocomplete=\"off\" class=\"layui-input singleAmount\">" +
                    "   </div>" +
                    "   <div class=\"layui-input-inline newCategoryWidth\">" +
                    "      <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"销售额\" autocomplete=\"off\" class=\"layui-input salesVolume\">" +
                    "      <i class=\"layui-icon layui-icon-close add-icon-btn-close\"></i>" +
                    "   </div>" +
                    "</div>";
                $("#add-keyWords").append(keyWordsHtml);
            });
            $("body").delegate(".add-icon-btn-close","click",function(){
                $(this).parent().parent().remove();
            });
            // 新增类目-end

            $(".returnLevel").click(function(){
               if(urlFlag == "0"){

               }else if(urlFlag == "1"){
                   $(".returnLevel").removeClass("layui-btn-normal");
                   $(".returnLevel").addClass("layui-btn-disabled");
                   urlFlag = "0";
                   getTable();
               }else if(urlFlag == "2"){
                   urlFlag = "1";
                   if(lastPid != 0){
                       getTable(lastPid);
                   }else{
                       getTable();
                   }

               }
            });

            table.on('tool(categoryTable)', function(obj){
                var data = obj.data;

                if(obj.event === 'detailOne'){
                    urlFlag = "1";
                    getTable(data.wid);
                } else if(obj.event === 'detailTwo'){
                    urlFlag = "2";
                    lastPid = data.pid;
                    getTable(data.wid);
                } else if(obj.event === 'editTwo'){
                    editCategory(data);
                } else if(obj.event === 'delTwo'){
                    delCategory(data);
                } else if(obj.event === 'editThree'){
                    editCategory(data);
                } else if(obj.event === 'delThree'){
                    delCategory(data);
                }
                $(".returnLevel").removeClass("layui-btn-disabled");
                $(".returnLevel").addClass("layui-btn-normal");
            });
        });

        // 编辑类目-start

        $(".addKeyWordGroup").click(function(){
            var keyWordsHtml = "" +
                "<div class=\"categoryKeyWordItem\">" +
                "   <div class=\"layui-input-inline newCategoryWidth\">" +
                "     <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"主控词\" autocomplete=\"off\" class=\"layui-input keyWord\">" +
                "   </div>" +
                "   <div class=\"layui-input-inline newCategoryWidth\">" +
                "      <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"单量\" autocomplete=\"off\" class=\"layui-input singleAmount\">" +
                "   </div>" +
                "   <div class=\"layui-input-inline newCategoryWidth\">" +
                "      <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"销售额\" autocomplete=\"off\" class=\"layui-input salesVolume\">" +
                "      <i class=\"layui-icon layui-icon-close icon-btn-close\"></i>" +
                "   </div>" +
                "</div>";
            $("#keyWords").append(keyWordsHtml);
        });

        function editCategory(data){
            removeErrorInput();
            $.ajax({
                url:"<%=basePath%>plan/category/getInfoCategory.do",
                type:"get",
                data:{
                    id : data.id
                },
                beforeSend:function(){
                    layer.msg('正在加载类目信息...', {icon: 16,shade:[0.4,'#000'],time:false});
                },
                success:function(data){
                    if(data.code == 200){
                        layer.close(layer.index);
                        var param = data.paramObject;
                        $("input[name=categoryName]").val(param.name);
                        if(param.cmeta != "" && param.cmeta != "[]"){
                            $("#keyWords").html("");
                            var cmeta = JSON.parse(param.cmeta);
                            $.each(cmeta,function(i,item){
                                var keyWordsHtml = "" +
                                    "<div class=\"categoryKeyWordItem\">" +
                                    "   <div class=\"layui-input-inline newCategoryWidth\">" +
                                    "      <input type=\"text\" name=\"email\" value=\""+item.mainkey+"\" lay-verify=\"email\" placeholder=\"主控词\" autocomplete=\"off\" class=\"layui-input keyWord\">" +
                                    "   </div>" +
                                    "   <div class=\"layui-input-inline newCategoryWidth\">" +
                                    "      <input type=\"text\" name=\"email\" value=\""+item.orders+"\" lay-verify=\"email\" placeholder=\"单量\" autocomplete=\"off\" class=\"layui-input singleAmount\">" +
                                    "   </div>" +
                                    "   <div class=\"layui-input-inline newCategoryWidth\">" +
                                    "      <input type=\"text\" name=\"email\" value=\""+item.sales+"\" lay-verify=\"email\" placeholder=\"销售额\" autocomplete=\"off\" class=\"layui-input salesVolume\">" +
                                    "      <i class=\"layui-icon layui-icon-close icon-btn-close\"></i>" +
                                    "   </div>" +
                                    "</div>";
                                $("#keyWords").append(keyWordsHtml);
                            });
                        } else {
                            var keyWordsHtml = "" +
                                "<div class=\"categoryKeyWordItem\">" +
                                "   <div class=\"layui-input-inline newCategoryWidth\">" +
                                "       <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"主控词\" autocomplete=\"off\" class=\"layui-input keyWord\">" +
                                "   </div>" +
                                "   <div class=\"layui-input-inline newCategoryWidth\">" +
                                "      <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"单量\" autocomplete=\"off\" class=\"layui-input singleAmount\">" +
                                "   </div>" +
                                "   <div class=\"layui-input-inline newCategoryWidth\">" +
                                "      <input type=\"text\" name=\"email\" lay-verify=\"email\" placeholder=\"销售额\" autocomplete=\"off\" class=\"layui-input salesVolume\">" +
                                "      <i class=\"layui-icon layui-icon-close icon-btn-close\"></i>" +
                                "   </div>" +
                                "</div>";
                            $("#keyWords").html(keyWordsHtml);
                        }


                        var editLayerIndex = layer.open({
                            type: 1 ,
                            area: ['450px', '400px'],  //指定弹出层尺寸
                            content: $("#categoryEdit"),
                            title:'修改类目',
                            btn:["立即修改"],
                            btnAlign: 'c',
                            btn1:function () {
                                var name = $("#categoryEdit input[name=categoryName]").val();
                                if(name != ""){
                                    // keyWord   singleAmount   salesVolume
                                    var result = new Array();
                                    var keyWords = $("#keyWords .keyWord");
                                    var singleAmounts = $("#keyWords .singleAmount");
                                    var salesVolumes = $("#keyWords .salesVolume");
                                    $("#keyWords .keyWord").each(function (i,item) {
                                        var keyword = $(keyWords[i]).val();
                                        var orders = $(singleAmounts[i]).val();
                                        var sales = $(salesVolumes[i]).val();
                                        if(keyword != "" && orders != ""){
                                            var obj = {};
                                            obj.mainkey = keyword;
                                            obj.orders = orders;
                                            obj.sales = sales;
                                            result.push(obj);
                                        }
                                        if(keyword == ""){
                                            $(keyWords[i]).addClass("errorInput");
                                        }
                                        if(orders == ""){
                                            $(singleAmounts[i]).addClass("errorInput");
                                        }
                                    });
                                    if(result.length == keyWords.length){
                                        var resultStr = JSON.stringify(result);
                                        $.ajax({
                                            url:"<%=basePath%>plan/category/editCategory.do",
                                            type:"post",
                                            data:{
                                                "id":param.id,
                                                "name": name,
                                                "editInfo":resultStr
                                            },
                                            beforeSend:function(){
                                                layer.msg('正在修改类目...', {icon: 16,shade:[0.4,'#000'],time:false});
                                            },
                                            success:function(data2){
                                                if(data2.code == 200){
                                                    layer.msg("修改类目信息成功");
                                                    layer.close(editLayerIndex);
                                                    getTable(param.pid);

                                                }else{
                                                    layer.msg(data2.paramObject, {
                                                        icon : 2,
                                                        time : 2000
                                                    });
                                                }
                                            }
                                        });
                                    }else{
                                        layer.msg("保存失败！请检查填写内容。", {
                                            icon : 2,
                                            time : 2000
                                        });
                                    }
                                }else{
                                    layer.msg("类目名称不可为空。", {
                                        icon : 2,
                                        time : 2000
                                    });
                                }
                            }
                        });
                    }else{
                        layer.msg(data.paramObject, {
                            icon : 2,
                            time : 2000
                        });
                    }
                }
            });
        }

        $("body").delegate(".icon-btn-close","click",function(){
            $(this).parent().parent().remove();
        });
        // 编辑类目-end

        function removeErrorInput(){
            $(".errorInput").each(function(){
                $(this).removeClass("errorInput");
            })
        }

        function getTable(wid) {
            var url = "<%=basePath%>plan/category/getListCategory.do?grade=#grade";
            if(wid != 0 && wid != undefined){
                url = "<%=basePath%>plan/category/getListCategory.do?grade=#grade&pid="+wid;
            }
            if(urlFlag == "0"){
                url = url.replace("#grade","1");
                oneTable(url);
            } else if (urlFlag == "1"){
                url = url.replace("#grade","2");
                twoTable(url);
            } else if (urlFlag == "2"){
                url = url.replace("#grade","3");
                threeTable(url);
            }
        }

        function delCategory(sourceData){
            layer.confirm('您确定要删除这个类目吗？删除后将无法恢复数据', {
                btn: ['确定','关闭'] //按钮
            }, function(){
                $.ajax({
                    url: '<%=basePath%>plan/category/delCategory.do',
                    type: 'post',
                    data: {
                        "id" : sourceData.id,
                        "wid" : sourceData.wid
                    },
                    beforeSend:function(){
                        layer.msg('正在删除类目...', {icon: 16,shade:[0.4,'#000'],time:false});
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            layer.msg("删除类目成功！");
                            getTable(sourceData.pid);
                        }else{
                            layer.msg(data.paramObject, {
                                icon : 2,
                                time : 2000
                            });
                        }
                    }
                });
            }, function(){
                layer.closeAll();
            });
        }

        function oneTable(url){
            table.render({
                elem: '#categoryTable'
                ,id:'categoryTableList'
                ,url:url
                ,even: true
                ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                    curr:1
                    ,layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                    ,groups: 3 //只显示 1 个连续页码
                }
                ,limit: 17 //每页默认显示的数量
                ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {field:'id', title: 'ID'}
                    ,{field:'wid', title: '类目ID'}
                    ,{field:'name', title: '类目名称'}
                    ,{field:'pid', title: '父ID'}
                    ,{field:'grade', title: '类目级别'}
                    ,{field:'score', title: '操作', toolbar:'#toolOne', width:100}
                ]]
            });
        }

        function twoTable(url){
            table.reload("categoryTableList",{
                id:'categoryTableList'
                ,url:url
                ,even: true
                ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                    curr:1
                    ,layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                    ,groups: 3 //只显示 1 个连续页码
                }
                ,limit: 17 //每页默认显示的数量
                ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {field:'id', title: 'ID'}
                    ,{field:'wid', title: '类目ID'}
                    ,{field:'name', title: '类目名称'}
                    ,{field:'pid', title: '父ID'}
                    ,{field:'grade', title: '类目级别'}
                    ,{field:'tool', title: '操作', toolbar:'#toolTwo', width:220}
                ]]
            });
        }

        function threeTable(url){
            table.reload("categoryTableList",{
                id:'categoryTableList'
                ,url:url
                ,even: true
                ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                    curr:1
                    ,layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                    ,groups: 3 //只显示 1 个连续页码
                }
                ,limit: 17 //每页默认显示的数量
                ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {field:'id', title: 'ID'}
                    ,{field:'wid', title: '类目ID'}
                    ,{field:'name', title: '类目名称'}
                    ,{field:'pid', title: '父ID'}
                    ,{field:'grade', title: '类目级别'}
                    ,{field:'tool', title: '操作', toolbar:'#toolThree', width:220}
                ]]
            });
        }
    </script>
</body>
</html>
