package com.vsj.studio.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpConstants;

public class IntegracaoServidor extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        from("direct:integra-servidor")
                .split()
                    .xpath("/pedido/itens/item")
                    .log("${body}")
                .filter()
                    .xpath("/item/formato[text()='EBOOK']")
                .routeId("integracao-arquivo-servidor")
                .setHeader(HttpConstants.HTTP_METHOD, constant("POST"))
                .setHeader(HttpConstants.HTTP_URI, constant("{{urlApi}}"))
                .setHeader(HttpConstants.HTTP_PATH, constant("nf"))
                .setHeader(HttpConstants.CONTENT_TYPE).constant("application/xml")
                .log("enviando para http:")
                .to("http:servidor")
        ;



    }
}
