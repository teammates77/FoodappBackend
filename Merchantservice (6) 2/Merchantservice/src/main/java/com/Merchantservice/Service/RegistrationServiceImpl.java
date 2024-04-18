package com.Merchantservice.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Merchantservice.DTO.LoginDTO;
import com.Merchantservice.DTO.RegistrationDTO;
import com.Merchantservice.Repository.MerchantRepo;
import com.Merchantservice.model.Merchant;



@Service
public class RegistrationServiceImpl implements RegistrationService {
 
    private final MerchantRepo merchantRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
 
    @Autowired
    private EmailService email;
 
    @Autowired
    public RegistrationServiceImpl(MerchantRepo merchantRepo, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.merchantRepo = merchantRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        
        
    }
 
    @Override
    public RegistrationDTO createMerchant(RegistrationDTO registrationDTO) {
        if (existsByEmail(registrationDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Merchant createdMerchant = merchantRepo.save(modelMapper.map(registrationDTO, Merchant.class));
        email.sendSimpleEmail(registrationDTO.getEmail(),
                registrationDTO.getFirstName() + " Welcome to FoodFun Application, Thank you for registering our app.",
                "Registration Successful");
        return modelMapper.map(createdMerchant, RegistrationDTO.class);
    }
   
    @Override
    public boolean validateLogin(LoginDTO loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Merchant merchant = merchantRepo.findByEmail(email);
 
        if (merchant != null) {
            return passwordEncoder.matches(password, merchant.getPassword());
        } else {
            return false;
        }
    }
    private RegistrationDTO getRegistrationDTOFromMerchant(Merchant merchant){

        RegistrationDTO registrationDTO = new RegistrationDTO();

        registrationDTO.setMerchantId(merchant.getMerchantId());
        registrationDTO.setFirstName(merchant.getFirstName());
        registrationDTO.setLastName(merchant.getLastName());
        registrationDTO.setEmail(merchant.getEmail());
        registrationDTO.setPhoneNumber(merchant.getPhoneNumber());
        registrationDTO.setPassword(merchant.getPassword());

        return registrationDTO;

    }
    @Override
    public RegistrationDTO getMerchantDetailsByEmail(String email) {
        Merchant merchant = merchantRepo.findByEmail(email);

        if (merchant != null) {
            RegistrationDTO merchantDetails = new RegistrationDTO();
            merchantDetails.setMerchantId(merchant.getMerchantId());
            merchantDetails.setFirstName(merchant.getFirstName());
            merchantDetails.setLastName(merchant.getLastName());
            merchantDetails.setEmail(merchant.getEmail());
            merchantDetails.setPhoneNumber(merchant.getPhoneNumber());
          
            return merchantDetails;
        }else {
		return null; 
    }
	}

    @Override
	public List<RegistrationDTO> getAllRegistrations() {
    return null;
	}
  

    @Override
    public List<RegistrationDTO> getAllMerchants() {
        List<Merchant> merchants = merchantRepo.findAll();
        return merchants.stream()
                .map(merchant -> modelMapper.map(merchant, RegistrationDTO.class))
                .collect(Collectors.toList());
    }
	@Override
	public boolean existsByEmail(String email) {
		return merchantRepo.existsByEmail(email);
	}

	

	
}

