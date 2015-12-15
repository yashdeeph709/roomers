package com.roommanagement.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class Email {
	
//	private MailSender mailSender;
//	private SimpleMailMessage simpleMailMessage;
//	
//	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
//		this.simpleMailMessage = simpleMailMessage;
//	}
//
//	public void setMailSender(MailSender mailSender) {
//		this.mailSender = mailSender;
//	}
//	
//	public void sendMail(String dear, String content) {
//		SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
//		
//		message.setText(String.format(
//				simpleMailMessage.getText(), dear, content));
//
//		mailSender.send(message);
//		
//	}
//	
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String from, String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
}
