package org.myvotingsystem.app.service.implementations;

import org.myvotingsystem.app.model.Dish;
import org.myvotingsystem.app.model.Menu;
import org.myvotingsystem.app.repository.interfaces.DishRepo;
import org.myvotingsystem.app.repository.interfaces.MenuRepo;
import org.myvotingsystem.app.service.interfaces.MenuService;
import org.myvotingsystem.app.util.ValidationUtil;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class MenuServiceImpl implements MenuService {

    private MenuRepo menuRepo;
    private DishRepo dishRepo;

    public MenuServiceImpl(MenuRepo menuRepo, DishRepo dishRepo) {
        this.menuRepo = menuRepo;
        this.dishRepo = dishRepo;
    }

    @Override
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "some menu is required");

        Menu created = menuRepo.save(menu, restaurantId);

        Set<Dish> menuDishes = created.getDishes();
        if (menuDishes != null) {
            menuDishes.forEach(dish -> {
                dish.setId(null);
                dishRepo.save(dish, created.getId());
            });
        }

        return created;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return ValidationUtil.checkNotFoundWithId(menuRepo.get(id, restaurantId), id);
    }

    @Override
    public List<Menu> getAll() {
        return menuRepo.getAll();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return menuRepo.getAllByDate(date);
    }

    @Override
    public List<Menu> getAllByDateAndRestaurantId(LocalDate date, int restaurantId) {
        return menuRepo.getAllByDate(date, restaurantId);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "some menu is required");

        Set<Dish> oldMenuDishes = get(menu.getId(), restaurantId).getDishes();
        if (oldMenuDishes != null) {
            oldMenuDishes.forEach(dish -> dishRepo.delete(dish.getId(), menu.getId()));
        }

        Set<Dish> newMenuDishes = menu.getDishes();
        if (newMenuDishes != null) {
            newMenuDishes.forEach(dish -> {
                dish.setId(null);
                dishRepo.save(dish, menu.getId());
            });
        }

        return menuRepo.save(menu, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) {
        ValidationUtil.checkNotFoundWithId(menuRepo.delete(id, restaurantId), id);
    }

    @Override
    public void deleteAll() {
        menuRepo.deleteAll();
    }
}
