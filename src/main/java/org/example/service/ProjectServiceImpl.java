package org.example.service;

import org.example.dao.ProjectMapper;
import org.example.entity.Project;
import org.example.entity.uuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> findPjByName(String name) {
        return projectMapper.findPjByName(name);
    }

    @Override
    public void updateProject(uuser user) {
        projectMapper.updateProject(user);  // 调用 Mapper 中的 updateProject 方法
    }

}
