package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 显示登录页面
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 处理登录请求
    @RequestMapping("/user/login")
    public String login(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {
        User user = userService.findByName(name);

        if (user != null && user.getPassword().equals(password)) {
            // 登录成功，保存用户信息到 session
            session.setAttribute("user", user);
            // 根据用户的角色跳转到不同的页面
            if ("admin".equals(user.getRole())) {
                return "redirect:/AdminMain";
            } else if ("u".equals(user.getRole())) {
                return "redirect:/uuserMain";
            } else {
                model.addAttribute("error", "未知角色权限");
                return "login";
            }
        } else {
            // 登录失败，返回登录页面并显示错误信息
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }


    // 退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 清除 session 信息
        return "redirect:/login"; // 重定向到登录页面
    }

    //注册
    @RequestMapping("/userRegister")
    public String userRegister(User user, Model model) {
        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return "redirect:/login"; // 注册成功后跳转到登录页面
        } else {
            model.addAttribute("error", "注册失败，请重试");
            return "register"; // 注册失败重新返回注册页面
        }
    }


    @RequestMapping("/AdminMain")
    public String adminMain() {
        return "AdminMain";
    }

    @RequestMapping("/uuserMain")
    public String uuserMain() {
        return "uuserMain";
    }


}
