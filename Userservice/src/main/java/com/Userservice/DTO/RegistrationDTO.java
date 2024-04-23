package com.Userservice.DTO;



import lombok.Data;

@Data
public class RegistrationDTO {
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
    private Integer foodCartId;

}
