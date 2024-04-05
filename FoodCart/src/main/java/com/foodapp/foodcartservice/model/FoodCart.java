package com.foodapp.foodcartservice.model;
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
public class FoodCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @Column(unique = true, nullable = false)
    private Integer userId;

    @OneToMany(mappedBy = "foodCart", cascade = CascadeType.REMOVE)
    private List<CartItem> items = new ArrayList<>();

}
