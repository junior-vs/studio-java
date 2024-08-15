package io.openliberty.guide.inventory;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@RequestScoped
public class InventoryConfig {

  @Inject
  @ConfigProperty(name = "io_openliberty_guides_port_number")
  private int portNumber;

  private Provider<Boolean> inMaintenance;


  public int getPortNumber() {
    return portNumber;
  }


}