package ru.dsoccer1980.repository.restaurant;

import ru.dsoccer1980.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();
}
