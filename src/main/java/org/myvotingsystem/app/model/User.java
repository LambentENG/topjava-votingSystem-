package org.myvotingsystem.app.model;

import java.util.Set;

public class User extends BaseEntity {

    private String name;
    private Set<Role> roles;
    private String password;
    private Vote vote;

    public User(int id, String name, Set<Role> roles, String password, Vote vote) {
        super(id);
        this.name = name;
        this.roles = roles;
        this.password = password;
        this.vote = vote;
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
