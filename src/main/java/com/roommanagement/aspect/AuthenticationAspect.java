package com.roommanagement.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roommanagement.services.AuthenticationService;


@Component
@Aspect
class UserAspect{
	@Autowired
	private AuthenticationService registerService;
	
	@Around("execution(* com.roommanagement.controllers.UsersController.getUsers(..))")
	public Object authenticateAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		String requestId=pjp.getArgs()[0].toString();
		if(registerService.checkUser(requestId, 0)){
			System.out.println("Request Authentication");
			Object obj=pjp.proceed();
			return obj;
		}else{
			System.out.println("Request Not Authentication");
			return null;
		}
	}
/*	@Around("execution(* com.roommanagement.controllers.UsersController.*(..))")
	public Object authenticateSubAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		String requestId=pjp.getArgs()[1].toString();
		if(registerService.checkUser(requestId, 0)){
			Object obj=pjp.proceed();
			return obj;
		}else{
			return null;
		}
	}
*/
/*@Around("execution(* com.roommanagement.controllers.UsersController.*(..))")
	public Object authenticateAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		String requestId=pjp.getArgs()[1].toString();
		if(registerService.checkUser(requestId, 0)){
			Object obj=pjp.proceed();
			return obj;
		}else{
			return null;
		}
}*/
	
}