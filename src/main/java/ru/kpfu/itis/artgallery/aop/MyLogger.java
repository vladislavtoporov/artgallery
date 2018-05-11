package ru.kpfu.itis.artgallery.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyLogger {
    private final Logger log = Logger.getLogger(this.getClass());

    @Pointcut("execution(* ru.kpfu.itis.artgallery.*.*.*(..))")
    public void selectAllMethodAvailable() {
    }

    @Before("selectAllMethodAvailable()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("Method " + joinPoint.getSignature().getName() + " is called");
    }

    //    @After("selectAllMethodAvailable()")
//    public void afterAdvice(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + "is executed");
//    }
//
//    @AfterReturning(value = "selectAllMethodAvailable()", returning = "someValue")
//    public void afterAdvice(JoinPoint joinPoint, Object someValue) {
//        log.info("Method " + joinPoint.getSignature().getName() + "is executed");
//    }
//
    @AfterThrowing(value = "selectAllMethodAvailable()", throwing = "e")
    public void afterAdvice(JoinPoint joinPoint, ClassCastException e) {
        log.error("We have an exeption here " + e.toString());
    }
}
