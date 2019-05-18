package org.lyh.springboot.simple.resource;

import org.lyh.springboot.simple.config.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/9/3 18:09
 */
@RestController
public class DemoController {

    /*
    * 要读取配置，只需要配置在application.yml文件中，然后直接使用Value注解读取
    * */
    @Value("${my.name}")
    private String name;

    /*
    * 配置也可以专门注入到一个java bean中
    * */
    @Autowired
    private ConfigBean configBean;

    @RequestMapping("/")
    public String index()
    {
        return "simple app" + name;
    }

    @RequestMapping("/conf")
    public String conf(){
        return configBean.getGreeting();
    }

}

