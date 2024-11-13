package org.example.controller;

import org.example.entity.User;
import org.example.entity.uuser;
import org.example.service.uuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class uuserController {

    @Autowired
    private uuserService uuserService;

    @RequestMapping("/uuserinfo")
    public String uuserMain(HttpSession session, Model model) {
        // 从 session 中获取当前登录的 User 对象
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser != null) {
            // 使用用户名（或ID）从 uuser 表中查询详细信息
            uuser uuserDetails = uuserService.findByuName(loggedInUser.getName());
            model.addAttribute("uuser", uuserDetails);  // 将用户信息添加到 Model 中
            return "uuserInformation"; // 跳转到显示详细信息的页面
        } else {
            // 如果未登录，重定向到登录页面
            return "redirect:/login";
        }
    }
}
