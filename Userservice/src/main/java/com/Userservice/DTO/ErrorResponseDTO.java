package com.Userservice.DTO;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponseDTO {
	private HttpStatus errorCode;
	private String errorMsg;
	private LocalDateTime errorTime;

}
