package org.lyh.spring.base.boot.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/3/9 13:42
 */
public class ObjectReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    public void receiveObject(Object obj) {
        LOGGER.info("msg :" + obj);
    }
}
