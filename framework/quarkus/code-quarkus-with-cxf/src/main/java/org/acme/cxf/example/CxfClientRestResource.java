package org.acme.cxf.example;

import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.eap.quickstarts.wscalculator.calculator.CalculatorService;


@Path("/cxf/calculator-client")
public class CxfClientRestResource {

    @CXFClient("myCalculator")
    CalculatorService myCalculator;

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public int add(@QueryParam("a") int a, @QueryParam("b") int b) {
        return myCalculator.add(a, b);
    }

}