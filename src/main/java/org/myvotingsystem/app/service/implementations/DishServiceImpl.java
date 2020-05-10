package org.myvotingsystem.app.service.implementations;

import org.myvotingsystem.app.model.Dish;
import org.myvotingsystem.app.repository.interfaces.DishRepo;
import org.myvotingsystem.app.service.interfaces.DishService;
import org.myvotingsystem.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private DishRepo dishRepo;

    @Autowired
    public DishServiceImpl(DishRepo dishRepo) {
        this.dishRepo = dishRepo;
    }

    @Override
    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "some dish is required");
        return dishRepo.save(dish, menuId);
    }

    @Override
    public Dish get(int id, int menuId) {
        return ValidationUtil.checkNotFoundWithId(dishRepo.get(id, menuId), id);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepo.getAll();
    }

    @Override
    public List<Dish> getAllMenuDishes(int menuId) {
        return dishRepo.getAllMenuDishes(menuId);
    }

    @Override
    public Dish update(Dish dish, int menuId) {
        Assert.notNull(dish, "some dish is required");
        return dishRepo.save(dish, menuId);
    }

    @Override
    public void delete(int id, int menuId) {
        ValidationUtil.checkNotFoundWithId(dishRepo.delete(id, menuId), id);
    }

    @Override
    public void deleteAllMenuDishes(int menuId) {
        dishRepo.deleteAllMenuDishes(menuId);
    }
}
