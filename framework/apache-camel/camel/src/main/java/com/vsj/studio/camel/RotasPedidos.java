package com.vsj.studio.camel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vsj.studio.camel.model.Pedido;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class RotasPedidos extends RouteBuilder {
    @java.lang.Override
    public void configure() throws Exception {

        from("file:pedidos?delay=5s&noop=true")
                .log("${id} - ${body}")
                .to("file:saida");

    }
}
