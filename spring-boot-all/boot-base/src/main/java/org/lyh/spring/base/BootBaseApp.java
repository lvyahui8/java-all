package org.lyh.spring.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class BootBaseApp
{
    public static void main( String[] args )
    {
//        SpringApplication.run(BootBaseApp.class,args);
        SpringApplication application = new SpringApplication(BootBaseApp.class);
        application.setWebEnvironment(false);
        application.run(args);
    }
}
