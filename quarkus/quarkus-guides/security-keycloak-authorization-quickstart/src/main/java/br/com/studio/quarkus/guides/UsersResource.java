package br.com.studio.quarkus.guides;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.quarkus.security.identity.SecurityIdentity;

@Path("/api/users")
public class UsersResource {

  @Inject
  SecurityIdentity identity;

  @GET
  @Path("/me")
  public User me() {
    return new User(identity);
  }

  public static class User {

    private final String userName;

    User(SecurityIdentity identity) {
      this.userName = identity.getPrincipal().getName();
    }

    public String getUserName() {
      return userName;
    }
  }
}