package com.vsj.studio.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpConstants;

public class IntegracaoServidorError extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        from("direct:integra-servidor-error")
                .routeId("integracao-arquivo-servidor-error")
                .setHeader(HttpConstants.HTTP_METHOD, constant("POST"))
                .setHeader(HttpConstants.HTTP_URI, constant("{{urlApi}}"))
                .setHeader(HttpConstants.HTTP_PATH, constant("nf"))
                .setHeader(HttpConstants.CONTENT_TYPE).constant("application/xml")
                .log("enviando para http de erro")
                .to("http:servidor-error")
        ;



    }
}
