
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
<form action="${pageContext.request.contextPath}/uploadHeadshot" method="post" enctype="multipart/form-data">
    <label for="headshot">上传头像:</label>
    <input type="file" id="headshot" name="headshot">
    <button type="submit">上传</button>
</form>
<img src="data:image/jpeg;base64,${headshot}" alt="用户头像" />


</body>
</html>
