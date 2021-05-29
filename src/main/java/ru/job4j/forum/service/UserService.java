package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(User.of("admin", "root"));
    }

    public List<User> getAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }

    public Optional<User> findByUsername(String username) {
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> findByCredentials(String username, String password) {
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
