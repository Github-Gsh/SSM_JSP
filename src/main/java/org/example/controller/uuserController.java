package org.example.controller;

import org.example.entity.User;
import org.example.entity.uuser;
import org.example.service.uuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

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

            // 将头像转换为 Base64 字符串
            if (uuserDetails.getHeadshot() != null) {
                String base64Headshot = Base64.getEncoder().encodeToString(uuserDetails.getHeadshot());
                model.addAttribute("headshot", base64Headshot);
            } else {
                model.addAttribute("headshot", ""); // 头像为空时，设置默认值
            }

            model.addAttribute("uuser", uuserDetails); // 将用户信息添加到 Model 中
            session.setAttribute("uuser", uuserDetails); // 将 uuser 对象存入 session

            return "uuserInformation"; // 跳转到显示详细信息的页面
        } else {
            // 如果未登录，重定向到登录页面
            return "redirect:/login";
        }
    }

    @RequestMapping("/uploadHeadshot")
    public String uploadHeadshot(@RequestParam("headshot") MultipartFile file, HttpSession session, Model model) {
        // 从 session 中获取当前用户信息
        uuser loggedInUser = (uuser) session.getAttribute("uuser");

        if (loggedInUser != null && !file.isEmpty()) {
            try {
                // 获取文件的字节数据并存储
                byte[] headshotBytes = file.getBytes();
                loggedInUser.setHeadshot(headshotBytes);
                uuserService.updateHeadshot(loggedInUser); // 更新数据库中的头像信息

                model.addAttribute("message", "头像上传成功");
            } catch (IOException e) {
                model.addAttribute("message", "头像上传失败：" + e.getMessage());
            }
        } else {
            model.addAttribute("message", "用户未登录或文件为空");
        }

        // 使用 forward 而不是 redirect，保持在同一个请求内
        return "forward:/uuserinfo";
    }



}
