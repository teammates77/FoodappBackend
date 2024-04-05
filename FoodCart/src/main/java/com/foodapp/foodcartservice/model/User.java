package com.foodapp.foodcartservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
