package org.myvotingsystem.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.DELETE_ALL, query = "DELETE FROM Restaurant r")

})
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity{

    public static final String GET_ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String DELETE_ALL = "Restaurant.deleteAll";

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Restaurant() {
    }

    public Restaurant(int id, String name) {
        super(id);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
