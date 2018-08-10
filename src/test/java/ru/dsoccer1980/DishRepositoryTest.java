package ru.dsoccer1980;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dsoccer1980.model.Dish;
import ru.dsoccer1980.repository.dish.DishRepository;
import ru.dsoccer1980.util.exception.NotFoundException;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.dsoccer1980.testdata.DishTestData.*;

import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT_ID1;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishRepositoryTest {

    @Autowired
    private DishRepository dishRepository;

    @Test
    public void get() {
        Dish dish = dishRepository.get(DISH_ID1);
        assertEquals(DISH1, dish);
    }

    @Test
    public void getAllDishByRestaurant() {
        List<Dish> dishes = dishRepository.getAllDishByRestaurantAndDate(RESTAURANT_ID1, DATE1);
        assertEquals(Arrays.asList(DISH1, DISH2, DISH3), dishes);
    }

    @Test(expected = NotFoundException.class)
    public void getNotExist() {
        Dish dish = dishRepository.get(DISH_ID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        List<Dish> dishes = dishRepository.getAll();
        assertEquals(Arrays.asList(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6), dishes);
    }

    @Test
    public void save() {
        Dish newDish = new Dish("New Dish", 55, RESTAURANT1, DATE1);
        Dish dish = dishRepository.save(newDish);
        assertEquals(newDish, dish);
    }

    @Test
    public void update() {
        DISH1.setPrice(111);
        Dish updateDish = dishRepository.save(DISH1);
        assertEquals(updateDish, DISH1);
    }

    @Test
    public void delete() {
        dishRepository.delete(DISH_ID1);
        assertEquals(Arrays.asList(DISH2, DISH3, DISH4, DISH5, DISH6), dishRepository.getAll());
    }
}
