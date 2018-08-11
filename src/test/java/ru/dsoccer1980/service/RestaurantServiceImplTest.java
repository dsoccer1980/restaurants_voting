package ru.dsoccer1980.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.dsoccer1980.testdata.RestaurantTestData.*;

public class RestaurantServiceImplTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT_ID1);
        assertEquals(RESTAURANT1, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void getNotExist() {
        service.get(RESTAURANT_ID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        List<Restaurant> restaurants = service.getAll();
        assertEquals(Arrays.asList(RESTAURANT1, RESTAURANT2), restaurants);
    }

    @Test
    public void save() {
        Restaurant newRestaurant = new Restaurant("New Name", "New Address");
        Restaurant restaurant = service.create(newRestaurant);
        assertEquals(newRestaurant, restaurant);
    }

    @Test
    public void update() {
        RESTAURANT1.setAddress("New address");
        Restaurant updateRestaurant = service.update(RESTAURANT1);
        assertEquals(updateRestaurant, RESTAURANT1);
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT_ID1);
        assertEquals(Arrays.asList(RESTAURANT2), service.getAll());
    }
}
