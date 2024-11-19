package org.example.controller;

import org.example.entity.Project;
import org.example.entity.User;
import org.example.entity.uuser;
import org.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/uuserproject")
    public String uuserProject(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser != null) {
            // 使用用户名查询 project 表中的数据
            List<Project> projects = projectService.findPjByName(loggedInUser.getName());

            // 将结果添加到 Model 中
            model.addAttribute("projects", projects);
            return "project_list"; // 跳转到显示详细信息的页面
        } else {
            return "redirect:/login"; // 如果未登录，重定向到登录页面
        }
    }

    // 上传项目文件
    @RequestMapping(value = "/uploadProject", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadProject(@RequestParam("projectFile") MultipartFile file, HttpSession session, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        uuser loggedInUser = (uuser) session.getAttribute("uuser");

        if (loggedInUser != null && !file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String uploadDir = request.getSession().getServletContext().getRealPath("/upload");

                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filePath = uploadDir + "/" + originalFilename;
                file.transferTo(new File(filePath));

                String fileUrl = "/upload/" + originalFilename; // 存储相对路径
                loggedInUser.setProject(fileUrl); // 更新项目文件字段
                projectService.updateProject(loggedInUser); // 更新数据库中的项目文件信息

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
    public ResponseEntity<byte[]> downloadProject(@RequestParam("fileUrl") String fileUrl, HttpServletRequest request) {
        // 获取文件的绝对路径
        String filePath = request.getSession().getServletContext().getRealPath(fileUrl);
        File file = new File(filePath);

        if (file.exists()) {
            try {
                // 设置响应头，指示浏览器下载文件
                HttpHeaders headers = new HttpHeaders();
                String filename = URLEncoder.encode(file.getName(), "UTF-8");  // 对文件名进行编码

                // 手动设置 Content-Disposition 头
                headers.add("Content-Disposition", "attachment; filename=\"" + filename + "\"");

                // 根据文件类型设置 Content-Type
                String contentType = Files.probeContentType(file.toPath());
                if (contentType == null) {
                    contentType = "application/octet-stream"; // 默认类型
                }
                headers.setContentType(MediaType.parseMediaType(contentType));

                // 读取文件内容并返回
                byte[] fileContent = Files.readAllBytes(file.toPath());
                return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 文件不存在
        }
    }



}
