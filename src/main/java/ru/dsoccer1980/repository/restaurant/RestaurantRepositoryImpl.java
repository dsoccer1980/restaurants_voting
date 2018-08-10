package ru.dsoccer1980.repository.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private CrudRestaurantRepository repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "restaurant cannot be null");
        return repository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id:" + id));
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }


}
