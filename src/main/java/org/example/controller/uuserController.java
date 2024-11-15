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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class uuserController {

    @Autowired
    private uuserService uuserService;

    // 显示用户基本信息，包括头像
    @RequestMapping("/uuserinfo")
    public String uuserMain(HttpSession session, Model model) {
        // 从 session 中获取当前登录的 User 对象
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser != null) {
            // 使用用户名（或ID）从 uuser 表中查询详细信息
            uuser uuserDetails = uuserService.findByuName(loggedInUser.getName());

            // 将用户信息添加到 Model 中
            model.addAttribute("uuser", uuserDetails);
            session.setAttribute("uuser", uuserDetails); // 将 uuser 对象存入 session

            return "uuserInformation"; // 跳转到显示详细信息的页面
        } else {
            // 如果未登录，重定向到登录页面
            return "redirect:/login";
        }
    }

    // 上传头像
    @RequestMapping("/uploadHeadshot")
    public String uploadHeadshot(@RequestParam("headshot") MultipartFile file, HttpSession session, Model model, HttpServletRequest request) {
        // 从 session 中获取当前用户信息
        uuser loggedInUser = (uuser) session.getAttribute("uuser");

        if (loggedInUser != null && !file.isEmpty()) {
            try {
                // 获取文件的原始文件名
                String originalFilename = file.getOriginalFilename();

                // 获取项目路径，将文件保存到 upload 文件夹中
                String uploadDir = request.getSession().getServletContext().getRealPath("/upload");

                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();  // 如果目录不存在，则创建
                }

                String filePath = uploadDir + "/" + originalFilename;

                // 保存文件
                file.transferTo(new File(filePath));

                // 更新用户头像的路径（存储为访问路径）
                String fileUrl = "/upload/" + originalFilename; // 存储相对路径
                loggedInUser.setHeadshot(fileUrl);
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
