package com.foodapp.categoryservice.model;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private List<String> role = new ArrayList<>();

    private String mobile;

    @Transient
    private Address address;

    @Transient
    private FoodCart foodCart;


}
