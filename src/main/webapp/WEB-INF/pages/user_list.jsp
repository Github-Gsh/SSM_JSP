<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <title>用户列表</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style>
        body { font-family: "微软雅黑"; background-color: #EDEDED; }
        h2 { text-align: center; }
        #add-user { text-align: center; font-size: 20px; }
    </style>
    <script>
        $(document).ready(function() {
            // 查询用户的AJAX逻辑（保持不变）
            $('#userSearchForm').on('submit', function(event) {
                event.preventDefault();

                $.ajax({
                    url: 'userSearch',
                    type: 'GET',
                    data: $(this).serialize(),
                    success: function(response) {
                        $('#user-list').html(response); // 插入查询结果到用户列表
                    },
                    error: function() {
                        alert('查询失败，请重试！');
                    }
                });
            });

            // 提交新增或修改用户表单的AJAX逻辑
            $('#userAddForm').on('submit', function(event) {
                event.preventDefault();

                $.ajax({
                    url: $(this).attr('action'),
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function(response) {
                        // 关闭模态框
                        $('#userAddModal').modal('hide');
                        // 更新用户列表
                        $('#user-list').html(response);
                    },
                    error: function() {
                        alert('保存失败，请重试！');
                    }
                });
            });
        });

        // 使用 AJAX 删除用户
        function deleteUser(userId) {
            if (!confirm("确定要删除此用户吗？")) return; // 删除确认
            $.ajax({
                url: 'userDelete',
                type: 'POST',
                data: { id: userId },
                success: function(response) {
                    $('#user-list').html(response); // 更新用户列表区域
                },
                error: function() {
                    alert('删除失败，请重试！');
                }
            });
        }

        // 打开新增用户模态框
        function openUserAddModal() {
            $('#userAddModal').modal('show');
            $('#userAddForm')[0].reset();
            $('#userAddForm').attr('action', 'userAdd');
            $('#userAddModalLabel').text('新增用户');
        }

        // 打开修改用户模态框并填充数据
        function openUserEditModal(userId, userName, userPassword) {
            $('#userAddModal').modal('show');
            $('#userAddForm')[0].reset();
            $('#userAddForm').attr('action', 'userUpdate');
            $('#userAddModalLabel').text('修改用户');

            $('#name').val(userName);
            $('#password').val(userPassword);
            $('#userId').val(userId);
        }
    </script>

</head>
<body>
<h2>用户列表</h2>
<div id="add-user">
    <button type="button" onclick="openUserAddModal()">新增用户</button>
</div>

<!-- 查询用户表单 -->
<div id="search-user" style="text-align:center; margin-bottom: 20px;">
    <form id="userSearchForm">
        <input type="text" name="name" placeholder="请输入用户名">
        <button type="submit">查询</button>
    </form>
</div>

<hr/>

<!-- 用户列表显示区域 -->
<div id="user-list">
    <!-- 初始页面加载时显示用户列表 -->
    <jsp:include page="user_table.jsp" />
</div>

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
                <form id="userAddForm" action="userAdd" method="post">
                    <input type="hidden" id="userId" name="id">
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
</body>
</html>
