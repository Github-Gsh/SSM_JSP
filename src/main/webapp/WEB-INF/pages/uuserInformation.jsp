<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>基础资料</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
<c:if test="${not empty uuser.headshot}">
    <img id="user-avatar" src="${uuser.headshot}" alt="头像" width="100" height="100">
</c:if>
<c:if test="${empty uuser.headshot}">
    <img id="user-avatar" src="/images/default-avatar.png" alt="头像" width="100" height="100"> <!-- 默认头像 -->
</c:if>

<!-- 上传头像的表单 -->
<form id="uploadForm" enctype="multipart/form-data">
    <input type="file" name="headshot" id="headshot" />
    <button type="submit">上传头像</button>
</form>

<div id="message"></div>

<script type="text/javascript">
    $(document).ready(function() {
        // 处理表单提交
        $("#uploadForm").on("submit", function(event) {
            event.preventDefault(); // 防止表单默认提交

            var formData = new FormData(this); // 获取表单数据

            $.ajax({
                url: "/uploadHeadshot",  // 处理上传的URL
                type: "POST",
                data: formData,
                processData: false,  // 不处理数据
                contentType: false,  // 不设置Content-Type
                success: function(response) {
                    // 上传成功后，更新头像显示
                    if(response.uploadSuccess) {
                        $("#user-avatar").attr("src", response.newHeadshot); // 更新头像
                        $("#message").text("头像上传成功");
                    } else {
                        $("#message").text("头像上传失败：" + response.message);
                    }
                },
                error: function(xhr, status, error) {
                    $("#message").text("上传失败：" + error);
                }
            });
        });
    });
</script>

<c:if test="${param.uploadSuccess == 'true'}">
    <p>头像上传成功！</p>
</c:if>

</body>
</html>
