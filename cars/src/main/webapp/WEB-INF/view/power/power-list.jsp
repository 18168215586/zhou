<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html class="x-admin-sm">
    <head>
        <base href="${basePath}">
        <meta charset="UTF-8">
        <title>欢迎页面-X-admin2.2</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="stylesheet" href="static/css/font.css">
        <link rel="stylesheet" href="static/css/xadmin.css">
        <script src="static/js/jquery.min.js"></script>

        <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
        <script src="static/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="static/js/xadmin.js"></script>
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
    <div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">演示</a>
            <a>
              <cite>导航元素</cite></a>
          </span>
        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
    </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <table class="layui-hide" id="powerTable" lay-filter="powerTable"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>





    <script type="text/html" id="toolbar">
        <button type="button" class="layui-btn layui-btn-sm" onclick="xadmin.open('用户添加','power/toEdit.do',600,400);">添加
        </button>
    </script>

    <script type="text/html" id="opt">
        <button type="button" class="layui-btn layui-btn-sm" lay-event="ednt">修改</button>
        <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">删除</button>
    </script>

    <script>

        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#powerTable',
                url:'power/getAll.do',
                method:'get',
                page:true,
                toolbar:'#toolbar',
                cols: [[
                    {field:'id', title: '编号', sort: true}
                    ,{field:'power', title: '权限'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'status', title: '状态', sort: true}
                    ,{field:'createDate', title: '创建时间'}
                    ,{field:'createAdmin', title: '创建人'}
                    ,{field:'updateDate', title: '修改时间'} //单元格内容水平居中
                    ,{field:'updateAdmin', title: '修改人'} //单元格内容水平居中
                    ,{title:'操作',fixed:'right',toolbar:'#opt'}

                ]]

            });
            table.on('tool(powerTable)',function (data) {
                if (data.event=='del'){
                    layer.confirm('确定删除本数据吗？',function () {
                        $.ajax({
                            url: 'power/delete.do',
                            data:{
                                id:data.data.id
                            },
                            method: 'post',
                            dataType:'json',
                            success:function (res) {
                                layer.alert(res.msg,function (index) {
                                    layer.close(index);
                                    if (res.code==1){
                                        table.reload('powerTable')
                                    }
                                });
                            },
                            error:function (e) {
                                console.log(e);
                                layer.alert('与服务器链接失败，请稍后在试...',{icon:5});
                            }
                        })
                    })
                }
            })
        });


      /* /!*用户-停用*!/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }*/






    </script>

</html>