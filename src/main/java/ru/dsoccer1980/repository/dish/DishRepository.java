package ru.dsoccer1980.repository.dish;

import ru.dsoccer1980.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish restaurant);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getAll();

    List<Dish> getAllDishByRestaurantAndDate(int id, LocalDate date);
}
