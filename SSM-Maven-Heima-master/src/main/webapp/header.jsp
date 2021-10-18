<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<!-- 登录 注册 购物车... -->
<div class="container-fluid ">
	<div class="row">
	<div class="col-xs-4">
		<img src="img/log2.png"  style="width: 60%;height: 100%;position: relative;left: -20px"/>
	</div>
	<div class="col-xs-5">
		<img src="img/header.png" style="height: 100%;width: 80%" />
	</div>
	<div class="col-xs-3" style="padding-top:20px;">
		<ol class="list-inline" >
			<c:if test="${empty user}">
				<li><a style="font-size: 1.2vw" href="${pageContext.request.contextPath}/login.jsp" >登录</a></li>
				<li><a style="font-size: 1.2vw" href="${pageContext.request.contextPath}/register.jsp">注册</a></li>
			</c:if>
			<c:if test="${!empty user}">
				<li style="font-size: 1.2vw"  style="color:red">欢迎您，<a href="${pageContext.request.contextPath}/user/touserupdate">${user.username}</a></li>
				<li><a style="font-size: 1.2vw" href="${pageContext.request.contextPath}/user/logout">退出</a> </li>
				<c:if test="${user.state==3}">
				<li><a  style="font-size: 1.2vw" href="admin2/index.jsp">管理页面</a></li>
				</c:if>
			</c:if>
			<li><a style="font-size: 1.2vw" href="cart.jsp">购物车</a></li>
			<li><a  style="font-size: 1.2vw" href="${pageContext.request.contextPath}/myOrders">我的订单</a></li>
		</ol>
	</div>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categoryUl">
					<%--<c:forEach items="${categoryList}" var="category">--%>
						<%--<li><a href="#">${category.cname}</a></li>--%>
					<%--</c:forEach>--%>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
		
		<script type="text/javascript">

			$(function() {
			    var content = "";
				$.post(
				    "${pageContext.request.contextPath}/categoryList",
					function (data) {
                        //[{"cid":"xxx","cname":"xxxx"},{},{}]
                        //动态创建<li><a href="#">${category.cname }</a></li>
						for (var i = 0; i < data.length; i++){
						    content += "<li><a href='${pageContext.request.contextPath}/productListByCid?cid=" + data[i].cid +"'>"+ data[i].cname + "</a></li>";
                        }
                        $("#categoryUl").html(content);
                    },
					"json"
				);
            });
		</script>

	</nav>
</div>