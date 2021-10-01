package com.example.store.utils;

import com.example.store.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLSocketFactory;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class SendMail {
	@Autowired
	JavaMailSender javaMailSender;

	public String sendMail(Account account,String subject, String text){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("longttph12421@fpt.edu.vn");
		message.setTo(account.getEmail().toString());
		message.setSubject(subject);
		message.setText(text);
		System.out.println("Send Mail...");
		System.out.println(message);
		javaMailSender.send(message);
		return "đã gửi mail";
	}
}
