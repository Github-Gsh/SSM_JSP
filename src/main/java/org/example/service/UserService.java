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

}

