package com.Merchantservice.controller;

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

import com.Merchantservice.DTO.LoginDTO;
import com.Merchantservice.DTO.LoginResponseDTO;
import com.Merchantservice.DTO.RegistrationDTO;
import com.Merchantservice.Service.MerchantService;
import com.Merchantservice.Service.RegistrationService;


import jakarta.validation.Valid;

@Validated
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/fooddelivery/merchant")
public class MerchantController {
	@Autowired
	
	private MerchantService merchantService;
	private RegistrationService RegistrationService;
	
	public MerchantController(RegistrationService registrationService) {
		this.RegistrationService = registrationService;
		
	}
@PostMapping("/register")
public ResponseEntity<?> createUser(@Valid @RequestBody RegistrationDTO registrationDTO){
	try {
		if (RegistrationService.existsByEmail( registrationDTO.getEmail())) {
			return new ResponseEntity<>("Email ID already exists", HttpStatus.CONFLICT);
		}
		RegistrationDTO createdRegistrationDTO = RegistrationService.createMerchant(registrationDTO);
		return new ResponseEntity<>(createdRegistrationDTO, HttpStatus.CREATED);
	}catch (Exception e) {
		return new ResponseEntity<>("Merchant Registration is failed" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}}


@GetMapping("/allmerchants")
public ResponseEntity<List<RegistrationDTO>>getAllUsers(){
	List<RegistrationDTO> registrationDTO = RegistrationService.getAllMerchants();
	if(registrationDTO.isEmpty()) {
		return ResponseEntity.ok(Collections.emptyList());
	}else {
	return ResponseEntity.ok(registrationDTO);
	}
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
    if (loginRequest == null || loginRequest.getEmail().isEmpty() || loginRequest.getPassword().isEmpty()) {
        return new ResponseEntity<>("Email or Password can't be Empty", HttpStatus.BAD_REQUEST);
    }
    boolean isValidLogin = RegistrationService.validateLogin(loginRequest);
    if (isValidLogin) {
        // Fetch user details from registrationService and create LoginResponseDTO
        RegistrationDTO merchantDetails= RegistrationService.getMerchantDetailsByEmail(loginRequest.getEmail());
        if (merchantDetails != null) {
            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setMerchantId(merchantDetails.getMerchantId());
            responseDTO.setFirstName(merchantDetails.getFirstName());
            responseDTO.setLastName(merchantDetails.getLastName());
            responseDTO.setEmail(merchantDetails.getEmail());
            responseDTO.setPhoneNumber(merchantDetails.getPhoneNumber());
        
 
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User details not found", HttpStatus.NOT_FOUND);
        }
    } else {
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }
}



}
