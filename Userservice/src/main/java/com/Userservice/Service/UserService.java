package com.Userservice.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
 
import com.Userservice.Repository.UserRepo;
import com.Userservice.Util.EmailUtil;
import com.Userservice.model.User;
 
import jakarta.mail.MessagingException;
 
@Service
public class UserService {
    @Autowired
    private EmailUtil emailUtil;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder; // Adding BCryptPasswordEncoder
 
    @Autowired
    public UserService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public String forgotPassword(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new  IllegalArgumentException("User not found with this email: " + email);
        }
        try {
            emailUtil.sendSetPasswordEmail(email);
        } catch (MessagingException e) {
            throw new  IllegalArgumentException("Unable to send set password Link. Please try again.");
        }
        return "Please check your email to set a new password.";
    }
    public String setPassword(String email, String newPassword) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new  IllegalArgumentException("User not found with this email: " + email);
        }
        // Encrypt the password before saving
        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userRepo.save(user);
        return "Password Reset successful";
    }
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}