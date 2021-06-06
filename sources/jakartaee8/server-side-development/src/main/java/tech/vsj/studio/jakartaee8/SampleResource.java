package tech.vsj.studio.jakartaee8;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("sample")
public class SampleResource {

  private Logger logger = LoggerFactory.getLogger(SampleResource.class);

  @Inject
  @ConfigProperty(name = "message")
  private String message;

  @GET
  public Response message() {
    logger.info(message);
    return Response.ok(message).build();
  }

}
