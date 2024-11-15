<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>基础资料</title>
</head>
<body>

<h1>欢迎, ${uuser.name}</h1>
<p>年龄: ${uuser.age}</p>
<p>性别: ${uuser.sex}</p>
<p>电话号码: ${uuser.phone}</p>
<p>地址: ${uuser.address}</p>
<p>邮箱: ${uuser.mail}</p>
<p>项目: ${uuser.project}</p>
<p>组织: ${uuser.organization}</p>

<!-- 显示当前头像 -->
<%--<c:if test="${not empty uuser.headshot}">--%>
<%--    <img src="${uuser.headshot}" alt="头像" width="100" height="100">--%>
<%--</c:if>--%>

<!-- 上传头像表单 -->
<form action="/uploadHeadshot" method="post" enctype="multipart/form-data">
    <input type="file" name="headshot" />
    <button type="submit">上传头像</button>
</form>

<img src="${uuser.headshot}" alt="头像" />

</body>
</html>
