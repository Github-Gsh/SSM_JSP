package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/userList")
    public String userList(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("list", list);
        return "user_list";
    }
    @RequestMapping("/userAdd")
    public String userAdd(User user, Model model) {
        userService.addUser(user);
        return "redirect:/AdminMain";
    }
    @RequestMapping("/userDelete")
    public String userDelete(Integer id) {
        userService.deleteById( id );
        return "redirect:/AdminMain";
    }
    // 查询用户
    @RequestMapping("/userSearch")
    public String searchUser(@RequestParam("name") String name, Model model) {
        List<User> users = userService.searchUser(name);  // 调用 Service 层的 searchUser 方法
        model.addAttribute("list", users);  // 将查询结果传递给视图
        return "user_table";  // 返回包含用户表格的视图片段
    }



    @RequestMapping("/userUpdate")
    public String userUpdate(User user) {
        userService.updateById(user);
        return "redirect:/AdminMain";
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

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;
    }
}