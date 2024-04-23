package com.Userservice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Userservice.DTO.FoodCartDTO;
import com.Userservice.DTO.LoginDTO;
import com.Userservice.DTO.RegistrationDTO;
import com.Userservice.Repository.UserRepo;
import com.Userservice.model.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
private final UserRepo userRepo;
private final ModelMapper modelMapper;
private final BCryptPasswordEncoder passwordEncoder;
private final CartService cartService;
private final EmailService email;


  
	@Autowired
	public RegistrationServiceImpl(UserRepo userRepo, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder,CartService cartService, EmailService email) {
		this.userRepo = userRepo;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.cartService = cartService;
		this.email =email;
}
	
	@Override
	public RegistrationDTO saveUser(RegistrationDTO registrationDTO) {
		if (existsByEmail(registrationDTO.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}
		registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
		User savedUser = userRepo.save(modelMapper.map(registrationDTO, User.class));
		email.sendSimpleEmail(registrationDTO.getEmail(),
				registrationDTO.getFirstName()  +" Welcome to FoodFun Application as a Valued Customer where Delivery is Pleasure and NEVER HAVE A BAD MEAL.",
				"Registration Successful");
		// Save user's cart
		 FoodCartDTO foodCartDTO = new FoodCartDTO();
		 foodCartDTO.setUserId(savedUser.getUserId());
		 FoodCartDTO savedCartDto = cartService.saveFoodCart(foodCartDTO);
		 savedUser.setFoodCartId(savedCartDto.getCartId());

        User updatedUser = userRepo.save(savedUser);

        RegistrationDTO savedDTO = getRegistrationDTOFromUser(updatedUser);
		return modelMapper.map(savedUser, RegistrationDTO.class);
		}
	
	@Override
	public boolean validateLogin(LoginDTO loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		User user = userRepo.findByEmail(email);
		if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        } else {
            return false;
        }
	}

	@Override
	public List<RegistrationDTO> getAllRegistrations() {
		return null;
	}
	@Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
	@Override
    public Optional<User> getUserById(Integer userId) {
        return userRepo.findById(userId);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
}
	@Override
    public RegistrationDTO getUserDetailsByEmail(String email) {
        User user = userRepo.findByEmail(email);

        if (user != null) {
            RegistrationDTO userDetails = new RegistrationDTO();
            userDetails.setUserId(user.getUserId());
            userDetails.setFirstName(user.getFirstName());
            userDetails.setLastName(user.getLastName());
            userDetails.setEmail(user.getEmail());
            userDetails.setPhoneNumber(user.getPhoneNumber());
            userDetails.setFoodCartId(user.getFoodCartId());

            return userDetails;
        }else {
		return null; 
    }
	}
	private RegistrationDTO getRegistrationDTOFromUser(User user){

        RegistrationDTO registrationDTO = new RegistrationDTO();

        registrationDTO.setUserId(user.getUserId());
        registrationDTO.setFirstName(user.getFirstName());
        registrationDTO.setLastName(user.getLastName());
        registrationDTO.setEmail(user.getEmail());
        registrationDTO.setPhoneNumber(user.getPhoneNumber());
        registrationDTO.setPassword(user.getPassword());
        registrationDTO.setFoodCartId(user.getFoodCartId());

        return registrationDTO;

    }
@Override
	public RegistrationDTO getUserDetailsById(Integer userId) {
	    Optional<User> optionalUser = userRepo.findById(userId);
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        RegistrationDTO userDetails = new RegistrationDTO();
	        userDetails.setUserId(user.getUserId());
	        userDetails.setFirstName(user.getFirstName());
	        userDetails.setLastName(user.getLastName());
	        userDetails.setEmail(user.getEmail());
	        userDetails.setPhoneNumber(user.getPhoneNumber());
            userDetails.setFoodCartId(user.getFoodCartId());
	        
	        return userDetails;
	    }else {
		return null; 
	}
}

	@Override
	public User getUserByEmail(String email) {
		return null;
	}
	@Override
	public List<RegistrationDTO> getAllUsers() {
	    List<User> users = userRepo.findAll();
	    List<RegistrationDTO> registrationDTOs = new ArrayList<>();
	    for (User user : users) {
	        RegistrationDTO registrationDTO = getRegistrationDTOFromUser(user);
	        registrationDTOs.add(registrationDTO);
	    }
	    return registrationDTOs;
	}
	@Override
	public void deleteUserById(Integer userId) {
	    userRepo.deleteById(userId);
	}


}




