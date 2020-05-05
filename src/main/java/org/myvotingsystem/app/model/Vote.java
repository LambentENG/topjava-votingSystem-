package org.myvotingsystem.app.model;

import java.time.LocalDate;

public class Vote extends BaseEntity {

    public static final String GET_ALL = "Vote.getAll";
    public static final String GET_ALL_BY_DATE = "Vote.getAllByDate";
    public static final String GET_BY_USER_AND_DATE = "Vote.getByUserAndDate";
    public static final String DELETE = "Vote.delete";
    public static final String DELETE_ALL = "Vote.deleteAll";

    private User user;
    private Restaurant restaurant;
    private LocalDate date;

    public Vote(int id, User user, Restaurant restaurant, LocalDate date) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user +
                ", restaurant=" + restaurant +
                ", date=" + date +
                '}';
    }
}
