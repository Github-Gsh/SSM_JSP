package org.example.service;


import org.example.entity.uuser;

public interface uuserService {
   public uuser findByuName(String name);
   void updateHeadshot(uuser user);
}
