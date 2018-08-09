package ru.dsoccer1980.repository;

import ru.dsoccer1980.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish restaurant);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getAll();

    List<Dish> getAllDishByRestaurant(int id);

    List<Dish> getDishByDate(LocalDate date);
}
