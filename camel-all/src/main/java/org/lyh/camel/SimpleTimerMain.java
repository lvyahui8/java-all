package org.lyh.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.lyh.camel.builder.TimerRouteBuilder;

public class SimpleTimerMain extends Main {

    public static void main(String[] args) throws Exception {
        SimpleTimerMain main = new SimpleTimerMain();
        main.enableHangupSupport();
        main.addRouteBuilder(createRouteBuilder());
        main.run(args);
    }

    static RouteBuilder createRouteBuilder() {
        return new TimerRouteBuilder();
    }
}