<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	
<script type="text/javascript">
	function addCart() {
		//获得购买的商品的数量
		var buyNum = $("#buyNum").val();
		location.href = "${pageContext.request.contextPath}/addProductToCart?pid=${product.pid}&buyNum="+buyNum;
		
    }
</script>	
</head>

<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div
				style="border: 1px solid #e4e4e4; width: 100%; margin-bottom: 10px; margin: 0 auto; padding: 10px; margin-bottom: 10px;">
				<a href="./index.htm">首页&nbsp;&nbsp;&gt;</a> <a href="#">&nbsp;&nbsp;&gt;</a>
				<a>商品详情页</a>
			</div>

			<div style="margin: 0 auto; width: 100%;">
				<div class="col-md-6">
					<img style="opacity: 1; width: 70%; height: 70%;" title=""
						class="medium"
						src="${pageContext.request.contextPath}/${product.pimage}">
				</div>

				<div class="col-md-6">
					<div>
						<strong>${product.pname}</strong>
					</div>
					<div
						style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
						<div>编号：${product.pid}</div>
					</div>

					<div style="margin: 10px 0 10px 0;">
						亿家价: <strong style="color: #ef0101;">￥：${product.shop_price}元/份</strong> 参 考 价：
						<del>￥${product.market_price}元/份</del>
					</div>

					<div style="margin: 10px 0 10px 0;">
						促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)"
							style="background-color: #f07373;">限时抢购</a>
					</div>

					<div
						style="padding: 10px; border: 1px solid #e7dbb1; width: 330px; margin: 15px 0 10px 0;; background-color: #fffee6;">
						<div style="margin: 5px 0 10px 0;">白色</div>

						<div
							style="border-bottom: 1px solid #faeac7; margin-top: 20px; padding-left: 10px;">
							购买数量: <input id="buyNum" name="buyNum" value="1"
								maxlength="4" size="10" type="text">
						</div>

						<div style="margin: 20px 0 10px 0;; text-align: center;">
							<a href="javascript:void(0);" onclick="addCart()">
								<input
								style="background: url('./images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0); height: 36px; width: 127px;"
								value="加入购物车" type="button">
							</a> &nbsp;收藏商品
						</div>
					</div>



					<div>
						<a id="css" href="${pageContext.request.contextPath}/productListByCid?cid=${cid}&currentPage=${currentPage}&sy=${sy}">返回列表页面</a>
					</div>

					<script>

						var $_GET = (function(){
							var url = window.document.location.href.toString(); //获取的完整url
							var u = url.split("?");
							if(typeof(u[1]) == "string"){
								u = u[1].split("&");
								var get = {};
								for(var i in u){
									var j = u[i].split("=");
									get[j[0]] = j[1];
								}
								return get;
							} else {
								return {};
							}
						})();

						var sex= $_GET['sy'];
						var  css=document.getElementById('css')
						if (sex=="sy"){
							css.style.display="none"
						}

					</script>
				</div>
			</div>
			<div class="clear"></div>
			<div style="width: 100%; margin: 0 auto;">
				<div
					style="background-color: #d3d3d3; width: 100%; padding: 10px 10px; margin: 10px 0 10px 0;">
					<strong>商品介绍</strong>
				</div>

				<div>
					<h3>图片介绍</h3>

					<img
						src="${pageContext.request.contextPath}/${product.pimage}" style=" float:left;width: 50%;height: 50%">

					<div  style="height: 400px;float:left;padding: 40px">
					<span style="vertical-align:top;" ><h4 style="color: #00FF00"> 产品品牌：</h4>  <span>${product.pdesc}</span></span>
					<p style="vertical-align:top;" ><h4 style="color: #00FF00"> 产品详情：</h4><span>暂无详情（后续更新）</span></p>
					</div>
				</div>


				<div style="margin-top: 10px; width: 100%;">
					<table class="table table-bordered">
						<tbody>
							<tr class="active">
								<th colspan="2">基本参数</th>
							</tr>
							<tr>
								<th width="10%">级别</th>
								<td width="30%">标准</td>
							</tr>
							<tr>
								<th width="10%">标重</th>
								<td>500</td>
							</tr>
							<tr>
								<th width="10%">浮动</th>
								<td>200</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div style="background-color: #d3d3d3; width: 100%;">
					<table class="table table-bordered">
						<tbody>
							<tr class="active">
								<th><strong>商品评论</strong></th>
							</tr>
							<tr class="warning">
								<th>暂无商品评论信息 <a>[发表商品评论]</a></th>
							</tr>
						</tbody>
					</table>
				</div>

				<div style="background-color: #d3d3d3; width: 100%;">
					<table class="table table-bordered">
						<tbody>
							<tr class="active">
								<th><strong>商品咨询</strong></th>
							</tr>
							<tr class="warning">
								<th>暂无商品咨询信息 <a>[发表商品咨询]</a></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>


	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>