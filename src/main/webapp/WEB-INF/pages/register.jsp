<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<html>
<head>
    <title>regist</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- jquery.js -->
    <script src="jquery.min.js"></script>
</head>

<body>
<div id="content">
    <form action="${pageContext.request.contextPath}/user/register" method="post">
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
            <input type="submit"  value="注册" />
        </p>
    </form>
</div>
</body>
</html>
