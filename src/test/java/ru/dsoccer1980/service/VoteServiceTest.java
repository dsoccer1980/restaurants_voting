package ru.dsoccer1980.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.model.Vote;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT2;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT_ID1;
import static ru.dsoccer1980.testdata.UserTestData.USER_ID1;
import static ru.dsoccer1980.testdata.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void save() {
        LocalDate date = LocalDate.now().plusDays(1);
        Vote vote = service.save(USER_ID1, RESTAURANT_ID1, date);
        assertEquals(service.getVotesByRestaurantAndByDate(RESTAURANT_ID1, date), Arrays.asList(vote));
    }

    @Test
    public void delete() {
        service.delete(VOTE_ID1);
        List<Vote> votes = service.getVotesByRestaurantAndByDate(RESTAURANT_ID1, DATE1);
        assertEquals(Collections.emptyList(), votes);
    }

    @Test
    public void get() {
        Vote vote = service.get(VOTE_ID1);
        assertEquals(VOTE1, vote);
    }


    @Test
    public void getVotesByRestaurantAndByDate() {
        List<Vote> votes = service.getVotesByRestaurantAndByDate(RESTAURANT_ID1, DATE1);
        assertEquals(Arrays.asList(VOTE1), votes);
    }

    @Test
    public void getVotesByUserId() {
        List<Vote> votes = service.getAllVotesByUser(USER_ID1);
        assertEquals(Arrays.asList(VOTE1), votes);
    }

    @Test
    public void getVotesAmountForRestaurantsByDate() {
        Map<Restaurant, Long> votesAmount = service.getVotesAmountForRestaurantsByDate(DATE1);
        Map<Restaurant, Long> expectedMap = new HashMap<>();
        expectedMap.put(RESTAURANT1, 1L);
        expectedMap.put(RESTAURANT2, 2L);
        assertEquals(expectedMap, votesAmount);
    }

}