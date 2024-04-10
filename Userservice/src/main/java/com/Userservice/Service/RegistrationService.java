package com.Userservice.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.Userservice.DTO.LoginDTO;
import com.Userservice.DTO.RegistrationDTO;
import com.Userservice.model.User;

@Service
public interface RegistrationService {

	List<RegistrationDTO> getAllRegistrations();

	List<RegistrationDTO> getAllUsers();

	boolean validateLogin(LoginDTO loginRequest);

	boolean existsByEmail(String email);

	User updateUser(User user);

	User getUserByEmail(String email);

	RegistrationDTO getUserDetailsByEmail(String email);

	RegistrationDTO saveUser(RegistrationDTO registrationDTO);

	Optional<User> getUserById(Integer userId);

	RegistrationDTO getUserDetailsById(Integer userId);


}
