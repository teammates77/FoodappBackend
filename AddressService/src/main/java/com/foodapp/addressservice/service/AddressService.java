package com.foodapp.addressservice.service;

import java.util.List;

import com.foodapp.addressservice.model.Address;

public interface AddressService {

    public Address registerAddress(Address address);

    public Address updateAddress(Address address);

    public Address getAddress(Integer addressId);

    public Address deleteAddressById(Integer addressId);

	public List<Address> getAddressesByUserId(Integer userId);


}
