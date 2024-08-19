package org.acme.integration;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RestHelloWorldRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/helloWorld")
        .get()
            .route()
            .routeId("rest-hello-world")
            .setBody(constant("Hello World"))
            .log("Request responded with body: ${body}")
        .endRest();

    }
}
    
}
