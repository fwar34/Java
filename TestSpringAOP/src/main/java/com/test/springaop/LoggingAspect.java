package com.test.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(public * com.test.springaop.service.UserService.*(..))")
    public void doAccessCheck() {
        System.err.println("[Before] do access check...");
    }

    @Around("execution(public * com.test.springaop.service.MailService.*(..))")
    public Object doLoggin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.err.println("[Around] start " + proceedingJoinPoint.getSignature());
        Object retVal = proceedingJoinPoint.proceed();
        System.err.println("[Around] done " + proceedingJoinPoint.getSignature());
        return retVal;
    }
}
