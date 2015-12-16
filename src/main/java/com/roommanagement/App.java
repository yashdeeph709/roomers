package com.roommanagement;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan
@EnableScheduling
@ImportResource("classpath:Mail-bean.xml")
public class App 
{
    public static void main( String[] args )
    {

    	SpringApplication.run(App.class, args);
    	ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("Mail-bean.xml");
		
//		Random randomGenerator = new Random();
//		int randompasswrd = randomGenerator.nextInt(9999-1000)+1000;
//		
//		EmailAPI emailAPI=(EmailAPI)context.getBean("emailBean");
//		String toAddr="shrutiu.27@gmail.com";
//		String fromAddr="shrutiu.7@gmail.com";
//		
//		String subject="Hi from Shruti";
//		
//		String body="There you go!! Keep Moving\nUSerId : "+toAddr+"\nPassword : "+randompasswrd;
//		emailAPI.ReadyToSendEmail(toAddr, fromAddr, subject, body);	
    }
}
