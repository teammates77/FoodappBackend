package com.Userservice.Util;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class EmailUtil {
private final JavaMailSender javaMailSender;
@Autowired
public EmailUtil(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
public void sendSetPasswordEmail(String email) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Set Password");

    // Construct the URL with placeholders for email
    String url = "http://localhost:8080/fooddelivery/user/set-password";
    String formattedUrl = String.format(url, email);

    // Set the email body with the formatted URL
    String emailBody = String.format("<div><a href=\"%s\" target=\"_blank\">Click link to set password</a></div>", formattedUrl);
    mimeMessageHelper.setText(emailBody, true);

    javaMailSender.send(mimeMessage);
}

}
