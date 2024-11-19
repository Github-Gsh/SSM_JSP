package org.example.service;

import org.example.dao.ProjectMapper;
import org.example.entity.Project;
import org.example.entity.uuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    List<Project> findPjByName(String name);
    void updateProject(uuser user);  // 新增的方法，用于更新项目文件字段

}
