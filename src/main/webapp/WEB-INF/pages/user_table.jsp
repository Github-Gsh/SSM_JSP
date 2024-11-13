<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<table border="1" style="width: 96%; margin: 0 auto; text-align: center; border-collapse: collapse; font-size: 16px;">
    <tr>
        <th class="width-40">编号</th>
        <th>用户名</th>
        <th class="width-80">密码</th>
        <th class="width-80">操作</th>
    </tr>

    <!-- 显示用户列表 -->
    <c:forEach items="${list}" var="user" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${user.name}</td>
            <td>${user.password}</td>
            <td>
                <button onclick="deleteUser(${user.id})">删除</button>
                &nbsp;|&nbsp;
                <button type="button" onclick="openUserEditModal(${user.id}, '${user.name}', '${user.password}')">修改</button>
            </td>
        </tr>
    </c:forEach>
</table>
