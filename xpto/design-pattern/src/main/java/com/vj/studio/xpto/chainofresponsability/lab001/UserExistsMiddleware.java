package com.vj.studio.xpto.chainofresponsability.lab001;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ConcreteHandler. Checks whether a user with the given credentials exists.
 */
public class UserExistsMiddleware extends Middleware {

    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }
    Logger logger = LoggerFactory.getLogger(UserExistsMiddleware.class);

    @Override
    public boolean check(String email, String password) {

        if (!server.hasEmail(email)) {
            logger.info("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            logger.info("Wrong password");
            return false;
        }
        return checkNext(email, password);
    }
}
