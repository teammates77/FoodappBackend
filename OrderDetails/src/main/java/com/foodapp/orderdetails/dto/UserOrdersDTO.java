package com.foodapp.orderdetails.dto;

import com.foodapp.orderdetails.model.OrderRequest;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrdersDTO {
    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private Double cost;
    private String status;
    private String deliveryStatus;
    private Integer orderId;
    private Integer userid;
    private String paymentId;
//    @Embedded
//    private OrderRequest paymentDetails; 
}