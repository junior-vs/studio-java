package com.vj.studio.xpto.chainofresponsability.lab001;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ConcreteHandler. Checks a user's role.
 */
public class RoleCheckMiddleware extends Middleware{

Logger logger = LoggerFactory.getLogger(RoleCheckMiddleware.class);


    @Override
    public boolean check(String email, String password) {
    if(email.equals("admin@example.com")){
        logger.info("Hello, admin!");
        return true;
    }
        logger.info("Hello, user!");
        return checkNext(email, password);
    }
}
