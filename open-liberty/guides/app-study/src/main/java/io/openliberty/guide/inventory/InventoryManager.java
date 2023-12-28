package io.openliberty.guide.inventory;

import io.openliberty.guide.inventory.client.SystemClient;
import io.openliberty.guide.inventory.client.UnknownUriException;
import io.openliberty.guide.inventory.client.UnknownUriExceptionMapper;
import io.openliberty.guide.inventory.model.InventoryList;
import io.openliberty.guide.inventory.model.SystemData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ProcessingException;
import java.net.ConnectException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.apache.commons.lang3.exception.ExceptionUtils;

@ApplicationScoped
public class InventoryManager {


  private List<SystemData> systems = Collections.synchronizedList(
      new ArrayList<SystemData>());

  @Inject
  @ConfigProperty(name = "default.http.port")
  String DEFAULT_PORT;

  @Inject
  @RestClient
  private SystemClient defaultRestClient;

  public Properties get(String hostname) {
    Properties properties = null;
    if (hostname.equals("localhost")) {
      properties = getPropertiesWithDefaultHostName();
    } else {
      properties = getPropertiesWithGivenHostName(hostname);
    }

    return properties;
  }

  private Properties getPropertiesWithGivenHostName(String hostname) {
    String customURIString = "http://" + hostname + ":" + DEFAULT_PORT + "/system";
    URI customURI = null;
    try {
      customURI = URI.create(customURIString);
      SystemClient customRestClient = RestClientBuilder.newBuilder()
          .baseUri(customURI)
          .register(UnknownUriExceptionMapper.class)
          .build(SystemClient.class);
      return customRestClient.getProperties();
    } catch (ProcessingException ex) {
      handleProcessingException(ex);
    } catch (UnknownUriException e) {
      System.err.println("The given URI is unreachable.");
    }
    return null;
  }

  private void handleProcessingException(ProcessingException ex) {
    Throwable rootEx = ExceptionUtils.getRootCause(ex);
    if (rootEx != null && (rootEx instanceof UnknownHostException
        || rootEx instanceof ConnectException)) {
      System.err.println("The specified host is unknown.");
    } else {
      throw ex;
    }
  }

  private Properties getPropertiesWithDefaultHostName() {
    try {
      return defaultRestClient.getProperties();
    } catch (UnknownUriException e) {
      System.err.println("The given URI is not formatted correctly.");
    } catch (ProcessingException ex) {
      handleProcessingException(ex);
    }
    return null;
  }

  public void add(String hostname, Properties systemProps) {
    Properties props = new Properties();
    props.setProperty("os.name", systemProps.getProperty("os.name"));
    props.setProperty("user.name", systemProps.getProperty("user.name"));

    SystemData host = new SystemData(hostname, props);
    if (!systems.contains(host)) {
      systems.add(host);
    }
  }

  public InventoryList list() {
    return new InventoryList(systems);
  }

}
