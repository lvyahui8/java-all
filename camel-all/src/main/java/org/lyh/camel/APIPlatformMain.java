package org.lyh.camel;

import org.apache.camel.main.Main;
import org.lyh.camel.builder.APIRouteBuilder;
import org.lyh.camel.data.APIManager;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 15:04
 */
public class APIPlatformMain{
    public static void main(String[] args) throws Exception {
        final APIRouteBuilder routeBuilder = new APIRouteBuilder();
        final Main main = new Main();

        routeBuilder.setApis(APIManager.getApis());

        Thread apiProxyThread = new Thread(new Runnable() {
            public void run() {
                main.addRouteBuilder(routeBuilder);
                try {
                    main.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        apiProxyThread.start();

//        Thread.sleep(5000);
//
//        routeBuilder.deployApi(new API().setMethod(Method.GET).setPath("test").setUri("app/info"));
//        main.stop();
//        main.start();
    }
}
