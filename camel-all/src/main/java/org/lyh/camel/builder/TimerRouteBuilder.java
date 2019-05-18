package org.lyh.camel.builder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://saltnlight5.blogspot.ie/2013/08/getting-started-with-apache-camel-using.html
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/6 15:49
 */
public class TimerRouteBuilder extends RouteBuilder{
    static Logger LOG = LoggerFactory.getLogger(TimerRouteBuilder.class);
    public void configure() {
        from("timer://timer1?period=1000")
                .process(new Processor() {
                    public void process(Exchange msg) {
                        LOG.info("Processing {}", msg);
                    }
                });
    }
}
