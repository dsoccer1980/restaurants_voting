package ru.dsoccer1980.web.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.service.DishService;
import ru.dsoccer1980.service.RestaurantService;

import java.util.List;


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
        List<Restaurant> all = restaurantService.getAll();
        return all;
    }

}