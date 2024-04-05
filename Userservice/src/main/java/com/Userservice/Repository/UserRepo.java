package com.Userservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Userservice.model.User;
@Repository
public interface UserRepo extends JpaRepository <User, Integer>{
	User findByEmail(String email);
    boolean existsByEmail(String email);
}

   

               