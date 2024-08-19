package com.example.fullstack.task;

import com.example.fullstack.user.User;
import com.example.fullstack.project.Project;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tasks")
public class Task extends PanacheEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private Integer priority;

    @ManyToOne(optional = false)
    private User user;

    private ZonedDateTime complete;

    @ManyToOne
    private Project project;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private ZonedDateTime created;

    @Version
    private int version;


    public Task() {
    }

    public Task(String title, String description, Integer priority, User user, ZonedDateTime complete, Project project, ZonedDateTime created, int version) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.user = user;
        this.complete = complete;
        this.project = project;
        this.created = created;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", user=" + user +
                ", complete=" + complete +
                ", project=" + project +
                ", created=" + created +
                ", version=" + version +
                ", id=" + id +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

    public User getUser() {
        return user;
    }

    public ZonedDateTime getComplete() {
        return complete;
    }

    public Project getProject() {
        return project;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public int getVersion() {
        return version;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setComplete(ZonedDateTime complete) {
        this.complete = complete;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
