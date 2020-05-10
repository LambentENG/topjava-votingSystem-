package org.myvotingsystem.app.service.implementations;

import org.myvotingsystem.app.model.Restaurant;
import org.myvotingsystem.app.repository.interfaces.RestaurantRepo;
import org.myvotingsystem.app.service.interfaces.RestaurantService;
import org.myvotingsystem.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepo restaurantRepo;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "some restaurant is required");
        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepo.get(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepo.getAll();
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "some restaurant is required");
        return restaurantRepo.save(restaurant);
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(restaurantRepo.delete(id), id);
    }

    @Override
    public void deleteAll() {
        restaurantRepo.deleteAll();
    }
}
