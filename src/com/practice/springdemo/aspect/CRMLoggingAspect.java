package com.practice.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations
	@Pointcut("execution(* com.practice.springdemo.service.*.*(..))")
	public void forServicePackage() {
	}

	@Pointcut("execution(* com.practice.springdemo.controller.*.*(..))")
	public void forControllerPackage() {
	}

	@Pointcut("execution(* com.practice.springdemo.dao.*.*(..))")
	public void forDaoPackage() {
	}

	@Pointcut("forServicePackage() || forControllerPackage() || forDaoPackage()")
	public void forAppFlow() {
	}

	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		// display method we are calling
		String theMethod = joinPoint.getSignature().toShortString();
		myLogger.info("====> in @Before: calling method: " + theMethod);

		// display the arguments to the method

		// get the arguments
		Object[] args = joinPoint.getArgs();

		// loop thru and display args
		for (Object tempArg : args) {
			myLogger.info("====> argument: " + tempArg);
		}
	}

	// add @AfterReturning advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
	public void afterReturning(JoinPoint joinPoint, Object theResult) {

		// display method we are returning from
		String theMethod = joinPoint.getSignature().toShortString();
		myLogger.info("====> in @AfterReturning: from method: " + theMethod);

		// display data returned
		myLogger.info("====> result: " + theResult);
	}
}
