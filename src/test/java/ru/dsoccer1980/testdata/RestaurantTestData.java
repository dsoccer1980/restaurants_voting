package ru.dsoccer1980.testdata;

import ru.dsoccer1980.model.Restaurant;

public class RestaurantTestData {

    public static final Integer RESTAURANT_ID1 = 100003;
    public static final Integer RESTAURANT_ID2 = 100004;
    public static final Restaurant RESTAURANT1 = new Restaurant(
            RESTAURANT_ID1, "RestaurantName1", "RestaurantAddress1");
    public static final Restaurant RESTAURANT2 = new Restaurant(
            RESTAURANT_ID2, "RestaurantName2", "RestaurantAddress2");

}
