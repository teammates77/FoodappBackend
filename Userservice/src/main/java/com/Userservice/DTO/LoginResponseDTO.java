package com.Userservice.DTO;

import com.Userservice.model.Address;
import com.Userservice.model.FoodCart;

import jakarta.persistence.Embedded;
import lombok.Data;
@Data
public class LoginResponseDTO {


	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@Embedded
	private Address address;
	
}