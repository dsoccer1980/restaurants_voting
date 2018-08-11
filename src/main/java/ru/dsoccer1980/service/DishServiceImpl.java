package ru.dsoccer1980.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.model.Dish;
import ru.dsoccer1980.repository.CrudDishRepository;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.*;

import static ru.dsoccer1980.util.ValidationUtil.checkNotFoundWithId;


@Service
public class DishServiceImpl implements DishService {

    private final CrudDishRepository repository;

    @Autowired
    public DishServiceImpl(CrudDishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        Dish dish = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id:" + id));
        return checkNotFoundWithId(dish, id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.findAll();
    }

    @Override
    public Dish create(Dish dish) {
        Objects.requireNonNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    @Override
    public void delete(int dishId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(dishId), dishId);
    }

    @Override
    public Dish update(Dish dish) {
        Objects.requireNonNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    @Override
    public List<Dish> getAllDishByRestaurantAndDate(int id, LocalDate date) {
        Objects.requireNonNull(date, "date must not be null");
        return repository.findDishByRestaurantIdAndDate(id, date);
    }

}