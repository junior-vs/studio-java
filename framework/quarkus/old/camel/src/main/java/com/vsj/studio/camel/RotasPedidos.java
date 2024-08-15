package com.vsj.studio.camel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vsj.studio.camel.model.Pedido;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpConstants;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class RotasPedidos extends RouteBuilder {
    private final String CONFIRMADO = "CONFIRMADO";
    private final String PENDENTE = "PENDENTE";

    @java.lang.Override
    public void configure() throws Exception {

        from("file:{{diretorioEntrada}}?delay=5s")

                .routeId("integracao-arquivo")
                .setProperty("STATUS", xpath("{{xpathStatus}}"))
                .setProperty("PEDIDO_ID", xpath("{{xpathIdPedido}}"))
                .log("processando ${id} - ${file:name}")
                .choice()
                    .when(exchangeProperty("STATUS").isEqualTo(CONFIRMADO)).to("direct:integra-servidor")
                    .when(exchangeProperty("STATUS").isEqualTo(PENDENTE)).to("direct:integracao-pasta")
                    .otherwise().log("Processo não integrado")
                .endChoice()
                .end();





    }
}
