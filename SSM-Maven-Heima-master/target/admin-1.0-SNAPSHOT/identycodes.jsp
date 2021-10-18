<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css" />
<script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
<!-- 引入表单校验jquery插件 -->
<script src="../js/jquery.validate.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="../css/style.css" type="text/css" />

<style>
    body {
        margin-top: 20px;
        margin: 0 auto;
    }

    .carousel-inner .item img {
        width: 100%;
        height: 300px;
    }

    font {
        color: #3164af;
        font-size: 18px;
        font-weight: normal;
        padding: 0 10px;
    }

    .error{
        color:red
    }
</style>



</head>
<body>

<!-- 引入header.jsp -->
<jsp:include page="/header.jsp"></jsp:include>

<script>
    $(function (){
        //验证用户不合法
        $("#sub_btn").click(function (){
            $("span.errorMsg1").text("");

            var codes= $("#codes").val();
            var activecodes = $("#activecodes").val();


            if (codes!=null&&codes==activecodes){
                alert("账户激活成功");
                return true;

            }else{
                $("span.errorMsg1").text("验证码不能为空或验证码错误");
                return false;
            }

        })
    })

</script>

<div class="container"
     style="width: 100%; background: url('../image/regist_bg.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8"
             style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
            <font>激活用户</font>USER REGISTER
            <form id="myform" class="form-horizontal" action="${pageContext.request.contextPath }/register/active" method="get" style="margin-top: 5px;">
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">用户邮箱</label>
                    <div class="col-sm-6">
                        <input type="email" class="form-control" id="email" name="email" value="${email}"
                               placeholder="用户邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <label for="codes" class="col-sm-2 control-label">输入验证码</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="codes" name="codes"
                               placeholder="获取验证码">
                        <span style="color: red" class="errorMsg1"></span>
                    </div>
                </div>


                <input id="activecodes" type="hidden" name="activecodes" value="${activeCodes}"/>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button id="sub_btn" type="submit" width="100"
                                style="background: url('../images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">激活用户</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>

<!-- 引入footer.jsp -->
<div class="container-fluid">
    <div style="margin-top:50px;">
        <img src="../img/footer.jpg" width="100%"  alt="我们的优势" title="我们的优势" />
    </div>

    <div style="text-align: center;margin-top: 5px;">
        <ul class="list-inline">
            <li><a>关于我们</a></li>
            <li><a>联系我们</a></li>
            <li><a>招贤纳士</a></li>
            <li><a>法律声明</a></li>
            <li><a>友情链接</a></li>
            <li><a>支付方式</a></li>
            <li><a>配送方式</a></li>
            <li><a>服务声明</a></li>
            <li><a>广告声明</a></li>
        </ul>
    </div>
    <div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
        Copyright &copy; 2005-2016  版权所有
    </div>
</div>

</body>
</html>




