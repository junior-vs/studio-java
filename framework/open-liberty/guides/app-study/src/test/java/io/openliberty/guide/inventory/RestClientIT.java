package io.openliberty.guide.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestClientIT {

  private static String port;

  private Client client;

  private final String INVENTORY_SYSTEMS = "inventory/systems";

  @BeforeAll
  public static void oneTimeSetup() {
    port = System.getProperty("http.port");
  }

  @BeforeEach
  public void setup() {
    client = ClientBuilder.newClient();
  }

  @AfterEach
  public void teardown() {
    client.close();
  }

  @Test
  public void testSuite() {

    this.testRestClientBuilder();
  }

  @Test
   public void testDefaultLocalhost() {
    String hostname = "localhost";

    String url = "http://localhost:" + port + "/" + INVENTORY_SYSTEMS + "/" + hostname;

    JsonObject obj = fetchProperties(url);

    assertEquals(System.getProperty("os.name"), obj.getString("os.name"),
        "The system property for the local and remote JVM should match");
  }

  public void testRestClientBuilder() {
    String hostname = null;
    try {
      hostname = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      System.err.println("Unknown Host.");
    }

  }

  private JsonObject fetchProperties(String url) {
    WebTarget target = client.target(url);
    Response response = target.request().get();

    assertEquals(200, response.getStatus(), "Incorrect response code from " + url);

    JsonObject obj = response.readEntity(JsonObject.class);
    response.close();
    return obj;
  }


}
