package com.vk.backoffice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("com.vk.backoffice.aspect.CommonJoinPointConfig.controllerLayerExecution()")
    public void before(JoinPoint joinPoint) {
        logger.info(">>> {}: Entering method {}",
                joinPoint.getSignature().getDeclaringType() ,joinPoint.getSignature());
    }

    /*@AfterReturning(value = "com.vk.backoffice.aspect.CommonJoinPointConfig.controllerLayerExecution()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info(">>> {}:  {} returned with value {}",
                joinPoint.getSignature().getDeclaringType() ,joinPoint.getSignature().getName(), result);
    }*/

    @After("com.vk.backoffice.aspect.CommonJoinPointConfig.controllerLayerExecution()")
    public void after(JoinPoint joinPoint) {
        logger.info(">>> {}: {} method exited . .",
                joinPoint.getSignature().getDeclaringType() , joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "com.vk.backoffice.aspect.CommonJoinPointConfig.controllerLayerExecution()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Object exception) {
        logger.error(">>> {}: {} method throws an exception {}",
                joinPoint.getSignature().getDeclaringType() , joinPoint.getSignature().getName(), exception);
    }

    @Around("@annotation(com.vk.backoffice.aspect.TrackTime)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        Long timeTaken = System.currentTimeMillis() - startTime;
        logger.info(">>> {}:  Time taken by {} is {} "
                ,joinPoint.getSignature().getDeclaringType() , joinPoint.getSignature().getName(), timeTaken);
    }
}
