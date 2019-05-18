package org.lyh.spring.base.boot.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 将main放在root package 可以隐含一个基础的包搜索路径
 * 这种时候，可能就不需要配置ComponentScan的basePackage属性
 *
 * SpringBootApplication 注解等价于以默认属性使用@Configuration ， @EnableAutoConfiguration 和 @ComponentScan
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class,args);
    }
}
