package org.lyh.spring.base.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/7/8 21:53
 */
@Component
public class AppConfig {
    @Value("${app.name:sampleSpringBootApp}")
    private String appName;

    @Value("${spring.application.name}")
    private String springAppName;

    public String getAppName() {
        return appName;
    }


    public String getSpringAppName() {
        return springAppName;
    }
}
