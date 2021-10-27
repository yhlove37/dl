<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<script>
	$(function () {
		var flag = 1;

		$("#submit").click(function () {
			if (flag==1){
				return true
			}
			alert("提交失败")
			return false
		})

		//验证用户不合法
		$("#addressname").blur(function () {
			var addressnameText = $("#addressname").val();
			if ( addressnameText == "") {
				$("span.errorMsg1").text("用户名不能为空")
				$("span.errorMsg1").css("color", "red")
				flag = 0;
			} else {
				$("span.errorMsg1").text("*√")
				$("span.errorMsg1").css("color", "green")
				flag = 1;

			}
		})

			$("#address").blur(function () {
				var addressText = $("#address").val();

				if (addressText=="") {
					$("span.errorMsg2").text("收货地址不能为空")
					$("span.errorMsg2").css("color","red");
					flag = 0;
				} else{
					$("span.errorMsg2").text("*√")
					$("span.errorMsg2").css("color", "green")
					flag = 1;

				}
			})


		$("#telephone").blur(function () {
			var telephoneText = $("#telephone").val();
			var telephonePatt = /^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/;
			if (!telephonePatt.test(telephoneText)) {
				$("span.errorMsg3").text("电话的格式错误")
				$("span.errorMsg3").css("color","red");
				flag = 0;
			}else {
				$("span.errorMsg3").text("*√")
				$("span.errorMsg3").css("color", "green")
				flag = 1;

			}
		})
	})


</script>


<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div style="margin: 0 auto; margin-top: 10px; width: 100%;">
				<strong>订单详情</strong>
				<table class="table table-bordered">
					<tbody>
						<tr class="warning">
							<th colspan="5">订单编号:${order.oid}</th>
						</tr>
						<tr class="warning">
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<c:forEach items="${order.orderItems}" var="orderItem">
							<tr class="active">
								<td width="60" width="40%"><input type="hidden" name="id"
																  value="22"> <img src="${pageContext.request.contextPath}/${orderItem.product.pimage}" width="70"
																				   height="60"></td>
								<td width="30%"><a target="_blank"> ${orderItem.product.pname}</a></td>
								<td width="20%">￥${orderItem.product.shop_price}</td>
								<td width="10%">${orderItem.count}</td>
								<td width="15%"><span class="subtotal">￥${orderItem.subtotal}</span></td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>

			<div style="text-align: right; margin-right: 120px;">
				商品金额: <strong style="color: #ff6600;">￥${order.total}元</strong>
			</div>

		</div>

		<div>
			<hr />
			<form class="form-horizontal" id="form-horizontal" action="${pageContext.request.contextPath}/gogo"
				style="margin-top: 5px; margin-left: 150px;">

				<div class="form-group">
					<label for="addressname" class="col-sm-1 control-label">收货人</label>
					<div class="col-sm-5 form-inline row">
						<input name="name" type="text" class="form-control" id="addressname"
							   placeholder="请输收货人" required><span style="color: #5FB878" class="errorMsg1">*输入收货人名称</span>
					</div>
				</div>

				<div class="form-group">
					<label for="address" class="col-sm-1 control-label">收货地址</label>
					<div class="col-sm-5 form-inline row">
						<input name="address" type="text" class="form-control" id="address"
							   placeholder="请输入收货地址" required><span style="color: #5FB878" class="errorMsg2"> *输入收货地址</span>
					</div>
				</div>

				<div class="form-group">
					<label for="telephone" class="col-sm-1 control-label">电话</label>
					<div  class="col-sm-5 form-inline row ">
						<input name="telephone" type="text" class="form-control" id="telephone"
							   placeholder="请输入联系方式" required><span style="color: #5FB878" class="errorMsg3">*输入联系电话</span>
					</div>
				</div>
			</form>

			<hr />

			<div style="margin-top: 5px; margin-left: 150px;">
				<strong>选择付款方式</strong>
				<p>
					<br /> <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"
						checked="checked" />微信 <img style="width: 10%" src="./bank_img/p1.png"
						align="middle" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
						name="pd_FrpId" value="BOC-NET-B2C" /> 支付宝<img
						src="./bank_img/p2.png" align="middle" style="width: 10%" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />银联 <img
						src="./bank_img/p3.png" align="middle"  style="width: 10%"/> <br /> <br />
				<hr />
				<p style="text-align: right; margin-right: 100px;">
					<a id="submit" href="javascript:document.getElementById('form-horizontal').submit();">
						<img src="./images/finalbutton.gif" width="204" height="51"
						border="0" />
					</a>
				</p>
				<hr />

			</div>
		</div>

	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

	<c:if test="${not empty shopMsg}">
		<script type="text/javascript" language="javascript">
			{
				alert("${shopMsg}");
			}
		</script>
	</c:if>

</body>

</html>