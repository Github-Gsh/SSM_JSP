<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- jquery.js -->
    <script src="jquery.min.js"></script>
</head>

<body>
<div id="content">
    <form action="${path}/user/login" method="post">
        <table>
            <tr>
                <td valign="middle" align="right">
                    用户名:
                </td>
                <td valign="middle" align="left">
                    <input type="text" class="inputgri" name="name" />
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">
                    密码:
                </td>
                <td valign="middle" align="left">
                    <input type="password" class="inputgri" name="password" />
                </td>
            </tr>
        </table>
        <p>
            <input type="submit" class="button" value="登录" />
            <input type="button" class="button" value="注册" onclick="window.location.href='${path}/register'" />

        </p>
    </form>
</div>
</body>
</html>
