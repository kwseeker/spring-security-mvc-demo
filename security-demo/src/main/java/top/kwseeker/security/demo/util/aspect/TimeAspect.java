package top.kwseeker.security.demo.util.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    @Around("execution(public * top.kwseeker.security.demo.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("TimeAspect start");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        long start = new Date().getTime();

        Object object = pjp.proceed();  //这里执行controller方法

        System.out.println("TimeAspect 耗时:"+ (new Date().getTime() - start));
        System.out.println("TimeAspect end");

        return object;
    }

}
