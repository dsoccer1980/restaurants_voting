package ru.dsoccer1980.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dsoccer1980.model.User;
import ru.dsoccer1980.repository.CrudUserRepository;
import ru.dsoccer1980.util.exception.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.dsoccer1980.util.ValidationUtil.checkNotFoundWithId;


@Service("userService")
public class UserServiceImpl implements UserService,UserDetailsService {

    private final CrudUserRepository repository;

    @Autowired
    public UserServiceImpl(CrudUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User get(int id) throws NotFoundException {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id:" + id));
        return checkNotFoundWithId(user, id);
    }

    @Override
    public User update(User user) throws NotFoundException {
        Assert.notNull(user, "user must not be null");
        return checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email.toLowerCase());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        org.springframework.security.core.userdetails.User grantedUser = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);

        if (!user.isNew()) {
            ru.dsoccer1980.web.AuthorizedUser.setId(user.getId());
        }
        return grantedUser;

    }

}