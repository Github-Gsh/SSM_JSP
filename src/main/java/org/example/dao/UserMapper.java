package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.entity.User;
import java.util.List;

public interface UserMapper {
    public List<User> findAll();
    public void addUser(User user);
    public void deleteById(Integer id);
    public User findById(Integer id);
    public void updateById(User user);
    public User findByName(String name);
    //注册
    void insertUser(User user);
    List<User> searchUser(@Param("name") String name);
}
