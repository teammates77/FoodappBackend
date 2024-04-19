package com.foodapp.orderdetails.model;

import java.math.BigInteger;
import java.util.UUID;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class OrderRequest {

 
    private String paymentId;
    private Integer userId;
    private String customerName;
    private String email;
    private String phoneNumber;
    private BigInteger amount;
    private String razorpayOrderId;

    public OrderRequest() {
        this.paymentId = generatePaymentId();
    }

    private String generatePaymentId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String shortUuid = uuid.substring(0, 8);
        return shortUuid.toUpperCase();
    }
    public Integer getuserId() {
        return userId;
    }

    public void setId(Integer userId) {
        this.userId = userId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }
}


