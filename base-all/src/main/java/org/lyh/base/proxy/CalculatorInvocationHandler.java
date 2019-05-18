package org.lyh.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/8/4 17:56
 */
public class CalculatorInvocationHandler implements InvocationHandler {

    private Object target;

    public CalculatorInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("args" + Arrays.toString(args));
        Object result = method.invoke(target,args);
        System.out.println("result:" + result);
        return result;
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new CalculatorInvocationHandler(target));
    }
}
