package org.myvotingsystem.app.model;

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
