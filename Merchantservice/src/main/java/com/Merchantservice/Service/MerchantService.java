package com.Merchantservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Merchantservice.Repository.MerchantRepo;
import com.Merchantservice.model.Merchant;
import com.Merchantservice.util.EmailUtil;


@Service
public class MerchantService {
	@Autowired
    private EmailUtil emailUtil;
    
    private final MerchantRepo merchantRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public MerchantService(MerchantRepo merchantRepo, BCryptPasswordEncoder passwordEncoder) {
        this.merchantRepo = merchantRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
  
    
    public Merchant findByEmail(String email) {
        return merchantRepo.findByEmail(email);
    }
}


