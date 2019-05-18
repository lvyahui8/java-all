package org.lyh.spring.base.ctx;

import org.lyh.spring.base.conf.AppConfig;
import org.lyh.spring.base.conf.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/7/8 0:24
 */
@Component
public class SampleCmdRunner implements CommandLineRunner {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private SysConfig sysConfig;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("appName : " + appConfig.getAppName());
        System.out.println("springAppName : " + appConfig.getSpringAppName());
        System.out.println("sys.secret : " + sysConfig.getSecret());
        System.out.print("command arguments : ");
        Arrays.asList(args).stream().forEach(System.out::print);
        System.out.println();
    }
}
