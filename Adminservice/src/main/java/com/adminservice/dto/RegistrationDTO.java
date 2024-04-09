package com.adminservice.dto;

import lombok.Data;

@Data
public class RegistrationDTO {
	private int adminId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	
}
