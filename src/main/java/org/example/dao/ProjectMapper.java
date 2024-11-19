package org.example.dao;

import org.example.entity.Project;
import org.example.entity.uuser;

import java.util.List;

public interface ProjectMapper {
     List<Project> findPjByName(String name);
     void updateProject(uuser user);  // 新增的方法，用于更新项目文件字段
}
