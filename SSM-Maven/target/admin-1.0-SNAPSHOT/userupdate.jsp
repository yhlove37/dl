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
<%--jquery验证--%>
<script>
    $(function () {
        //验证用户不合法
        $("#sub_btn").click(function () {
            $("span.errorMsg1").text("");
            $("span.errorMsg2").text("");
            $("span.errorMsg3").text("")
            $("span.errorMsg4").text("")
            $("span.errorMsg5").text("")
            $("span.errorMsg6").text("")

            var flag = 1;
            var userNameText = $("#userName").val();
            var userNamePatt = /^\w{2,5}$/;
            if (!userNamePatt.test(userNameText)) {
                $("span.errorMsg1").text("用户名不合法!，2-5字母与数字组成")
                flag = 0;
            }
            //验证密码格式不相同
            var passwordText = $("#password").val();
            var passwordPatt = /^\w{5,12}$/;
            if (!passwordPatt.test(passwordText)) {
                $("span.errorMsg2").text("密码不正确!，5-12字母与数字组成")
                flag = 0;
            }
            //验证密码不相同
            var conPasswordText = $("#Conpassword").val();
            if (conPasswordText != passwordText) {
                $("span.errorMsg3").text("两次密码不相同！")
                flag = 0;
            }

            //验证邮箱格式不相同
            var emailText = $("#email").val();
            var emailPatt = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if (!emailPatt.test(emailText)) {
                $("span.errorMsg4").text("邮箱的格式不zhe'na'que")
                flag = 0;
            }

            //验证电话号码的格式
            var telephoneText = $("#telephone").val();
            var telephonePatt = /^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/;
            if (!telephonePatt.test(telephoneText)) {
                $("span.errorMsg5").text("电话的格式不正确")
                flag = 0;
            }

            if (flag == 0) {
                return false;
            }
            if (confirm("是否确认修改用户信息")) {
                //确认执行时的代码
            } else {
                return false;//取消执行时的代码
            }


        })
    })


</script>
<body class="bg-primary">
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
                    img.setAttribute('src', imgUrl); // 修改img标签src属性值
                };
            </script>

            <form class="form-horizontal" action="${pageContext.request.contextPath}/user/updateaction" method="post"
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
                    <label for="email" class="col-sm-3 control-label">确认你的密码</label>
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
                    <label for="headfile" class="col-sm-3 control-label" >头像上传</label>
                    <div class="col-sm-6">
                        <input id="headfile" type="file" class="form-control" name="headfile" onchange="imgChange(this)">

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
                        <button id="sub_btn" type="submit" class="btn btn-primary">修改</button>
                    </div>

                </div>


            </form>

        </div>
    </div>


</div>

</body>
</html>
