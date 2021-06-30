package tech.vsj.studio.jakartaee.resources;

import java.util.Collections;
import javax.enterprise.context.ApplicationScoped;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;


@Path("/metricas")
@ApplicationScoped
public class MetricsResouces {

    @Timed(
        name = "getResouceTime",
        description = "get Resouce Time"
    )
    @Path("/timed")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    private static final JsonBuilderFactory builder =
            Json.createBuilderFactory(Collections.emptyMap());


    public JsonObject getResourceTimed() {
        String response = getResource();
        return Json.createObjectBuilder().add("message", response).build();
    }

    @Metered(name = "getResourceMetered")
    @Path("/metered")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getResourceMetered() {
        String response = getResource();

        return JSON.createObjectBuilder()
                .add("message", response)
                .build();
    }


    private String getResource() {

        Client client = null;
        String response;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://eldermoraes.com/book");
            response =
                    target.request().header("Content-Type", "application/json").get(String.class);
        } finally {
            if (client != null)
                client.close();
        }
        return response;
    }

}
