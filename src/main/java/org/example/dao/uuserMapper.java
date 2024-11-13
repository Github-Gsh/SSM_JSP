package org.example.dao;


import org.example.entity.uuser;

public interface uuserMapper {
    public uuser findByuName(String name);
    void updateHeadshot(uuser user);
}
