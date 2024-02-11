package com.example.springSecurity07.config;

import com.example.springSecurity07.entity.User;
import com.example.springSecurity07.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user= userRepo.findByEmail(username);
            if (user==null){
                throw new UsernameNotFoundException("user not found");
            }
            else {
                return new CustomUser(user);
            }


    }
}
