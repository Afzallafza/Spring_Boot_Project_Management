package org.babor.boot_project_management.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(* org.babor.boot_project_management.repository.*.save(..))")
    public void logBeforeSavingEntity(JoinPoint joinPoint) {
        Object entity = joinPoint.getArgs()[0];
        String entityName = entity.getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{} method called for {} entity", methodName, entityName);
    }

    @AfterReturning(value = "execution(* org.babor.boot_project_management.repository.*.save(..))")
    public void logAfterSavingEntity(JoinPoint joinPoint) {
        String fullClassName = joinPoint.getTarget().getClass().getSimpleName();
        String entityName = fullClassName.replace("Dao", "");
        String methodName = joinPoint.getSignature().getName();
//        (MethodSignature) joinPoint.getSignature().getName();
//        log.info("just checking: {}", joinPoint.);
        log.info("DAO Method called: {} for entity: {}", methodName, fullClassName);
        log.info("{} saved to DB", joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterThrowing(value = "execution(* org.babor.boot_project_management.service.*(..)))", throwing = "exception")
    public void logAfterThrowingException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        log.error("{} thrown exception: {}", methodName, exception.getMessage());
    }
}
