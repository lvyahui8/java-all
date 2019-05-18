package org.lyh.base.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/8/4 18:09
 */
public class CalculatorInterceptor implements MethodInterceptor {
    private Object target ;

    public CalculatorInterceptor(Object target) {
        this.target = target;
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("args" + Arrays.toString(args));
        Object result = proxy.invoke(target,args);
        System.out.println("result:" + result);
        return result;
    }

    public <T> T proxy(){
        return (T) Enhancer.create(target.getClass(),new CalculatorInterceptor(target));
    }
}
