package ru.dsoccer1980.testdata;

import ru.dsoccer1980.model.Dish;

import java.time.LocalDate;

import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT2;


public class DishTestData {

    public static final Integer DISH_ID1 = 100005;
    public static final Integer DISH_ID2 = 100006;
    public static final Integer DISH_ID3 = 100007;
    public static final Integer DISH_ID4 = 100008;
    public static final Integer DISH_ID5 = 100009;
    public static final Integer DISH_ID6 = 100010;
    public static final LocalDate DATE1 = LocalDate.of(2018, 07, 26);

    public static final Integer DISH_ID_NOT_EXIST = 900001;
    public static final Dish DISH1 = new Dish(
            DISH_ID1, "Borsch", 250, RESTAURANT1, DATE1);
    public static final Dish DISH2 = new Dish(
            DISH_ID2, "Cutlet", 175, RESTAURANT1, DATE1);
    public static final Dish DISH3 = new Dish(
            DISH_ID3, "Stewed fruit", 55, RESTAURANT1, DATE1);
    public static final Dish DISH4 = new Dish(
            DISH_ID4, "Saltwort", 260, RESTAURANT2, DATE1);
    public static final Dish DISH5 = new Dish(
            DISH_ID5, "Cutlet", 175, RESTAURANT2, DATE1);
    public static final Dish DISH6 = new Dish(
            DISH_ID6, "Orange juice", 40, RESTAURANT2, DATE1);
}
