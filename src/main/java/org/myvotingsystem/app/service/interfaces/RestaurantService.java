package org.myvotingsystem.app.service.interfaces;

import org.myvotingsystem.app.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);

    Restaurant get(int id);
    List<Restaurant> getAll();

    Restaurant update(Restaurant restaurant);

    void delete(int id);
    void deleteAll();
}
