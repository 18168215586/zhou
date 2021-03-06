<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="x-admin-sm">
    <head>
        <base href="${basePath}">
        <meta charset="UTF-8">
        <title>逆时光网咖欢迎您</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <link rel="stylesheet" href="static/css/font.css">
        <link rel="stylesheet" href="static/css/xadmin.css">
        <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
        <script src="static/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="static/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script>
            // 是否开启刷新记忆tab功能
            // var is_remember = false;
        </script>
    </head>
    <body class="index">
        <!-- 顶部开始 -->
        <div class="container">
            <div class="logo">
                <a href="./index.html">逆时光后台</a></div>
            <div class="left_open">
                <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
            </div>
            <ul class="layui-nav left fast-add" lay-filter="">
                <li class="layui-nav-item">

                    <dl class="layui-nav-child">
                        <!-- 二级菜单 -->
                        <dd>
                            <a onclick="xadmin.open('最大化','http://www.baidu.com','','',true)">
                                <i class="iconfont">&#xe6a2;</i>弹出最大化</a></dd>
                        <dd>
                            <a onclick="xadmin.open('弹出自动宽高','http://www.baidu.com')">
                                <i class="iconfont">&#xe6a8;</i>弹出自动宽高</a></dd>
                        <dd>
                            <a onclick="xadmin.open('弹出指定宽高','http://www.baidu.com',500,300)">
                                <i class="iconfont">&#xe6a8;</i>弹出指定宽高</a></dd>
                        <dd>
                            <a onclick="xadmin.add_tab('在tab打开','member-list.html')">
                                <i class="iconfont">&#xe6b8;</i>在tab打开</a></dd>
                        <dd>
                            <a onclick="xadmin.add_tab('在tab打开刷新','member-del.html',true)">
                                <i class="iconfont">&#xe6b8;</i>在tab打开刷新</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav right" lay-filter="">
                <li class="layui-nav-item">
                    <a href="javascript:;">${loginUser.realname}</a>
                    <dl class="layui-nav-child">
                        <!-- 二级菜单 -->
                        <dd>
                            <a onclick="xadmin.open('个人信息','user/toEdit.do?id=${loginUser.id}',600,400)">个人信息</a></dd>
                        <dd>
                            <a onclick="xadmin.open('切换帐号','user/toPwd.do',600,400)">修改密码</a></dd>
                        <dd>
                            <a href="exit.do">退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item to-index">
                    <a href="/">前台首页</a></li>
            </ul>
        </div>
        <!-- 顶部结束 -->
        <!-- 中部开始 -->
        <!-- 左侧菜单开始 -->
        <div class="left-nav">
            <div id="side-nav">
                <ul id="nav">
                    <c:if test="${loginUser.powerid==1}">
                        <li>
                        <a href="javascript:;">
                            <i class="iconfont left-nav-li" lay-tips="会员管理">&#xe6b8;</i>
                            <cite>用户管理</cite>
                            <i class="iconfont nav_right">&#xe697;</i></a>
                        <ul class="sub-menu">
                            <li>
                                <a onclick="xadmin.add_tab('用户列表','user/toList.do',true)">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>用户列表</cite></a>
                            </li>
                            <li>
                                <a onclick="xadmin.add_tab('权限列表','power/toList.do',true)">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>权限列表</cite></a>
                            </li>
                        </ul>
                    </li>
                    </c:if>

                    <c:if test="${loginUser.powerid<3}">
                        <li>
                        <a href="javascript:;">
                            <i class="iconfont left-nav-li" lay-tips="车辆管理">&#xe723;</i>
                            <cite>车辆管理</cite>
                            <i class="iconfont nav_right">&#xe697;</i></a>
                        <ul class="sub-menu">
                            <li>
                                <a onclick="xadmin.add_tab('车辆列表','cars//toList.do',true)">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>车辆列表</cite></a>
                            </li>
                            <li>
                                <a onclick="xadmin.add_tab('品牌管理','brand/toList.do',true)">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>品牌管理</cite></a>
                            </li>
                        </ul>
                    </li>
                    </c:if>


                    <li>
                        <a href="javascript:;">
                            <i class="iconfont left-nav-li" lay-tips="用车管理">&#xe723;</i>
                            <cite>用车管理</cite>
                            <i class="iconfont nav_right">&#xe697;</i></a>
                        <ul class="sub-menu">
                            <c:if test="${loginUser.powerid>2}">
                                <li>
                                <a onclick="xadmin.add_tab('借车管理','cars/toList.do',true)">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>借车管理</cite></a>
                            </li>
                            </c:if>

                            <c:if test="${loginUser.powerid==2}">
                            <li>
                                <a onclick="xadmin.add_tab('还车管理','cate.html')">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>还车管理</cite></a>
                            </li>
                            </c:if>

                            <li>
                                <a onclick="xadmin.add_tab('借车记录','record/borrowList.do?apply=10',true)">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>借车记录</cite></a>
                            </li>
                            <li>
                                <a onclick="xadmin.add_tab('还车记录','record/borrowList.do?apply=40',true)">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>还车记录</cite></a>
                            </li>
                            <li>
                                <a onclick="xadmin.add_tab('所有记录','record/borrowList.do',true)">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>所有记录</cite></a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <!-- <div class="x-slide_left"></div> -->
        <!-- 左侧菜单结束 -->
        <!-- 右侧主体开始 -->
        <div class="page-content">
            <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
                <ul class="layui-tab-title">
                    <li class="home">
                        <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
                <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
                    <dl>
                        <dd data-type="this">关闭当前</dd>
                        <dd data-type="other">关闭其它</dd>
                        <dd data-type="all">关闭全部</dd></dl>
                </div>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <iframe src='welcome.do' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
                    </div>
                </div>
                <div id="tab_show"></div>
            </div>
        </div>
        <div class="page-content-bg"></div>
        <style id="theme_style"></style>
        <!-- 右侧主体结束 -->
        <!-- 中部结束 -->
    </body>

</html>