package ru.dsoccer1980;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.repository.restaurant.RestaurantRepository;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.dsoccer1980.testdata.RestaurantTestData.*;

public class RestaurantRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void get() {
        Restaurant restaurant = restaurantRepository.get(RESTAURANT_ID1);
        assertEquals(RESTAURANT1, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void getNotExist() {
        Restaurant restaurant = restaurantRepository.get(RESTAURANT_ID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        List<Restaurant> restaurants = restaurantRepository.getAll();
        assertEquals(Arrays.asList(RESTAURANT1, RESTAURANT2), restaurants);
    }

    @Test
    public void save() {
        Restaurant newRestaurant = new Restaurant("New Name", "New Address");
        Restaurant restaurant = restaurantRepository.save(newRestaurant);
        assertEquals(newRestaurant, restaurant);
    }

    @Test
    public void update() {
        RESTAURANT1.setAddress("New address");
        Restaurant updateRestaurant = restaurantRepository.save(RESTAURANT1);
        assertEquals(updateRestaurant, RESTAURANT1);
    }

    @Test
    public void delete() {
        restaurantRepository.delete(RESTAURANT_ID1);
        assertEquals(Arrays.asList(RESTAURANT2), restaurantRepository.getAll());
    }
}
