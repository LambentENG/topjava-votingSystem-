package org.myvotingsystem.app.service.interfaces;

import org.myvotingsystem.app.model.Dish;

import java.util.List;

public interface DishService {
    Dish create(Dish dish, int menuId);

    Dish get(int id, int menuId);
    List<Dish> getAll();
    List<Dish> getAllMenuDishes(int menuId);

    Dish update(Dish dish, int menuId);

    void delete(int id, int menuId);
    void deleteAllMenuDishes(int menuId);
}
