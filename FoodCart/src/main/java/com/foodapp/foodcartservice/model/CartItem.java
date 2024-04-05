package com.foodapp.foodcartservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    private Integer itemId;

    private String itemName;

    @Embedded
    private Category category;

    private Integer quantity;

    private Double cost;

    private Integer restaurantId ;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private FoodCart foodCart;

}
