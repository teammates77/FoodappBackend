package com.BillService.demo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private LocalDateTime timeSpan;

    private String message;

    private String description;

	public LocalDateTime getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(LocalDateTime timeSpan) {
		this.timeSpan = timeSpan;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    

}
