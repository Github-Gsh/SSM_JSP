package org.example.service;

import org.example.entity.User;
import org.example.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
    @Override
    public void updateById(User user) {
        userMapper.updateById(user);
    }
    @Override
    public User findByName(String name) {
       return userMapper.findByName(name);
    }
    //检查是否存在同名用户
    @Override
    public boolean isUserNameTaken(String name) {
        User user = userMapper.findByName(name);
        return user != null; // 如果找到用户，返回 true，表示用户名已存在
    }

    //注册时检查是否存在同名用户
    @Override
    public boolean registerUser(User user) {
        // 检查用户名是否已经存在
        if (isUserNameTaken(user.getName())) {
            return false; // 用户名已存在，注册失败
        }
        try {
            userMapper.insertUser(user); // 插入用户信息
            return true; // 注册成功
        } catch (Exception e) {
            // 处理异常，例如记录日志
            e.printStackTrace(); // 或使用日志框架记录异常
            return false; // 注册失败
        }
    }


}

