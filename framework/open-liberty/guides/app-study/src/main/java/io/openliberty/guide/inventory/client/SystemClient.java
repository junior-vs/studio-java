package io.openliberty.guide.inventory.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Properties;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "systemClient", baseUri = "http://localhost:9080/system")
@RegisterProvider(UnknownUriExceptionMapper.class)
@Path("/properties")
public interface SystemClient extends AutoCloseable {


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Properties getProperties() throws UnknownUriException, ProcessingException;
}

