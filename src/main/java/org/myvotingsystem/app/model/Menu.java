package org.myvotingsystem.app.model;

import java.time.LocalDate;
import java.util.Set;

public class Menu extends BaseEntity {

    private Restaurant restaurant;
    private LocalDate date;
    private Set<Dish> dishes;

    public Menu(int id, Restaurant restaurant, LocalDate date, Set<Dish> dishes) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                ", id=" + id +
                "restaurant=" + restaurant +
                ", date=" + date +
                ", dishes=" + dishes +
                '}';
    }
}
