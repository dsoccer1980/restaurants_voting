package ru.dsoccer1980.repository.dish;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.model.Dish;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private CrudDishRepository repository;

    @Override
    public Dish save(Dish dish) {
        Objects.requireNonNull(dish, "restaurant cannot be null");
        return repository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id:" + id));
    }

    @Override
    public List<Dish> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Dish> getAllDishByRestaurantAndDate(int id, LocalDate date) {
        return repository.findDishByRestaurantIdAndDate(id, date);
    }

}
