package ru.dsoccer1980.service;


import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(int id) throws NotFoundException;
}