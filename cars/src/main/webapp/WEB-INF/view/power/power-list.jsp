<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <base href="${basePath}">
    <meta charset="UTF-8">
    <title>权限列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="static/css/font.css">
    <link rel="stylesheet" href="static/css/xadmin.css">
    <script src="static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="static/js/xadmin.js"></script>
    <script src="static/js/jquery.min.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-nav">
            <span class="layui-breadcrumb">
                <a href="">首页</a>
                <a href="">用户管理</a>
                <a>
                    <cite>权限列表</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" class="layui-input" name="power" id="power" required lay-verify="required" placeholder="权限名">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="add">添加</button>
                        </div>
                    </form>
                    <table class="layui-table"  lay-filter="infoTable" id="infoTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="opt">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">删除</button>
</script>
<script type="text/html" id="staTpl">
    <input type="checkbox" value="{{d.id}}" lay-text="可用|禁用" lay-skin="switch" lay-filter="staFilter" {{d.status==1?'checked':''}}>
</script>
<script>
    $(function () {
        layui.use(['table','form'],function () {
            var table=layui.table;
            var form=layui.form;
            table.render({
                elem:'#infoTable',
                url:'power/getPage.do',
                method:'get',
                page:true,
                toolbar:true,
                cols:[[
                    {field:'id',title:'编号',sort:true},
                    {field:'power',title:'权限名',edit:'text'},
                    {field:'createDate',title:'创建时间'},
                    {field:'createAdmin',title:'创建人'},
                    {field:'status',title:'状态',templet:'#staTpl'},
                    {title:'操作',fixed:'right',toolbar:'#opt'}
                ]]
            });
            form.on('submit(add)',function (data) {
                $.ajax({
                    url:'power/doEdit.do',
                    data:data.field,
                    method:'post',
                    dataType:'json',
                    success:function (res) {
                        layer.alert(res.msg,{icon:res.code==1?6:5},function (index) {
                            $('#power').val('');
                            layer.close(index);
                            table.reload('infoTable');
                        });
                    },
                    error:function (e) {
                        layer.alert('添加权限出现异常！');
                        console.log(e);
                    }

                });
                return false;
            });
            form.on('switch(staFilter)',function (data) {
                $.ajax({
                    url:'power/doEdit.do',
                    data:{
                        id:data.value,
                        status:data.elem.checked?1:2
                    },
                    method:'post',
                    dataType:'json',
                    success:function (res) {
                        layer.msg(res.msg,{icon:res.code==1?6:5});
                        table.reload('infoTable');
                    },
                    error:function (e) {
                        console.log(e);
                        layer.alert('与服务器链接失败，请稍后再试...');
                    }
                });
            });

            /*监听修改删除按钮的单击事件*/
            table.on('tool(infoTable)',function (data) {
                if(data.event=='del'){
                    layer.confirm('确定删除本数据吗？',function () {
                        $.ajax({
                            url:'power/delete.do',
                            data:{
                                id:data.data.id
                            },
                            method:'post',
                            dataType:'json',
                            success:function (res) {
                                layer.alert(res.msg,function (index) {
                                    layer.close(index);
                                    if(res.code==1){
                                        table.reload('infoTable');
                                    }
                                });
                            },
                            error:function (e) {
                                console.log(e);
                                layer.alert('与服务器链接失败，请稍后再试...',{icon:5});
                            }
                        });
                    });
                }
            });
            table.on('edit(infoTable)',function (obj) {
                $.ajax({
                    url:'power/doEdit.do',
                    data:{
                        id:obj.data.id,
                        power:obj.value
                    },
                    dataType:'json',
                    success:function (res) {
                        layer.msg(res.msg,{icon:res.code==1?6:5,time:1000});
                        table.reload('infoTable');
                    }
                });
            });


        });
    });
</script>
</html>