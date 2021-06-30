package tech.vsj.studio.jakartaee.healths;

import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

     
    @Override
    public HealthCheckResponse call() {
        if (isAcessible()){
            return HealthCheckResponse.up("I'm up and ready!");
        } else{
            return HealthCheckResponse.down("I'm up, but not ready...");
        }
    }
    
    private boolean isAcessible(){
        return new Random().nextBoolean();
    }

}
