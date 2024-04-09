package com.adminservice.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponseDTO {
	public String apiPath;
	private HttpStatus errorCode;
	private String errorMsg;
	private LocalDateTime errorTime;

}