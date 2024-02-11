package com.example.springSecurity07.repo;

import com.example.springSecurity07.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
    public User findByVerificationCode(String code);
}
