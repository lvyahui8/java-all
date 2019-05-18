package org.lyh.camel.processor;

import org.apache.camel.Exchange;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 15:18
 */
public class PostProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange);
        // 将请求透传到真实的uri
    }
}
