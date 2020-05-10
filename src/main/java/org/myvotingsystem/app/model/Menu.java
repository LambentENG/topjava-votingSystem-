package org.myvotingsystem.app.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.swing.text.View;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Menu.GET_ALL, query = "SELECT m FROM Menu m"),
        @NamedQuery(name = Menu.GET_ALL_BY_DATE, query = "SELECT m FROM Menu m WHERE m.date=:date"),
        @NamedQuery(name = Menu.GET_ALL_BY_DATE_AND_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.date=:date" +
                " AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Menu.DELETE_ALL, query = "DELETE FROM Menu m")
})
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    public static final String GET_ALL = "Menu.getAll";
    public static final String GET_ALL_BY_DATE = "Menu.getAllByDate";
    public static final String GET_ALL_BY_DATE_AND_RESTAURANT = "Menu.getAllByDateAndRestaurant";
    public static final String DELETE = "Menu.delete";
    public static final String DELETE_ALL = "Menu.deleteAll";

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotBlank
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private Set<Dish> dishes;

    public Menu() {
    }

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
