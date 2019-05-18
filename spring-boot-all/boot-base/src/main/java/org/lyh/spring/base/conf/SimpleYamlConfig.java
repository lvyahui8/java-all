package org.lyh.spring.base.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/9/3 17:43
 */

@Configuration
@PropertySource("classpath:simple.yaml")
public class SimpleYamlConfig {
    private Map<String,Object> environments = new HashMap<>();

    public Map<String, Object> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Map<String, Object> environments) {
        this.environments = environments;
    }
}
