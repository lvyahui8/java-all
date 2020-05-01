package org.lyh.base.proxy;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * 代理模式，有点类似于装饰模式
 * 其实两者都是在加功能，加前置后置操作，不同的是：
 * 功能上
 *      代理模式倾向与添加与原方法功能不太相关的功能，如日志、校验、鉴权等
 *      装饰模式倾向与增强原有功能，提高原方法的性能、易用性等等
 * 复用性
 *      代理模式编写的类复用性要更强，首先跟业务无关的方法，一般都是较为通用的，如日志。
 *          再者代理模式以Proxy类基于反射创建代理类，复用性更高
 *      装饰模式因为跟原类的方法业务耦合性太高，基本一个包装类只适用实现相同接口的类
 * 实现上
 *      代理模式以Proxy类基于反射创建代理对象
 *      装饰模式则一般new固定类的对象（不论此对象从构造函数来还是在装饰类内部创建）
 *
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/8/4 18:02
 */
public class CalcClient {
    public static void main(String[] args) throws Exception {
        /*
         * jdk自带的代理只能
         */
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ICalculator calculator = new CalculatorInvocationHandler(new Calculator()).getProxy();
        System.out.println(calculator.add(1,1));

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\tmp\\class");
        Calculator calculator2 = new CalculatorInterceptor(new Calculator()).proxy();
        System.out.println(calculator2.div(2,1));

        testBenchmark();
    }

    public static void testBenchmark() throws Exception {
        try {
            int [] arr ;
            for (int i = 0 ;i < 100000; i++) {
                ICalculator calculator = new CalculatorInvocationHandler(new Calculator()).getProxy();
                calculator.add(1,1);
                for(int j = 0 ; j < 100; j ++){
                    arr = new int[150 * 2048];
                    arr[1] = 1;
                    arr = null;
                    System.gc();
                }

//            Thread.sleep(20);


//            Calculator calculator2 = new CalculatorInterceptor(new Calculator()).proxy();
//            calculator2.div(2,1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
