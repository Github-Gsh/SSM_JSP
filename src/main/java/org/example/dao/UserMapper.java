package org.example.dao;

import org.example.entity.User;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();
    public void addUser(User user);
    public void deleteById(Integer id);
    public User findById(Integer id);
    public void updateById(User user);
}
