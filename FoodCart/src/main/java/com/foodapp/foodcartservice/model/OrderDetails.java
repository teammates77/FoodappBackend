package com.foodapp.foodcartservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private Integer orderId;

    private LocalDateTime timeSpan;

    private FoodCart foodCart;

    private String status;

}
