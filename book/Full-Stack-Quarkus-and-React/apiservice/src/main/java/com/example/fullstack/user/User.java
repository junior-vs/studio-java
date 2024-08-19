package com.example.fullstack.user;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private ZonedDateTime created;

    @Version
    private int version;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    public List<String> roles;

    public User() {
    }

    public User(String name, String password, ZonedDateTime created, int version, List<String> roles) {
        this.name = name;
        this.password = BcryptUtil.bcryptHash(password);
        this.created = created;
        this.version = version;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", version=" + version +
                ", roles=" + roles +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public int getVersion() {
        return version;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password);
        // TODO: Rehash password;
    }

    public boolean comparePassword(String password) {
        return BcryptUtil.matches(password, this.getPassword());
    }

    public void setName(String name) {
        this.name = name;
    }
}
