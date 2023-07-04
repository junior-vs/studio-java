package com.vj.studio.xpto.chainofresponsability.lab001;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Server {
    Logger logger = LoggerFactory.getLogger(Server.class);

    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) {
            logger.info("Authorization have benn successful!");
            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);

    }

    public boolean isValidPassword(String email, String password){
        return users.get(email).equals(password);
    }

    }
