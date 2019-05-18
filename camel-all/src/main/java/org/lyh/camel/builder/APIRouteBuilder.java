package org.lyh.camel.builder;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestDefinition;
import org.lyh.camel.bean.API;
import org.lyh.camel.config.Method;
import org.lyh.camel.processor.GetProcessor;
import org.lyh.camel.processor.PostProcessor;

import java.util.List;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 15:01
 */
public class APIRouteBuilder extends RouteBuilder {

    private List<API> apis ;

    public void setApis(List<API> apis) {
        this.apis = apis;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("restlet").host("127.0.0.1").port(8088)
                .bindingMode(RestBindingMode.auto)
                .dataFormatProperty("prettyPrint", "true");
        deployApis();
    }

    private void deployApis() {
        if(apis != null){
            for (API api : apis){
                deployApi(api);
            }
        }
    }

    public void deployApi(API api){
        RestDefinition restDefinition = rest(api.getPath());
        if(Method.POST.equals(api.getMethod())){
            restDefinition.post(api.getUri()).produces(api.getProduces())
                    .route()
                    .process(new PostProcessor())
                    .end();
        } else {
            restDefinition.get(api.getUri()).produces(api.getProduces())
                    .route()
                    .process(new GetProcessor())
                    .end();
        }
    }
}
