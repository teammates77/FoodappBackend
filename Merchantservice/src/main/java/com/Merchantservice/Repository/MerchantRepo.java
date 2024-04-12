package com.Merchantservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Merchantservice.model.Merchant;



@Repository
public interface MerchantRepo extends JpaRepository <Merchant, Integer>{

	Merchant findByEmail(String email);
	boolean existsByEmail(String email);
}
