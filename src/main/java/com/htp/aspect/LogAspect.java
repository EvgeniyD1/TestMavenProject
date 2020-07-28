package com.htp.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@Aspect
public class LogAspect {

    private static final Logger log = Logger.getLogger(LogAspect.class);

    private static Map<String, Integer> methodInvocation = new ConcurrentHashMap<>();

    public static String showStatistic() {
        return methodInvocation.entrySet().stream().map(e -> e.getKey() + " " + e.getValue())
                .collect(Collectors.joining(",\n"));
    }

    public static Map<String, Integer> getMethodInvocation() {
        return methodInvocation;
    }

    @Pointcut("execution(* com.htp.dao..*(..))")
    public void daoJdbcTemplateRepositoryPointcut() {
    }

//    @Before("daoJdbcTemplateRepositoryPointcut()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " start");
//    }
//
//    @AfterReturning(pointcut = "daoJdbcTemplateRepositoryPointcut()")
//    public void doAccessCheck(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " finished");
//    }

    @Around("daoJdbcTemplateRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String mapKey = joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName();
        Integer mapValue = methodInvocation.get(mapKey);
        methodInvocation.put(mapKey, mapValue == null ? 1 : mapValue + 1);

        StopWatch sw = new StopWatch();
        log.info("Method " + joinPoint.getSignature().getDeclaringTypeName() + " " +
                joinPoint.getSignature().getName() + " start");
        sw.start();
        Object proceed = joinPoint.proceed();
        sw.stop();
        log.info("Method " + joinPoint.getSignature().getDeclaringTypeName() + " " +
                joinPoint.getSignature().getName() + " finished");
        log.info("Method execution time: " + sw.getTotalTimeMillis() + " milliseconds.");
        return proceed;
    }

}
