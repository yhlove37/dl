<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" />




</HEAD>
<body>

<c:if test="${not empty mes}">
	<script type="text/javascript" language="javascript">
		{
			alert("<%=request.getAttribute("mes")%>");
		}
	</script>
</c:if>
	<br>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
		bgColor="#f5fafe" border="0">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>分类列表</strong>
				</TD>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

							<td align="center" width="10%">编号</td>
							<td align="center" width="10%">用户名</td>
							<td align="center" width="10%">用户邮箱</td>
							<td align="center" width="10%">用户电话</td>
							<td width="7%" align="center">状态</td>
							<td width="7%" align="center">删除</td>
						</tr>
						<c:forEach  var="user1" items="${users}">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">

								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="10%">${user1.username}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="10%">${user1.name}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="10%">${user1.email}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="10%">${user1.telephone}</td>
								<td align="center" style="HEIGHT: 22px">
									<c:choose>
										<c:when test="${user1.state==1}">
											<button style="background-color: red;color: white" class="but-primary" onclick="window.location.href='${pageContext.request.contextPath}/user/activebyuid?uid=${user1.uid}&&type=0'">禁用用户</button>
										</c:when>
										<c:when test="${user1.state==3}">
											<a>管理员</a>
										</c:when>
									<c:otherwise>
										<button style="background-color: #00FF00;color: white" class="but-primary" onclick="window.location.href='${pageContext.request.contextPath}/user/activebyuid?uid=${user1.uid}&&type=1'">激活用户</button>

									</c:otherwise>

									</c:choose>
								</td>
								<td align="center" style="HEIGHT: 22px">
<%--									<button class="but-primary" onclick="window.location.href='${pageContext.request.contextPath}/user/deletebyid?uid=${user1.uid}">删除当前用户</button>--%>
									<a href="${pageContext.request.contextPath}/user/deletebyid?uid=${user1.uid}">删除用户</a>
								</td>

							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>



		</TBODY>
	</table>

<script>
	<%--function jihuo(){--%>
	<%--	if(confirm("您是否要激活当前用户？")){--%>
	<%--		location.href="${pageContext.request.contextPath}/user/activebyuid?uid=${user1.uid}";--%>
	<%--	}--%>
	<%--}--%>
</script>
</body>
</HTML>

