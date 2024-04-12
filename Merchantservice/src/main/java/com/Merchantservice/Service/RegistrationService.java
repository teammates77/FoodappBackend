package com.Merchantservice.Service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.Merchantservice.DTO.LoginDTO;
import com.Merchantservice.DTO.RegistrationDTO;

import jakarta.validation.Valid;



@Service
public interface RegistrationService {

	List<RegistrationDTO> getAllRegistrations();

	List<RegistrationDTO> getAllMerchants();

	boolean validateLogin(LoginDTO loginRequest);

	RegistrationDTO createMerchant(RegistrationDTO registrationDTO);

	boolean existsByEmail(String email);

	RegistrationDTO getMerchantDetailsByEmail(String email);




}
