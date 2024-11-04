<%@ page pageEncoding="utf-8"%>
<%-- 引入JSTL标签库 --%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户列表</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body{ font-family: "微软雅黑"; background-color: #EDEDED; }
        h2{ text-align: center;}
        table{ width:96%; margin: 0 auto; text-align: center; border-collapse:collapse; font-size:16px;}
        td, th{ padding: 5px;}
        th{ background-color: #DCDCDC; width:120px; }
        th.width-40{ width: 40px; }
        th.width-70{ width: 70px; }
        th.width-80{ width: 80px; }
        hr{ margin-bottom:20px; border:1px solid #aaa; }
        #add-user{text-align:center;font-size:20px;}
    </style>
</head>
<body><!-- body-start  -->
<h2>用户列表</h2>
<div id="add-user">
    <a href="user_add" target="rightFrame">新增用户</a>
</div>
<hr/>
<table border="1">
    <tr>
        <th class="width-40">编号</th>
        <th>用户名</th>
        <th class="width-80">密码</th>
        <th class="width-80">操 作</th>
    </tr>

    <!-- 模版数据 -->
    <c:forEach items="${ list }" var="user" varStatus="status">
        <tr>
            <td>${ status.count }</td>
            <td>${ user.name }</td>
            <td>${ user.password}</td>
            <td>
                <a href="userDelete?id=${ user.id }">删除</a>
                &nbsp;|&nbsp;
                <a href="userInfo?id=${ user.id }">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body><!-- body-end  -->
</html>
