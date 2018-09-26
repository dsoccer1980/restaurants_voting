package ru.dsoccer1980.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.model.Restaurant;
import ru.dsoccer1980.model.User;
import ru.dsoccer1980.model.Vote;
import ru.dsoccer1980.repository.CrudRestaurantRepository;
import ru.dsoccer1980.repository.CrudUserRepository;
import ru.dsoccer1980.repository.CrudVoteRepository;
import ru.dsoccer1980.util.exception.NotFoundException;
import ru.dsoccer1980.util.exception.VoteException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class VoteServiceImpl implements VoteService {

    private final LocalTime DEADLINE = LocalTime.of(11, 0);

    private final CrudVoteRepository voteRepository;
    private final CrudUserRepository userRepository;
    private final CrudRestaurantRepository restaurantRepository;

    @Autowired
    public VoteServiceImpl(CrudVoteRepository voteRepository,
                           CrudUserRepository userRepository,
                           CrudRestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Vote save(Integer userId, Integer restaurantId, LocalDate date) {
        if (canVote(date)) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("Not found entity with id:" + userId));
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new NotFoundException("Not found entity with id:" + restaurantId));
            Optional<Vote> voteByUserIdAndDate = voteRepository.findByUserIdAndDate(userId, date);
            Vote vote = voteByUserIdAndDate.orElse(new Vote(user, restaurant, date));
            vote.setRestaurant(restaurant);
            return voteRepository.save(vote);
        } else {
            throw new VoteException("you can not vote this date");
        }
    }

    @Override
    public Vote save(Vote vote) {
        if (canVote(vote.getDate())) {
            return voteRepository.save(vote);
        } else {
            throw new VoteException("you can not vote this date");
        }
    }

    @Override
    public boolean delete(int userId, LocalDate date) {
        return voteRepository.delete(userId, date) != 0;
    }

    @Override
    public List<Vote> getVotesByRestaurantAndByDate(int id, LocalDate date) {
        return voteRepository.findByRestaurantIdAndDate(id, date);
    }

    @Override
    public List<Vote> getAllVotesByUser(int userId) {
        return voteRepository.findByUserId(userId);
    }

    @Override
    public Map<Restaurant, Long> getVotesAmountForRestaurantsByDate(LocalDate date) {
        List<Vote> votes = voteRepository.findByDate(date);
        Map<Restaurant, Long> map = new ConcurrentHashMap<>();
        votes.forEach(vote -> map.merge(vote.getRestaurant(), 1L, (k, v) -> v + 1));
        return map;
    }

    @Override
    public Vote get(int userId, LocalDate date) {
        return voteRepository.findByUserIdAndDate(userId, date).orElse(null);
    }

    private boolean canVote(LocalDate date) {
        return LocalDateTime.now().isBefore(LocalDateTime.of(date, DEADLINE));
    }
}