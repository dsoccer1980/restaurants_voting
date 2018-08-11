package ru.dsoccer1980.service;



import ru.dsoccer1980.model.User;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.List;


public interface UserService {

    User get(int id) throws NotFoundException;

    User update(User user)  throws NotFoundException;

    List<User> getAll();

    User create(User user);

    void delete(int id) throws NotFoundException;

    User getByEmail(String email);
}