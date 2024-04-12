package com.Merchantservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Component
public class EmailUtil {
	@Autowired
	  private JavaMailSender javaMailSender;
	 public void sendSetPasswordEmail(String email) throws MessagingException {
		    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		    mimeMessageHelper.setTo(email);
		    

		    javaMailSender.send(mimeMessage);
		  }

}
