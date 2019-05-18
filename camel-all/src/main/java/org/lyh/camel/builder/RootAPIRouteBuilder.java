package org.lyh.camel.builder;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestDefinition;
import org.lyh.camel.config.Produces;
import org.lyh.camel.processor.HttpRequestProcessor;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 22:16
 */
public class RootAPIRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("restlet").host("127.0.0.1").port(8088)
                .bindingMode(RestBindingMode.auto)
                .dataFormatProperty("prettyPrint", "true");


        // 仅仅只注册一个根API处理所有请求，自己判断Path来匹配API，这样当有API更新时，不需要重启camel context

        RestDefinition restDefinition = rest("");
        restDefinition.get().produces(Produces.APPLICATION_JSON)
                .route()
                .process(new HttpRequestProcessor())
                .end();

        restDefinition = rest("");
        restDefinition.post().produces(Produces.APPLICATION_JSON)
                .route()
                .process(new HttpRequestProcessor())
                .end();
    }
}
