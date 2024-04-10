package com.Userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    	public class Address {

	    private Integer addressId;

	    private String buildingName;

	    private String streetName;

	    private String area;

	    private String city;

	    private String state;

	    private String country;

	    private Integer pinCode;


	}

