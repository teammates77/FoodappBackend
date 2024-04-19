package com.foodapp.orderdetails.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.foodapp.orderdetails.model.OrderRequest;

@FeignClient(name = "payment-service")
public interface PaymentService {

    @GetMapping("/fooddelivery/payment/paymentdetails/{paymentId}")
    public OrderRequest getOrdersByPaymentId(@PathVariable String paymentId);
}
