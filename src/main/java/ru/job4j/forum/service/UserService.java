package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public List<User> getAll() {
        List<User> rsl = new ArrayList<>();
        users.findAll().forEach(rsl::add);
        return rsl;
    }

    public void save(User user) {
        users.save(user);
    }

    public List<User> findByUsername(String username) {
        return users.findByUsername(username);
    }
}
