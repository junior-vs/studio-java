package com.example.fullstack.project;

import com.example.fullstack.user.User;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "projects", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "user_id"})
})
public class Project extends PanacheEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private User user;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private ZonedDateTime created;

    @Version
    private int version;

    public Project() {
    }

    public Project(String name, User user, ZonedDateTime created, int version) {
        this.name = name;
        this.user = user;
        this.created = created;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", version=" + version +
                ", created=" + created +
                ", user=" + user +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
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

    public void setName(String name) {
        this.name = name;
    }
}
