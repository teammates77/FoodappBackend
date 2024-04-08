package com.BillService.demo.Model;


import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Integer itemId;

    private String itemName;

   // private Category category;

    private List<Category> category = new ArrayList<>();
    
    private Integer quantity;

    private Double cost;

    private Integer restaurantId;

	
    
    

}
