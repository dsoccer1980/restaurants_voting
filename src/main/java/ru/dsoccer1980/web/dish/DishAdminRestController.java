package ru.dsoccer1980.web.dish;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsoccer1980.model.Dish;
import ru.dsoccer1980.service.DishService;
import ru.dsoccer1980.service.RestaurantService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/dish";

    @Autowired
    private DishService dishService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping(value = "/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        log.info("create dish {} for restaurant {}", dish, restaurantId);
        dish.setRestaurant(restaurantService.get(restaurantId));
        Dish created = dishService.create(dish);
        Map<String, Integer> uriParams = new HashMap<>();
        uriParams.put("restaurantId", restaurantId);
        uriParams.put("id", created.getId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(uriParams).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete dish {}", id);
        dishService.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish) {
        log.info("update dish {}", dish);
        dish.setRestaurant(dishService.get(dish.getId()).getRestaurant());
        dishService.update(dish);
    }

}