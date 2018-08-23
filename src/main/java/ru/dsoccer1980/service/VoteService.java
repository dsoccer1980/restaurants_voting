package ru.dsoccer1980.service;


import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface VoteService {

    Vote save(Integer userId, Integer restaurantId, LocalDate date);

    boolean delete(int id);

    Vote get(int id);

    List<Vote> getVotesByRestaurantAndByDate(int id, LocalDate date);

    List<Vote> getAllVotesByUser(int userId);

    Map<Restaurant, Long> getVotesAmountForRestaurantsByDate(LocalDate date);

}