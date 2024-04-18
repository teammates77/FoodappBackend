package com.foodapp.restaurantservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;
    
    private Integer merchantId;

    private String restaurantName;

    private String managerName;
    
   private String restaurant_image_Url;
    
    
    private String contact;
    
    private String addressLine;

    private String city;

    private String state;

    private String country;

    private Integer pinCode;


//    @ManyToMany(mappedBy = "restaurants")
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)  
    private List<Item> items = new ArrayList<>();



}



