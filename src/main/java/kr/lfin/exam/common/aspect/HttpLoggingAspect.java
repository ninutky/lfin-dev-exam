package kr.lfin.exam.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(HttpLoggingAspect.class);

    @Before("execution(* kr.lfin.exam.controllers.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Request: {}({})", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* kr.lfin.exam.controllers.*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("Response: {}({}) -> {}", joinPoint.getSignature().getName(), joinPoint.getArgs(), result);
    }
}
