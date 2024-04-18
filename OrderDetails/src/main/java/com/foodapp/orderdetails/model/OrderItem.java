package com.foodapp.orderdetails.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    private Integer itemId;

    private String itemName;

    private Integer quantity;

    private Double cost;
    
    private Integer userId;
    
    private Integer restaurantId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("OrderItem")
    private OrderDetails orderDetails;
}