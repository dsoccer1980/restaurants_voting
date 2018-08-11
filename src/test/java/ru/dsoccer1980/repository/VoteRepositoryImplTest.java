package ru.dsoccer1980.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.model.Vote;
import ru.dsoccer1980.repository.vote.VoteRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.testdata.RestaurantTestData.RESTAURANT_ID1;
import static ru.dsoccer1980.testdata.UserTestData.USER1;
import static ru.dsoccer1980.testdata.VoteTestData.*;

public class VoteRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    public void save() {
        Vote vote = new Vote(USER1, RESTAURANT1, LocalDate.now());
        Vote saveVote = voteRepository.save(vote);
        assertEquals(vote, saveVote);
    }

    @Test
    public void delete() {
        voteRepository.delete(VOTE_ID1);
        assertEquals(Arrays.asList(VOTE2), voteRepository.getAll());
    }

    @Test
    public void get() {
        Vote vote = voteRepository.get(VOTE_ID1);
        assertEquals(VOTE1, vote);
    }

    @Test
    public void getAll() {
        List<Vote> votes = voteRepository.getAll();
        assertEquals(Arrays.asList(VOTE1, VOTE2), votes);
    }

    @Test
    public void getVotesByRestaurantAndByDate() {
        List<Vote> votes = voteRepository.getVotesByRestaurantAndByDate(RESTAURANT_ID1, DATE1);
        assertEquals(Arrays.asList(VOTE1), votes);
    }

}