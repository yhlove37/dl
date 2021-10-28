<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="css/style.css" type="text/css"/>

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
            location.href = "${pageContext.request.contextPath}/addProductToCart?pid=${product.pid}&buyNum=" + buyNum;

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
                    <c:if test="${currentPage ==0}">
                        <a id="css"
                           href="${pageContext.request.contextPath}/index">返回首页</a>
                    </c:if>
                    <c:if test="${currentPage !=0}">
                    <a id="css"
                       href="${pageContext.request.contextPath}/productListByCid?cid=${cid}&currentPage=${currentPage}">返回列表页面</a>
                    </c:if>
                </div>

                <script>

                    var $_GET = (function () {
                        var url = window.document.location.href.toString(); //获取的完整url
                        var u = url.split("?");
                        if (typeof (u[1]) == "string") {
                            u = u[1].split("&");
                            var get = {};
                            for (var i in u) {
                                var j = u[i].split("=");
                                get[j[0]] = j[1];
                            }
                            return get;
                        } else {
                            return {};
                        }
                    })();

                    var sex = $_GET['sy'];
                    var css = document.getElementById('css')
                    if (sex == "sy") {
                        css.style.display = "none"
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
                        src="${pageContext.request.contextPath}/${product.pimage}"
                        style=" float:left;width: 50%;height: 50%">

                <div style="height: 400px;float:left;padding: 40px">
                    <span style="vertical-align:top;"><h4
                            style="color: #00FF00"> 产品品牌：</h4>  <span>${product.pdesc}</span></span>
                    <p style="vertical-align:top;"><h4 style="color: #00FF00"> 产品详情：</h4><span>暂无详情（后续更新）</span></p>
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

            <%--                显示评论信息--%>

            <div style="background-color: #d3d3d3; width: 100%;">
                <table class="table table-bordered">
                    <tbody>
                    <tr class="active">
                        <th><strong>商品评论</strong></th>
                    </tr>

                    <c:if test="${empty replies}">
                        <tr class="warning">
                            <th>暂无商品评论信息</th>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
                <c:if test="${!empty replies}">
                    <tr class="warning">
                        <ul class="list-group" style="width: 100%">
                                <%--遍历并显示回复--%>
                            <c:forEach var="reply" items="${replies}">
                                <li class="list-group-item">
                                    <div style="height: auto; ">
                                        <div>
                                            <a href="getUserInfo.do?userId=${reply.user.uid}" style="color: black">
                                                <strong>
                                                        <%--显示发表回复的用户昵称--%>
                                                        ${reply.user.name}
                                                </strong>
                                                    <%--展示用户权限--%>
                                                <c:choose>
                                                    <c:when test="${reply.user.state == 3}"> <span
                                                            class="bg-success">管理员</span></c:when>
                                                    <c:when test="${reply.user.state == 1}"> <span
                                                            class="bg-warning">普通用户</span></c:when>
                                                    <c:otherwise><span class="bg-primary">游客</span></c:otherwise>
                                                </c:choose>
                                            </a>
                                                <%--发表回复的用户如果是楼主则显示楼主标签--%>
                                                <%--										<c:if test="${reply.user.uid == tip.user_id}"><span--%>
                                                <%--												class="bg-info">楼主</span>--%>
                                                <%--										</c:if>--%>
                                            &nbsp;
                                            <small class="text-muted">发表于：
                                                    <%--显示回复发表的时间--%>
                                                    <%--											<fmt:formatDate value="${reply.reply_createTime}" />--%>
                                                <span>${reply.reply_createTime}</span>
                                            </small>
                                        </div>
                                        <div style="height:auto; overflow:auto; word-wrap:break-word;">
                                                <%--这里显示回复的正文--%>
                                            <pre>${reply.reply_content}</pre>

                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </tr>

                    &nbsp;
                </c:if>
                <div class=" form-group">
                    <%--非删除或结贴的贴子才能回复--%>
                    <c:choose>
                        <c:when test="${not empty user}">
                            <c:choose>
                                <c:when test="${user.state == 0}">
                                    <%--被锁定的用户不能回贴--%>
                                    <p style="color: red">游客，不能发表评论，请先登录。</p>
                                </c:when>
                                <c:otherwise>
                                    <%--这里是发表回复的表单--%>
                                    <form action="${pageContext.request.contextPath}/commentDo" method="post" id="myReplyForm">
                                        <input type="hidden" name="productId" value="${productId}">
                                            <%--这里显示输入回复内容的文本框--%>
                                        <input type="hidden" name="cid" value="${cid}">
                                        <input type="hidden" name="currentPage" value="${currentPage}">
                                        <textarea class="form-control" rows="3" name="reply_content"
                                                  id="reply_content" required>
                                                    </textarea>
                                        <br/>
                                        <input type="button" class="btn btn-success btn-sm"
                                               value="发表评论" onclick="publishReply_confirm()"/>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <%--如果用户没有登录则取消文本框--%>
                            <input type="button" class="btn btn-warning btn-sm"
                                   value="请先登录:"
                                   onclick="window.location.href='${pageContext.request.contextPath}/login.jsp'"/>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>


        </div>


    </div>
</div>


<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

<script>
    function publishReply_confirm() {
        var form = document.getElementById("myReplyForm"); // 由id获取表单
        var str=document.getElementById("reply_content").value.replace(/(^[\s\t\xa0\u3000]+)|([\u3000\xa0\s\t]+$)/g, "");
        if (str =="") {
            alert("评论内容不能为空！");
            return false;
        } else {
            var r = confirm("确定提交评论?")
            if (r == true) {
                form.submit(); // 提交表单
            }
        return

        }
    }
</script>

</body>

</html>