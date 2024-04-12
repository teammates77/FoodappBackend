package com.Merchantservice.DTO;

import lombok.Data;

@Data
public class LoginResponseDTO {
	private Integer merchantId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
}
