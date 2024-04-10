package com.adminservice.service;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.adminservice.dto.LoginDTO;
import com.adminservice.dto.RegistrationDTO;


@Service
//@Component
//@Repository
//@Configuration

public interface RegistrationService {

	List<RegistrationDTO> getAllRegistrations();

	List<RegistrationDTO> getAllAdmin();

	boolean validateLogin(LoginDTO loginRequest);

	RegistrationDTO createAdmin(RegistrationDTO registrationDTO);

	boolean existsByEmail(String email);


}
