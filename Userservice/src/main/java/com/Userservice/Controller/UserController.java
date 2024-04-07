package com.Userservice.Controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.Userservice.Service.AddressService;
import com.Userservice.Service.RegistrationService;
import com.Userservice.Service.UserService;
import com.Userservice.model.User;
import com.Userservice.model.Address;

import jakarta.validation.Valid;
@Validated
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fooddelivery/user")
public class UserController {

    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final AddressService addressService;

    @Autowired
    public UserController(RegistrationService registrationService, ModelMapper modelMapper, UserService userService, AddressService addressService) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.addressService = addressService;
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
        return new ResponseEntity<>("Registration Successful", HttpStatus.CREATED);
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
            responseDTO.setAddress(userDetails.getAddress());
 
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User details not found", HttpStatus.NOT_FOUND);
        }
    } else {
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }
}
@GetMapping("/{userId}")
public ResponseEntity<?> getUserById(@PathVariable int userId) {
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
public ResponseEntity<?> updateProfile(@PathVariable int userId, @Valid @RequestBody LoginResponseDTO responseDTO) {
    try {
        // Retrieve the user from the registration service
        Optional<User> optionalUser = registrationService.getUserById(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Update user details
        User existingUser = optionalUser.get();
        existingUser.setFirstName(responseDTO.getFirstName());
        existingUser.setLastName(responseDTO.getLastName());
        existingUser.setPhoneNumber(responseDTO.getPhoneNumber());
        existingUser.setEmail(responseDTO.getEmail());

        // Update the address
        Address address = responseDTO.getAddress();
        if (address != null) {
            // Call the Feign client to update the address
            addressService.updateAddress(address);
            // Assuming the updateAddress method in the Feign client interface handles updating the address
        }

        // Save the updated user
        User updatedUser = registrationService.updateUser(existingUser);

        // Map the updated user to response DTO
		LoginResponseDTO updatedLoginResponseDTO = modelMapper.map(updatedUser, LoginResponseDTO.class);
        return new ResponseEntity<>("User Profile Updated", HttpStatus.OK);
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
}




	
	

