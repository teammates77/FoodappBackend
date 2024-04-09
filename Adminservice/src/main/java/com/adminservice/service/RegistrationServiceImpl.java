package com.adminservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adminservice.dto.LoginDTO;
import com.adminservice.dto.RegistrationDTO;
import com.adminservice.model.Admin;
import com.adminservice.repository.AdminRepo;

@Service

public class RegistrationServiceImpl implements RegistrationService  {
	private final AdminRepo adminRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    
 
    @Autowired
    private EmailService email;
 
    @Autowired
    public RegistrationServiceImpl(AdminRepo adminRepo, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepo = adminRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public RegistrationDTO createAdmin(RegistrationDTO registrationDTO) {
        if (existsByEmail(registrationDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Admin createdAdmin = adminRepo.save(modelMapper.map(registrationDTO, Admin.class));
        email.sendSimpleEmail(registrationDTO.getEmail(),
                registrationDTO.getFirstName() + " Welcome to FoodFun Application ",
                "Registration Successful");
        return modelMapper.map(createdAdmin, RegistrationDTO.class);
    }
    @Override
    public boolean validateLogin(LoginDTO loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Admin admin = adminRepo.findByEmail(email);
 
        if (admin != null) {
            return passwordEncoder.matches(password, admin.getPassword());
        } else {
            return false;
        }
    }

    @Override
	public List<RegistrationDTO> getAllRegistrations() {
		return null;
	}
    @Override
	public List<RegistrationDTO> getAllAdmin() {
		return null;
	}

	@Override
	public boolean existsByEmail(String email) {
	    return adminRepo.existsByEmail(email);
	    
	}
	
    
}




