package com.foodapp.foodcartservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {
	   
    private Integer userId;
    
    private List<ItemInCartDTO> itemsInCart;
    
    private Double totalCost;
    
}