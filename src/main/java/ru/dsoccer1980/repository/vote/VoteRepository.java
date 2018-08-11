package ru.dsoccer1980.repository.vote;

import ru.dsoccer1980.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(int id);

    Vote get(int id);

    List<Vote> getAll();

    List<Vote> getVotesByRestaurantAndByDate(int id, LocalDate date);
}
