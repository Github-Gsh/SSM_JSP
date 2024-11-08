<%@ page pageEncoding="utf-8"%>
<%-- 引入JSTL标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- 引入 jQuery 和 Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
    <script>
        // 打开新增用户模态框
        function openUserAddModal() {
            $('#userAddModal').modal('show');
            $('#userAddForm')[0].reset();  // 清空表单
            $('#userAddForm').attr('action', 'userAdd');  // 设置新增用户的提交路径
            $('#userAddModalLabel').text('新增用户');  // 修改标题
        }

        // 打开修改用户模态框并填充数据
        function openUserEditModal(userId, userName, userPassword) {
            $('#userAddModal').modal('show');
            $('#userAddForm')[0].reset();  // 清空表单
            $('#userAddForm').attr('action', 'userUpdate');  // 设置更新用户的提交路径
            $('#userAddModalLabel').text('修改用户');  // 修改标题

            // 填充用户信息到表单
            $('#name').val(userName);
            $('#password').val(userPassword);
            $('#userId').val(userId);  // 存储用户ID以便后端识别
        }
    </script>
</head>
<body><!-- body-start  -->
<h2>用户列表</h2>
<div id="add-user">
    <button type="button" onclick="openUserAddModal()">新增用户</button>
</div>

<div id="search-user" style="text-align:center; margin-bottom: 20px;">
    <form id="userSearchForm" action="userSearch" method="get">
        <input type="text" name="name" placeholder="请输入用户名">
        <button type="submit">查询</button>
    </form>
</div>


<hr/>
<table border="1">
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
                <a href="userDelete?id=${user.id}">删除</a>
                &nbsp;|&nbsp;
                <button type="button" onclick="openUserEditModal(${user.id}, '${user.name}', '${user.password}')">修改</button>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- 修改/新增用户模态框 -->
<div class="modal fade" id="userAddModal" tabindex="-1" role="dialog" aria-labelledby="userAddModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="userAddModalLabel">新增用户</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- 新增/修改用户表单 -->
                <form id="userAddForm" action="userAdd" method="post">
                    <input type="hidden" id="userId" name="id">  <!-- 隐藏的用户ID -->
                    <label for="name">用户名:</label>
                    <input type="text" id="name" name="name" required>

                    <label for="password">密码:</label>
                    <input type="text" id="password" name="password" required>

                    <button type="submit">保存</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body><!-- body-end  -->
</html>
