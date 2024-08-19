package com.example.fullstack.infra.exceptions;


import io.vertx.pgclient.PgException;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleObjectStateException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Objects;
import java.util.Optional;

@Provider
public class RestExceptionHandler implements ExceptionMapper<HibernateException> {

    private static final String PG_UNIQUE_VIOLATION_ERROR = "23505";

    /**
     * This Java method, `toResponse`, is part of an exception handler that maps Hibernate exceptions to
     * HTTP responses.
     * <p>
     * It checks the type of Hibernate exception and returns a corresponding HTTP response:
     * <p>
     * - If the exception is an `ObjectNotFoundException`, it returns a 404 (NOT FOUND) response with the
     * exception message.
     * - If the exception is a `StaleObjectStateException` or a PostgreSQL unique violation error, it
     * returns a 409 (CONFLICT) response.
     * - For any other Hibernate exception, it returns a 400 (BAD REQUEST) response with the exception message.
     * Maps a HibernateException to a Response object with a corresponding HTTP status code.
     *
     * @param exception the HibernateException to be mapped
     * @return a Response object with a HTTP status code and an optional error message
     */
    @Override
    public Response toResponse(HibernateException exception) {
        if (hasExceptionInChain(exception, ObjectNotFoundException.class)) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        }
        if (hasExceptionInChain(exception, StaleObjectStateException.class)
                || hasPostgresErrorCode(exception, PG_UNIQUE_VIOLATION_ERROR)) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("\"" + exception.getMessage() + "\"")
                .build();
    }

    /**
     * Checks if a Throwable contains an exception of the specified class in its causal chain.
     * <p>
     * <p>
     * This Java method checks if a given `Throwable` (e.g., an exception) has a specific type of exception in its
     * causal chain. It returns `true` if the exception is found, and `false` otherwise.
     * <p>
     * The method uses another method, `getExceptionInChain`, to perform the actual search. If `getExceptionInChain`
     * returns an empty `Optional`, `hasExceptionInChain` returns `false`; otherwise, it returns `true`.
     *
     * @param throwable      the Throwable to check
     * @param exceptionClass the class of the exception to look for
     * @return true if the Throwable contains an exception of the specified class, false otherwise
     */
    private static boolean hasExceptionInChain(Throwable throwable, Class<? extends Throwable> exceptionClass) {
        return getExceptionInChain(throwable, exceptionClass).isPresent();
    }

    /**
     * Checks if a Throwable contains a PostgreSQL exception with the specified error code in its causal chain.
     * <p>
     * <p>
     * <p>
     * This Java method checks if a given `Throwable` (e.g., an exception) contains a PostgreSQL exception with a
     * specific error code in its causal chain.
     * <p>
     * Here's a breakdown:
     * <p>
     * `getExceptionInChain` searches for a `PgException` (a PostgreSQL exception) in the causal chain of the given
     * `Throwable`.
     * `filter` narrows down the search to only `PgException` instances with a code matching the specified `code`.
     * `isPresent` returns `true` if a matching `PgException` is found, and `false` otherwise.
     * <p>
     * In summary, this method checks if a PostgreSQL exception with a specific error code is present in the exception
     * chain.
     *
     * @param throwable the Throwable to check
     * @param code      the PostgreSQL error code to look for
     * @return true if the Throwable contains a PostgreSQL exception with the specified error code, false otherwise
     */

    private static boolean hasPostgresErrorCode(Throwable throwable, String code) {
        return getExceptionInChain(throwable, PgException.class)
                .filter(ex -> Objects.equals(ex.getCode(), code))
                .isPresent();
    }

    /**
     * Retrieves the first occurrence of an exception of the specified class in the causal chain of the given throwable.
     * <p>
     * This Java method, `getExceptionInChain`, searches for a specific type of exception (`exceptionClass`) in the
     * causal chain of a given `Throwable` (`throwable`). It returns an `Optional` containing the first matching
     * exception if found, or an empty `Optional` if not.
     *
     * @param throwable      the throwable to search for an exception in its causal chain
     * @param exceptionClass the class of the exception to look for
     * @param <T>            the type of the exception to search for
     * @return an optional containing the first occurrence of the specified exception in the causal chain,
     * or an empty optional if no such exception is found
     */

    private static <T extends Throwable> Optional<T> getExceptionInChain(Throwable throwable, Class<T> exceptionClass) {
        while (throwable != null) {
            if (exceptionClass.isInstance(throwable)) {
                return Optional.of((T) throwable);
            }
            throwable = throwable.getCause();
        }
        return Optional.empty();
    }
}

