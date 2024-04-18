package com.foodapp.foodcartservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
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
    
    
    private String description;
    
    private String ItemimageUrl;

    //@JsonIgnore
    private Integer categoryId;
   

    private Double cost;

//    @ManyToMany
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Specify the foreign key column
    @JsonIgnoreProperties("items")
    private Restaurant restaurant;

}
