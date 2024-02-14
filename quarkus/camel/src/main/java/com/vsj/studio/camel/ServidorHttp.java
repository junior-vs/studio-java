package com.vsj.studio.camel;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.support.builder.Namespaces;
import static org.apache.camel.Exchange.CONTENT_TYPE;
public class ServidorHttp extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("rest:post:nf")
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
