<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>项目列表</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
<h1>项目列表</h1>
<!-- 上传项目文件的表单 -->
<form id="uploadProjectForm" enctype="multipart/form-data">
    <input type="file" name="projectFile" id="projectFile" />
    <button type="submit">上传项目文件</button>
</form>
<!-- 显示项目文件的下载链接 -->
<c:if test="${not empty project.project}">
    <p>项目文件: <a href="/downloadProject?fileUrl=${project.project}" download>点击下载</a></p>
</c:if>
<!-- 判断是否有项目数据 -->
<c:if test="${empty projects}">
    <p>当前没有任何项目数据。</p>
</c:if>

<!-- 显示项目表 -->
<c:if test="${not empty projects}">
    <table>
        <thead>
        <tr>
            <th>项目内容</th>
            <th>得分</th>
            <th>提交人</th>
            <th>审批人</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <!-- 遍历项目数据 -->
        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${fn:substringAfter(project.project, '/upload/')}</td>
                <td>${project.scores}</td>
                <td>${project.suser}</td>
                <td>${project.ouser}</td>
                <td>${project.state}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<script type="text/javascript">
    //项目文件
    $(document).ready(function() {
        // 处理表单提交
        $("#uploadProjectForm").on("submit", function(event) {
            event.preventDefault(); // 防止表单默认提交

            var formData = new FormData(this); // 获取表单数据

            $.ajax({
                url: "/uploadProject",  // 处理上传的URL
                type: "POST",
                data: formData,
                processData: false,  // 不处理数据
                contentType: false,  // 不设置Content-Type
                success: function(response) {
                    // 上传成功后，更新文件下载链接
                    if(response.uploadSuccess) {
                        $("#message").text("项目文件上传成功");
                        // 更新项目文件链接
                        $("p:contains('项目文件')").html('项目文件: <a href="' + response.newProject + '" download>点击下载</a>');
                    } else {
                        $("#message").text("文件上传失败：" + response.message);
                    }
                },
                error: function(xhr, status, error) {
                    $("#message").text("上传失败：" + error);
                }
            });
        });
    });
</script>
</body>
</html>
