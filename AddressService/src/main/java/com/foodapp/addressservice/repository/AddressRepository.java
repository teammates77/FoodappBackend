package com.foodapp.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.foodapp.addressservice.model.Address;


public interface AddressRepository extends JpaRepository<Address, Integer> {

	public List<Address> findByUserId(Integer userId);

}
