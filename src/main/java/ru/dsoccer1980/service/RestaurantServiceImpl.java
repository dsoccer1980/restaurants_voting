package ru.dsoccer1980.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.repository.CrudRestaurantRepository;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

import static ru.dsoccer1980.util.ValidationUtil.checkNotFoundWithId;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final CrudRestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id:" + id));
        return checkNotFoundWithId(restaurant, id);
    }

    @Cacheable("users")
    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);

    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant update(Restaurant restaurant) throws NotFoundException {
        Objects.requireNonNull(restaurant, "restaurant must not be null");
        return checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

}