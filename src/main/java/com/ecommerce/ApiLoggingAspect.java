package com.ecommerce;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingAspect.class);

    // Advice that runs before any controller method executes
    @Before("execution(* com.ecommerce.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("🔥 API Hit: " + joinPoint.getSignature().toShortString());
    }
}