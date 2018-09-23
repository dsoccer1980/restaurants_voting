package ru.dsoccer1980.web.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dsoccer1980.model.Dish;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.service.DishService;
import ru.dsoccer1980.service.RestaurantService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/rest/restaurants/";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }

    @GetMapping(value = "/{id}/dish/by")
    public List<Dish> getAllDishByRestaurantAndDate(
            @PathVariable("id") int id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return dishService.getAllDishByRestaurantAndDate(id, date);
    }

    @GetMapping(value = "/dish/by")
    public Map<Restaurant, List<Dish>> getAllDishByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return dishService.getAllDishDate(date);
    }


}