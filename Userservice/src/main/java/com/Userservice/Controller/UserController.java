package com.Userservice.Controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Userservice.DTO.ForgotPasswordRequest;
import com.Userservice.DTO.LoginDTO;
import com.Userservice.DTO.LoginResponseDTO;
import com.Userservice.DTO.RegistrationDTO;
import com.Userservice.DTO.SetPasswordRequest;
import com.Userservice.Service.RegistrationService;
import com.Userservice.Service.UserService;
import com.Userservice.model.User;


import jakarta.validation.Valid;
@Validated
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fooddelivery/user")
public class UserController {

    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(RegistrationService registrationService, ModelMapper modelMapper, UserService userService) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

@PostMapping("/register")
public ResponseEntity<?> createUser(@RequestBody RegistrationDTO registrationDTO){
    try {
        // Validate the registrationDTO
        if (registrationDTO == null || registrationDTO.getEmail() == null || registrationDTO.getPassword() == null) {
            return new ResponseEntity<>("Email or Password can't be Empty", HttpStatus.BAD_REQUEST);
        }
        
        // Check if email already exists
        if (registrationService.existsByEmail(registrationDTO.getEmail())) {
            return new ResponseEntity<>("Email ID already exists", HttpStatus.CONFLICT);
        }
        
        // Attempt to create the user
        RegistrationDTO createdregistrationDTO = registrationService.saveUser(registrationDTO);
        return new ResponseEntity<>(createdregistrationDTO, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("User Registration is failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    
@GetMapping("/all")
public ResponseEntity<List<RegistrationDTO>> getAllUsers() {
    List<RegistrationDTO> users = registrationService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
    if (loginRequest == null || loginRequest.getEmail().isEmpty() || loginRequest.getPassword().isEmpty()) {
        return new ResponseEntity<>("Email or Password can't be Empty", HttpStatus.BAD_REQUEST);
    }
    boolean isValidLogin = registrationService.validateLogin(loginRequest);
    if (isValidLogin) {
        // Fetch user details from registrationService and create LoginResponseDTO
        RegistrationDTO userDetails = registrationService.getUserDetailsByEmail(loginRequest.getEmail());
        if (userDetails != null) {
            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setUserId(userDetails.getUserId());
            responseDTO.setFirstName(userDetails.getFirstName());
            responseDTO.setLastName(userDetails.getLastName());
            responseDTO.setEmail(userDetails.getEmail());
            responseDTO.setPhoneNumber(userDetails.getPhoneNumber());
            responseDTO.setAddressLine(userDetails.getAddressLine());
            responseDTO.setCity(userDetails.getCity());
            responseDTO.setState(userDetails.getState());
            responseDTO.setCountry(userDetails.getCountry());
            responseDTO.setPinCode(userDetails.getPinCode());
            responseDTO.setFoodCartId(userDetails.getFoodCartId());
 
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User details not found", HttpStatus.NOT_FOUND);
        }
    } else {
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }
}
@GetMapping("/{userId}")
public ResponseEntity<?> getUserById(@PathVariable Integer userId) {
    try {
        RegistrationDTO userDetails = registrationService.getUserDetailsById(userId);
        if (userDetails != null) {
            LoginResponseDTO responseDTO = modelMapper.map(userDetails, LoginResponseDTO.class);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Failed to retrieve user details: " + e.getMessage());
    }
}

@PutMapping("/updateProfile/{userId}")
public ResponseEntity<?> updateProfile(@PathVariable Integer userId, @Valid @RequestBody LoginResponseDTO responseDTO) {
    try {
        // Retrieve the user from the registration service
        Optional<User> optionalUser = registrationService.getUserById(userId);
        User existingUser = optionalUser.get();
        existingUser.setFirstName(responseDTO.getFirstName());
        existingUser.setLastName(responseDTO.getLastName());
        existingUser.setPhoneNumber(responseDTO.getPhoneNumber());
        existingUser.setEmail(responseDTO.getEmail());
        existingUser.setAddressLine(responseDTO.getAddressLine());
        existingUser.setCity(responseDTO.getCity());
        existingUser.setState(responseDTO.getState());
        existingUser.setCountry(responseDTO.getCountry());
        existingUser.setPinCode(responseDTO.getPinCode());
     // Update user in the database
        registrationService.updateUser(existingUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Failed to update profile: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PutMapping("/forgot-password{email}")
public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
    return new ResponseEntity<>(userService.forgotPassword(request.getEmail()), HttpStatus.OK);
}

@PutMapping("/set-password")
public ResponseEntity<String> setPassword(@RequestBody SetPasswordRequest request) {
    String email = request.getEmail();
    String newPassword = request.getNewPassword();
    String confirmPassword = request.getConfirmPassword();
 
    if (!newPassword.equals(confirmPassword)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("New password and confirm password do not match");
    }
 
    return new ResponseEntity<>(userService.setPassword(email, newPassword), HttpStatus.OK);
}
@GetMapping("/find-by-email/{email}")
public ResponseEntity<User> findByEmail(@PathVariable String email) {
    User user = userService.findByEmail(email);
    return ResponseEntity.ok(user);
}
@DeleteMapping("/{userId}")
public ResponseEntity<String> deleteUserById(@PathVariable Integer userId) {
    registrationService.deleteUserById(userId);
    return ResponseEntity.ok("User deleted successfully");
}
}


	
	

