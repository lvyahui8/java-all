package org.lyh.spring.base.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/7/8 21:58
 */
@Component
@PropertySource("classpath:sys.properties")
public class SysConfig {
    @Value("sys.secret")
    private String secret;

    public String getSecret() {
        return secret;
    }
}
