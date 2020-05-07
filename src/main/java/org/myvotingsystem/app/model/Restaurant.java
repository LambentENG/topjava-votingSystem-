package org.myvotingsystem.app.model;

public class Restaurant extends BaseEntity{

    public static final String GET_ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String DELETE_ALL = "Restaurant.deleteAll";

    private String name;

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
