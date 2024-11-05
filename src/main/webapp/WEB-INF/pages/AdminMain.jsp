<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/AdminMain.css">
    <!--这里引入的是矢量图标签库-->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <!-- https://fontawesome.com/v5/icons/times?s=solid -->
</head>

<body>
<div class="wrapper">
    <div class="sidebar">
        <div class="bg_shadow"></div>
        <div class="sidebar_inner">
            <div class="close">
                <i class="fas fa-times"></i>
            </div>
            <div class="profile_info">
                <div class="profile_img">
                    <img src="images/logo_logo.png" alt="">
                </div>
                <div class="profile_data">
                    <p class="name">${sessionScope.user.name}</p>
                    <span>
                        <i class="fas fa-map-marker-alt"></i>
                        欢迎进入该系统
                    </span>
                </div>
            </div>

            <ul class="siderbar_menu">
                <li class="active">
                    <a href="#" data-url="index.jsp">
                        <div class="icon"><i class="fas fa-heart"></i></div>
                        <div class="title">主页</div>
                    </a>
                </li>
                <li>
                    <a href="#" data-url="/userList">
                        <div class="icon"><i class="fas fa-user"></i></div>
                        <div class="title">用户详细信息</div>
                        <div class="arrow"><i class="fas fa-chevron-down"></i></div>
                    </a>
                    <ul class="accordion">
                        <li><a href="#" data-url="/userList">全部用户</a></li>
                        <li><a href="#" data-url="user/addUser">增加用户</a></li>
                        <li><a href="#" data-url="user/sub3">子菜单3</a></li>
                        <li><a href="#" data-url="user/sub4">子菜单4</a></li>
                        <li><a href="#" data-url="user/sub5">子菜单5</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" data-url="submission/info">
                        <div class="icon"><i class="fas fa-receipt"></i></div>
                        <div class="title">投稿信息</div>
                        <div class="arrow"><i class="fas fa-chevron-down"></i></div>
                    </a>
                    <ul class="accordion">
                        <li><a href="#" data-url="submission/sub1">子菜单1</a></li>
                        <li><a href="#" data-url="submission/sub2">子菜单2</a></li>
                        <li><a href="#" data-url="submission/sub3">子菜单3</a></li>
                        <li><a href="#" data-url="submission/sub4">子菜单4</a></li>
                        <li><a href="#" data-url="submission/sub5">子菜单5</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" data-url="position/info">
                        <div class="icon"><i class="fas fa-credit-card"></i></div>
                        <div class="title">占个位置</div>
                        <div class="arrow"><i class="fas fa-chevron-down"></i></div>
                    </a>
                    <ul class="accordion">
                        <li><a href="#" data-url="position/sub1">子菜单1</a></li>
                        <li><a href="#" data-url="position/sub2">子菜单2</a></li>
                        <li><a href="#" data-url="position/sub3">子菜单3</a></li>
                        <li><a href="#" data-url="position/sub4">子菜单4</a></li>
                        <li><a href="#" data-url="position/sub5">子菜单5</a></li>
                        <li><a href="#" data-url="position/sub6">子菜单6</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" data-url="other/info">
                        <div class="icon"><i class="fas fa-unlock"></i></div>
                        <div class="title">占个位置</div>
                    </a>
                </li>
            </ul>
            <div class="logout_btn">
                <a href="${pageContext.request.contextPath}/logout">退出登陆</a>
            </div>

        </div>
    </div>
    <div class="main_container">
        <div class="navbar">
            <div class="hamburger">
                <i class="fas fa-bars"></i>
            </div>
            <div class="logo">
                <a href="index.jsp">主页logo</a>
            </div>
        </div>
        <div class="content" id="content">
            <div class="item">
                这是一个测试文字？？？？？？？？？
            </div>
        </div>
    </div>
</div>
<script src="js/AdminMain.js"></script>
</body>

</html>