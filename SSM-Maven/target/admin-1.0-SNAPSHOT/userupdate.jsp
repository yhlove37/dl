<%--
  Created by IntelliJ IDEA.
  User: yh139
  Date: 9/19/2021
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息修改</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

    <!-- 弹出层插件 -->
    <link href="${pageContext.request.contextPath}/css/popup_layer.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/popup_layer.js"></script>
</head>

<style>
    #d1 {
        width: 100px;
        height: 100px;
        border: 1px solid blue;
        border-radius: 50%;
        overflow: hidden;

    }

    #img1 {
        max-height: 100%;
        width: 100%;

    }
</style>

<script type="text/javascript">
    $(function () {
        //弹出层插件调用
        new PopupLayer({
            trigger: ".clickedElement",//触发点 点击谁弹出div
            popupBlk: "#showDiv",//弹出哪个div
            closeBtn: "#closeBtn",//关闭按钮
            useOverlay: true
        });

    });

    //点击按钮查询某个订单的详情
    function findOrderInfoByOid() {

        $("#loading").css("display", "block");
        $('#uusername').html($('#userName').val())

        $('#upassword').html($('#password').val())

        $('#uemail').html($('#email').val())

        $('#utelephone').html($('#telephone').val())

        $('#ubirthday').html($('#birthday').val())
        if ($('.form-check-input').val() == 1) {
            $('#usex').html('男')
        } else {
            $('#usex').html('女')
        }

    }


</script>
<%--jquery验证--%>
<script>
    $(function () {

        var flag = 1;
        $('#userName').blur(function () {
            var userNameText = $("#userName").val();
            var userNamePatt = /^\w{2,5}$/;
            if (!userNamePatt.test(userNameText)) {
                $("span.errorMsg1").text("用户名不合法!，2-5字母与数字组成")
                flag = 0;
            } else {
                $("span.errorMsg1").text("")
            }
        })
        //验证密码格式不相同
        $('#password').blur(function () {
            var passwordText = $("#password").val();
            var passwordPatt = /^\w{5,12}$/;
            if (!passwordPatt.test(passwordText)) {
                $("span.errorMsg2").text("密码不正确!，5-12字母与数字组成")
                flag = 0;
            } else {
                $("span.errorMsg2").text("")
            }
        })
        //验证密码不相同
        $('#Conpassword').blur(function () {
            var conPasswordText = $("#Conpassword").val();
            var passwordText = $("#password").val();
            if (conPasswordText != passwordText) {
                $("span.errorMsg3").text("两次密码不相同！")
                flag = 0;
            } else {
                $("span.errorMsg3").text("")
            }
        })

        //验证邮箱格式不相同
        $('#email').blur(function () {
            var emailText = $("#email").val();
            var emailPatt = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if (!emailPatt.test(emailText)) {
                $("span.errorMsg4").text("邮箱的格式不正确")
                flag = 0;
            } else {
                $("span.errorMsg4").text("")
            }
        })

        $('#telephone').blur(function () {
            var telephoneText = $("#telephone").val();
            var telephonePatt = /^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/;
            if (!telephonePatt.test(telephoneText)) {
                $("span.errorMsg5").text("电话的格式不正确")
                flag = 0;
            } else {
                $("span.errorMsg5").text("")
            }
        })


        $("#sub_btn").click(function () {
            if (flag == 0) {
                alert("请确保格式正确")
            } else {
                findOrderInfoByOid()
            }

        });

    })


</script>


