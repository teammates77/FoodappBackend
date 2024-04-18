package com.foodapp.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

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
