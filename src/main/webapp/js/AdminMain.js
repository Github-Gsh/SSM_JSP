$(document).ready(function() {
    // 激活菜单项的样式
    $(".siderbar_menu li").click(function() {
        $(".siderbar_menu li").removeClass("active");
        $(this).addClass("active");

        // 判断是否点击了“主页”菜单项
        if ($(this).find(".title").text() === "主页") {
            location.reload(); // 刷新页面
        }
    });

    $(".accordion li a").click(function(e) {
        e.preventDefault(); // 阻止默认链接跳转
        $(".accordion li a").removeClass("active");
        $(this).addClass("active");

        const url = $(this).attr("data-url"); // 获取自定义的 data-url 属性值
        if (url) {
            // 发送Ajax请求来加载内容
            $.ajax({
                url: url,
                method: "GET",
                success: function(data) {
                    $("#content").html(data); // 将返回的内容填充到内容区域
                },
                error: function() {
                    alert("内容加载失败，请稍后重试！");
                }
            });
        }
    });

    // 显示和隐藏侧边栏
    $(".hamburger").click(function() {
        $(".wrapper").addClass("active");
    });

    $(".bg_shadow, .close").click(function() {
        $(".wrapper").removeClass("active");
    });
});
