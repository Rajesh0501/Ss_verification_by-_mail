package com.example.springSecurity07.SecurityConfig;

import com.example.springSecurity07.config.CustomAuthSuccessHandler;
import com.example.springSecurity07.config.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CustomAuthSuccessHandler customAuthSuccessHandler;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new CustomUserDetailsService();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable())
                    .authorizeRequests(authorize -> authorize
                            .requestMatchers("/user/**").hasRole("USER")
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .anyRequest().permitAll()
                    )
                    .formLogin(formLogin -> formLogin
                            .loginPage("/signin")
                            .loginProcessingUrl("/userLogin")
                            .successHandler(customAuthSuccessHandler)
                            .permitAll()
                    );


//            http.csrf(csrf -> csrf.disable())
//                    .authorizeRequests(authorize -> authorize
//                            .requestMatchers("/user/**").hasRole("ADMIN") // Differentiate role-based access for /user/**
//                            .requestMatchers("/user/**").hasRole("USER")
//                            .requestMatchers("/**").permitAll() // Allow access to all other endpoints
//                    )
//                    .formLogin(formLogin -> formLogin
//                            .loginPage("/signin")
//                            .defaultSuccessUrl("/userLogin")
//                            .successHandler(customAuthSuccessHandler) // Set your custom success handler here
//                            .permitAll()
//                    );
            return http.build();


        }


    }

