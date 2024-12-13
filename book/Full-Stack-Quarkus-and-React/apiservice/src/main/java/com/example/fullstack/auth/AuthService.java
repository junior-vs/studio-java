package com.example.fullstack.auth;

import com.example.fullstack.user.UserService;
import io.quarkus.security.AuthenticationFailedException;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Duration;
import java.util.HashSet;

@ApplicationScoped
public class AuthService {

    private final String issuer;
    private final UserService userService;

    @Inject
    public AuthService(
            @ConfigProperty(name = "mp.jwt.verify.issuer") String issuer, UserService userService) {
        this.issuer = issuer;
        this.userService = userService;
    }

    /**
     * Authenticates a user based on the provided AuthRequest.
     * <p>
     * This Java method, `authenticate`, takes an `AuthRequest` object and attempts to authenticate the user.
     * It does this by:
     * <p>
     * 1. Finding a user with the matching name using `userService.findByName`.
     * 2. If a user is found and their password matches the provided password, it generates a JWT (JSON Web Token) with
     * the user's name, roles, and a 1-hour expiration time.
     * 3. If the user is not found or the password does not match, it throws an `AuthenticationFailedException`.
     * <p>
     * The method returns a `Uni` (a type of asynchronous result) containing the JWT as a string if authentication is
     * successful.
     *
     * @param authRequest the authentication request containing user credentials
     * @return a JWT token if authentication is successful, otherwise throws an AuthenticationFailedException
     */
    public Uni<String> authenticate(AuthRequest authRequest) {
        return userService.findByName(authRequest.name())
                .onItem()
                .transform(user -> {
                    if (user == null || !UserService.matches(user, authRequest.password())) {
                        throw new AuthenticationFailedException("Invalid credentials");
                    }
                    return Jwt.issuer(issuer)
                            .upn(user.getName())
                            .groups(new HashSet<>(user.roles))
                            .expiresIn(Duration.ofHours(1L))
                            .sign();
                });
    }
}