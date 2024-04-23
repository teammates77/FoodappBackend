package com.Userservice.DTO;


import lombok.Data;
@Data
public class LoginResponseDTO {


	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
    private Integer foodCartId;
	
}