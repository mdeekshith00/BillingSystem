//package com.system.serviceImpl;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import com.system.service.EmailService;
//import lombok.RequiredArgsConstructor;
//
//
//
//@Service
//@RequiredArgsConstructor
//public class EmailServiceImpl implements EmailService  {
//	
//	private final JavaMailSender javaMailSender;
//	
//	public void sendEmail(String to , String subject , String body) {
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo(to);
//      msg.setSubject(subject);
//      msg.setText(body);
//      javaMailSender.send(msg);
//	}
//
//}
//
//
//
