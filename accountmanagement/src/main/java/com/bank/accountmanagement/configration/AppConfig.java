package com.bank.accountmanagement.configration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

  
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder as the password encoder.
        return new BCryptPasswordEncoder();
    }
}
