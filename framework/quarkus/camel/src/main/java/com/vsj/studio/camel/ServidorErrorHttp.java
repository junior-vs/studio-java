package com.vsj.studio.camel;

import org.apache.camel.builder.RouteBuilder;

public class ServidorErrorHttp extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("rest:post:error")
                .log("${body}")
                .end();
        /*restConfiguration().host("0.0.0.0.").port("8080");

        from("rest:post")
                .routeId("servidor-http")
                .setProperty("STATUS", xpath("{{xpathStatus}}"))
                .setProperty("PEDIDO_ID", xpath("{{xpathIdPedido}}"))
                .log("MSG - Status: ${xpathStatus.STATUS}; Pedido id: ${exchangeProperty.PEDIDO_ID};")
                .setHeader(CONTENT_TYPE, constant("application/xml"));
        */        //.setBody(constant("{\"status\":\"recebia\"}"));
    }
}
