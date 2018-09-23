package ru.dsoccer1980.service;

import ru.dsoccer1980.model.Dish;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DishService {

    Dish get(int id) throws NotFoundException;

    List<Dish> getAll();

    Dish create(Dish dish);

    void delete(int dishId) throws NotFoundException;

    Dish update(Dish dish) throws NotFoundException;

    List<Dish> getAllDishByRestaurantAndDate(int id, LocalDate date);

    Map<Restaurant, List<Dish>> getAllDishDate(LocalDate date);
}