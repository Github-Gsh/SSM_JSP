package org.example.service;


import org.example.dao.uuserMapper;
import org.example.entity.uuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class uuserServiceImpl implements uuserService {
    @Autowired private uuserMapper uuserMapper;

    @Override
    public uuser findByuName(String name) {
        return uuserMapper.findByuName(name);
    }

    @Override
    public void updateHeadshot(uuser user) {
        uuserMapper.updateHeadshot(user);
    }

//    @Override
//    public void updateProject(uuser user) {
//        uuserMapper.updateProject(user);  // 调用 Mapper 中的 updateProject 方法
//    }
}
