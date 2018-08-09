package ru.dsoccer1980.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.model.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private CrudRestaurantRepository repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }


}
