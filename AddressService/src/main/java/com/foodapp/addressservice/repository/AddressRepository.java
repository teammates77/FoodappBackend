package com.foodapp.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.addressservice.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
