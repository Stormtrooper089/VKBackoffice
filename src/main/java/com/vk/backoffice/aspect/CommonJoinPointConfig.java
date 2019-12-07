package com.vk.backoffice.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {


    @Pointcut("execution(* com.vk.backoffice.qr.repository.*.*(..))")
    public void dataLayerExecution() {}

    @Pointcut("execution(* com.vk.backoffice.qr.service.*.*(..))")
    public void serviceLayerExecution() {}

    @Pointcut("execution(* com.vk.backoffice.controller.*.*(..))")
    public void controllerLayerExecution(){}
}
