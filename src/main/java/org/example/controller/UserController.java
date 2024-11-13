package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // 显示用户列表
    @RequestMapping("/userList")
    public String userList(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("list", list);
        return "user_list";
    }

    // 新增用户，并返回更新后的用户表
    @RequestMapping("/userAdd")
    public String userAdd(User user, Model model) {
        userService.addUser(user);
        List<User> updatedList = userService.findAll();
        model.addAttribute("list", updatedList);
        return "user_table"; // 返回用户表格部分视图
    }

    // 删除用户，并返回更新后的用户表
    @RequestMapping(value = "/userDelete", method = RequestMethod.POST)
    public String userDelete(@RequestParam("id") Integer id, Model model) {
        userService.deleteById(id);
        List<User> updatedList = userService.findAll();
        model.addAttribute("list", updatedList);
        return "user_table"; // 返回用户表格部分视图
    }


    // 查询用户
    @RequestMapping("/userSearch")
    public String searchUser(@RequestParam("name") String name, Model model) {
        List<User> users = userService.searchUser(name);
        model.addAttribute("list", users);
        return "user_table"; // 返回用户表格部分视图
    }

    // 更新用户，并返回更新后的用户表
    @RequestMapping("/userUpdate")
    public String userUpdate(User user, Model model) {
        userService.updateById(user);
        List<User> updatedList = userService.findAll();
        model.addAttribute("list", updatedList);
        return "user_table"; // 返回用户表格部分视图
    }

    // 用于动态页面加载
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;
    }
}
