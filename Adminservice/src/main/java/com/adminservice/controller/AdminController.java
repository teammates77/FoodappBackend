package com.adminservice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adminservice.dto.LoginDTO;
import com.adminservice.dto.RegistrationDTO;
import com.adminservice.service.AdminService;
import com.adminservice.service.RegistrationService;

import jakarta.validation.Valid;


@Validated
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v")
public class AdminController {
    private final RegistrationService registrationService;

   
    private final AdminService adminService; 
    @Autowired
    
    public AdminController(RegistrationService registrationService,  AdminService adminService) {
        this.registrationService = registrationService;
		this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody RegistrationDTO registrationDTO){
        try {
            if (registrationService.existsByEmail(registrationDTO.getEmail())) {
                return new ResponseEntity<>("Email ID already exists", HttpStatus.CONFLICT);
            }
            RegistrationDTO createdRegistrationDTO = registrationService.createAdmin(registrationDTO);
            return new ResponseEntity<>("Admin Registration Successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Admin Registration is failed" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/registereddetails")
    public ResponseEntity<List<RegistrationDTO>> getAllAdmin(){
        List<RegistrationDTO> registrationDTO = registrationService.getAllAdmin();
        if(registrationDTO.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(registrationDTO);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginDTO loginRequest){
        if (loginRequest == null || loginRequest.getEmail().isEmpty() || loginRequest.getPassword().isEmpty()) {
            return new ResponseEntity<>("Email or Password can't be Empty", HttpStatus.BAD_REQUEST);
        }
        boolean isValidLogin = registrationService.validateLogin(loginRequest);
        if(isValidLogin) {
            return new ResponseEntity<>("Login Successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
    }
}

    