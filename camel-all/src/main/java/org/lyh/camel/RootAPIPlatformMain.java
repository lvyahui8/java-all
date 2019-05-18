package org.lyh.camel;

import org.apache.camel.main.Main;
import org.lyh.camel.builder.RootAPIRouteBuilder;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 22:14
 */
public class RootAPIPlatformMain {
    public static void main(String[] args) {
        final RootAPIRouteBuilder routeBuilder = new RootAPIRouteBuilder();
        final Main main = new Main();
        main.addRouteBuilder(routeBuilder);
        Thread apiProxyThread = new Thread(new Runnable() {
            public void run() {
                try {
                    main.run();
                } catch (Exception e) {
                   //
                   e.printStackTrace();
                }
            }
        });
        apiProxyThread.start();
    }
}
