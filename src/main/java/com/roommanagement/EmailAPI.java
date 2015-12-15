package com.roommanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emailBean")
public class EmailAPI {
	
	@Autowired
	private MailSender mailSender;  // MailSender interface defines a strategy
										// for sending simple mails
	public void ReadyToSendEmail(String toAddress,String fromAddress,String subject,String msgBody)
	{
		SimpleMailMessage simpleMailMessageObj = new SimpleMailMessage();
		simpleMailMessageObj.setFrom(fromAddress);
		simpleMailMessageObj.setTo(toAddress);
		simpleMailMessageObj.setSubject(subject);
		simpleMailMessageObj.setText(msgBody);
		mailSender.send(simpleMailMessageObj);
	}
}
