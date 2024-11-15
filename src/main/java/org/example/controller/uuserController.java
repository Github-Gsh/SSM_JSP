package org.example.controller;

import org.example.entity.User;
import org.example.entity.uuser;
import org.example.service.uuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping(value = "/uploadHeadshot", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadHeadshot(@RequestParam("headshot") MultipartFile file, HttpSession session, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
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

                // 返回成功的结果，包含新头像的URL
                response.put("uploadSuccess", true);
                response.put("newHeadshot", fileUrl);
            } catch (IOException e) {
                response.put("uploadSuccess", false);
                response.put("message", "头像上传失败：" + e.getMessage());
            }
        } else {
            response.put("uploadSuccess", false);
            response.put("message", "用户未登录或文件为空");
        }

        return response;  // 返回响应
    }

    // 上传项目文件
    @RequestMapping(value = "/uploadProject", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadProject(@RequestParam("projectFile") MultipartFile file, HttpSession session, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
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

                // 更新用户项目文件的路径（存储为访问路径）
                String fileUrl = "/upload/" + originalFilename; // 存储相对路径
                loggedInUser.setProject(fileUrl); // 更新项目文件字段
                uuserService.updateProject(loggedInUser); // 更新数据库中的项目文件信息

                // 返回成功的结果，包含新项目文件的URL
                response.put("uploadSuccess", true);
                response.put("newProject", fileUrl);
            } catch (IOException e) {
                response.put("uploadSuccess", false);
                response.put("message", "项目文件上传失败：" + e.getMessage());
            }
        } else {
            response.put("uploadSuccess", false);
            response.put("message", "用户未登录或文件为空");
        }

        return response;  // 返回响应
    }

    // 下载项目文件
    @RequestMapping(value = "/downloadProject", method = RequestMethod.GET)
    @ResponseBody
    public File downloadProject(@RequestParam("fileUrl") String fileUrl, HttpServletRequest request) {
        // 获取文件的绝对路径
        String filePath = request.getSession().getServletContext().getRealPath(fileUrl);
        File file = new File(filePath);

        if (file.exists()) {
            return file; // 返回文件
        } else {
            return null; // 文件不存在
        }
    }

}
