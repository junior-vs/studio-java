package com.example.fullstack.user;

import com.example.fullstack.project.Project;
import com.example.fullstack.task.Task;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

@ApplicationScoped
public class UserService {

    private JsonWebToken jwt;


    @Inject
    public UserService(JsonWebToken jwt) {
        this.jwt = jwt;
    }

    @WithTransaction
    public Uni<User> create(User user) {
        return user.persistAndFlush();
    }

    public Uni<User> findById(long id) {
        return User.<User>findById(id)
                .onItem().ifNull().failWith(() -> new ObjectNotFoundException(id, "User"));
    }

    public Uni<User> findByName(String name) {
        return User.find("name", name)
                .firstResult();
    }

    public Uni<List<User>> list() {
        return User.listAll();
    }

    /**
     * Deletes a user by the given id. This operation is transactional and also deletes the tasks and projects
     * associated with the user.
     *
     * <p>
     * <p>
     * This Java method, delete, is used to delete a user with a given id.
     * <p>
     * Here's a step-by-step breakdown:
     * <p>
     * It first finds the user by id using the findById method.
     * If the user is found, it then deletes all tasks and projects associated with the user's id using Task.delete
     * and Project.delete.
     * After deleting the tasks and projects, it then deletes the user itself.
     * The @WithTransaction annotation ensures that all these operations are performed within a single database
     * transaction, meaning that if any part of the deletion process fails, the entire operation will be rolled back and
     * the database will remain in its original state.
     *
     * @param id the id of the user to be deleted
     * @return a Uni that completes when the deletion is successful
     **/
    @WithTransaction
    public Uni<Void> delete(long id) {
        return findById(id)
                .chain(u -> Uni.combine().all().unis(
                                Task.delete("userId", id),
                                Project.delete("userId", id))
                        .asTuple().chain(t -> u.delete()));
    }

    @WithTransaction
    public Uni<User> update(User user) {
        return findById(user.id)
                .chain(u -> User.getSession())
                .chain(s -> s.merge(user));
    }

    public Uni<User> getCurrentUser() {
        return findByName(jwt.getName());
    }

    public static boolean matches(User user, String password) {
       return user.comparePassword(password);
    }

    @WithTransaction
    public Uni<User> changePassword(String currentPassword, String newPassword) {
        return getCurrentUser()
                .chain(u -> {
                    if (!matches(u, currentPassword)) {
                        throw new ClientErrorException("Current password does not match", Response.Status.CONFLICT);
                    }
                    u.setPassword(newPassword);
                    return u.persistAndFlush();
                });
    }

}
