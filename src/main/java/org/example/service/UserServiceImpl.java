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

    @Override
    public boolean registerUser(User user) {
        try {
            userMapper.insertUser(user);
            return true;
        } catch (Exception e) {
            // 处理异常，例如日志记录
            return false;
        }
    }
}

