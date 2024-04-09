package com.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adminservice.repository.AdminRepo;
import com.adminservice.util.EmailUtil;


@Service
public class AdminService {
	
    private final EmailUtil emailUtil; 
    private final AdminRepo adminRepo;
    private final BCryptPasswordEncoder passwordEncoder; 

    @Autowired
    public AdminService(AdminRepo userRepo, BCryptPasswordEncoder passwordEncoder, EmailUtil emailUtil ) {
        this.adminRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailUtil = emailUtil;
    }
    


}
