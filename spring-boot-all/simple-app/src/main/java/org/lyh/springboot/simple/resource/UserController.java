package org.lyh.springboot.simple.resource;

import org.lyh.springboot.simple.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/9/3 19:01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserConfig userConfig;

    @RequestMapping("/conf")
    public String conf(){
        return userConfig.getName() + " -> " + userConfig.getAge();
    }
}
