package com.example.springSecurity07.service;

import com.example.springSecurity07.entity.User;

public interface UserService {

    public User save(User user,String path);
    public void removeSessionMsg();
    public void sendEmail(User user,String path);
    public boolean verifyAccount(String verificationCode);
}
