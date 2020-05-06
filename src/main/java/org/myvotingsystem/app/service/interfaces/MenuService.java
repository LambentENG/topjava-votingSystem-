package org.myvotingsystem.app.service.interfaces;

import org.myvotingsystem.app.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {
    Menu create(Menu menu, int restaurantId);

    Menu get(int id, int restaurantId);
    List<Menu> getAll();
    List<Menu> getAllByDate(LocalDate date);
    List<Menu> getAllByDateAndRestaurantId(LocalDate date, int restaurantId);

    Menu update(Menu menu, int restaurantId);

    void delete(int id, int restaurantId);
    void deleteAll();
}
