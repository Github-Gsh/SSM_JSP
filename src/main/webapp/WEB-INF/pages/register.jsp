<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>。
<html>
<head>
    <title>用户注册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="jquery.min.js"></script>
    <style>
        /* 简单样式调整 */
        body { font-family: Arial, sans-serif; }
        #content { width: 300px; margin: 0 auto; }
        table { width: 100%; }
        .inputgri { width: 100%; padding: 8px; }
        .error { color: red; font-size: 0.9em; }
    </style>
    <script>
        $(document).ready(function() {
            $("form").on("submit", function(event) {
                var valid = true;
                $(".error").remove();

                // 验证用户名不能为空
                var username = $("input[name='name']").val().trim();
                if (username === "") {
                    $("input[name='name']").after("<span class='error'>请输入用户名</span>");
                    valid = false;
                }

                // 验证密码长度
                var password = $("input[name='password']").val();
                if (password.length < 6) {
                    $("input[name='password']").after("<span class='error'>密码至少6位</span>");
                    valid = false;
                }

                if (!valid) {
                    event.preventDefault(); // 阻止表单提交
                }
            });
        });
    </script>
</head>

<body>
<div id="content">
    <h2>用户注册</h2>
    <%-- 显示错误信息 --%>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/userRegister" method="post">
        <table>
            <tr>
                <td valign="middle" align="right">用户名:</td>
                <td valign="middle" align="left">
                    <input type="text" class="inputgri" name="name" />
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">密码:</td>
                <td valign="middle" align="left">
                    <input type="password" class="inputgri" name="password" />
                </td>
            </tr>
        </table>
        <p>
            <input type="submit" value="注册" />
        </p>
    </form>
</div>
</body>
</html>
