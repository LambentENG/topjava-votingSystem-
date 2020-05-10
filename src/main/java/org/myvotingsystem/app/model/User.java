package org.myvotingsystem.app.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;


@NamedQueries({
        @NamedQuery(name = User.GET_BY_NAME, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.name=:name"),
        @NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles"),
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id")
})
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public static final String GET_BY_NAME = "User.getByName";
    public static final String GET_ALL = "User.getAll";
    public static final String DELETE = "User.delete";

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @NotBlank
    @Length(min = 5)
    @Column(name = "password", nullable = false)
    private String password;
    private Vote vote;

    public User() {
    }

    public User(Integer id, String name, Set<Role> roles, String password, Vote vote) {
        super(id);
        this.name = name;
        this.roles = roles;
        this.password = password;
        this.vote = vote;
    }

    public User(Integer id, String name, String password, Role roles) {
        super(id);
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name +
                ", roles=" + roles +
                ", password='" + password +
                ", vote=" + vote +
                '}';
    }
}
