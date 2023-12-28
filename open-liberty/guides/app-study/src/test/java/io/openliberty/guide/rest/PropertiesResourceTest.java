package io.openliberty.guide.rest;


import org.junit.jupiter.api.Assertions;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.Properties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PropertiesResourceTest {


  private static final Jsonb JSONB = JsonbBuilder.create();
  private static String port;
  private static String baseUrl;



  @BeforeAll
  public static void oneTimeSetup() {
    port = System.getProperty("http.port");
    baseUrl = "http://localhost:" + port + "/";
  }


  @Test
  public void testGetProperties() {

    Client client = ClientBuilder.newClient();

    WebTarget target = client.target(baseUrl + "system/properties");
    Response response = target.request().get();

    Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(),
        "Incorrect response code from " + baseUrl);

    String json = response.readEntity(String.class);
    Properties sysProps = JSONB.fromJson(json, Properties.class);

    Assertions.assertEquals(System.getProperty("os.name"), sysProps.getProperty("os.name"),
        "The system property for the local and remote JVM should match");
    response.close();
    client.close();

  }

}