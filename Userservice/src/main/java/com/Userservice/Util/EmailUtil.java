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
public EmailUtil (JavaMailSender javaMailSender) {
	this.javaMailSender = javaMailSender;
}
	 public void sendSetPasswordEmail(String email) throws MessagingException {
		    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		    mimeMessageHelper.setTo(email);
		    mimeMessageHelper.setSubject("Set Password");
		    mimeMessageHelper.setText("""
		        <div>
		          <a href="http://localhost:8080/set-password?email=%s" target="_blank">click link to set password</a>
		        </div>
		        """.formatted(email), true);

		    javaMailSender.send(mimeMessage);
		  }
}
