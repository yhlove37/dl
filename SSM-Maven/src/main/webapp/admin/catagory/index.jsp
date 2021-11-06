<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link href="${pageContext.request.contextPath}/admin/catagory/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/admin/catagory/css/htmleaf-demo.css" rel="stylesheet">

    <style type="text/css">
        .box {
            width: 20px;
            height: 20px;
            padding: 2px;
            border: 1px solid #ccc;
            border-radius: 2px;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-12" style="padding:2em 0;">
                <p>点击 <i class="fa fa-pencil box"></i> 按钮可以对表格进行编辑，点击 <i class="fa fa-trash-o box"></i>按钮可以将该表格行删除。</p>
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="mytable">
                        <thead>

                        <tr>
                            <td>编号</td>
                            <td>分类名</td>
                            <td>分类信息介绍</td>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${categoryList }" var="pro" varStatus="s">
                            <c:if test="${pro.cstate == 1}">
                            <tr>
                                <td>${pro.cid}</td>
                                <td>${pro.cname}</td>
                                <td>${pro.cinfo}</td>
                            </tr>
                            </c:if>


                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-12" style="padding-bottom:2em;">
                <button class="btn btn-info" id="add"><i class="fa fa-plus"></i> 添加新的表格行</button>
            </div>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/admin/catagory/js/jquery.min.js" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="${pageContext.request.contextPath}/admin/catagory/js/jquery-1.11.0.min.js"><\/script>')</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/catagory/js/bootstable.js"></script>
    <script type="text/javascript">
        $('#mytable').SetEditable({
            $addButton: $('#add')
        });
    </script>
</body>
</html>