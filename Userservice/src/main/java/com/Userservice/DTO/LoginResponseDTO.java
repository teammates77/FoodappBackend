package com.Userservice.DTO;

import com.Userservice.model.Address;

import jakarta.persistence.Embedded;
import lombok.Data;
@Data
public class LoginResponseDTO {


	private Integer userid;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@Embedded
	private Address address;
	
}