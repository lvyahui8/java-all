package org.lyh.java.mybatis.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/8/4 21:51
 */
@Component
@Aspect
public class CostTimeInterceptor {

    /**
     * 此方法只是用来定义切入点，即被拦截（代理）的方法.
     * 此方法并不会调用
     */
    @Pointcut("execution(* org.lyh.java.mybatis.service.*.*(..))")
    @SuppressWarnings("unused")
    private void aspectJMethod() {
    }

    @Around(value="aspectJMethod()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long time = System.currentTimeMillis();
        Object result;
        result = proceedingJoinPoint.proceed();
        System.out.println("cost_time :" + (System.currentTimeMillis() - time) + " ms");
        return result;
    }


    @AfterThrowing(value="aspectJMethod()",throwing="e")
    public void doThrowing(JoinPoint joinPoint, Exception e) {
        System.err.println("method : " + joinPoint.getTarget().toString());
        e.printStackTrace();
    }
}
