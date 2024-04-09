package com.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminservice.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository <Admin, Integer>{
	Admin findByEmail(String email);
    boolean existsByEmail(String email);
}

   
