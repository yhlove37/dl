<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台表单</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <link rel="stylesheet" href="./css/index.css">
</head>

<body class="layui-layout-body">
    <div class="layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">layui 后台布局</div>
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="">控制台</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">其它系统</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">授权管理</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="./images/img.jpg" class="layui-nav-img">
                        admin
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="">资料</a></dd>
                        <dd><a href="">设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/index">退出</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">主页</a>
                    </li>
                    <li class="layui-nav-item">
                        <a data_herf="${pageContext.request.contextPath}/adminCatagory" href="javascript:;">商品分类管理</a>
                    </li>

                    <li class="layui-nav-item">
                        <a data_herf="${pageContext.request.contextPath}/adminProductList" href="javascript:;">商品信息管理</a>
                    </li>
                    <li class="layui-nav-item">
                        <a data_herf="${pageContext.request.contextPath}/findAllOrderss" href="javascript:;">订单信息管理</a>
                    </li>
                    <li class="layui-nav-item">
                        <a data_herf="${pageContext.request.contextPath}/user/findAllusers" href="javascript:;">用户管理</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="layui-body">
            <!-- 内容主体区域 -->
            <iframe class="layadmin-iframe" id="Z_iframe"  frameborder="0"></iframe>
        </div>

        <div class="layui-footer">
            <!-- 底部固定区域 -->
            © xxxx.com - 底部固定区域
        </div>
    </div>
    <script src="./layui/layui.js"></script>
    <script>
        //JavaScript代码区域
        layui.use('element', function () {
            var element = layui.element;
            var Z_iframe = document.getElementById("Z_iframe")
            element.on('nav(test)', function (data) {
                // console.log(data)
                let href = data[0].attributes[0].nodeValue
                Z_iframe.src = href
            });


        });
    </script>
</body>

</html>