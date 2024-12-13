package org.studio.reactive.routes;

import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyDeclarativeRoutes {


    /**
     * A anotação @Route indica que o método é uma rota reativa. Novamente, por padrão, o código contido no método
     * não deve ser bloqueado.
     * O método obtém um RoutingContext como parâmetro. No RoutingContext você pode recuperar a solicitação
     * HTTP (usando request()) e escrever a resposta usando response().end(…)
     * @param routingContext
     */
    @Route(path = "/", methods = Route.HttpMethod.GET)
    public void handle(RoutingContext routingContext) {
        routingContext.response().end("hello");
    }

    @Route(path = "/hello", methods = Route.HttpMethod.GET)//The methods - the HTTP verbs triggering the route such as GET, POST…
    public void greetings(RoutingContext routingContext) {
        String name = routingContext.request().getParam("name");
        if (name == null) {
            name = "world";

        }
        routingContext.response().end("hello" + name);
    }

    /**
     * Se o método anotado não retornar void, os argumentos serão opcionais.
     * @return
     */
    @Route(path = "/world")//The path - for routing by path, using the Vert.x Web format
    @Route(path = "/first", produces = "text/html") //Os tipos MIME produzidos e consumidos usando produz e consome
    @Route(path = "/second")
    @Route(regex = "/.*foo")//The regex - for routing with regular expressions,
    String helloWorld(){
        return "Hello World!";
    }

    /**
     * RoutingExchange é um wrapper conveniente de RoutingContext que fornece alguns métodos úteis
     * O RoutingExchange é usado para recuperar o nome do parâmetro de consulta da solicitação.
     * @param ex
     */
    @Route(path = "/greetings", methods = Route.HttpMethod.GET)
    void greetingsQueryParam(RoutingExchange ex) {
        ex.ok("hello " + ex.getParam("name").orElse("world"));
    }

    /**
     * O caminho define um nome de parâmetro que pode ser injetado dentro dos parâmetros do método usando
     * a anotação @Param
     * @param name
     * @param ex
     */
    @Route(path = "/greetings/:name", methods = Route.HttpMethod.GET)
    void greetingsPathParam(@Param String name, RoutingExchange ex) {
        ex.ok("hello " + name);
    }



}
