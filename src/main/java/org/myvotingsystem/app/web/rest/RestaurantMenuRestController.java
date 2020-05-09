package org.myvotingsystem.app.web.rest;

import org.myvotingsystem.app.model.Menu;
import org.myvotingsystem.app.model.Restaurant;
import org.myvotingsystem.app.service.interfaces.MenuService;
import org.myvotingsystem.app.service.interfaces.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.View;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.myvotingsystem.app.web.rest.RestaurantMenuRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(REST_URL)
public class RestaurantMenuRestController {
    private static final Logger LOG = getLogger(RestaurantMenuRestController.class);
    static final String REST_URL = "/rest/admin/restaurants";
    private RestaurantService restaurantService;
    private MenuService menuService;

    @Autowired
    public RestaurantMenuRestController(RestaurantService restaurantService, MenuService menuService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllRestaurants() {
        LOG.info("getAll restaurants");
        return restaurantService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurant(@PathVariable Integer id) {
        LOG.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    @GetMapping(value = "/{restaurantId}/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllMenus(@PathVariable Integer restaurantId,
                                  @RequestParam(value = "date")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("getAll menus date {} for restaurant {}", date, restaurantId);
        return menuService.getAllByDateAndRestaurantId(date, restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/menus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getMenu(@PathVariable Integer restaurantId, @PathVariable Integer id) {
        LOG.info("get menu {} for restaurant {}", id, restaurantId);
        return menuService.get(id, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        restaurant.setId(null);
        LOG.info("create {}", restaurant);
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(@Validated @RequestBody Menu menu,
                                           @PathVariable Integer restaurantId) {
        menu.setId(null);
        menu.setRestaurant(null);
        LOG.info("create {} for restaurant {}", menu, restaurantId);
        Menu created = menuService.create(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/menus/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRestaurant(@Valid @RequestBody Restaurant restaurant, @PathVariable Integer id) {
        restaurant.setId(id);
        LOG.info("update {} with id={}", restaurant, id);
        restaurantService.update(restaurant);
    }

    @PutMapping(value = "/{restaurantId}/menus/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMenu(@Validated @RequestBody Menu menu,
                           @PathVariable Integer restaurantId, @PathVariable Integer id) {
        menu.setId(id);
        LOG.info("update {} with id={} for restaurant {}", menu, id, restaurantId);
        menuService.update(menu, restaurantId);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRestaurant(@PathVariable Integer id) {
        LOG.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    @DeleteMapping(value = "/{restaurantId}/menus/{id}")
    public void deleteMenu(@PathVariable Integer restaurantId, @PathVariable Integer id) {
        LOG.info("delete menu {} for restaurant {}", id, restaurantId);
        menuService.delete(id, restaurantId);
    }
}
