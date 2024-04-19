package com.foodapp.addressservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.addressservice.exceptions.AddressException;
import com.foodapp.addressservice.model.Address;
import com.foodapp.addressservice.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{

    
    private final AddressRepository addressRepository;
    
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
    	this.addressRepository=addressRepository;
    }
    
    @Override
    public Address registerAddress(Address address) {

        return addressRepository.save(address);

    }

    @Override
    public Address updateAddress(Address address) {

        Address validatedAddress = addressRepository.findById(address.getAddressId()).orElseThrow(()-> new AddressException("Address not found with address id : "+address.getAddressId()));

        return addressRepository.save(address);

    }

    @Override
    public Address getAddress(Integer addressId) {

        return addressRepository.findById(addressId).orElseThrow(()-> new AddressException("Address does not exists with address id : "+addressId));

    }

    @Override
    public Address deleteAddressById(Integer addressId) {

        Address address = addressRepository.findById(addressId).orElseThrow(()-> new AddressException("Address does not exists with address id : "+addressId));

        addressRepository.delete(address);

        return address;

    }
    
    public List<Address> getAddressesByUserId(Integer userId) {
        return addressRepository.findByUserId(userId);
    }

    
}
