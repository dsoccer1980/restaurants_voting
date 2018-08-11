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
            Vote vote = new Vote(user, restaurant, date);
            return voteRepository.save(vote);
        }
        else {
            throw new VoteException("you can not vote this date");
        }
    }

    @Override
    public boolean delete(int id) {
        return voteRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return voteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found entity with id:" + id));
    }

    @Override
    public List<Vote> getVotesByRestaurantAndByDate(int id, LocalDate date) {
        return voteRepository.findByRestaurantIdAndDate(id, date);
    }

    private boolean canVote(LocalDate date) {
        return LocalDateTime.now().isBefore(LocalDateTime.of(date, DEADLINE));
    }
}