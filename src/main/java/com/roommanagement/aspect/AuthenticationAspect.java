package com.roommanagement.aspect;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.roommanagement.services.AuthenticationService;


@Component
@Aspect
class UserAspect{
	@Autowired
	private AuthenticationService registerService;
	
	@Around("execution(* com.roommanagement.controllers.UsersController.*(..))")
	public Object authenticateUserAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args=pjp.getArgs();
		String id=args[0].toString();
		if(registerService.checkUser(id, 0)){
			Object obj=pjp.proceed();
			return obj;
		}else{
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}	
	@Around("execution(* com.roommanagement.controllers.RoomController.addRoom(..) )")
	public Object authenticateRoomAddAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args=pjp.getArgs();
		String id=args[0].toString();
		if(registerService.checkUser(id, 0)){
			Object obj=pjp.proceed();
			return obj;
		}else{
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}	
	@Around("execution(* com.roommanagement.controllers.RoomController.getRoom(..) )")
	public Object authenticateRoomUpdateAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args=pjp.getArgs();
		String id=args[0].toString();
		if(registerService.checkUser(id, 0)){
			Object obj=pjp.proceed();
			return obj;
		}else{
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}	
	@Around("execution(* com.roommanagement.controllers.RoomController.updateRoom(..) )")
	public Object authenticateGetRoomAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args=pjp.getArgs();
		String id=args[0].toString();
		if(registerService.checkUser(id, 0)){
			Object obj=pjp.proceed();
			return obj;
		}else{
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}	
	@Around("execution(* com.roommanagement.controllers.RoomController.deleteRoom(..) )")
	public Object authenticateDeleteRoomAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args=pjp.getArgs();
		String id=args[0].toString();
		if(registerService.checkUser(id, 0)){
			Object obj=pjp.proceed();
			return obj;
		}else{
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}	
	@Around("execution(* com.roommanagement.controllers.RoomController.roomRange(..) )")
	public Object authenticateRoomAdmin(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args=pjp.getArgs();
		String id=args[0].toString();
		if(registerService.checkUser(id, 0)){
			Object obj=pjp.proceed();
			return obj;
		}else{
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}	

	@Around("execution(* com.roommanagement.controllers.*.*(..))")
	public Object logRoomAdvice(ProceedingJoinPoint pjp) throws Throwable
	{
		System.out.println("Method Named "+pjp.getSignature().getName()+" called with these arguments:");
		Object[] args=pjp.getArgs();
		for(Object obj:args){
			System.out.println("AspectJ Logger:"+obj);
		}
		Object obj=pjp.proceed();
		return obj;
	}
}