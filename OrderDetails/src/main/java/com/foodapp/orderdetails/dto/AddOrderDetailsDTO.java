package com.foodapp.orderdetails.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderDetailsDTO {
	 private LocalDateTime timeSpan;
	    private Integer cartId;
	    private String status;
	    private List<OrderItemDTO> items;
	    private Integer addressId;
	    private String paymentId;
}
