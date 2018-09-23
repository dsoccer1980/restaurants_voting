package ru.dsoccer1980.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate registered = LocalDate.now();

    @Column(name = "isadmin", nullable = false, columnDefinition = "bool default false")
    @NotNull
    private boolean isAdmin = false;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.registered, u.isAdmin());
    }

    public User(String name, @Email @NotBlank @Size(max = 100) String email, @NotBlank @Size(min = 5, max = 64) String password) {
        this(null, name, email, password, LocalDate.now(), false);
    }

    public User(Integer id, String name, @Email @NotBlank @Size(max = 100) String email, @NotBlank @Size(min = 5, max = 64) String password) {
        this(id, name, email, password, LocalDate.now(), false);
    }

    public User(Integer id, String name, @Email @NotBlank @Size(max = 100) String email,
                @NotBlank @Size(min = 5, max = 64) String password, @NotNull LocalDate registered,
                boolean isAdmin) {
        super(id, name);
        Objects.requireNonNull(email, "email cannot be null");
        Objects.requireNonNull(password, "password cannot be null");
        Objects.requireNonNull(registered, "registered cannot be null");
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", email=" + email +
                '}';
    }

    public String getRole() {
        return (isAdmin() ? "ROLE_ADMIN" : "ROLE_USER");
    }
}