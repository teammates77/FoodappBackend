package com.Userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private Integer billId;

    private LocalDateTime timeSpan;

    private OrderDetails orderDetails;

    private Integer totalItem;

    private Double totalCost;
}
