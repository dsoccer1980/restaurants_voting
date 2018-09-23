package ru.dsoccer1980.web.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.service.RestaurantService;

import java.net.URI;


@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {

    static final String REST_URL = "/rest/admin/restaurants/";

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        restaurantService.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant) {
        restaurantService.update(restaurant);
    }


}