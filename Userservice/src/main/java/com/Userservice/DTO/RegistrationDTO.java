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
//	private String addressLine;
//    private String city;
//    private String state;
//    private String country;
//    private Integer pinCode;
    private Integer foodCartId;

}
