package org.example.dao;


import org.example.entity.uuser;

public interface uuserMapper {
    public uuser findByuName(String name);
    void updateHeadshot(uuser user);
    void updateProject(uuser user);  // 新增的方法，用于更新项目文件字段
}
