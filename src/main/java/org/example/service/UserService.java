package org.example.service;

import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> findAll();

    public void addUser(User user);

    public void deleteById(Integer id);

    public User findById(Integer id);

    public void updateById(User user);

    public User findByName(String name);

    boolean registerUser(User user);

    //注册时检查是否存在同名用户
    boolean isUserNameTaken(String name);
    //用户列表查询用户
    List<User> searchUser(String username);

}

