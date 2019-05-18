package org.lyh.camel.processor;

import org.apache.camel.Exchange;
import org.lyh.camel.bean.Input;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 15:19
 */
public class GetProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        Input input = Input.createInstance(exchange);
        System.err.println(input);
        // cost time
        Thread.sleep(3000);
        // 将请求透传到真实的uri
    }
}
