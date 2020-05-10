package org.myvotingsystem.app.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d"),
        @NamedQuery(name = Dish.GET_ALL_MENU_DISHES, query = "SELECT d FROM Dish d WHERE d.menu.id=:menuId"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.menu.id=:menuId"),
        @NamedQuery(name = Dish.DELETE_ALL_MENU_DISHES, query = "DELETE FROM Dish d WHERE d.menu.id=:menuId")

})
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    public static final String GET_ALL = "Dish.getAll";
    public static final String GET_ALL_MENU_DISHES = "Dish.getAllMenuDishes";
    public static final String DELETE = "Dish.delete";
    public static final String DELETE_ALL_MENU_DISHES = "Dish.deleteAllMenuDishes";

    private String name;
    private double price;
    private Menu menu;

    public Dish(int id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
        this.menu = null;
    }

    public String getName() {
        return name;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                '}';
    }

    public Menu getMenu() {
        return menu;
    }
}
