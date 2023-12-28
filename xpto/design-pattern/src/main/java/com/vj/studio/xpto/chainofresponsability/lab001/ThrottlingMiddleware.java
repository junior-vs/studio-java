package com.vj.studio.xpto.chainofresponsability.lab001;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ConcreteHandler. Checks whether there are too many failed login requests.
 */
public class ThrottlingMiddleware extends Middleware {

    Logger logger = LoggerFactory.getLogger(ThrottlingMiddleware.class);

    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * Please, not that checkNext() call can be inserted both in the beginning
     * of this method and in the end.
     * <p>
     * This gives much more flexibility than a simple loop over all middleware
     * objects. For instance, an element of a chain can change the order of
     * checks by running its check after all other checks.
     */
    @Override
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {

            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;
        if (request>requestPerMinute){
            logger.info("Request limite exceeded");
            Thread.currentThread().stop();
        }
        return checkNext(email, password);


    }
}
