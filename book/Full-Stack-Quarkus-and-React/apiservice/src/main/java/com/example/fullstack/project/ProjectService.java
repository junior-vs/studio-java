package com.example.fullstack.project;

import com.example.fullstack.task.Task;
import com.example.fullstack.user.UserService;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

@ApplicationScoped
public class ProjectService {

    private final UserService userService;

    @Inject
    public ProjectService(UserService userService) {
        this.userService = userService;
    }

    @WithTransaction
    public Uni<Project> create(Project project) {
        return userService.getCurrentUser()
                .chain(user -> {
                    project.setUser(user);
                    return project.persistAndFlush();
                });
    }

    /**
     * Updates an existing project.
     * <p>
     * This Java method, `update`, updates a `Project` entity in the database.
     * Here's a step-by-step breakdown:
     * <p>
     * 1. It first finds the `Project` by its `id` using the `findById` method.
     * 2. If the project is found, it retrieves a database session using `Project.getSession()`.
     * 3. Finally, it merges the updated `project` entity into the session, effectively updating the project
     * in the database.
     * <p>
     * The `Uni` return type indicates that this operation is asynchronous.
     *
     * @param project the project to update
     * @return a Uni that completes with the updated project
     */

    public Uni<Project> update(Project project) {
        return findById(project.id)
                .chain(p -> Project.getSession())
                .chain(s -> s.merge(project));
    }

    /**
     * Deletes a project by its ID in a transactional manner.
     * <p>
     * <p>
     * This Java method, `delete`, is used to delete a project with a given `id`. Here's a step-by-step breakdown:
     * <p>
     * 1. It first finds the project by `id` using the `findById` method.
     * 2. If the project is found, it then updates all tasks associated with the project by setting the project
     * reference to `null` using `Task.update`.
     * 3. After updating the tasks, it then deletes the project itself using `p.delete`.
     * <p>
     * The `@WithTransaction` annotation ensures that all these operations are performed within a single database
     * transaction, meaning that if any part of the deletion process fails, the entire operation will be rolled back
     * and the database will remain in its original state.
     * <p>
     * *Source:** `src/main/java/com/example/fullstack/project/ProjectService.java:com.example.fullstack.project.ProjectService.delete`
     *
     * @param id the ID of the project to be deleted
     * @return a Uni that completes when the deletion is successful
     */

    @WithTransaction
    public Uni<Void> delete(long id) {
        return findById(id)
                .chain(p -> Task.update("project = null where project = ?1", p)
                        .chain(i -> p.delete()));
    }

    public Uni<List<Project>> listForUser() {
        return userService.getCurrentUser()
                .chain(user -> Project.find("user", user).list());
    }

    /**
     * Retrieves a project by its ID.
     *
     * <p>
     * This Java method, `findById`, is used to find a project by its ID.
     * <p>
     * Here's a step-by-step breakdown:
     * <p>
     * 1. It first retrieves the current user using `userService.getCurrentUser()`.
     * 2. Then, it finds the project by the given ID using `Project.findById(id)`.
     * 3. If the project is not found, it throws an `ObjectNotFoundException`.
     * 4. If the project is found, it checks if the current user is the same as the project's user. If not,
     * it throws an `UnauthorizedException`.
     * <p>
     * In essence, this method not only retrieves a project by ID but also enforces ownership-based access control.
     *
     * @param id the ID of the project to be retrieved
     * @return the project with the specified ID, or an exception if not found or unauthorized
     */

    public Uni<Project> findById(long id) {
        return userService.getCurrentUser()
                .chain(user -> Project.<Project>findById(id)
                        .onItem().ifNull().failWith(() -> new ObjectNotFoundException(id, "Project"))
                        .onItem().invoke(project -> {
                            if (!user.equals(project.getUser())) {
                                throw new UnauthorizedException("You are not allowed to update this project");
                            }
                        }));
    }
}
