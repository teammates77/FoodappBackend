package com.Userservice.DTO;


import com.Userservice.model.Address;

import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class RegistrationDTO {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	@Embedded
	private Address address;

}
