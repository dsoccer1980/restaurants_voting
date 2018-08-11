package ru.dsoccer1980.repository.vote;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.model.Vote;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository repository;

    @Override
    public Vote save(Vote vote) {
        Objects.requireNonNull(vote, "vote cannot be null");
        return repository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id:" + id));
    }

    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Vote> getVotesByRestaurantAndByDate(int id, LocalDate date) {
        return repository.findByRestaurantIdAndDate(id, date);
    }


}
