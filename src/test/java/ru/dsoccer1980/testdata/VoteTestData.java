package ru.dsoccer1980.testdata;

import ru.dsoccer1980.model.Vote;

import java.time.LocalDate;

import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT2;
import static ru.dsoccer1980.testdata.UserTestData.USER1;
import static ru.dsoccer1980.testdata.UserTestData.USER2;

public class VoteTestData {

    public static final Integer VOTE_ID1 = 100011;
    public static final Integer VOTE_ID2 = 100012;
    public static final LocalDate DATE1 = LocalDate.of(2018, 7, 26);
    public static final Vote VOTE1 = new Vote(
            VOTE_ID1, USER1, RESTAURANT1, DATE1);
    public static final Vote VOTE2 = new Vote(
            VOTE_ID2, USER2, RESTAURANT2, DATE1);


}
