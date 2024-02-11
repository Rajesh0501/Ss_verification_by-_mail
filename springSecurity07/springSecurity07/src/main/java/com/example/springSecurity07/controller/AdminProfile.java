package com.example.springSecurity07.controller;

import com.example.springSecurity07.entity.User;
import com.example.springSecurity07.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminProfile {
    @Autowired
    UserRepo userRepo;
    @ModelAttribute
    public void customUser(Principal p, Model m){
        if (p!=null){
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user",user);
        }

    }
    @GetMapping("/profile")
    public String profile(){
        return "admin_profile";
    }
}
