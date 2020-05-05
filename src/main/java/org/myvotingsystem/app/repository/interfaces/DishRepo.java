package org.myvotingsystem.app.repository.interfaces;

import org.myvotingsystem.app.model.Dish;

import java.util.List;

public interface DishRepo {
    Dish get(int id, int menuId);
    List<Dish> getAll();
    List<Dish> getAllMenuDishes(int menuId);

    Dish save(Dish dish, int menuId);

    boolean delete(int id, int menuId);
    void deleteAllMenuDishes(int menuId);
}
