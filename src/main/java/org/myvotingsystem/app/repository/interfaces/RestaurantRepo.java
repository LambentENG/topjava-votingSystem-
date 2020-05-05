package org.myvotingsystem.app.repository.interfaces;

import org.myvotingsystem.app.model.Restaurant;

import java.util.List;

public interface RestaurantRepo {
    Restaurant get(int id);
    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);
    void deleteAll();
}
