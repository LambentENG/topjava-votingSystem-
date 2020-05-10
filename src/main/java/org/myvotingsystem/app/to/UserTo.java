package org.myvotingsystem.app.to;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UserTo {
    @NotBlank
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 5, max = 32)
    private String password;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + getId() +
                ", name='" + name +
                '}';
    }
}
