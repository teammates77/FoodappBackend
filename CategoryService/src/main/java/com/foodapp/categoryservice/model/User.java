package com.foodapp.categoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
	private int userid;
	private String firstName;
	private String lastName;
	private String email;
	private String phNumber;
	private String password;
	 @JsonProperty
	   private Integer addressId;

	    @JsonProperty
	    private Integer foodCartId;
    


}
