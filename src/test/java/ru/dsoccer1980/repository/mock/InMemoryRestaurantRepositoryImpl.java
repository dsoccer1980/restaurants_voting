package ru.dsoccer1980.repository.mock;

import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.dsoccer1980.testdata.RestaurantTestData.*;

public class InMemoryRestaurantRepositoryImpl implements RestaurantRepository {
    private Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        repository.put(RESTAURANT_ID1, RESTAURANT1);
        repository.put(RESTAURANT_ID2, RESTAURANT2);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            restaurant.setId(counter.incrementAndGet());
            return repository.put(restaurant.getId(), restaurant);
        } else {
            return repository.computeIfPresent(restaurant.getId(), (id, oldRestaurant) -> restaurant);
        }
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return new ArrayList<>(repository.values());
    }
}
