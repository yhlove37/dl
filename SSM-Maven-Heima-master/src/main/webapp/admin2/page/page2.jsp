
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>表单1</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../css/index.css">
</head>

<body>
    <div class="layui-fluid">
        <div class="layui-row">
            <div class="layui-collapse" lay-filter="test">
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">搜索</h2>
                    <div class="layui-colla-content">
                        <form class="layui-form">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label" style="width: 50px;">ID</label>
                                    <div class="layui-input-inline">
                                        <input type="id" name="id" placeholder="请输入ID" autocomplete="off"
                                            class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label" style="width: 50px;">用户名</label>
                                    <div class="layui-input-inline">
                                        <input type="name" name="name" placeholder="请输入用户名" autocomplete="off"
                                            class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label" style="width: 50px;">价格</label>
                                    <div class="layui-input-inline">
                                        <input type="price" name="price" placeholder="请输入价格" autocomplete="off"
                                            class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-inline" style="float: right;text-align: right;">
                                    <button class="layui-btn" lay-submit lay-filter="formDemo">搜索</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-row">
            <table id="demo" lay-filter="test"></table>
        </div>
    </div>


    <h2>33333333</h2>

   

    
    
  

</body>
<script src="../layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['layer', 'element', 'table', 'jquery', 'form', 'upload'], function () {
        var layer = layui.layer;
        var element = layui.element;
        var table = layui.table;
        var $ = layui.$;
        var form = layui.form;
        var upload = layui.upload;
      

        
     
        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData': // 复制按钮
                    var data = checkStatus.data;
                    if (data == "") {
                        layer.msg("请选择1个");
                    } else {
                        // layer.alert(JSON.stringify(data));   
                        layer.open({
                            title: ['复制', 'font-size:18px;'],
                            area: '500px',
                            type: 1,
                            content: $('#tk'), //数组第二项即吸附元素选择器或者DOM
                            success: function (layero, index) {
                                fz(data[0], "复制")
                            }
                        });
                    }
                    break;
                case 'getCheckadd':  // 新增按钮
                    // layer.alert("新增");
                    layer.open({
                        title: ['新增', 'font-size:18px;'],
                        area: '500px',
                        type: 1,
                        // btn: ['确认', '取消',],
                        // btnAlign: 'c',
                        content: $('#tk'), //数组第二项即吸附元素选择器或者DOM
                        success: function (layero, index) {
                            // console.log(layero, index);
                            fz("")
                        }
                    });
                    break;
            };
        });
      
        

        


    });


</script>

</html>