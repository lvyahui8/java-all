package org.lyh.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.lyh.camel.bean.API;
import org.lyh.camel.config.Method;
import org.lyh.camel.data.APIManager;

import java.net.URI;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 22:21
 */
public class HttpRequestProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange);
        String uriStr = exchange.getIn().getHeader(Exchange.HTTP_URI).toString();
        URI uri = new URI(uriStr);
        String gwPath = StringUtils.strip(uri.getPath(),"/");
        if( ! APIManager.getApiDict().containsKey(gwPath)){
            // 无效API
        } else{
            API api = APIManager.getApiDict().get(gwPath);
            String reqMethod = exchange.getIn().getHeader(Exchange.HTTP_METHOD,String.class);
            if( ! api.getMethod().toString().equals(reqMethod)){
                // 错误的请求方式
                return;
            }
            Processor process = null;

            if(Method.GET.toString().equals(reqMethod)){
                process = new GetProcessor();
            } else if(Method.POST.toString().equals(reqMethod)) {
                process = new PostProcessor();
            }

            if(process != null){
                process.process(exchange);
            }
        }
    }
}
