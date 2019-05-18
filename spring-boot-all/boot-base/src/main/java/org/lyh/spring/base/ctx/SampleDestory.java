package org.lyh.spring.base.ctx;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/7/8 0:27
 */
@Component
public class SampleDestory  {
    @PreDestroy
    public void exit(){
        System.out.println("exit");
    }
}
