
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<p>头像: ${uuser.headshot}</p>
<p>邮箱: ${uuser.mail}</p>
<p>项目: ${uuser.project}</p>
<p>组织: ${uuser.organization}</p>


</body>
</html>
