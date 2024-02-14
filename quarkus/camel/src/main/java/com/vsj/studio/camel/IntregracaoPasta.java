package com.vsj.studio.camel;

import org.apache.camel.builder.RouteBuilder;

public class IntregracaoPasta extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .log("Enviado para servidor de erro")
                .to("direct:integra-servidor-error")
                .stop()
                .end();

        from("direct:integracao-pasta")
                .routeId("integracao-arquivo-pasta")
                .log("enviado arquivo ${file:name} para pasta")

                .to("file:{{diretorioSaida}}?fileName=${date:now:HHmmss}_${file:name}&noop=true").end();
        ;
    }
}