<body class="bg-primary" style="width: 100%">
<div style="position: absolute;margin-top: 10%;left: 10%">
    <div class="container">
        <div class="form row">
            <div>
                <h3 class="form-title" style="text-align: center">用户信息</h3>
                <div id="d1" style="margin: auto">
                    <img id="img1" src="${pageContext.request.contextPath}/img/head.png"
                         alt="">
                </div>
            </div>

            <script type="text/javascript">
                // 选择图片显示
                function imgChange(obj) {

                    var file = document.getElementById("headfile");
                    var imgUrl = window.URL.createObjectURL(file.files[0]);
                    var img = document.getElementById('img1');
                    var img2 = document.getElementById('img2')
                    img.setAttribute('src', imgUrl); // 修改img标签src属性值
                    img2.setAttribute('src', imgUrl); // 修改img标签src属性值
                };
            </script>

            <form class="form-horizontal" id="action" action="${pageContext.request.contextPath}/user/updateaction"
                  method="post"
                  enctype="multipart/form-data">

                <!--                设置隐藏域传递id-->
                <input hidden name="uid" value="${user.uid}">


                <div class="form-group">
                    <label for="userName" class="col-sm-3 control-label">用户名</label>
                    <div class="col-sm-6">
                        <input id="userName" value="${user.username}" type="text" class="form-control" name="username"
                               placeholder="LastName">
                        <span class="errorMsg1" style="color: red"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">用户密码</label>
                    <div class="col-sm-6">
                        <input id="password" value="${user.password}" type="password" class="form-control">
                        <span class="errorMsg2" style="color: red"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="Conpassword" class="col-sm-3 control-label">确认你的密码</label>
                    <div class="col-sm-6">
                        <input id="Conpassword" value="${user.password}" type="password" class="form-control"
                               name="password">
                        <span class="errorMsg3" style="color: red"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label">确认你的邮箱</label>
                    <div class="col-sm-6">
                        <input id="email" value="${user.email}" type="email" class="form-control" name="email"
                               placeholder="1176244270@qq.com">
                        <span class="errorMsg4" style="color: red"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="telephone" class="col-sm-3 control-label">电话号码</label>
                    <div class="col-sm-6">
                        <input id="telephone" value="${user.telephone}" type="text" class="form-control"
                               name="telephone"
                               placeholder="13915471519">
                        <span class="errorMsg5" style="color: red"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="headfile" class="col-sm-3 control-label">头像上传</label>
                    <div class="col-sm-6">
                        <input id="headfile" type="file" class="form-control" name="headfile"
                               onchange="imgChange(this)">

                    </div>
                </div>


                <div class="form-group">
                    <label for="birthday" class="col-sm-3 control-label">出生日期</label>
                    <div class="col-sm-6">
                        <input type="date" id="birthday" class="form-control" name="birthday"
                               value="${user.getBirthday()}">
                        <span class="errorMsg6" style="color: red"></span>

                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-3 control-label">性别</label>
                    <div class="col-sm-6">
                        <div class="form-check form-check-inline">
                            <input checked="${user.sex=="男"}" class="form-check-input" type="radio" name="sex"
                                   value="1">
                            <label class="form-check-label"> 男</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input checked="${user.sex=="女"}" class="form-check-input" type="radio" name="sex"
                                   value="0">
                            <label class="form-check-label">女</label>
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-10">
                        <input  type="button" class="btn btn-primary clickedElement"  value="点击修改" class="clickedElement" onclick="findOrderInfoByOid()"/>
                    </div>

                </div>


            </form>

        </div>
    </div>


</div>


<!-- 弹出层 HaoHao added -->
<div id="showDiv" class="blk" style="display:none; height: 85%; position: absolute; left: 120%; ">
    <div class="main" style="height:120% ;width: 80%">
        <h2>用户信息：<span id="shodDivOid" style="font-size: 13px;color: #999">123456789</span></h2>
        <a href="javascript:void(0);" id="closeBtn" class="closeBtn">关闭</a>
        <%--				模拟加载的gif--%>
        <div style="padding:20px;">
            <table id="showDivTab" style="width:100%">
                <div class="panel-body">
                    <%--                Bootstrap3基础 form-horizontal 表单元素横向布局 简单示例--%>
                    <form class="form-horizontal" role="form">

                        <div class="form-group row">
                            <label style="color: black" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <p style="color: black" class="form-control-static" id="uusername"></p>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label style="color: black" class="col-sm-2 control-label">用户密码</label>
                            <div class="col-sm-10">
                                <p style="color: black" class="form-control-static" id="upassword"></p>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label style="color: black" class="col-sm-2 control-label">电话号码</label>
                            <div class="col-sm-10">
                                <p style="color: black" class="form-control-static" id="utelephone">
                                </p>
                            </div>
                        </div>

                        <div class="form-group row user_photo">
                            <label style="color: black" class="col-sm-2 control-label">头像</label>
                            <div class="col-sm-10">

                                <%--默认图片--%>
                                <img id="img2" style="width: 100px;height: 100px"
                                     src="${pageContext.request.contextPath}/img/head.png"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label style="color: black" class="col-sm-2 control-label">出生日期</label>
                            <div class="col-sm-10">
                                <p style="color: black" class="form-control-static" id="ubirthday">

                                </p>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label style="color: black" class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <p style="color: black" class="form-control-static" id="usex">
                                </p>
                            </div>
                        </div>

                        <script>
                            function gogo() {

                                $('#action').submit();
                            }
                        </script>

                        <input class="btn btn-primary" value="确定修改" onclick="gogo()"/>

                    </form>

                </div>

            </table>
        </div>
    </div>

</div>

</body>
</html>
