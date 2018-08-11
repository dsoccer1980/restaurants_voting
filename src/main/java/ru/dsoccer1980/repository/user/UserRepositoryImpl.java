package ru.dsoccer1980.repository.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.model.User;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private CrudUserRepository repository;

    @Override
    public User save(User user) {
        Objects.requireNonNull(user, "user cannot be null");
        return repository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id:" + id));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }


}
