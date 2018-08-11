package ru.dsoccer1980.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.model.Dish;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.dsoccer1980.testdata.DishTestData.*;
import static ru.dsoccer1980.testdata.DishTestData.DISH6;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT_ID1;

public class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    private DishService service;
    
    @Test
    public void get() {
        Dish dish = service.get((DISH_ID1));
        assertEquals(DISH1, dish);
    }

    @Test
    public void getAllDishByRestaurant() {
        List<Dish> dishes = service.getAllDishByRestaurantAndDate(RESTAURANT_ID1, DATE1);
        assertEquals(Arrays.asList(DISH1, DISH2, DISH3), dishes);
    }

    @Test(expected = NotFoundException.class)
    public void getNotExist() {
        service.get(DISH_ID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        List<Dish> dishes = service.getAll();
        assertEquals(Arrays.asList(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6), dishes);
    }

    @Test
    public void save() {
        Dish newDish = new Dish("New Dish", 55, RESTAURANT1, DATE1);
        Dish dish = service.create(newDish);
        assertEquals(newDish, dish);
    }

    @Test
    public void update() {
        DISH1.setPrice(111);
        Dish updateDish = service.update(DISH1);
        assertEquals(updateDish, DISH1);
    }

    @Test
    public void delete() {
        service.delete(DISH_ID1);
        assertEquals(Arrays.asList(DISH2, DISH3, DISH4, DISH5, DISH6), service.getAll());
    }

}